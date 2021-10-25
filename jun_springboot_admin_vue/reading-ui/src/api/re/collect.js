import request from '@/utils/request'

// 查询收藏列列表
export function listCollect(query) {
  return request({
    url: '/re/collect/list',
    method: 'get',
    params: query
  })
}

// 查询收藏列详细
export function getCollect(id) {
  return request({
    url: '/re/collect/' + id,
    method: 'get'
  })
}

// 新增收藏列
export function addCollect(data) {
  return request({
    url: '/re/collect',
    method: 'post',
    data: data
  })
}

// 修改收藏列
export function updateCollect(data) {
  return request({
    url: '/re/collect',
    method: 'put',
    data: data
  })
}

// 删除收藏列
export function delCollect(id) {
  return request({
    url: '/re/collect/' + id,
    method: 'delete'
  })
}

// 导出收藏列
export function exportCollect(query) {
  return request({
    url: '/re/collect/export',
    method: 'get',
    params: query
  })
}