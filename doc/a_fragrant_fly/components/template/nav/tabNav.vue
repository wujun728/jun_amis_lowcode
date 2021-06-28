<template>

    <view class="uni-flex uni-column  " style="width: 100%;box-sizing: border-box;" :style="[theStyle,styleShadow,styleHeight]"
        :class="[theClass?theClass:'']">

        <view class="" v-if="isHeader" :style="'height:'+statusBarHeight+'px'">
        </view>
        <view class="uni-flex uni-row " style="justify-content:space-between;position: relative;top: 0;align-items: center;"
            :style="{height:Height+'px',width:Width+'px'}">
            <block v-if='back'>
                <view class="iconfont uni-flex uni-row" style="justify-content: center;width: 30px;" @tap="onBack"
                    v-html="backText"></view>
            </block>

            <block v-if='title'>
                <view class="uni-flex uni-row uni-flex-item yc-tab-bar-title" style="justify-content: center;">{{title}}</view>
            </block>
            <block v-for="(item,idx) in tabBarData" :key="idx">
                <view :hover-class="hoverClass?hoverClass:'hover-class'" :style="item.style?item.style:'flex:1'" class="uni-flex uni-row"
                    :class="[item.class?item.class:'',item.name=='title'?'yc-tab-bar-title':'']" style="white-space:nowrap;overflow:hidden;text-overflow:ellipsis;box-sizing: border-box;padding: 0 5px; box-sizing: border-box;align-items: center; justify-content: flex-start; height: 100%;position: relative;">

                    <view v-if="item.type=='input'" class=" uni-flex uni-row  uni-flex-item " style="align-items: center;justify-content: center;">
                        <view class="uni-flex-item uni-flex uni-row" style="height: 22px; align-items: center;position: relative;justify-content: center;">
                            <input @tap="onTap({idx,item,name:item.name,tag:'input'})" :class="index==idx && tag=='input'?'':''"
                                :disabled="!item.disabled?true:false" class="uni-flex-item" v-model="item.value" style="width: 100%; box-shadow: 0px 0px 3px;padding: 0 20px;border-radius: 10px;font-size: 16px;box-sizing: border-box;"
                                :style="{paddingLeft:item.left?'22px':'6px',paddingRight:item.right?'22px':'6px'}"
                                :placeholder="item.placeholder">
                            </input>
                            <view @tap="onTap({idx,item,name:item.name,tag:'right'})" :class="index==idx && tag=='right'?'uni-badge-primary uni-badge-inverted':''"
                                v-if="item.right" class=" iconfont" style="font-size:1em; position: absolute;right:6px;"
                                v-html="item.right">
                            </view>
                            <view v-if="item.left" class=" iconfont" :class="index==idx && tag=='left'?'uni-badge-primary uni-badge-inverted':''"
                                @tap="onTap({idx,item,name:item.name,tag:'left'})" style="font-size:1em; position: absolute;left:6px;"
                                v-html="item.left">
                            </view>
                        </view>
                        <view class="uni-flex uni-column" style="justify-content: center;align-items: center;padding:0 8px;box-sizing: border-box;height: 100%;margin-top: -2px;">
                            <view :class="index==idx && tag=='text'?'uni-badge-primary uni-badge-inverted':''" v-if="item.text"
                                @tap="onTap({idx,item,name:item.name,tag:'text'})" style="" v-html="item.text">
                            </view>
                            <view class=" iconfont" :class="index==idx && tag=='icon'?'uni-badge-primary uni-badge-inverted':''"
                                v-else-if="item.icon" @tap="onTap({idx,item,name:item.name,tag:'icon'})" v-html="item.icon">
                            </view>
                        </view>


                    </view>

                    <view v-else class=" uni-flex uni-row " style="align-items: center;justify-content: flex-start;">

                        <view v-if="item.msg" class="uni-row uni-flex-item" style="position: relative;align-self: flex-end;">
                            <view v-if="item.msg" class="uni-badge uni-badge-danger" @tap="onTap({idx,item,name:item.name,tag:'msg'})"
                                style="font-size: 0.7em;position: absolute;right: -4px;top: -8px; ">
                                <text v-if="item.msg>10">…</text>
                                <text v-else>{{item.msg}}</text>
                            </view>
                        </view>
                        <view class="uni-flex uni-column" v-else-if="item.icon && item.text" style="justify-content: center;align-items: center;font-size:14px;">
                            <view style="position: relative;font-size:14px;">
                                <view @tap="onTap({idx,item,name:item.name,tag:'icon'})" class=" iconfont" style="font-size:1.4em;line-height: 1.3em;margin-top: -10upx;"
                                    v-html="item.icon">
                                </view>
                                <view v-if="item.tag" @tap="onTap({idx,item,name:item.name,tag:'tag'})" class="uni-flex uni-row"
                                    style="position: absolute;top: 0;width: 100%;">
                                    <text style="font-size: 0.8em; opacity: 0.8;left: 23px; position: absolute;top:-5px;width: 100%;">{{" "+item.tag}}</text>
                                </view>
                            </view>

                            <view @tap="onTap({idx,item,name:item.name,tag:'text'})" style="font-size: 0.7em;line-height:0.7em;">
                                {{item.text}}
                            </view>
                        </view>

                        <view class="uni-flex uni-row" v-else-if="item.icon" style="justify-content: center;align-items: center;">
                            <view class="iconfont" style="font-size:1.4em;line-height: 1.3em;" v-html="item.icon" @tap="onTap({idx,item,name:item.name,tag:'icon'})">
                            </view>
                            <view v-if="item.tag" @tap="onTap({idx,item,name:item.name,tag:'tag'})" class="uni-flex uni-column"
                                style="justify-content: center;">
                                <text style="font-size: 0.8em; opacity: 0.8;">{{" "+item.tag}}</text>
                            </view>
                        </view>
                        <view v-else-if="item.text" class="uni-flex uni-row " style="font-size: 1em;line-height:1em;">
                            <view class="iconfont" v-if="item.leftIcon" style="padding-right:2px;box-sizing: border-box;left: 10px;"
                                :style="item.leftIconStyle?item.leftIconStyle:''" v-html="item.leftIcon" @tap="onTap({idx,item,name:item.name,tag:'leftIcon'})">
                            </view>
                            <view style="text-align: center;" @tap="onTap({idx,item,name:item.name,tag:'text'})">
                                {{item.text}}
                            </view>

                            <view :style="item.rightIconStyle?item.rightIconStyle:''" class="iconfont" v-if="item.rightIcon"
                                style="padding-left: 2px;box-sizing: border-box;right: 10px;" v-html="item.rightIcon"
                                @tap="onTap({idx,item,name:item.name,tag:'rightIcon'})">
                            </view>
                            <view v-if="item.tag" @tap="onTap({idx,item,name:item.name,tag:'tag'})" class="uni-badge "
                                style="font-size: 0.8em; opacity: 0.8;">
                                <text>{{" "+item.tag}}</text>
                            </view>
                        </view>
                        <view v-else-if="item.title" class="uni-flex yc-tab-bar-title uni-row">
                            <text style="text-align: center; " @tap="onTap({idx,item,name:item.name,tag:'text'})">
                                {{item.title}}
                            </text>

                        </view>
                    </view>
                </view>

            </block>
            <block v-if="btn">
                <view class="iconfont uni-flex uni-row" style="justify-content: center;width:30px" @tap="onTap({name:'btn'})"
                    v-html="btnText">
                </view>
            </block>

        </view>
    </view>


