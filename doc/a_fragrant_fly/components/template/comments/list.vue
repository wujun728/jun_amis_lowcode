<template>
    <view>

        <view class="uni-comment-list">
            <view v-if="items.headimg" class="uni-comment-face" @tap="tapBtn({key:'headimg',items,level:1})">
                <image :src="items.headimg" mode="widthFix"></image>
            </view>

            <view style="width: 100%;">
                <view class="uni-comment-right-body" style="font-size: 1em;">

                    <view class="uni-comment-top" style="font-size: 0.7em;" @tap="tapBtn({key:'nickname',items,level:1})">
                        <text>{{items.nickname}}</text>
                    </view>
                    <view @tap="tapBtn({key:'content',items,level:1})" class="uni-comment-content">{{items.content}}</view>
                    <view class="uni-comment-date uni-flex" style="justify-content: space-between;width: 100%;">
                        <view class="" style="font-size: 0.8em;opacity: 0.7;">{{items.time}}</view>
                        <view class="uni-row uni-flex-item uni-flex" style="justify-content:flex-end;" v-if="bottonObj">
                            <block v-for="(btn,key) in bottonObj" :key="key">
                                <view class="uni-row iconfont" style="padding:0 10upx;justify-content: center;align-items: center;display: flex;"
                                    :class="btn.class?btn.class:''" hover-class="uni-list-cell-hover" @tap="tapBtn({key,idx,items,level:1})">
                                    <view v-if="btn.value" :style="{'font-size':btn.size?btn.size:'','color':items['is_'+key]?'#00B2EE':''}"
                                        v-html="btn.value"></view>
                                    <view v-if="items[key+'_num']" style="padding-left: 5upx;">{{items[key+'_num']}}</view>

                                </view>
                            </block>

                        </view>

                    </view>

                </view>

                <view v-if="onShow" class="uni-reply-body uni-flex uni-row" v-for="(item,idxb) in items.reply" :key="idxb">
                    <view v-if="item.headimg" class="uni-comment-face" @tap="tapBtn({key:'headimg',item,idxb,idx,level:2})">
                        <image :src="item.headimg" mode="widthFix"></image>
                    </view>
                    <view class="uni-comment-right uni-flex-item" style="border-top:0px #E5E5E5 solid; ">
                        <view class="uni-comment-right-body">
                            <view class="uni-row uni-flex">
                                <view :style="{background:item.user_id==uid?'rgba(200,200,200,0.4)':''}" class="uni-comment-top"
                                    @tap="tapBtn({key:'nickname',item,idxb,idx,level:2})">{{item.nickname}}</view>
                                <view style="padding:0 10upx;font-size: 0.7em;" class="iconfont" @click="onUid(item.user_id,'uid')">&#xe6ff;
                                    :</view>

                            </view>
                            <view class="uni-comment-content" :style="{background:item.to_uid==uid?'rgba(200,200,200,0.4)':''}"
                                style="font-size: 0.8em;" v-if="item.to_nickname" @tap="tapBtn({key:'to_nickname',item,idxb,idx,level:2})">@
                                <text style="color:#00B2EE;display:inline">{{item.to_nickname}}</text>
                                <text style="padding:0 10upx; box-sizing: border-box;display:inline"
                                    class="iconfont" @click="onUid(item.to_uid,'uid')">&#xe6ff;</text>
                                <text style="display:inline" @tap="tapBtn({key:'content',item:item,items:items,level:2})">
                                    {{item.content}}
                                </text>
                            </view>

                            <view v-if="bottonObj" class="uni-comment-date uni-flex   ">
                                <view class="uni-flex-item" style="font-size: 0.8em;opacity: 0.7;">
                                    {{item.time}}

                                </view>
                                <view v-for="(btn,key) in bottonObj" :key="key" style="padding:0 10upx;justify-content: center;align-items: center;display: flex;"
                                    :class="btn.class?btn.class:''" class="uni-row iconfont" hover-class="uni-list-cell-hover"
                                    @tap="tapBtn({key,idx,item,idxb,level:2})">
                                    <view class="uni-row uni-flex" v-if="key!='reply'">
                                        <view v-if="btn.value" :style="{'font-size':btn.size?btn.size:'','color':item['is_'+key]?'#00B2EE':''}"
                                            v-html="btn.value"></view>
                                        <view v-if="item[key+'_num']">{{item[key+'_num']}}</view>
                                    </view>

                                </view>
                            </view>

                        </view>

                    </view>
                </view>
                <view v-if="onShow && items.have" style="font-size: 0.7em;text-align: center;padding: 5upx;" @tap="getMore(items)">
                    获取更多……
                </view>
            </view>

        </view>

    </view>
</template>

<script>
    export default {
        props: {
            items: {
                default: {
                    headimg: "https://img-cdn-qiniu.dcloud.net.cn/uniapp/images/uni@2x.png",
                    nickname: '小溜溜',
                    content: '晚了一步。被你们抢先了',
                    time: '12/5 11:23',
                    reply: [{
                        headimg: "https://img-cdn-qiniu.dcloud.net.cn/uniapp/images/uni@2x.png",
                        nickname: '小明',
                        content: '抢个小沙发',
                        time: '12/5 11:23',

                    }, {
                        headimg: "https://img-cdn-qiniu.dcloud.net.cn/uniapp/images/uni@2x.png",
                        nickname: '小刚',
                        content: '还是你快呀，又没抢到',
                        time: '12/5 11:23',

                    }]
                },
                type: Object
            },
            idx: {
                default: 0
            },
            Height: {
                default: 800,
            },
            bottonObj: {
                type: Object,
                default: function() {
                    return {
                        reply: {
                            class: 'iconfont',
                            value: '&#xe8bb;',
                            name: 'reply',
                            size: '1.2em',
                        },
                        zan: {
                            class: 'iconfont',
                            value: '&#xe876;',
                            name: 'zan',
                            size: '1.2em',
                        },
                        speak: {
                            class: 'iconfont',
                            value: '@',
                            name: 'speak',
                            size: '1.2em',
                        }

                    }
                }

            }
        },
        data() {
            return {
                title: "评论",
                onShow: null,
                // to_uid: 0,
                uid: 0
            }
        },
        methods: {
            onUid(uid, type) {

                this[type] = uid
            },
            getMore(e) {
                // this.index = idx;
                this.$emit('getMore', e)
            },
            tapBtn(e) {
                // console.log([this.index, e.idx])
                if (e.key == 'reply') {
                    this.onShow = !this.onShow;
                }
                this.$emit('change', e)

            }
        }
    }
</script>

<style>
    .uni-comment {
        padding: 0;
    }

    .uni-comment-top {
        font-size: 0.7em;
        color: rgb(0, 160, 250);
    }

    .uni-comment-list {
        padding: 0;
        flex-direction: row;
    }

    .uni-padding-wrap {
        width: 100%;
        box-sizing: border-box;
    }

    .uni-reply-body {
        box-sizing: border-box;
        padding-top: 5upx;
        padding-left: 10upx;
        /* border-bottom: 1px #E5E5E5 solid; */
    }

    .uni-comment-face {
        background: #FFFFFF;
    }

    .uni-comment-right {}

    .uni-comment-content {
        display: block;
    }

    .uni-comment-right-body {
        box-sizing: border-box;
        padding: 5upx 15upx;
        background: #FFFFFF;
        /* border-top: 1px #E5E5E5 solid; */
    }
</style>
