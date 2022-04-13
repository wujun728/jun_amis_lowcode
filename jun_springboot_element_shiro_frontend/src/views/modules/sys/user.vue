<template>
  <div class="app-container">
    <el-card class="filter-container" shadow="never">
      <div class="filter-title">
        <i class="el-icon-search"></i>
        <span>筛选搜索</span>
        <el-button size="small" class="filter-title-btn-search" type="primary" @click="getDataList()">
          搜索
        </el-button>
        <el-button size="small" class="filter-title-btn-reset" @click="handleResetSearch()">
          重置
        </el-button>
      </div>
      <div class="filter-content">
        <el-form :inline="true" :model="listQuery" size="small" label-width="100px">
          <el-form-item label="输入搜索：">
            <el-input v-model="listQuery.username" class="input-width" placeholder="用户名" clearable></el-input>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
    <el-card class="operate-container" shadow="never">
      <i class="el-icon-tickets"></i>
      <span>数据列表</span>
      <el-button size="small" class="btn-add" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      <el-button size="small" class="btn-add" type="primary" @click="addOrUpdateHandle()">新增用户</el-button>
    </el-card>
    <div class="table-container">
      <el-table :data="list" border v-loading="dataListLoading" @selection-change="selectionChangeHandle" style="width: 100%;">
        <el-table-column type="selection" header-align="center" align="center" width="50">
        </el-table-column>
        <el-table-column prop="userId" header-align="center" align="center" width="80" label="编号">
        </el-table-column>
        <el-table-column prop="username" header-align="center" align="center" label="用户名">
        </el-table-column>
        <el-table-column prop="email" header-align="center" align="center" label="邮箱">
        </el-table-column>
        <el-table-column prop="mobile" header-align="center" align="center" label="手机号">
        </el-table-column>
        <el-table-column prop="status" header-align="center" align="center" label="状态">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.status === 0" size="small" type="danger">禁用</el-tag>
            <el-tag v-else size="small">正常</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" header-align="center" align="center" width="180" label="创建时间">
        </el-table-column>
        <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">
          <template slot-scope="scope">
            <el-button type="primary" size="small" @click="addOrUpdateHandle(scope.row.userId)">修改</el-button>
            <el-button type="danger" size="small" @click="deleteHandle(scope.row.userId)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-pagination
      background
      class="table-pagination"
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="listQuery.pageIndex"
      :page-size="listQuery.pageSize"
      :total="total"
      :page-sizes="[10, 20, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
  import { fetchList, deleteUser } from '@/api/sys/user'
  import AddOrUpdate from './user-add-or-update'
  const defaultListQuery = {
    pageIndex: 1,
    pageSize: 10,
    username: ''
  };
  export default {
    data() {
      return {
        listQuery: Object.assign({}, defaultListQuery),
        list: [],
        total: 0,
        dataListLoading: false,
        dataListSelections: [],
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
      // 重置查询表单
      handleResetSearch() {
        this.listQuery = Object.assign({}, defaultListQuery);
        this.getDataList();
      },
      // 获取数据列表
      getDataList() {
        this.dataListLoading = true
        fetchList(this.listQuery).then(response => {
          this.list = response.page.list
          this.total = response.page.totalCount
          this.dataListLoading = false
        })
      },
      // 每页数
      sizeChangeHandle(val) {
        this.listQuery.pageSize = val
        this.listQuery.pageIndex = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle(val) {
        this.listQuery.pageIndex = val
        this.getDataList()
      },
      // 多选
      selectionChangeHandle(val) {
        this.dataListSelections = val
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
        var userIds = id ? [id] : this.dataListSelections.map(item => {
          return item.userId
        })
        this.$confirm(`确定对[id=${userIds.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteUser(userIds).then(res => {
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
