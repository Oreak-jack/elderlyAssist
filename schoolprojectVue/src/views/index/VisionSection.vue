<template>
  <section id="vision" class="section vision">
    <h2>项目愿景</h2>
    <div class="vision-content">
      <div class="vision-card">
        <h3>成为老年人日常生活中不可或缺的智能助手</h3>
        <p>通过智能化的服务，让老年人的生活更加便捷、安全。</p>
      </div>
      <div class="vision-card">
        <h3>建立完善的老年人健康数据平台</h3>
        <p>收集和分析老年人的健康数据，为医疗保健提供支持。</p>
      </div>
      <div class="vision-card">
        <h3>推动边缘计算技术在老年人辅助记忆领域的应用</h3>
        <p>利用先进技术，提供更加智能和个性化的服务。</p>
      </div>
    </div>
  </section>
</template>

<style scoped>
.vision {
  background-color: #f8f9fa;
  padding: 4rem 2rem;
}

.vision-content {
  max-width: 1000px;
  margin: 3rem auto 0;
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 2rem;
}

.vision-card {
  padding: 2rem;
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.05);
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
  .vision-content {
    grid-template-columns: 1fr;
    gap: 1.5rem;
  }
}
</style>