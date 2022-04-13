<template>
  <el-dialog :title="!dataForm.id ? '新增' : '修改'" :close-on-click-modal="false" :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" label-width="80px">
      <el-form-item label="参数名称" prop="paramKey">
        <el-input v-model="dataForm.paramKey" placeholder="请输入参数名称"></el-input>
      </el-form-item>
      <el-form-item label="参数内容" prop="paramValue">
        <el-input v-model="dataForm.paramValue" placeholder="请输入参数内容"></el-input>
      </el-form-item>
      <el-form-item label="参数备注" prop="remark">
        <el-input v-model="dataForm.remark" placeholder="请输入参数备注"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
      <el-button @click="visible = false">取消</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import {
    getConfigInfo,
    createConfig,
    updateConfig
  } from '@/api/sys/config'
  const defaultDataForm = {
    id: 0,
    paramKey: '',
    paramValue: '',
    remark: ''
  }
  export default {
    data() {
      return {
        dataForm: Object.assign({}, defaultDataForm),
        visible: false,
        dataRule: {
          paramKey: [{
            required: true,
            message: '参数名不能为空',
            trigger: 'blur'
          }],
          paramValue: [{
            required: true,
            message: '参数值不能为空',
            trigger: 'blur'
          }]
        }
      }
    },
    methods: {
      init(id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            getConfigInfo(this.dataForm.id).then(res => {
              if (res && res.code === 0) {
                this.dataForm.paramKey = res.config.paramKey
                this.dataForm.paramValue = res.config.paramValue
                this.dataForm.remark = res.config.remark
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            let params = {
              'id': this.dataForm.id || undefined,
              'paramKey': this.dataForm.paramKey,
              'paramValue': this.dataForm.paramValue,
              'remark': this.dataForm.remark
            }
            if (!this.dataForm.id) {
              // 创建用户
              createConfig(params).then(response => {
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
              updateConfig(params).then(response => {
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
