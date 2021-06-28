<template>
	<view>
			<view class="uni-padding-wrap">
				<view style="background:#FFF; padding:50upx 0;">
					<view class="uni-hello-text uni-center">支付金额</text></view>
					<view class="uni-h1 uni-center uni-common-mt" style="color: red;"><text class="rmbLogo">￥</text>{{money}}</view>
					<!-- <view class="uni-hello-text uni-center uni-common-mt">实际应用中可自定义金额</text></view> -->
				</view>
				<view class="uni-btn-v uni-common-mt">
					<!-- #ifdef MP-WEIXIN -->
					<button type="primary" @tap="weixinPay" :loading="loading">微信支付</button>
					<!-- #endif -->
					<!-- #ifdef APP-PLUS -->
					<button v-for="(item,index) in providerList" :key="index" @tap="requestPayment(item,index)" :loading="item.loading">{{item.name}}支付</button>
					<!-- #endif -->
					<!-- #ifdef  H5  -->
					<button v-for="(item,index) in providerList" :key="index" @tap="requestPayment(item,index)" :loading="item.loading">{{item.name}}支付</button>
					<!-- #endif -->
				</view>
			</view>
		</view>
	</view>
</template>
<script>
	export default {
		data() {
			return {
				title: '支付',
				loading: false,
				money:0,
				orderSn:0,
				providerList: [{
						id:'alipay',
						name:"支付宝",
					},
					{
						name:"微信",
						id:'wxpay',
					}]
			}
		},
		onLoad: function(order) {
			// var order=this.$store.getters.order;
			
			if(!order.sum){

				uni.showModal({
					title: '提示',
					content:'订单错误,系统将退回',
					showCancel:false,
					text: 'center',
					complete() {
						uni.navigateBack({
							delta: 1
						});
					}
				})

			}
			this.money=order.sum;
			// ifdef APP-PLUS
			// #ifdef APP-PLUS 
			uni.getProvider({
				service: "payment",
				success: (e) => {
					console.log("payment success", e);
					this.providerList = e.provider.map((value) => {
						switch (value) {
							case 'alipay':
								return {
									name: '支付宝',
									id: value,
									loading: false
								}
							case 'wxpay':
								return {
									name: '微信',
									id: value,
									loading: false
								}
						}
					})
				},
				fail: (e) => {
					console.log("获取登录通道失败：", e);
				}
			});
			// #endif
		},
		methods: {
			weixinPay() {
				console.log("发起支付");
				this.loading = true;
				uni.login({
					success: (e) => {
						console.log("login success", e);
						uni.request({
							url: `https://unidemo.dcloud.net.cn/payment/wx/mp?code=${e.code}&amount=0.01`,
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
												content: "支付失败,原因为: " + res.errMsg,
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
				uni.showLoading({
					title: '处理中请耐心等待'
				});
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

				uni.hideLoading();
// 				uni.showToast({
// 					title: '支付成功',
// 					icon:"success",
// 					duration: 1500,
// 					complete(){
// 						setTimeout(function () {
// 							uni.navigateBack({
// 								delta: 1
// 							});
// 						}, 1500);
// 					}
// 				});

				uni.showModal({
					content: "支付失败,重新支付" ,
					showCancel: false,
					complete(e) {
							if(!e.confirm){
								uni.navigateBack({
									delta: 2
								});
							}
					}
				})
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
				let url = 'https://demo.dcloud.net.cn/payment/?payid=' + e + '&appid=' + appid + '&total=0.01';
				return new Promise((res) => {
					uni.request({
						url: url,
						success: (result) => {
							res(result);
						},
						fail: (e) => {
							res(e);
						}
					})
				})
			}
		}
	}
</script>

<style>
	.rmbLogo {
		font-size: 40upx;
	}

	button {
		background-color: #007aff;
		color: #ffffff;
	}
</style>
