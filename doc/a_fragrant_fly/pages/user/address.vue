<template>
	<view class="page-body tui">
		<view style="flex: 1;">
				<view class="tui-flex tui-column uni-card " style="height: 130upx;" v-for="(item,index) in addressInfo" :key="index">
					<view  class="tui-flex tui-padding ">
						<view class="tui-item ">
							<label class="">姓名</label>
						{{item.name}}</view>
						<view class="tui-item">
						<label class="">电话</label>
						{{item.phone}}</view>
						<text v-if="item.selected" class="iconfont" style="color: #32cd32;" :data-index="index"   @tap="selectList(index)">&#xe99c;</text>
						<text v-else class="iconfont" :data-index="index" @tap="selectList(index)">&#xe9ae;</text>
					</view>
					<view class="tui-flex tui-padding tui-border">
						<label class="">地址</label>
						<view class="tui-item">{{item.address}}</view>
							
						<text class="iconfont" style="font-size:0.9em" @tap="delet(index)"  >✕</text>
					</view>
					
				</view>	
		</view>

		<view class="" style="position: absolute;display: flex;justify-content: center;width: 100%;height: 100%;" v-if="hasAddress">
					<view class="" style="opacity: 0.5;width: 100%;background: #000000;height: 100%;position: absolute;z-index: 1;">
						
					</view>
					<view style="opacity: 1;position: absolute;z-index: 2; width: 100%;height:;background: #FFFFFF;margin-top: 20%;"  class="tui-flex tui-column">
							<view class="tui-center tui-padding">输入新地址 
								<text class="iconfont " style="position: absolute;right: 10upx;" @tap="hasAddress=!hasAddress" >✕</text>
							</view>
							<view class="tui-flex tui-column uni-card">
								<view class="tui-flex tui-padding ">
									<view class="tui-flex ">
									<label class="">姓名</label>
									<input class="tui-input tui-border" type="text"  v-model="name" />
									</view>
									<view class="tui-flex">
										<label class="">电话</label>
										<input class="tui-input tui-border" type="text"   v-model="phone"  />
									</view>
								</view>
								<view class="tui-flex tui-border tui-padding">
									<label class="">地址</label>
									<input class="tui-input tui-border" type="text"  v-model="address"  />
								</view>
							</view>
							<view class="tui-item">
								<button class="" @tap="add()" type="primary">确认添加</button>
							</view>
					</view>
		</view>

	<view v-if="!hasAddress" style="height: 200upx;">
		<button    @tap="hasAddress=!hasAddress" >
			<text>添加新地址</text>
		</button>
		<button   @tap="commit" type="primary">
			<text>确认</text>
		</button>
	</view>

</view>
</template>

<script>

	import {Storage,Validate} from '@/common/yc_js/';
	export default {
		data(){
			return {
				name:'',
				phone:'',
				detail:'',
				addressInfo:[],
				hasAddress:false,
			}
		},computed:{
// 			address(){
// 				return this.$store.getters.login;
// 			}
		},onLoad() {
			var addressInfo=[{
				name:"李先生",
				phone:"18888888888",
				address:"王府井边中南胡同1号院1101号"
			},{
				name:"李先生",
				phone:"16668888888",
				address:"西单胡同1号院101号"
			},{
				name:"李先生",
				phone:"19988886999",
				address:"中南海1号院1号"
			}];
			this.addressInfo=addressInfo;
			var data=Storage.get('addressInfo');
			if(typeof data==='object' && Array.isArray(data)){
				var addr=[];
				for(let i=0;i<data.length;i++){
					if(typeof data[i]==='object'){
						addr.push(data[i])	
					}
				}
				this.addressInfo=addr;
			}else if(data){
				Storage.remove('addressInfo');
			}
			// this.hasAddress=true;
		},
		methods:{
			delet(index){
				// this.address[index];
				this.addressInfo.splice(index,1); 
			},
			commit(){
				// console.log(this.address)
				Storage.set('addressInfo',this.addressInfo,300);
				var data=Storage.get('addressInfo')
				uni.showToast({
					title:"提交成功"
				})
				// console.log(data)
			},
			selectList(key){
				var addressInfo=this.addressInfo;
				for(let i=0;i<addressInfo.length;i++){
					if(i===key){
						addressInfo[i].selected=true;
					}else{
						addressInfo[i].selected=false;
					}
				}
				this.$store.commit('setAddress',addressInfo[key]);
				this.$set(this.addressInfo, key,addressInfo[key]);
			},
			add(){
				var addressInfo=this.addressInfo;
				var length=addressInfo.length;
				if(length>4){
					uni.showModal({
						title:"失败提示",
						content:"最多只能保存5个地址信息，请删除多余信息后提交"
					})
					return ;
				}
				var rule=[{
							name:"phone",
							errorMsg:"手机号输入不正确",
							checkType:"phoneno",
							},{
							name:"name",
							errorMsg:"收件人不能为空",
							checkType:"notnull",
							// checkRule:"1,8"
							},{
							name:"address",
							errorMsg:"地址不能为空",
							checkType:"notnull",
							checkRule:"6,20"
							},
						];
			var data={
						name:this.name,
						phone:this.phone,
						address:this.address
					}
			var Vali=Validate.check(data,rule);
			// console.log(Validate)
				if(!Vali){
					uni.showModal({
						title:"提示",
						content:Validate.error,
						showCancel:false
					})
					console.log(Validate)
				}else{
					this.addressInfo=this.addressInfo.concat([{
						name:this.name,
						phone:this.phone,
						address:this.address
					}])
					this.hasAddress=false;
					
					this.selectList(length);
					this.commit();
					uni.showToast({
						title:"提交成功"
					})
				}
			console.log(this.address)
			}
		}
	}
</script>

<style>
.tui{
	height:100%;
	/* position: relative; */
	overflow-x:scroll;
}
view{
	/* line-height: 3em; */
}
.uni-card .uni-list{
	box-sizing: border-box;
	/* margin:10upx 0; */
	padding: 0 15upx;
}
</style>
