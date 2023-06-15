export default [
    {
        path: '/user',
        name: 'user',
        meta: {
            title: '用户管理'
        },
        component: () => import('@/views/SysMain'),
        children:[
            {
                path: '/user/userManager',
                name: 'userManager',
                meta: {
                    title: '用户管理'
                },
                component: () => import('@/views/user/UserManager')
            }
        ]
    }
]