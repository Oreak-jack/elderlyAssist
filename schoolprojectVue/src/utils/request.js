import axios from 'axios'

const request = axios.create({
  baseURL: import.meta.env.VITE_API_URL, // 从环境变量获取基础URL
  timeout: 5000,
  headers: {
    'Content-Type': 'application/json'
  }
})

// 请求拦截器
request.interceptors.request.use(
  config => {
    console.log('请求配置：', {
      url: config.url,
      method: config.method,
      baseURL: config.baseURL,
      headers: config.headers,
      data: config.data // 添加请求数据的日志
    })
    // 可以在这里添加token等认证信息
    return config
  },
  error => {
    console.error('请求错误：', error)
    return Promise.reject(error)
  }
)

// 响应拦截器
request.interceptors.response.use(
  response => {
    console.log('响应数据：', response.data)
    // 检查响应格式
    if (response.data.hasOwnProperty('success')) {
      return response.data
    }
    return {
      success: true,
      data: response.data
    }
  },
  error => {
    console.error('响应错误：', {
      status: error.response?.status,
      statusText: error.response?.statusText,
      data: error.response?.data,
      url: error.config?.url,
      method: error.config?.method,
      headers: error.config?.headers,
      requestData: error.config?.data
    })
    return Promise.reject(error)
  }
)

export default request 