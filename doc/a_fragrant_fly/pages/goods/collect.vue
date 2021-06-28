<template>
	<view class="tui">
		<!-- <view class="">{{title}}</view> -->
		<view class="tui-flex tui-list" v-for="(item,index) in shops" :key="index"  >
			<image class="tui-list-item tui-list-image" :src="item.image" mode="aspectFill">左边</image>
			<view class="item tui-list-item"  >
				<text class="line-clamp">{{item.describe}}</text>
				<text class="line-clamp tui-title" @tap="toPage(item)">{{item.shopName}}</text>
			</view>
			<view style="" @tap="deleteTap(item)">
			<text  class="uni-badge" style="color: #fff;background-color: #dd524d">x</text>
			</view>
		</view>
	</view>
</template>

<script>
	export default{
		data(){
			return {
				shops:[{
					shopName:"大和烧烤店",
					shop_id:"1",
					image:"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544799000&di=1610b5317bb15dbb6a36e4d57cf4eb3f&imgtype=jpg&er=1&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fc995d143ad4bd1138854651451afa40f4bfb057f.jpg",
					describe:"南极虾（学名Euphausia superba）又名大磷虾或南极大磷虾，是一种生活在南极洲水域的浮游甲壳动物。磷虾属，于南纬60℃以南的冰缘区域大量聚集。极磷虾是似虾的无脊椎动物，它们以微小的浮游植物作为食物，从中将初级生产而来的能量，转化来维持其远洋带的生命周期。它们是南极生态系统的关键物种，若以生物质能来说，它们可能是地球上最成功的动物物种 （大约共有5亿吨）。"
					
				},{
					shopName:"大和西餐",
					shop_id:"2",
					image:"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544799000&di=1610b5317bb15dbb6a36e4d57cf4eb3f&imgtype=jpg&er=1&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fc995d143ad4bd1138854651451afa40f4bfb057f.jpg",
					describe:"南极虾（学名Euphausia superba）又名大磷虾或南极大磷虾，是一种生活在南极洲水域的浮游甲壳动物。磷虾属，于南纬60℃以南的冰缘区域大量聚集。极磷虾是似虾的无脊椎动物，它们以微小的浮游植物作为食物，从中将初级生产而来的能量，转化来维持其远洋带的生命周期。它们是南极生态系统的关键物种，若以生物质能来说，它们可能是地球上最成功的动物物种 （大约共有5亿吨）。"
					
				}],
				title:"收藏也没见"
			}
		},methods:{
			toPage(e){
				uni.navigateTo({
					url:'../goods/shop?id='+e.shop_id
				})
			},
			deleteTap(item){
				console.log('删除')
				var self=this;
				uni.showModal({
					title:"您确定要删除吗?",
					content:"删除后无法恢复",
					success(e){
						if(e.confirm){
							var shops=self.shops;
							var shopNew=[];
							for(let i=0;i<shops.length;i++){
								if(item.shop_id!=shops[i].shop_id){
									shopNew.push(shops[i])
								}
							}
							self.shops=shopNew;
						}else{
							// console.log("已取消")
						}
					}
				})

			}
		}
	}
</script>

<style lang="scss">

	@mixin line-clamp($num) {
			overflow: hidden;
			text-overflow: ellipsis;
			word-break: break-all;
			display: -webkit-box;
			-webkit-line-clamp:$num;  /*限制在一个块元素显示的文本的行数*/
			-webkit-box-orient: vertical;
}

	.tui{
		&-title{
			font-size: 1.2em;
			font-weight:bold;
		}
		&-text{
			
		}
		.line-clamp{
			@include line-clamp(2);
// 			overflow: hidden;
// 			text-overflow: ellipsis;
// 			word-break: break-all;
// 			display: -webkit-box;
// 			-webkit-line-clamp: 2;  /*限制在一个块元素显示的文本的行数*/
// 			-webkit-box-orient: vertical;
		}
		&-flex{
			display: flex;
			.item{
				flex:1;
			}
			.column{
				flex-direction: column;
			}
			.row{
				flex-direction: row;
			}
			.center{
				justify-content: center
			}
		}
		
		&-list{
			background: #fff;
			border-bottom:1upx solid #e3e3e3;
			padding: 10upx;
			&-image{
				width:130upx;
				height: 130upx;
			}
			&-item:nth-of-type(1) {
				
			};
			&-item:last-child{
				
			};
			&-item{
				padding: 10upx;
			}
		}
	}
	
</style>
