<script setup lang="ts">
import { onMounted } from 'vue'
import { useUserStore } from '@/stores/userStore'

const userStore = useUserStore()

onMounted(() => {
  userStore.initUserFromStorage()
})
</script>

<template>
  <router-view></router-view>
</template>

<style scoped>

</style>