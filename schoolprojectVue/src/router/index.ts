import {createRouter,createWebHistory} from 'vue-router'

import Index from "../components/Index.vue";
import Login from "../views/index/views/Login.vue";
import Register from "../views/index/views/Register.vue";
import Introduce from "../views/index/views/Introduce.vue";


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

    ]
})
export default router;
