<template>
<view class="page-body tui">
	<scroll-view class="scrollList" scroll-y   :style="{height:contentHeight + 'px'}" style="margin-top: 0;">
				<view class="uni-card tui-flex tui-center tui-column" style="padding-top: 50upx;">
						<image :src="goods.image" mode="aspectFill" class="goods-thumb"></image>
							<view class="goods-title">{{goods.title}}</view>

				</view>

				<view class="tui-flex tui-center">
					<view class="goods-price">
						<text style="color:red;">￥ {{goods.price}}</text>
					</view>
				</view>
				<view v-if="goods.stock" class="uni-card tui-flex tui-center">
					<!-- <text class="">库存:{{goods.stock}}</text> -->

				</view>
		<view class="uni-list">
            <radio-group @change="radioChange">
                <label class="uni-list-cell uni-list-cell-pd" v-for="(item, index) in goods.version" :key="index">
                    <view>
                        <radio :value="item.name" :checked="index === current" />
                    </view>
					
					<view class="uni-flex-item" v-if="item.price">￥{{item.price}}</view>
                    <view>{{item.name}}</view>
                </label>
            </radio-group>
        </view>
				<view class="uni-card tui-flex tui-column" >

						<view class="goods-content tui-border" >

						<view  v-if="goods.detail" class="uni-flex uni-column">
							
						<view class="goods-tab-nav">
									<view class="on tui-color tui-border " @tap="bindTap(0)" :data-index="0">商品详情</view>
									
						</view>
							<text>
								{{goods.detail}}
							</text>
						</view>
					<!-- </view> -->
						</view> 
				</view>
		</scroll-view>
		<!-- <drawer-bottom  ref="drawerBottom"  :drawerBottomShow="drawerBottomShow" :goods="goods" v-on:change="goodsUpdate"></drawer-bottom> -->
		<nav class="tui-bottom-nav tui-flex" style="bottom:0;z-index: 99;">
				<view class="tui-item tui-flex tui-center" style="justify-content: space-around;">
					<view class="iconfont icon-dianpu tui-flex tui-column" style="font-size: 1.4em;line-height: 0.8em;" @tap="goPage('shop')">
						<text style="font-size:0.6em ;">店家</text>
					</view>
					<view class="iconfont icon-kefu tui-flex tui-column" style="font-size: 1.4em;line-height: 0.8em;"  @tap="goPage('chat')">
						<text style="font-size:0.6em ;">客服</text>
					</view>
					<view class="iconfont icon-dingdanjihe tui-flex tui-column" style="font-size: 1.4em;line-height: 0.8em;" @tap="goPage('order')">
						<text style="font-size:0.6em ;">订单</text>
					</view>
					<view class="iconfont icon-wode tui-flex tui-column" style="font-size: 1.4em;line-height: 0.8em;" @tap="goPage('wode')">
						<text style="font-size:0.6em ;">我的</text>
					</view>
				</view>
				<button type="warn" @tap="goCart()" size="mini" style="line-height:100upx;padding: 0 15upx;">下单</button>
		</nav>
</view>
</template>

