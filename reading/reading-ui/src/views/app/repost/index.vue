<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="帖子类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择帖子类型" clearable size="small">
          <el-option v-for="dict in typeOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="帖子标题" prop="title">
        <el-input v-model="queryParams.title" placeholder="请输入帖子标题" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="用户账号id" prop="aid">
        <el-input v-model="queryParams.aid" placeholder="请输入用户账号id" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="创建时间" prop="createtime">
        <el-date-picker clearable size="small" v-model="queryParams.createtime" type="date" value-format="yyyy-MM-dd" placeholder="选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="帖子分类" prop="classType">
        <el-select v-model="queryParams.classType" placeholder="请选择帖子分类" clearable size="small">
          <el-option v-for="dict in classTypeOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="配图" prop="pictures">
        <el-input v-model="queryParams.pictures" placeholder="请输入配图" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="图片名称" prop="picnames">
        <el-input v-model="queryParams.picnames" placeholder="请输入图片名称" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete">删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="repostList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="图片名称" align="center" prop="id" />
      <el-table-column label="帖子类型" align="center" prop="type" :formatter="typeFormat" />
      <el-table-column label="帖子标题" align="center" prop="title" />
      <el-table-column label="用户账号id" align="center" prop="aid" />
      <el-table-column label="创建时间" align="center" prop="createtime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createtime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="帖子分类" align="center" prop="classType" :formatter="classTypeFormat" />
      <el-table-column label="内容" align="center" prop="content" />
      <el-table-column label="配图" align="center" prop="pictures" />
      <el-table-column label="图片名称" align="center" prop="picnames" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />

    <!-- 添加或修改帖子对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="帖子类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择帖子类型">
            <el-option v-for="dict in typeOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="parseInt(dict.dictValue)"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="帖子标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入帖子标题" />
        </el-form-item>
        <el-form-item label="用户账号id" prop="aid">
          <el-input v-model="form.aid" placeholder="请输入用户账号id" />
        </el-form-item>
        <el-form-item label="帖子分类" prop="classType">
          <el-select v-model="form.classType" placeholder="请选择帖子分类">
            <el-option v-for="dict in classTypeOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="内容">
          <el-input
            type="textarea"
            :rows="2"
            placeholder="请输入内容"
            v-model="form.content">
          </el-input>
<!--          <editor v-model="form.content" :min-height="192" />-->
        </el-form-item>
        <el-form-item label="配图" prop="pictures">
          <el-input v-model="form.pictures" placeholder="请输入配图" />
        </el-form-item>
        <el-form-item label="图片名称" prop="picnames">
          <el-input v-model="form.picnames" placeholder="请输入图片名称" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listRepost, getRepost, delRepost, addRepost, updateRepost, exportRepost } from "@/api/app/repost";
import Editor from '@/components/Editor';

export default {
  name: "Repost",
  components: {
    Editor,
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 帖子表格数据
      repostList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 帖子类型字典
      typeOptions: [],
      // 帖子分类字典
      classTypeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        type: null,
        title: null,
        aid: null,
        createtime: null,
        classType: null,
        content: null,
        pictures: null,
        picnames: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  mounted() {
    this.getList();
    this.getDicts("post_type").then(response => {
      this.typeOptions = response.data;
    });
    this.getDicts("post_class").then(response => {
      this.classTypeOptions = response.data;
    });
  },
  methods: {
    /** 查询帖子列表 */
    getList() {
      this.loading = true;
      listRepost(this.queryParams).then(response => {
        this.repostList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 帖子类型字典翻译
    typeFormat(row, column) {
      return this.selectDictLabel(this.typeOptions, row.type);
    },
    // 帖子分类字典翻译
    classTypeFormat(row, column) {
      return this.selectDictLabel(this.classTypeOptions, row.classType);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        type: null,
        title: null,
        aid: null,
        createtime: null,
        classType: null,
        content: null,
        pictures: null,
        picnames: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加帖子";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getRepost(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改帖子";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateRepost(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRepost(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除帖子编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delRepost(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有帖子数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return exportRepost(queryParams);
      }).then(response => {
        this.download(response.msg);
      })
    }
  }
};
</script>
