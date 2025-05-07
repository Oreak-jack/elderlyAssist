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
        console.log('userStore收到的原始数据:', JSON.stringify(user, null, 2))
        
        // 验证必要字段
        if (!user.userId) {
          throw new Error('用户ID不能为空')
        }
        if (!user.userName) {
          throw new Error('用户名不能为空')
        }
        if (!user.userType) {
          throw new Error('用户类型不能为空')
        }

        console.log("user",user)

        const userData: User = {
          userId: Number(user.userId),
          userName: String(user.userName),
          password: '',
          userType: user.userType === '老人' ? '老人' : '家属',
          phoneNumber: String(user.phoneNumber || ''),
          email: String(user.email || ''),
          address: String(user.address || ''),
          age: user.age ? Number(user.age) : null,
          gender: user.gender === '男' ? '男' : (user.gender === '女' ? '女' : null),
          createdAt: user.createdAt instanceof Date ? user.createdAt : new Date()
        }

        console.log("userData",userData)

        console.log('转换后的用户数据:', JSON.stringify(userData, null, 2))
        
        // 保存数据
        this.user = userData
        localStorage.setItem('user', JSON.stringify(userData))
        
        console.log('用户数据已保存到localStorage')
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
        if (!parsedUser || !parsedUser.userId || !parsedUser.userName) {
          console.warn('存储的用户数据无效')
          this.clearUser()
          return
        }

        this.user = {
          ...parsedUser,
          createdAt: new Date(parsedUser.createdAt),
          age: parsedUser.age ? Number(parsedUser.age) : null,
          userId: Number(parsedUser.userId)
        }
        console.log('从 localStorage 恢复的用户数据:', this.user)
      } catch (error) {
        console.error('解析存储的用户数据失败:', error)
        this.clearUser()
      }
    }
  }
})