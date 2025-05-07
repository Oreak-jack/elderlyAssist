import axios, { type InternalAxiosRequestConfig } from 'axios'
import { ElMessage } from 'element-plus'
import type { AxiosInstance, AxiosResponse } from 'axios'

// 创建axios实例
const service: AxiosInstance = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080',
  timeout: 15000, // 增加超时时间到15秒
  headers: {
    'Content-Type': 'application/json'
  }
})

// 请求拦截器
service.interceptors.request.use(
  (config: InternalAxiosRequestConfig) => {
    // 从localStorage获取token
    const token = localStorage.getItem('token')
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    console.log('发送请求，完整配置:', JSON.stringify(config, null, 2))
    return config
  },
  (error) => {
    console.error('请求错误:', error)
    return Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(
  (response: AxiosResponse) => {
    console.log('收到响应，完整数据:', JSON.stringify(response, null, 2))
    
    // 检查响应状态
    if (response.status === 200) {
      const res = response.data
      if (res.code === 200 && res.success) {
        return response
      } else {
        ElMessage.error(res.message || '请求失败')
        return Promise.reject(new Error(res.message || '请求失败'))
      }
    } else {
      ElMessage.error('请求失败')
      return Promise.reject(new Error('请求失败'))
    }
  },
  (error) => {
    console.error('响应错误:', error)
    ElMessage.error(error.message || '请求失败')
    return Promise.reject(error)
  }
)

// 定义通用的响应类型
export interface ApiResponse<T = any> {
  code: number;
  message: string;
  data: T;
}

// 定义请求配置类型
export interface RequestConfig extends Omit<InternalAxiosRequestConfig, 'headers'> {
  headers?: Record<string, string>;
}

// 导出请求函数
export const request = <T = any>(config: RequestConfig): Promise<AxiosResponse<T>> => {
  return service.request(config)
}

export default service