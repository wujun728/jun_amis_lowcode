<template>
	<view>
		<view class="imageView">
			<image class="imageC" @click.stop="handleClick()"  :src="src"></image>
		</view>
	</view>
</template>

<script>	
	export default{
		data(){
			return{
			}
		},
		props:{
			src: {
				type : String,
				default : ""
			}
		},
		methods:{
			handleClick(){
				if(this.$store.hasLogin){
					uni.navigateTo({
					    url: '../../test/test'
					});
				}else{
					uni.navigateTo({
					    url: '../../Login/Login'
					});
				}
				
			}
		}
	}
</script>

<style scoped>
	.imageView{
		width: 100%;
		height: 100%;
		padding: 5%;
	}
	.imageC{
		border-radius: 50%;
		width: 90%;
		height: 90%;
	}
</style>
