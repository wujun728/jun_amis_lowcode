// 这里导入的时候，只能够是apipi，因为你暴露的时候，就是apiapi;
// 当然你也可以使用别名去修改名称

import {
	uniRequest
} from "@/https/request.js"

/**
 * 请求的方式大写
 * */
// 查询定时任务调度详细


/*  这是接口示例
export const listArrlist = (params, util) => uniRequest('/api/userInfo/teacherInfo/getTeacherClassValue', 'GET', params,
	util);
这一句等价于
const demo2=function(params){
   return	apiapi('/school/campusPro/getWeatherByOrgId', 'POST',params)
}
export  demo2
 */

/**
 * 测试接口
 */
export const hello = (params) => uniRequest('/app/hello', 'GET', params);
/**
 * 查询某一字典得所有数据
 */
export const upload = (data) => uniRequest('/app/common/upload', 'POST', data);
/**
 * 查询某一字典得所有数据
 */
export const getDicts = (param) => uniRequest('/app/dict/data/type/' + param, 'GET');
/**
 * 登录接口
 */
export const login = (data) => uniRequest('/app/login', 'POST', data);
/**
 * 注册接口
 */
export const register = (data) => uniRequest('/app/register', 'POST', data);
/**
 * 修改密码接口
 */
export const changePwd = (data) => uniRequest('/app/changePwd', 'POST', data);
/**
 * 获取验证码
 */
export const verifyCode = (data) => uniRequest('/app/verifyCode', 'POST', data);
/**
 * 根据用户编号查询用户详细信息
 */
export const selectUser = (param) => uniRequest('/app/user/' + param, 'GET');
/**
 * 更新用户信息
 */
export const updateUser = (data) => uniRequest('/app/user/update', 'POST', data);
/**
 * 签到
 */
export const sign = (data) => uniRequest('/app/integral/sign', 'POST', data);
/**
 * 积分查询
 */
export const getIntegralList = (data) => uniRequest('/app/integral/list', 'GET', data);
/**
 * 根据书贴查询书贴列表
 */
export const getPostList = (data) => uniRequest('/app/repost/list/' , 'GET', data);
/**
 * 新增书贴
 */
export const insertPost = (data) => uniRequest('/app/repost', 'POST', data);
/**
 * 删除书贴
 */
export const delPost = (param) => uniRequest('/app/repost/delete/' + param, 'GET');
/**
 * 查询书贴详情
 */
export const getPost = (param) => uniRequest('/app/repost/' + param, 'GET');
/**
 * 根据书贴编号查询评论列表
 */
export const getRemarkList = (param) => uniRequest('/app/remark/list/' + param, 'GET');
/**
 * 新增评论
 */
export const insertRemark = (data) => uniRequest('/app/remark/', 'POST', data);
/**
 * 删除评论
 */
export const delRemark = (param) => uniRequest('/app/remark/delete/' + param, 'GET');
/**
 * 新增点赞
 */
export const insertLike =  (data) => uniRequest('/app/like/', 'POST', data);

/**
 * 新增收藏
 */
export const insertCollect =  (data) => uniRequest('/app/collect/', 'POST', data);


