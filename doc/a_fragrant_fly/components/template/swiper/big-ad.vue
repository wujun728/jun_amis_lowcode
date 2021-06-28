<template>
		<!-- <page-head :title="title"></page-head> -->

			<view class="page-section" v-if="bigAd.length"> 

					<view class="swiper-item-badge" >
						<view  :style="index==idx?'background:#fff':''" @tap="index=idx" v-for="(item,idx) in bigAd" :key="idx" >
							{{idx+1}} 
						</view>
						
					</view>

					<swiper class="swiper"  :indicator-dots="indicatorDots" :current="index"  @change="change" :autoplay="autoplay" :interval="interval" :duration="duration">

						<swiper-item v-for="(item,idx2) in bigAd" :key="idx2" class="swiper-item ">

								<view class="swiper-item-title">{{item.title}}</view>
								<image :src="item.image" mode="aspectFill"></image>
	
						</swiper-item>
					</swiper>

			</view>

</template>
<script>
	export default {
		props:['bigAd','autoplay'],
		data() {
			return {
				nb:'',
				// items:[],
				index:0,//当前显示索引
				indicatorDots: false,//显示指示点
				// autoplay: true,//自动轮播
				interval: 2000,//每次轮播间隔时间
				duration: 1000,//轮播进行速度
			}
		},
		mounted() {

		},
		methods: {
			change(e){
				// 监听轮播变化
				// console.log(e)
				this.index=e.detail.current;
			},

			setIndicatorDots(e) {
				// 开启/关闭指示点
				this.indicatorDots = !this.indicatorDots
			},
			setAutoplay(e) {
				// 开启/关闭轮播自动
				this.autoplay = !this.autoplay
			},
			setInterval(num) {
				// 设置轮播间隔时间
				this.interval = num;
			},
			setDuration(num) {
				// 设置轮播进行速度
				this.duration = num
			}
		}
	}
</script>

<style>
	.page-section{
		height: 100%;
		/* 调用页面外层div要设置高度 */
		/* height: 500upx; */
		box-sizing: border-box;
		width: 100%;
		min-height: 50upx;
		text-align: center;
		position:relative;
	}
.swiper{
	height: 100%;
	width: 100%;

}
/* 	.swiper-item {
		text-align: center;
	} */
	.swiper-item-title{
		opacity:0.5;
		background: #2F2F2F;
		line-height: 1em;
		box-sizing: border-box;
		width: 100%;
		padding: 0.5em;
		bottom: 0;
		position:absolute;
		overflow: hidden;/*超出部分隐藏*/
		white-space: nowrap;/*不换行*/
        text-overflow:ellipsis;/*超出部分文字以...显示*/
	}
	.swiper-item-badge{
		top: 0.2em;
		display: flex;
		right: 1em;
		z-index: 1;
		position:absolute;
	}
	.swiper-item-badge view{
		line-height: 1em;
		margin: 0 0.1em;
		/* text-align: center; */
		padding:0.2em;
		opacity:0.5;
		background: #000;
	}
	.swiper-item image{
		min-height: 100%;
		min-width: 100%;
	}
		
</style>
