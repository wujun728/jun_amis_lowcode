<template>
    <view class="uni-page-body">
        <view class="uni-flex" style="flex-direction: column;width: 100%;">

            <view class="uni-input-group">

                <view class="uni-input-row" style="align-items: center;">
                    <view class="uni-label ">
                        电子邮箱
                    </view>
                    <input class="uni-flex-item" style="font-size:1em ;"  v-model="email" type="text" :placeholder="'请输入'">

                </view>
                <view v-if="isShowCaptcha" class="uni-input-row" style="align-items: center; box-sizing: border-box;padding-top: 0;padding-bottom: 0;">

                    <image class="uni-label" style="height: 80upx;" :src="captcha" mode="aspectFit" @tap="resetCaptcha"></image>

                    <input class="uni-flex-item" style="font-size:1em ;" v-model="inputCaptcha" type="text"
                        :placeholder="'请输入图片验证码'">

                </view>
                <view class="uni-input-row" style="align-items: center;">
                    <view class="uni-label ">
                        邮箱验证码
                    </view>
                    <input class="uni-flex-item" style="font-size:1em ;" v-model="code" type="text" :placeholder="'请输入'">
                    <view class="uni-flex-item uni-flex uni-row" style="box-sizing: border-box;">


                        <view class="uni-label">
                            <view v-if="CountdownBt<1" id='msg-type' @tap="getCode('email')">获取验证码</view>
                            <view v-else id='msg-type'>{{CountdownBt}}秒</view>
                        </view>

                    </view>
                </view>
            </view>
            <button type="primary" size="default" style="width: 100%;" @tap="commit">确定</button>
        </view>

    </view>
</template>

<script>
    import Request from '@/request/index.js'

    import {
        Validate
    } from '@/common/yc_js/'
    export default {
        computed: {
            loginList() {
                return this.detail
            }
        },
        data() {
            return {
                captcha: '',
                CountdownBt: 0,
                email: '18888888888@qq.com',
                detail: [],
                inputCaptcha: '',
                code: '',
                isShowCaptcha: false,
                // detail: null
                // amount:'54.6'
            }
        },
        onLoad() {

        },
        methods: {
            commit() {
                if (this.code.length < 4) {
                    uni.showModal({
                        showCancel: false,
                        title: '失败提示',
                        content: '验证码至少4位'
                    });
                    return;
                }
                var form={};
                form.email=this.email;
                // var code=that.account+that.code;
                form.code = this.code;

                Request('UserLogin_bindEmail', {
                    data: form
                }).then(res => {
                    console.log(res)
                    if (res.data.code === 200) {
                        uni.showToast({
                            title: '绑定成功'
                        })
                        this.detail = res.data.data
                    } else {
                        uni.showToast({
                            title: res.data.message|| '失败',
                            icon:'none'
                        })
                    }
                })
            },
            getCode(type) {

                switch (type) {
                    case 'email':
                        this.getPhoneCode();
                        break;
                    case 'email':
                        this.getEmailCode();
                        break;
                    default:
                        this.getEmailCode();
                        break;
                }
            },
            getPhoneCode() {

                var inputCaptcha = this.inputCaptcha;

                let rule = (tp) => {
                    return [{
                        checkType: tp,
                        name: 'account',
                        errorMsg: '这个错拉'
                    }];
                };

                let data = {
                    account: this.email
                };


                if (!Validate.check(data, rule('emailno'))) {
                    uni.showToast({
                        icon: 'none',
                        title: '手机号不正确',
                    });
                    return;
                }

                if (!this.isShowCaptcha) {
                    this.isShowCaptcha = true;
                    uni.showToast({
                        icon: 'none',
                        title: '请输入图片验证码',
                    });
                    this.resetCaptcha();
                    return;
                }
                if (!inputCaptcha || inputCaptcha.length < 4) {
                    uni.showModal({
                        showCancel: false,
                        title: '失败提示',
                        content: '图片验证码至少4位'
                    });
                    return;
                }
                Request('UserVerify_SendEmail', {
                    data: {
                        code: inputCaptcha,
                        email: this.email
                    }
                }).then(res => {
                    // console.log(res);
                    if (res.data.code === 200) {
                        uni.showToast({
                            icon: 'none',
                            title: res.data.message + ',手机验证码:' + res.data.code,
                            duration: 3000
                        });
                        var that = this;
                        // 倒计时秒
                        function settime(num) {
                            var num = that.CountdownBt;
                            if (num > 0) {
                                num--;
                                that.CountdownBt = num;
                                setTimeout(() => {
                                    settime()
                                }, 1000)
                            }
                        }
                        that.CountdownBt = 90
                        settime();
                    } else {
                        this.failAccount[this.account] = this.account;

                        this.resetCaptcha(); //刷新验证码	
                        uni.showModal({
                            showCancel: false,
                            title: '失败提示',
                            content: res.data.message ? res.data.message : '未知'
                        });

                    }

                })

            },
            resetCaptcha() {

                var that = this;
                Request('Captcha', {
                    data: {
                        outType: 'base64',
                        sid: this.email
                    }
                }, {
                    // responseType: 'arraybuffer',
                }).then(res => {
                    // console.log(res)
                    if (res.data) {
                        that.isShowCaptcha = true;
                        that.captcha = res.data;
                    }
                }).catch(res => {
                    // console.log(res)
                })
            },
            tapTag(type) {
                switch (type) {
                    case 'weixin':
                        uni.showToast({
                            title: '暂未开通',
                            icon: 'none'
                        })
                        break;
                    case 'email':
                        // 去支付
                        var url = '/pages/security/my/bindPhone';
                        uni.navigateTo({
                            url: url
                        })
                        break;
                    default:
                        break;
                }
            },
        }
    }
</script>

<style>
</style>
