<template>
<view class="main tui">
	<!-- <drawer-bottom  ref="drawerBottom"  :drawerBottomShow="drawerBottomShow" :goods="goods" v-on:change="goodsUpdate"></drawer-bottom> -->

	<scroll-view class="scrollList" scroll-y   :style="{height:contentHeight + 'px'}" style="padding: 0 15upx;box-sizing: border-box;">
<!-- 		<view class="flex center column" style="">
			<view class="icon flex center" style="padding: 0upx 0;color: #000000;font-weight:bold;background: ;" @tap="goPage('shop')">
				<text  class="" style="font-size:1.4em ;background:#1482D1 ;color: #fff;padding: 20upx;width: 100%;">{{orderDetail.shopName}}</text>
			</view>
			<view class="" style="padding: 20upx;">
				<button type="" class="iconfont icon-kefu" style="font-size:1em ;padding: 8upx;line-height: 1em;"  @tap="goPage('kefu')" >客服</button>
			</view>
			

		</view> -->

			 <view class="uni-card tui-flex tui-center tui-column">
<!-- 						<image :src="goods.image" mode="aspectFill" class="goods-thumb"></image>
							<view class="goods-title">{{goods.title}}</view> -->

				</view>
				<view class="uni-card"  >
					<view class="orders-list" style="justify-content: space-between;">
						<view class="" style="font-weight:bold;" @tap="goPage('shop',orderDetail.shop_id)" >{{orderDetail.shopName}} ></view>
						<!-- <view class=""  >单号:{{orderDetail.sn}}</view> -->
						<view  style="color: red;">
							<text v-if="orderDetail.status!='0'">已付款</text>
							<text v-else class="">未付款</text>
						</view>
					</view>
					<view style="background: #F9F9F9;"  @tap="goPage('orderDetail',orderDetail)">
						<view v-for="(item,key) in orderDetail.data" :key="key" class="orders-list tui-column">

							<view class="tui-item tui-flex">
								<view style="flex: 1;">
									<text class="orders-title">{{item.title}}</text>
									<text class="orders-version" v-if="item.versionName">{{item.versionName}}</text>
								</view>
								<view class="orders-right" style="flex-direction: row;width: 150upx;">
									<view>￥{{item.price}}</view>
									<view style="width: 100upx;">x{{item.number}}</view>
								</view>
							</view>
							<view class="tui-item tui-flex" v-if="item.note">
								<view style="flex: 1;font-size: 0.8em;">
									<text class="orders-title">备注：</text>
									<text class="" >{{item.note}}</text>
								</view>
							</view>
						</view>
					</view>

					<view class="orders-list tui-flex" style="justify-content:flex-end;text-align:left">
						<text class="bagde" style="" v-if="orderDetail.sum>orderDetail.pay"> 已优惠：￥{{orderDetail.sum-orderDetail.pay}}</text>
						<text  style="">实付：￥{{orderDetail.pay}}</text>
					</view>
<!-- 					<view class="orders-list flex" style="justify-content:flex-end;align-self: flex-end;">
						<view v-if="orderDetail.status!='0'" >
							<text  class="button border" style="margin-left:10upx;" @tap="deleteOrder(orderDetail)" >删除订单</text>
							<text  class="button border" style="margin-left:10upx;" @tap="refund(orderDetail)" >申请退款</text>
						</view>
						<view v-else >
							<text  class="button border" style="margin: 0 10upx;" @tap="cancelOrder(orderDetail)" >取消订单</text>
							<text  class="button" style="background: #FF3030;color: #fff;" @tap="toPay(orderDetail)" >去支付</text>
						</view>
					</view> -->
					<view   v-if="orderDetail.note" class="orders-list"  style="background: #F9F9F9;justify-content: space-between;color:;">		
						<view class="">备注: {{orderDetail.note}}</view>
					</view>
				</view>
				<view class="tui-flex tui-center tui-column" style="">
					<view class="tui-icon tui-flex tui-center" style="">
						<text  class="" @tap="goPage('kefu')" style="font-size:1.2em ;background:#1482D1 ;color: #fff;padding: 20upx;width: 100%;">联系卖家</text>
					</view>

				</view>
				<view class="uni-card tui-flex tui-column tui-padding"  >
					<text>订单编号：{{orderDetail.sn}}</text>
					<text>支付方式：{{orderDetail.payType}}</text>
					<text>下单时间：{{orderDetail.createTime}}</text>
					<text>付款时间：{{orderDetail.payTime}}</text>
					<text>接单时间：{{orderDetail.connectTime}}</text>
				</view>


		</scroll-view>
		<nav class="tui-bottom-nav tui-flex" style="bottom:0;z-index: 99;">
			<view class="orders-list tui-flex" style="justify-content:flex-end;align-self: flex-end;">
				<view v-if="orderDetail.status!='0'" >
					<text  class="tui-button tui-border" style="margin-left:10upx;" @tap="deleteOrder(orderDetail)" >删除订单</text>
					<text  class="tui-button tui-border" style="margin-left:10upx;" @tap="refund(orderDetail)" >申请退款</text>
				</view>
				<view v-else >
					<text  class="tui-button tui-border" style="margin: 0 10upx;" @tap="cancelOrder(orderDetail)" >取消订单</text>
					<text  class="tui-button" style="background: #FF3030;color: #fff;" @tap="toPay(orderDetail)" >去支付</text>
				</view>
			</view>
		</nav>
