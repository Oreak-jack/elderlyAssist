<script setup>
import { ref } from 'vue'




const selectedCategory = ref(null)  // 存储选中的分类

const handleCategoryChange = (categoryName) => {
  selectedCategory.value = categoryName  // 更新选中的分类
}
</script>

<template>
  <header>
    <HeaderNav></HeaderNav>
  </header>

  <div>
    <SideBar @categorySelected="handleCategoryChange"></SideBar>
  </div>

  <main>
    <Books :category="selectedCategory"></Books>
  </main>
</template>
