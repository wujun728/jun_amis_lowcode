// 模拟数据请求部分 ————————————————————————————————————————————————————————————————————
import ceshiData from './data/index.js';//测试数据代替远程服务器的请求返回数据
var access_token= function(){
	var token={
        token:'xfdsgsdafgatoken',
		userId:'1234',
		time:Date.now()
		}
        return token.token+'.'+token.userId+'.'+token.time;
	
}
//实际项目使用中请删除这个函数
var ceshifun=(Obj)=>{
	console.log(Obj)
	if(ceshiData[Obj.url]){
        console.log('模拟数据请求成功')
            // console.log(ceshiData[Obj.url])
		if(Obj.success && typeof Obj.success=='function'){
		Obj.success({header:'',data:{code:200,message:'ok',data:ceshiData[Obj.url]}});	
		}else{
			console.log('您未设置成功回调函数success')
			if(Obj.fail && typeof Obj.fail=='function'){
				Obj.fail('请求失败')
			}
			
		}
		if(Obj.complete && typeof Obj.complete=='function'){
			Obj.complete('请求结束')
		}
		return true;
	}
	// console.log('测试请求函数，位置request/ajax.js-11行ceshifun(),正式使用请删除')

	return false;
}
// 模拟数据请求部分完————————————————————————————————————————————————————————————————————



/**
 * 向服务器发起异步请求
 * Obj 包含如下参数：
 * url			string 		请求地址
 * method 		string 		请求类型大写，如：'GET'|'POST'|'PUT'等
 * data			Object		发给服务器的请求数据
 * header		object 		请求头
 * dataType		string		如果为json返回的数据会尝试做一次 JSON.parse
 * responseType string		响应返回的数据类型 'json'|'text'
 * success 		function	请求成功的返回内容
 * fail			function	请求接口失败返回内容
 * complete		function	请求结束的返回内容无论成功失败均会执行
 */
function request(Obj){
	if(ceshifun(Obj)){
		console.log('request/ajax/request() 测试模拟数据，实际项目使用时请删除')
		// console.log(ceshifun(Obj))
		return ;
	};
	//模拟请求函数，实际项目使用中请删除这行代码，和这个函数 ——————————————————————————————————————————————————
// 	console.log(Obj)
	var auth=Obj.auth || 0;
	if(typeof auth=== 'object'){
		Obj.header= configure;
	}
	var method=Obj.method || 'GET';//请求方式
	var url =Obj.url || host; //仅为示例，并非真实接口地址。
	var data= Obj.data || {};
	var header=Obj.header || {'custom-header': 'hello'};//自定义请求头信息
	var success =Obj.success ||  function(res){//请求成功返回值
			// console.log(res.data);
			console.log('request/ajax.js 请求成功返回')
		};
	var dataType =Obj.dataType || 'json';//如果设为 json，会尝试对返回的数据做一次 JSON.parse
	var responseType =Obj.responseType || 'text';//相应数据类型，合法值：text、arraybuffer
	var fail= Obj.fail || function(e){//请求接口失败返回值
		console.log(e)
	}
	var complete=Obj.complete || function(e){//请求结束返回，无论成功失败都返回
	uni.hideLoading();//关闭等等框
		console.log(e)
	}
    if(data.access_token){
        data.access_token=access_token;
    }
	uni.request({
		method:method,
		url: url, //仅为示例，并非真实接口地址。
		data:data,
		header:header,
		success:success,
		dataType:dataType
	})
}
/**
 * 修正请求方法
 */
function xiuzhengData(url,data,success){
	var Rd={};//
	if(url.url){
		Rd=url;
	}else{
		Rd.url=url;
	}
	if(typeof data  === "function"){
		Rd.success=data;
	}
	if(typeof success=== "function"){
		Rd.success=success;
		Rd.data=data;
	}
	return Rd;
}
/** 
 * get		向服务器发送POST请求
 * url 		多功能			str (http请求地址) || Obj (参考uni.request()方法的请求方法集合)
 * data 	多功能			请求参数	|| 请求成功的返回函数
 * success   				请求成功的返回函数
 */
function post(url,data,success){
var Rd=xiuzhengData(url,data,success)
	Rd.method='POST';
	request(Rd)
}
/** 
 * get		向服务器发送GET请求
 * url 		多功能			str (http请求地址) || Obj (参考uni.request()方法的请求方法集合)
 * data 	多功能			请求参数	|| 请求成功的返回函数
 * success   				请求成功的返回函数
 */
function get(url,data,success){
	var Rd=xiuzhengData(url,data,success)
	Rd.method='GET';
	request(Rd)
}
export default {
	request:request,
	post:post,
	get:get
}
// // 中断请求任务
// requestTask.abort();