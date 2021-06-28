<template>
    <view class="m-item uni-column" style="position: relative;">
        <view v-if="toUser.nickname && !isMe" class="" style="align-self: flex-start;font-size: 0.6em;">
            <text style="color:#C8C7CC;padding: 5px 21upx; font-weight: 200;">{{toUser.nickname}}</text>

        </view>
        <view v-else-if="userInfo.nickname && isMe" class="" style="align-self: flex-end;font-size: 0.6em;">
            <text style="color:#C8C7CC;padding: 5px 21upx; font-weight: 200;">{{userInfo.nickname}}</text>

        </view>
        <view class="uni-flex uni-row">
            <view class="m-left" @click="tapMsg({type:'headimg',isMe:isMe,data:data})">

                <image @error="imgError(isMe)" class="head_icon" mode="aspectFill" :src="errImg?image:headimg" v-if="!isMe"></image>
            </view>
            <view class="m-content">

                <view class="m-content-head" :class="!isMe?'m-content-head-left':'m-content-head-right'">
                    <view class="uni-flex uni-column" style="justify-content: center;"  v-if="data.send==1" @tap="tapFail(data)">
                        <text style="background: #F76260; color: #fff;border-radius: 50%;width: 1.6em;height: 1.6em;text-align: center;line-height: 1.5em;">!</text>
                    </view>
                    <view :class="!isMe?'m-content-head-home':'m-content-head-customer'">

                        <view v-if="data.type">
                            <view v-if="data.type=='text'">
                                {{data.content}}
                            </view>
                            <view v-else-if="data.type=='outreach'"  class="uni-flex uni-row" :style="{height: outreachHeight+'px'}"
                                style="justify-content: space-between;background: #FFFFFF;box-sizing: border-box;max-width: 400upx;color: #2C405A;" @tap="tapMsg({type:'outreach',data:data})">
                                <view class="" :style="{height: outreachHeight+'px'}">
                                    <image style="box-sizing: border-box;padding: 3px;" :style="{height: outreachHeight+'px',width:outreachHeight+'px'}"
                                        :src="data.content.image" mode="aspectFill"></image>
                                </view>
                                <view class="uni-flex uni-column uni-flex-item" style=" justify-content: space-between;padding-bottom: 4px;width: 100%;white-space: nowrap;overflow:hidden;text-overflow: ellipsis;">
                                    <view class="uni-row" style="white-space: nowrap;overflow:hidden;text-overflow: ellipsis;text-align: left;">
                                        {{data.content.title}}
                                    </view>
                                    <view class="" style="white-space: nowrap;overflow:hidden;text-overflow: ellipsis;font-size: 0.8em;width: 100%;">
                                        <text style="width: 100%;">{{data.content.text}}</text>
                                    </view>
                                </view>
<!--                                <view class="uni-column uni-flex" style="white-space: nowrap;justify-content: center;align-items: center;width: 100px;">
                                    <button type="warn" style="background: #F76260;" size="mini">打开</button>
                                </view> -->
                            </view>
                        </view>
                        <view v-else class="">
                            {{data.content}}
                        </view>

                    </view>
                </view>
            </view>
            <view class="m-right" @click="tapMsg({type:'headimg',isMe:isMe,data:data})">
                <image @error="imgError(isMe)" class="head_icon" mode="aspectFill" :src="errImg?image:headimg" v-if="isMe"></image>
            </view>
        </view>

        <view v-if="data.datetime" class="uni-flex" style="justify-content: center;font-size: 0.7em;width: 100%;bottom: 0upx;">
            <text style="color: #C8C7CC;padding:0 5upx 0 5upx;font-weight: 100;">{{data.datetime}}</text>
        </view>
    </view>
</template>

<script>
    export default {
        props: ['message', 'index', 'userInfo', 'toUser'],
        data() {
            return {
                outreachHeight:50,
                errImg: '',
                image: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564475586166&di=4e41030ee36f44e165227b676572ef48&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201801%2F25%2F20180125173013_sveub.jpg'
                // headimg: null
            }
        },
        methods: {
            tapFail(e){
                // console.log(e)
              this.$emit('tapFail',e)  
            },
            tapMsg(e){
              this.$emit('tapMsg',e)  
            },
            imgError(e) {
                this.errImg = true
            }
        },
        computed: {
            headimg() {
                if (this.isMe) {
                    return this.userInfo.headimg || '';
                } else {
                    return this.toUser.headimg
                }
            },
            data() {
                return this.message
            },

            isMe() {
                if (this.message.uid == this.userInfo.id) {
                    return true
                } else {
                    return false
                }
            }
        }
    }
</script>

<style>
    view {
        padding: 0px;
        width: auto;
    }

    .m-item {
        justify-content: space-between;
        display: flex;
        width: 100%;
        padding-bottom: 10px;
    }

    .m-left {
        
        flex-direction: row;
        box-sizing: border-box;
        display: flex;
        width: 120upx;
        justify-content: center;
        /* align-items: flex-start; */
    }

    .m-content {
        box-sizing: border-box;
        display: flex;
        flex: 1;
        flex-direction: column;
        justify-content: center;
        /* word-break: break-all; */
    }

    .m-right {
        flex-direction: row;
        box-sizing: border-box;
        display: flex;
        width: 120upx;
        justify-content: center;
        /* align-items: flex-end; */
    }

    .head_icon {
        width: 80upx;
        height: 80upx;
    }

    .m-content-head {
        display: flex;
        justify-content: space-between;
        flex-direction: row;
        position: relative;
    }

    .m-content-head-left {
        text-align: left;
        justify-content: flex-start;

    }

    .m-content-head-right {
        text-align: right;
        justify-content: flex-end;
    }

    .m-content-head-home {

        justify-content: flex-start;
        text-align: left;
        background: #1482d1;
        border: 1px #1482d1 solid;
        border-radius: 20upx;
        padding: 15upx;
        color: white;
    }

    .m-content-head-home:before {
        border: 15upx solid transparent;
        border-right: 15upx solid #1482d1;
        left: -26upx;
        width: 0;
        height: 0;
        position: absolute;
        content: ' '
    }

    .m-content-head-customer {
        border: 1upx white solid;
        background: white;
        border-radius: 20upx;
        padding: 15upx;
    }

    .m-content-head-customer:after {
        border: 15upx solid transparent;
        border-left: 15upx solid white;
        top: 20upx;
        right: -26upx;
        width: 0;
        height: 0;
        position: absolute;
        content: ' '
    }
</style>
