<template>
  <div class="login-container">
    <el-form :model="loginForm" class="login-form">
      <el-form-item>
        <el-input 
          v-model="loginForm.phoneNumber"
          placeholder="请输入手机号"
          prefix-icon="User"
          maxlength="11"
        />
      </el-form-item>
      <el-form-item>
        <el-input 
          v-model="loginForm.password" 
          type="password" 
          placeholder="请输入密码"
          prefix-icon="Lock"
          show-password
          @keyup.enter="handleLogin"
        />
      </el-form-item>
      <el-form-item>
        <el-button 
          type="primary" 
          @click="handleLogin" 
          style="width: 100%"
        >
          登录
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/userStore'
import { ElMessage } from 'element-plus'
import type { User } from '@/models/user'
import { login } from '@/api/userApi'

const router = useRouter()
const userStore = useUserStore()

const loginForm = ref({
  phoneNumber: '',
  password: ''
})

const handleLogin = async () => {
  try {
    if (!loginForm.value.phoneNumber || !loginForm.value.password) {
      ElMessage.warning('请输入手机号和密码')
      return
    }

    const response = await login(loginForm.value)
    console.log('完整的登录响应数据:', {
      code: response.code,
      message: response.message,
      data: response.data,
      原始响应: response
    })

    if (response.code === 200) {
      const userData = response.data
      console.log('后端返回的原始用户数据结构:', {
        数据类型: typeof userData,
        是否为空: !userData,
        所有字段: Object.keys(userData),
        完整数据: userData
      })

      // 检查数据是否在 data.data 中（有些后端会这样嵌套）
      const actualUserData = userData.data || userData
      console.log('实际使用的用户数据:', actualUserData)

      if (!actualUserData) {
        console.error('用户数据为空')
        ElMessage.error('获取用户信息失败')
        return
      }

      // 在构造用户对象之前添加详细的数据检查
      console.log('数据转换调试:', {
        原始userId值: actualUserData.userId,
        原始id值: actualUserData.id,
        原始ID值: actualUserData.ID,
        值类型: typeof actualUserData.userId,
        Number转换结果: Number(actualUserData.userId),
        直接使用值: actualUserData.userId || actualUserData.id || actualUserData.ID
      })

      // 修改构造用户对象的代码
      const user: User = {
        UserID: actualUserData.userId, // 先不做 Number 转换，保持原值
        UserName: String(actualUserData.userName || ''),
        Password: '',
        UserType: String(actualUserData.type || actualUserData.userType || actualUserData.role || '家属'),
        PhoneNumber: String(actualUserData.phone || actualUserData.phoneNumber || actualUserData.mobile || loginForm.value.phoneNumber),
        Email: String(actualUserData.email || ''),
        Address: String(actualUserData.address || ''),
        Age: actualUserData.age ? Number(actualUserData.age) : null,
        Gender: String(actualUserData.gender || actualUserData.sex || null),
        CreatedAt: actualUserData.createdAt ? new Date(actualUserData.createdAt) : new Date()
      }

      console.log('构造的用户对象:', {
        原始数据: actualUserData,
        转换后数据: user,
        UserID值: user.UserID,
        UserID类型: typeof user.UserID
      })

      try {
        await userStore.setUser(user)
        ElMessage.success('登录成功')
        router.push('/family')
      } catch (storeError) {
        console.error('存储用户数据失败:', storeError)
        ElMessage.error('登录成功但保存用户信息失败')
      }
    } else {
      const errorMsg = response.message || '登录失败'
      console.error('登录失败:', errorMsg)
      ElMessage.error(errorMsg)
    }
  } catch (error: any) {
    console.error('登录请求失败:', error)
    const errorMsg = error.response?.message || error.message || '登录失败，请稍后重试'
    ElMessage.error(errorMsg)
  }
}
</script>

<script>
// No changes to script section
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f5f7fa;
}

.login-form {
  width: 350px;
  padding: 20px;
  background: white;
  border-radius: 4px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
</style> 