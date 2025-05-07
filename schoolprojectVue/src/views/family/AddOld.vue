<script setup lang="ts">
import { ref } from 'vue'
import { ElMessage } from 'element-plus'

const dialogVisible = ref(false)
const availableElderly = ref([
  { UserID: 1001, UserName: '张爷爷' },
  { UserID: 1002, UserName: '李奶奶' },
  { UserID: 1003, UserName: '王爷爷' },
  { UserID: 1004, UserName: '赵奶奶' }
])
const selectedElderly = ref<number | null>(null)

// 显示绑定对话框
const showBindDialog = () => {
  dialogVisible.value = true
}

// 处理绑定老人
const handleBindElderly = () => {
  if (!selectedElderly.value) {
    ElMessage.warning('请选择要绑定的老人')
    return
  }

  const elderly = availableElderly.value.find(e => e.UserID === selectedElderly.value)
  ElMessage.success(`成功绑定老人: ${elderly?.UserName}`)
  dialogVisible.value = false
  selectedElderly.value = null
}
</script>

<template>
  <div>
    <el-button
        type="primary"
        @click="showBindDialog"
    >
      添加老人
    </el-button>

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
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button
              type="primary"
              @click="handleBindElderly"
          >
            确定
          </el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<style scoped>
.dialog-footer {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}
</style>