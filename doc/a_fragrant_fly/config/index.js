// var server={
//     api:"http://csapi.we99.net/public/",
// 	// host:"http://www.shop.com/api/",
// 	image:"http://csapi.we99.net/public/",
// 	// host:"https://gitee.com/wokaixin/data/raw/demo4/",
// 	// host:"https://gitee.com/wokaixin/a_fragrant_fly/raw/master/request/data/",
// 	host:"http://csapi.we99.net/public/index.php/api/"
// }
// var api={
//     api:"http://csapi.we99.net/api/",
//     image:"http://csapi.we99.net/public/",
// }
// module.exports = {
//     api:api,

// }

var domain='zf.01film.cn';
var websockethost='api.01film.cn/mqtt';//wss连接服务器地址
// var API='api.cs.01film.cn/public';
var API='api.01film.cn';
// var domain='dt.01film.cn';
// var API=domain+'/API/public';//API服务端域名
var source='h5';
// #ifdef APP-PLUS
source='app';
// #endif

// #ifdef MP-WEIXIN
source='weixin';
// #endif
const config={
    source:source,//前端标识
    websockethost,
    appid:101552115164859,
    runLocal:false,//本地模式，不发送ajax请求服务器
    domain:domain,//前端域名
    host:'http://'+domain+'/#',//h5访问地址
    
    userAPI:'https://'+API+'/user.php',//用戶中心接口
    API:'https://'+API+'/home.php',//服务器API接口
    fileUrl:{
        image:'https://'+API+'/upload/',//文件服务器通用地址
        media:'https://'+API+'/media/',//图片服務器地址
        file:'https://'+API+'/file/',//媒体服务器地址
    },
    //获取附件服务器的url地址 如果图片地址有http开头就直接返回如果没有将会拼接接口地址
    getFileUrl(url,type){
        var type=type || 'image';
        var fileUrl=this.fileUrl[type] || '';
        if(url && url.slice(0, 4) != "http"){
               url=fileUrl+url;
        }
        return url;
    }
}
// console.log({...config})
// export config
export default config