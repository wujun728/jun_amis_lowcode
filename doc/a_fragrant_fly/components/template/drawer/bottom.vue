<template>

			<view   :style="drawerBottomShow?'top:0':'top:100vh'" class="tui-flex  tui-fixed tui-column tui-bottom-nav tui-center tui-drawer" style="height: 100vh;width: 100%;padding-bottom: 100upx;">
				<view  v-if="drawerBottomShow" class="toubu">
					<view class="toubu-list tui-flex">
						<view  class="left tui-relative">
								<image class="img tui-absolute" :src="image" mode="aspectFill"></image>
						</view>
						<view  class="right tui-item tui-flex tui-column line-height6 relative">
							
							<view  class="tui-title" style="position: relative;">
								<text class="tui-absolute  iconfont" @tap="onClose()" style="right: 0upx;top: 0;font-size:2em ;">✕</text>
								<text style="color: red;">￥{{goods.price}}</text>
							</view>
							<view  class="text"  v-if="goods.version">
								已选：<text class="uni-badge tui-button tui-tag"   style="line-height: 1.6;height: 1.6em;" >{{goods.versionName}}</text>
							</view>
							<view class="tui-item number-box" style="font-size:1.5em ;" >
								<!-- <number-box :min="1" :max="parseInt(goods.stock)"  :value="number"   v-on:change="numberUpdate" ></number-box> -->
							</view>
						</view>
					</view>
				</view>
				<view v-if="drawerBottomShow && goods.version"  class="tui-list tui-flex " style="flex-wrap: wrap;justify-content: space-between;" >

					<text   style="line-height: 1.6;height: 1.6em;" class="tui-button tui-tag"  :style="key==botIdx?'opacity:0.5;':'opacity:1;'" v-for="(item,key) in goods.version "  :key="key" @tap="selection(key,item)">{{item.name}}</text>
				</view>
				<button  @tap="commit()" type="primary" style="width:100%">确认</button>
	
			</view>	
</template>

<script>
	// import numberBox from '../box/number.vue';
	var timeOut=0;
	export default {
		components: {
			// numberBox
		},
		props:{
			goods:{
					id: 1,
					image: 'https://img-cdn-qiniu.dcloud.net.cn/uploads/example/product3.jpg',
					title: '新鲜梨花带雨',
					price: 0.01,
					stock: 12,
					number:0,
					versionName:'',
					versionIdx:'',
					synopsis:"简介，这里是梨花带雨详情",
					version:[{name:'大份',price:18,stock:6,image:''}]
				}
		},
		data(){
			return{
				drawerBottomShow:false,
// 				goods:{
// 					id: 1,
// 					image: 'https://img-cdn-qiniu.dcloud.net.cn/uploads/example/product3.jpg',
// 					title: '新鲜梨花带雨',
// 					price: 0.01,
// 					stock: 12,
// 					number:0,
// 					versionName:'未选择',
// 					versionIdx:'',
// 					synopsis:"简介，这里是梨花带雨详情",
// 					version:[{name:'大份',price:18,stock:6,image:''}]
// 				},
				number:0,
				image:0,
				botIdx:0,
				isShow:false
			};
		},
		onLoad() {
			console.log("bottom")
		},
		watch: {
				drawerBottomShow(val){
					if(val){
						// var goods = Object.assign({}, this.goods);//深度拷贝源数据防止联动改变值
						var goods=this.goods;
						this.image=goods.image;
						this.number=goods.number;
						this.goods=goods; 
						this.isShow=true;
						// console.log(goods)

					}else{
						this.isShow=false;
					}
				}
			},methods:{
			commit(){
				var self =this;
				this.onClose();
					var goods =  self.goods;//深度拷贝源数据防止联动改变值

					self.$emit('change',goods);
				// }, 1000);
				
			},
			selection(idx,item){
				this.botIdx=idx;
				// var goods = Object.assign({}, this.goods);//深度拷贝源数据防止联动改变值
				var goods=this.goods;
					goods.number=1;
					goods.price=item.price ||goods.price;
					goods.versionName=item.name || '';
					this.image=item.image || goods.image;
					goods.stock=item.stock || goods.stock;
					goods.stock=parseInt(goods.stock)
					this.goods=goods;
			},
			onShow(){
				this.drawerBottomShow=true;

			},
			onClose(){
				this.drawerBottomShow=false;
			},
			numberUpdate(item){
				this.goods.number=item.value;
				console.log(item.value)
			}
		},mounted() {
		}
	}
</script>

<style>


	.tui .line-height6{
		line-height: 1.6;
	}
	.tui .tui-bottom-nav{
		justify-content: flex-end;
		width: 100%;
		bottom:0;
	}
	.tui .toubu{
		width: 100%;
		display: flex;
		flex-direction: column;
		justify-content: flex-end;
		height: 180upx;

	}
	.tui .toubu >.toubu-list{
		height: 180upx;
		width: 100%;
		background: #fff;
		
	}
	.tui .toubu .toubu-list >.left{
		width:280upx ;
	}
	.tui .toubu .toubu-list .left >.img{

		bottom: 0;
		height: 240upx;
		width: 240upx;
		background: #fff;
		padding:10upx ;
		border-radius: 10upx;
		text-align:center;
		left: 20upx;
		border: 1upx solid rgb(200,200,200);
	}
	.tui .toubu .toubu-list >.right{
		padding: 20upx;
		justify-content: center;
		text-align: left;
		box-sizing: border-box;
	}
  

	.tui .tui-tag{
		font-size: 1.2em;
		text-align: center;

		line-height: 40upx;

		border:#f9f9f9 solid 1upx;
	}
	.tui .tui-button:hover{
		opacity:0.6;
	}
	.tui .tanchu{
		width: 100%;
	}
	.tui .tui-drawer{
		height: 100%;
		top:0;
		bottom: 0;
		background: rgba(0,0,0,0.5);
		transition:top 1s;
		-moz-transition:top 1s;
		-webkit-transition:top 1s;
		-o-transition:top 1s;
	}
	.tui-drawer .tui-list{
		border:#f9f9f9 solid 1upx;

		box-sizing: border-box;
		height: 200upx;
		overflow-y:auto;
	}
	.tui .tui-list::-webkit-scrollbar {
		 display: none;
	}



</style>
