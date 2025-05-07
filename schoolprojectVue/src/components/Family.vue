<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useUserStore } from '@/stores/userStore'
import { getFamilyInfo, getElderlyHealthData } from '@/api/familyApi'
import { ElMessage } from 'element-plus'
import type { User } from '@/models/user'
import type { SmartwatchData } from '@/models/healthData'
import FamilyInfo from '@/views/family/FamilyInfo.vue'
import AddOld from '@/views/family/AddOld.vue'

interface ElderlyWithHealth extends User {
  healthData?: SmartwatchData;
}

interface FamilyUser extends User {
  elderlyList?: ElderlyWithHealth[];
}

const userStore = useUserStore()
const familyInfo = ref<FamilyUser>({
  UserID: 0,
  UserName: '',
  Password: '',
  UserType: '家属',
  PhoneNumber: '',
  CreatedAt: new Date(),
  Age: undefined,
  Gender: undefined,
  Email: '',
  Address: '',
  elderlyList: []
})

// 固定的老人健康数据
const fixedElderlyData: ElderlyWithHealth[] = [
  {
    UserID: 1001,
    UserName: '张爷爷',
    Password: '',
    UserType: '老人',
    PhoneNumber: '13800138001',
    Age: 72,
    Gender: '男',
    Email: '',
    Address: '北京市朝阳区',
    CreatedAt: new Date('2020-01-01'),
    healthData: {
      heartRate: 78,
      bloodOxygen: 98,
      stepCount: 4500,
      sleepDuration: 7.5,
      recordedAt: new Date().toISOString()
    }
  },
  {
    UserID: 1002,
    UserName: '李奶奶',
    Password: '',
    UserType: '老人',
    PhoneNumber: '13800138002',
    Age: 68,
    Gender: '女',
    Email: '',
    Address: '北京市海淀区',
    CreatedAt: new Date('2019-05-15'),
    healthData: {
      heartRate: 85,
      bloodOxygen: 96,
      stepCount: 3200,
      sleepDuration: 6.2,
      recordedAt: new Date(Date.now() - 3600000).toISOString() // 1小时前
    }
  }
]

// 获取老人的健康数据
const fetchElderlyHealthData = async (elderly: User) => {
  try {
    const response = await getElderlyHealthData(elderly.UserID)
    if (response.code === 200 && response.data) {
      return response.data
    }
  } catch (error) {
    console.error(`获取老人${elderly.UserName}的健康数据失败:`, error)
  }
  return null
}

const fetchFamilyInfo = async () => {
  try {
    // 模拟API响应
    const mockResponse = {
      code: 200,
      data: {
        ...familyInfo.value,
        elderlyList: fixedElderlyData
      }
    }

    // 实际项目中应该使用API调用
    // const response = await getFamilyInfo()
    const response = mockResponse

    console.log('获取家属信息响应：', response)

    if (response.code === 200 && response.data) {
      // 获取每个老人的健康数据
      const elderlyList = await Promise.all(
          response.data.elderlyList.map(async (elderly) => ({
            ...elderly,
            healthData: elderly.healthData || await fetchElderlyHealthData(elderly)
          }))
      )

      familyInfo.value = {
        ...familyInfo.value,
        elderlyList
      }
    }
  } catch (error) {
    console.error('获取家属信息失败:', error)
    ElMessage.error('获取家属信息失败')
  }
}

onMounted(async () => {
  // 模拟用户数据
  const mockUser = {
    UserID: 12345,
    UserName: '王小明',
    Password: '',
    UserType: '家属',
    PhoneNumber: '13800138000',
    Email: 'wangxiaoming@example.com',
    Address: '北京市朝阳区',
    Age: 45,
    Gender: '男',
    CreatedAt: new Date('2018-03-10')
  }

  userStore.user = mockUser
  userStore.initUserFromStorage()
  const user = userStore.user
  console.log('从 store 获取的用户信息:', user)

  if (user && user.UserID) {
    const userInfo = {
      UserID: Number(user.UserID),
      UserName: String(user.UserName),
      Password: String(user.Password),
      UserType: String(user.UserType),
      PhoneNumber: String(user.PhoneNumber || ''),
      Email: String(user.Email || ''),
      Address: String(user.Address || ''),
      Age: user.Age ? Number(user.Age) : null,
      Gender: user.Gender || null,
      CreatedAt: new Date(user.CreatedAt),
      elderlyList: []
    }

    console.log('准备设置的用户信息:', userInfo)
    familyInfo.value = userInfo

    await fetchFamilyInfo()
  } else {
    ElMessage.error('未获取到有效的用户信息，请重新登录')
  }
})

// 健康数据状态判断
const getHeartRateStatus = (heartRate: number) => {
  if (!heartRate) return { class: '', text: '未知', icon: 'mdi-help-circle-outline' }
  if (heartRate < 60) return { class: 'warning', text: '偏低', icon: 'mdi-arrow-down-bold' }
  if (heartRate > 100) return { class: 'danger', text: '偏高', icon: 'mdi-arrow-up-bold' }
  return { class: 'normal', text: '正常', icon: 'mdi-check-circle-outline' }
}

