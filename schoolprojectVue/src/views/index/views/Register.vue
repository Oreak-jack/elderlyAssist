<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { register } from '@/api/userApi.ts'  // 改用封装的 API 函数

const router = useRouter()
const registerForm = ref({
  username: '',
  password: '',
  confirmPassword: '',
  email: '',
  userType: '老人', // 默认选择老人
  age: '',
  gender: '',
  phoneNumber: '',
  address: '',
  agreement: false
})

// 添加加载状态和错误提示
const loading = ref(false)
const errorMessage = ref('')

const handleRegister = async () => {
  try {
    // 表单验证
    if (registerForm.value.password !== registerForm.value.confirmPassword) {
      errorMessage.value = '两次输入的密码不一致'
      return
    }

    // 手机号格式验证
    const phoneRegex = /^1[3-9]\d{9}$/
    if (!phoneRegex.test(registerForm.value.phoneNumber)) {
      errorMessage.value = '请输入正确的手机号码'
      return
    }

    // 设置加载状态
    loading.value = true
    errorMessage.value = ''



    // 准备发送给后端的数据
    const registerData = {
      userName: registerForm.value.username,
      password: registerForm.value.password,
      userType: registerForm.value.userType,
      phoneNumber: registerForm.value.phoneNumber,
      // 可选字段
      age: registerForm.value.age ? parseInt(registerForm.value.age) : null,
      gender: registerForm.value.gender || null,
      email: registerForm.value.email || null,
      address: registerForm.value.address || null
    }

    // 添加更详细的日志
    console.log('发送注册请求，数据：', {
      url: 'http://localhost:8080/user/register',
      method: 'POST',
      data: registerData,
      headers: {
        'Content-Type': 'application/json'
      }
    })

    const response = await register(registerData)
    console.log('注册响应：', response)

    // response 已经是响应数据了，不需要 .data
    if (response.success) {

      // 注册成功，提示用户
      alert('注册成功,\n点击确定跳转到登录页面');

      // 用户点击“确定”后，跳转到登录页面
      setTimeout(() => {
        router.push('/index/login');
      }, 0);
    } else {
      // 显示错误信息
      errorMessage.value = response.message || '注册失败，请重试'
    }
  } catch (error) {
    // 添加更详细的错误日志
    console.error('注册错误详情：', {
      message: error.message,
      status: error.response?.status,
      data: error.response?.data,
      config: {
        url: error.config?.url,
        method: error.config?.method,
        baseURL: error.config?.baseURL,
        headers: error.config?.headers
      }
    })
    errorMessage.value = error.message || '注册失败，请稍后重试'
  } finally {
    // 结束加载状态
    loading.value = false
  }
}
</script>

<template>
  <div class="register-container">
    <div class="register-box">
      <h2>用户注册</h2>
      <!-- 显示错误信息 -->
      <div v-if="errorMessage" class="error-message">
        {{ errorMessage }}
      </div>
      <form @submit.prevent="handleRegister">
        <div class="form-group">
          <label for="username">用户名</label>
          <input 
            type="text" 
            id="username"
            v-model="registerForm.username"
            placeholder="请输入用户名"
            required
          >
        </div>

        <div class="form-group">
          <label for="userType">用户类型</label>
          <select 
            id="userType" 
            v-model="registerForm.userType"
            required
          >
            <option value="老人">老人</option>
            <option value="家属">家属</option>
          </select>
        </div>

        <div class="form-group">
          <label for="age">年龄</label>
          <input 
            type="number" 
            id="age"
            v-model="registerForm.age"
            placeholder="请输入年龄"
          >
        </div>

        <div class="form-group">
          <label for="gender">性别</label>
          <select 
            id="gender" 
            v-model="registerForm.gender"
          >
            <option value="">请选择性别</option>
            <option value="男">男</option>
            <option value="女">女</option>
          </select>
        </div>

        <div class="form-group">
          <label for="phoneNumber">手机号码</label>
          <input 
            type="tel" 
            id="phoneNumber"
            v-model="registerForm.phoneNumber"
            placeholder="请输入手机号码"
          >
        </div>

        <div class="form-group">
          <label for="email">邮箱</label>
          <input 
            type="email" 
            id="email"
            v-model="registerForm.email"
            placeholder="请输入邮箱"
          >
        </div>

        <div class="form-group">
          <label for="address">地址</label>
          <input 
            type="text" 
            id="address"
            v-model="registerForm.address"
            placeholder="请输入地址"
          >
        </div>
        
        <div class="form-group">
          <label for="password">密码</label>
          <input 
            type="password" 
            id="password"
            v-model="registerForm.password"
            placeholder="请输入密码"
            required
          >
        </div>

        <div class="form-group">
          <label for="confirmPassword">确认密码</label>
          <input 
            type="password" 
            id="confirmPassword"
            v-model="registerForm.confirmPassword"
            placeholder="请再次输入密码"
            required
          >
        </div>

        <div class="form-options">
          <label class="agreement">
            <input 
              type="checkbox" 
              v-model="registerForm.agreement"
              required
            >
            我已阅读并同意<a href="#" class="agreement-link">用户协议</a>和<a href="#" class="agreement-link">隐私政策</a>
          </label>
        </div>

        <button 
          type="submit" 
          class="register-btn" 
          :disabled="loading"
        >
          {{ loading ? '注册中...' : '注册' }}
        </button>

        <div class="login-link">
          已有账号？
          <router-link to="/index/login">立即登录</router-link>
        </div>
      </form>
    </div>
  </div>
</template>

<style scoped>
.register-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f5f5f5;
}

.register-box {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
}

h2 {
  text-align: center;
  color: #333;
  margin-bottom: 2rem;
}

.form-group {
  margin-bottom: 1.5rem;
}

label {
  display: block;
  margin-bottom: 0.5rem;
  color: #666;
}

input[type="text"],
input[type="email"],
input[type="password"] {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 1rem;
}

input[type="text"]:focus,
input[type="email"]:focus,
input[type="password"]:focus {
  outline: none;
  border-color: #4a90e2;
}

.form-options {
  margin-bottom: 1.5rem;
}

.agreement {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  font-size: 0.9rem;
  color: #666;
}

.agreement-link {
  color: #4a90e2;
  text-decoration: none;
}

.agreement-link:hover {
  text-decoration: underline;
}

.register-btn {
  width: 100%;
  padding: 0.75rem;
  background-color: #4a90e2;
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 1rem;
  cursor: pointer;
  transition: background-color 0.2s;
}

.register-btn:hover {
  background-color: #357abd;
}

.login-link {
  text-align: center;
  margin-top: 1.5rem;
  color: #666;
}

.login-link a {
  color: #4a90e2;
  text-decoration: none;
}

.login-link a:hover {
  text-decoration: underline;
}

select {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 1rem;
  background-color: white;
}

select:focus {
  outline: none;
  border-color: #4a90e2;
}

input[type="number"],
input[type="tel"] {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 1rem;
}

input[type="number"]:focus,
input[type="tel"]:focus {
  outline: none;
  border-color: #4a90e2;
}

/* 添加新样式 */
.error-message {
  color: #ff4d4f;
  background-color: #fff2f0;
  border: 1px solid #ffccc7;
  padding: 0.5rem;
  border-radius: 4px;
  margin-bottom: 1rem;
  text-align: center;
}

.register-btn:disabled {
  background-color: #b3b3b3;
  cursor: not-allowed;
}
</style>