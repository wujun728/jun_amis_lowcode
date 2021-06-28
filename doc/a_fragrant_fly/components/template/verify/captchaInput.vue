<template>
	<view class="captcha">
		<view class="captcha-left">
			<!-- <view class="uni-icon uni-icon-mic" @tap="startRecognize"> </view> -->
			<image :src="captcha" mode="aspectFit" @tap="resetCode"></image> 
		</view>
		<view class="captcha-center">
			<input class="input-text" type="text" v-model="inputCaptcha" placeholder="请输入图片验证码"></input>
		</view>

	</view>
</template>

<script>
	
	export default {
		name: "captcha-input",
		props:['captchaUrl'],
		data() {
			return {
				captcha:'http://s4.sinaimg.cn/bmiddle/003bsgbmgy6R6efoOr1c3',
				inputCaptcha: ''
			}
		}, 
		methods: {
			getcaptcha: function (){
			},
			resetCode: function () { 
				var that = this;
				console.log('template/verify/captchainput。刷新验证码')
				this.captcha=this.captchaUrl+'?time='+new Date().getTime();
				// console.log(that.Config.client())
			},
			sendMessge: function () {
// 				var that = this;
// 				if (that.inputValue.trim() == '') {
// 
// 					that.inputValue = '';
// 				} else {
// 
// 					//点击发送按钮时，通知父组件用户输入的内容
// 					this.$emit('send-message', {
// 						type: 'text',
// 						content: that.inputValue
// 					});
// 					that.inputValue = '';
// 				}
			}
		},
		onLoad() {
			this.captcha=this.captchaUrl;
			// this.resetCode();
			// this.captcha=this.Config.service.url+'/code/captcha?time='+new Date().getTime();
		}
	}
</script>

<style>
	/* @import "../../common/icon.css"; */

	.captcha {
		
		display: flex;
		flex-direction: row;
		width: 750upx;
		margin: 10upx 0;
		/* height: 80upx; */
		height: 80upx;
		/* border-top: solid 1px #bbb; */
		overflow: hidden;
		/* margin: 10upx; */
		/* background-color: #fafafa; */
	}
	.captcha-left {
		flex: 1;
		padding:0 10upx;
		width: 320upx;
		height: 80upx;
		display: flex;
		flex-grow:1;
		justify-content: center;
		align-items: center;
	}
/* 	.captcha image{
		margin: 10upx;
	} */


	.captcha-center {

		flex: 1;
		flex-grow:1;
		display: flex;
		justify-content: flex-end;
		align-items: center;
	}

</style>
