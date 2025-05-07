<script setup>
import {ref, watch, computed} from 'vue'
import HeaderElderly from "@/views/elderly/HeaderElderly.vue";
import RemindersElderly from "@/views/elderly/RemindersElderly.vue";
import HealthElderly from "@/views/elderly/HealthElderly.vue";
import FamilyInteractionElderly from "@/views/elderly/FamilyInteractionElderly.vue";
import { useUserStore } from '@/stores/userStore'

const store = useUserStore()
const elderlyInfo = computed(() => store.getCurrentUser)
</script>

<template>
  <div class="elderly-app">
    <!-- 顶部信息区 -->
    <HeaderElderly :elderly-info="elderlyInfo" class="header-section"/>

    <!-- 主内容区 -->
    <div class="content-container">
      <!-- 第一行：提醒事项独占一行 -->
      <section class="reminders-section">
        <RemindersElderly :elderly-info="elderlyInfo"/>
      </section>

      <!-- 第二行：健康数据和家属关怀 -->
      <div class="secondary-sections">
        <!-- 健康数据 -->
        <section class="health-section">
          <HealthElderly/>
        </section>

        <!-- 家属关怀 -->
        <section class="family-section">
          <FamilyInteractionElderly/>
        </section>
      </div>
    </div>
  </div>
</template>

<style scoped>
.elderly-app {
  font-family: 'Microsoft YaHei', Arial, sans-serif;
  background-color: #f8fafc; /* 更浅的背景色 */
  min-height: 100vh;
  color: #333;
  line-height: 1.6;
}

 .header-section {
   background: linear-gradient(to right, #6c9eff, #8ab4ff); /* 更浅的蓝色渐变 */
   color: white;
   padding: 1.5rem 0;
   margin-bottom: 2rem;
   box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08); /* 更柔和的阴影 */
   position: relative;
   overflow: hidden;
   text-align: center;
 }

/* 优化波浪效果 */
.header-section::after {
  content: "";
  position: absolute;
  bottom: -5px;
  left: 0;
  right: 0;
  height: 24px;
  background: url('data:image/svg+xml;utf8,<svg viewBox="0 0 1200 120" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="none"><path d="M0,0V46.29c47.79,22.2,103.59,32.17,158,28,70.36-5.37,136.33-33.31,206.8-37.5C438.64,32.43,512.34,53.67,583,72.05c69.27,18,138.3,24.88,209.4,13.08,36.15-6,69.85-17.84,104.45-29.34C989.49,25,1113-14.29,1200,52.47V0Z" fill="%23f8fafc" opacity=".25"/></svg>');
  background-size: cover;
  background-repeat: no-repeat;
}

.content-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 1.5rem 3rem;
  display: flex;
  flex-direction: column;
  gap: 2.5rem; /* 增加间距 */
}

/* 统一卡片样式 */
.reminders-section,
.health-section,
.family-section {
  background: white;
  border-radius: 12px;
  padding: 2rem;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
  border-top: none; /* 移除顶部边框 */
  position: relative;
  overflow: hidden;
}

/* 添加彩色装饰条 */
.reminders-section::before,
.health-section::before,
.family-section::before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  height: 6px;
}

.reminders-section::before { background: #4CAF50; }
.health-section::before { background: #2196F3; }
.family-section::before { background: #FF9800; }

/* 卡片悬停效果 */
.reminders-section:hover,
.health-section:hover,
.family-section:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
}

/* 第二行容器 */
.secondary-sections {
  display: grid;
  grid-template-columns: 1fr;
  gap: 2rem;
}

/* 标题样式 */
h2 {
  font-size: 1.5rem;
  margin-bottom: 1.5rem;
  color: #2c3e50;
  font-weight: 600;
  position: relative;
  padding-bottom: 0.5rem;
}

h2::after {
  content: "";
  position: absolute;
  bottom: 0;
  left: 0;
  width: 50px;
  height: 3px;
  background: currentColor;
  opacity: 0.2;
}

/* 响应式设计 */
@media (min-width: 768px) {
  .secondary-sections {
    grid-template-columns: 1fr 1fr;
    gap: 2.5rem;
  }

  .health-section,
  .family-section {
    min-height: auto; /* 移除固定高度 */
  }
}

@media (min-width: 1024px) {
  .content-container {
    gap: 3rem;
  }

  .reminders-section {
    padding: 2.5rem;
  }

  .health-section,
  .family-section {
    padding: 2rem 2.5rem;
  }
}

/* 增大字体大小 */
:deep() {
  font-size: 16px;

  @media (min-width: 768px) {
    font-size: 17px;
  }

  button, .btn {
    font-size: 1.1em;
    padding: 0.9em 1.3em;
    border-radius: 8px;
  }
}
</style>