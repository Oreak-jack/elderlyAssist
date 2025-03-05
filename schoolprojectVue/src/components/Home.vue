<script setup>
import { ref } from 'vue'
import HeaderNav from "@/views/home/HeaderNavHome.vue";
import SideBar from "@/views/home/SideBarHome.vue";
import Books from "@/views/home/Books.vue";

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
