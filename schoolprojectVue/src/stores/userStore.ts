import { defineStore } from 'pinia'
import type { User } from '@/models/user'

export const useUserStore = defineStore('user', {
  state: () => ({
    user: null as User | null
  }),

  getters: {
    isLoggedIn: (state) => !!state.user,
    getCurrentUser: (state) => state.user
  },

  actions: {
    setUser(user: User) {
      try {
        console.log('userStore收到的数据:', {
          原始用户数据: user,
          UserID原始值: user.userId,
          UserID类型: typeof user.userId
        })
        
        // 在转换之前检查值
        const userIdValue = user.userId
        console.log('UserID转换过程:', {
          转换前: userIdValue,
          转换前类型: typeof userIdValue,
          Number转换结果: Number(userIdValue),
          转换后类型: typeof Number(userIdValue)
        })

        const userData = {
          UserID: Number(user.userId), // 这里进行转换
          UserName: String(user.userName || '未知用户'),
          Password: '',
          UserType: String(user.userType || '家属'),
          PhoneNumber: String(user.phoneNumber || ''),
          Email: String(user.email || ''),
          Address: String(user.address || ''),
          Age: user.age ? Number(user.age) : null,
          Gender: user.gender || null,
          CreatedAt: user.createdAt instanceof Date ? user.createdAt : new Date()
        }

        console.log('最终存储的数据:', {
          转换后的UserID: userData.UserID,
          转换后类型: typeof userData.UserID
        })
        
        // 验证转换后的数据
        if (!userData.UserID) {
          console.warn('用户ID为0，这可能是一个问题')
        }
        
        if (!userData.UserName) {
          console.warn('用户名为空，使用默认值')
        }

        console.log('最终存储的用户数据:', userData)
        
        // 保存数据
        this.user = userData
        localStorage.setItem('user', JSON.stringify(userData))
        
        return true
      } catch (error) {
        console.error('存储用户数据时出错:', error)
        throw error
      }
    },

    clearUser() {
      this.user = null
      localStorage.removeItem('user')
    },

    initUserFromStorage() {
      try {
        const storedUser = localStorage.getItem('user')
        if (!storedUser) {
          return
        }

        const parsedUser = JSON.parse(storedUser)
        if (!parsedUser || !parsedUser.UserID || !parsedUser.UserName) {
          console.warn('存储的用户数据无效')
          this.clearUser()
          return
        }

        this.user = {
          ...parsedUser,
          CreatedAt: new Date(parsedUser.CreatedAt),
          Age: parsedUser.Age ? Number(parsedUser.Age) : null,
          UserID: Number(parsedUser.UserID)
        }
        console.log('从 localStorage 恢复的用户数据:', this.user)
      } catch (error) {
        console.error('解析存储的用户数据失败:', error)
        this.clearUser()
      }
    }
  }
})