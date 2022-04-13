<template>
  <el-dialog title="日志列表" :close-on-click-modal="false" :visible.sync="visible" width="75%">
    <el-form :inline="true">
      <el-form-item>
        <el-input v-model="listQuery.id" placeholder="任务ID" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="list" border v-loading="dataListLoading" height="460" style="width: 100%;">
      <el-table-column prop="logId" header-align="center" align="center" width="80" label="日志ID">
      </el-table-column>
      <el-table-column prop="jobId" header-align="center" align="center" width="80" label="任务ID">
      </el-table-column>
      <el-table-column prop="beanName" header-align="center" align="center" label="bean名称">
      </el-table-column>
      <el-table-column prop="params" header-align="center" align="center" label="参数">
      </el-table-column>
      <el-table-column prop="status" header-align="center" align="center" label="状态">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status === 0" size="small">成功</el-tag>
          <el-tag v-else @click.native="showErrorInfo(scope.row.logId)" size="small" type="danger"
            style="cursor: pointer;">失败</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="times" header-align="center" align="center" label="耗时(单位: 毫秒)">
      </el-table-column>
      <el-table-column prop="createTime" header-align="center" align="center" width="180" label="执行时间">
      </el-table-column>
    </el-table>
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
  </el-dialog>
</template>

<script>
  import { fetchLogList, getJobLogInfo } from '@/api/job/job'
  const defaultListQuery = {
    pageIndex: 1,
    pageSize: 10,
    id: ''
  };
  export default {
    data() {
      return {
        listQuery: Object.assign({}, defaultListQuery),
        visible: false,
        list: [],
        total: 0,
        dataListLoading: false
      }
    },
    methods: {
      init() {
        this.listQuery = Object.assign({}, defaultListQuery)
        this.visible = true
        this.getDataList()
      },
      // 获取数据列表
      getDataList() {
        this.dataListLoading = true
        fetchLogList(this.listQuery).then(response => {
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
      // 失败信息
      showErrorInfo(id) {
        getJobLogInfo(id).then(res => {
          if (res && res.code === 0) {
            this.$alert(res.log.error)
          } else {
            this.$message.error(res.msg)
          }
        })
      }
    }
  }
</script>
