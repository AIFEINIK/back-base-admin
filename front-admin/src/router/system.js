
export default [
    {
        path: '/',
        redirect: {
            name: 'login',
        },
    },
    {
        path: '/login',
        name: 'login',
        meta: {
            title: '后台登录系统'
        },
        component: () => import('@/views/SysLogin')
    },
    {
        path: '/system',
        name: 'system',
        meta: {
            title: '系统管理'
        },
        component: () => import('@/views/SysMain'),
        children:[
            {
                path: '/system/home',
                name: 'systemHome',
                meta: {
                    title: '主页'
                },
                component: () => import('@/views/system/SysHome')
            }
        ]
    }
]