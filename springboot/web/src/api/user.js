// api/user.js
import { axios } fromm '@/utils/request'

const api = {
    info: '/user',
    list: '/users'
}

// 根据用户 id 获取用户信息
export function getUser (id) {
    return axios({
        url: `${api.user}/${id}`,
        method: 'get'
    })
}

// 增加用户
export function addUser (parameter) {
    return axios({
        url: api.user,
        method: 'post',
        data: parameter
    })
}

// 更新用户 // or (id, parameter)
export function updateUser (parameter) {
    return axios({
        url: `${api.user}/${parameter.id}`, // or `${api.user}/${id}`
        method: 'put',
        data: parameter
    })
}

// 删除用户
export function deleteUser (id) {
    return axios({
        url: `${api.user}/${id}`,
        method: 'delete',
        data: parameter
    })
}

// 获取用户列表 parameter: { pageSize: 10, pageNo: 1 }
export function getUsers (parameter) {
    return axios({
        url: api.list,
        method: 'get',
        params: parameter
    })
}