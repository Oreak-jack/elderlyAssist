<script setup>
import { ref, onMounted, computed } from 'vue'
import { getReminders, addReminder, deleteReminder, updateReminder } from '@/api/remindersApi'
import { ElMessage } from 'element-plus'

const props = defineProps({
  elderlyInfo: {
    type: Object,
    required: true
  }
})

const reminders = ref([])
const showAddForm = ref(false)
const newReminder = ref({
  userId: props.elderlyInfo.userId,
  reminderTime: '',
  content: '',
  iconClass: '⏰',
  completed: false,
  important: false,
  createdAt: new Date(),
  updatedAt: new Date()
})

// 格式化时间显示
const formatTime = (isoTime) => {
  if (!isoTime) return ''
  const date = new Date(isoTime)
  return `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, '0')}-${String(date.getDate()).padStart(2, '0')} ${String(date.getHours()).padStart(2, '0')}:${String(date.getMinutes()).padStart(2, '0')}`
}

// 获取提醒列表
const fetchReminders = async () => {
  try {
    console.log("开始获取提醒列表，用户ID:", props.elderlyInfo.userId)
    const userId = props.elderlyInfo.userId
    const response = await getReminders(userId)
    console.log("获取到的提醒列表:", response)
    reminders.value = response.data
  } catch (error) {
    console.error('获取提醒列表失败:', error)
    ElMessage.error('获取提醒列表失败')
  }
}

// 添加新提醒
const handleAddReminder = async () => {
  try {
    if (!newReminder.value.reminderTime || !newReminder.value.content) {
      ElMessage.warning('请填写完整的提醒信息')
      return
    }

    // 添加日志
    console.log('准备发送的数据：', newReminder.value)
    
    const response = await addReminder(newReminder.value)
    console.log('服务器响应：', response)
    
    await fetchReminders()
    showAddForm.value = false
    ElMessage.success('添加成功')
    
    // 将HH:mm格式转换为ISO格式
    const [hours, minutes] = newReminder.value.reminderTime.split(':')
    const hoursInt = parseInt(hours)
    const minutesInt = parseInt(minutes)
    
    // 确保小时数在0-23之间
    if (hoursInt >= 0 && hoursInt <= 23 && minutesInt >= 0 && minutesInt <= 59) {
      const newDate = new Date()
      newDate.setHours(hoursInt)
      newDate.setMinutes(minutesInt)
      newDate.setSeconds(0)
      newDate.setMilliseconds(0)
      
      // 格式化为后端要求的ISO格式(yyyy-MM-ddTHH:mm:ss)
      const year = newDate.getFullYear()
      const month = String(newDate.getMonth() + 1).padStart(2, '0')
      const day = String(newDate.getDate()).padStart(2, '0')
      const formattedHours = String(hoursInt).padStart(2, '0')
      const formattedMinutes = String(minutesInt).padStart(2, '0')
      newReminder.value.reminderTime = `${year}-${month}-${day}T${formattedHours}:${formattedMinutes}:00`
      
      await addReminder(newReminder.value)
      await fetchReminders()
      showAddForm.value = false
      
      // 重置表单
      newReminder.value = {
        reminderId: 0,
        userId: props.elderlyInfo.userId,
        reminderTime: '',
        content: '',
        iconClass: '⏰',
        completed: false,
        important: false,
        createdAt: new Date(),
        updatedAt: new Date()
      }
    } else {
      alert('请输入有效的24小时制时间（00:00-23:59）')
      return
    }
  } catch (error) {
    console.error('添加提醒失败:', error)
    ElMessage.error(`添加失败: ${error.message || '未知错误'}`)
  }
}

// 删除提醒
const handleDeleteReminder = async (reminderId) => {
  try {
    if (!reminderId || typeof reminderId !== 'number') {
    console.log("reminderId",reminderId);
    
      console.error('无效的提醒ID');
      return;
    }
    
    if (!confirm('确定要删除这条提醒吗？')) {
      return;
    }
    
    await deleteReminder(reminderId);
    await fetchReminders();
  } catch (error) {
    console.error('删除提醒失败:', error);
  }
}

