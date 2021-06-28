<template>

    <view class="uni-flex uni-row page-temp-tab-bar" style="z-index: 1;box-shadow:-2px 0px 2px  #888888;justify-content:space-between;width: 100vw;align-items: center;padding: 0 0px;box-sizing: border-box;"
        :style="'height:'+Height+'px;'">
        <block v-for="(item,idx) in tabBarData" :key="idx">
            <view v-if="item.type=='input'" class=" uni-flex uni-row" :style="item.style?item.style:'flex:1'" style="align-items: center;justify-content: center;padding: 0 5px;box-sizing: border-box;">
                <view class="uni-flex-item uni-flex uni-row" style="box-sizing: border-box; align-items: center;position: relative;justify-content: center;">
                    <input @tap="onTap(idx,item,item.name,'input')" @input="Input(item)" :class="[index==idx && tag=='input'?'uni-badge-primary uni-badge-inverted':'',item.value?'uni-column':'uni-row']"
                        :disabled="!item.disabled?true:false" type="text" v-model="item.value" style="text-align: left;height: 24px;line-height: 24px;box-shadow: 0px 0px 3px;border-radius: 10px;font-size: 16px;box-sizing: border-box;width: 100%;" :style="{'padding-left':item.left?'20px':'5px','padding-right':item.right?'20px':'5px'}"
                        :placeholder="item.placeholder">
                    </input>
                    <view @tap="onTap(idx,item,item.name,'right')" :class="index==idx && tag=='right'?'uni-badge-primary uni-badge-inverted':''"
                        v-if="item.right" class=" iconfont" style="font-size:12px; position: absolute;right:5px;"
                        v-html="item.right">
                    </view>
                    <view v-if="item.left" class=" iconfont" :class="index==idx && tag=='left'?'uni-badge-primary uni-badge-inverted':''"
                        @tap="onTap(idx,item,item.name,'left')" style="font-size:12px; position: absolute;left:5px;"
                        v-html="item.left">
                    </view>
                </view>
                <view class="uni-flex uni-column" style="justify-content: center;align-items: center;">
                    <view :class="index==idx && tag=='text'?'uni-badge-primary uni-badge-inverted':''" v-if="item.text"
                        @tap="onTap(idx,item,item.name,'text')" style="padding:0 8px;box-sizing: border-box;" v-html="item.text">
                    </view>
                    <view class=" iconfont" :class="index==idx && tag=='icon'?'uni-badge-primary uni-badge-inverted':''"
                        v-else-if="item.icon" @tap="onTap(idx,item,item.name,'icon')" style="padding:0 8px;box-sizing: border-box;"
                        v-html="item.icon">
                    </view>
                </view>
            </view>

            <view style="justify-content: center;align-items: center;" v-else :style="item.style?item.style:'flex:1'"
                class=" uni-flex uni-row" :class="index==idx?'uni-badge-primary uni-badge-inverted':''">

                <view v-if="item.msg" class=" uni-flex-item" style="position: relative;align-self: flex-end;">
                    <view v-if="item.msg" class="uni-badge uni-badge-danger" @tap="onTap(idx,item,item.name,'msg')"
                        style="font-size: 12px;position: absolute;right: -5upx;top: -10upx; ">
                        <text v-if="item.msg>10">…</text>
                        <text v-else>{{item.msg}}</text>
                    </view>
                </view>
                <view class="uni-flex uni-column uni-flex-item" v-if="item.icon && item.text" style="justify-content: center;align-items: center;box-sizing: border-box;">
                    <view @tap="onTap(idx,item,item.name,'icon')">
                        <view class=" iconfont" v-html="item.icon" style="font-size:22px;line-height: 28px;">
                        </view>
                        <text v-if="item.tag" @tap="onTap(idx,item,item.name,'tag')" class="uni-badge uni-badge-red"
                            style="margin-left: 10px;position: absolute;font-size: 10px; opacity: 0.8;">
                            <text>{{" "+item.tag}}</text>
                        </text>
                    </view>
                    <view @tap="onTap(idx,item,item.name,'text')" style="font-size: 10px;line-height:14px;">
                        {{item.text}}
                    </view>

                </view>

                <view class="uni-flex uni-row" v-else-if="item.icon" style="justify-content: center;align-items: center;">
                    <view class="iconfont" style="font-size:20px;" v-html="item.icon" @tap="onTap(idx,item,item.name,'icon')">

                    </view>
                    <text v-if="item.tag" @tap="onTap(idx,item,item.name,'tag')" class="" style="font-size: 10px; opacity: 0.8;padding-left: 3px;box-sizing: border-box;">
                        <text>{{" "+item.tag}}</text>
                    </text>
                </view>
                <view v-else-if="item.text" class="uni-flex" style="font-size: 26px;line-height:26px;">
                    <text style="text-align: center; " @tap="onTap(idx,item,item.name,'text')">
                        {{item.text}}
                    </text>
                    <text v-if="item.tag" @tap="onTap(idx,item,item.name,'tag')" class="uni-badge uni-badge-red" style="margin-left: 10px;position: absolute;font-size: 10px; opacity: 0.8;">
                        <text>{{" "+item.tag}}</text>
                    </text>
                </view>

            </view>

        </block>
    </view>

</template>

<script>
    export default {
        props: {
            Height: {
                default: 50
            },
            changeInput:{
                default: function() {
                    return
                },
                type: Function,
            },
            change: {
                default: function() {
                    return
                },
                type: Function,
            },
            tabBar: {
                default: function() {
                    return [{
                            flex: '1',
                            // msg: 0,
                            // name: '评论',
                            // tag:'12345',
                            icon: '&#xe8b4;',
                            url: '/pages/order/list'
                        }, {
                            flex: '1',
                            // msg: 0,
                            // name: '评论',
                            tag: '12345',
                            icon: '&#xe8bb;',
                            url: '/pages/order/list'
                        }, {
                            flex: '1',
                            // msg: 0,
                            // name: '订单',
                            // tag: '12345',
                            icon: '&#xe871;',
                            url: '/pages/order/list'
                        },
                        {
                            // msg: 1,
                            flex: '2',
                            type: 'input',
                            placeholder: '留言',
                            // name: '发送',
                            icon: '&#xe600;',
                            url: '/pages/order/list'
                        }, {
                            flex: '',
                            name: '发送',
                            url: '/pages/order/list'
                        }

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
            tabBarData() {
                return this.tabBar.filter(e => {
                    // console.log(e)
                    if (e.tag && e.tag.length > 4) {
                        e.tag = e.tag.substring(0, 4) + '+'
                    }
                    return e
                })
            }
        },
        methods: {
            Input(e){
              this.$emit('changeInput', e) 
            },
            onTap(idx, item, name, tag) {
                this.tag = tag || null;
                this.index = idx
                this.$emit('change', {
                    name: name ? name : null,
                    item,
                    idx,
                    tag: tag ? tag : null,
                    data: this.tabBarData
                })
            }
        }
    }
</script>
<style>
    .page-temp-tab-bar {
        font-size: 10px;
    }
</style>
