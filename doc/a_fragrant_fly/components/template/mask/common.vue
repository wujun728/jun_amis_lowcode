<template>
       <view v-if="state"  class="uni-flex uni-column" style="opacity: 0.5 ;position: absolute;width:100vw;z-index: 3; background: #555555;color: #E5E5E5;height: 100%;justify-content: center;align-items: center;text-align: center;" @tap="onTap">
            {{text}}
        </view>
</template>

<script>
	export default {
        props:{
            text:{
                type: String,
                default: "拒绝访问"
            },
            state:{
               default: 1
            },changes: {
                //监听变化
                type: Function,
                default: (e)=>{
                    // console.log(e)
                }
            }
        },
		methods:{
            onTap(){
                this.$emit('changes',this.state)
            }

		}
	}
</script>