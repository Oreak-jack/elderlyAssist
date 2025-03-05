<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue';

const isScrolled = ref(false);

const handleScroll = () => {
  isScrolled.value = window.scrollY > 50;
};

const scrollToSection = (event: Event, sectionId: string) => {
  event.preventDefault();
  const element = document.getElementById(sectionId);
  if (element) {
    const headerHeight = 64;
    const elementPosition = element.getBoundingClientRect().top + window.pageYOffset;
    const offsetPosition = elementPosition - headerHeight;

    window.scrollTo({
      top: offsetPosition,
      behavior: 'smooth'
    });
  }
};

//点击登录注册按钮后设置的值
const emits = defineEmits(['setLoginOrRegisterOption'])

const handleLoginOrRegisterClick = (option)=> {
  emits('setLoginOrRegisterOption',option);
}

onMounted(() => {
  window.addEventListener('scroll', handleScroll);
});

onUnmounted(() => {
  window.removeEventListener('scroll', handleScroll);
});
</script>

<template>
  <header class="header">
    <nav class="nav-container">
      <div class="logo">Memory Assistant</div>
      <div class="nav-links" @click="handleLoginOrRegisterClick(false)">
        <a href="#overview" @click="(e) => scrollToSection(e, 'overview')">概述</a>
        <a href="#features" @click="(e) => scrollToSection(e, 'features')">功能</a>
        <a href="#users" @click="(e) => scrollToSection(e, 'users')">用户群体</a>
        <a href="#vision" @click="(e) => scrollToSection(e, 'vision')">愿景</a>
        <a href="#advantage" @click="(e) => scrollToSection(e, 'advantage')">竞争优势</a>
      </div>
      <div class="auth-buttons" @click="handleLoginOrRegisterClick(true)">
        <router-link to="/index/login" class="btn btn-login" >登录</router-link>
        <router-link to="/index/register" class="btn btn-register">注册</router-link>
      </div>
    </nav>
  </header>
</template>

<style scoped>
.header {
  background: #fff;
  box-shadow: 0 1px 3px rgba(0,0,0,0.1);
  padding: 1rem 0;
  position: fixed;
  width: 100%;
  top: 0;
  z-index: 1000;
  left: 0;
}

.nav-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 1rem;
  display: flex;
  align-items: center;
}

.logo {
  font-size: 1.5rem;
  font-weight: bold;
  color: var(--primary-color);
  margin-right: auto;
}

.nav-links {
  display: flex;
  align-items: center;
}

.nav-links a {
  color: var(--text-color);
  text-decoration: none;
  margin-left: 2rem;
  font-weight: 500;
}

.auth-buttons {
  margin-left: 2rem;
  display: flex;
  gap: 1rem;
}

.btn {
  padding: 0.5rem 1.5rem;
  border-radius: 4px;
  font-weight: 500;
  text-decoration: none;
  transition: all 0.3s ease;
}

.btn-login {
  color: var(--primary-color);
  border: 1px solid var(--primary-color);
}

.btn-login:hover {
  background: var(--primary-color);
  color: white;
}

.btn-register {
  background: var(--primary-color);
  color: white;
  border: 1px solid var(--primary-color);
}

.btn-register:hover {
  background: #5a9935;
  border-color: #5a9935;
}
</style>