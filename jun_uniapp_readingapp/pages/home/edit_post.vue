<template>
	<view>
		<cu-custom bgColor="bg-gradual-pink" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">表单</block>
		</cu-custom>
		<l-file ref="lFile" @up-success="onSuccess"></l-file>
		<form>
			<view class="cu-form-group margin-top">
				<input placeholder="点击输入标题" name="input" @input="titleAInput"></input>
			</view>
			<view class="cu-form-group margin-top">
				<textarea maxlength="-1" @input="textareaAInput" placeholder="多行文本输入框"></textarea>
			</view>
			<view class="cu-bar bg-white margin-top">
				<view class="action">
					图片上传
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
		</form>
		<view class="padding">
			<button @tap="handleCommit">发布</button>
		</view>
		<view class="padding text-center">
			<view class="padding" style="font-size: 26rpx; text-align: center">
				<text decode>{{ tip }}</text>
			</view>
		</view>
	</view>
</template>

<script>
	import {
		insertPost
	} from '@/https/api/api.js'
	import PostItem from '@/pages/components/post/post-item.vue'
	export default {
		components: {
			"post-item": PostItem
		},
		data() {
			return {
				localPath: "",
				imgList: [],
				post: {
					title: "",
					content: "",
				},
				fileList: [],
				modalName: null,
				textareaAValue: '',
				tip: `
					*添加文件，文件格式必须为txt,rar,zip格式
					`,
				placeholder: "开始输入...",
				user:{}
			};
		},
		methods: {
			titleAInput(e) {
				this.post.title = e.detail.value
			},
			textareaAInput(e) {
				this.post.content = e.detail.value
				this.textareaAValue = e.detail.value
			},
			ChooseImage() {
				uni.chooseImage({
					count: 4, //默认9
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album'], //从相册选择
					success: (res) => {
						if (this.imgList.length != 0) {
							this.imgList = this.imgList.concat(res.tempFilePaths)
						} else {
							this.imgList = res.tempFilePaths
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
					title: '召唤师',
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
				this.fileList.push(fileName);
			},
			back2OriginPage() {
				uni.navigateTo({
					url: '/pages/index/index',
				})
			},
			handleCommit() {
				var picStr = this.fileList.join(";");
				console.log("picStr")
				console.log(picStr)
				this.user = this.$store.getters.getUserDetail;
				// if(!this.user.id){
				// 	uni.showToast({
				// 		title: "请先登录",
				// 		icon: 'none',
				// 		duration: 1000
				// 	});
				// }
				var reqData = {
					type: 1,
					classType: "1",
					title: this.post.title,
					content: this.post.content,
					aid: this.user.id,
					pictures:picStr,
				}
				console.log(reqData)
				insertPost(reqData).then(res=>{
					console.log(res);
					uni.showToast({
						title: res.data.msg,
						icon: 'none',
						duration: 1000
					});
					this.back2OriginPage();
				})
				
			}
		},
	};
</script>

<style>

</style>
