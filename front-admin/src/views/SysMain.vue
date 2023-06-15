<template>
  <div>
    <el-container>
      <el-aside width="200px">
        <SysMenu/>
      </el-aside>

      <el-container>
        <el-header>Header</el-header>
        <el-main><router-view/></el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import SysMenu from "@/components/SysMenu";
export default {
  name:"SysMain",
  components:{
    SysMenu
  }
}
</script>

<style scoped>

</style>