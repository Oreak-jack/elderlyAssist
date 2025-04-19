import {createRouter,createWebHistory} from 'vue-router'

import Index from "../components/Index.vue";
import Login from "../views/index/views/Login.vue";
import Register from "../views/index/views/Register.vue";
import Introduce from "../views/index/views/Introduce.vue";
import Home from "../components/Home.vue";
import Test from "../components/Test.vue";
import Elderly from "../components/Elderly.vue";
import Family from "../components/Family.vue";
import {useUserStore} from "../stores/userStore";

const router = createRouter({
    history: createWebHistory(),
    routes:[
        {
            path:'/index',
            component:Index,
            meta: { requiresAuth: false }, // 不需要登录
            children:[
                {
                    path:'',
                    component:Introduce
                },
                {
                    path:'login',
                    component:Login
                },
                {
                    path:'register',
                    component:Register
                },
                {
                    path:'introduce',
                    component:Introduce
                }
            ]
        },
        {
            path:'/',
            redirect:'/index'
        },
        {
            path:'/home',
            component: Home,
            meta: { requiresAuth: true } // 需要登录
        },
        {
            path:'/test',
            component: Test
        },
        {
            path:'/elderly',
            component: Elderly,
            meta: { requiresAuth: true }
        },
        {
            path:'/family',
            component: Family,
            meta: { requiresAuth: true }
        }

    ]
})
// 全局路由守卫
router.beforeEach((to, from, next) => {
    const userStore = useUserStore();
    if (to.meta.requiresAuth && !userStore.isLoggedIn) {
        next('/index/login'); // 未登录，重定向到登录页
    } else {
        next(); // 继续跳转
    }
});
export default router;
