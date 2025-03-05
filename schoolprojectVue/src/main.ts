import './assets/main.css'  // 暂时注释掉
import { createApp } from 'vue'
import App from './App.vue'
import router from './router/index'  // 确保路径正确

const app = createApp(App)
app.use(router)
app.mount('#app') 