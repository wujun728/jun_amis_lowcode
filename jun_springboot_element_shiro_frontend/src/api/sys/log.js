import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/sys/log/list',
    method: 'get',
    params: query
  })
}