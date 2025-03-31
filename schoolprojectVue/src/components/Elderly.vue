<script setup>
import {ref, watch, computed} from 'vue'
import HeaderElderly from "@/views/elderly/HeaderElderly.vue";
import RemindersElderly from "@/views/elderly/RemindersElderly.vue";
import HealthElderly from "@/views/elderly/HealthElderly.vue";
import FamilyInteractionElderly from "@/views/elderly/FamilyInteractionElderly.vue";
import { useUserStore } from '@/stores/userStore'

// 从 userStore 中拿到用户的信息
const store = useUserStore()
const elderlyInfo = computed(() => store.getCurrentUser)

// function elderlyInfoBtn(){
//   console.log("elderlyInfo发生了变化：",elderlyInfo.value)
// }

// 从 remindersApi 中获取数据，然后将数据存储在 RemindersStore 中，
// 由<RemindersElderly></RemindersElderly>展示，并更新

</script>

<template>
<!--  从 elderlyInfo 中拿到信息传入到下面组件中去-->
<!--  <button @click="elderlyInfoBtn">展示传进来的数据</button>-->
  <HeaderElderly :elderly-info="elderlyInfo"></HeaderElderly>
  <main>
    <!-- 今日提醒（核心区） -->
    <RemindersElderly :elderly-info="elderlyInfo"></RemindersElderly>
  </main>
  <!-- 健康数据（健康监测区） -->
  <HealthElderly></HealthElderly>
  <!-- 家属关怀（情感互动区） -->
  <FamilyInteractionElderly></FamilyInteractionElderly>
</template>

<style scoped>
main {
  padding: 1rem;
}
</style>
