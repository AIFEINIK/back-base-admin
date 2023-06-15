<template>
  <div class="sys-menu">
    <el-menu
        default-active="home"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b">
      <h3>通用后台管理</h3>

      <el-menu-item v-for="item in noChildren" :key="item.name" :index="item.name" @click="menuClick(item)">
        <i :class="`el-icon-${item.icon}`"></i>
        <span slot="title">{{ item.label }}</span>
      </el-menu-item>

      <el-submenu v-for="item in hasChildren" :key="item.name" :index="item.name">
        <template slot="title">
          <i :class="`el-icon-${item.icon}`"></i>
          <span>{{ item.label }}</span>
        </template>
        <el-menu-item-group v-for="subItem in item.children" :key="subItem.name">
          <el-menu-item :index="subItem.name" @click="menuClick(subItem)">{{ subItem.label }}</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isCollapse: true,
      menuData: [
        {
          path: '/',
          name: 'home',
          label: '首页',
          icon: 's-home',
          url: 'Home/home'
        },
        {
          label: '基础数据',
          icon: 'location',
          name: 'base',
          children: [
            {
              path: '/product/warehouseManager',
              name: 'warehouseManager',
              label: '仓库管理',
              icon: 'video-play',
              url: 'product/warehouseManager'
            },
            {
              path: '/product/skuManager',
              name: 'skuManager',
              label: '商品管理',
              icon: 'video-play',
              url: 'product/skuManager'
            },
          ]
        },
        {
          name: 'user',
          label: '用户管理',
          icon: 'user',
          children: [
            {
              path: '/user/userManager',
              name: 'userManager',
              label: '用户',
              icon: 'video-play',
              url: '/user/userManager'
            }
          ]
        },
        {
          label: '其他',
          icon: 'location',
          name: 'other',
          children: [
            {
              path: '/other/page1',
              name: 'page1',
              label: '页面1',
              icon: 'setting',
              url: 'Other/PageOne'
            },
            {
              path: '/other/page2',
              name: 'page2',
              label: '页面2',
              icon: 'setting',
              url: 'Other/PageTwo'
            }
          ]
        },

      ]
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    menuClick(item) {
      if (this.$route.path !== item.path && !(this.$route.path === '/home' && item.path === '/')) {
        this.$router.push(item.path);
      }
    }
  },
  computed: {
    // 没有子菜单
    noChildren() {
      return this.menuData.filter(item => !item.children)
    },
    // 有子菜单
    hasChildren() {
      return this.menuData.filter(item => item.children)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="less" scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}

.el-menu {
  height: 100vh;

  h3 {
    color: #fff;
    text-align: center;
    line-height: 48px;
    font-size: 16px;
    font-weight: 400;
  }
}
</style>