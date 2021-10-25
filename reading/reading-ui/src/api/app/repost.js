import request from '@/utils/request'

// 查询帖子列表
export function listRepost(query) {
    return request({
        url: '/re/repost/list',
        method: 'get',
        params: query
    })
}

// 查询帖子详细
export function getRepost(id) {
    return request({
        url: '/re/repost/' + id,
        method: 'get'
    })
}

// 新增帖子
export function addRepost(data) {
    return request({
        url: '/re/repost',
        method: 'post',
        data: data
    })
}

// 修改帖子
export function updateRepost(data) {
    return request({
        url: '/re/repost',
        method: 'put',
        data: data
    })
}

// 删除帖子
export function delRepost(id) {
    return request({
        url: '/re/repost/' + id,
        method: 'delete'
    })
}

// 导出帖子
export function exportRepost(query) {
    return request({
        url: '/re/repost/export',
        method: 'get',
        params: query
    })
}