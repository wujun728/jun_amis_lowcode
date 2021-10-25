<template>
	<view>
		<cu-custom bgColor="bg-gradual-pink" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">个人中心</block>
		</cu-custom>
		<l-file ref="lFile" @up-success="onSuccess"></l-file>
		<form>
			<view class="cu-form-group margin-top" @tap="onUpload">
				<view class="title">头像</view>
				<view class="cu-avatar radius bg-gray" :style="[{ backgroundImage:'url(' + appBaseUrl + user.pic + ')' }]"></view>
			</view>
			<view class="cu-form-group">
				<view class="title">昵称</view>
				<input  name="input" v-model="user.nick"></input>
			</view>
			<view class="cu-form-group">
				<view class="title">邮箱</view>
				<input  name="input" v-model="user.email"></input>
			</view>
			<view class="cu-form-group">
				<view class="title">生日</view>
				<input  name="input" v-model="user.birthday"></input>
			</view>
			<view class="cu-form-group">
				<view class="title">个性签名</view>
				<input  name="input" v-model="user.note"></input>
			</view>
			
			
			
		</form>
		
		<view class="box">
			<view class="cu-bar btn-group">
				<button class="cu-btn bg-blue shadow-blur round" @click="doCommit()">提交</button>
			</view>
			<view class="cu-bar btn-group">
				<button class="cu-btn shadow-blur round" @click="doLoginOut()">登出</button>
			</view>
		</view>
	</view>
</template>

<script>
	import {
		updateUser,selectUser
	} from '@/https/api/api.js'
	export default{
		data(){
			return{
				user: {
					pic: "https://ossweb-img.qq.com/images/lol/web201310/skin/big10001.jpg",
					nick : "",
					account : "",
					birthday: "",
					note : ""
				}
			}
		},
		methods:{
			/* 上传 */
			onUpload() {
				console.log("upload")
				/**
				 * currentWebview: 当前webview
				 * url：上传接口地址
				 * name：附件key,服务端根据key值获取文件流，默认file,上传文件的key
				 * header: 上传接口请求头
				 */
				var uploadPath = this.appBaseUrl + '/app/common/upload';
				
				this.$refs.lFile.upload({
					// #ifdef APP-PLUS
					// nvue页面使用时请查阅nvue获取当前webview的api，当前示例为vue窗口
					currentWebview: this.$mp.page.$getAppWebview(),
					// #endif
					url: uploadPath,
					name: 'appfile',
					// header: {'Authorization':'token'},
					// 其他业务参数直接写key,value,如：
					// key1: 'value1',
					// key2: 'value2',
				});
				console.log("upload end")
			},
			onSuccess(res) {
				console.log('上传成功回调', JSON.stringify(res));
				var response = JSON.parse(res.data.id); 
				let fileName = response.fileName;
				let fileUrl = response.url;
				this.user.pic = response.fileName
			},
			doCommit(){
				if(this.user.id){
					updateUser(this.user).then(res=>{
						uni.showModal({
							content: `提交成功！！！`,
						});
						this.back2OriginPage()
					})
				}else{
					uni.showModal({
						content: `孩子，先去登录`,
					});
				}
			},
			doLoginOut(){
				console.log("登出")
				this.$store.dispatch("storeUserData", {});
				this.$store.dispatch("logout");
				this.back2OriginPage()
			},
			back2OriginPage() {
				uni.navigateTo({
					url: '/pages/index/index',
				})
			},
		},
		mounted() {
			this.user = this.$store.getters.getUserDetail;
			if (this.user.pic) {
				this.avatarStyle = "background-image:url(" + this.user.pic + ");"
			    console.log(this.avatarStyle)
			}
			if(this.user.id){
				selectUser(this.user.id).then(res=>{
					console.log(res.data.data)
					this.user = res.data.data;
				})
			}
			
		},
		
		
	}
</script>

<style>
	
</style>
