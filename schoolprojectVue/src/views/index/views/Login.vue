<script setup>
import {ref, watch} from 'vue'
import { useRouter } from 'vue-router'
import {login} from "@/api/user.js";

const router = useRouter()
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



  const res = await login(loginData)
  response.value = res
  // alert(response);
  // if (response.success) {
  //   router.push('/')
  // } else {
  //   alert(response.message)
  // }
  console.log("下面是 watch 信息")


  console.log('登录信息：', loginForm.value)


}
// 监视 response 的变化
watch(response,(newResponse) => {
  console.log("watch正在监视")
  console.log('response 变化了：', newResponse);
  alert(newResponse.message)
  if (newResponse.success) {
    console.log("接下来路由重定向");
    router.push('/home')
  }
})
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