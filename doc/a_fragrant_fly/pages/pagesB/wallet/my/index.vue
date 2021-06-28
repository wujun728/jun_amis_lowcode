<template>
    <view class="uni-page-body uni-flex" style="flex-direction: column;width: 100%;" v-if="detail">
            <view class="uni-flex uni-column" style="height: 200upx;justify-content: center;padding:0 50upx;">
                <view class="uni-flex" style="justify-content: center;">
                    余额(元)
                </view>
                <view class="uni-flex " style="justify-content: center;font-size: 3em;">
                    {{detail.amount}} 
                    <!-- <text @tap="refresh">刷新</text> -->
                </view>

            </view>
            <view class="uni-list">

                <view class="uni-list-cell "  hover-class="uni-list-cell-hover" @tap="goPage('bill')">
                    <view class="uni-list-cell-navigate " style="width: 100upx;">
                        账本
                    </view>
                    <view class="uni-list-cell-navigate uni-navigate-right  " style="flex: 4;justify-content: flex-end;">

                    </view>
                </view>

                <view class="uni-list-cell " hover-class="uni-list-cell-hover" @tap="goPage('supply')">
                    <view class="uni-list-cell-navigate " style="width: 100upx;">
                        充值
                    </view>
                    <view class="uni-list-cell-navigate uni-navigate-right  " style="flex: 4;justify-content: flex-end;">

                    </view>
                </view>

<!--                <view class="uni-list-cell " hover-class="uni-list-cell-hover" @tap="goPage('buyback')">
                    <view class="uni-list-cell-navigate " style="width: 100upx;">
                        提现
                    </view>
                    <view class="uni-list-cell-navigate uni-navigate-right  " style="flex: 4;justify-content: flex-end;">

                    </view>
                </view> -->

            </view>

    </view>
</template>

<script>
    import Request from "@/request/index.js";
    export default {
        computed: {
            wallet() {
                return this.detail
            }
        },
        data() {
            return {
                detail: null
                // amount:'54.6'
            }
        },
        onShow() {
            // console.log('钱包')
            this.refresh();
        },
        methods: {
            refresh() {
                Request('UserWallet_Detail', {
                    data: {}
                }).then(res => {
                    // console.log(res)
                    if (res.data.code === 200) {
                        this.detail = res.data.data
                    } else {

                    }
                })
            },
            tapTag(type) {
                // buyback
            },
            goPage(type) {
                switch (type) {
                    case 'supply':
                        // 去支付
                        var url = 'payment';
                        uni.navigateTo({
                            url: url
                        })
                        break;
                    case 'buyback':
                        // 去支付
                        var url = 'buyback';
                        uni.navigateTo({
                            url: url
                        })
                        break;
                    case 'bill':
                        // 去账本
                        var url = 'bill';
                        uni.navigateTo({
                            url: url
                        })
                        break;
                    default:
                        break;
                }
            }
        }
    }
</script>

<style>
</style>
