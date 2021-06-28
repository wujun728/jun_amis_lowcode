<template>
	<view class="search">
			<view class="search-text" @tap="tijiao(2)" @onceAgain="tijiao(2)">
				<icon  class="weui-icon-search_in-box search-icon"   @tap="search('searchInput')" :color="[searchInput?'#1AAD19':'']" type="search" size="14"></icon>
				<input type="text" v-model="searchInput"  placeholder="搜索" />
				<i v-if="searchInput" class="iconfont icon-guanbijiantou" @tap="clean('searchInput')"></i>
			</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			};
		}
	}
</script>

<style>

</style>
