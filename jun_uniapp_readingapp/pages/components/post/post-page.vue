<template>
	<view>
		<scroll-view scroll-x class="bg-white nav" scroll-with-animation :scroll-left="scrollLeft">
			<view class="cu-item" :class="index==TabCur?'text-green cur':''" v-for="(item,index) in tabList" :key="index" @tap="tabSelect"
			 :data-id="index">
				{{item.dictLabel}}
			</view>
		</scroll-view>
		<post-item v-for="(item,index) in postList" :post="item"></post-item>
	</view>
</template>

<script>
	import {
		getDicts,
		getPostList
	} from '@/https/api/api.js'
	import PostItem from '@/pages/components/post/post-item.vue'
	export default {
		data() {
			return {
				TabCur: 0,
				scrollLeft: 0,
				tabList: [],
				postList: [{
					nick: "凯尔",
					createTime: "2019年12月3日",
					content: "折磨生出苦难，苦难又会加剧折磨，凡间这无穷的循环，将有我来终结！",
					avatar: "https://ossweb-img.qq.com/images/lol/web201310/skin/big10001.jpg",
					pictures: [
						'https://ossweb-img.qq.com/images/lol/web201310/skin/big10001.jpg',
						'https://ossweb-img.qq.com/images/lol/web201310/skin/big81005.jpg',
						'https://ossweb-img.qq.com/images/lol/web201310/skin/big25002.jpg',
						'https://ossweb-img.qq.com/images/lol/web201310/skin/big91012.jpg',
						'https://ossweb-img.qq.com/images/lol/web201310/skin/big91012.jpg'
					],
					remark: {
						avatar: "https://ossweb-img.qq.com/images/lol/web201310/skin/big10001.jpg",
					}
				}, {
					nick: "凯尔",
					createTime: "2019年12月3日",
					content: "折磨生出苦难，苦难又会加剧折磨，凡间这无穷的循环，将有我来终结！",
					avatar: "https://ossweb-img.qq.com/images/lol/web201310/skin/big10001.jpg",
					pictures: [
						'https://ossweb-img.qq.com/images/lol/web201310/skin/big10001.jpg',
						'https://ossweb-img.qq.com/images/lol/web201310/skin/big81005.jpg',
						'https://ossweb-img.qq.com/images/lol/web201310/skin/big25002.jpg',
						'https://ossweb-img.qq.com/images/lol/web201310/skin/big91012.jpg',
						'https://ossweb-img.qq.com/images/lol/web201310/skin/big91012.jpg'
					],
				}, ]

			}
		},
		methods: {
			tabSelect(e) {
				this.TabCur = e.currentTarget.dataset.id;
				this.scrollLeft = (e.currentTarget.dataset.id - 1) * 60;
				var classType = this.tabList[this.TabCur].dictValue
				console.log(this.tabList[this.TabCur])
				var reqData = {
					classType :classType
				}
				this.getList(reqData);
			},
			getList(data) {
				getPostList(data).then(res => {
					var list = res.data.rows;
					list = this.changeList(list);
					this.postList = list;
					console.log(this.postList);
				})
			},
			changeList(list) {
				list.forEach(item => {
					if (item.pictures) {
						var str = item.pictures.toString().trim();
						var pics = str.split(";");
						item.pictures = pics;
					}
					if(item.reUser){
						item.nick = item.reUser.nick ? item.reUser.nick : "系统默认";
						item.avatar = this.appBaseUrl + item.reUser.pic;
					}
					
					item.createTime = "2021-03-04"
				});
				return list;
			}

		},
		components: {
			PostItem
		},
		mounted() {
			getDicts("post_class").then(res => {
				this.tabList = res.data.data;
			});
			this.getList();
		}
	}
</script>

<style>
</style>
