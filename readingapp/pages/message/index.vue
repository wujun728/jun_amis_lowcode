<template>
	<view class="re-post-page">
		<cu-custom bgColor="bg-gradual-pink" :isBack="false">
			<block slot="content">消息</block>
		</cu-custom>
		<scroll-view scroll-y class="page">
			<view class="cu-list menu" >
				<view class="cu-item arrow">
					<view class="content"  @click="toSysMsgPage()">
						<text class="cuIcon-circlefill text-grey"></text>
						<text class="text-grey">系统消息</text>
					</view>
				</view>
				<view class="cu-item arrow">
					<view class="content"  @click="toLikePage()">
						<text class="cuIcon-circlefill text-grey"></text>
						<text class="text-grey">喜欢/关注/点赞</text>
					</view>
				</view>
			</view>
			<uni-segmented-control :current="current" :values="items" @clickItem="onClickItem" styleType="text" activeColor="#4cd964"></uni-segmented-control>
			<view class="content">
				<view v-show="current === 0">
					暂无内容
				</view>
				<view v-show="current === 1">
					暂无内容
				</view>
			</view>
			<view class="cu-tabbar-height"></view>
		</scroll-view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				current: 0,
				items: ["我的私聊", "收到的文件"],
			}
		},
		components: {

		},
		methods: {
			onClickItem(param) {
				this.current = param.currentIndex
			},
			toLikePage(){
				console.log("like")
				uni.navigateTo({
					url:'/pages/message/like_detail'
				})
			},
			toSysMsgPage(){
				console.log("sys_msg")
				uni.navigateTo({
					url:'/pages/message/sys_msg_detail'
				})
			}
		}
	}
</script>

<style>
	.re-post-page {
		color: #000000;
	}
</style>
