<template>
	<view>
		<cu-custom bgColor="bg-gradual-pink" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">接口测试</block>
		</cu-custom>
		<text>返回值：{{amessage}}</text>
		<button @click="Meshello2">还原</button>
		<button @click="Meshello()">hello</button>
		<button @click="Meshello1">hello（uni.request）</button>
		<button @click="getStore">获取登录状态</button>
		<button @click="getUserLogin">获取用户信息</button>
		
	</view>
</template>

<script>
	import {hello,hello2} from '@/https/api/api.js'
	import appConfig from "@/https/appConfig.js"
	export default {
		data() {
			return {
				amessage: "默认为空"
			}
		},
		mounted() {
			
		},
		// navigate.vue页面接受参数
		onLoad: function (option) {
		    const item = JSON.parse(decodeURIComponent(option.item));
			this.amessage = item
		},
		components: {},
		methods: {
			back2OriginPage() {
				uni.navigateBack({
				});
			},
			Meshello() {
				hello().then(response => {
					console.log(response);
					this.amessage = response.data;
				})
			},
			Meshello1() {
				var aurl = appConfig.baseUrl + "/hello";
				uni.request({
					url: aurl, //
					success: (res) => {
						console.log(res.data);
						this.amessage = res.data;
					}
				});
			},
			getStore(){
				if(this.$store.getters.hasLogin){
					this.amessage = "true";
				}else{
					this.amessage = "false";
				}
			},
			getUserLogin(){
				this.amessage = this.$store.state.user.account
			},
			Meshello2() {
				this.amessage = "默认为空";
			},
		}
	}
</script>

<style>

</style>
