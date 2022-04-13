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
            <el-input v-model="listQuery.key" class="input-width" placeholder="用户名／用户操作" clearable></el-input>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
    <div class="table-container">
      <el-table :data="list" border v-loading="dataListLoading" style="width: 100%">
        <el-table-column prop="id" header-align="center" align="center" width="80" label="编号">
        </el-table-column>
        <el-table-column prop="username" header-align="center" align="center" label="用户名">
        </el-table-column>
        <el-table-column prop="operation" header-align="center" align="center" label="用户操作">
        </el-table-column>
        <el-table-column prop="method" header-align="center" align="center" width="150" :show-overflow-tooltip="true"
          label="请求方法">
        </el-table-column>
        <el-table-column prop="params" header-align="center" align="center" width="150" :show-overflow-tooltip="true"
          label="请求参数">
        </el-table-column>
        <el-table-column prop="time" header-align="center" align="center" label="执行时长(毫秒)">
        </el-table-column>
        <el-table-column prop="ip" header-align="center" align="center" width="150" label="IP地址">
        </el-table-column>
        <el-table-column prop="createDate" header-align="center" align="center" width="180" label="创建时间">
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
  </div>
</template>

<script>
  import { fetchList } from '@/api/sys/log'
  const defaultListQuery = {
    pageIndex: 1,
    pageSize: 10,
    key: ''
  };
  export default {
    data() {
      return {
        listQuery: Object.assign({}, defaultListQuery),
        list: [],
        total: 0,
        dataListLoading: false,
        selectionDataList: []
      }
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
      }
    }
  }
</script>
