<template>
    <view>
        <view class="uni-padding-wrap" style="font-size: 20px;">
            <view style="background:#FFF; padding:50upx 0;">
                <view class="uni-hello-text uni-center">支付金额</text></view>
                <view class="uni-h1 uni-center uni-common-mt uni-row" style="align-items: center;font-size: 2em;">
                    <text class="rmbLogo">￥</text>
                    <input class="price" type="digit" :value="price" maxlength="7" @input="priceChange" />
                </view>
            </view>
            <view class="uni-btn-v uni-common-mt">
                <!-- #ifndef APP-PLUS -->
                <view class="uni-flex uni-column" style="align-items: center;">
                    <text>暂时仅支持app端支付</text>
                    <!-- <button class='wepay' @tap='goBaidu'>点击下载</button> -->
                </view>
                <!-- #endif -->
                <!-- #ifdef MP-WEIXIN -->
                <!-- <button type="primary" @click="weixinPay" :loading="loading">微信支付</button> -->
                <!-- #endif -->
                <!-- #ifdef APP-PLUS -->
                <template v-if="providerList.length > 0">
                    <button v-for="(item,index) in providerList" :key="index" @click="requestPayment(item,index)"
                        :loading="item.loading">{{item.name}}支付</button>
                </template>
                <!-- #endif -->
            </view>
        </view>
    </view>
    </view>
</template>
<script>
    import Request from "@/request/index.js";
    export default {
        data() {
            return {
                title: '钱包充值',
                loading: false,
                price: 1,
                providerList: []
            }
        },
        onLoad: function() {
            // #ifdef APP-PLUS
            uni.getProvider({
                service: "payment",
                success: (e) => {
                    console.log("payment success:" + JSON.stringify(e));
                    let providerList = [];
                    e.provider.map((value) => {
                        switch (value) {
                            case 'alipay':
                                providerList.push({
                                    name: '支付宝',
                                    id: value,
                                    loading: false
                                });
                                break;
                            case 'wxpay':
                                providerList.push({
                                    name: '微信',
                                    id: value,
                                    loading: false
                                });
                                break;
                            default:
                                break;
                        }
                    })
                    this.providerList = providerList;
                },
                fail: (e) => {
                    console.log("获取支付通道失败：", e);
                }
            });
            // #endif
        },
        methods: {
            // goBaidu() {
            //     // 去下载app
            //     var info = {
            //         url: 'http://zf.01film.cn/__UNI__5C4411E_0820231819.apk'
            //     }
            //     uni.navigateTo({
            //         // url: '/pages/update/index?query=' +
            //         //     encodeURIComponent(JSON.stringify(info))
            //         url: "/components/template/out/down"
            //     })
            // },
            weixinPay() {
                console.log("发起支付");
                this.loading = true;
                uni.login({
                    success: (e) => {
                        console.log("login success", e);
                        uni.request({
                            url: `https://unidemo.dcloud.net.cn/payment/wx/mp?code=${e.code}&amount=${this.price}`,
                            success: (res) => {
                                console.log("pay request success", res);
                                if (res.statusCode !== 200) {
                                    uni.showModal({
                                        content: "支付失败，请重试！",
                                        showCancel: false
                                    });
                                    return;
                                }
                                if (res.data.ret === 0) {
                                    console.log("得到接口prepay_id", res.data.payment);
                                    let paymentData = res.data.payment;
                                    uni.requestPayment({
                                        timeStamp: paymentData.timeStamp,
                                        nonceStr: paymentData.nonceStr,
                                        package: paymentData.package,
                                        signType: 'MD5',
                                        paySign: paymentData.paySign,
                                        success: (res) => {
                                            uni.showToast({
                                                title: "感谢您的赞助!"
                                            })
                                        },
                                        fail: (res) => {
                                            uni.showModal({
                                                content: "支付失败,原因为: " + res
                                                    .errMsg,
                                                showCancel: false
                                            })
                                        },
                                        complete: () => {
                                            this.loading = false;
                                        }
                                    })
                                } else {
                                    uni.showModal({
                                        content: res.data.desc,
                                        showCancel: false
                                    })
                                }
                            },
                            fail: (e) => {
                                console.log("fail", e);
                                this.loading = false;
                                uni.showModal({
                                    content: "支付失败,原因为: " + e.errMsg,
                                    showCancel: false
                                })
                            }
                        })
                    },
                    fail: (e) => {
                        console.log("fail", e);
                        this.loading = false;
                        uni.showModal({
                            content: "支付失败,原因为: " + e.errMsg,
                            showCancel: false
                        })
                    }
                })
            },
            async requestPayment(e, index) {
                this.providerList[index].loading = true;
                let orderInfo = await this.getOrderInfo(e.id);
                console.log("得到订单信息", orderInfo);
                if (orderInfo.statusCode !== 200) {
                    console.log("获得订单信息失败", orderInfo);
                    uni.showModal({
                        content: "获得订单信息失败",
                        showCancel: false
                    })
                    return;
                }
                uni.requestPayment({
                    provider: e.id,
                    orderInfo: orderInfo.data,
                    success: (e) => {
                        console.log("success", e);
                        uni.showToast({
                            title: "感谢您的赞助!"
                        })
                    },
                    fail: (e) => {
                        console.log("fail", e);
                        uni.showModal({
                            content: "支付失败,原因为: " + e.errMsg,
                            showCancel: false
                        })
                    },
                    complete: () => {
                        this.providerList[index].loading = false;
                    }
                })
            },
            getOrderInfo(e) {

                let appid = "";
                // #ifdef APP-PLUS
                appid = plus.runtime.appid;
                // #endif

                return new Promise((res) => {
                    console.log(JSON.stringify({
                        res,
                        appid,
                        e
                    }))
                    Request('Wallet_AliPay', {
                        data: {
                            title: '一名电影人',
                            payid: e,
                            appid,
                            total: this.price
                        }
                    }).then(result => {
                        console.log(JSON.stringify({
                            result
                        }))
                        res(result);
                    })

                })
            },
            priceChange(e) {
                // console.log(e.detail.value)
                this.price = e.detail.value;
            }
        }
    }
</script>

<style>
    .rmbLogo {
        color: #DD524D;
        /* font-size: 2em; */
    }

    button {
        background-color: #007aff;
        color: #ffffff;
    }

    .uni-h1.uni-center {
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: flex-end;
    }

    .price {
        font-size: 1em;
        border-bottom: 1px solid #eee;
        width: 230upx;
        max-width: 300upx;
        height: 80upx;
        padding-bottom: 4upx;
    }

    .ipaPayBtn {
        margin-top: 30upx;
    }
</style>
