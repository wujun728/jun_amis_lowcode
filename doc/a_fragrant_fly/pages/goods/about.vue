<template>
<view class="page-body tui">
	<!-- <drawer-bottom  ref="drawerBottom"  :drawerBottomShow="drawerBottomShow" :about="about" v-on:change="aboutUpdate"></drawer-bottom> -->

	<scroll-view class="scrollList" scroll-y   :style="{height:contentHeight + 'px'}" style="margin-bottom: 120upx;">
				<view class="uni-card tui-flex tui-center tui-column" style="padding-top:50upx ;">
						<image :src="about.image" mode="aspectFill" class="about-thumb"></image>
							<view class="tui-title " style="opacity: 0.7;">{{about.name}}</view>

				</view>

<!-- 				<view class="uni-card flex center">
					<view class="about-price">
						<text style="color:red;">{{about.name}}</text>
					</view>
				</view> -->
<!-- 				<view v-if="about.address" class="uni-card flex center">
					<text class="">地址:{{about.address}}</text>

				</view> -->
				<view class="uni-card tui-center">
					<view class="about-tab-nav">
							<view class="about-title " @tap="bindTap(0)" :data-index="0">客户须知</view>
					</view>
					<text class="tui-text">
						{{about.detail}}
					</text>
				</view>
				<!-- <view class="uni-card flex column center" > -->

					<view class="uni-card  tui-center">
						<view class="about-tab-nav">
								<view class="about-title " @tap="bindTap(0)" :data-index="0">商家介绍</view>
						</view>
						<text class="tui-text">
							{{about.detail}}
						</text>
					</view>
					<view class="uni-card tui-center">
						<view class="about-tab-nav " style="width: auto;">
								<text class=" about-title " @tap="bindTap(0)" :data-index="0">联系地址</text>
						</view>
						<view class="tui-item">{{about.address}}</view>
					</view>
				<!-- </view> -->
		</scroll-view>

</view>
</template>

<script>

	import {Storage,ajax} from '@/common/yc_js/';
	// import drawerBottom from '../../components/template/drawer/bottom.vue'
	export default {
		components: {
			// numberBox,
			// drawerBottom
		},
		data() {
			return {
				winHeight:0,
				contentHeight:100,
				drawerBottomShow:false,
				timeOut:'',
				timeOut2:'',
				selected:'',
				vIndex:0,
					about:{
						name:'王家小店',
						id: 1,
						image: 'https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1554891209&di=7f3924b8f49b388467d0ac8ba44a788e&src=http://img.article.pchome.net/00/23/22/32/pic_lib/s960x639/31s960x639.jpg',
						address: "北京市东城区王府井门口",
						detail: '这里是梨花带雨详情。',
						synopsis:"简介，这里是梨花带雨详情"
						}
				
			}
		},
		methods:{
			xuanZhe(){
				if(typeof this.about.version ==='object' && this.about.version[0]){
					this.$refs.drawerBottom.drawerBottomShow=true;
				}else{
					this.tongJi()
					this.goPage('cart')
				}
			},
			tongJi(){
				var cart={};
				cart[this.about.id]=this.about;
				Storage.set('cart',cart,100)
			},
			aboutUpdate(item){
				this.about=item;
				this.tongJi()
				console.log(item)
				this.goPage('cart')
			},
			goPage(e){
				var url='';
				switch (e){
					case "cart":

			// 												var about=this.about;
			// 												var cart={};
			// 												// cart[this.about.id]=this.about;
			// 												for(let i=0;i<about.length;i++){
			// 													if(about[i].selected){
			// 														cart[about[i].id]=about[i];
			// 													}
			// 												}
								// Storage.set('cart',cart,1000);
						url="/pages/about/"+e+"?id=2";
						break;
						case "wode":
						url="/pages/user/"+e+"?id=2";
						break;
						case "chat":
						url="/pages/chat/"+e+"?id=2";
						break;
						case "order":
						url="/pages/pay/"+e+"?id=2";
						break;
						
					default:
						url="/pages/about/"+e+"?id=2";
						break;
				}

				
				uni.navigateTo({
					url: url,
					success: res => {
						
					},
					fail: () => {},
					complete: () => {}
				});
			},gotoCart(){
				var cart={};
				cart[this.about.id]=this.about;
				Storage.set('cart',cart,100)
				uni.navigateTo({
					url: "/pages/about/cart?id=2",
					success: res => {
						
					},
					fail: () => {},
					complete: () => {}
				});
			},
			  bindTap(index) {
				  // console.log(index)
				this.curIndex=index

			  }
		},onLoad(e){
				let winHeight = uni.getSystemInfoSync().windowHeight;
			//创建节点选择器 获取底部导航高度 
				this.contentHeight=(winHeight-uni.upx2px(100));
				this.winHeight = winHeight;
			
			ajax.get('aboutDetail',(res)=>{
				var about=res.data.data ||{};
						about.number=1;
						about.versionName='未选择';
				this.about=about;

			})
			console.log(e)
		}
	}
</script>

<style>

page{
	/* overflow-y: scroll; */
}
.about-box .about-thumb{

	border-radius: 20upx;
}
.about-title{
	
	font-size: 1.2em;
}
.about-box text{
	opacity: 0.7;
}
.about-box .about-operation{
	
	padding: 10upx 30upx;
	display: flex;
	align-items:center;
	box-sizing: border-box;
	border-radius: 50upx;
	color: #fff;
	font-size: 1.2em;
}

.about-operation-left{
	justify-content: flex-start;
	display: flex;
	align-items: center;
	flex: 1;
	font-size: 1em;
}
.about-operation-left i{
	padding:0 15upx;
}
.about-operation-right{
	font-size: 1em;
	justify-content:flex-end;
	display: flex;
	align-items: center;
	width: 210upx;
}

.about-content{
	
	margin: 40upx;
	padding: 10upx;
	display: flex;
	flex-direction: column;
}
.about-content view{
	border-width:2upx;
	box-sizing: border-box;
	
}
.about-content text{
	flex: 1;
	text-align: center;

}
.about-content text:last-child{

	border-left-width:0;
	flex: 3;
}
</style>