const getBloodOxygenStatus = (bloodOxygen: number) => {
  if (!bloodOxygen) return { class: '', text: '未知', icon: 'mdi-help-circle-outline' }
  if (bloodOxygen < 95) return { class: 'danger', text: '偏低', icon: 'mdi-arrow-down-bold' }
  if (bloodOxygen < 97) return { class: 'warning', text: '注意', icon: 'mdi-alert-circle-outline' }
  return { class: 'normal', text: '正常', icon: 'mdi-check-circle-outline' }
}

const getSleepStatus = (sleepDuration: number) => {
  if (!sleepDuration) return { class: '', text: '未知', icon: 'mdi-help-circle-outline' }
  if (sleepDuration < 6) return { class: 'warning', text: '不足', icon: 'mdi-alert-circle-outline' }
  if (sleepDuration > 9) return { class: 'warning', text: '过长', icon: 'mdi-alert-circle-outline' }
  return { class: 'normal', text: '良好', icon: 'mdi-check-circle-outline' }
}

const getStepStatus = (stepCount: number) => {
  if (!stepCount) return { class: '', text: '未知', icon: 'mdi-help-circle-outline' }
  if (stepCount < 3000) return { class: 'warning', text: '不足', icon: 'mdi-alert-circle-outline' }
  if (stepCount > 10000) return { class: 'normal', text: '优秀', icon: 'mdi-star-circle-outline' }
  return { class: 'normal', text: '良好', icon: 'mdi-check-circle-outline' }
}
</script>

<template>
  <div class="family-dashboard">
    <!-- 顶部欢迎区域 -->
    <div class="welcome-section">
      <h1>欢迎回来，{{ familyInfo.UserName }}！</h1>
      <p class="subtitle">以下是您关联老人的健康数据概览</p>
    </div>

    <!-- 家属信息卡片 -->
    <div class="info-card">
      <FamilyInfo :family-info="familyInfo" />
    </div>

    <!-- 老人列表区域 -->
    <div class="elderly-section">
      <div class="section-header">
        <h2>关联老人</h2>
        <AddOld :family-id="familyInfo.UserID" @update:elderly-list="fetchFamilyInfo" />
      </div>

      <div v-if="familyInfo.elderlyList?.length === 0" class="empty-state">
        <div class="empty-icon">
          <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path>
            <circle cx="9" cy="7" r="4"></circle>
            <path d="M23 21v-2a4 4 0 0 0-3-3.87"></path>
            <path d="M16 3.13a4 4 0 0 1 0 7.75"></path>
          </svg>
        </div>
        <h3>暂无关联老人</h3>
        <p>点击上方按钮添加您关心的老人</p>
      </div>

      <div v-else class="elderly-grid">
        <div v-for="elderly in familyInfo.elderlyList" :key="elderly.UserID" class="elderly-card">
          <div class="elderly-header">
            <div class="avatar">
              {{ elderly.UserName.charAt(0) }}
            </div>
            <div class="elderly-info">
              <h3>{{ elderly.UserName }}</h3>
              <div class="elderly-meta">
                <span v-if="elderly.Age">{{ elderly.Age }}岁</span>
                <span v-if="elderly.Gender">{{ elderly.Gender }}</span>
                <span>{{ elderly.Address }}</span>
              </div>
            </div>
          </div>

          <div v-if="elderly.healthData" class="health-stats">
            <div class="stat-item">
              <div class="stat-icon heart">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                  <path d="M20.42 4.58a5.4 5.4 0 0 0-7.65 0l-.77.78-.77-.78a5.4 5.4 0 0 0-7.65 0C1.46 6.7 1.33 10.28 4 13l8 8 8-8c2.67-2.72 2.54-6.3.42-8.42z"></path>
                </svg>
              </div>
              <div class="stat-content">
                <div class="stat-value">{{ elderly.healthData.heartRate || '--' }} <small>bpm</small></div>
                <div :class="['stat-label', getHeartRateStatus(elderly.healthData.heartRate).class]">
                  <span>{{ getHeartRateStatus(elderly.healthData.heartRate).text }}</span>
                </div>
              </div>
            </div>

            <div class="stat-item">
              <div class="stat-icon oxygen">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                  <circle cx="12" cy="12" r="10"></circle>
                  <path d="M12 8v4l3 3"></path>
                </svg>
              </div>
              <div class="stat-content">
                <div class="stat-value">{{ elderly.healthData.bloodOxygen || '--' }}%</div>
                <div :class="['stat-label', getBloodOxygenStatus(elderly.healthData.bloodOxygen).class]">
                  <span>{{ getBloodOxygenStatus(elderly.healthData.bloodOxygen).text }}</span>
                </div>
              </div>
            </div>

            <div class="stat-item">
              <div class="stat-icon steps">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                  <path d="M12 19l7-7 3 3-7 7-3-3z"></path>
                  <path d="M18 13l-1.5-7.5L2 2l3.5 14.5L13 18l5-5z"></path>
                  <path d="M2 2l7.586 7.586"></path>
                  <circle cx="11" cy="11" r="2"></circle>
                </svg>
              </div>
              <div class="stat-content">
                <div class="stat-value">{{ elderly.healthData.stepCount || '0' }}</div>
                <div :class="['stat-label', getStepStatus(elderly.healthData.stepCount).class]">
                  <span>{{ getStepStatus(elderly.healthData.stepCount).text }}</span>
                </div>
              </div>
            </div>

            <div class="stat-item">
              <div class="stat-icon sleep">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                  <rect x="2" y="4" width="20" height="12" rx="2" ry="2"></rect>
                  <path d="M2 10h20M10 16v4M14 16v4"></path>
                </svg>
              </div>
              <div class="stat-content">
                <div class="stat-value">{{ elderly.healthData.sleepDuration || '0' }} <small>小时</small></div>
                <div :class="['stat-label', getSleepStatus(elderly.healthData.sleepDuration).class]">
                  <span>{{ getSleepStatus(elderly.healthData.sleepDuration).text }}</span>
                </div>
              </div>
            </div>
          </div>

          <div v-else class="no-data">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
              <circle cx="12" cy="12" r="10"></circle>
              <line x1="4.93" y1="4.93" x2="19.07" y2="19.07"></line>
            </svg>
            <p>暂无健康数据</p>
          </div>

          <div v-if="elderly.healthData" class="update-time">
            最后更新: {{ new Date(elderly.healthData.recordedAt).toLocaleString() }}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.family-dashboard {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  color: #333;
}

