import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/sys/config/list',
    method: 'get',
    params: query
  })
}

export function getConfigInfo(id) {
  return request({
    url: '/sys/config/info/' + id,
    method: 'get',
  })
}

export function createConfig(data) {
  return request({
    url: '/sys/config/save',
    method: 'post',
    data: data
  })
}

export function updateConfig(data) {
  return request({
    url: '/sys/config/update',
    method: 'post',
    data: data
  })
}

export function deleteConfig(data) {
  return request({
    url: '/sys/config/delete',
    method: 'post',
    data: data
  })
}