<script>

	import {ajax,Storage} from '@/common/yc_js/';
	import drawerBottom from '../../components/template/drawer/bottom.vue'
	// import icon from '../../components/template/icon/icon.vue'
	export default {
		components: {
			// icon,
			drawerBottom
		},
		data() {
			return {
				winHeight:0,
				contentHeight:100,
				drawerBottomShow:false,
					goods:{
							id: 1,
							image: 'https://img-cdn-qiniu.dcloud.net.cn/uploads/example/product3.jpg',
							title: '新鲜梨花带雨',
							price: 0.01,
							stock: 12,
							number:0,
							detail: '这里是梨花带雨详情。',
							synopsis:"简介，这里是梨花带雨详情",
							parameter: '125g/个',
							service: '不支持退货',
							version:[]
						},
						num: 1,
						totalNum: 0,
						totalPrice:0,
						current: 0
				
			};
		},
		methods:{
		radioChange: function(evt) {
			var goods= this.goods;
			console.log(evt)
			var version=goods.version|| [];
            for (let i = 0; i < version.length; i++) {
				console.log(version)
                if (version[i].name === evt.target.value) {
					console.log('等于')
					goods.versionName=evt.target.value;
					goods.versionIndex=i;
					goods.image=version[i].image||goods.image;
					goods.price=version[i].price||goods.price;
					goods.stock=version[i].stock|| goods.stock;
					goods.total=goods.price*goods.stock;
                    this.current = i;
					this.goods=goods;
					console.log(goods)
                    break;
                }
            }
        },
							xuanZhe(){
								if(typeof this.goods.version ==='object' && this.goods.version[0]){
									this.$refs.drawerBottom.drawerBottomShow=true;
								}else{
									// this.tongJi()
									this.goPage('cart')
								}
							},

							goodsUpdate(item){
								this.goods=item;
								// this.tongJi()
								console.log(item)
								this.goPage('cart')
							},
							goPage(e){
								var url='';
								switch (e){
									case "cart":
										url="/pages/goods/"+e+"?id=2";
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
										url="/pages/goods/"+e+"?id=2";
										break;
								}
			
								
								uni.navigateTo({
									url: url,
									success: res => {
										
									},
									fail: () => {},
									complete: () => {}
								});
						},goCart(){
							var cart=this.$store.getters.cart;
							var goods=this.goods;
							goods.number=goods.number || 1;
							delete goods.detail;
							 goods.selected=true;
							cart[goods.id]=goods;
							this.$store.commit('cart',cart)
							console.log('选中跳转获取页面栈判断上个页面是不是购物车页面,如果是就回返回，否在前进')
							var cur=getCurrentPages()
							var curObj=cur[cur.length-2] || {};
							if(curObj.route && curObj.route==="pages/goods/cart"){
								uni.navigateBack({
									delta: 1
								});
							}
							uni.navigateTo({
								url: "/pages/goods/cart?id=1",
								success: res => {
									console.log('res1')
								},
								fail: (res) => {
									console.log(res)
									console.log('res2')
								},
								complete: () => {
									console.log('res3')
								}
							});
					}
		},onLoad(e){
				let winHeight = uni.getSystemInfoSync().windowHeight;
			//创建节点选择器 获取底部导航高度 
				this.contentHeight=(winHeight-uni.upx2px(100));
				this.winHeight = winHeight;
			
			ajax.get('goodsDetail',(res)=>{
				
				var goods=res.data.data ||{};
						goods.number=1;
						
						if(typeof goods.versionIndex =='number' && typeof goods.version=='object' ){
							goods.versionName=goods.version[goods.versionIndex].name || '';
							goods.price=goods.version[goods.versionIndex].price || goods.price;
							goods.image=goods.version[goods.versionIndex].image || goods.image
							goods.stock=goods.version[goods.versionIndex].stock || 0;
						}else{
							goods.versionName='';
						}
						
						this.goods=goods;
				console.log(goods)
			})
			// console.log(e)
		}
	}
</script>

<style>

page{
	/* overflow-y: scroll; */
}
.goods-box .goods-thumb{

	border-radius: 20upx;
}


.goods-box .goods-operation{
	
	padding: 10upx 30upx;
	display: flex;
	align-items:center;
	box-sizing: border-box;
	border-radius: 50upx;
	color: #fff;
	font-size: 1.2em;
}

.goods-operation-left{
	justify-content: flex-start;
	display: flex;
	align-items: center;
	flex: 1;
	font-size: 1em;
}
.goods-operation-left i{
	padding:0 15upx;
}
.goods-operation-right{
	font-size: 1em;
	justify-content:flex-end;
	display: flex;
	align-items: center;
  width: 210upx;
}

.goods-stock{
    font-size: 28upx;
    margin-bottom: 20upx;
}
.goods-title{
    font-size: 40upx;
    margin-bottom: 30upx;
}
.goods-price{
    font-size: 40upx;
}
.goods-tab{
	display: flex;
	flex-direction :column;
}
.goods-tab-nav{
	display: flex;
	flex-direction :row;
	justify-content:space-around;
	box-sizing: border-box;


}
.goods-tab-nav view{
	font-size:1.3em ;
}

.goods-tab-nav .on{
	font-weight:bold;
	border-width:0 ;
	border-bottom-style:solid;
  border-bottom-width: 5upx; 
}

.goods-content{
	
	margin: 40upx;
	padding: 10upx;
	display: flex;
	flex-direction: column;
}
.goods-content view{
	border-width:2upx;
	display: flex;
	flex:1;
	box-sizing: border-box;
	
}
.goods-content text{
	flex: 1;
	text-align: center;

}
.goods-content text:last-child{

	border-left-width:0;
	flex: 3;
}
</style>
