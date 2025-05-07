<script setup lang="ts">
import { ref, onMounted } from 'vue'
import type { User } from '@/models/user'

const props = defineProps<{
  familyInfo: User
}>()

onMounted(() => {
  console.log('FamilyInfo 组件收到的 props:', JSON.parse(JSON.stringify(props.familyInfo)))
})
</script>

<template>
  <div class="info-card">
    <h2>个人信息</h2>
    <div class="info-item">
      <label>姓名：</label>
      <span>{{ familyInfo.userName || '未设置' }}</span>
    </div>
    <div class="info-item">
      <label>年龄：</label>
      <span>{{ familyInfo.age ? `${familyInfo.age}岁` : '未设置' }}</span>
    </div>
    <div class="info-item">
      <label>性别：</label>
      <span>{{ familyInfo.gender || '未设置' }}</span>
    </div>
    <div class="info-item">
      <label>联系电话：</label>
      <span>{{ familyInfo.phoneNumber || '未设置' }}</span>
    </div>
    <div class="info-item">
      <label>邮箱：</label>
      <span>{{ familyInfo.email || '未设置' }}</span>
    </div>
    <div class="info-item">
      <label>地址：</label>
      <span>{{ familyInfo.address || '未设置' }}</span>
    </div>
  </div>
</template>

<style scoped>
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