<template>
  <section id="users" class="section users">
    <h2>目标用户</h2>
    <div class="users-grid">
      <div class="user-card">
        <h3>60岁以上的老年人</h3>
        <p>为有记忆障碍风险的老年人提供日常生活辅助。</p>
      </div>
      <div class="user-card">
        <h3>有记忆障碍的人群</h3>
        <p>帮助记忆障碍人群更好地管理日常生活。</p>
      </div>
      <div class="user-card">
        <h3>生活作息不规律人群</h3>
        <p>协助建立健康的生活作息习惯。</p>
      </div>
      <div class="user-card">
        <h3>常年处于亚健康状态的人群</h3>
        <p>提供健康监测和生活建议。</p>
      </div>
      <div class="user-card">
        <h3>关注老年人健康状况的家属和护理人员</h3>
        <p>实时了解老年人的健康状况和生活情况。</p>
      </div>
    </div>
  </section>
</template>

<style scoped>
.users {
  background-color: white;
  padding: 4rem 2rem;
}

.users-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 2rem;
  margin-top: 3rem;
}

.user-card {
  padding: 2rem;
  border-radius: 8px;
  background: #f8f9fa;
}

h3 {
  color: var(--primary-color);
  font-size: 1.25rem;
  margin-bottom: 1rem;
}

p {
  color: var(--text-color);
  line-height: 1.6;
}

@media (max-width: 768px) {
  .users-grid {
    grid-template-columns: 1fr;
    gap: 1.5rem;
  }
}
</style>