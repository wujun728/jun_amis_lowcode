import request from '@/utils/request'

// 查询评论列表
export function listRemark(query) {
  return request({
    url: '/re/remark/list',
    method: 'get',
    params: query
  })
}

// 查询评论详细
export function getRemark(id) {
  return request({
    url: '/re/remark/' + id,
    method: 'get'
  })
}

// 新增评论
export function addRemark(data) {
  return request({
    url: '/re/remark',
    method: 'post',
    data: data
  })
}

// 修改评论
export function updateRemark(data) {
  return request({
    url: '/re/remark',
    method: 'put',
    data: data
  })
}

// 删除评论
export function delRemark(id) {
  return request({
    url: '/re/remark/' + id,
    method: 'delete'
  })
}

// 导出评论
export function exportRemark(query) {
  return request({
    url: '/re/remark/export',
    method: 'get',
    params: query
  })
}