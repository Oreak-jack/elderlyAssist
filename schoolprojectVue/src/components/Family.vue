<script setup>
import { ref } from 'vue'

const familyInfo = ref({
  name: '',
  relationship: '',
  phoneNumber: '',
  elderlyList: []
})
</script>

<template>
  <div class="family-container">
    <h1>家属主页</h1>
    <div class="info-card">
      <h2>个人信息</h2>
      <div class="info-item">
        <label>姓名：</label>
        <span>{{ familyInfo.name }}</span>
      </div>
      <div class="info-item">
        <label>与老人关系：</label>
        <span>{{ familyInfo.relationship }}</span>
      </div>
      <div class="info-item">
        <label>联系电话：</label>
        <span>{{ familyInfo.phoneNumber }}</span>
      </div>
    </div>

    <div class="elderly-list-card">
      <h2>关联老人</h2>
      <div v-if="familyInfo.elderlyList.length === 0" class="empty-list">
        暂无关联老人
      </div>
      <div v-else class="elderly-list">
        <div v-for="elderly in familyInfo.elderlyList" :key="elderly.id" class="elderly-item">
          <span>{{ elderly.name }}</span>
          <span>{{ elderly.age }}岁</span>
          <span>{{ elderly.healthStatus }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.family-container {
  padding: 20px;
}

.info-card, .elderly-list-card {
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

.elderly-list {
  margin-top: 10px;
}

.elderly-item {
  display: flex;
  justify-content: space-between;
  padding: 10px;
  border-bottom: 1px solid #eee;
}

.elderly-item:last-child {
  border-bottom: none;
}

.empty-list {
  text-align: center;
  color: #999;
  padding: 20px;
}
</style>