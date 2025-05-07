<script setup>
import {ref, watch} from 'vue'
import { useRouter } from 'vue-router'
import {login} from "@/api/userApi.ts"
import { useUserStore } from '@/stores/userStore'

const router = useRouter()
const userStore = useUserStore()
const response = ref(null)
const loginForm = ref({
  phoneNumber: '',
  password: '',
  remember: false
})

const handleLogin = async () => {
  // 手机号格式验证
  const phoneRegex = /^1[3-9]\d{9}$/
  if (!phoneRegex.test(loginForm.value.phoneNumber)) {
    alert('请输入正确的手机号码')
    return
  }

  // TODO: 实现登录逻辑
  const loginData = {
    phoneNumber: loginForm.value.phoneNumber,
    password: loginForm.value.password,
  }
  //  await 等 login 执行完毕后继续执行，下面的数据才不会出错

  try {
    const res = await login(loginData)
    console.log(res.data)
    if (!res) {
      alert('登录失败，请稍后重试')
      return
    }
    
    if (res.data.success) {
      // 保存用户信息到 userStore
      userStore.setUser(res.data.data)
      // 显示登录成功消息
      alert(res.data.message || '登录成功')
      
      console.log("用户类型:", res.data.userType)
      
      // 根据用户类型跳转到不同页面
      if (res.data.userType === '老人') {
        console.log("正在执行老人跳转")
        setTimeout(() => {
          router.push('/elderly')
        }, 100)
      } else {
        console.log("正在执行家人跳转")
        setTimeout(() => {
          router.push('/family')
        }, 100)
      }
    } else {
      alert(res.message || '登录失败，请检查账号密码')
    }
  } catch (error) {
    console.error('登录错误：', error)
    alert('登录出错，请稍后重试')
  }
}

// 移除 watch 函数，避免重复的路由跳转逻辑
</script>

<template>
  <div class="login-container">
    <div class="login-box">
      <h2>用户登录</h2>
      <form @submit.prevent="handleLogin">
        <div class="form-group">
          <label for="phoneNumber">手机号码</label>
          <input 
            type="tel" 
            id="phoneNumber"
            v-model="loginForm.phoneNumber"
            placeholder="请输入手机号码"
            required
            pattern="^1[3-9]\d{9}$"
            title="请输入正确的手机号码"
          >
        </div>
        
        <div class="form-group">
          <label for="password">密码</label>
          <input 
            type="password" 
            id="password"
            v-model="loginForm.password"
            placeholder="请输入密码"
            required
          >
        </div>

        <div class="form-options">
          <label class="remember-me">
            <input 
              type="checkbox" 
              v-model="loginForm.remember"
            >
            记住我
          </label>
          <a href="#" class="forgot-password">忘记密码？</a>
        </div>

        <button type="submit" class="login-btn">登录</button>

        <div class="register-link">
          还没有账号？
          <router-link to="/index/register">立即注册</router-link>
        </div>
      </form>
    </div>
  </div>
</template>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f5f5f5;
}

.login-box {
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

input[type="tel"],
input[type="password"] {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 1rem;
}

input[type="tel"]:focus,
input[type="password"]:focus {
  outline: none;
  border-color: #4a90e2;
}

.form-options {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1.5rem;
}

.remember-me {
  display: flex;
  align-items: center;
  gap: 0.5rem;
}

.forgot-password {
  color: #4a90e2;
  text-decoration: none;
}

.forgot-password:hover {
  text-decoration: underline;
}

.login-btn {
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

.login-btn:hover {
  background-color: #357abd;
}

.register-link {
  text-align: center;
  margin-top: 1.5rem;
  color: #666;
}

.register-link a {
  color: #4a90e2;
  text-decoration: none;
}

.register-link a:hover {
  text-decoration: underline;
}
</style>