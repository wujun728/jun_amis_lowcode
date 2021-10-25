<template>
	<view>
		<cu-custom bgColor="bg-gradual-pink" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">{{title}}</block>
		</cu-custom>
		<view>
			<view class="cu-form-group">
				{{content}}
			</view>
			<view class="cu-form-group">
				<view class="grid col-3 grid-square flex-sub">
					<view class="bg-img" v-for="(item,index) in imgList" :key="index" @tap="ViewImage" :data-url="imgList[index]">
						<image :src="appBaseUrl + imgList[index]" mode="aspectFill"></image>
					</view>
				</view>
			</view>
		</view>
		<view class="box">
			<view class="cu-bar btn-group bg-white">
				<button class="cu-btn shadow-blur round" @click="handleCollect()">收藏</button>
				<button class="cu-btn shadow-blur round" @click="handleLike()">点赞</button>
			</view>
		</view>
		<view class="cu-list menu-avatar comment solids-top">
			<view class="cu-item" v-for="(item,index) in remarks" :key="index">
				<view class="cu-avatar round" :style="[{ backgroundImage:'url(' + item.avatar + ')' }]"></view>
				<view class="content">
					<view class="text-grey">{{item.nick}}</view>
					<view class="text-gray text-content text-df">
						{{item.remark}}
					</view>
				</view>
			</view>
		</view>
		<view class="box">
			<view class="cu-bar input">
				<input v-model="remark" :adjust-position="false" class="solid-bottom" :focus="false" maxlength="300" cursor-spacing="10"></input>
				<button class="cu-btn bg-green shadow-blur" @click="handleRemark">发送</button>
			</view>
		</view>
	</view>
</template>

<script>
	import {
		insertRemark,
		insertLike,
		insertCollect,
		getPost
	} from '@/https/api/api.js'
	export default {
		data() {
			return {
				title: "",
				content: "",
				imgList: [],
				remark: "",
				aid: "",
				pid: "",
				user: {},
				remarks: [{
					avatar: "https://ossweb-img.qq.com/images/lol/img/champion/Morgana.png",
					nick: "莫甘娜",
					remark: "凯尔，你被自己的光芒变的盲目。",
					updateTime: "2018年12月4日",
				}]
			};
		},
		// navigate.vue页面接受参数
		onLoad: function(option) {
			const item = JSON.parse(decodeURIComponent(option.item));
			console.log(item)
			this.pid = item.id;
			this.re()
		},
		props: {
			post: {},
		},
		methods: {
			initPage(item) {
				this.title = item.title
				this.content = item.content
				if(item.pictures){
					var str = item.pictures.trim()
					this.imgList = str.split(";")
				}
				this.pid = item.id;
				this.user = this.$store.getters.getUserDetail;
				item.remarks.forEach(i => {
					if (i.reUser) {
						i.avatar = this.appBaseUrl + i.reUser.pic
					}
				})
				this.remarks = item.remarks
				console.log(this.remarks)
			},
			re() {
				getPost(this.pid).then(res => {
					console.log(res);
					this.initPage(res.data.data)
				})
			},
			ViewImage(e) {
				uni.previewImage({
					urls: this.imgList,
					current: e.currentTarget.dataset.url
				});
			},
			handleRemark() {
				console.log("reamrk");
				if (this.user.id) {
					var reqData = {
						remark: this.remark,
						pid: this.pid,
						aid: this.user.id
					}
					console.log(reqData)
					insertRemark(reqData).then(res => {
						console.log(res);
						uni.showModal({
							content: res.data.msg,
						});
						this.re();
						this.remark = "";
					})
				} else {
					uni.showModal({
						content: `孩子，先去登录`,
					});
				}
			},
			handleLike() {
				console.log("like");
				if (this.user.id) {
					var reqData = {
						pid: this.pid,
						uid: this.user.id
					}
					insertLike(reqData).then(res => {
						console.log(res);
						uni.showModal({
							content: res.data.msg,
						});
					})
				} else {
					uni.showModal({
						content: `孩子，先去登录`,
					});
				}
			},
			handleCollect() {
				console.log("collect");
				if (this.user.id) {
					var reqData = {
						pid: this.pid,
						aid: this.user.id
					}
					insertCollect(reqData).then(res => {
						console.log(res);
						uni.showModal({
							content: res.data.msg,
						});
					})
				} else {
					uni.showModal({
						content: `孩子，先去登录`,
					});
				}
			}
		},
	};
</script>

<style>
</style>
