<template>
  <el-dialog :title="!dataForm.id ? '新增' : '修改'" :close-on-click-modal="false" :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" label-width="80px">
      <el-form-item label="菜单类型" prop="type">
        <el-radio-group v-model="dataForm.type">
          <el-radio v-for="(type, index) in dataForm.typeList" :label="index" :key="index">{{ type }}</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item :label="dataForm.typeList[dataForm.type] + '名称'" prop="name">
        <el-input v-model="dataForm.name" :placeholder="dataForm.typeList[dataForm.type] + '名称'"></el-input>
      </el-form-item>
      <el-form-item label="上级菜单" prop="parentName">
        <el-popover ref="menuListPopover" placement="bottom-start" trigger="click">
          <el-tree :data="menuList" :props="menuListTreeProps" node-key="menuId" ref="menuListTree"
            @current-change="menuListTreeCurrentChangeHandle" :default-expand-all="true" :highlight-current="true"
            :expand-on-click-node="false">
          </el-tree>
        </el-popover>
        <el-input v-model="dataForm.parentName" v-popover:menuListPopover :readonly="true" placeholder="点击选择上级菜单"
          class="menu-list__input"></el-input>
      </el-form-item>
      <el-form-item v-if="dataForm.type !== 2" label="菜单路由" prop="path">
        <el-input v-model="dataForm.path" placeholder="菜单路由"></el-input>
      </el-form-item>
      <el-form-item v-if="dataForm.type === 1" label="组件路径" prop="component">
        <el-input v-model="dataForm.component" placeholder="组件路径"></el-input>
      </el-form-item>
      <el-form-item v-if="dataForm.type !== 0" label="授权标识" prop="perms">
        <el-input v-model="dataForm.perms" placeholder="多个用逗号分隔, 如: user:list,user:create"></el-input>
      </el-form-item>
      <el-form-item v-if="dataForm.type !== 2" label="菜单排序" prop="orderNum">
        <el-input-number v-model="dataForm.orderNum" controls-position="right" :min="0"></el-input-number>
      </el-form-item>
      <el-form-item v-if="dataForm.type !== 2" label="菜单图标" prop="icon">
        <el-popover ref="iconListPopover" placement="bottom-start" trigger="click" popper-class="mod-menu__icon-popover">
          <div class="mod-menu__icon-inner">
            <div class="mod-menu__icon-list">
              <el-button v-for="(item, index) in iconList" :key="index" @click="iconActiveHandle(item)"
                :class="{ 'is-active': item === dataForm.icon }">
                <svg-icon :iconClass="item"></svg-icon>
              </el-button>
            </div>
          </div>
        </el-popover>
        <el-input v-model="dataForm.icon" v-popover:iconListPopover :readonly="true" placeholder="菜单图标名称" class="icon-list__input"></el-input>
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
    fetchMenuSelectList,
    getMenuInfo,
    createMenu,
    updateMenu
  } from '@/api/sys/menu'
  import {
    treeDataTranslate
  } from '@/utils'
  import Icon from '@/icons'
  const defaultDataForm = {
    id: 0,
    type: 1,
    typeList: ['目录', '菜单', '按钮'],
    name: '',
    parentId: 0,
    parentName: '',
    path: '',
    component: '',
    perms: '',
    orderNum: 0,
    icon: '',
    iconList: []
  }
  export default {
    data() {
      var validateComponent = (rule, value, callback) => {
        if (this.dataForm.type === 1 && !/\S/.test(value)) {
          callback(new Error('组件路径不能为空'))
        } else {
          callback()
        }
      }

      var validatePath = (rule, value, callback) => {
        if (this.dataForm.type !== 2 && !/\S/.test(value)) {
          callback(new Error('菜单路由不能为空'))
        } else {
          callback()
        }
      }
      return {
        dataForm: Object.assign({}, defaultDataForm),
        visible: false,
        dataRule: {
          name: [{
            required: true,
            message: '菜单名称不能为空',
            trigger: 'blur'
          }],
          parentName: [{
            required: true,
            message: '上级菜单不能为空',
            trigger: 'change'
          }],
          path: [{
            validator: validatePath,
            trigger: 'blur'
          }],
          component: [{
            validator: validateComponent,
            trigger: 'blur'
          }]
        },
        menuList: [],
        menuListTreeProps: {
          label: 'name',
          children: 'children'
        }
      }
    },
    created() {
      this.iconList = Icon.getNameList()
    },
    methods: {
      init(id) {
        this.dataForm.id = id || 0
        fetchMenuSelectList().then(res => {
          this.menuList = treeDataTranslate(res.menuList, 'menuId')
        }).then(() => {
          this.visible = true
          this.$nextTick(() => {
            this.$refs['dataForm'].resetFields()
          })
        }).then(() => {
          if (!this.dataForm.id) {
            // 新增
            this.menuListTreeSetCurrentNode()
          } else {
            // 修改
            getMenuInfo(this.dataForm.id).then(res => {
              if (res && res.code === 0) {
                this.dataForm.id = res.menu.menuId
                this.dataForm.type = res.menu.type
                this.dataForm.name = res.menu.name
                this.dataForm.parentId = res.menu.parentId
                this.dataForm.path = res.menu.path
                this.dataForm.component = res.menu.component
                this.dataForm.perms = res.menu.perms
                this.dataForm.orderNum = res.menu.orderNum
                this.dataForm.icon = res.menu.icon
                this.menuListTreeSetCurrentNode()
              }
            })
          }
        })
      },
      // 菜单树选中
      menuListTreeCurrentChangeHandle(data, node) {
        this.dataForm.parentId = data.menuId
        this.dataForm.parentName = data.name
      },
      // 菜单树设置当前选中节点
      menuListTreeSetCurrentNode() {
        this.$refs.menuListTree.setCurrentKey(this.dataForm.parentId)
        this.dataForm.parentName = (this.$refs.menuListTree.getCurrentNode() || {})['name']
      },
      // 图标选中
      iconActiveHandle(iconName) {
        this.dataForm.icon = iconName
      },
      // 表单提交
      dataFormSubmit() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            let params = {
              'menuId': this.dataForm.id || undefined,
              'type': this.dataForm.type,
              'name': this.dataForm.name,
              'parentId': this.dataForm.parentId,
              'path': this.dataForm.path,
              'component': this.dataForm.component,
              'perms': this.dataForm.perms,
              'orderNum': this.dataForm.orderNum,
              'icon': this.dataForm.icon
            }
            if (!this.dataForm.id) {
              // 创建用户
              createMenu(params).then(response => {
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
              updateMenu(params).then(response => {
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

<style lang="scss">
  .mod-menu {

    .menu-list__input,
    .icon-list__input {
      >.el-input__inner {
        cursor: pointer;
      }
    }

    &__icon-popover {
      width: 458px;
      overflow: hidden;
    }

    &__icon-inner {
      width: 478px;
      max-height: 258px;
      overflow-x: hidden;
      overflow-y: auto;
    }

    &__icon-list {
      width: 458px;
      padding: 0;
      margin: -8px 0 0 -8px;

      >.el-button {
        padding: 8px;
        margin: 8px 0 0 8px;

        >span {
          display: inline-block;
          vertical-align: middle;
          width: 18px;
          height: 18px;
          font-size: 18px;
        }
      }
    }

    .icon-list__tips {
      font-size: 18px;
      text-align: center;
      color: #e6a23c;
      cursor: pointer;
    }
  }
</style>
