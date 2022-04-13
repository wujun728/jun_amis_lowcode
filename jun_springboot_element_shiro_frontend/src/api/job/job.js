import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/sys/schedule/list',
    method: 'get',
    params: query
  })
}

export function getJobInfo(id) {
  return request({
    url: '/sys/schedule/info/' + id,
    method: 'get',
  })
}

export function createJob(data) {
  return request({
    url: '/sys/schedule/save',
    method: 'post',
    data: data
  })
}

export function updateJob(data) {
  return request({
    url: '/sys/schedule/update',
    method: 'post',
    data: data
  })
}

export function deleteJob(data) {
  return request({
    url: '/sys/schedule/delete',
    method: 'post',
    data: data
  })
}

export function pauseJob(data) {
  return request({
    url: '/sys/schedule/pause',
    method: 'post',
    data: data
  })
}

export function resumeJob(data) {
  return request({
    url: '/sys/schedule/resume',
    method: 'post',
    data: data
  })
}

export function runJob(data) {
  return request({
    url: '/sys/schedule/run',
    method: 'post',
    data: data
  })
}

export function fetchLogList(query) {
  return request({
    url: '/sys/scheduleLog/list',
    method: 'get',
    params: query
  })
}

export function getJobLogInfo(id) {
  return request({
    url: '/sys/scheduleLog/info/' + id,
    method: 'get',
  })
}
