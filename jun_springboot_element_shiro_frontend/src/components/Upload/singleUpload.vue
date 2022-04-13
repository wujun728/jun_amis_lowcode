<template> 
  <div>
    <el-upload
      :action="localUploadUrl"
      :data="moduleData"
      list-type="picture"
      :multiple="false"
      :show-file-list="showFileList"
      :file-list="fileList"
      :before-upload="beforeUpload"
      :on-remove="handleRemove"
      :on-success="handleUploadSuccess"
      :on-preview="handlePreview">
      <el-button size="small" type="primary">点击上传</el-button>
      <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过10MB
        <span v-if="prcScaleValue">，推荐图片比例：{{prcScaleValue}}</span>
      </div>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="fileList[0].url" alt="">
    </el-dialog>
  </div>
</template>
<script>
  import {
    getToken
  } from '@/utils/auth'

  export default {
    name: 'singleUpload',
    props: {
      value: String,
      // 模块路径名称
      module: String,
      // 图片比例说明
      prcScale: String,
    },
    computed: {
      imageUrl() {
        return this.value;
      },
      imageName() {
        if (this.value != null && this.value !== '') {
          return this.value.substr(this.value.lastIndexOf("/") + 1);
        } else {
          return null;
        }
      },
      fileList() {
        return [{
          name: this.imageName,
          url: this.$global.urlImg + this.imageUrl
        }]
      },
      showFileList: {
        get: function() {
          return this.value !== null && this.value !== '' && this.value !== undefined;
        },
        set: function(newValue) {}
      }
    },
    data() {
      return {
        moduleData: {
          modelFile: this.$props.module,
          Authorization: getToken(),
        },
        dialogVisible: false,
        prcScaleValue: this.$props.prcScale,
        localUploadUrl: this.$global.uploadUrl + '/backsystem/upload/uploadSingle',
      };
    },
    methods: {
      emitInput(val) {
        this.$emit('input', val)
      },
      handleRemove(file, fileList) {
        this.emitInput('');
      },
      handlePreview(file) {
        this.dialogVisible = true;
      },
      beforeUpload(file) {
        let _self = this;
        return true;
      },
      handleUploadSuccess(res, file) {
        this.showFileList = true;
        this.fileList.pop();
        let url = res.data.url;
        this.fileList.push({
          name: file.name,
          url: url
        });
        this.emitInput(this.fileList[0].url);
      }
    }
  }
</script>
