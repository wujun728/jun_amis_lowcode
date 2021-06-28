<template>
    <view class="page-body tui ">
				<view class="uni-input-group">
					<view class="uni-input-row" v-if="failAccount[account]">
						<view class="uni-label " style="flex: 1;" >
							<image class="" style="height: 100%;width: 100%;" :src="captchaUrl" mode="aspectFill" @tap="resetCaptcha"></image> 
						</view>
						<input class="uni-flex-item" type="text" v-model="inputCaptcha" placeholder="请输入图片验证码"></input>
					</view>
					
					<view class="uni-input-row tui-flex" >
						
						<view v-if="sloginType=='psd'" class="uni-label">
							账号
						</view>
						<input type="text" class="uni-flex-item " v-model="account" placeholder="请输入密保手机或邮箱">
						<view v-if="sloginType=='code'" class="uni-label" >
							<view v-if="Countdown" id='msg-type' @tap="sendMessge">获取验证码</view>
							<view v-else id='msg-type' >{{CountdownBt}}秒</view>
						</view>
					</view>
					
					<view v-if="sloginType=='psd'" class="uni-input-row uni-flex ">
						
						<view class="uni-label" >
							<view class="" >密码</view>
						</view>
						<input type="text" class="uni-flex-item" password="true" v-model="password" placeholder="请输入密码">
					</view>
					
					<view v-if="sloginType=='code'" class="uni-input-row  uni-flex">
						<view class="uni-label" >
							<view class="" >验证码</view>
						</view>
						<input type="text"  class="uni-flex-item" v-model="code" placeholder="请输验证码">
					</view>

					<view class="action-row " style="height: 80upx;line-height: 80upx;">
						<navigator type="primary" @tap="loginType(sloginType)">【切换登陆方式】{{jloginType[sloginType]}}</navigator>
					</view>

				</view>
				<!-- <button type="primary"  size="default" style="width: 100%;" @tap="bindLogin">登录</button> -->
				<button type="primary"  size="default" style="width: 100%;" @tap="login">登录</button>
				<!-- <button type="primary"  size="default" style="width: 100%;" @tap="logout">退出</button> -->
				<view class="action-row" style="margin-top:100upx;position:;">
					<navigator url="./reg">注册账号</navigator>
					<text></text>
					<navigator url="./pwd">忘记密码</navigator>
					<navigator  @tap="login1('access')">access</navigator>
					<navigator @tap="login1('token')" >token</navigator>
				</view>
				<view class="oauth-row" v-if="hasProvider" v-bind:style="{top: positionTop + 'px'}">
					<view class="oauth-image" v-for="provider in providerList" :key="provider.value">
						<image :src="provider.image" @tap="oauth(provider.value)"></image>
					</view>
				</view>

    </view>
</template>

