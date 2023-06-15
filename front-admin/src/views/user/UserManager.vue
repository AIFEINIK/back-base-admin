<template>
  <el-table
      v-loading="loading"
      :data="userData"
      border
      style="width: 100%"
      max-height="250">
    <el-table-column
        prop="id"
        type="index"
        label="序号"
        width="150"
    >
    </el-table-column>
    <el-table-column
        fixed
        prop="createTime"
        label="创建日期"
        width="150">
    </el-table-column>
    <el-table-column
        prop="userName"
        label="姓名"
        width="120">
    </el-table-column>
    <el-table-column
        prop="province"
        label="省份"
        width="150">
    </el-table-column>
    <el-table-column
        prop="city"
        label="市区"
        width="220">
    </el-table-column>
    <el-table-column
        prop="address"
        label="地址"
        width="300">
    </el-table-column>
    <el-table-column
        label="操作"
        width="100">
      <template slot-scope="scope">
        <el-button @click="editUser(scope.row)" type="text" size="small">编辑</el-button>
        <el-button @click="detailUser(scope.row)" type="text" size="small">详情</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  data() {
    return {
      userData:[],
      loading:true
    }
  },
  methods: {
    editUser() {

    }
  },
  created() {

  }
}
</script>

<style scoped>
.el-table th.gutter {
  display: table-cell !important;
}

.el-table colgroup.gutter {
  display: table-cell !important;
}
</style>