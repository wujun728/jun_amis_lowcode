<template>
    <web-view src="http://zf.01film.cn/__UNI__5C4411E_0820231819.apk"></web-view>
</template>

<script type="text/javascript">
    export default {
        data() {
            return {
                src: 'http://zf.01film.cn/__UNI__5C4411E_0820231819.apk'
            }
        },
        computed: {
            url() {
                return this.src
            }
        },
        onLoad(e) {
            const downloadTask = uni.downloadFile({
                url:this.url, //仅为示例，并非真实的资源
                success: (res) => {
                    if (res.statusCode === 200) {
                        console.log('下载成功');
                    }
                }
            });
            
            downloadTask.onProgressUpdate((res) => {
                console.log('下载进度' + res.progress);
                console.log('已经下载的数据长度' + res.totalBytesWritten);
                console.log('预期需要下载的数据总长度' + res.totalBytesExpectedToWrite);
            
                // 测试条件，取消下载任务。
                if (res.progress > 50) {
                    downloadTask.abort();
                }
            });
            // this.src=e.src;
        }
    }
</script>

<style>
</style>
