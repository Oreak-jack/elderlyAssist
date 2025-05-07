<script setup>
import { ref } from 'vue'

const healthData = ref({
  heartRate: 72,
  bloodPressure: '120/80',
  steps: 4582,
  sleep: '7小时32分',
  lastUpdate: '今天 08:30'
})

const healthTips = ref([
  '您的血压在正常范围内，继续保持',
  '今日步数达标，建议继续保持适量运动',
  '昨晚睡眠质量良好，建议保持规律作息'
])
</script>

<template>
  <div class="health-container">
    <h2 class="section-title">健康数据</h2>

    <div class="health-summary">
      <div class="health-card">
        <div class="health-icon">❤️</div>
        <div class="health-info">
          <h3>心率</h3>
          <p>{{ healthData.heartRate }} <small>次/分钟</small></p>
        </div>
      </div>

      <div class="health-card">
        <div class="health-icon">🩺</div>
        <div class="health-info">
          <h3>血压</h3>
          <p>{{ healthData.bloodPressure }} <small>mmHg</small></p>
        </div>
      </div>

      <div class="health-card">
        <div class="health-icon">🚶</div>
        <div class="health-info">
          <h3>步数</h3>
          <p>{{ healthData.steps.toLocaleString() }} <small>步</small></p>
        </div>
      </div>

      <div class="health-card">
        <div class="health-icon">😴</div>
        <div class="health-info">
          <h3>睡眠</h3>
          <p>{{ healthData.sleep }}</p>
        </div>
      </div>
    </div>

    <div class="health-tips">
      <h3>健康建议</h3>
      <ul>
        <li v-for="(tip, index) in healthTips" :key="index">{{ tip }}</li>
      </ul>
    </div>

    <div class="last-update">
      最后更新: {{ healthData.lastUpdate }}
    </div>
  </div>
</template>

<style scoped>
.health-container {
  background: white;
  border-radius: 12px;
  padding: 1.5rem;
}

.section-title {
  font-size: 1.5rem;
  color: #2196F3;
  margin-bottom: 1.5rem;
  padding-bottom: 0.5rem;
  border-bottom: 2px solid #E3F2FD;
}

.health-summary {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(150px, 1fr));
  gap: 1rem;
  margin-bottom: 1.5rem;
}

.health-card {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 1rem;
  background: #E3F2FD;
  border-radius: 8px;
  text-align: center;
}

.health-icon {
  font-size: 2rem;
  margin-bottom: 0.5rem;
}

.health-info h3 {
  margin: 0.5rem 0;
  color: #0D47A1;
  font-size: 1.1rem;
}

.health-info p {
  margin: 0;
  font-size: 1.3rem;
  font-weight: bold;
  color: #1565C0;
}

.health-info small {
  font-size: 0.8rem;
  color: #666;
  font-weight: normal;
}

.health-tips {
  background: #E8F5E9;
  padding: 1rem;
  border-radius: 8px;
  margin-bottom: 1rem;
}

.health-tips h3 {
  color: #2E7D32;
  margin-top: 0;
}

.health-tips ul {
  padding-left: 1.5rem;
  margin-bottom: 0;
}

.health-tips li {
  margin-bottom: 0.5rem;
  color: #555;
}

.last-update {
  text-align: right;
  font-size: 0.9rem;
  color: #999;
}

@media (max-width: 768px) {
  .health-summary {
    grid-template-columns: 1fr 1fr;
  }
}
</style>