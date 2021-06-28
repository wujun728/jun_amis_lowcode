<template>
	<view :style="{ height: statusBarHeight }" class="uni-status-bar">
		<slot />
	</view>
</template>

<script>
	
    var system= uni.getSystemInfoSync();
    var statusBarHeight = system.statusBarHeight || 0 ;
    // console.log(system)
    // console.log(statusBarHeight)
	export default {
		name: 'StatusBar',
		data() {
			return {
				statusBarHeight: statusBarHeight+'px'
			}
		}
	}
</script>

<style>
	.uni-status-bar {
		display: block;
		width: 100%;
		height: 20px;
		height: var(--status-bar-height);
	}
</style>