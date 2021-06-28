<template>
	<view >
		<view class="header">
			<view class="input-view">
				<uni-icon type="search" size="22" color="#666666"></uni-icon>
				<input confirm-type="search" @confirm="confirm" class="input" type="text" placeholder="输入搜索关键词" />
			</view>
			<!-- #ifdef MP-WEIXIN -->
			<view class="icon" @tap="showRightDrawer">
				<uni-icon type="bars" color="#666666" size="22"></uni-icon>
			</view>
			<view class="nav-badge">
			<text v-if="totalCart>0" class="uni-badge uni-badge-warning">{{totalCart}}</text>
			</view>
			<!-- #endif -->
		</view>

		<view class="container">
		<view v-if="isShow" class="page-body">
<!-- 			<view class="nav-left" >
				<view class="nav-left-item" @click="categoryClickMain(index,item)" :key="index" :class="index==categoryActive?'active':''"
					v-for="(item,index) in categoryList">
					{{item.name}}
					<view class="nav-badge">
					<text v-if="item.number" class="uni-badge uni-badge-warning">{{item.number}}</text>
					</view>
				</view>
			</view> -->
			<!-- <scroll-view class="nav-right" scroll-y :scroll-top="scrollTop" @scroll="scroll"  scroll-with-animation> -->
				<view class="nav-item" v-if="categoryList[index] && categoryList[index].items">
						<view  :id="key" class="nav-right-item" v-for="(item,key,idx) in categoryList[index].items" :key="item.id">
							<!-- {{item}} -->
							<view class="nav-right-item-image" >
								<image v-on:click="isShow=!isShow,detail=item" :src="item.src" mode="aspectFill" />
							</view>
							<view class="nav-right-item-text">
								<view>标题:{{item.title}}</view>
								<view>库存:{{item.stock}}</view>
								<view>数量:{{item.number}}</view>
								<view>价格:{{item.price}}/{{item.newprice}}</view>
								{{numberValue}}
								<view class="category-numbox">
									<number-box :min="0" :max="item.stock" :item="item" :other="{id:item.id,upIndex:index}" v-on:update="numberUpdate" v-on:click="setNumber(item)"></number-box>
								</view>
								<!-- <button type="primary">添加</button> -->
							</view>
						</view>
				</view>
				<!-- <page-foot :name="name" v-if="subCategoryList.length > 1"></page-foot> -->
			<!-- </scroll-view> -->
		</view>
		<view v-if="!isShow" class="card-big">
			<div class="uni-card" >
				<!-- <image v-on:click="isShow=!isShow" :src="detail.src"/> -->
				<view class="nav-right-item-image"  >
					<image v-on:click="isShow=!isShow" :src="detail.src" mode="aspectFill" />
				</view>
				<view class="nav-right-item-text">
				{{detail.title}}
				</view>
			</div>
			<view class="uni-card">
				<view>标题:{{detail.title}}</view>
				<view>库存:{{detail.stock}}</view>
				<view>价格:{{detail.price}}/{{detail.newprice}}</view>
				<view class="category-numbox">
					<number-box :min="0" :max="9"></number-box>
				</view>
				<button type="primary">添加</button>
			</view>
		</view>

	</view>
			<uni-drawer :visible="rightDrawerVisible" mode="right" @close="closeRightDrawer">
				<view class="drawer-content">

								<view class="nav-left" >
									<button class="button "  @click="categoryClickMain(index,item),closeRightDrawer()" :key="index" :class="index==categoryActive?'active':''"
										v-for="(item,index) in categoryList">
										{{item.name}}
										<view class="nav-badge">
										<text v-if="item.total" class="uni-badge uni-badge-warning">{{item.total}}</text>
										</view>
									</button>
								</view>
					<button class="button" type="warn" @tap="closeRightDrawer">关闭</button>
<!-- 					<view class="uni-list">
						<view class="uni-list-cell" hover-class="uni-list-cell-hover">
							<view class="uni-list-cell-navigate uni-navigate-right" @tap="item1">
								Item1
							</view>
						</view>
						<view class="uni-list-cell uni-list-cell-last" hover-class="uni-list-cell-hover">
							<view class="uni-list-cell-navigate uni-navigate-right" @tap="item1">
								Item2
							</view>
						</view>
					</view> -->
				</view>
			</uni-drawer>
</view>
</template>

