<template>
  <el-dialog :title="!dataForm.id ? '新增' : '修改'" :close-on-click-modal="false" :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" label-width="80px">
      <el-form-item label="角色名称" prop="roleName">
        <el-input v-model="dataForm.roleName" placeholder="角色名称"></el-input>
      </el-form-item>
      <el-form-item label="角色备注" prop="remark">
        <el-input v-model="dataForm.remark" placeholder="角色备注"></el-input>
      </el-form-item>
      <el-form-item label="角色授权">
        <el-checkbox v-model="menuExpand" @change="handleCheckedTreeExpand($event, 'menu')">展开/折叠</el-checkbox>
        <el-tree
          class="tree-border"
          :data="menuList"
          :props="treeProps"
          node-key="menuId"
          ref="menuListTree"
          show-checkbox>
        </el-tree>
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
    getRoleInfo,
    createRole,
    updateRole
  } from '@/api/sys/role'
  import {
    fetchList
  } from '@/api/sys/menu'
  import {
    treeDataTranslate
  } from '@/utils'
  const defaultDataForm = {
    id: 0,
    roleName: '',
    remark: ''
  }
  export default {
    data() {
      return {
        dataForm: Object.assign({}, defaultDataForm),
        visible: false,
        menuList: [],
        treeProps: {
          label: 'name',
          children: 'children'
        },
        menuExpand: false,
        dataRule: {
          roleName: [{
            required: true,
            message: '角色名称不能为空',
            trigger: 'blur'
          }]
        },
        tempKey: -666666 // 临时key, 用于解决tree半选中状态项不能传给后台接口问题
      }
    },
    methods: {
      init(id) {
        this.dataForm.id = id || 0
        fetchList().then(res => {
          this.menuList = treeDataTranslate(res.menuList, 'menuId')
        }).then(() => {
          this.visible = true
          this.$nextTick(() => {
            this.$refs['dataForm'].resetFields()
            this.$refs.menuListTree.setCheckedKeys([])
          })
        }).then(() => {
          if (this.dataForm.id) {
            getRoleInfo(this.dataForm.id).then(res => {
              if (res && res.code === 0) {
                this.dataForm.roleName = res.role.roleName
                this.dataForm.remark = res.role.remark
                var idx = res.role.menuIdList.indexOf(this.tempKey)
                if (idx !== -1) {
                  res.role.menuIdList.splice(idx, res.role.menuIdList.length - idx)
                }
                this.$refs.menuListTree.setCheckedKeys(res.role.menuIdList)
              }
            })
          }
        })
      },
      // 树权限（展开/折叠）
      handleCheckedTreeExpand(value, type) {
        if (type == 'menu') {
          let treeList = this.menuList;
          for (let i = 0; i < treeList.length; i++) {
            this.$refs.menuListTree.store.nodesMap[treeList[i].menuId].expanded = value;
          }
        }
      },
      // 表单提交
      dataFormSubmit() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            // 请求参数
            let params = {
              'roleId': this.dataForm.id || undefined,
              'roleName': this.dataForm.roleName,
              'remark': this.dataForm.remark,
              'menuIdList': [].concat(this.$refs.menuListTree.getCheckedKeys(), [this.tempKey], this.$refs
                .menuListTree.getHalfCheckedKeys())
            }
            if (!this.dataForm.id) {
              // 创建角色
              createRole(params).then(response => {
                this.$refs['dataForm'].resetFields();
                this.dataForm = Object.assign({}, defaultDataForm);
                this.$message({
                  message: '提交成功',
                  type: 'success',
                  duration: 1000
                })
                this.visible = false
                this.$emit('refreshDataList')
              });
            } else {
              // 更新角色
              updateRole(params).then(response => {
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