</template>

<script>
    var Height = 44; //导航高度
    export default {
        props: {
            hoverClass: {
                default: ''
            },
            isHeader: {
                default: false
            },
            back: {
                default: ''
            },
            title: {
                type: String,
                default: ""
            },
            btn: {
                default: ''
            },
            theClass: {
                type: String,
                default: ""
            },
            theStyle: {
                type: Object,
                default: function() {
                    return {
                        background: "#F7F7F7"
                    }
                }
            },
            shadow: {
                type: String,
                default: "bottom"
            },
            change: {
                default: function() {
                    return
                },
                type: Function,
            },
            Height: {
                default: function() {
                    return Height
                },
                type: Number
            },
            tabBar: {
                default: function() {
                    return [
                        //     {
                        //     style: 'flex: 8',
                        //     type: 'text',
                        //     text: '通讯录',
                        //     name: 'title',
                        //     noTap: true,
                        //     // left: '&#xe7b1;',
                        // }
                    ]
                },
                type: Array,
            },
        },
        name: "bottomNav",
        data() {
            return {
                index: 0,
                tag: '',
            }
        },
        computed: {
            styleHeight() {
                var height = {
                    height: (this.Height + this.statusBarHeight) + ':px'
                };
                return height;
            },
            statusBarHeight() {
                // console.log(this.systemInfo.statusBarHeight)
                var hei = this.systemInfo.statusBarHeight || 0;
                return hei
            },
            systemInfo() {
                return uni.getSystemInfoSync()
            },
            Width() {
                var wid = this.systemInfo.windowWidth || 540
                var width = wid;
                if (this.isHeader) {
                    // #ifdef MP-WEIXIN
                    // 微信小程序 减去胶囊的宽度
                    width = wid - 92;
                    // #endif   
                }
                return width;
            },
            backText() {
                var back = this.back;
                if (back) {
                    var text = '';
                    switch (back) {
                        case ' ':
                            text = ''
                            break;
                        case true:
                            // 返回
                            text = '&#xe95b;'
                            break;
                        default:
                            text = back
                            break;
                    }
                    return text;
                }
                return ' ';
            },
            btnText() {
                var btn = this.btn;
                if (btn) {
                    var text = '';
                    switch (btn) {
                        case '':
                            text = ''
                            break;
                        case true:
                            // 返回
                            text = '&#xe7a5;'
                            break;
                        default:
                            text = btn
                            break;
                    }
                    return text;
                }
                return '';
            },
            styleShadow() {
                var direction = '';
                var style = {};
                switch (this.shadow) {
                    case 'top':

                        break;
                    case 'full':
                        break;
                    default:
                        direction = 'buttom';
                        break;
                }
                style = {
                    boxShadow: "0px 1px 0px 0px #888888"
                }
                return style;
            },
            height() {
                return this.statusBarHeight + this.Height;
            },
            tabBarData() {
                return this.tabBar.filter(e => {
                    if (e.tag && e.tag.length > 2) {
                        e.tag = e.tag.substring(0, 2) + '+'
                    }
                    if (this.title && e.name == 'title') {
                        e.text = this.title
                    }
                    return e
                })
            }
        },
        methods: {

            onBack() {
                if (this.back == true || this.back == 1) {
                    uni.navigateBack()
                } else {
                    this.$emit('change', {
                        name: 'back'
                    })
                }
            },
            onTap(item) {
                if (item.noTap) {
                    // 被禁止点击的元素
                    return;
                }
                // this.index = idx
                item.data = this.tabBarData
                this.$emit('change', item)
            }
        }
    }
</script>
<style lang="scss">
    .hover-class {
        opacity: 0.8;
    }

    .topshadow {
        color: #1AAD19;
    }
</style>
