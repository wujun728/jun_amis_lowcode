<template>

    <view v-if="param" style="padding: 0;margin: 0;position: absolute;box-sizing: border-box;width: 100%;height: 100%;z-index: 99;justify-content: center;align-items: center;background: rgba(0,0,0,0.5)"
        class="uni-flex uni-column">
        <view style="border-radius:15upx 20upx;overflow: auto;" :style="pageStyle?pageStyle:''">

            <view style="justify-content: center;align-items: center; " class="uni-flex" :class="pageClass?pageClass:''">
                <view class="uni-flex uni-column " style="position: relative;background: #FFFFFF;width: 100%;border-box;border-radius: 20upx;justify-content:space-between;align-items: center;padding: 20upx 40upx;box-sizing: border-box; ">
                    <view @tap="close" style="font-weight: 200;font-size: 2em;text-align: right;position: absolute; z-index: 99;top:-10upx;right: 30upx;">
                        x
                    </view>
                    <view v-if="param.title" class="" style="font-weight:550;font-size: 1.2em; height:2em;position:relative ;">
                        {{param.title}}
                    </view>
                    <view style="max-height:50vh;position: relative;overflow-y: scroll;width: 100%;" id="scrollview">
                        <block v-for="(items,idx) in param.content" :key="idx">


                            <view class="uni-flex uni-flex-item uni-column" style="width: 100%;justify-content:space-between; box-sizing: border-box;">

                                <view v-if="items.type=='input'" class="uni-flex-item  uni-flex uni-column" style="width: 100%;box-sizing: border-box;">
                                    <view class="uni-flex " style="padding: 5upx;width: 100%;box-sizing: border-box; "
                                        v-for="(item,idxb) in items.items" :key="idxb">
                                        <view :class="items.class?items.class:''" :style="items.style?items.style:''"
                                            class="uni-flex uni-flex-item" style="justify-content: center;align-items: center;"
                                            v-if="items.type=='input'">

                                            <view v-if="item.text" class="uni-flex-item" :class="item.class?item.class:''">
                                                <text>{{item.text}}</text>
                                            </view>

                                            <input :maxlength="item.maxlength?item.maxlength:'20'" @click="tapBtn({idx,idxb,item,items,content:param.content})"
                                                :class="items.style?'':'uni-flex-item'" :placeholder="item.placeholder"
                                                v-model="item.value" style="font-size:0.8em; padding:0 5upx;box-sizing: border-box;box-shadow: 0px 0px 1px #888888;height: 2.2em;width: 100%;"
                                                :style="item.style?item.style:'flex:2'">

                                            </input>

                                        </view>
                                    </view>

                                </view>
                                <block v-else-if="items.type=='textarea'">
                                    <view class="uni-flex uni-flex-item uni-column" style="width: 100%;box-sizing: border-box;">
                                        <block v-for="(item,idxb) in items.items" :key="idxb">
                                            <view class="uni-flex " style="padding: 5upx;width: 100%;box-sizing: border-box; flex-shrink: 0;">
                                                <view :class="items.class?items.class:''" :style="items.style?items.style:''"
                                                    class="uni-flex uni-flex-item" style="justify-content: center;align-items: center;height: 100%;">
                                                    <view v-if="item.text" class="uni-flex-item" :class="item.class?item.class:''">
                                                        <text style="">{{item.text}}</text>
                                                    </view>
                                                    <view style="box-sizing: border-box;min-height: 100upx;width: 100%;">
                                                        <textarea @click="tapBtn({idx,idxb,item,items,content:param.content})"
                                                            :class="items.style?'':'uni-flex-item'" :placeholder="item.placeholder"
                                                            v-model="item.value" :style="item.style?item.style:'flex:2'"
                                                            :maxlength="item.maxlength?item.maxlength:'200'"
                                                            :auto-height="item.autoheight?true:false" style="width: 100%;height: 100upx;padding:0 5upx;box-sizing: border-box;box-shadow: 0px 0px 1px #888888;">
                                            </textarea>
                                                    </view>

                                                </view>
                                            </view>
                                        </block>
                                    </view>
                                </block>
                                <block v-else-if="items.type=='checkbox'">
                                    <view class="uni-flex uni-flex-item uni-column" style="width: 100%;box-sizing: border-box;">
                                        <block v-for="(item,idxb) in items.items" :key="idxb">
                                            <view class="uni-flex " style="padding: 5upx;width: 100%;box-sizing: border-box; flex-shrink: 0;">
                                                <view :class="items.class?items.class:''" :style="items.style?items.style:''"
                                                    class="uni-flex uni-flex-item" style="justify-content: center;align-items: center;">

                                                    <checkbox-group @change="checkboxChange(item)">
                                                        <label class="uni-list-cell uni-list-cell-pd">
                                                            <view>{{item.value}}</view>
                                                            <view>
                                                                <checkbox :checked="item.checked" />
                                                            </view>

                                                        </label>
                                                    </checkbox-group>
                                                </view>
                                            </view>
                                        </block>

                                    </view>
                                </block>
                                <block v-else>
                                    <view :class="items.class?items.class:''" :style="items.style?items.style:''" class="uni-flex-item  uni-flex"
                                        style="width: 100%;box-sizing: border-box;">
                                        <view class="uni-flex " style="padding: 5upx;width: 100%;box-sizing: border-box; "
                                            v-for="(item,idxb) in items.items" :key="idxb">

                                            <button v-if="items.type=='button'" :style="item.style?item.style:''"
                                                :class="item.class?item.class:'uni-flex-item'" :type="item.type?item.type:''"
                                                :size="item.size?item.size:'mini'" @click="tapBtn({idx,idxb,item,items,content:param.content})">{{item.text}}</button>

                                            <view v-else v-html="item.text" :style="item.style?item.style:''" @click="tapBtn({idx,idxb,item,items,content:param.content})"
                                                :class="item.class?item.class:'uni-flex-item'">

                                            </view>
                                        </view>
                                    </view>
                                </block>


                            </view>
                        </block>
                    </view>
                    <view v-if="param.button" class="uni-flex-item  uni-flex" style="width: 100%;box-sizing: border-box;">
                        <view class="uni-flex " style="padding: 5upx;width: 100%;box-sizing: border-box; " v-for="(item,idxb) in param.button.items"
                            :key="idxb">

                            <button v-if="!param.button.type || param.button.type=='button'" :style="item.style?item.style:''"
                                :class="item.class?item.class:'uni-flex-item'" :type="item.type?item.type:''" :size="item.size?item.size:'mini'"
                                @click="tapBtn({type:'button',idx:idxb,item,items:param.button.items,content:param.content})">{{item.text}}</button>
                            <view v-else v-html="item.text" :style="item.style?item.style:''" @click="tapBtn({type:'button',idx:idxb,item,items:param.button.items,content:param.content})"
                                :class="item.class?item.class:'uni-flex-item'">
                            </view>
                        </view>
                    </view>
                    <view v-if="param.footer" class="" style="font-weight: 100;font-size: 0.7em;padding:10upx;">
                        {{param.footer}}
                    </view>
                </view>

            </view>
        </view>
    </view>
