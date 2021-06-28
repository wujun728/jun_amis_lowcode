<template>
    <!-- <view class="page-body tui"> -->
    <scroll-view style="padding: 0 15upx;box-sizing: border-box;" :style="'height:'+contentHeight+'px'" scroll-y>
        <view class="uni-card" v-for="(orders,index) in orderList" :key="index">
            <view class="orders-list" style="justify-content: space-between;">
                <view class="tui-title" @tap="goPage('shop',orders.shop_id)" style="">{{orders.shopName}} ></view>
                <view style="color: red;">
                    <text v-if="orders.state=='4'" class="">订单完成</text>
                    <text v-else-if="orders.state=='-4'">订单已删除</text>
                    <text v-else-if="orders.state<2 " class="">未付款</text>
                    <text v-else-if="orders.state>1" class="">已付款</text>
                </view>
            </view>
            <view style="background: #F9F9F9;" @tap="goPage('orderDetail',orders)">
                <view v-for="(item,key) in orders.data" :key="key" class="orders-list">
                    <view style="flex: 1;">
                        <text class="orders-title">{{item.title}}</text>
                        <text class="orders-version" v-if="item.versionName">{{item.versionName}}</text>
                    </view>
                    <view class="orders-right" style="flex-direction: row;width: 150upx;">
                        <view>￥{{item.price}}</view>
                        <view style="width: 100upx;">x{{item.number}}</view>
                    </view>
                </view>
            </view>
            <view class="orders-list tui-flex" style="justify-content:flex-end;text-align:left">
                <text style="">实付：￥{{orders.pay}}</text>
            </view>
            <!-- <order-state :sn="orders.sn" :state="orders.state" :click="commit"></order-state> -->
            <view class="orders-list tui-flex" style="justify-content:flex-end;align-self: flex-end;">
                <!-- <view v-if="orders.state!='0'" > -->
                <text v-if="orders.state!='-4'" class="tui-button tui-border" style="margin-left:10upx;" @tap="deleteOrder(orders)">删除订单</text>
                <text v-if="orders.state=='-4'" class="tui-button tui-border" style="margin-left:10upx;">订单已删除</text>
                <text v-if="orders.state>'1'" class="tui-button tui-border" style="margin-left:10upx;" @tap="refund(orders)">申请退款</text>
                <text v-else-if="orders.state=='-2'" class="tui-button tui-border" style="margin-left:10upx;">退款成功</text>
                <text v-else-if="orders.state=='-1'" class="tui-button tui-border" style="margin-left:10upx;">退款处理中</text>
                <!-- </view>
						<view v-else > -->
                <!-- <text v-if="orders.state=='1'"  class="tui-button tui-border" style="margin: 0 10upx;" @tap="cancelOrder(orders)" >取消订单</text> -->
                <!-- <text v-else-if="orders.state=='0'" class="tui-button" style="margin-left:10upx;" >订单已取消</text> -->
                <text v-if="orders.state=='1'" class="tui-button" style="background: #FF3030;color: #fff;margin-left:10upx;"
                    @tap="toPay(orders)">去支付</text>
                <text v-if="orders.state=='2'" class="tui-button" style="background: #FF3030;color: #fff;margin-left:10upx;">已支付</text>
                <!-- </view> -->
            </view>

        </view>
    </scroll-view>

    <!-- </view> -->
</template>

