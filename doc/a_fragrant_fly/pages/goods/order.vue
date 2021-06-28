<template>
	<view class="page-body tui"  >
		<scroll-view class=""  :style="{height:contentHeight + 'px'}" style="padding:0 15upx;box-sizing:border-box;" scroll-y  >
				<view class="uni-card" >

						<view class="orders-list tui-flex" style="width: 100%;">
							<view style="flex: 1;">
								<text class="orders-title">单号:{{order.sn}}</text>
							</view>
							<view class="orders-right" >
								<view style="width: 100%"><text class="iconfont">&#xe8c7;</text> {{order.shop_name}}</view>
							</view>
						</view>
					

					<view v-for="(item,index) in order.goods" :key="index" class="orders-list tui-column">

						<view class="tui-item tui-flex" style="width: 100%;">
							<view style="flex: 1;">
								<text class="orders-title" style="width: 80upx;font-size: 0.8em;">{{index+1}}</text>
								<text class="orders-title">{{item.title}}</text>
								<text class="orders-version" v-if="item.versionName">{{item.versionName}}</text>
							</view>
							<view class="orders-right" style="flex-direction: row;width: 180upx;justify-content: space-between;">
								<view>￥{{item.price}}</view>
								<view style="width: 60upx;">x{{item.number}}</view>
							</view>
						</view>
						<view v-if="item.note" class="tui-item" style="width: 100%;font-size:0.6em;opacity:0.5;height: 1em;line-height: 1em;">
							备注:{{item.note}}
						</view>
					</view>
					<view class="orders-list tui-flex" style="width: 100%;">
						<view style="flex: 1;">
							<text class="orders-title"></text>
						</view>
						<view class="orders-right" >
							<view style="width: 100%">金额:￥{{order.sum}}<text class="iconfont"></text> </view>
						</view>
					</view>
					<view class="orders-list" style="align-items: center;">
						<view style="padding:0 20upx">订单备注</view>
						<input class="tui-border" style="padding:0 20upx" placeholder="给商家留言,可填写注意事项,特殊要求等"  />
					</view>
				</view>
				<view class="tui-flex tui-center" style="">
					<view class="" style="padding: 0 30upx;"  >
						<text class="tui-item tui-center" style="color: #1482D1;" @tap="switchTab(0)" v-if="!addrShow">外卖送餐</text>
						<text class="tui-item tui-center tui-bt-a"  style="color: #1482D1;" @tap="switchTab(1)" v-else >店内就餐</text>
					</view>
					<!-- <view class="" v-if="addrShow" style="color: #1482D1;" @tap="refreshAddress()">刷新地址</view> -->

				</view>
				<view class="uni-card">
					<view v-if="addrShow" class="">
						<button  @tap="goPage('address')" v-if="address.name" class="tui-flex tui-padding tui-relative tui-column" style="padding:20upx 30upx">
							<view class="tui-flex tui-item" style="text-align: left;">
								<text class="tui-item">收货人: {{address.name}}</text>
								<text class="tui-item">电话: {{address.phone}}</text>
							</view>

							<view class="tui-item" style="text-align: left;">
							{{address.address}}
							</view>
							<view class="tui-absolute tui-flex " style=" font-size:1.3em ; align-items: center;justify-content: flex-end; right: 20upx;top:0;bottom:0;height: 95%;">
								<!-- <icon class="iconfont " style="color:#999;"></icon> -->
							</view>
							
						</button>
						
						<view v-else class="tui-center">
							<button  @tap="goPage('address')"  class="flex padding relative column" style="padding:20upx 30upx">
								
							<view class="tui-item">
								添加您的地址
							</view>
							<view class="tui-absolute tui-flex " style=" font-size:1em ; align-items: center;justify-content: flex-end; right: 20upx;top:0;bottom:0;height: 95%;">
								 <text class="iconfont" style="color:#999;">&#xe95a;</text>
							</view>
							</button>

						</view>

					</view>
					<view v-else class="orders-list uni-flex" >
						<view class="uni-flex " style="padding-right:10upx ;flex: 1; align-items: center;">
							<view style="padding:0 20upx">人数:</view>
							<input  class="tui-border" placeholder="1" style="padding:0 20upx" v-model="peopleNumber"  />
							<view style="padding:0 20upx">人</view>
						</view>
						<view class="uni-flex " style="padding-left:10upx ;flex: 1;justify-content: center;align-items: center;">
							<view style="padding:0 20upx">桌号:</view>
							<input  class="tui-border"  placeholder="001" style="padding:0 20upx" v-model="tableNumbers"  />
							<view style="padding:0 20upx">号</view>
						</view>
					</view>
				</view>

		</scroll-view>
		<nav class="tui-bottom-nav tui-flex " style="background: #F9F9F9;z-index: 99;text-align: left;padding-left: 30upx;">
			<view class="tui-item tui-flex " style="font-size: 1.2em;" >应付:<text style="color:red">￥{{order.sum}}</text></view>
			<button type="warn" @tap="toPay" size="mini"  style="line-height:100upx;padding: 0 15upx;">立即支付</button>
		</nav>
	</view>
