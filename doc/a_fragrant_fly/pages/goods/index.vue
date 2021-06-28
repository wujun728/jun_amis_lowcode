<!-- 侧边分类导航 -->
<template>
	<view class="container">
		<view class="page-body">
			<scroll-view class="nav-left" scroll-y :style="'height:'+height+'px'">
				 <!-- @click="categoryClickMain(item,index)" -->
				<view class="nav-left-item"  v-for="(item,key,index) in category" :key="key" :data-id="item.id" :data-index="index"
            @tap="switchTab" :class="index==categoryActive?'active':''" >
					{{item.name}}
				</view>
			</scroll-view>
	<!-- 		<scroll-view class="nav-right" :scroll-y="isScroll" :scroll-into-view="toView" :scroll-with-animation="true" :style="'height:'+height+'px'" >
				<view :id="index==0?'first':''" class="nav-right-item" v-for="item in subCategoryList" :key="item">
					<image :src="item.LOGO" />
					<view>{{item.NAME}}</view>
				</view>
				<page-foot :name="name" v-if="subCategoryList.length > 1"></page-foot>
			</scroll-view> -->
			
			<scroll-view  class="nav-right" :style="'height:'+height+'px'" :scroll-y="isScroll" :scroll-into-view="toView" :scroll-with-animation="true">
			         <block v-for="(item,key,idx) in detail"  :key="idx">
			            <view :id="item.id" class="cate-box">
<!-- 			                <view class="cate-banner">
			                    <image :src="item.banner"></image>
			                </view>
			                <view class="cate-title">
			                    <text>{{item.cate}}</text>
			                </view> -->
			
			                <view class="product" v-if="item.detail">
			                   <view class="product-list" v-for="(val,key2,index) in item.detail" :key="index" >
			                        <navigator url="../list/list">
			                            <image :src="val.thumb"></image>
			                            <view class="classname"><text>{{val.name}}</text></view>
			                        </navigator>
			                    </view>
			                </view>
			            </view>
			        </block> 
			    </scroll-view>
			
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
// 				category: [
// 					{name:'果味',id:'guowei'},
// 					{name:'蔬菜',id:'shucai'},
// 					{name:'炒货',id:'chaohuo'},
// 					{name:'点心',id:'dianxin'},
// 					{name:'粗茶',id:'cucha'},
// 					{name:'淡饭',id:'danfan'}
// 				],
				detail:[],
				curIndex: 0,
				isScroll: false,
				toView: 'guowei',
				
				categoryList: [],
				subCategoryList: [],
				height: 0,
				categoryActive: 0,
				scrollTop: 0,
				scrollHeight: 0,
				name: "七月_"
			}
		},mounted:function () {
			var self = this;
				uni.request({
					url:'http://www.gdfengshuo.com/api/wx/cate-detail.txt',
					success(res){
						console.log(res.data)
						self.detail =res.data
					}
				});
		},
		methods: {
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
				
			},
// 			scroll(e) {
// 				this.scrollHeight = e.detail.scrollHeight;
// 			},
// 			categoryClickMain(categroy, index) {
// 				this.categoryActive = index;
// 				this.subCategoryList = categroy.subCategoryList;
// 				this.scrollTop = -this.scrollHeight * index;
// 			},
			getCategory() {
				for (var i = 1; i < 21; i++) {
					var subList = [];
					for (var j = 1; j < 31; j++) {
						subList.push({
							"NAME": "分类" + i + ":商品" + j,
							"LOGO": "http://placehold.it/50x50"
						})
					}
					this.categoryList.push({
						"NAME": "分类" + i,
						"subCategoryList": subList
					})
				}
				this.subCategoryList = this.categoryList[0].subCategoryList;
			}
		},
		onLoad: function () {
			this.getCategory();
			this.height = uni.getSystemInfoSync().windowHeight;
		}
	}
</script>

<style>
	.page-body {
		display: flex;
	}

	.nav {
		display: flex;
		width: 100%;
	}

	.nav-left {
		width: 30%;
	}

	.nav-left-item {
		height: 100upx;
		border-right: solid 1px #E0E0E0;
		border-bottom: solid 1px #E0E0E0;
		font-size: 30upx;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.nav-right {
		width: 70%;
	}

	.nav-right-item {
		width: 28%;
		height: 220upx;
		float: left;
		text-align: center;
		padding: 11upx;
		font-size: 28upx;
	}

	.nav-right-item image {
		width: 100upx;
		height: 100upx;
	}

	.active {
		color: #007AFF;
	}
</style>
