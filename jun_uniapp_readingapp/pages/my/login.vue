<template>
	<view>
		<cu-custom bgColor="bg-gradual-pink" :isBack="false">
			<block slot="backText">返回</block>
			<block slot="content">登录</block>
		</cu-custom>
		<view class="flex-item flex-item-V">
			<image class="mu-login-image" src="@/static/mu/login_img.png"></image>
		</view>
		<view class="cu-form-group margin-top">
			<view class="title">账号</view>
			<input placeholder="请输入邮箱" v-model="account" name="input"></input>
		</view>
		<view class="cu-form-group">
			<view class="title">密码</view>
			<input placeholder="请输入密码" type="password" v-model="password" name="input"></input>
		</view>
		<view class="flex-item flex-item-V padding" style="text-align: right;">
			<text style="color: red;" @click="toResetPassword()">忘记密码</text>
		</view>
		<view class="box">
			<view class="cu-bar btn-group">
				<button class="cu-btn bg-green shadow-blur round lg" @click="doLogin()">登录</button>
			</view>
			<view class="cu-bar btn-group">
				<button class="cu-btn bg-blue shadow-blur round" @click="toRegistor()">注册</button>
			</view>
			<view class="cu-bar btn-group">
				<button class="cu-btn text-green line-green shadow" @click="back2OriginPage()">先逛逛</button>
			</view>
		</view>
	</view>
</template>

<script>
	import {
		login
	} from '@/https/api/api.js'
	export default {
		data() {
			return {
				message: "",
				account: "",
				password: "",
			}
		},
		methods: {
			toRegistor() {
				uni.navigateTo({
					url: '/pages/my/registor',
				})
			},
			doLogin() {
				var resdata = {
					account: this.account,
					password: this.password
				};
				login(resdata).then(res => {
					console.log(res)
					if (res.data.code == 200) {
						uni.showToast({
							title: res.data.msg,
							icon: 'none',
							duration: 1000
						});
						this.$store.dispatch("storeUserData", res.data.data);
						this.back2OriginPage()
					} else {
						uni.showToast({
							title: res.data.msg,
							icon: 'none',
							duration: 1000
						});
						
					}
				}).catch(e => {
					uni.showToast({
						title: e.errMsg,
						icon: 'none',
						duration: 1000
					});
				});
			},
			back2OriginPage() {
				uni.navigateTo({
					url: '/pages/index/index',
				})
			},
			toResetPassword() {
				uni.navigateTo({
					url: '/pages/my/reset_password',
				})
			}
		}
	}
</script>

<style scoped>
	.mu-login-image {
		width: 100%;
	}

	.mu-login-label {
		width: 80%;
		margin: 0 5%;
	}

	.popup-content {
		background-color: #fff;
		padding: 15px;
	}
</style>