<script>
    import {
        Storage
    } from '@/common/yc_js/';
    import Request from "@/request/"
    // import orderState from "../../components/pages/order/state.vue"
    export default {
        components: {
            // orderState
        },
        props: ['contentHeight'],
        data() {

            return {
                title: "订单",
                // contentHeight:0,
                winHeight: 0,
                orderList: [{
                        id: 1,
                        title: '新鲜芹菜 半斤',
                        num: 4,
                        price: 0.01
                    },
                    {
                        id: 2,
                        title: '素米 500g',
                        num: 1,
                        price: 0.03
                    }
                ]

            };
        },
        onLoad(e) {

        },
        methods: {
            commit(e) {
                console.log(e)
            },
            init() {
                // var orderData =Storage.get('payOrder') //读取购物车缓存数据
                // 请求服务器
                var self = this;
                Request('orderList').then(res => {
                    console.log(res)
                    var orderList = res.data.data || {};
                    self.orderList = orderList;
                    // console.log(res.data.data)

                })
            },
            cancelOrder(order) {
                var orderList = this.orderList;
                var that = this;
                // 取消订单
                uni.showModal({
                    title: "确认取消订单？",
                    content: "订单一旦取消,获得的相关优惠将会全部取消。",
                    complete(e) {
                        if (e.confirm) {
                            var neworder = [];
                            for (var i = 0; i < orderList.length; i++) {
                                if (orderList[i].sn == order.sn) {
                                    orderList[i].state = -4;
                                    break;
                                }
                            }

                            that.orderList = orderList;
                        }
                    }
                })
            },
            refund(order) {
                var that = this;
                var orderList = this.orderList;
                // 申请退款
                uni.showModal({
                    title: "确认要退款吗？",
                    text: "",
                    complete(e) {
                        if (e.confirm) {
                            var neworder = [];
                            for (var i = 0; i < orderList.length; i++) {
                                if (orderList[i].sn == order.sn) {
                                    orderList[i].state = -1;
                                    break;
                                }
                            }

                            that.orderList = orderList;
                        }
                    }
                })
            },
            deleteOrder(order) {
                var orderList = this.orderList;
                var that = this;
                // 删除订单
                uni.showModal({
                    title: "确认删除订单？",
                    content: "删除之后订单无法恢复,无法处理您的售后问题,请您慎重考虑。",
                    complete(e) {
                        if (e.confirm) {
                            var neworder = [];
                            for (var i = 0; i < orderList.length; i++) {
                                if (orderList[i].sn == order.sn) {
                                    orderList[i].state = -4;
                                    break;
                                }
                            }

                            that.orderList = orderList;
                        }
                    }
                })
            },
            goPage(e, data) {
                var url = '../order/detail';
                switch (e) {
                    case "shop":
                        url = '../goods/shop?id=' + data;
                        break;
                    default:
                        break;
                }
                uni.navigateTo({
                    url: url
                });
            },
            toPay(order) {
                // var order= this.orderList[key];
                // var that=this;
                uni.showModal({
                    title: '提示',
                    content: '本系统只做演示，支付系统已屏蔽',
                    text: 'center',
                    complete(e) {
                        if (e.confirm) {
                            uni.navigateTo({
                                url: '/components/pages/pay/payment?sn=' + order.sn + '&sum=' + order.sum
                            })
                        }
                    }
                })
            }
        },
        mounted() {
            uni.setNavigationBarTitle({
            	title: this.title
            });

            console.log("装置orderlist")
            // this.getTotalPrice();
            // el渲染完成触发
            this.$nextTick(() => {
                this.init();
            })
        }
    }
</script>

<style>
    /* 	.section {
		border-top: 1upx solid #ededed;
		border-bottom: 1upx solid #EDEDED;
		display: flex;
		justify-content: center;
	}
 */
    .orders-list view {

        /* padding: 0 20upx; */
    }

    .orders-list input {
        padding-top: 4upx;
        flex: 1;
        /* border: 1upx solid #7f7f7f; */

    }

    .orders-address {
        position: relative;
        padding: 20upx 50upx 20upx 35upx;
        font-size: 14px;
        line-height: 25px;
        border-bottom: 20upx solid #ededed;
        color: #adadad;
    }

    .orders-address::after {
        position: absolute;
        right: 30upx;
        top: 60upx;
        content: '';
        width: 8px;
        height: 8px;
        border-top: 4upx solid #7f7f7f;
        border-right: 4upx solid #7f7f7f;
        -webkit-transform: rotate(45deg);
        transform: rotate(45deg);
    }

    .orders-address-name {
        display: inline-block;
        width: 300upx;
    }

    .orders-no-address {
        position: relative;
        height: 90upx;
        line-height: 90upx;
        color: #adadad;
        border-bottom: 20upx solid #ededed;
        text-align: center;
    }

    .orders-no-address::after {
        position: absolute;
        right: 30upx;
        top: 34upx;
        content: '';
        width: 16upx;
        height: 16upx;
        border-top: 4upx solid #7f7f7f;
        border-right: 4upx solid #7f7f7f;
        -webkit-transform: rotate(45deg);
        transform: rotate(45deg);
    }


    .orders-box {
        padding-bottom: 105upx;
    }

    .orders-list {
        display: flex;
        padding: 15upx;
        border-bottom: 1upx solid #ededed;
    }

    .orders-thumb {
        padding: 0 20upx;
        width: 100upx;
        height: 100upx;
    }

    .orders-right {
        text-align: right;
        box-sizing: border-box;
        display: flex;
        justify-content: flex-end;
        /* flex-direction: column; */
        /* flex: 1; */
        /* position:relative; */
    }

    .orders-title {
        padding-right: 10upx;
        color: #333;
        /* font-weight:bold; */
    }

    .orders-version {

        background: hsla(30, 100%, 0%, 0.1);
        padding: 2upx 5upx;
        font-size: 0.8em;
        border-radius: 10upx;
    }

    .orders-tip {
        text-align: right;
        right: 15upx;
        position: absolute;
    }

    /* .orders-right .orders-pro-price{
	display: flex;
	justify-content: space-between;
	flex-direction: row;
} */
    .orders-right view {
        align-self: flex-end;
        text-align: right;
    }

    .orders-list view {
        line-height: 60upx;
    }

    .orders-footer {
        position: fixed;

        bottom: 0;
        left: 0;
        width: 100%;
        height: 95upx;
        line-height: 95upx;
        border-top: 1upx solid #ededed;
    }

    .orders-footer .orders-footer-total {
        align-self: center;
        text-align: left;
        flex: 1;
        padding-left: 30upx;
        box-sizing: border-box;
        color: red;
    }

    .orders-footer .orders-footer-btn {
        width: 240upx;
        text-align: center;
        color: #fff;
        background: #AB956D;
    }
</style>