</template>

<script>
    //开启，传参 this.show({title:'提示',content{type:button|input|textarea|checkbox}});

    // 关闭this.show();传空置关闭
    export default {

        props: {
            content: {
                type: Object,
                default: function() {
                    return null;
                }
            },
            change: {
                type: Function,
                default: function() {
                    return;
                }
            },
            pageClass: {
                type: String,
                default: function() {
                    return " "
                }
            },
            pageStyle: {
                type: String,
                default: function() {
                    return "min-height:50%;max-height:90%;width:85%"
                }
            },
        },
        data() {
            return {
                height: '',
                param: null
                // content: {
                //     title: '提示',
                //     footer: '底部',
                //     button:{type:button,items[]}
                //     content: [{
                //             type: 'input',
                //             style: '',
                //             // class: 'uni-column uni-flex-item uni-flex',
                //             items: [{
                //                 // style: 'flex:1',
                //                 // class: 'uni-flex uni-row uni-flex-item',
                //                 value: '挺好的',
                //                 name: 'queren',
                //                 text: '标题'
                //             }, {
                //                 // style: 'flex:2',
                //                 // class: 'uni-flex uni-row uni-flex-item',
                //                 value: '挺好的',
                //                 name: 'queren',
                //                 text: '出生地区'
                //             }]
                //         }]
                //     }
            }
        },
        watch: {

            content: {
                handler(val) {
                    // console.log(val)
                    this.param = val
                },
                deep: true
            },

            param: {
                handler(value, old) {
                    var val = '';
                    var oldVal = '';
                    if (value && typeof value == 'object') {
                        val = value.content || value;
                    }
                    if (old && typeof old == 'object') {
                        oldVal = old.content || old;
                    }


                    if (this.param && this.param.watch && typeof this.param.watch == 'function') {
                        this.param.watch(val, oldVal)
                    }

                    this.$emit('change', val, oldVal)


                },
                deep: true
            }
        },

        methods: {
            checkboxChange(e) {
                if (e.checked) {
                    e.checked = false
                } else {
                    e.checked = true
                }
                // console.log(e)
            },
            close() {
                this.param = null;
            },
            show(param) {
                this.param = param
            },
            tapBtn(param) {
                // console.log(param)
                param.name = param.item.name || '';
                param.type = param.type || param.items.type || param.item.type || '';
                if (this.param && this.param.success && typeof this.param.success == 'function') {
                    this.param.success(param)
                } else if (param.type == 'button') {
                    this.param = null;
                }
            }
        }
    }
</script>

<style>
</style>
