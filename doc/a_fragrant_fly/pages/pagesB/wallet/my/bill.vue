<template>
    <view class="uni-page-body">
        <scroll-view class="uni-list" style="background: rgba(0,0,0,0);" :style="'height:'+contentHeight+'px'"
            @scrolltolower="scrolltolower">
            <view class="uni-flex" style="flex-direction: column;width: 100%;">

                <view v-if="!isHaveBill" class="uni-flex" style="justify-content: center;height: 100px;align-items: center;">
                    没有数据
                </view>
                <view v-else class="uni-list">

                    <block v-for="(item ,key) in billData" :key="key">
                        <view class="uni-list-cell " hover-class="uni-list-cell-hover" @tap="tapTag(item)" style="justify-content: space-between;">
                            <view class="uni-list-cell-navigate uni-column uni-flex-item" style="align-items: flex-start;">

                                <view class="" style="opacity: 0.7;">
                                    {{item.pay_type_name}}
                                </view>
                                <view class="">
                                    余额:{{item.balance}}
                                </view>
                            </view>
                            <view class="uni-list-cell-navigate uni-column" style="align-items: flex-end;">

                                <view class="" style="opacity: 0.7;">
                                    {{item.date}}
                                </view>
                                <view class="uni-flex uni-row">
                                    <text :style="item.state==1?'color:green':'color:red'">【{{item.pay_state_name}}】</text>
                                    <text>{{item.pay_amount}}</text>
                                </view>
                            </view>
                        </view>
                    </block>


                </view>

            </view>
        </scroll-view>
    </view>
</template>

<script>
    import Request from "@/request/index.js";
    import {
        Time
    } from "@/common/yc_js/index.js"
    export default {
        computed: {

            isHaveBill() {
                return this.bill.length > 0 ? true : false
            },
            billData() {
                var bill = this.bill;
                // console.log(bill)
                return bill.filter(e => {
                    e.date = Time.formatDate(e.update_time, "yyyy-MM-dd")
                    return e
                })
            }
        },
        data() {
 
            return {
                query: {},
                bill: [],
                value: ''
            }
        },
        onLoad() {
            var system = uni.getSystemInfoSync()
            this.contentHeight = system.windowHeight;
            this.getData()
        },
        methods: {
            scrolltolower(e) {
                this.getData()
            },
            getData() {
                Request('UserWalletBill_List', {
                    data: this.query
                }).then(res => {
                    // console.log(res)
                    if (res.data.code == 200) {
                        if (res.data.data.length < 20) {
                            this.query.noHave = true;
                        }
                        var data=res.data.data;
                        this.bill = data.concat(this.bill)
                        this.query.time=res.data.data[0].create_time
                    } else {
                        this.query.noHave = true;
                    }
                })
            },
            tapTag(item) {
                uni.navigateTo({
                    url: 'billDetail?query=' + encodeURIComponent(JSON.stringify(item))
                })
            },
        }
    }
</script>

<style>
</style>
