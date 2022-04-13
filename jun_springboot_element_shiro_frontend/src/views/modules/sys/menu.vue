<template>
  <div class="app-container">
    <el-card class="operate-container" shadow="never">
      <i class="el-icon-tickets"></i>
      <span>数据列表</span>
      <el-button size="small" class="btn-add" type="primary" @click="addOrUpdateHandle()">新增菜单</el-button>
    </el-card>
    <div class="table-container">
      <el-table :data="dataList" row-key="menuId" border style="width: 100%; ">
        <el-table-column prop="name" header-align="center" min-width="120" label="名称">
        </el-table-column>
        <el-table-column header-align="center" align="center" label="图标">
          <template slot-scope="scope">
            <svg-icon :iconClass="scope.row.icon || ''"></svg-icon>
          </template>
        </el-table-column>
        <el-table-column prop="type" header-align="center" align="center" label="类型">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.type === 0" size="small">目录</el-tag>
            <el-tag v-else-if="scope.row.type === 1" size="small" type="success">菜单</el-tag>
            <el-tag v-else-if="scope.row.type === 2" size="small" type="info">按钮</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="orderNum" header-align="center" align="center" label="排序">
        </el-table-column>
        <el-table-column prop="path" header-align="center" align="center" label="菜单路由">
        </el-table-column>
        <el-table-column prop="component" header-align="center" align="center" width="150" :show-overflow-tooltip="true" label="组件路径">
        </el-table-column>
        <el-table-column prop="perms" header-align="center" align="center" width="150" :show-overflow-tooltip="true" label="授权标识">
        </el-table-column>
        <el-table-column prop="createTime" header-align="center" align="center" width="160" label="创建时间">
        </el-table-column>
        <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">
          <template slot-scope="scope">
            <el-button type="primary" size="small" @click="addOrUpdateHandle(scope.row.menuId)">修改</el-button>
            <el-button type="danger" size="small" @click="deleteHandle(scope.row.menuId)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
  import { fetchList, deleteMenu } from '@/api/sys/menu'
  import AddOrUpdate from './menu-add-or-update'
  import { treeDataTranslate } from '@/utils'
  export default {
    data() {
      return {
        dataForm: {},
        dataList: [],
        dataListLoading: false,
        addOrUpdateVisible: false
      }
    },
    components: {
      AddOrUpdate
    },
    created() {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList() {
        this.dataListLoading = true
        fetchList().then(res => {
          this.dataList = treeDataTranslate(res.menuList, 'menuId')
          this.dataListLoading = false
        })
      },
      // 新增 / 修改
      addOrUpdateHandle(id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },
      // 删除
      deleteHandle(id) {
        this.$confirm(`确定对[id=${id}]进行[删除]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteMenu(id).then(res => {
            if (res && res.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1000
              })
              this.getDataList()
            } else {
              this.$message.error(res.msg)
            }
          })
        }).catch(() => {})
      }
    }
  }
</script>
