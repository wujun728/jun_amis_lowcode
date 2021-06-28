<template>
    <view class="uni-page-body">
        <block class="" v-if="detailData">


            <view class="uni-flex uni-column" style="justify-content: center; align-items: center;padding: 20px;box-sizing: border-box;">
                <view class="">
                    {{detailData.pay_type_name}}
                </view>
                <view class="" style="color: #F76260;font-size: 2em;font-weight: 700;">
                    {{detailData.pay_amount}}
                </view>
                <view class="">
                    <text :style="detailData.state==1?'color:green':'color:red'">【{{detailData.pay_state_name}}】</text>
                </view>
            </view>
            <view class="uni-list-cell " hover-class="uni-list-cell-hover">
                <view class="uni-media-list" style="">
                    付款方式
                    <view style=""> {{detailData.pay_type_name}}</view>
                </view>

            </view>
            <view class="uni-list-cell " hover-class="uni-list-cell-hover">
                <view class="uni-media-list" style="">
                    商品说明
                    <view style="">{{detailData.subject}}</view>
                </view>

            </view>
<!--            <view class="uni-list-cell " hover-class="uni-list-cell-hover">
                <view class="uni-media-list" style="">
                    收货地址
                    <view style="">{{detailData.subject}}</view>
                </view>
                <view class="uni-list-cell-navigate uni-navigate-right  " style="">

                </view>
            </view> -->
            <view class="uni-list-cell " hover-class="uni-list-cell-hover">
                <view class="uni-media-list" style="">
                    创建时间
                    <view style="">{{detailData.create_date}}</view>
                </view>

            </view>
            <view class="uni-list-cell " hover-class="uni-list-cell-hover">
                <view class="uni-media-list" style="">
                    付款时间
                    <view style="">{{detailData.pay_date}}</view>
                </view>

            </view>
            <view class="uni-list-cell " hover-class="uni-list-cell-hover">
                <view class="uni-media-list" style="">
                    更新时间
                    <view style="">{{detailData.update_date}}</view>
                </view>

            </view>
            <view class="uni-list-cell " hover-class="uni-list-cell-hover">
                <view class="uni-media-list" style="">
                    订单号
                    <view style="">{{detailData.id}}</view>
                </view>

            </view>
            <view class="uni-list-cell " hover-class="uni-list-cell-hover">
                <view class="uni-media-list">
                    支付平台单号
                    <view style="">{{detailData.trade_no}}</view>
                </view>
            </view>
          <!--  <view class="uni-list-cell " hover-class="uni-list-cell-hover">
                <view class="uni-media-list" style="">
                    账单分类
                    <view style="">{{detailData.subject}}</view>
                </view>
                <view class="uni-list-cell-navigate uni-navigate-right  " style="">

                </view>
            </view>

            <view class="uni-list-cell " hover-class="uni-list-cell-hover">
                <view class="uni-media-list" style="">
                    标签备注
                    <view style="">{{detailData.subject}}</view>
                </view>
                <view class="uni-list-cell-navigate uni-navigate-right  " style="">

                </view>
            </view>
            <view class="uni-list-cell " hover-class="uni-list-cell-hover">
                <view class="uni-media-list">
                    资金往来记录
                    <view style="">{{detailData.subject}}</view>
                </view>
                <view class="uni-list-cell-navigate uni-navigate-right  " style="">

                </view>
            </view>

            <view class="uni-list-cell " hover-class="uni-list-cell-hover">
                <view class="uni-media-list" style="">
                    对订单号有疑问
                    <view style="">{{detailData.subject}}</view>
                </view>
                <view class="uni-list-cell-navigate uni-navigate-right  " style="">

                </view>
            </view> -->
            <!--            <view class="uni-list-cell " hover-class="uni-list-cell-hover">
                <view class="uni-media-list" style="">
                    <view class="">投诉</view>
                    <view class="">投诉</view>
                </view>
                <view class="uni-list-cell-navigate uni-navigate-right" style="">
                </view>
            </view> -->
        </block>
    </view>
</template>

<script>
    import Request from '@/request/index.js';
    import {
        Time
    } from '@/common/yc_js/index.js';

    export default {
        computed: {
            detailData() {

                var detail = this.detail;
                // console.log(detail)
                if (detail) {


                    detail.create_date = Time.formatDate(detail.create_time);
                    detail.update_date = Time.formatDate(detail.update_time);
                    if(detail.pay_time && detail.pay_time!=0){
                        detail.pay_date = Time.formatDate(detail.pay_time);
                    }
                    return detail

                } else {
                    return null
                }
            }
        },
        data() {
            return {
                detail: null
            }
        },
        onLoad(event) {
            // console.log(event)
            try {
                this.param = JSON.parse(decodeURIComponent(event.query));
            } catch (error) {
                try {
                    this.param = JSON.parse(event.query);
                } catch (error) {
                    return
                }
            }

            this.query = 'query=' + encodeURIComponent(JSON.stringify(this.param))
            this.getDetail(this.param.id);
        },
        methods: {
            getDetail(tid) {
                Request('UserWalletBill_Detail', {
                    data: {
                        tid
                    },
                }).then(result => {
                    // console.log(result)
                    if (result.statusCode == 200 && result.data.data) {
                        this.detail = result.data.data
                    }
                })
                // console.log(tid)
            },
            toPage(e) {

            }
        }
    }
</script>

<style>
    .uni-media-list {
        display: flex;
        flex-direction: row;
        justify-content: space-between
    }


    .uni-list-cell {
        display: flex;
        flex-direction: row;
        background: #fff;
        justify-content: space-between
    }
</style>
