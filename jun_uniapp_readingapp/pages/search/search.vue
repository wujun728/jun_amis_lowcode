<template>
	<view>
		<cu-custom bgColor="bg-gradual-pink" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">搜索页面</block>
		</cu-custom>
		<view class="cu-bar search bg-white">
			
			<view class="search-form round">
				<text class="cuIcon-search"></text>
				<input @focus="InputFocus" @blur="InputBlur" v-model="value" :adjust-position="false" type="text"
					placeholder="搜索图片、文章、视频" confirm-type="search"></input>
			</view>
			<view class="action">
				<button class="cu-btn bg-green shadow-blur round" @click="handleSearch">搜索</button>
			</view>

		</view>
		<scroll-view scroll-y class="page">
			<post-item v-for="(item,index) in postList" :post="item"></post-item>
		</scroll-view>
	</view>
</template>

<script>
	import {
		getPostList
	} from '@/https/api/api.js'
	import PostItem from '@/pages/components/post/post-item.vue'
	export default {
		data() {
			return {
				value: "",
				postList: []
			}
		},
		methods: {
			InputFocus(e) {
				this.InputBottom = e.detail.height
			},
			InputBlur(e) {
				this.InputBottom = 0
			},
			back2OriginPage() {
				uni.navigateBack({});
			},
			handleSearch() {
				console.log(this.value)
				var data = {
					title: this.value,
					content: this.value
				}
				this.getList(data)
			},
			getList(data) {
				getPostList(data).then(res => {
					var list = res.data.rows;
					this.changeList(list)
					this.postList = list;
					console.log(this.postList);
				})
			},
			changeList(list) {
				list.forEach(item => {
					// console.log(item)
					if (item.pictures) {
						var str = item.pictures.toString().trim();
						var pics = str.split(";");
						item.pictures = pics;
						console.log(item);

					}
					item.nickName = item.reUser.nick ? item.reUser.nick : "系统默认";
					item.avatar = this.appBaseUrl + item.reUser.pic;
					item.createTime = "2021-03-04"

				});
				return list;
			}
		},
		components: {
			PostItem,
		}
	}
</script>

<style>

</style>
