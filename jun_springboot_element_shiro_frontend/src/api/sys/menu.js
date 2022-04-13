import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/sys/menu/list',
    method: 'get',
    params: query
  })
}

export function fetchMenuSelectList(query) {
  return request({
    url: '/sys/menu/select',
    method: 'get',
    params: query
  })
}

export function getMenuInfo(id) {
  return request({
    url: '/sys/menu/info/' + id,
    method: 'get',
  })
}

export function createMenu(data) {
  return request({
    url: '/sys/menu/save',
    method: 'post',
    data: data
  })
}

export function updateMenu(data) {
  return request({
    url: '/sys/menu/update',
    method: 'post',
    data: data
  })
}

export function deleteMenu(id) {
  return request({
    url: '/sys/menu/delete/' + id,
    method: 'post'
  })
}
