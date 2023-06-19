<template>
  <el-dialog title="修改密码" :visible="modifyPasswordVisible" :before-close="close">
    <el-form :model="form" ref="formName" :rules="rules">
      <el-form-item label="旧密码" :label-width="formLabelWidth" prop="oldPassword">
        <el-input v-model="form.oldPassword" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="新密码" :label-width="formLabelWidth" prop="newPassword">
        <el-input v-model="form.newPassword" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" :label-width="formLabelWidth" prop="repeatPassword">
        <el-input v-model="form.repeatPassword" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="close">取 消</el-button>
      <el-button type="primary" @click="submit('formName')">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import {modifyPassword} from '@/api/sysUser'

export default {
  props: ['modifyPasswordVisible'],
  data() {
    const checkNewPassword = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('密码不能为空'))
      }

      if (!(6 <= value.length <= 8)) {
        return callback(new Error('密码长度范围为6-8位'))
      }
      callback()
    };

    const checkRepeatPassword = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('确认密码不能为空'))
      }

      if (value !== this.form.newPassword) {
        return callback(new Error('两次输入的密码不一致'))
      }
      callback()
    }


    return {
      form: {
        oldPassword: '',
        newPassword: '',
        repeatPassword: ''
      },
      formLabelWidth: '120px',
      rules: {
        newPassword: [
          {validator: checkNewPassword, trigger: 'blur'}
        ],
        repeatPassword: [
          {validator: checkRepeatPassword, trigger: 'blur'}
        ],
      }
    }
  },

  methods: {
    submit(formName) {
      this.$refs[formName].validate((valid) => {
        if (!valid) {
          return false
        }
        modifyPassword(this.form).then(() => {
          this.close()
        })
      })
    },

    // 通过$emit来出发父组件中的modifyPasswordClose事件，从而达到在子组件中间接影响父组件的属性值修改
    close() {
      this.$emit('modifyPasswordClose')
    },
  }
};

</script>