<script setup>
import { ref } from 'vue'

const elderlyInfo = ref({
  name: '',
  age: '',
  healthStatus: '',
  emergencyContact: ''
})
</script>

<template>
  <div class="elderly-container">
    <h1>老人主页</h1>
    <div class="info-card">
      <h2>个人信息</h2>
      <div class="info-item">
        <label>姓名：</label>
        <span>{{ elderlyInfo.name }}</span>
      </div>
      <div class="info-item">
        <label>年龄：</label>
        <span>{{ elderlyInfo.age }}</span>
      </div>
      <div class="info-item">
        <label>健康状况：</label>
        <span>{{ elderlyInfo.healthStatus }}</span>
      </div>
      <div class="info-item">
        <label>紧急联系人：</label>
        <span>{{ elderlyInfo.emergencyContact }}</span>
      </div>
    </div>
  </div>
</template>

<style scoped>
.elderly-container {
  padding: 20px;
}

.info-card {
  background: #fff;
  border-radius: 8px;
  padding: 20px;
  margin-top: 20px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

.info-item {
  margin: 10px 0;
}

.info-item label {
  font-weight: bold;
  margin-right: 10px;
}
</style>