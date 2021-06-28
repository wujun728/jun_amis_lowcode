<template>
	<view class="tui">
		<view class="uni-list" v-if="onshow">
<!-- 			<view class="uni-list-cell-divider">
				右侧带箭头
			</view> -->
			<view class="uni-list-cell uni-flex" style="justify-content: space-between;" hover-class="uni-list-cell-hover" @tap="update('headimg')">
				<view class="uni-list-cell-navigate item" style="width: 50upx;">
					头像
				</view>
				<view class="uni-list-cell-navigate uni-navigate-right flex " style="justify-content:flex-end;flex:2" >
					<image  class=" tui-headimg"  style="padding:0 20upx;" :src="userinfo.headimg" mode="aspectFit"></image>
				</view>
			</view>
			<view class="uni-list-cell flex" style="justify-content: space-between;" hover-class="uni-list-cell-hover"  @tap="update('nickName')">
				<view class="uni-list-cell-navigate item" style="width: 50upx;">
					昵称
				</view>
				<view class="uni-list-cell-navigate uni-navigate-right flex " style="justify-content:flex-end;flex:2">
					<text  style="padding:0 20upx;">{{userinfo.nickName}}</text>
				</view>
			</view>
			<view class="uni-list-cell flex" style="justify-content: space-between;" hover-class="uni-list-cell-hover" @tap="update('area')">
				<view class="uni-list-cell-navigate item" style="width: 50upx;">
					地区
				</view>
				<view class="uni-list-cell-navigate uni-navigate-right flex " style="justify-content:flex-end;flex:2">
					<text  style="padding:0 20upx;">{{userinfo.area}}</text>
				</view>
			</view>
			<view class="uni-list-cell flex" style="justify-content: space-between;" hover-class="uni-list-cell-hover" @tap="update('individuality')">
				<view class="uni-list-cell-navigate item" style="width: 50upx;">
					个性
				</view>
				<view class="uni-list-cell-navigate uni-navigate-right flex " style="justify-content:flex-end;flex:2">
					<text  style="padding:0 20upx;">{{userinfo.individuality}}</text>
				</view>
			</view>
			<view class="uni-list-cell flex" style="justify-content: space-between;" hover-class="uni-list-cell-hover" @tap="toPage('address')">
				<view class="uni-list-cell-navigate item uni-list-cell-navigate uni-navigate-right" style="width: 50upx;">
					地址信息
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import {Url,Storage} from '@/common/yc_js/';

	export default {

		data(){
			return{
				onshow:false,
// 				userinfo:{
// 					nickName:"小宝宝",
// 					headimg:"../../static/HM-PersonalCenter/face.jpeg",
// 					user_id:"123",
// 					individuality:"爱你一万年",
// 					address:"北京市西城区中南海大院1号",
// 					sex:"男",
// 					area:"北京-北京-东城区",
// 					infoUpdate:{
// 						key:null,
// 						value:null
// 					}
// 					
// 					
// 				}
			}
		},computed:{
			userinfo(){
				return this.$store.getters.userinfo;
			}
		},methods:{
			update(e){
				var str="key="+e+"&value="+this.userinfo[e];
				this.infoUpdate={key:e,value:this.userinfo[e]};
				switch (e){
					case 'headimg':
						this.toPage('updateHeadimg',str)
						break;
					default:
						this.toPage('update',str)
						break;
				}

// 				switch (e){
// 					case "nickName":
// 						break;
// 					default:
// 						break;
// 				}

				console.log(e)
			},toPage(url,e){
				uni.navigateTo({
					url:url+'?'+e
				})
			}
		},onShow(){
			this.onshow=true;
// 		var value= Storage.get('userinfoUpdate');
// 			if(value && this.infoUpdate && this.infoUpdate.key && this.infoUpdate.value!= value){
// 				this.userinfo[this.infoUpdate.key]=value;
// 				Storage.set('userinfoUpdate',false,10);
// 			}
// 			console.log('返回触发')
		}
	}
</script>

<style>
</style>
