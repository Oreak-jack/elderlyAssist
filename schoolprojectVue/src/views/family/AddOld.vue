<script setup lang="ts">
import { ref } from 'vue'
import { getAvailableElderly, bindElderly } from '@/api/familyApi'
import { ElMessage } from 'element-plus'
import type { User } from '@/models/user'

const props = defineProps<{
  familyId: number
}>()

const emit = defineEmits(['update:elderlyList'])

const dialogVisible = ref(false)
const availableElderly = ref<User[]>([])
const selectedElderly = ref<number | null>(null)
const loading = ref(false)

// 显示绑定对话框
const showBindDialog = async () => {
  try {
    loading.value = true
    const response = await getAvailableElderly()
    if (response.code === 200 && response.data) {
      availableElderly.value = response.data
      dialogVisible.value = true
    } else {
      ElMessage.warning(response.message || '获取可绑定老人列表失败')
    }
  } catch (error) {
    console.error('获取可绑定老人列表失败:', error)
    ElMessage.error('获取可绑定老人列表失败')
  } finally {
    loading.value = false
  }
}

// 处理绑定老人
const handleBindElderly = async () => {
  if (!selectedElderly.value) {
    ElMessage.warning('请选择要绑定的老人')
    return
  }

  try {
    loading.value = true
    const response = await bindElderly(selectedElderly.value, props.familyId)
    if (response.code === 200) {
      ElMessage.success('绑定成功')
      dialogVisible.value = false
      selectedElderly.value = null
      emit('update:elderlyList')
    } else {
      ElMessage.warning(response.message || '绑定失败')
    }
  } catch (error) {
    console.error('绑定失败:', error)
    ElMessage.error('绑定失败')
  } finally {
    loading.value = false
  }
}
</script>

<template>
  <div class="elderly-list-card">
    <div class="header-with-button">
      <h2>关联老人</h2>
      <el-button 
        type="primary" 
        @click="showBindDialog"
        :loading="loading"
      >
        添加老人
      </el-button>
    </div>
    <slot></slot>

    <!-- 绑定老人对话框 -->
    <el-dialog 
      v-model="dialogVisible" 
      title="绑定老人" 
      width="30%"
      :close-on-click-modal="false"
    >
      <el-select 
        v-model="selectedElderly" 
        placeholder="请选择要绑定的老人" 
        style="width: 100%"
      >
        <el-option
          v-for="elderly in availableElderly"
          :key="elderly.UserID"
          :label="elderly.UserName"
          :value="elderly.UserID"
        />
      </el-select>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false" :disabled="loading">取消</el-button>
          <el-button 
            type="primary" 
            @click="handleBindElderly"
            :loading="loading"
          >
            确定
          </el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<style scoped>
.elderly-list-card {
  background: #fff;
  border-radius: 8px;
  padding: 20px;
  margin-top: 20px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

.header-with-button {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.dialog-footer {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}
</style>