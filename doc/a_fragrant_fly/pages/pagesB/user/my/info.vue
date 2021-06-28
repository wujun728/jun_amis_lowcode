<template>
    <view class=" uni-flex">
        <view class="uni-flex-item uni-column " v-if="onshow">
            <view class="uni-list-cell"  hover-class="uni-list-cell-hover" @tap="update('headimg')">
                <view class="uni-list-cell-navigate " style="width: 60upx;">
                    头像
                </view>
                <view class="uni-list-cell-navigate uni-navigate-right  " style="flex: 4;justify-content: flex-end;">
                    <image   class="" style="padding:0 20upx;width: 100upx;height: 100upx;" :src="userInfo.headimg"
                       @error="userInfo.headimg='/static/img/logo.png'"  mode="aspectFit"></image>
                </view>
            </view>
            <view class="uni-list-cell "  hover-class="uni-list-cell-hover" @tap="update('nickname')">
                <view class="uni-list-cell-navigate " style="width: 100upx;">
                    昵称
                </view>
                <view class="uni-list-cell-navigate uni-navigate-right  " style="flex: 4;justify-content: flex-end;">
                    <text style="padding:0 20upx;">{{userInfo.nickname}}</text>
                </view>
            </view>
            <view class="uni-list-cell "  hover-class="uni-list-cell-hover" @tap="update('area')">
                <view class="uni-list-cell-navigate " style="width: 100upx">
                    地区
                </view>
                <view class="uni-list-cell-navigate uni-navigate-right  " style="flex: 4;justify-content: flex-end;">
                    <text style="padding:0 20upx;">{{userInfo.area}}</text>
                </view>
            </view>
            <view class="uni-list-cell "  hover-class="uni-list-cell-hover" @tap="update('individuality')">
                <view class="uni-list-cell-navigate " style="width: 100upx;">
                    个性
                </view>
                <view class="uni-list-cell-navigate uni-navigate-right " style="flex: 4;justify-content: flex-end;">
                    <text style="padding:0 20upx;">{{userInfo.individuality}}</text>
                </view>
            </view>
            <view class="uni-list-cell " hover-class="uni-list-cell-hover" @tap="toPage('address')">
                <view class="uni-list-cell-navigate   uni-navigate-right" style="width: 100upx;">
                    地址信息
                </view>
            </view>
        </view>
    </view>
</template>

<script>
    import Url from '@/common/yc_js/Url.js';
    import Storage from "@/common/yc_js/Storage.js";
    import yc_js from "@/common/yc_js/index.js";
    var Base64 = yc_js.Base64;
    export default {

        data() {
            return {
                updateKey: true,
                onshow: false,
            }
        },
        computed: {
            userInfo() {
                var userInfo = this.$store.getters.userInfo;
                return userInfo;
            }
        },
        methods: {
            update(e) {
                var value = '';
                if (e == 'headimg') {
                    value = Base64.urlDecoder(this.userInfo[e])
                } else {
                    value = this.userInfo[e]
                }
                var updateData = 'key=' + e + '&value=' + value;
                // this.infoUpdate={key:e,value:this.userInfo[e]};
                switch (e) {
                    case 'headimg':
                        this.toPage('update', updateData)
                        // this.toPage('updateHeadimg',str)
                        break;
                    default:
                        this.toPage('update', updateData)
                        break;
                }
            },
            toPage(url, e) {
                uni.navigateTo({
                    url: url + '?' + e
                })
            }
        },
        onShow() {
            this.onshow = true;
            this.$store.commit('pages', this.pageName || "mainMain")
        }
    }
</script>
<style>

    .uni-list-cell {
        display: flex;
        flex-direction: row;
        background: #fff;
        justify-content: space-between
    }


</style>