// 标记提醒为已完成
const completeReminder = async (reminder) => {
  // console.log("已完成里面的 reminder",reminder)
  try {
    await updateReminder({
      ...reminder,
      completed: true
    })
    await fetchReminders()
  } catch (error) {
    console.error('更新提醒状态失败:', error)
  }
}

onMounted(() => {
  fetchReminders()
})
</script>

<template>
  <div class="reminders-container">
    <header class="reminders-header">
      <h1>提醒事项</h1>
      <button class="add-btn" @click="showAddForm = true">添加提醒 ➕</button>
    </header>

    <!-- 添加提醒表单 -->
    <div v-if="showAddForm" class="add-form">
      <h2>新增提醒</h2>
      <div class="form-group">
        <label>时间</label>
        <input type="time" v-model="newReminder.reminderTime" required>
      </div>
      <div class="form-group">
        <label>内容</label>
        <input type="text" v-model="newReminder.content" placeholder="请输入提醒内容" required>
      </div>
      <div class="form-group">
        <label>优先级</label>
        <select v-model="newReminder.important">
          <option :value="true">高</option>
          <option :value="false">低</option>
        </select>
      </div>
      <div class="form-actions">
        <button class="cancel-btn" @click="showAddForm = false">取消</button>
        <button class="submit-btn" @click="handleAddReminder">保存</button>
      </div>
    </div>

    <!-- 提醒列表 -->
    <section class="reminder-section">
      <div class="reminder-list">
        <div v-for="reminder in reminders" :key="reminder.reminderId" class="reminder-item" :class="{ 'completed': reminder.completed }">
          <div class="reminder-info">
            <span class="reminder-time">🕒 {{ formatTime(reminder.reminderTime) }}</span>
            <span class="reminder-content">{{ reminder.content }} {{ reminder.iconClass }}</span>
          </div>
          <div class="reminder-actions">
            <button 
              v-if="!reminder.completed"
              @click="completeReminder(reminder)" 
              class="complete-btn"
            >
              已完成 ✅
            </button>
            <span v-else class="completed-text">已完成 ✅</span>
            <button 
              @click="handleDeleteReminder(reminder.reminderId)" 
              class="delete-btn"
            >
              删除 🗑️
            </button>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<style scoped>
.reminders-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 2rem;
  font-family: 'Microsoft YaHei', sans-serif;
}

.reminders-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 2rem;
}

.reminders-header h1 {
  font-size: 2rem;
  color: #333;
  margin: 0;
}

.add-btn {
  background: #4a90e2;
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1rem;
}

.add-form {
  background: white;
  border-radius: 12px;
  padding: 1.5rem;
  margin-bottom: 2rem;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
}

.add-form h2 {
  margin-top: 0;
  margin-bottom: 1.5rem;
  color: #333;
}

.form-group {
  margin-bottom: 1rem;
}

.form-group label {
  display: block;
  margin-bottom: 0.5rem;
  color: #666;
}

.form-group input,
.form-group select {
  width: 100%;
  padding: 0.5rem;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 1rem;
}

.form-actions {
  display: flex;
  justify-content: flex-end;
  gap: 1rem;
  margin-top: 1.5rem;
}

.cancel-btn {
  background: #f5f5f5;
  color: #666;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
}

.submit-btn {
  background: #4CAF50;
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
}

.reminder-section {
  background: white;
  border-radius: 12px;
  padding: 1.5rem;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
}

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

.reminder-actions {
  display: flex;
  gap: 0.5rem;
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

.delete-btn {
  background: #f44336;
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

@media (max-width: 768px) {
  .reminders-container {
    padding: 1rem;
  }

  .reminder-actions {
    flex-direction: column;
    gap: 0.5rem;
  }
}
</style>