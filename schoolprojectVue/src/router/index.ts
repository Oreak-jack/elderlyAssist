import {createRouter,createWebHistory} from 'vue-router'

import Index from "../components/Index.vue";
import Login from "../views/index/views/Login.vue";
import Register from "../views/index/views/Register.vue";
import Introduce from "../views/index/views/Introduce.vue";
import Home from "../components/Home.vue";
import Test from "../components/Test.vue";


let router = createRouter({
    history: createWebHistory(),
    routes:[
        {
            path:'/index',
            component:Index,
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
            meta: { requiresAuth: false } // 需要登录
        },
        {
            path:'/test',
            component: Test
        }

    ]
})
// 全局路由守卫
router.beforeEach((to, from, next) => {
    const isAuthenticated = localStorage.getItem('token'); // 这里检查是否有登录凭证
    if (to.meta.requiresAuth && !isAuthenticated) {
        next('/index/login'); // 未登录，重定向到登录页
    } else {
        next(); // 继续跳转
    }
});
export default router;
