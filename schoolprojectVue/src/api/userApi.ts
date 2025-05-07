import request from '@/utils/request'

export interface LoginParams {
  phoneNumber: string
  password: string
}

export interface RegisterParams {
  userName: string
  password: string
  phoneNumber: string
  userType: string
  age?: number
  gender?: string
  email?: string
  address?: string
}

export interface LoginResponse {
  success: boolean;
  message: string;
  data: {
    userId: number;
    userName: string;
    userType: string;
    phoneNumber: string;
    email?: string;
    address?: string;
    age?: number;
    gender?: string;
    createdAt?: string;
  };
  code: number;
}

export const login = async (data: LoginParams) => {
  console.log('发送登录请求，数据:', JSON.stringify(data, null, 2))
  
  const response = await request<LoginResponse>({
    url: '/user/login',
    method: 'post',
    data
  })

  console.log('登录API响应，完整数据:', JSON.stringify(response, null, 2))

  if (response.data.code !== 200 || !response.data.success) {
    throw new Error(response.data.message || '登录失败')
  }

  return response
}

export function register(data: RegisterParams) {
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