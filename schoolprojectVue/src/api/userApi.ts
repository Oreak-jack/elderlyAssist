import request from '@/utils/request'

export interface LoginParams {
  phoneNumber: string
  password: string
}

export const login = (data: LoginParams) => {
  console.log('发送登录请求:', data)
  
  return request({
    url: '/user/login',
    method: 'post',
    data
  }).then(response => {
    console.log('登录API响应:', response)
    return response
  })
}

export function register(data) {
  console.log('注册请求URL:', 'http://localhost:8080/user/register')
  console.log('注册请求数据:', data)
  
  return request({
    url: 'http://localhost:8080/user/register',
    method: 'post',
    data,
    validateStatus: function (status) {
      return status >= 200 && status < 300
    }
  })
} 