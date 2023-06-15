export default [
    {
        path: '/product',
        name: 'product',
        meta: {
            title: '基础数据'
        },
        component: () => import('@/views/SysMain'),
        children:[
            {
                path: '/product/warehouseManager',
                name: 'productWarehouseManager',
                meta: {
                    title: '仓库管理'
                },
                component: () => import('@/views/product/WarehouseManager')
            },
            {
                path: '/product/skuManager',
                name: 'productSkuManager',
                meta: {
                    title: '商品管理'
                },
                component: () => import('@/views/product/SkuManager')
            }
        ]
    }
]