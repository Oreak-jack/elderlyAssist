<template>
  <section id="features" class="section features">
    <h2>核心功能</h2>
    <div class="features-grid">
      <div class="feature-card">
        <h3>数据采集</h3>
        <p>通过智能手表、智能家居等设备，实时收集老年人的生理数据、行为数据和生活环境数据。</p>
      </div>
      <div class="feature-card">
        <h3>数据处理与分析</h3>
        <p>利用边缘计算技术，对收集到的数据进行处理和分析，提取有用信息。</p>
      </div>
      <div class="feature-card">
        <h3>辅助记忆</h3>
        <p>提供个性化的提醒服务，如按时吃药、按时睡觉、按时吃饭等。</p>
      </div>
      <div class="feature-card">
        <h3>用户界面</h3>
        <p>简洁易用的操作界面设计，确保老年人能够轻松使用。</p>
      </div>
      <div class="feature-card">
        <h3>家属监护</h3>
        <p>允许家属远程查看老年人的健康状况和生活习惯信息。</p>
      </div>
    </div>
  </section>
</template>

<style scoped>
.features {
  background-color: #f8f9fa;
}

.features-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 2rem;
  margin-top: 3rem;
}

.feature-card {
  background: white;
  padding: 2.5rem;
  border-radius: 12px;
  box-shadow: 0 4px 20px rgba(0,0,0,0.06);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  border: 1px solid rgba(0,0,0,0.05);
}

.feature-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 30px rgba(0,0,0,0.1);
}

h3 {
  color: var(--primary-color);
  font-size: 1.4rem;
  margin-bottom: 1.2rem;
  font-weight: 600;
}

p {
  color: var(--text-color);
  line-height: 1.7;
  font-size: 1.1rem;
}

@media (max-width: 768px) {
  .features-grid {
    grid-template-columns: 1fr;
    gap: 1.5rem;
  }
}
</style>