<!-- 						<nav class="bottom-nav flex  " style="bottom:0;z-index: 99;">
								<view class="item flex center" style="justify-content: space-around;">
									<view class="icon flex column" style="flex-direction: column;" @tap="goPage('shop')">
										<icon class="iconfont icon-dianpu" style="font-size: 1.4em;line-height: 0.6em;"></icon>
										<text style="font-size:0.8em ;">店铺</text>
									</view>
									<view class="icon flex column" @tap="goPage('chat')">
										<icon class="iconfont icon-kefu" style="font-size: 1.4em;line-height: 0.6em;"></icon>
										<text style="font-size:0.8em ;">客服</text>
									</view>
									<view class="icon flex column" @tap="goPage('order')">
										<icon class="iconfont icon-dingdanjihe" style="font-size: 1.4em;line-height: 0.6em;"></icon>
										<text style="font-size:0.8em ;">订单</text>
									</view>
								</view>


								<button type="warn" @tap="xuanZhe()" >下单</button>
						</nav> -->
</view>
</template>

<script>

	// import orderData from "../../request/data/orderDetail.js";
	import {ajax,Storage} from '@/common/yc_js/';
	import drawerBottom from '../../components/template/drawer/bottom.vue'
	export default {
		components: {
			// numberBox,
			drawerBottom
		},
		data() {
			return {
				winHeight:0,
				contentHeight:100,
				drawerBottomShow:false,
				timeOut:'',
				timeOut2:'',
				selected:'',
				vIndex:0,
					orderDetail:{
									"sn":"11200321322",
									"time":"1566666666",
									"sum":"645",
									"status":"1",
									"shop_id":"1",
									"shopName":"大龙烧烤店",
									"data":{
										"2_1":{
											"id":"2",
											"title":"大虾",
											"price":"108",
											"number":3,
											"versionName":"大盘",
										},"3_1":{
											"id":"3",
											"title":"韩国寿司",
											"price":"36",
											"number":3,
											"versionName":"鱼肉",
											
										},"4_1":{
											"id":"4",
											"title":"清淡菜品",
											"price":"58",
											"number":3,
											"versionName":null,
										},
									}
									
					},
// 						num: 1,
// 						totalNum: 0,
// 						totalPrice:0,
// 						curIndex: 0,
// 						scaleCart: false
				
			};
		},
		methods:{
			cancelOrder(){
				// 取消订单
				uni.showModal({
					title:"确认取消订单？",
					content:"订单一旦取消,获得的相关优惠将会全部取消。"
				})
			},
			refund(e){
				// 申请退款
				uni.showModal({
					title:"申请退款"
				})
			},
			deleteOrder(e){
				// 删除订单
				uni.showModal({
					title:"确认删除订单？",
					content:"删除之后订单无法恢复,无法处理您的售后问题,请您慎重考虑。"
				})
			},
				goPage(e){
					var url='';
					switch (e){
						case "cart":

// 												var goods=this.goods;
// 												var cart={};
// 												// cart[this.goods.id]=this.goods;
// 												for(let i=0;i<goods.length;i++){
// 													if(goods[i].selected){
// 														cart[goods[i].id]=goods[i];
// 													}
// 												}
									// Storage.set('cart',cart,1000);
									url="/pages/goods/"+e+"?id=2";
							break;
							case "wode":
							url="/pages/user/"+e+"?id=2";
							break;
							case "kefu":
							url="/pages/chat/chat?id=2";
							break;
// 										case "chat":
// 										url="/pages/chat/"+e+"?id=2";
// 										break;
							case "order":
							url="/pages/pay/"+e+"?id=2";
							break;
							
						default:
							url="/pages/goods/"+e+"?id=2";
							break;
					}

					
					uni.navigateTo({
						url: url,
						success: res => {
							
						},
						fail: () => {},
						complete: () => {}
					});
			}
		},onLoad(e){
				let winHeight = uni.getSystemInfoSync().windowHeight;
			//创建节点选择器 获取底部导航高度 
				this.contentHeight=(winHeight-uni.upx2px(100));
				this.winHeight = winHeight;
			
			ajax.get('orderDetail',(res)=>{
				
				var orderDetail=res.data.data ||{};
						orderDetail.number=1;
						orderDetail.versionName='未选择';
				this.orderDetail=orderDetail;
				console.log(res.data.data)
			})
			console.log(e)
		}
	}
</script>

<style>
	.orders-list view {

		padding: 0 20upx;
	}

	.orders-list input {
		padding-top:4upx ;
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
