import { defineStore } from 'pinia'
import type { User } from '@/models/user'

export const useUserStore = defineStore('user', {
  state: () => ({
    currentUser: null as User | null,
    isLoggedIn: false
  }),

  getters: {
    // 获取当前用户信息
    getCurrentUser: (state) => state.currentUser,
    // 判断是否登录
    getLoginStatus: (state) => state.isLoggedIn,
    // 获取用户类型
    getUserType: (state) => state.currentUser?.UserType
  },

  actions: {
    // 设置用户信息并更新登录状态
    setUser(user: User) {
      this.currentUser = user
      this.isLoggedIn = true
    },

    // 清除用户信息并更新登录状态
    clearUser() {
      this.currentUser = null
      this.isLoggedIn = false
    },

    // 更新用户信息
    updateUser(userData: Partial<User>) {
      if (this.currentUser) {
        this.currentUser = { ...this.currentUser, ...userData }
      }
    }
  }
})