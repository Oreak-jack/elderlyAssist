<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'
import { defineEmits } from 'vue'  // 传输数组到父组件中去

const router = useRouter()
const categories = ref([])
const loading = ref(true)
const error = ref(null)

const emits = defineEmits(['categorySelected'])  // 定义事件

// 获取分类数据
const fetchCategories = async () => {
  try {
    loading.value = true
    const response = await axios.get('http://localhost:8080/detail/books/categories')
    if (response.data.success) {
      categories.value = response.data.data.map((name, index) => ({
        id: index + 1,
        name: name
      }))
    } else {
      error.value = response.data.message || '获取分类失败'
    }
  } catch (err) {
    error.value = '获取分类失败：' + err.message
    console.error('获取分类错误：', err)
  } finally {
    loading.value = false
  }
}

// 处理分类点击
const handleCategoryClick = (categoryName) => {
  emits('categorySelected', categoryName)  // 触发事件，传递分类名称
}

// 组件挂载时获取数据
onMounted(fetchCategories)
</script>

<template>
  <div class="sidebar">
    <h3>图书分类</h3>

    <div v-if="loading" class="loading">加载中...</div>
    <div v-else-if="error" class="error">{{ error }}</div>

    <ul v-else class="category-list">
      <li
          v-for="category in categories"
          :key="category.id"
          @click="handleCategoryClick(category.name)"
          class="category-item"
      >
        {{ category.name }}
      </li>
    </ul>
  </div>
</template>