<script>
	import uniDrawer from '../../components/template/drawer/drawer.vue';
	import uniIcon from '../../components/template/icon/icon.vue';
				import numberBox from '../../components/template/box/number.vue'
				// import categoryList from"../../common/data/category.js"
				export default {
					components: {
						numberBox,
						uniDrawer,
						uniIcon
					},
					data() {
						return {
							rightDrawerVisible: false,
							// categoryActive:'',
							index:0,
							isShow:true,
							detail:{
									title:"商品1",
									src:'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1542463574450&di=435cc8ddb1d6422b217f30386cfd4de9&imgtype=0&src=http%3A%2F%2Fpic167.nipic.com%2Ffile%2F20180602%2F8952533_124808772000_2.jpg',
									stock:'13',
									price:'123',
									newprice:'99'
								},
							categoryList:[],//商品列表
							totalCart:0,//购物车商品总数量
							shopCart:{},
						
// 				        category: [
// 									{name:'果味',id:'guowei'},
// 									{name:'蔬菜',id:'shucai'},
// 									{name:'炒货',id:'chaohuo'},
// 									{name:'点心',id:'dianxin'},
// 									{name:'粗茶',id:'cucha'},
// 									{name:'淡饭',id:'danfan'}
// 								],
								// detail:[],
// 								curIndex: 0,
// 								isScroll: false,
// 								toView: 'guowei'
				
			};
		},mounted:function () {
			var self = this;
			this.categoryList=categoryList;
			// self.detail =res.data
			// 请求服务器
// 			var self = this;
// 				uni.request({
// 					url:'http://www.gdfengshuo.com/api/wx/cate-detail.txt',
// 					success(res){
// 						console.log(res.data)
// 						self.detail =res.data
// 					}
// 				});
		},methods:{
			/* 侧边菜单 */
			closeRightDrawer() {
				this.rightDrawerVisible = false;
			},
			showRightDrawer() {
				this.rightDrawerVisible = true;
			},
			confirm() {
				uni.showToast({
					title: '搜索'
				})
			},//侧边菜单完
			// 
			setNumber(e){
				console.log(e)
			},
			numberUpdate(value,item,other) {
				console.log(value)
				console.log(item)
				console.log(other)
// 				this.numberValue = value;
// 				this.detail.number=value;
				item.number=value;
				// 商品放进购物车
				if(item.number>=0){
					this.shopCart[other.id]=item;	
				}else{
					delete  this.shopCart[other.id];	
				}
				var shopCart =this.shopCart;
				var totalCart=0;
				for(var p in shopCart){
					totalCart+=shopCart[p].number;
				}
				this.totalCart=totalCart
			},
			categoryClickMain(index,item){
				this.index=index;
			},
			    switchTab(e){
				  const self = this;
				  this.isScroll= true;
				  setTimeout(function(){
					self.toView= e.target.dataset.id;
					  self.curIndex=e.target.dataset.index;
				  },0)
				  setTimeout(function () {
					self.isScroll=false;
				  },1)
					
				}
		}
	}
</script>

<style>
	/* 侧边菜单 */
.header {
		display: flex;
		flex-direction: row;
		padding: 10px 15px;
		align-items: center;
	}

	.input-view {
		display: flex;
		align-items: center;
		flex-direction: row;
		background-color: #e7e7e7;
		height: 30px;
		border-radius: 15px;
		padding: 0 10px;
		flex: 1;
	}

	.input {
		flex: 1;
		padding: 0 5px;
		height: 24px;
		line-height: 24px;
		font-size: 16px;
	}

	.icon {
		display: flex;
		flex-direction: column;
		justify-content: center;
		margin-left: 10px;
	}

	.page-content {
		padding: 15px;
		font-size: 16px;
		color: #6d6d6d;
		text-indent: 2em;
	}

	.uni-list {
		margin-top: 15px;
	}

	.drawer-content {
		padding: 15px;
	}

	.drawer-content>.title {
		font-size: 18px;
	}

	.drawer-content>.text {
		font-size: 15px;
	}

	.drawer-content>.button {
		font-size: 14px;
	}
	/* 侧边菜单完*/
	
	.category-numbox{
		width: 1rem;
		height:1rem;
		box-sizing: border-box;
	}
	.uni-card{
		
		display: flex;
		text-align: center;
		align-content: center;
		align-items: center;
		justify-content: center;
		flex-direction:column;
	}
	.card-big{
		text-align: center;
		display: flex;
		justify-content: center;
		flex-direction:column;
	}
	
		.page-body {
			display: flex;
		}
	
/* 		.nav-left {
			width: 100%;
			height: 100%;
			overflow-y: scroll; 
		} */
	
/* 		.nav-left-item {
			height: 150upx;
			border-right: solid 1px #E0E0E0;
			border-bottom: solid 1px #E0E0E0;
			font-size: 30upx;
			display: flex;
			align-items: center;
			justify-content: center;
		} */
	
/* 		.nav-right {
			width: 100%;
		} */
	.nav-item{
		width: 100%;
	}
		.nav-right-item {
			width: 100%;
			display: flex;
		}
		.nav-right-item-text{
			flex: 1;
		}
	.nav-right-item-image{
		display: flex;
		justify-content: center;
		text-align: center;
		box-sizing: border-box;
		width: 90%;
		flex: 2;
	}
		.nav-right-item image {
			box-sizing: border-box;
			width: 90%;
			height: 90%;
			padding: 10upx;
		}
	
		.active {
			color: #007AFF;
		}
/* page,.main{
    height: 100%;
}
.categroy-left{
    float: left;
    width: 150upx;
    height: 100%;
    border-right: 1px solid #ddd;
    box-sizing: border-box;
}
.categroy-left .cate-list{
    height: 90upx;
    line-height: 90upx;
    text-align: center;
    border-left: 3px solid #fff;
}
.categroy-left .cate-list.on{
    color: #AB956D;
    border-color: #AB956D;
}
.categroy-right{
    float: right;
    width: 600upx;
    height: 100%;
    overflow: hidden;
    
}
.cate-box{
    height: 100%;
    padding:40upx;
    box-sizing: border-box;
}
.cate-box .cate-banner image{
    display: block;
    width: 100%;
    height: 190upx;
}
.cate-title{
    position: relative;
    height: 30upx;
    line-height: 30upx;
    padding:30upx 0 55upx;
    text-align: center;
    color: #AB956D;
    font-size: 28upx;
}
.cate-title::before{
    position: absolute;
    left: 130upx;
    top: 43upx;
    content: '';
    width: 70upx;
    height: 4upx;
    background: #AB956D;
}
.cate-title::after{
    position: absolute;
    right: 130upx;
    top: 43upx;
    content: '';
    width: 70upx;
    height: 4upx;
    background: #AB956D;
}

.product-list{
    display: inline-block;
    width: 160upx;
    height: 160upx;
    text-align: center;
    margin:0 20upx 20upx 0;
    font-size: 24upx;
}
.product-list image{
    width: 80upx;
    height: 80upx;
    margin-bottom: 20upx;
}
.product-list:nth-child(3n){
    margin-right: 0;
} */
</style>
