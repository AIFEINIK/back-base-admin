<template>
  <div class="login-page" @keyup.enter="onSubmit">
    <div class="container">
      <div class="login-logo"></div>

      <div class="login-form-box">
        <p class="login-title">登录信息</p>
        <el-form :model="loginForm" ref="loginForm" :rules="rules" class="login-form">
          <el-form-item prop="account">
            <el-input v-model="loginForm.account" placeholder="账号">
            </el-input>
          </el-form-item>

          <el-form-item prop="pwd">
            <el-input type="password" v-model="loginForm.password" placeholder="密码">
            </el-input>
          </el-form-item>

          <el-form-item>
            <div class="login-form-button" @click="onSubmit">立即登录</div>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import {getToken, removeToken} from "@/utils/auth";
import store from '@/store'
export default {

  data() {
    return {
      loginForm:{
        account: '',
        password: ''
      },

      rules: {
        account: [
          {required: true, message: "请输入账号", trigger: 'blur'}
        ],
        password: [
          {required: true, message: "请输入密码", trigger: 'blur'}
        ]
      }
    }
  },

  methods: {
    onSubmit() {
      console.log(this.loginForm)
      store.dispatch('login', this.loginForm).then(res => {
        if (getToken()) {
          this.$router.push('/system/home')
          return
        }
        console.log('fail:' + res)
      }).catch(e => {
        console.log(e)
      });
    },
  },

  created() {
    removeToken()
    // if (getToken()) {
    //   this.$router.push('/system/home')
    // }
  }
}
</script>

<style lang="less">
body {
  height: 100%;
}

.login-page {
  height: 100%;
  background: url('../assets/images/background.png') no-repeat;
  background-size: 100% 100%;

  .container {
    width: 560px;
    height: 440px;
    display: flex;
    flex-direction: column;
    align-items: center;
    position: absolute;
    top: 44%;
    left: 50%;
    margin-top: -220px;
    margin-left: -280px;

    .login-logo {
      width: 100%;
      height: 50px;
      text-indent: -1000px;
      position: relative;
      background: center url('../assets/images/login_logo.png') no-repeat;
    }

    .login-form-box {
      width: 360px;
      height: 300px;
      padding: 0 30px;
      margin-top: 45px;
      background-color: #3e4048;
      border-radius: 8px;

      .login-title {
        color: #ffffff;
        font-size: 20px;
        text-align: center;
        margin: 32px 0;
        font-weight: lighter;
        letter-spacing: 2px;
      }

      .login-form {
        .login-form-button {
          height: 42px;
          line-height: 42px;
          margin-top: 18px;
          text-align: center;
          color: #fff;
          font-size: 16px;
          background-color: #ec6c00;
          border-radius: 5px;
          cursor: pointer;

          &:hover {
            background-color: #f48b19;
          }
        }
      }
    }
  }
}
</style>