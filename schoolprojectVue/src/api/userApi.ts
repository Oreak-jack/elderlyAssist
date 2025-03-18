import request from '@/utils/request'

export function register(data) {
  console.log('注册请求URL:', 'http://localhost:8080/user/register')
  console.log('注册请求数据:', data)
  
  return request({
    url: 'http://localhost:8080/user/register',
    method: 'post',
    data,
    validateStatus: function (status) {
      return status >= 200 && status < 300 // 接受所有2xx状态码
    }
  })
}

export function login(data) {
  return request({
    url: 'http://localhost:8080/user/login',
    method: 'post',
    data
  })
} 