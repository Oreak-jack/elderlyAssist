<template>
  <footer class="footer">
    <div class="footer-content">
      <p>© 2024 Memory Assistant System. All rights reserved.</p>
      <div class="footer-links">
        <a href="#privacy">隐私政策</a>
        <a href="#terms">使用条款</a>
        <a href="#contact">联系我们</a>
      </div>
    </div>
  </footer>
</template>

<style scoped>
.footer {
  background-color: #2c3e50;
  color: white;
  padding: 2rem 0;
  margin-top: 4rem;
}

.footer-content {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 2rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
  gap: 1rem;
}

.footer-links {
  display: flex;
  gap: 2rem;
}

.footer-links a {
  color: white;
  text-decoration: none;
  opacity: 0.8;
  transition: opacity 0.3s;
}

.footer-links a:hover {
  opacity: 1;
}

@media (max-width: 768px) {
  .footer-content {
    flex-direction: column;
    text-align: center;
  }
  
  .footer-links {
    justify-content: center;
  }
}
</style>