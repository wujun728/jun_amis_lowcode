<template>
  <div class="login-container" :style="{backgroundImage:'url('+bgImg+')'}">
    <el-card class="login-form-layout" shadow="hover" >
      <h2 class="login-title color-main">系统登录</h2>
      <el-form autoComplete="on" :model="loginForm" :rules="loginRules" ref="loginForm" label-position="left">
        <el-form-item prop="username">
          <el-input name="username" type="text" v-model="loginForm.username" autoComplete="on" placeholder="请输入用户名">
            <span slot="prefix">
              <svg-icon icon-class="ums-admin" class="color-main"></svg-icon>
            </span>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input name="password" :type="pwdType" v-model="loginForm.password"
            autoComplete="on" placeholder="请输入密码">
            <span slot="prefix">
              <svg-icon icon-class="password" class="color-main"></svg-icon>
            </span>
            <span slot="suffix" @click="showPwd">
              <svg-icon icon-class="eye" class="color-main"></svg-icon>
            </span>
          </el-input>
        </el-form-item>
        <el-form-item prop="captcha">
          <el-row>
            <el-col :span="14">
              <el-input v-model="loginForm.captcha" placeholder="输入验证码" />
            </el-col>
            <el-col :span="10" class="login-captcha">
              <img :src="captchaPath" alt="看不清,点击刷新!" @click="getCaptcha()">
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item style="margin-bottom: 15px;text-align: center">
          <el-button style="width: 100%" type="primary" :loading="loading" @click.native.prevent="handleLogin">
            登录
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
  import { getCodeImg } from '@/api/login'
  import { getUUID } from '@/utils'
  import { isvalidUsername } from '@/utils/validate';
  import {
    setSupport,
    getSupport,
    setCookie,
    getCookie
  } from '@/utils/support';
  import bgImg from "@/assets/images/bg-img.jpg"

  export default {
    name: 'login',
    data() {
      const validateUsername = (rule, value, callback) => {
        if (!isvalidUsername(value)) {
          callback(new Error('请输入正确的用户名'))
        } else {
          callback()
        }
      };
      const validatePass = (rule, value, callback) => {
        if (value.length < 3) {
          callback(new Error('密码不能小于3位'))
        } else {
          callback()
        }
      };
      return {
        bgImg: bgImg,
        loginForm: {
          username: 'admin',
          password: 'admin123',
          uuid: '',
          captcha: ''
        },
        loginRules: {
          username: [{
            required: true,
            trigger: 'blur',
            validator: validateUsername
          }],
          password: [{
            required: true,
            trigger: 'blur',
            validator: validatePass
          }]
        },
        captchaPath: '',
        loading: false,
        pwdType: 'password',
      }
    },
    created() {
      this.getCaptcha()
    },
    methods: {
      showPwd() {
        if (this.pwdType === 'password') {
          this.pwdType = ''
        } else {
          this.pwdType = 'password'
        }
      },
      handleLogin() {
        this.$refs.loginForm.validate(valid => {
          if (valid) {
            this.loading = true;
            this.$store.dispatch('Login', this.loginForm).then(() => {
              this.loading = false;
              this.$router.push({
                path: '/'
              })
            }).catch(() => {
              this.getCaptcha();
              this.loading = false
            })
          } else {
            console.log('参数验证不合法！');
            return false
          }
        })
      },
      getCaptcha() {
        this.loginForm.uuid = getUUID()
        getCodeImg(this.loginForm.uuid).then(res => {
          this.captchaPath = 'data:image/gif;base64,' + res.img
        })
      }
    }
  }
</script>

<style scoped>
  .login-form-layout {
    position: absolute;
    left: 0;
    right: 0;
    width: 360px;
    margin: 150px auto;
  }

  .login-title {
    text-align: center;
  }

  .login-container {
    position:fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-attachment: fixed;
    background-repeat: no-repeat;
    background-size: cover;
    -moz-background-size: cover;
    -webkit-background-size: cover;
  }

  .login-captcha {
    height: 36px;
    line-height: 36px;
  }

  .login-captcha>img {
    margin-left: 12px;
  }
</style>
