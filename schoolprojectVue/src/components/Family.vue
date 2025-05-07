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
  userId: 0,
  userName: '',
  password: '',
  userType: '家属' as const,
  phoneNumber: '',
  createdAt: new Date(),
  age: undefined,
  gender: undefined,
  email: '',
  address: '',
  elderlyList: []
})

// 获取老人的健康数据
const fetchElderlyHealthData = async (elderly: User) => {
  try {
    const response = await getElderlyHealthData(elderly.userId)
    console.log('获取老人健康数据响应:', response)
    
    if (response.status === 200 && response.data) {
      return response.data
    }
  } catch (error) {
    console.error(`获取老人${elderly.userName}的健康数据失败:`, error)
  }
  return undefined
}

const fetchFamilyInfo = async () => {
  try {
    const response = await getFamilyInfo()
    console.log('获取家属信息响应：', response)

    if (response.status === 200 && response.data) {
      // 获取每个老人的健康数据
      const elderlyList = await Promise.all(
        response.data.elderlyList.map(async (elderly) => ({
          ...elderly,
          healthData: await fetchElderlyHealthData(elderly)
        }))
      )
      
      familyInfo.value = {
        ...familyInfo.value,
        elderlyList
      }
    }
  } catch (error: any) {
    console.error('获取家属信息失败:', error)
    if (error.message === '未获取到家属ID') {
      ElMessage.error('请先登录')
      // 可以添加重定向到登录页面
      // router.push('/login')
    } else {
      ElMessage.error(error.message || '获取家属信息失败')
    }
  }
}

onMounted(async () => {
  try {
    userStore.initUserFromStorage()
    const user = userStore.user
    console.log('从 store 获取的用户信息:', user)

    if (user && user.userId) {
      // 创建一个新的对象，避免响应式问题
      const userInfo = {
        userId: Number(user.userId),
        userName: String(user.userName),
        password: String(user.password),
        userType: '家属' as const,
        phoneNumber: String(user.phoneNumber || ''),
        email: String(user.email || ''),
        address: String(user.address || ''),
        age: user.age ? Number(user.age) : null,
        gender: user.gender || null,
        createdAt: new Date(user.createdAt),
        elderlyList: []
      }
      
      console.log('准备设置的用户信息:', userInfo)
      familyInfo.value = userInfo
      
      await fetchFamilyInfo()
    } else {
      ElMessage.error('未获取到有效的用户信息，请重新登录')
      // 可以添加重定向到登录页面
      // router.push('/login')
    }
  } catch (error: any) {
    console.error('初始化家属页面失败:', error)
    ElMessage.error(error.message || '初始化家属页面失败')
  }
})

// 健康数据状态判断
const getHeartRateStatus = (heartRate: number) => {
  if (!heartRate) return { class: '', text: '未知' }
  if (heartRate < 60) return { class: 'warning', text: '偏低' }
  if (heartRate > 100) return { class: 'danger', text: '偏高' }
  return { class: 'normal', text: '正常' }
}

const getBloodOxygenStatus = (bloodOxygen: number) => {
  if (!bloodOxygen) return { class: '', text: '未知' }
  if (bloodOxygen < 95) return { class: 'danger', text: '偏低' }
  if (bloodOxygen < 97) return { class: 'warning', text: '注意' }
  return { class: 'normal', text: '正常' }
}
</script>

<template>
  <div class="family-container">
    <h1>家属主页</h1>
    <FamilyInfo :family-info="familyInfo" />
    <AddOld :family-id="familyInfo.userId" @update:elderly-list="fetchFamilyInfo">
      <div v-if="familyInfo.elderlyList?.length === 0" class="empty-list">
        暂无关联老人
      </div>
      <div v-else class="elderly-list">
        <div v-for="elderly in familyInfo.elderlyList" :key="elderly.userId" class="elderly-item">
          <div class="elderly-basic-info">
            <span class="elderly-name">{{ elderly.userName }}</span>
            <span class="elderly-age">{{ elderly.age || '未知' }}岁</span>
          </div>
          <div class="elderly-health-info" v-if="elderly.healthData">
            <div class="health-item">
              <span class="label">心率:</span>
              <span :class="['value', getHeartRateStatus(elderly.healthData.heart_rate).class]">
                {{ elderly.healthData.heart_rate }} 
                ({{ getHeartRateStatus(elderly.healthData.heart_rate).text }})
              </span>
            </div>
            <div class="health-item">
              <span class="label">步数:</span>
              <span class="value">{{ elderly.healthData.step_count || 0 }}</span>
            </div>
            <div class="health-item">
              <span class="label">睡眠:</span>
              <span class="value">{{ elderly.healthData.sleep_duration || 0 }}小时</span>
            </div>
            <div class="health-item">
              <span class="label">血氧:</span>
              <span :class="['value', getBloodOxygenStatus(elderly.healthData.blood_oxygen).class]">
                {{ elderly.healthData.blood_oxygen }}% 
                ({{ getBloodOxygenStatus(elderly.healthData.blood_oxygen).text }})
              </span>
            </div>
            <div class="health-item">
              <span class="label">更新时间:</span>
              <span class="value">{{ new Date(elderly.healthData.recorded_at).toLocaleString() }}</span>
            </div>
          </div>
          <div v-else class="no-health-data">
            暂无健康数据
          </div>
        </div>
      </div>
    </AddOld>
  </div>
</template>

<style scoped>
.family-container {
  padding: 20px;
}

.empty-list {
  text-align: center;
  color: #999;
  padding: 20px;
}

.elderly-list {
  margin-top: 10px;
}

.elderly-item {
  display: flex;
  flex-direction: column;
  gap: 10px;
  padding: 15px;
  border-bottom: 1px solid #eee;
}

.elderly-basic-info {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.elderly-name {
  font-size: 1.1em;
  font-weight: bold;
}

.elderly-health-info {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 10px;
  background: #f8f9fa;
  padding: 10px;
  border-radius: 6px;
}

.health-item {
  display: flex;
  align-items: center;
  gap: 5px;
}

.health-item .label {
  color: #666;
  font-size: 0.9em;
}

.health-item .value {
  font-weight: bold;
}

.health-item .value.normal {
  color: #28a745;
}

.health-item .value.warning {
  color: #ffc107;
}

.health-item .value.danger {
  color: #dc3545;
}

.no-health-data {
  text-align: center;
  color: #999;
  padding: 10px;
  background: #f8f9fa;
  border-radius: 6px;
}
</style>