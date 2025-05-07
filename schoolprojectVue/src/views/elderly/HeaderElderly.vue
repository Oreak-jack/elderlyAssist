<script setup>
import { ref } from 'vue'

// 固定用户名为"张爷爷"
const userName = ref("张爷爷")

// 获取当前日期
const currentDate = ref(new Date().toLocaleDateString('zh-CN', {
  weekday: 'long',
  year: 'numeric',
  month: 'long',
  day: 'numeric'
}))
</script>

<template>
  <header class="home-header">
    <h1>欢迎，{{ userName }}</h1>
    <p class="date">{{ currentDate }}</p>
  </header>
</template>

<style scoped>
.home-header {
  margin-bottom: 2rem;
  text-align: center;
  padding: 1.5rem;
  background: linear-gradient(135deg, #a8edea 0%, #fed6e3 100%);
  color: #333;
  border-radius: 0 0 12px 12px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);
}

.home-header h1 {
  font-size: 2.2rem;
  margin-bottom: 0.8rem;
  color: #2c3e50;
}

.date {
  color: #555;
  font-size: 1.2rem;
  margin: 0;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .home-header h1 {
    font-size: 1.8rem;
  }

  .date {
    font-size: 1rem;
  }
}
</style>