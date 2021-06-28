<template>
    <view class="uni-product-list tui" v-if="productList.length">
        <view class="uni-product-item tui-item" hover-class="uni-list-cell-hover" v-for="(product,index) in productList"
            :key="index">
            <view class="" style="padding: 8upx;background: #FFFFFF;border-radius: 5px;">
                <view class="image-view" @tap="goTo(product)">
                    <text class="tui-button tui-tip" style="right: 0;position: absolute;z-index: 1;">{{product.tip}}</text>

                    <view v-if="product.show && index>showImageNum" :data-index="index" class="uni-product-image  lazy"
                        style="display: flex;justify-content:center ;flex-direction:column">
                        图片加载中...
                    </view>

                    <view v-else>
                        <image class="uni-product-image" mode="aspectFill" :src="product.image" />
                    </view>
                </view>
                <view class="uni-flex-item tui-relative">
                    <view class="weight center uni-flex-item nowrap">
                        {{product.name}}
                    </view>

                    <view class="tui-tip tui-absolute" style="font-size: 0.6em;background-color:#fff;color:red;top:-5upx;right: 0;opacity: 0.7;">
                        {{product.juli>1000? ((product.juli)/1000)+"千米":product.juli+'米'}}
                    </view>
                </view>


                <!-- 						<view class="uni-flex">
                				简介:
                				<text>{{product.synopsis}}</text>
                			</view> -->
            </view>

        </view>
    </view>
</template>

<script>
    // 图片加载等等动画圈圈已废除 
    // import uniLoadMore from '../unit/uni-load-more.vue';
    var timeOut = ''; //定时器
    export default {
        props: ['productList'],
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
                showImageNum: 5 //页面首次打开就会加载显示的图片数量，不参加懒加载触发。


            };
        },
        computed: {

        },
        mounted() {
            this.$nextTick(function() {
                console.log('mounted list');
                this.load()
            })
        },
        filters: {
            //管道
            juli: function(item) {
                // 这里用坐标计算距离即可，模拟数据请删除
                return '1' + item.id + '00米';
            }
        },
        methods: {

            goTo(e) {
                // console.log('gotolist')
                // console.log(e)
                uni.navigateTo({
                    url: '/pages/goods/shop?id=' + e.id
                });
                // 				uni.navigateTo({
                // 				    url: '/pages/goods/detail?'+e
                // 				});
            },
            load(e) {
                var that = this;
                console.log({text:'触发懒加载扫描',windowHeight:this.windowHeight})
                var query=uni.createSelectorQuery();
                // console.log(query.in(this))
                query.in(this).selectAll('.lazy').boundingClientRect((images) => {
                    // console.log(images)
                    // setTimeout(() => {
                    // if (images.forEach) {
                    images.forEach((image, index) => {
                        // console.log(JSON.stringify(image))
                        // console.log(image.dataset.index+'top:'+image.top)
                        // console.log(image.dataset.index+'bottom:'+image.bottom)

                        if (image.bottom < this.windowHeight) {
                            console.log({bot:image.bottom,height:this.windowHeight,img:JSON.stringify(image)})
                            that.imageShow(image.dataset.index)
                            // console.log(image)
                        }
                    })
                    // }
                    // }, 10)
                }).exec()
            },
            imageShow(index) {
                console.log(index)
                var data = this.productList[index];
                if (data.show != true) {
                    clearTimeout(timeOut)
                    data.show = true;
                    timeOut = setTimeout(() => {
                        // console.log(data)
                        // 异步处理 防止数据未显示出来
                        this.$set(this.productList, index, data)
                        console.log(this.productList)
                    }, 100)
                }
            },

        },
        beforeCreate() {
            // console.log(this.productList)
            // console.log("onLoad product/list 屏幕高度")
            // console.log(uni.getSystemInfoSync().windowHeight)
            this.windowHeight = uni.getSystemInfoSync().windowHeight
        },
        components: {
            // uniLoadMore
        }

    };
</script>
<style>
    .uni-product-tip {
        left: 0;
    }

    .defaul {
        opacity: 0.5;
        /* 渐显示 */
        transition: opacity 1s;
    }

    .defaul .loaded {
        opacity: 1;
    }

    .image-view {
        width: 100%;
        box-sizing: border-box;
        position: relative;
    }

    /* 	.uni-product-juli{
		color: #B42F2D;
		position:absolute;
		right: 10upx;
	} */
    .uni-product-title {
        position: relative;
        box-sizing: border-box;
        width: 100%;
    }

    .uni-product-price {
        text-align: left;
        width: 100%;
    }

    .uni-product-image {
        width: 100%;
        padding: 0;
        margin: 0;
    }

    .uni-product-list {
        background: #EDEDED;
        padding: 4upx;
        position: relative;
        flex-direction: row;
        display: flex;
        height: 100%;
        width: 100%;
        box-sizing: border-box;
    }

    .uni-product-item {
        /* background: #fff; */
        padding: 6upx;
        flex: 1;
        text-align: center;
        flex-direction: column;
        min-width: 40%;
        box-sizing: border-box;
    }

    .uni-product-item:nth-child(odd) {
        /* border:1px solid rgba(100,100,100,0.1); */
        border-top-width: 0;
    }

    .uni-product-item:nth-child(even) {
        /* border:1px solid rgba(100,100,100,0.1); */
        border-left-width: 0;
        border-top-width: 0;
    }

    .uni-product-item:hover {
        opacity: 0.9;
        padding: 10upx;
        background: #FF8C00;
    }
</style>
