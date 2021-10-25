import request from '@/utils/request'

// 查询积分列表
export function listIntegral(query) {
    return request({
        url: '/re/integral/list',
        method: 'get',
        params: query
    })
}

// 查询积分详细
export function getIntegral(id) {
    return request({
        url: '/re/integral/' + id,
        method: 'get'
    })
}

// 新增积分
export function addIntegral(data) {
    return request({
        url: '/re/integral',
        method: 'post',
        data: data
    })
}

// 修改积分
export function updateIntegral(data) {
    return request({
        url: '/re/integral',
        method: 'put',
        data: data
    })
}

// 删除积分
export function delIntegral(id) {
    return request({
        url: '/app/integral/' + id,
        method: 'delete'
    })
}

// 导出积分
export function exportIntegral(query) {
    return request({
        url: '/app/integral/export',
        method: 'get',
        params: query
    })
}