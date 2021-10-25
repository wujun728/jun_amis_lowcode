<template>
	<view>
		<cu-custom bgColor="bg-gradual-pink" :isBack="false">
			<block slot="content">主页</block>

		</cu-custom>
		<scroll-view scroll-y class="page">
			
			<view class="bg-img align-center" style="background-image: url('/static/mu/homebg.png');height: 1124upx;">
				
			</view>

			<uni-fab class="re-uni-fab" :pattern="pattern" :content="content" :horizontal="horizontal" :vertical="vertical"
			 :direction="direction" @trigger="trigger"></uni-fab>



		</scroll-view>

	</view>
</template>

<script>
	export default {
		data() {
			return {

				title: 'uni-fab',
				directionStr: '垂直',
				horizontal: 'right',
				vertical: 'top',
				direction: 'horizontal',
				pattern: {
					color: '#7A7E83',
					backgroundColor: '#fff',
					selectedColor: '#007AFF',
					buttonColor: '#007AFF'
				},
				content: [{
						iconPath: '/static/component.png',
						selectedIconPath: '/static/componentHL.png',
						text: '文件',
						active: false
					},
					{
						iconPath: '/static/template.png',
						selectedIconPath: '/static/templateHL.png',
						text: '帖子',
						active: false
					}
				]
			}
		},
		methods: {

			trigger(e) {
				console.log(e)
				if (e.index == 0) {
					console.log("上传文件页面")
					uni.navigateTo({
						url: '/pages/home/upload_file'
					});
				} else {
					uni.navigateTo({
						url: '/pages/home/edit_post'
					});
				}
				this.content[e.index].active = e.item.active
			},
			fabClick() {
				uni.showToast({
					title: '点击了悬浮按钮',
					icon: 'none'
				})
			},
		},
		components: {

		}
	}
</script>

<style>
	.home-container {
		height: 80%;
	}

	. {
		z-index: 999;
	}
</style>
