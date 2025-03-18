<script setup>
import { ref, onMounted } from 'vue'

// 模拟数据 - 实际项目中应该从API获取
const reminders = ref([
  { id: 1, time: '08:00', content: '吃降压药', icon: '💊', completed: false, priority: 1 },
  { id: 2, time: '12:00', content: '午餐时间', icon: '🍽', completed: false, priority: 2 },
  { id: 3, time: '14:00', content: '散步 30 分钟', icon: '🚶', completed: false, priority: 3 },
  { id: 4, time: '20:30', content: '休息提醒', icon: '🛏', completed: false, priority: 4 }
])

const healthData = ref({
  heartRate: { value: 72, unit: 'bpm', status: 'normal' },
  bloodPressure: { value: '125/80', unit: 'mmHg', status: 'normal' },
  steps: { value: 3200, unit: '步', status: 'normal' },
  temperature: { value: 22, unit: '°C', status: 'normal' }
})

const familyContacts = ref([
  { id: 1, name: '张伟', relation: '儿子', avatar: '👩‍👦', online: true },
  { id: 2, name: '李护士', relation: '养老护理员', avatar: '👩‍⚕️', online: true }
])

const familyMessages = ref([
  { id: 1, sender: '张伟', content: '爸，记得今天喝多点水！❤️', time: '10:30' }
])

// 标记提醒为已完成
const completeReminder = (id) => {
  const reminder = reminders.value.find(r => r.id === id)
  if (reminder) {
    reminder.completed = true
  }
}

// 拨打电话/视频
const callFamily = (contact) => {
  alert(`正在连接${contact.relation} ${contact.name}...`)
  // 实际项目中应该调用视频/语音通话API
}

// 播放语音消息
const playVoiceMessage = (message) => {
  alert(`播放来自${message.sender}的语音消息`)
  // 实际项目中应该调用语音播放API
}

// 模拟获取数据
onMounted(() => {
  // 实际项目中应该从API获取数据
  console.log('主页组件已加载')
})
</script>

