<template>
	<view>
		<cu-custom bgColor="bg-gradual-pink" :isBack="false">
			<block slot="content">发现</block>
		</cu-custom>
		<scroll-view scroll-y class="page">
			<view class="uni-flex uni-column" style="color: #000000;">
				<view class="padding flex flex-direction bg-white">
					<button class="cu-btn lg" @click="toSearchPage()">请输入关键字</button>
				</view>
				<view class="bg-white cu-card case" @click="handleSign">
					<view class="cu-item shadow">
						<view class="image">
							<image src="https://ossweb-img.qq.com/images/lol/web201310/skin/big10006.jpg"
							 mode="widthFix"></image>
							<view class="cu-tag bg-blue">签到</view>
							<view class="cu-bar bg-shadeBottom"> <text class="text-cut">我已天理为凭，踏入这片荒芜，不再受凡人的枷锁遏制。我已天理为凭，踏入这片荒芜，不再受凡人的枷锁遏制。</text></view>
						</view>
					</view>
				</view>

                <view class="cu-list grid col-4 no-border">
                	<view class="cu-item" v-for="(item,index) in cuIconList" :key="index" >
                		<view :class="['cuIcon-' + item.cuIcon,'text-' + item.color]"></view>
                		<text>{{item.name}}</text>
                	</view>
                </view>
				<post-page></post-page>
				<!-- <view class="flex-item flex-item-V uni-bg-blue">列表页面</view>
				<view class="flex-item flex-item-V uni-bg-blue">
					<post-list :postDetail="postDetail"></post-list>
					<post-list :postDetail="postDetail"></post-list>
					<post-list :postDetail="postDetail"></post-list>
				</view> -->
			</view>
			<view class="cu-tabbar-height"></view>
		</scroll-view>

	</view>
</template>

<script>
	import {
		sign
	} from '@/https/api/api.js'
	import PostList from '@/pages/components/PostList/PostList.vue'
	import PostPage from '@/pages/components/post/post-page.vue'
	export default {
		data() {
			return {
				user:{},
				cuIconList: [{
					cuIcon: 'cardboardfill',
					color: 'red',
					badge: 120,
					name: '小说分享'
				}, {
					cuIcon: 'recordfill',
					color: 'orange',
					badge: 1,
					name: '书单'
				}, {
					cuIcon: 'picfill',
					color: 'yellow',
					badge: 0,
					name: '标签'
				}, {
					cuIcon: 'noticefill',
					color: 'olive',
					badge: 22,
					name: '小说话题'
				}],
				postDetail: {
					title: "uni-app",
					avatarUrl: "https://vkceyugu.cdn.bspapp.com/VKCEYUGU-dc-site/460d46d0-4fcc-11eb-8ff1-d5dcf8779628.png",
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
				url: 'https://vkceyugu.cdn.bspapp.com/VKCEYUGU-uni-app-doc/6acec660-4f31-11eb-a16f-5b3e54966275.jpg',
				avatarList: [{
					url: 'https://vkceyugu.cdn.bspapp.com/VKCEYUGU-dc-site/460d46d0-4fcc-11eb-8ff1-d5dcf8779628.png'
				}, {
					url: 'https://vkceyugu.cdn.bspapp.com/VKCEYUGU-dc-site/460d46d0-4fcc-11eb-8ff1-d5dcf8779628.png'
				}, {
					url: 'https://vkceyugu.cdn.bspapp.com/VKCEYUGU-dc-site/460d46d0-4fcc-11eb-8ff1-d5dcf8779628.png'
				}],
				
			}
		},
		methods: {
			toTestPage() {
				uni.navigateTo({
					url: '../../test/test'
				});
			},
			toSearchPage() {
				uni.navigateTo({
					url:'/pages/search/search'
				})
			},
			handleSign(){
				var data = {
					aid: this.user.aid,
					integral : 5
				}
				if(data.aid){
					sign(data).then(res=>{
						console.log(res)
						uni.showToast({
							title: "签到成功",
							icon: 'none',
							duration: 1000
						});
					})
				}else{
					uni.showToast({
						title: "请先登录",
						icon: 'none',
						duration: 1000
					});
				}
				
				
			}

		},
		mounted() {
			this.user = this.$store.getters.getUserDetail;
			if (this.user.pic) {
				this.avatarStyle = "background-image:url(" + this.user.pic + ");"
			    console.log(this.avatarStyle)
			}
		},
		components: {
			PostList,
			PostPage

		}
	}
</script>

<style>
	.chat-custom-right {
		flex: 1;
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex-direction: column;
		justify-content: space-between;
		align-items: flex-end;
	}

	.chat-custom-text {
		font-size: 12px;
		color: #999;
	}
</style>
