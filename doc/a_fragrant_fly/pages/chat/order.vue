<template>
	<view class="uni-column">
		<!-- <view class="content" id="content" :style="{height:contentHeight+'px'}"> -->
			<scroll-view id="scrollview" scroll-y="true" :style="{height:contentHeight+'px'}" :scroll-with-animation="true"
			    :scroll-top="scrollTop">
				<!-- <page-foot :name="name"></page-foot> -->
				<order-message v-for="(message,index) in messages" :key="index" :message="message" :id="index" @tap="goPage(message)"></order-message>
				<view id="bottom"></view>
			</scroll-view>
		<!-- </view> -->
		<view class="foot">
			<chat-input @send-message="getInputMessage" @focus="inputGetFocus"></chat-input>
		</view>
	</view>
</template>

<script>
	import chatInput from '../../components/template/im-chat/chatinput.vue';
	import orderMessage from '../../components/template/im-chat/orderMessage.vue';

	export default {
		data() {
			return {
				name:'xcecd@qq.com',
				style: {
					pageHeight: 0,
					contentViewHeight: 0,
					footViewHeight: 90,
					mitemHeight: 0,
				},
				scrollTop: 0,
				messages: [{
					user: '西部烧烤',
					sum:'188.00',
					sn:'11200wxdf02',
					type: 'head', //input,content 
					content: '羊肉串,羊腰,牛排,鸡翅中,烤鱿鱼,羊排,鸡脆骨,骨肉相连',
					time:'2019-01-22 16:15:00'
				},{
					user: '旺角茶吧',
					sum:'158.00',
					sn:'11200wxdf12',
					type: 'head', //input,content 
					content: '你好!',
					time:'2019-01-22 16:15:00'
				}]
			}
		},
		components: {
			chatInput,
			orderMessage
		},
		onLoad: function () {
			let winHeight = uni.getSystemInfoSync().windowHeight;
		//创建节点选择器 获取底部导航高度 
			this.contentHeight=(winHeight-uni.upx2px(100));
			this.winHeight = winHeight;
// 			const res = uni.getSystemInfoSync();
// 			this.style.pageHeight = res.windowHeight;
// 			this.style.contentViewHeight = res.windowHeight - uni.getSystemInfoSync().screenWidth / 750 * (100); //像素
		},
		methods: {
			goPage(e){
				console.log(e)
			},
			getInputMessage: function (message) { //获取子组件的输入数据
				// console.log(message);
				this.addMessage('customer', message.content, false);
				this.toRobot(message.content);
			},
			addMessage: function (user, content, hasSub, subcontent) {
				var that = this;
				that.messages.push({
					user: user,
					content: content,
					hasSub: hasSub,
					subcontent: subcontent
				});
			},
			scrollToBottom: function () {
				var that = this;
				var query = uni.createSelectorQuery();
				query.selectAll('.m-item').boundingClientRect();
				query.select('#scrollview').boundingClientRect();

				query.exec(function (res) {
					that.style.mitemHeight = 0;
					res[0].forEach(function (rect) {
						// console.info(rect.height);
						that.style.mitemHeight = that.style.mitemHeight + rect.height + 20;
					});

					if (that.style.mitemHeight > that.style.contentViewHeight) {
						that.scrollTop = that.style.mitemHeight - that.style.contentViewHeight;
					}
				});
			},
			toRobot: function (info) {

				// this.addMessage('home', info, false);
				var apiUrl = 'http://www.tuling123.com/openapi/api';
				uni.request({
					url: apiUrl,
					data: {
						"key": 'acfbca724ea1b5db96d2eef88ce677dc',
						"info": info,
						"userid": 'uni-test'
					},
					success: (res) => {
						console.log("s", res);
						this.addMessage('home', res.data.text, false);
						this.scrollToBottom();
						console.log('request success:' + res.data.text);
					},
					fail: (err) => {
						console.log('request fail', err);
						uni.showModal({
							content: err.errMsg,
							showCancel: false
						})
					}
				});
			}
		}
	}
</script>

<style>
	.uni-column {
		display: flex;
		flex-direction: column;
	}

	.content {

		display: flex;
		flex: 1;
		margin-bottom: 100upx;
	}

	.foot {
		position: fixed;
		width: 100%;
		height: 90upx;
		min-height: 90upx;
		left: 0upx;
		bottom: 0upx;
		overflow: hidden;
	}
</style>
