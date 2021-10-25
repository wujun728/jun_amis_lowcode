import appConfig from "@/https/appConfig.js"
export function uniRequest(networkUrl, methodsWay, dataCont, util) {
	// 默认为开启错误提示
	if (util == undefined) {
		util = {
			showError: true, //开启错误提示
		}
	}
	return new Promise((resolve, reject) => {
		uni.request({
			url: appConfig.baseUrl + networkUrl, //由基础路径和接口地址
			method: methodsWay || "GET", //请求的方式必须大写
			data: dataCont || {}, //参数
			header: {
				// 这里等会会配置下还有token
				'Content-Type': 'application/json',
				'Access-Control-Allow-Origin': '*'
				//'Authorization':"携带的token"
			},
			// 成功使用resolve
			success: (res) => {
				if (res.data && res.statusCode == 200) {
					resolve(res)
				} else {
					/**
					 * 请求失败，是否要提示出来;
					 * showError: true,开启错误提示
					 *  showError: false,关闭错误提示
					 * */
					if (util.showError) {
						showError(res);
					}
					resolve(res)
				}
			},
			//失败调用reject，
			fail: (err) => {
				// 失败做处理
				var str = err.errMsg.toString();
				if(str.indexOf("request:fail")!=-1){
					err.errMsg = "服务器地址错误，请检查!";
					reject(err);
					return;
				}else{
					showError(err);
				}
				reject(err)
			}
		});
	})
}

// 错误处理
const showError = (error) => {
	if (error.data.code != 'undefined') {
		switch (error.data.code) {
			case 401:
				uni.showToast({
					title: '没有权限',
					icon: 'none',
					duration: 1000
				});
				break;

			case 403:
				uni.showToast({
					title: '拒绝访问',
					icon: 'none',
					duration: 1000
				});
				break;

			case 404:
				uni.showToast({
					title: '很抱歉，资源未找到!',
					icon: 'none',
					duration: 1000
				});
				break;

			case 500:
				uni.showToast({
					title: '没有权限',
					icon: 'none',
					duration: 1000
				});
				break;

			case 502:
				uni.showToast({
					title: '服务器异常',
					icon: 'none',
					duration: 1000
				});
				break;

			case 504:
				uni.showToast({
					title: '网络超时',
					icon: 'none',
					duration: 1000
				});
				break;

			default:
				uni.showToast({
					title: "网络错误",
					icon: 'none',
					duration: 1000
				});
				break
		}
	}
}