<script>

	// import captchaInput from '../../components/template/verify/captchaInput.vue';
	import graceChecker from '../../common/graceChecker.js';
	import {Md5}  from '@/common/yc_js/'; 
    import {
        mapState,
        mapMutations
    } from 'vuex'

    export default {
        data() {
            return {
				captchaUrl:'',//图片验证码地址
				inputCaptcha:0,//图片验证码输入值
				jloginType:{
					psd:'账号+密码登陆',
					code:'免密码登陆',
					},
				sloginType:'psd',//当前在使用的登陆方法
				Countdown:true,//获取验证码状态开启
				CountdownBt:0,//验证码有效期
                providerList: [],//第三方快捷登陆方法
                hasProvider: false,//是否开启第三方登陆
                account: '18888888888',
                password: '18888888888',
				failAccount:{},//登陆失败的账号 再次登陆需要验证码
				successAccount:{},//登陆成功的账号组 再次登陆无需验证码
				code:'',//手机验证码
                positionTop: 0
            }
        },
        computed: mapState(['forcedLogin']),
        methods: {
			resetCaptcha(){
				this.captchaUrl=this.$config.server.host+'code/captcha/'+this.account+'?time='+new Date().getTime();
			},
			...mapMutations(['login','logout']),
			login1(type){
				this.Config.refresh(type);
			},
			loginType(){
				switch (this.sloginType){
					case 'psd':
					this.sloginType='code';
						break;
					default:
					this.sloginType='psd';
						break;
				}

// 				uni.showToast({
// 					title: '挺好挺好'+this.sloginType,
// 					mask: true,
// 					duration: 1500
// 				});
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
					　　return [{checkType:tp,name:'account',errorMsg:'这个错拉'}];
					};

					let data={account:that.account};
					let dt={type:'',msg:''};
					console.log(graceChecker.check(data,rule('email')))
					if(graceChecker.check(data,rule('email'))){
						// type='email';
						dt={type:'email',msg:'邮箱'};
					}
					if(graceChecker.check(data,rule('phoneno'))){
						// type='phone';
						dt={type:'phone',msg:'手机'};
					}
					if(dt.type!='email' && dt.type!='phone'){
						uni.showToast({
							icon: 'none',
							title: '输入有误,请输入手机号或者邮箱',
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
									content: '服务器错误'
								});
								//TODO handle the exception
							}
							},fail: () => {
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
					// console.log(res);
					// console.log('发送验证码'+that.$refs.captchaInput.inputCaptcha);
				},
            initProvider() {
                const filters = ['weixin', 'qq', 'sinaweibo'];
                uni.getProvider({
                    service: 'oauth',
                    success: (res) => {
                        if (res.provider && res.provider.length) {
                            for (let i = 0; i < res.provider.length; i++) {
                                if (~filters.indexOf(res.provider[i])) {
                                    this.providerList.push({
                                        value: res.provider[i],
                                        image: '../../static/img/' + res.provider[i] + '.png'
                                    });
                                }
                            }
                            this.hasProvider = true;
                        }
                    },
                    fail: (err) => {
                        console.error('获取服务供应商失败：' + JSON.stringify(err));
                    }
                });
            },
            initPosition() {
                /**
                 * 使用 absolute 定位，并且设置 bottom 值进行定位。软键盘弹出时，底部会因为窗口变化而被顶上来。
                 * 反向使用 top 进行定位，可以避免此问题。
                 */
                this.positionTop = uni.getSystemInfoSync().windowHeight - 100;
            },
            bindLogin() {
				var that=this;
				var captchaInput=this.inputCaptcha;//图片验证码

				// console.log(captchaInput)
                /**
                 * 客户端对账号信息进行一些必要的校验。
                 * 实际开发中，根据业务需要进行处理，这里仅做示例。
                 */
                if (this.account.length < 5) {
                    uni.showModal({
                    	showCancel:false,
                    	title: '失败提示',
                    	content: '账号最短为 5 个字符'
                    });
                    return;
                }
                if (this.password.length < 6) {
					uni.showModal({
						showCancel:false,
						title: '失败提示',
						content: '密码最短为 8 个字符'
					});
                    return;
                }
                /**
                 * 登陆服务器
                 */

				
				// http://www.api.com/user/account/login/psd?
				// var client= {"client":that.$config.client()};
				var client={};
					client.account=this.account;
					// 判断登陆方法
					switch (that.sloginType){
						case 'code'://验证码登陆
								
								if(that.code.length<4){
									uni.showModal({
										showCancel:false,
										title: '失败提示',
										content: '验证码至少4位'
									});
								return ;
								}
								// var code=that.account+that.code;
								client.code=Md5.Md5(that.account+that.code);
							break;
						default:
								if(captchaInput.length<4){
									uni.showModal({
										showCancel:false,
										title: '失败提示',
										content: '验证码至少4位'
									});
									return ;
								}
								//密码登陆
								client.captcha=captchaInput;
								client.password=Md5.Md5(this.password);
							break;
					}

					
					// console.log(client);
					// console.log(Base64.encoder(client.client));
					uni.request({
						url: that.$config.service.host+'/user/access/login/'+that.sloginType,
						method: 'GET',
						data: client,
						success: res => {
							console.log(res.data.data);
							try{
								if(res.data.code===200){
									uni.showToast({
										// icon:'none',
										title: res.data.message,
										duration: 3000
									});
									// that.$config.setToken(res.data.data);
// 										uni.redirectTo({
// 											url: '/pages/login/login'
// 										});
									uni.navigateBack({
										delta: 1
									});
									return;
								}else{
									// this.$refs.captchaInput.resetCode();//刷新验证码
									uni.showModal({
										showCancel:false,
										title: '失败提示',
										content: res.data.message
									});
								}
								}catch(e){
									console.log(e);
								}
						},
					})
//                 const validUser = service.getUsers().some(function (user) {
//                     return data.account === user.account && data.password === user.password;
//                 });
//                 if (validUser) {
//                     this.toMain(this.account);
//                 } else {
//                     uni.showToast({
//                         icon: 'none',
//                         title: '用户账号或密码不正确',
//                     });
//                 }
            },
            oauth(value) {
                uni.login({
                    provider: value,
                    success: (res) => {
                        uni.getUserInfo({
                            provider: value,
                            success: (infoRes) => {
                                /**
                                 * 实际开发中，获取用户信息后，需要将信息上报至服务端。
                                 * 服务端可以用 userInfo.openId 作为用户的唯一标识新增或绑定用户信息。
                                 */
                                this.toMain(infoRes.userInfo.nickName);
                            }
                        });
                    },
                    fail: (err) => {
                        console.error('授权登录失败：' + JSON.stringify(err));
                    }
                });
            },
            toMain(userName) {
                this.login(userName);
                /**
                 * 强制登录时使用reLaunch方式跳转过来
				 * 返回首页也使用reLaunch方式
                 */
                if (this.forcedLogin) {
                    uni.reLaunch({
                        url: '../main/main',
                    });
                } else {
                    uni.navigateBack();
                }

            }
        },
        onLoad() {
            this.initPosition();
            this.initProvider();
			// this.account='手机/邮箱/用户名';
        },components: { 
			// captchaInput
		}
    }
</script>

<style>
.page-body{
	justify-content: flex-start;
}



	.action-row {
        display: flex;
        flex-direction: row;
        justify-content: center;
    }

    .action-row navigator {
		justify-content: center;
		align-items: center;
        color: #007aff;
        padding: 0 20upx;
    }

    .oauth-row {
        display: flex;
        flex-direction: row;
        justify-content: center;
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
    }

    .oauth-image {
        width: 100upx;
        height: 100upx;
        border: 1upx solid #dddddd;
        border-radius: 100upx;
        margin: 0 40upx;
        background-color: #ffffff;
    }

    .oauth-image image {
        width: 60upx;
        height: 60upx;
        margin: 20upx;
    }
</style>
