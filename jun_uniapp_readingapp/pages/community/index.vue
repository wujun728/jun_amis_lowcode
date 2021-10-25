<template>
	<view>
		<cu-custom bgColor="bg-gradual-pink" :isBack="false">
			<block slot="content">社区</block>
		</cu-custom>
		<scroll-view scroll-y class="page">
			<button @click="toTestPage()">接口测试页面</button>
			<view class="bg-white flex padding justify-center">
				<view class="cu-capsule round">
					<view class='cu-tag bg-blue '>
						积分
					</view>
					<view class="cu-tag line-blue">
						{{integral}}
					</view>
				</view>
			</view>
			

			<view class="padding flex flex-direction bg-white">
				<button class="cu-btn lg" @click="toSearchPage()">请输入关键字</button>
			</view>
			<view class="flex bg-white">
				<view class="flex-sub padding-sm margin-xs radius">
					<view class="content">
						<text class="cuIcon-circlefill text-grey"></text>
						<text class="text-black">小说大全</text>
					</view>
				</view>
				<view class="flex-sub padding-sm margin-xs radius">
					<view class="content">
						<text class="cuIcon-circlefill text-grey"></text>
						<text class="text-black">购物逛街</text>
					</view>
				</view>
			</view>
			<view class="flex bg-white">
				<view class="flex-sub padding-sm margin-xs radius">
					<view class="content center">
						<text class="cuIcon-circlefill text-grey"></text>
						<text class="text-black"> 美食旅行</text>
					</view>
				</view>
				<view class="flex-sub padding-sm margin-xs radius">
					<view class="content">
						<text class="cuIcon-circlefill text-grey"></text>
						<text class="text-black"> 明星娱乐</text>
					</view>
				</view>
			</view>

			<view class="flex-item flex-item-V uni-bg-blue">
				<post-page></post-page>
			</view>
			<!-- <view class="flex-item flex-item-V uni-bg-blue">
				<post-list :postDetail="postDetail"></post-list>
			</view> -->
			<view class="cu-tabbar-height"></view>
		</scroll-view>
	</view>
</template>

<script>
	import {
		getIntegralList
	} from '@/https/api/api.js'
	import PostList from '@/pages/components/PostList/PostList.vue'
	import PostPage from '@/pages/components/post/post-page.vue'
	export default {
		data() {
			return {
				avatar: "",
				CustomBar: this.CustomBar,
				searchValue: "",
				src: 'https://vkceyugu.cdn.bspapp.com/VKCEYUGU-uni-app-doc/6acec660-4f31-11eb-a16f-5b3e54966275.jpg',
				postDetail: {
					title: "uni-app",
					avatarUrl: "https://vkceyugu.cdn.bspapp.com/VKCEYUGU-uni-app-doc/6acec660-4f31-11eb-a16f-5b3e54966275.jpg",
					note: "您收到一条消息",
					timeNote: "刚刚",
					content: "列表 title 超长文字显示一行,以下是测试文字以下是测试文字以下是测试文字以下是测试文字以下是测试文字以下是测试文字",
					imageUrl: [{
						url: 'https://vkceyugu.cdn.bspapp.com/VKCEYUGU-dc-site/460d46d0-4fcc-11eb-8ff1-d5dcf8779628.png',
						src: 'http://192.168.0.106:8080/profile/upload/2021/03/19/536015a7-896a-4ce5-a6ef-933f19381694.jpg'
					}, {
						url: 'https://vkceyugu.cdn.bspapp.com/VKCEYUGU-dc-site/460d46d0-4fcc-11eb-8ff1-d5dcf8779628.png',
						src: 'https://vkceyugu.cdn.bspapp.com/VKCEYUGU-dc-site/460d46d0-4fcc-11eb-8ff1-d5dcf8779628.png'
					}, {
						url: 'https://vkceyugu.cdn.bspapp.com/VKCEYUGU-dc-site/460d46d0-4fcc-11eb-8ff1-d5dcf8779628.png',
						src: '/static/logo.png'
					}]
				},
				integral: 0,
			}
		},
		methods: {
			toTestPage() {
				var value = {
					'1': 1
				}
				uni.navigateTo({
					url: '../test/test?item=' + encodeURIComponent(JSON.stringify(value))
				});
			},
			toSearchPage() {
				uni.navigateTo({
					url: '/pages/search/search'
				})
			},
			searchPost(e) {
				this.searchValue = e.detail.value
			}

		},
		mounted() {
			this.user = this.$store.getters.getUserDetail;
			if (this.user.pic) {
				this.avatar = this.appBaseUrl + this.user.pic;;
			}
			if (this.user.id) {
				var reqdata = {
					pageNum: 1,
					pageSize: 10,
					aid: this.user.id,
					integral: null
				}
				getIntegralList(reqdata).then(res => {
					console.log(res)
					console.log(res.data)
					this.integral = res.data.data[0].integral
				})
			}
			
		},
		components: {
			PostList,
			PostPage,
		}
	}
</script>

<style>
	.span-text {
		text-align: center;
	}
</style>
