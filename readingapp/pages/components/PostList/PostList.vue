<template>
	<view class="postlist-text">
		<uni-list>
			<!-- 自定义右侧内容 -->
			<uni-list-chat :title="postDetail.title" :avatar="postDetail.avatarUrl" :note="postDetail.note" time="2020-02-02 20:20">
				<!-- 自定义 header -->
				<view>
					<text>{{postDetail.timeNote}}</text>
					<!-- 需要使用 uni-icons 请自行引入 -->
					<uni-icons type="star-filled" color="#999" size="18"></uni-icons>
				</view>
			</uni-list-chat>
			<uni-list-item :border="false" :ellipsis="1" :title="postDetail.content"
			 rightText="更多" />
			<!-- 垂直排列，图文混排 -->
			<uni-list-item direction="column" :border="false">
				<template v-slot:body>
					<view>
						<uni-grid :column="3">
							<uni-grid-item v-for="obj in postDetail.imageUrl">
								<image :src="obj.src" mode="scaleToFill"></image>
							</uni-grid-item>
						</uni-grid>
					</view>
				</template>
			</uni-list-item>
			<uni-list-item :border="false">
				<template v-slot:body>
					<view>
						<text>点赞 收藏 转发</text>
					</view>
				</template>
			</uni-list-item>
		</uni-list>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {

		},
		props: {
			postDetail: {
				
			}
		}
	}
</script>

<style scoped>
	.postlist-text {
		color: #000000;
	}
</style>
