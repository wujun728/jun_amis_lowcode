<template>
    <view class="page-body">
		
        <view class="uni-input-group"> 
			<view class="uni-input-row" v-if="failAccount[account]">
				<view class="uni-label " style="flex: 1;" >
					<image class="" style="height: 100%;width: 100%;" :src="captchaUrl" mode="aspectFill" @tap="resetCaptcha"></image> 
				</view>
				<input class="uni-flex-item" type="text" v-model="inputCaptcha" placeholder="请输入图片验证码"></input>
			</view>
            <view class="uni-input-row">
                <input type="text" v-model="account" placeholder="请输入密保手机或邮箱">
				<view class="uni-label" >
					<view v-if="Countdown" id='msg-type' @tap="sendMessge">获取验证码</view>
					<view v-else id='msg-type' >{{CountdownBt}}秒</view>
				</view>
            </view>
			<view class="uni-input-row">
				<view class="uni-label" >
					验证码
				</view>
				<input type="text" v-model="code" placeholder="输入密保手机或邮箱收到的验证码">
			</view>
			<view class="uni-input-row ">
				<view class="uni-label" >
					<view  class="">新密码</view>
				</view>
				<input type="text" v-model="password" placeholder="请输入新密码">
			</view>
			<view class="uni-input-row">
				<view class="uni-label border" >
					<view class=""  >新密码</view>
				</view>
				<input type="text" v-model="password2" placeholder="请再次输入新密码">
			</view>
			<!-- <button class=""  @tap="getpage()">发送验证</button> -->
			<view class="btn-row">
				<button type="primary" class="primary" @tap="submit">提交</button>
			</view>
        </view>
<!--        <view class="btn-row">
            <button type="primary" class="primary" @tap="findPassword">提交</button>
        </view> -->
    </view>
</template>

<script>
	import captchaInput from '../../components/template/verify/captchaInput.vue';
	import graceChecker from '../../common/graceChecker.js';
	import {Md5}  from '@/common/yc_js/';
    export default {
        data() {
            return {
				captchaUrl:'',//图片验证码地址
				inputCaptcha:0,//图片验证码输入值
				CountdownBt:60,//短信验证码倒计时
				Countdown:true,
                account: '18888888889',
				password:'',
				password2:'',
				failAccount:{},//登陆失败的账号 再次登陆需要验证码
				successAccount:{},//登陆成功的账号组 再次登陆无需验证码
				code:''
            }
        },
        methods: {
            submit() {
	
						var that=this;
						var code=that.account+that.code;
// 						console.log(code);
// 						console.log(Md5.Md5(code))
// 
// 						console.log(that.password)
// 						console.log(graceChecker.checkName(that.password))
						
						var checkName=graceChecker.checkName(that.password);
						if(that.code.length<4){
							uni.showModal({
								showCancel:false,
								title: '失败提示',
								content: '验证码位数不正确。'
							});
							// this.$refs.captchaInput.resetCode();//刷新验证码
							return;
						}
						if(!checkName.ok){
							uni.showModal({
								showCancel:false,
								title: '失败提示',
								content: checkName.message
							});
							return;
						}
						if(that.password!=that.password2){
							uni.showModal({
								showCancel:false,
								title: '失败提示',
								content:'两次输入的密码不一致。',
							});
							return;
						}
						var client= {"client":that.Config.client()};
						client.account=that.account;
						client.code=Md5.Md5(code);
						client.password=Md5.Md5(that.password);
						console.log(client.client);
						// console.log(Base64.encoder(client.client));
						uni.request({
							url: that.$config.service.url+'/user/access/reset/psd',
							method: 'PUT',
							data: client,
							success: res => {
								console.log(res.data);
								try{
									// console.log(res.data.code+res.statusCode)
									if(res.data.code===200){
										uni.showToast({
											icon: 'none',
											title: res.data.message,
											duration: 3000
										});
// 										uni.redirectTo({
// 											url: '/pages/login/login'
// 										});
										uni.navigateBack({
											delta: 1
										});
										return;
									}else{
										uni.showModal({
											showCancel:false,
											title: '失败提示',
											content:res.data.message,
										});
									}
									// this.$refs.captchaInput.resetCode();//刷新验证码
									}catch(e){
										uni.showModal({
											showCancel:false,
											title: '失败提示',
											content:'服务器错误'+e,
										});
										console.log(e);
									}
									},
						})

            },
			getpage(){
				// console.log('子主键'+this.$refs.captchaInput.resetCode()+this.$refs.captchaInput.inputCaptcha);
			},
			sendMessge(){
					var that=this;
					var inputCaptcha=this.inputCaptcha;
					if(inputCaptcha.length<4){
						uni.showModal({
							showCancel:false,
							title: '失败提示',
							content: '图片验证码至少4位'
						});
						return ;
					}
					var client= {};
					// console.log(client.client);
					// console.log(Base64.encoder(client.client));
					
					client.account=that.account;
					client.captcha=inputCaptcha;
					
					let rule=(tp) => {
					　　return [{checkType:tp,name:'account',errorMsg:'错误信息'}];
					};

					let data={account:that.account};
					let dt={type:'',msg:''};
					// console.log(graceChecker.check(data,rule('email')))
					if(graceChecker.check(data,rule('email'))){
						// type='email';
						dt={type:'email',msg:'邮箱'};
					}
					if(graceChecker.check(data,rule('phoneno'))){
						// type='phone';
						dt={type:'phone',msg:'手机'};
					}
					if(dt.type!='email' && dt.type!='phone'){
						uni.showModal({
							showCancel:false,
							title: '失败提示',
							content: '输入有误,请输入手机号或者邮箱',
						});
						
						return ;
					}

					uni.request({
						url: that.$config.server.host+'/code/sendCode',
						method: 'GET',
						data: client,
						success: res => {
							console.log(res.header);
							try{
								console.log(res.data.code+res.statusCode)
								if(res.data.code===200){
									uni.showToast({
										icon: 'none',
										title: res.data.message+',手机验证码:'+res.data.code,
										duration: 3000
									});
									// 倒计时秒
								function settime(num) {
									var num=num||60;
									if (num > 1) {
										that.Countdown=false;
											num--;
											that.CountdownBt=num;
											setTimeout(function() {
												settime(num)
											},1000)
									} else {
										that.Countdown=true;
									}
								}
								settime(90);
								}else{
									uni.showModal({
										showCancel:false,
										title: '失败提示',
										content: res.data.message
									});
									this.$refs.captchaInput.resetCode();//刷新验证码	
								}
							}catch(e){
								uni.showModal({
									showCancel:false,
									title: '失败提示',
									content: '服务器错误/未知错误',
								});
								
								//TODO handle the exception
							}

							// console.log(res);
							// console.log('发送验证码'+that.$refs.captchaInput.inputCaptcha);
						},
						fail: () => {
							uni.showModal({
								showCancel:false,
								title: '失败提示',
								content: '服务器连接失败fail,请重新进行操作'
							});
						},
						complete: () => {
							// 请求结束执行
						}
					});
					
				}
        },components: { 
			captchaInput
		}
    }
</script>

<style>
	/* @import "../../common/icon.css"; */

	.input-row input{
		/* margin-left:20upx ; */
		/* width: 485upx; */
	}
</style>
