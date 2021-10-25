<template>
	<view class="re-post-page">
		<view class="cu-card dynamic" :class="'no-card'">
			<view class="cu-item shadow">
				<view class="cu-list menu-avatar solids-top" @click="toDetail()">
					<view class="cu-item">
						<view class="cu-avatar round lg" :style="[{ backgroundImage:'url(' + post.avatar + ')' }]"></view>
						<view class="content flex-sub">
							<view>{{post.nick}}</view>
							<view class="text-gray text-sm flex justify-between">
								{{post.createTime}}
							</view>
						</view>
					</view>
				</view>
				<view class="text-content">
					{{post.content}}
				</view>
				<view class="grid flex-sub padding-lr" :class="'col-3 grid-square'">
					<view class="bg-img"  v-for="(item,index) in post.pictures" :key="index" :style="[{ backgroundImage:'url(' + appBaseUrl + item + ')' }]">
					</view>
				</view>
				<!-- <view class="text-gray text-sm text-right padding">
					<text class="cuIcon-attentionfill margin-lr-xs"></text> 10
					<text class="cuIcon-appreciatefill margin-lr-xs"></text> 20
					<text class="cuIcon-messagefill margin-lr-xs"></text> 30
				</view> -->
		        <view class="cu-list menu-avatar comment solids-top">
		        	<view class="cu-item" v-for="(item,index) in post.remarks" :key="index" >
		        		<view class="cu-avatar round" :style="[{ backgroundImage:'url(' + formatAvator(item.reUser) + ')' }]"></view>
		        		<view class="content">
		        			<view class="text-grey">{{item.nick}}</view>
		        			<view class="text-gray text-content text-df">
		        				{{item.remark}}
		        			</view>
		        		</view>
		        	</view>
		        </view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				remarkList :[{
					avatar : "",
					remark : "",
				}],
				remarks :[{
					avatar: "https://ossweb-img.qq.com/images/lol/img/champion/Morgana.png",
					nick : "莫甘娜",
					remark : "凯尔，你被自己的光芒变的盲目。",
					updateTime : "2018年12月4日",
				},{
					avatar: "https://ossweb-img.qq.com/images/lol/web201310/skin/big10006.jpg",
					nick : "凯尔",
					remark : "妹妹，如果不是为了飞翔，我们要这翅膀有什么用?",
					updateTime : "2018年12月4日",
				},]
			};
		},
		props:{
			post : {},
		},
		methods: {
			toDetail(){
				console.log("detail")
				var value = this.post
				uni.navigateTo({
					url: '/pages/detail/detail?item=' + encodeURIComponent(JSON.stringify(value))
				})
			},
			formatAvator(reUser){
				if(reUser){
					return this.appBaseUrl + reUser.pic
				}
				return ""	
			}
		},
	};
</script>

<style>
	.re-post-page{
		color: #000000;
	}
</style>
