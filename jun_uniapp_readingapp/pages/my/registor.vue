<template>
	<view>
		<cu-custom bgColor="bg-gradual-pink" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">注册</block>
		</cu-custom>
		<view class="flex-item flex-item-V">
			<image class="mu-login-image" src="@/static/mu/login_img.png"></image>
		</view>
		<!-- <view class="cu-form-group margin-top">
			<view class="title">用户名</view>
			<input placeholder="请输入用户名" v-model="username" name="input"></input>
		</view> -->
		<view class="cu-form-group">
			<view class="title">邮箱</view>
			<input placeholder="请输入邮箱" v-model="account" name="input"></input>
		</view>
		<view class="cu-form-group">
			<view class="title">验证码</view>
			<input placeholder="请输入验证码" name="input" v-model="vercode"></input>
			<button class='cu-btn bg-green shadow' @click="getVerifyCode()">验证码</button>
		</view>
		<view class="cu-form-group">
			<view class="title">密码</view>
			<input placeholder="请输入密码" type="password" v-model="password" name="input"></input>
		</view>
		<view class="cu-form-group">
			<view class="title">确认密码</view>
			<input placeholder="请再次输入密码" type="repassword" v-model="repassword" name="input"></input>
		</view>
		<view class="box">
			<view class="cu-bar btn-group">
				<button class="cu-btn bg-blue shadow-blur round" @click="doRgister()">注册</button>
			</view>
		</view>

	</view>
</template>

<script>
	import {
		register,verifyCode
	} from '@/https/api/api.js'
	export default {
		data() {
			return {
				username: "",
				account: "",
				password: "",
				easyinputStyle: {
					'borderColor': "#FFFFFF"
				},
				vercodetxt: "获取验证码",
				vercode: "",
				repassword: "",
				rescode: "1"
			}
		},
		methods: {
			toRegistor() {
				uni.navigateTo({
					url: '/pages/Registor/Registor',
				})
			},
			doRgister() {
				if(this.rescode != this.vercode){
					uni.showModal({
						content: `验证码不正确！！！`,
					});
					return;
				}
				var resdata = {
					account: this.account,
					password: this.password,
				};
				register(resdata).then(res => {
					console.log(res);
				});
			},
			back2OriginPage() {
				uni.navigateBack({});
			},
			getVerifyCode() {
				var emailresp = /^\w+@[a-z0-9]+\.[a-z]{2,4}$/;
				if (emailresp.test(this.account)) {
					var reqData = {
						account : this.account
					}
					verifyCode(reqData).then(response=>{
						console.log(response)
						if(response.data.msg){
							uni.showModal({
								content: `破邮箱，这个邮箱地址不存在！！`,
							});
						}else{
							this.rescode = response.data;
							uni.showModal({
								content: `验证码已发送到邮箱，请注意查收！！`,
							});
						}
						
					})
				} else {
					uni.showModal({
						content: `邮箱不正确，请检查！！！`,
					});
				}
			}
		}
	}
</script>

<style scoped>
	.mu-login-image {
		width: 100%;
	}

	.mu-label-registor-item {
		background-color: #FFFFFF;
		margin: 3%;
	}

	.mu-text-label {
		width: 15%;
	}
</style>
