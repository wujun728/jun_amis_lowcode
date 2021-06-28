<template>
    <view  @click="Tap(item)" @touchmove="touchmove" :style="'top:'+BtnTop+'px;left:'+BtnLeft+'px'" class="uni-flex iconfont"
        style="color: #1AAD19;position: fixed;z-index: 2;font-size: 100upx; width: 100upx;height: 100upx;border-radius: 50%;text-align: center;justify-content: center;align-items: center;">
        &#xe600;
    </view>
</template>

<script>
    export default {
        props: {
            marginButtom: {
                default: 50
            },
            marginTop: {
                default: 50
            },
            item: {
                default: 1
            },
            change: {
                type: Function,
                default: function() {}
            }
        },
        computed: {
            marButtom(){
                return this.fabWidth
            },
            marTop(){
                return this.fabWidth
            },
        },
        mounted() {
            this.windowSize = uni.getSystemInfoSync();
            this.fabWidth=uni.upx2px(100)+10
            this.BtnLeft = this.windowSize.windowWidth - this.fabWidth;
            this.BtnTop = this.windowSize.windowHeight - this.marButtom;
        },
        data() {
            return {
                BtnLeft: 300,
                BtnTop: 80,
                fabWidth:60,
                windowSize: {}
            }
        },
        methods: {
            Tap(item) {
                this.$emit('change', item)
            },
            touchmove(e) {
                // console.log(e)
                var BtnLeft = e.touches[0].clientX
                var BtnTop = e.touches[0].clientY
                var pageY = e.touches[0].pageY
                var pageX = e.touches[0].pageX
                if (pageX < 10) {
                    BtnLeft = 10
                }
                if (pageY < this.marTop) {
                    BtnTop = this.marTop
                }
                var windowWidth = this.windowSize.windowWidth;
                var windowHeight = this.windowSize.windowHeight;
                if (pageX > windowWidth - this.fabWidth) {
                    BtnLeft = windowWidth -this.fabWidth
                }
                if (pageY > windowHeight - this.marButtom) {
                    BtnTop = windowHeight - this.marButtom
                }
                // console.log(this.marButtom)
                this.BtnLeft = BtnLeft
                this.BtnTop = BtnTop
            }
        }

    }
</script>

<style>
</style>
