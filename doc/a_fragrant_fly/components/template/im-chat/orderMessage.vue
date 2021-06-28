<template>
	<view class="m-item" :id="'message'+index" style="padding:0 15upx;">
			<view class="uni-card" >
				<view class="uni-card-header uni-card-media">
					<!-- <image class="uni-card-media-logo" src="https://img-cdn-qiniu.dcloud.net.cn/uniapp/images/uni@2x.png"></image> -->
					<view class="uni-card-media-body">
						<text class="uni-card-media-text-top">{{title}}</text>
						<text class="uni-card-media-text-bottom">{{message.time}}</text>
					</view>
				</view>
				<view class="uni-card-content-inner" style="font-size: 0.5em;">
					<!-- <view class="uni-card-media-body"> -->
						<view class="">订单号 : {{message.sn}}</view>
						<!-- <view class="">付款时间 : {{message.time}}</view> -->
						<view class="">商户名 : {{message.user}}</view>
						<view class="">付款金额 : {{message.sum}}</view>
					<!-- </view> -->
<!-- 					<view class="uni-card-content-inner">
						{{message.content}}
					</view> -->
					<view class="" @tap="goPage(message)" style="width: 100%;display: flex;"><text style="width: 3em;color:#007AFF;">详情</text><text style="white-space: nowrap;overflow: hidden;text-overflow: ellipsis;">{{message.content}}</text></view>
				</view>
				<!-- <view class="uni-card-footer" >详情<text style="white-space: nowrap;overflow: hidden;text-overflow: ellipsis;">{{message.content}}</text></view> -->
			</view>
<!-- 		<view class="m-left">
			<image class="head_icon" src="https://img-cdn-qiniu.dcloud.net.cn/uniapp/app/homeHL.png" v-if="message.user=='home'"></image>
		</view>
		<view class="m-content">
			<view class="m-content-head" :class="{'m-content-head-right':message.user=='customer'}">
				<view :class="'m-content-head-'+message.user">{{message.content}} </view>
			</view>
		</view>
		<view class="m-right">
			<image class="head_icon" src="https://img-cdn-qiniu.dcloud.net.cn/uniapp/app/customerHL.png" v-if="message.user=='customer'"></image>
		</view> -->
	</view>
</template>

<script>
	export default {
		props: ['message', 'index'],
		data(){
			return {
						title:'订单提醒',
			}
		},
		methods:{
			goPage(e){
				// var type=e.type;
				var  url ='../order/detail';
// 				if(type==='order'){
// 					url ='order';
// 				}
				// console.log(e);
				uni.navigateTo({
					url: url
				});
			}
		}
	}
</script>

<style>
/* 	.m-item {
		display: flex;
		flex-direction: row;
		padding-top: 40upx;
	}
	.m-left {
		display: flex;
		width: 120upx;
		justify-content: center;
		align-items: flex-start;
	}
	.m-content {
		display: flex;
		flex: 1;
		flex-direction: column;
		justify-content: center;
		word-break: break-all;
	}
	.m-right {
		display: flex;
		width: 120upx;
		justify-content: center;
		align-items: flex-start;
	}
	.head_icon {
		width: 80upx;
		height: 80upx;
	}
	.m-content-head {
		position: relative;
	}
	.m-content-head-right {
		display: flex;
		justify-content: flex-end;
	}
	.m-content-head-home {
		text-align: left;
		background: #1482d1;
		border: 1px #1482d1 solid;
		border-radius: 20upx;
		padding: 20upx;
		color: white;
	}
	.m-content-head-home:before {
		border: 15upx solid transparent;
		border-right: 15upx solid #1482d1;
		left: -26upx;
		width: 0;
		height: 0;
		position: absolute;
		content: ' '
	}
	.m-content-head-customer {
		border: 1upx white solid;
		background: white;
		border-radius: 20upx;
		padding: 20upx;
	}
	.m-content-head-customer:after {
		border: 15upx solid transparent;
		border-left: 15upx solid white;
		top: 20upx;
		right: -26upx;
		width: 0;
		height: 0;
		position: absolute;
		content: ' '
	} */
</style>
