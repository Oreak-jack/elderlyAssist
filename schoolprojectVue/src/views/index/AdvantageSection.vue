<template>
  <section id="advantage" class="section advantage-section">
    <h2>竞争优势</h2>
    <div class="advantages-grid">
      <div class="advantage-card">
        <div class="icon">🚀</div>
        <h3>高效性能</h3>
        <p>采用先进算法，确保系统运行高效稳定</p>
      </div>
      <div class="advantage-card">
        <div class="icon">🎯</div>
        <h3>精准匹配</h3>
        <p>智能分析用户需求，提供精准的记忆辅助</p>
      </div>
      <div class="advantage-card">
        <div class="icon">🛡️</div>
        <h3>安全可靠</h3>
        <p>严格的数据加密和隐私保护机制</p>
      </div>
    </div>
  </section>
</template>

<style scoped>
.advantage-section {
  background-color: var(--bg-light);
}

.advantages-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
  gap: 2rem;
  padding: 2rem 0;
}

.advantage-card {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0,0,0,0.1);
  text-align: center;
  transition: transform 0.3s;
}

.advantage-card:hover {
  transform: translateY(-5px);
}

.icon {
  font-size: 3rem;
  margin-bottom: 1rem;
}

h3 {
  color: var(--primary-color);
  margin-bottom: 1rem;
}
</style>