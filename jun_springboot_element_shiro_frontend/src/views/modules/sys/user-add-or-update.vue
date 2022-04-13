<template>
  <el-dialog :title="!dataForm.userId ? '新增' : '修改'" :close-on-click-modal="false" :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" label-width="80px">
      <el-form-item label="用户名称" prop="username">
        <el-input v-model="dataForm.username" placeholder="登录帐号"></el-input>
      </el-form-item>
      <el-form-item label="用户密码" prop="password" :class="{ 'is-required': !dataForm.userId }">
        <el-input v-model="dataForm.password" type="password" placeholder="密码"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="comfirmPassword" :class="{ 'is-required': !dataForm.userId }">
        <el-input v-model="dataForm.comfirmPassword" type="password" placeholder="确认密码"></el-input>
      </el-form-item>
      <el-form-item label="用户邮箱" prop="email">
        <el-input v-model="dataForm.email" placeholder="邮箱"></el-input>
      </el-form-item>
      <el-form-item label="手机号码" prop="mobile">
        <el-input v-model="dataForm.mobile" placeholder="手机号"></el-input>
      </el-form-item>
      <el-form-item label="用户角色" size="mini" prop="roleIdList">
        <el-checkbox-group v-model="dataForm.roleIdList">
          <el-checkbox v-for="role in roleList" :key="role.roleId" :label="role.roleId">{{ role.roleName }}
          </el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="账号状态" size="mini" prop="status">
        <el-radio-group v-model="dataForm.status">
          <el-radio :label="0">禁用</el-radio>
          <el-radio :label="1">正常</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
      <el-button @click="visible = false">取消</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import { getUserInfo, createUser, updateUser } from '@/api/sys/user'
  import { fetchRoleSelectList } from '@/api/sys/role'
  import { isEmail, isMobile } from '@/utils/validate'
  const defaultDataForm = {
    userId: 0,
    username: '',
    password: '',
    comfirmPassword: '',
    salt: '',
    email: '',
    mobile: '',
    roleIdList: [],
    status: 1
  }
  export default {
    data() {
      var validatePassword = (rule, value, callback) => {
        if (!this.dataForm.userId && !/\S/.test(value)) {
          callback(new Error('密码不能为空'))
        } else {
          callback()
        }
      }
      var validateComfirmPassword = (rule, value, callback) => {
        if (!this.dataForm.userId && !/\S/.test(value)) {
          callback(new Error('确认密码不能为空'))
        } else if (this.dataForm.password !== value) {
          callback(new Error('确认密码与密码输入不一致'))
        } else {
          callback()
        }
      }
      var validateEmail = (rule, value, callback) => {
        if (!isEmail(value)) {
          callback(new Error('邮箱格式错误'))
        } else {
          callback()
        }
      }
      var validateMobile = (rule, value, callback) => {
        if (!isMobile(value)) {
          callback(new Error('手机号格式错误'))
        } else {
          callback()
        }
      }
      return {
        dataForm: Object.assign({}, defaultDataForm),
        visible: false,
        roleList: [],
        dataRule: {
          username: [{
            required: true,
            message: '用户名不能为空',
            trigger: 'blur'
          }],
          password: [{
            validator: validatePassword,
            trigger: 'blur'
          }],
          comfirmPassword: [{
            validator: validateComfirmPassword,
            trigger: 'blur'
          }],
          email: [{
              required: true,
              message: '邮箱不能为空',
              trigger: 'blur'
            },
            {
              validator: validateEmail,
              trigger: 'blur'
            }
          ],
          mobile: [{
              required: true,
              message: '手机号不能为空',
              trigger: 'blur'
            },
            {
              validator: validateMobile,
              trigger: 'blur'
            }
          ]
        }
      }
    },
    methods: {
      // 初始化页面
      init(id) {
        this.dataForm.userId = id || 0
        fetchRoleSelectList().then(res => {
          this.roleList = res && res.code === 0 ? res.list : []
        }).then(() => {
          this.visible = true
          this.$nextTick(() => {
          	this.$refs['dataForm'].resetFields()
          })
        }).then(() => {
          if (this.dataForm.userId) {
            getUserInfo(this.dataForm.userId).then(res => {
              if (res && res.code === 0) {
                this.dataForm.username = res.user.username
                this.dataForm.salt = res.user.salt
                this.dataForm.email = res.user.email
                this.dataForm.mobile = res.user.mobile
                this.dataForm.roleIdList = res.user.roleIdList
                this.dataForm.status = res.user.status
              }
            })
          }
        })
      },
      // 提交表单数据
      dataFormSubmit() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            if (!this.dataForm.userId) {
              // 创建用户
              createUser(this.dataForm).then(response => {
                this.$refs['dataForm'].resetFields();
                this.dataForm = Object.assign({}, defaultDataForm);
                this.$message({
                  message: '提交成功',
                  type: 'success',
                  duration: 1000
                })
                this.visible = false
                this.$emit('refreshDataList')
              })
            } else {
              // 更新用户信息
              updateUser(this.dataForm).then(response => {
                this.$refs['dataForm'].resetFields();
                this.$message({
                  message: '修改成功',
                  type: 'success',
                  duration: 1000
                })
                this.visible = false
                this.$emit('refreshDataList')
              })
            }
          }
        })
      }
    }
  }
</script>
