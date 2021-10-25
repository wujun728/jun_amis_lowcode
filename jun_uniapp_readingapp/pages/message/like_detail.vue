<template>
	<view>
		<cu-custom bgColor="bg-gradual-pink" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">喜欢/点赞/关注</block>
		</cu-custom>
		<scroll-view scroll-y class="page">
			<uni-segmented-control :current="current" :values="items" @clickItem="onClickItem" styleType="text"
				activeColor="#4cd964"></uni-segmented-control>
			<view class="content">
				<view v-show="current === 0">
					<view class="cu-list menu">
						<view class="cu-item" v-for="(item,index) in likeList" :key="index">
							<view class="content">
								<image
									:src="item.avatar"
									class="png" mode="aspectFit"></image>
								<text class="text-grey">{{item.nick}}</text>
							</view>
							<view class="action">
								<text class="text-grey text-sm">{{item.updateTime}}</text>
							</view>
						</view>
					</view>
				</view>
				<view v-show="current === 1">
					<view class="cu-list menu">
						<view class="cu-item" v-for="(item,index) in collectList" :key="index">
							<view class="content">
								<image
									:src="item.avatar"
									class="png" mode="aspectFit"></image>
								<text class="text-grey">{{item.nick}}</text>
							</view>
							<view class="action">
								<text class="text-grey text-sm">{{item.updateTime}}</text>
							</view>
						</view>
					</view>
				</view>
				<view v-show="current === 2">
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
				items: ["点赞", "收藏", "关注"],
				likeList : [{
					nick: "aaa",
					avatar : "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimage.bubuko.com%2Finfo%2F201707%2F20180111000307971408.png&refer=http%3A%2F%2Fimage.bubuko.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1619740258&t=916d073e6b159803f68e68070ed33c5c",
					updateTime : "2021-17-30",
				}],
				collectList : [{
					nick: "bbb",
					avatar : "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimage.bubuko.com%2Finfo%2F201707%2F20180111000307971408.png&refer=http%3A%2F%2Fimage.bubuko.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1619740258&t=916d073e6b159803f68e68070ed33c5c",
					updateTime : "2021-17-30",
				}],
			}
		},
		methods: {
			onClickItem(param) {
				this.current = param.currentIndex
			},
		}
	}
</script>

<style>

</style>
