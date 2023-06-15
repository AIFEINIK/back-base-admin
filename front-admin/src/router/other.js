export default [
    {
        path: '/other',
        name: 'other',
        meta: {
            title: '其他模块'
        },
        component: () => import('@/views/SysMain'),
        children:[
            {
                path: '/other/page1',
                name: 'otherPage1',
                meta: {
                    title: '页面1'
                },
                component: () => import('@/views/other/PageOne')
            },
            {
                path: '/other/page2',
                name: 'otherPage2',
                meta: {
                    title: '页面2'
                },
                component: () => import('@/views/other/PageTwo')
            }
        ]
    }
]