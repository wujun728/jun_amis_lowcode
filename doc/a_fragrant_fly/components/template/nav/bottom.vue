<template>
	<view class="uni-flex tui">
		<view  class="uni-flex-item uni-flex uni-column tui-center" v-for="(item,idx) in bottomNav" :key="idx" @tap="index=idx,(item.fn)(item)"  :class="index==idx?'uni-badge-primary uni-badge-inverted':''">
		
			<!-- <view class="uni-item uni-column"> -->
				<view class="nav-icon uni-item">
					<view class="tui-nav-badge" style="">
						<view class="uni-badge uni-badge-danger" style="font-size: 0.7em;position: absolute;" v-if="item.msg">
							<text v-if="item.msg>10">…</text>
							<text v-else>{{item.msg}}</text>
						</view>
					</view>
					<text class="uni-item iconfont" style="font-size:1.6em;line-height: 1.3em;" v-html="item.ico"></text>
				</view>
				<view class="uni-item nav-title">
						{{item.name}}
				</view>
			<!-- </view> -->
			
		
		</view>
	</view>
<!-- 	<nav  class="tui uni-flex tui-center tui-bottom-nav ">


	</nav> -->
</template>

<script>
	export default {
		name: "bottomNav",
		data() {
			return {
				index:0,
				bottomNav:[
					// 底部按钮数据
					{
						fn:this.tijiao,
						msg:0,
						name:'首页',
						ico:'&#xe98e;',
						url:'/pages/home/index'
					},{
						fn:this.tijiao,
						msg:1,
						name:'订单',
						ico:'&#xe8cd;',
						url:'/pages/order/list'
					},
					{
						fn:this.tijiao,
						msg:23,
						name:'消息',
						ico:'&#xe872;',
						url:'/pages/chat/list'
					},
					{
						fn:this.tijiao,
						msg:0,
						name:'我的',
						ico:'&#xe8ea;',
						url:'/pages/user/wode'
					}
				]
				
			}
		},mounted(){
			// console.log("template/nav/bottom/mounted")
		
		},methods:{
			tijiao(item){
				console.log('tijiao')
				if(item.name=="首页"){
					uni.redirectTo({
						url: item.url
					});
				}else{
				// console.log(item)
					uni.navigateTo({
						url: item.url
					});
				}
			}
		}
	}
</script>

<style>

</style>
