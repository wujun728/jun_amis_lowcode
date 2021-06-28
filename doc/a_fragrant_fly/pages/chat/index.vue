<template>
<view class="tui">
	<chat-list :contentHeight="winHeight" ></chat-list>
</view>
</template>

<script>
	import chatList from "../../components/pages/chat/list.vue"
	export default {
		components:{chatList},
		data() {
			return {
				winHeight:0,
				contentHeight:0,
				title: '消息',
				showImg: false,
				order:[],

			}
		},
		mounted() {
			uni.setNavigationBarTitle({
				title: this.title
			});
		},onLoad() {
				let winHeight = uni.getSystemInfoSync().windowHeight;
			//创建节点选择器 获取底部导航高度 
				this.contentHeight=(winHeight-uni.upx2px(100));
				this.winHeight = winHeight;
		},
		methods:{
// 			goPage(value){
// 				var type=value.type;
// 				var  url ='/pages/chat/chat';
// 				if(type==='order'){
// 					url ='/pages/chat/order';
// 				}
// 				console.log(value);
// 				uni.navigateTo({
// 					url: url
// 				});
// 			}
		}
	}
</script>

<style>
.tui .tui-bagde{
	background: #FF4040;
	color: #fff;
	position: absolute;
	right:-15upx;
	top:-15upx
}
.title {
	padding: 20upx;
}
.uni-list-cell:last-child{
	border-bottom:1px solid #e5e5e5;
}
</style>
