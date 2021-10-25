<template>
	<view>
		<view class="uni-flex uni-column">
			<view class="flex-item flex-item-V" style="text-align: right;">
				<text style="margin: 0 5%;" @click="toRegistor()">注册</text>
			</view>
			<view class="flex-item flex-item-V">
				<image class="mu-login-image" src="@/static/mu/login_img.png"></image>
			</view>
			<view class="flex-item flex-item-V">
				<form>
					<label class="uni-list-cell uni-list-cell-pd uni-input mu-login-label">
                        <text>账号：</text>
						<input type="text"  v-model="account" placeholder="请输入邮箱" />
						<uni-easyinput prefixIcon="search" v-model="value" placeholder="请输入内容" @iconClick="onClick"></uni-easyinput>
					</label>
					
					<label class="uni-list-cell uni-list-cell-pd uni-input mu-login-label">
					    <text>密码 ：  </text>
						<input password="true"  type="text" v-model="password"  placeholder="请输入密码" />
					</label>
				</form>
			</view>
			<view class="flex-item flex-item-V">
				<view class="mu-box">
					<button size="mini" class="mu-button" @click="doLogin()">登录</button>
				</view>
				<view class="mu-box">
					<button size="mini" class="mu-button" @click="back2OriginPage()">先逛逛</button>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import { login } from '@/https/api/api.js'
	export default {
		data() {
			return {
				account : "",
				password : "",
			}
		},
		methods: {
			toRegistor(){
				uni.navigateTo({
					url:'/pages/Registor/Registor',
				})
			},
			doLogin(){
				var resdata = {
					account : this.account,
					password : this.password
				};
				login(resdata).then(res=>{
					console.log(res);
				});
			},
			onClick(){
			},
			back2OriginPage() {
				uni.navigateBack({
				});
			},
		}
	}
</script>

<style scoped>
.mu-login-image{
	width: 100%;
}
.mu-login-label{
	width: 80%;
	margin: 0 5%;
}

</style>
