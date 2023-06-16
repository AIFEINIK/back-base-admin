import Vue from 'vue';
import VueRouter from 'vue-router';
import system from '@/router/system';
import product from '@/router/product';
import other from '@/router/other';
import user from '@/router/user';
import {getToken} from "@/utils/auth";

const routes = [];

routes.push(...system)
routes.push(...product)
routes.push(...other)
routes.push(...user)

Vue.use(VueRouter);

const router = new VueRouter({
    routes
});

router.beforeEach((to, from, next) => {
    console.log('from:' + from.path + " to:" + to.path)
    if (to.meta.title) {
        document.title = to.meta.title
    }
    if (to.path === '/login') {
        next()
        return
    }
    if (getToken()) {
        next()
        return
    }
    next({path:'/login'})
})

export default router;