export default{
		state: {
			hasLogin: false,//登陆状态
			loginProvider: "",//登陆方式 如 微信
			openid: null, //应用id
			address:{},//收货地址
			userinfo:{
				nickName:"未登录",
				headimg:"../../static/image/logo.png",
				user_id:"123",
				individuality:"爱你一万年",
				address:"北京市西城区中南海大院1号",
				sex:"男",
				area:"北京-北京-东城区"
			}//用户信息
		},
		getters:{
			userinfo(state){
				return state.userinfo;
			},login(state){
				return  state.hasLogin;
	        },address(state){
				return state.address;
			}
		},
		mutations: {
			login(state, provider) {
				state.hasLogin = true;
				state.loginProvider = provider;
			},logout(state) {
				state.hasLogin = false
				state.openid = null
			},setOpenid(state, openid) {
				state.openid = openid
			},setAddress(state,address){
				state.address=address;
			},setUserinfo(state,userinfo){
				state.userinfo=userinfo;
			}
		},
		actions: {
	// 		// lazy loading openid
	// 		getUserOpenId: async function ({
	// 			commit,
	// 			state
	// 		}) {
	// 			return await new Promise((resolve, reject) => {
	// 				if (state.openid) {
	// 					resolve(state.openid)
	// 				} else {
	// 					uni.login({
	// 						success: (data) => {
	// 							commit('login')
	// 							setTimeout(function () { //模拟异步请求服务器获取 openid
	// 								const openid = '123456789'
	// 								console.log('uni.request mock openid[' + openid + ']');
	// 								commit('setOpenid', openid)
	// 								resolve(openid)
	// 							}, 1000)
	// 						},
	// 						fail: (err) => {
	// 							console.log('uni.login 接口调用失败，将无法正常使用开放接口等服务', err)
	// 							reject(err)
	// 						}
	// 					})
	// 				}
	// 			})
	// 		},
			isLogin:async function(context){
				return await new Promise((resolve, reject) => {
			var hasLogin=context.state.hasLogin;
			console.log(context)
			if(!hasLogin){
				uni.showModal({
					title:"您还未登陆,立即登陆?",
					content:"请登陆后进行访问",
					success(e){
						if(e.confirm){
							//登陆
							uni.navigateTo({
								url:'../login/login'
							})	
						}else{
							context.commit('logout',"退出")
							console.log(context.state)
							console.log("放弃登陆")
						}
					}
				})
				resolve(false)
			}else{
				resolve(true)
			}})
		
		}}
}