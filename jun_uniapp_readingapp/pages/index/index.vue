<template>
	<view>
		<community v-if="PageCur=='community'"></community>
		<find v-if="PageCur=='find'"></find>
		<home v-if="PageCur=='home'"></home>
		<message v-if="PageCur=='message'"></message>
		<my v-if="PageCur=='my'"></my>
		<view class="cu-bar tabbar bg-white shadow foot">
			<view class="action" @click="NavChange" data-cur="community">
				<view class='cuIcon-cu-image'>
					<image :src="'/static/tabbar/community' + [PageCur=='community'?'_cur':''] + '.png'"></image>
				</view>
				<view :class="PageCur=='community'?'text-green':'text-gray'">社区</view>
			</view>
			<view class="action" @click="NavChange" data-cur="find">
				<view class='cuIcon-cu-image'>
					<image :src="'/static/tabbar/find' + [PageCur=='find'?'_cur':''] + '.png'"></image>
				</view>
				<view :class="PageCur=='find'?'text-green':'text-gray'">发现</view>
			</view>
			<view class="action" @click="NavChange" data-cur="home">
				<view class='cuIcon-cu-image'>
					<image :src="'/static/tabbar/home' + [PageCur=='home'?'_cur':''] + '.png'"></image>
				</view>
				<view :class="PageCur=='home'?'text-green':'text-gray'">主页</view>
			</view>
			<view class="action" @click="NavChange" data-cur="message">
				<view class='cuIcon-cu-image'>
					<image :src="'/static/tabbar/message' + [PageCur == 'message'?'_cur':''] + '.png'"></image>
				</view>
				<view :class="PageCur=='message'?'text-green':'text-gray'">消息</view>
			</view>
			<view class="action" @click="NavChange" data-cur="my">
				<view class='cuIcon-cu-image'>
					<image :src="'/static/tabbar/my' + [PageCur == 'my'?'_cur':''] + '.png'"></image>
				</view>
				<view :class="PageCur=='my'?'text-green':'text-gray'">我的</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
		return {
				PageCur: 'community'
			}
		},
		methods: {
			NavChange: function(e) {
				this.PageCur = e.currentTarget.dataset.cur
			}
		}
	}
</script>

<style>

</style>
