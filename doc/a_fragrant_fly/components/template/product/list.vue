
<template>
				<view class="uni-product-list" v-if="productList.length">
					<view class="uni-product-item" hover-class="uni-list-cell-hover" v-for="(product,index) in productList"  :key="index">
						<view class="image-view" @tap="goTo(product)">

						<view  v-if="!product.show && index>showImageNum" :data-index="index" class="uni-product-image  lazy" style="display: flex;justify-content:center ;flex-direction:column">
							图片加载中...
						</view>
							
						<view v-else>
							<image  class="uni-product-image"  mode="aspectFill" :src="product.image" />
						</view>
						</view>
						<view class="uni-product-title">
						<text>{{product.title}}</text>
						<!-- 距离{{juli(index)}} -->
						<text class="uni-product-juli" >{{product.juli>1000? ((product.juli)/1000)+"千米":product.juli+'米'}}</text>
						</view>
						<view class="uni-product-price">
							<text class="uni-product-price-favour">￥{{product.price}}</text>
							<text class="uni-product-price-original">￥{{product.newprice}}</text>
							<text class="uni-product-tip">{{product.tip}}</text>
						</view>
					</view>
				</view>
</template>

<script>
	// 图片加载等等动画圈圈已废除 
	// import uniLoadMore from '../unit/uni-load-more.vue';
	var timeOut='';//定时器
export default {
	props:['productList'],
    data() {
        return {
			// image: '/static/image/60x60.png',
// 			loadingType: 1,//加载圈圈
// 			contentText: {
// 				// 加载圈圈上的文字内容
// 				contentdown: "",
// 				contentrefresh: "",
// 				contentnomore: ""
// 			},
			showImageNum:5//页面首次打开就会加载显示的图片数量，不参加懒加载触发。


        };
    },computed:{

	},
	mounted() {
		this.$nextTick(function(){
			console.log('mounted list');
			this.load()
		})
	},
	filters: {
		//管道
		juli: function (item) {
 		// 这里用坐标计算距离即可，模拟数据请删除
			return '1'+item.id+'00米';
		}
	},
    methods: {

			goTo(e){
				console.log('gotolist')
				console.log(e)
				uni.navigateTo({
				    url: '/pages/goods/detail?'+e
				});
			},
			load(e) {
				console.log('触发懒加载扫描')
                uni.createSelectorQuery().selectAll('.lazy').boundingClientRect((images) => {
					
                    images.forEach((image, index) => {
						// console.log(image.dataset.index+'top:'+image.top)
						// console.log(image.dataset.index+'bottom:'+image.bottom)
						
                        if (image.bottom <this.windowHeight) {
							this.onShow(image.dataset.index)
							// console.log(image)
                        }
                    })
                }).exec()
            },
			onShow(index) {
				var data=this.productList[index];
				if (data.show!=true) {
					clearTimeout(timeOut)
					data.show=true;
					timeOut=setTimeout(() => {
						// 异步处理 防止数据未显示出来
						this.$set(this.productList, index,data)
					}, 100)
				}
			},

    },
	beforeCreate() {
		// console.log(this.productList)
		// console.log("onLoad product/list 屏幕高度")
		// console.log(uni.getSystemInfoSync().windowHeight)
		this.windowHeight = uni.getSystemInfoSync().windowHeight
	},components:{
		// uniLoadMore
	}

};
</script>
<style>

    .defaul {
        opacity: 0.5;
		/* 渐显示 */
        transition: opacity 1s;
    }
	.defaul .loaded {
		opacity: 1;
	}
	.image-view{
		width: 100%;
		box-sizing: border-box;
		position: relative;
	}
	.uni-product-juli{
		color: #B42F2D;
		position:absolute;
		right: 10upx;
	}
	.uni-product-title{
		position: relative;
		box-sizing: border-box;
		/* padding: 0 15upx; */
		text-align: left;
		width: 100%;
	}
	.uni-product-price{
		text-align: left;
		width: 100%;
	}
.uni-product-image{
	width: 100%;
	padding: 0;
	margin: 0;
}
.uni-product-list{
	padding: 10upx;
	position: relative;
	flex-direction: row;
	display:flex;
	height: 100%;
	width: 100%;
	box-sizing:border-box;
}
.uni-product-item{
	background: #fff;
	margin: 8upx;
	padding: 8upx;
	flex: 1;
	text-align: center;
	flex-direction:column;
	/* 一行多列只需修改最小尺寸即可 */
	min-width: 40%;
	box-sizing: border-box;
}
.uni-product-item:hover{
	opacity: 0.9;
	padding: 10upx;
	background:#FF8C00;
}
</style>
