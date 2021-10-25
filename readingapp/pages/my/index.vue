<template>
	<view>
		<cu-custom bgColor="bg-gradual-pink" :isBack="false">
			<block slot="content">我的</block>
		</cu-custom>
		<scroll-view scroll-y class="page">
			<view class="bg-white flex padding justify-center">
				<view class="padding">
					<view @click="toUserDetail" class="cu-avatar xl round" :style="[{ backgroundImage:'url(' + avatar + ')' }]"></view>
				</view>
			</view>
			<view class="bg-white flex padding justify-center">
				<button class="cu-btn bg-green round lg" v-if="!hasLogin" :class="{'hidden': false }" @click="toLoginPage()">登录</button>
			</view>
			<view class="cu-list grid col-4">
				<view class="cu-item" v-for="(item,index) in cuIcon" :key="index" v-if="item.isShow">
					<text class="lg text-gray" :class="'cuIcon-' + item.icon"></text>
					<text>{{item.name}}</text>
				</view>
			</view>
			<view class="cu-tabbar-height"></view>
		</scroll-view>

	</view>

</template>

<script>
	import TouXiang from '@/pages/components/TouXiang/TouXiang.vue'
	export default {
		data() {
			return {
				avatar: "https://ossweb-img.qq.com/images/lol/web201310/skin/big10001.jpg",
				imageLink: "http://192.168.0.112:8080/files/touxiang.jpg",
				CustomBar: this.CustomBar,
				cuIcon: [{
					icon: 'pic',
					name: '任务中心',
					isShow: true
				}, {
					name: '导入书籍',
					isShow: true
				}, {
					name: '我的书单',
					isShow: true
				}, {
					name: '青蔓会员',
					isShow: true
				}, {
					name: '浏览记录',
					isShow: true
				}, {
					name: '我的动态',
					isShow: true
				}, {
					name: '我的标签',
					isShow: true
				}, {
					name: '联系客服',
					isShow: true
				}, {
					name: '我的专辑',
					isShow: true
				}, {
					name: '我的喜欢',
					isShow: true
				}, {
					name: '消息中心',
					isShow: true
				}, {
					name: '图库',
					isShow: true
				}],
				modalName: null,
				gridCol: 3,
				gridBorder: false,
				menuBorder: false,
				menuArrow: false,
				menuCard: false,
				skin: false,
				listTouchStart: 0,
				listTouchDirection: null,
				user: {},
			}
		},
		methods: {
			toLoginPage() {
				console.log("to login page")
				uni.navigateTo({
					url: '/pages/my/login'
				});
			},
			toUserDetail(){
				console.log("to user detail page")
				uni.navigateTo({
					url: '/pages/detail/user_detail'
				});
			}

		},
		components: {
			"tou-xiang": TouXiang
		},
		mounted() {
			this.user = this.$store.getters.getUserDetail;
			if (this.user.pic) {
				this.avatar =  thia.appBaseUrl + this.user.pic;
			}
		},
		computed: {
			hasLogin: function() {
				return this.$store.getters.hasLogin;
			}
		}
	}
</script>

<style>
	.span-text {
		text-align: center;
	}
</style>
