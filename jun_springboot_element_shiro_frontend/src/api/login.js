import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/sys/login',
    method: 'post',
    data: data
  })
}

export function getInfo(token) {
  return request({
    url: '/sys/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/sys/logout',
    method: 'post'
  })
}

// 获取验证码
export function getCodeImg(uuid) {
  return request({
    url: '/captcha.jpg?uuid=' + uuid,
    method: 'get'
  })
}

/**
 * 修改用户密码
 */
export function changePassword(data) {
  return request({
    url: '/sys/changePassword',
    method: 'post',
    data: data
  })
}
