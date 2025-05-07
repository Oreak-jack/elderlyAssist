<script setup>
import { ref, computed } from 'vue'
import { useUserStore } from '@/stores/userStore'
import { ElMessage } from 'element-plus'

const store = useUserStore()
const elderlyInfo = computed(() => store.getCurrentUser)

const messages = ref([
  { id: 1, sender: '女儿', content: '爸爸，记得按时吃药哦！', time: '10:30', unread: true },
  { id: 2, sender: '儿子', content: '周末我带孙子来看您', time: '昨天', unread: false },
  { id: 3, sender: '老伴', content: '今天天气冷，多穿点衣服', time: '周三', unread: false }
])

const newMessage = ref('')
const activeTab = ref('messages')

const sendMessage = () => {
  if (newMessage.value.trim()) {
    messages.value.unshift({
      id: Date.now(),
      sender: '我',
      content: newMessage.value,
      time: '刚刚',
      unread: false
    })
    newMessage.value = ''
    ElMessage.success('消息已发送')
  }
}

const familyMembers = ref([
  { id: 1, name: '女儿', relation: '女儿', avatar: '👧' },
  { id: 2, name: '儿子', relation: '儿子', avatar: '👦' },
  { id: 3, name: '老伴', relation: '配偶', avatar: '👵' }
])

const markAsRead = (id) => {
  const message = messages.value.find(m => m.id === id)
  if (message) message.unread = false
}
</script>

<template>
  <div class="family-interaction">
    <h2 class="section-title">家属关怀</h2>

    <div class="tabs">
      <button
          @click="activeTab = 'messages'"
          :class="{ active: activeTab === 'messages' }"
      >
        消息中心
      </button>
      <button
          @click="activeTab = 'family'"
          :class="{ active: activeTab === 'family' }"
      >
        家庭成员
      </button>
    </div>

    <!-- 消息中心 -->
    <div v-if="activeTab === 'messages'" class="messages-container">
      <div class="messages-list">
        <div
            v-for="message in messages"
            :key="message.id"
            class="message-item"
            :class="{ unread: message.unread }"
            @click="markAsRead(message.id)"
        >
          <div class="message-avatar">{{ message.sender === '我' ? '👴' : '👪' }}</div>
          <div class="message-content">
            <div class="message-header">
              <span class="sender">{{ message.sender }}</span>
              <span class="time">{{ message.time }}</span>
            </div>
            <p class="message-text">{{ message.content }}</p>
          </div>
          <span v-if="message.unread" class="unread-badge"></span>
        </div>
      </div>

      <div class="message-input">
        <input
            v-model="newMessage"
            placeholder="输入消息..."
            @keyup.enter="sendMessage"
        >
        <button @click="sendMessage">发送</button>
      </div>
    </div>

    <!-- 家庭成员 -->
    <div v-if="activeTab === 'family'" class="family-members">
      <div
          v-for="member in familyMembers"
          :key="member.id"
          class="member-card"
      >
        <div class="member-avatar">{{ member.avatar }}</div>
        <div class="member-info">
          <h3>{{ member.name }}</h3>
          <p>{{ member.relation }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.family-interaction {
  background: white;
  border-radius: 12px;
  padding: 1.5rem;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
}

.section-title {
  font-size: 1.5rem;
  color: #FF9800;
  margin-bottom: 1.5rem;
  padding-bottom: 0.5rem;
  border-bottom: 2px solid #FFE0B2;
}

.tabs {
  display: flex;
  margin-bottom: 1.5rem;
  border-bottom: 1px solid #eee;
}

.tabs button {
  padding: 0.8rem 1.5rem;
  background: none;
  border: none;
  cursor: pointer;
  font-size: 1rem;
  color: #666;
  position: relative;
}

.tabs button.active {
  color: #FF9800;
  font-weight: bold;
}

.tabs button.active::after {
  content: '';
  position: absolute;
  bottom: -1px;
  left: 0;
  right: 0;
  height: 3px;
  background: #FF9800;
}

/* 消息列表样式 */
.messages-container {
  display: flex;
  flex-direction: column;
  height: 400px;
}

.messages-list {
  flex: 1;
  overflow-y: auto;
  padding-right: 0.5rem;
}

.message-item {
  display: flex;
  padding: 1rem;
  margin-bottom: 0.5rem;
  border-radius: 8px;
  background: #FFF9E6;
  position: relative;
  cursor: pointer;
  transition: all 0.3s ease;
}

.message-item:hover {
  background: #FFF3CC;
}

.message-item.unread {
  background: #FFF3CC;
  border-left: 4px solid #FF9800;
}

.message-avatar {
  font-size: 1.8rem;
  margin-right: 1rem;
}

.message-content {
  flex: 1;
}

.message-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 0.5rem;
}

.sender {
  font-weight: bold;
  color: #333;
}

.time {
  color: #999;
  font-size: 0.9rem;
}

.message-text {
  margin: 0;
  color: #555;
  font-size: 1.1rem;
}

.unread-badge {
  position: absolute;
  top: 1rem;
  right: 1rem;
  width: 10px;
  height: 10px;
  background: #FF5722;
  border-radius: 50%;
}

/* 消息输入框 */
.message-input {
  display: flex;
  margin-top: 1rem;
  padding-top: 1rem;
  border-top: 1px solid #eee;
}

.message-input input {
  flex: 1;
  padding: 0.8rem;
  border: 1px solid #ddd;
  border-radius: 4px 0 0 4px;
  font-size: 1rem;
}

.message-input button {
  padding: 0 1.5rem;
  background: #FF9800;
  color: white;
  border: none;
  border-radius: 0 4px 4px 0;
  cursor: pointer;
  transition: background 0.3s;
}

.message-input button:hover {
  background: #F57C00;
}

/* 家庭成员样式 */
.family-members {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(180px, 1fr));
  gap: 1rem;
}

.member-card {
  display: flex;
  padding: 1.2rem;
  background: #FFF9E6;
  border-radius: 8px;
  transition: all 0.3s ease;
  align-items: center;
}

.member-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.member-avatar {
  font-size: 2.2rem;
  margin-right: 1rem;
}

.member-info {
  flex: 1;
}

.member-info h3 {
  margin: 0 0 0.2rem;
  color: #333;
  font-size: 1.1rem;
}

.member-info p {
  margin: 0;
  color: #666;
  font-size: 0.9rem;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .family-members {
    grid-template-columns: 1fr;
  }

  .messages-container {
    height: 300px;
  }
}
</style>