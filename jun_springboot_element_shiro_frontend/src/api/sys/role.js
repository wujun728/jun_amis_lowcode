import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/sys/role/list',
    method: 'get',
    params: query
  })
}

export function fetchRoleSelectList(query) {
  return request({
    url: '/sys/role/select',
    method: 'get',
    params: query
  })
}

export function getRoleInfo(id) {
  return request({
    url: '/sys/role/info/' + id,
    method: 'get',
  })
}

export function createRole(data) {
  return request({
    url: '/sys/role/save',
    method: 'post',
    data: data
  })
}

export function updateRole(data) {
  return request({
    url: '/sys/role/update',
    method: 'post',
    data: data
  })
}

export function deleteRole(data) {
  return request({
    url: '/sys/role/delete',
    method: 'post',
    data: data
  })
}
