<template>
	<view>
		<cu-custom bgColor="bg-gradual-pink" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">表单</block>
		</cu-custom>
		<view class="">
			<l-file ref="lFile" @up-success="onSuccess"></l-file>
			<view class="cu-bar bg-white margin-top">
				<view class="action">
					文件 上传
				</view>
				<view class="action">
					{{imgList.length}}/4
				</view>
			</view>
			<view class="cu-form-group">
				<view class="grid col-4 grid-square flex-sub">
					<view class="bg-img" v-for="(item,index) in imgList" :key="index" @tap="ViewImage" :data-url="imgList[index]">
						<image :src="imgList[index]" mode="aspectFill"></image>
						<view class="cu-tag bg-red" @tap.stop="DelImg" :data-index="index">
							<text class='cuIcon-close'></text>
						</view>
					</view>
					<view class="solids" @tap="onUpload" v-if="imgList.length<4">
						<text class='cuIcon-cameraadd'></text>
					</view>
				</view>
			</view>
		</view>
		<button class="cu-btn block line-orange lg">
			<text @tap="onUpload" class="cuIcon-upload"></text> 上传
		</button>
		<view class="padding" style="font-size: 26rpx; text-align: center">
			<text decode>{{ tip }}</text>
		</view>

	</view>
</template>

<script>
	import {
		upload
	} from '@/https/api/api.js'
	export default {
		data() {
			return {
				imgList: [],
				fileList: [],
				tip: `
					*添加文件，文件格式必须为txt,rar,zip格式
					`,
			};
		},
		methods: {
			ChooseImage() {
				uni.chooseImage({
					count: 4, //默认9
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album'], //从相册选择
					success: (res) => {
						console.log(res);
						if (this.imgList.length != 0) {
							let file = new File()
							this.imgList = this.imgList.concat(res.tempFilePaths)
							this.fileList = this.fileList.concat(res.tempFiles)
						} else {
							this.imgList = res.tempFilePaths
							this.fileList = res.tempFiles
						}
					}
				});
			},
			ViewImage(e) {
				uni.previewImage({
					urls: this.imgList,
					current: e.currentTarget.dataset.url
				});
			},
			DelImg(e) {
				uni.showModal({
					title: '小帅哥',
					content: '确定要删除这段回忆吗？',
					cancelText: '再看看',
					confirmText: '再见',
					success: res => {
						if (res.confirm) {
							this.imgList.splice(e.currentTarget.dataset.index, 1)
						}
					}
				})
			},
			/* 上传 */
			onUpload() {
				console.log("upload")
				/**
				 * currentWebview: 当前webview
				 * url：上传接口地址
				 * name：附件key,服务端根据key值获取文件流，默认file,上传文件的key
				 * header: 上传接口请求头
				 */
				var uploadPath = this.appBaseUrl + '/app/common/upload'
				this.$refs.lFile.upload({
					// #ifdef APP-PLUS
					// nvue页面使用时请查阅nvue获取当前webview的api，当前示例为vue窗口
					currentWebview: this.$mp.page.$getAppWebview(),
					// #endif
					url: uploadPath,
					name: 'appfile',
					// header: {'Authorization':'token'},
					// 其他业务参数直接写key,value,如：
					// key1: 'value1',
					// key2: 'value2',
				});
			},
			onSuccess(res) {
				console.log('上传成功回调', JSON.stringify(res));
				var response = JSON.parse(res.data.id); 
				let fileName = response.fileName;
				let fileUrl = response.url;
				this.imgList.push(fileUrl);
			}
		},
	};
</script>

<style>
	.padding-sm {
		padding: 20upx;
	}

	.padding {
		padding: 30upx;
	}
</style>
