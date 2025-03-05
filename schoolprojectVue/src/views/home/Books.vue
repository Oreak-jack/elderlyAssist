<script setup>
import { ref, watch } from 'vue'
import axios from 'axios'

const props = defineProps({
  category: String  // 接收分类
})

const books = ref([])
const loading = ref(false)
const error = ref(null)

// 获取书籍
const fetchBooks = async (categoryName) => {
  if (!categoryName) return

  try {
    loading.value = true
    const response = await axios.get(`http://localhost:8080/books?category=${categoryName}`)
    if (response.data.success) {
      books.value = response.data.data  // 假设接口返回 data 是书籍列表
    } else {
      error.value = response.data.message || '获取书籍失败'
    }
  } catch (err) {
    error.value = '获取书籍失败：' + err.message
  } finally {
    loading.value = false
  }
}

// 监听 category 变化
watch(() => props.category, (newCategory) => {
  fetchBooks(newCategory)
}, { immediate: true })  // 组件初始化时立即调用
</script>

<template>
  <div class="books">
    <h3 v-if="category">当前分类：{{ category }}</h3>

    <div v-if="loading">加载中...</div>
    <div v-else-if="error" class="error">{{ error }}</div>

    <ul v-else-if="books.length > 0">
      <li v-for="book in books" :key="book.id">
        {{ book.title }} - {{ book.author }}
      </li>
    </ul>

    <div v-else>暂无书籍</div>
  </div>
</template>

<style scoped>
.books {
  padding: 20px;
}
.error {
  color: red;
}
</style>
