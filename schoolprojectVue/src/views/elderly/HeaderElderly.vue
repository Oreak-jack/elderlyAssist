<script setup>
import { ref, watch, computed } from 'vue'

const props = defineProps({
  elderlyInfo: {
    type: Object,
    required: true
  }
})

const currentDate = ref(new Date().toLocaleDateString('zh-CN', { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' }))
const userName = computed(() => {
  return props.elderlyInfo?.userName || ''
})

// function btn(){
//   console.log(userName)
// }

</script>

<template>
  <header class="home-header">
    <h1 v-if="userName">欢迎，{{ userName }}</h1>
    <h1 v-else>加载中...</h1>
    <p class="date">{{ currentDate }}</p>
  </header>
<!--  <button @click="btn">显示名字 </button>-->
</template>

<style scoped>
.home-header {
  margin-bottom: 2rem;
  text-align: center;
  padding: 1rem;
  background-color: #f5f5f5;
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
</style>