.welcome-section {
  margin-bottom: 30px;
  text-align: center;
}

.welcome-section h1 {
  font-size: 2.2rem;
  color: #2c3e50;
  margin-bottom: 8px;
}

.welcome-section .subtitle {
  font-size: 1.1rem;
  color: #7f8c8d;
}

.info-card {
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  padding: 20px;
  margin-bottom: 30px;
}

.elderly-section {
  margin-top: 30px;
}

.section-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.section-header h2 {
  font-size: 1.5rem;
  color: #2c3e50;
}

.empty-state {
  text-align: center;
  padding: 40px 20px;
  background: #f8f9fa;
  border-radius: 12px;
  margin-top: 20px;
}

.empty-icon {
  margin-bottom: 15px;
  color: #95a5a6;
}

.empty-state h3 {
  font-size: 1.3rem;
  color: #2c3e50;
  margin-bottom: 8px;
}

.empty-state p {
  color: #7f8c8d;
}

.elderly-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 20px;
  margin-top: 20px;
}

.elderly-card {
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  padding: 20px;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.elderly-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
}

.elderly-header {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.avatar {
  width: 50px;
  height: 50px;
  background: #3498db;
  color: white;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 1.5rem;
  font-weight: bold;
  margin-right: 15px;
}

.elderly-info h3 {
  font-size: 1.2rem;
  color: #2c3e50;
  margin-bottom: 5px;
}

.elderly-meta {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  color: #7f8c8d;
  font-size: 0.8rem;
}

.health-stats {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 15px;
}

.stat-item {
  display: flex;
  align-items: center;
}

.stat-icon {
  width: 40px;
  height: 40px;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 12px;
}

.stat-icon.heart {
  background: rgba(231, 76, 60, 0.1);
  color: #e74c3c;
}

.stat-icon.oxygen {
  background: rgba(52, 152, 219, 0.1);
  color: #3498db;
}

.stat-icon.steps {
  background: rgba(46, 204, 113, 0.1);
  color: #2ecc71;
}

.stat-icon.sleep {
  background: rgba(155, 89, 182, 0.1);
  color: #9b59b6;
}

.stat-content {
  flex: 1;
}

.stat-value {
  font-size: 1.1rem;
  font-weight: bold;
  color: #2c3e50;
}

.stat-value small {
  font-size: 0.8rem;
  font-weight: normal;
}

.stat-label {
  font-size: 0.8rem;
  padding: 2px 8px;
  border-radius: 10px;
  display: inline-block;
  margin-top: 2px;
}

.stat-label.normal {
  background: rgba(46, 204, 113, 0.1);
  color: #2ecc71;
}

.stat-label.warning {
  background: rgba(241, 196, 15, 0.1);
  color: #f1c40f;
}

.stat-label.danger {
  background: rgba(231, 76, 60, 0.1);
  color: #e74c3c;
}

.no-data {
  text-align: center;
  padding: 30px 0;
  color: #95a5a6;
}

.no-data svg {
  margin-bottom: 10px;
}

.update-time {
  margin-top: 20px;
  font-size: 0.8rem;
  color: #95a5a6;
  text-align: center;
  padding-top: 10px;
  border-top: 1px dashed #eee;
}

@media (max-width: 768px) {
  .elderly-grid {
    grid-template-columns: 1fr;
  }

  .welcome-section h1 {
    font-size: 1.8rem;
  }
}
</style>