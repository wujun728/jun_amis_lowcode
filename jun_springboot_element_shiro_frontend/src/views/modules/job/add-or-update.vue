<template>
  <el-dialog :title="!dataForm.id ? '新增' : '修改'" :close-on-click-modal="false" :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" label-width="100px">
      <el-form-item label="bean名称" prop="beanName">
        <el-input v-model="dataForm.beanName" placeholder="请输入spring bean名称, 如: testTask"></el-input>
      </el-form-item>
      <el-form-item label="请求参数" prop="params">
        <el-input v-model="dataForm.params" placeholder="请输入请求参数"></el-input>
      </el-form-item>
      <el-form-item label="cron表达式" prop="cronExpression">
        <el-input v-model="dataForm.cronExpression" placeholder="输入执行表达式, 如: 0 0 12 * * ?"></el-input>
      </el-form-item>
      <el-form-item label="任务备注" prop="remark">
        <el-input v-model="dataForm.remark" placeholder="请输入任务备注"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
      <el-button @click="visible = false">取消</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import { getJobInfo, createJob, updateJob } from '@/api/job/job'
  const defaultDataForm = {
    id: 0,
    beanName: '',
    params: '',
    cronExpression: '',
    remark: '',
    status: 0
  }
  export default {
    data() {
      return {
        dataForm: Object.assign({}, defaultDataForm),
        visible: false,
        dataRule: {
          beanName: [{
            required: true,
            message: '用户名不能为空',
            trigger: 'blur'
          }],
          cronExpression: [{
            required: true,
            message: 'cron表达式不能为空',
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
            getJobInfo(this.dataForm.id).then(res => {
              if (res && res.code === 0) {
                this.dataForm.beanName = res.schedule.beanName
                this.dataForm.params = res.schedule.params
                this.dataForm.cronExpression = res.schedule.cronExpression
                this.dataForm.remark = res.schedule.remark
                this.dataForm.status = res.schedule.status
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
              'jobId': this.dataForm.id || undefined,
              'beanName': this.dataForm.beanName,
              'params': this.dataForm.params,
              'cronExpression': this.dataForm.cronExpression,
              'remark': this.dataForm.remark,
              'status': !this.dataForm.id ? undefined : this.dataForm.status
            }
            if (!this.dataForm.id) {
              // 创建任务
              createJob(params).then(response => {
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
              // 更新任务
              updateJob(params).then(response => {
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