<template>
  <div class="home-container">
    <header class="home-header">
      <h1>欢迎回来，老王</h1>
      <p class="date">{{ new Date().toLocaleDateString('zh-CN', { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' }) }}</p>
    </header>

    <main class="home-content">
      <!-- 今日提醒（核心区） -->
      <section class="reminder-section">
        <h2>📅 今日提醒</h2>
        <div class="reminder-list">
          <div v-for="reminder in reminders" :key="reminder.id" class="reminder-item" :class="{ 'completed': reminder.completed }">
            <div class="reminder-info">
              <span class="reminder-time">🕒 {{ reminder.time }}</span>
              <span class="reminder-content">{{ reminder.content }} {{ reminder.icon }}</span>
            </div>
            <button 
              v-if="!reminder.completed" 
              @click="completeReminder(reminder.id)" 
              class="complete-btn"
            >
              已完成 ✅
            </button>
            <span v-else class="completed-text">已完成 ✅</span>
          </div>
        </div>
      </section>

      <!-- 健康数据（健康监测区） -->
      <section class="health-section">
        <h2>📊 健康数据</h2>
        <div class="health-grid">
          <div class="health-card">
            <div class="health-icon">❤️</div>
            <div class="health-data">
              <span class="health-label">心率</span>
              <span class="health-value" :class="healthData.heartRate.status">
                {{ healthData.heartRate.value }} {{ healthData.heartRate.unit }}
              </span>
              <span v-if="healthData.heartRate.status === 'normal'" class="health-status">正常 ✅</span>
            </div>
          </div>

          <div class="health-card">
            <div class="health-icon">🩸</div>
            <div class="health-data">
              <span class="health-label">血压</span>
              <span class="health-value" :class="healthData.bloodPressure.status">
                {{ healthData.bloodPressure.value }} {{ healthData.bloodPressure.unit }}
              </span>
              <span v-if="healthData.bloodPressure.status === 'normal'" class="health-status">正常 ✅</span>
            </div>
          </div>

          <div class="health-card">
            <div class="health-icon">🏃‍♂️</div>
            <div class="health-data">
              <span class="health-label">今日步数</span>
              <span class="health-value">
                {{ healthData.steps.value }} {{ healthData.steps.unit }}
              </span>
            </div>
          </div>

          <div class="health-card">
            <div class="health-icon">🌡</div>
            <div class="health-data">
              <span class="health-label">室内温度</span>
              <span class="health-value">
                {{ healthData.temperature.value }} {{ healthData.temperature.unit }}
              </span>
            </div>
          </div>
        </div>
      </section>

      <!-- 家属关怀（情感互动区） -->
      <section class="family-section">
        <h2>👪 家属关怀</h2>
        
        <div class="family-contacts">
          <h3>📞 快速联系家人</h3>
          <div class="contact-list">
            <div 
              v-for="contact in familyContacts" 
              :key="contact.id" 
              class="contact-item"
              @click="callFamily(contact)"
            >
              <div class="contact-avatar">{{ contact.avatar }}</div>
              <div class="contact-info">
                <span class="contact-name">{{ contact.relation }}: {{ contact.name }}</span>
                <span v-if="contact.online" class="contact-status online">在线 ✅</span>
                <span v-else class="contact-status offline">离线</span>
              </div>
            </div>
          </div>
        </div>

        <div class="family-messages">
          <h3>💌 家人留言</h3>
          <div class="message-list">
            <div v-for="message in familyMessages" :key="message.id" class="message-item">
              <div class="message-header">
                <span class="message-sender">📝 {{ message.sender }}</span>
                <span class="message-time">{{ message.time }}</span>
              </div>
              <div class="message-content">{{ message.content }}</div>
              <button @click="playVoiceMessage(message)" class="voice-btn">🔊 播放语音</button>
            </div>
          </div>
        </div>
      </section>
    </main>
  </div>
</template>

<style scoped>
.home-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 2rem;
  font-family: 'Microsoft YaHei', sans-serif;
}

.home-header {
  margin-bottom: 2rem;
  text-align: center;
}

.home-header h1 {
  font-size: 2rem;
  color: #333;
  margin-bottom: 0.5rem;
}

.date {
  color: #666;
  font-size: 1.1rem;
}

.home-content {
  display: grid;
  grid-template-columns: 1fr;
  gap: 2rem;
}

section {
  background: white;
  border-radius: 12px;
  padding: 1.5rem;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
}

section h2 {
  font-size: 1.5rem;
  color: #333;
  margin-bottom: 1.5rem;
  border-bottom: 2px solid #f0f0f0;
  padding-bottom: 0.5rem;
}

section h3 {
  font-size: 1.2rem;
  color: #555;
  margin-bottom: 1rem;
}

/* 今日提醒样式 */
.reminder-list {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.reminder-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem;
  background: #f9f9f9;
  border-radius: 8px;
  border-left: 4px solid #4a90e2;
}

.reminder-item.completed {
  border-left-color: #4CAF50;
  opacity: 0.7;
}

.reminder-time {
  font-weight: bold;
  margin-right: 1rem;
}

.complete-btn {
  background: #4CAF50;
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  font-size: 0.9rem;
}

.completed-text {
  color: #4CAF50;
  font-weight: bold;
}

/* 健康数据样式 */
.health-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 1.5rem;
}

.health-card {
  display: flex;
  align-items: center;
  padding: 1rem;
  background: #f9f9f9;
  border-radius: 8px;
}

.health-icon {
  font-size: 2rem;
  margin-right: 1rem;
}

.health-data {
  display: flex;
  flex-direction: column;
}

.health-label {
  font-size: 0.9rem;
  color: #666;
}

.health-value {
  font-size: 1.2rem;
  font-weight: bold;
  color: #333;
}

.health-value.warning {
  color: #ff9800;
}

.health-value.danger {
  color: #f44336;
}

.health-status {
  font-size: 0.8rem;
  color: #4CAF50;
}

/* 家属关怀样式 */
.family-contacts, .family-messages {
  margin-bottom: 2rem;
}

.contact-list {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 1rem;
}

.contact-item {
  display: flex;
  align-items: center;
  padding: 1rem;
  background: #f9f9f9;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.2s;
}

.contact-item:hover {
  background: #f0f0f0;
}

.contact-avatar {
  font-size: 2rem;
  margin-right: 1rem;
}

.contact-info {
  display: flex;
  flex-direction: column;
}

.contact-name {
  font-weight: bold;
}

.contact-status {
  font-size: 0.8rem;
}

.contact-status.online {
  color: #4CAF50;
}

.contact-status.offline {
  color: #999;
}

.message-list {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.message-item {
  padding: 1rem;
  background: #f9f9f9;
  border-radius: 8px;
  border-left: 4px solid #4a90e2;
}

.message-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 0.5rem;
}

.message-sender {
  font-weight: bold;
}

.message-time {
  color: #999;
  font-size: 0.9rem;
}

.message-content {
  margin-bottom: 0.5rem;
  line-height: 1.5;
}

.voice-btn {
  background: #4a90e2;
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  font-size: 0.9rem;
  margin-top: 0.5rem;
}

/* 响应式布局 */
@media (min-width: 768px) {
  .home-content {
    grid-template-columns: 1fr 1fr;
  }
  
  .reminder-section {
    grid-column: 1 / -1;
  }
}

@media (min-width: 1024px) {
  .home-content {
    grid-template-columns: 1fr 1fr 1fr;
  }
  
  .reminder-section {
    grid-column: 1 / -1;
  }
}
</style>