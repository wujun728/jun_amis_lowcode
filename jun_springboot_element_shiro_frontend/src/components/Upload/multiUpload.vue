<template> 
  <div>
    <el-upload
      :class="uploadFileClass"
      :multiple="true"
      :action="localUploadUrl"
      :data="moduleData"
      list-type="picture-card"
      :file-list="fileList"
      :before-upload="beforeUpload"
      :on-remove="handleRemove"
      :on-change="handFileChange"
      :on-success="handleUploadSuccess"
      :on-preview="handlePreview"
      :limit="maxCount"
      :on-exceed="handleExceed">
      <i class="el-icon-plus"></i>
    </el-upload>
    <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过10MB
      <span v-if="prcScaleValue">，推荐图片比例：{{prcScaleValue}}</span>
    </div>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>
  </div>
</template>
<script>
  import {
    getToken
  } from '@/utils/auth'

  export default {
    name: 'multiUpload',
    props: {
      //图片属性数组
      value: Array,
      //模块名称
      module: String,
      // 图片比例说明
      prcScale: String,
      //控件样式名
      uploadClassName: String,
      //最大上传图片数量
      maxCount: {
        type: Number,
        default: 5
      }
    },
    data() {
      return {
        moduleData: {
          modelFile: this.$props.module,
          Authorization: getToken(),
        },
        dialogVisible: false,
        dialogImageUrl: null,
        prcScaleValue: this.$props.prcScale,
        localUploadUrl: this.$global.uploadUrl + '/backsystem/upload/uploadSingle',
        uploadNum: 0,
        oldImgLength: 0,
        uploadFileClass: this.$props.uploadClassName,
      };
    },
    computed: {
      fileList() {
        let fileList = [];
        for (let i = 0; i < this.value.length; i++) {
          fileList.push({
            url: this.$global.urlImg + this.value[i]
          });
        }
        return fileList;
      }
    },
    methods: {
      emitInput(fileList) {
        let inputValue = [];
        for (let i = 0; i < fileList.length; i++) {
          inputValue.push(fileList[i].url);
        }
        this.$emit('input', inputValue);
      },
      handleRemove(file, fileList) {
        let otherFileList = [];
        for (let i = 0; i < this.value.length; i++) {
          if (!file.url.endsWith(this.value[i])) {
            otherFileList.push({
              url: this.value[i]
            });
          }
        }
        this.emitInput(otherFileList);
      },
      handlePreview(file) {
        this.dialogVisible = true;
        this.dialogImageUrl = file.url;
      },
      beforeUpload(file) {
        this.oldImgLength = this.value.length;
        let _self = this;
        return true;
      },
      handFileChange(files, fileList) {
        var upload_img = document.getElementsByClassName(this.$props.uploadClassName)
        if (upload_img && upload_img.length > 0) {
          var upload = upload_img[0].getElementsByTagName('input');
          if (upload && upload.length > 0 && upload[0].files && upload[0].files.length > 0) {
            this.uploadNum = upload[0].files.length;
          }
        }
      },
      handleUploadSuccess(res, file) {
        let url = res.data.url;
        this.value.push(url);
        let resultFileList = [];
        for (let i = 0; i < this.value.length; i++) {
          resultFileList.push({
            url: this.value[i]
          });
        }
        console.log("上传图片数量", this.uploadNum);
        console.log("上传图片差值", this.value.length - this.oldImgLength);
        if ((this.value.length - this.oldImgLength) == this.uploadNum) {
          console.log("设置图片");
          this.emitInput(resultFileList);
        }
      },
      handleExceed(files, fileList) {
        this.$message({
          message: '最多只能上传' + this.maxCount + '张图片',
          type: 'warning',
          duration: 1000
        });
      },
    }
  }
</script>