</template>

<script>
	import {Storage} from '@/common/yc_js/';
	// import ajax from "../../request/ajax.js";
	// import uniIcon from "../../components/template/icon/icon.vue";
	export default {
		 // components: {uniIcon},
		data() {
			return {
				winHeight:0,
				contentHeight:0,
				tableNumbers:0,//桌号
				peopleNumber:0,//人数
				addrShow:false,
				hasAddress: false,
				order: {
					sn:"2323",
					shop_id:"132",
					shop_name:"大韩烤肉",
					sum:222.5,
					goods:[{
						id: 1,
						title: '新鲜芹菜 半斤',
						image: 'https://img-cdn-qiniu.dcloud.net.cn/uploads/example/product3.jpg',
						num: 4,
						price: 0.01
					}]
					}

			};
		},computed:{
			address(){
				return this.$store.getters.address;
			}
		},
		watch:{
			tableNumbers(val){
				Storage.Sync.set('tableNumbers',val,72000)
			},peopleNumber(val){
				Storage.Sync.set('peopleNumber',val,72000)
			}
		},
		onLoad(e) {
			this.tableNumbers=Storage.Sync.get('tableNumbers') || 1;
			this.peopleNumber=Storage.Sync.get('peopleNumber') || 1;
// 			console.log("onload")
// 			console.log(e)
			var order=this.$store.getters.order;
			if(!order.sum){

				uni.showModal({
					title: '提示',
					content:'未查询到任何商品，系统将返回',
					showCancel:false,
					text: 'center',
					complete() {
						uni.navigateBack({
							delta: 1
						});
					}
				})

			}
			this.order=order;
			// console.log(order)
			
			let winHeight = uni.getSystemInfoSync().windowHeight;
			//创建节点选择器 获取底部导航高度 
				this.contentHeight=(winHeight-uni.upx2px(100));
				this.winHeight = winHeight;
		},
		methods: {

			switchTab(e){
				if(e==0){
					this.addrShow=true;
				}else if(e==1){
					this.addrShow=false;
				}
				},
			goPage(){
				uni.navigateTo({
					url:"../user/address"
				}) 
				
			},
			toPay() {
				var that=this;
				uni.showModal({
					title: '提示',
					content: '本系统只做演示，支付系统已屏蔽',
					text: 'center',
					complete(e) {
						if(e.confirm){
						uni.navigateTo({
							url: '/components/pages/pay/payment?sn='+that.order.sn+'&sum='+that.order.sum
						})}
					}
				})
			}
		},
		mounted: function() {
			
			// el渲染完成触发
// 			this.$nextTick(function() {
// 				// const self = this;
// 				// this.refreshAddress()
// 
// 			})
		}
	}
</script>

<style>
	page{
		/* overflow-y:scroll; */
	}

	.orders-list view {

		/* padding: 0 20upx; */
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
	.orders-right view {
		align-self: flex-end;
		text-align: right;
	}

	.orders-list view {
		/* line-height: 60upx; */
	}

	.orders-footer {
		bottom: 0;
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
