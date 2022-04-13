import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/sys/user/list',
    method: 'get',
    params: query
  })
}

export function getUserInfo(id) {
  return request({
    url: '/sys/user/info/' + id,
    method: 'get',
  })
}

export function createUser(data) {
  return request({
    url: '/sys/user/save',
    method: 'post',
    data: data
  })
}

export function updateUser(data) {
  return request({
    url: '/sys/user/update',
    method: 'post',
    data: data
  })
}

export function deleteUser(data) {
  return request({
    url: '/sys/user/delete',
    method: 'post',
    data: data
  })
}
