<template>
  <el-dialog title="修改密码" :visible.sync="visible" :append-to-body="true">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" label-width="80px">
      <el-form-item label="用户账号">
        <span>{{ userName }}</span>
      </el-form-item>
      <el-form-item label="原密码" prop="password">
        <el-input type="password" v-model="dataForm.password"></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="newPassword">
        <el-input type="password" v-model="dataForm.newPassword"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="confirmPassword">
        <el-input type="password" v-model="dataForm.confirmPassword"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import {
    changePassword
  } from '@/api/login'
  export default {
    data() {
      var validateConfirmPassword = (rule, value, callback) => {
        if (this.dataForm.newPassword !== value) {
          callback(new Error('确认密码与新密码不一致'))
        } else {
          callback()
        }
      }
      return {
        visible: false,
        dataForm: {
          password: '',
          newPassword: '',
          confirmPassword: ''
        },
        dataRule: {
          password: [{
            required: true,
            message: '原密码不能为空',
            trigger: 'blur'
          }],
          newPassword: [{
            required: true,
            message: '新密码不能为空',
            trigger: 'blur'
          }],
          confirmPassword: [{
              required: true,
              message: '确认密码不能为空',
              trigger: 'blur'
            },
            {
              validator: validateConfirmPassword,
              trigger: 'blur'
            }
          ]
        }
      }
    },
    computed: {
      userName: {
        get() {
          return this.$store.state.user.name
        }
      },
    },
    methods: {
      // 初始化
      init() {
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
        })
      },
      // 表单提交
      dataFormSubmit() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            const data = {
              'username': this.$store.state.user.name,
              'password': this.dataForm.password,
              'newPassword': this.dataForm.newPassword
            }
            changePassword(new URLSearchParams(data)).then(response => {
              this.$refs['dataForm'].resetFields();
              this.visible = false;
              this.$message({
                message: '修改成功',
                type: 'success',
                duration: 1000
              });
              this.$store.dispatch('LogOut').then(() => {
                location.reload()
              })
            });
          }
        })
      }
    }
  }
</script>
