<template>
    <view v-if="!state" class="uni-flex uni-column" style="opacity: 0.5 ;position: absolute;width:100vw;z-index: 3; background: #555555;color: #E5E5E5;justify-content: center;align-items: center;text-align: center;"
        @tap="onTap">
        {{text}}
    </view>
</template>

<script>
    export default {
        props: {
            text: {
                type: String,
                default: '通讯已断开,点击重连'
            }
        },
        computed: {
            state() {
                return this.$store.getters.socketOpen
            }
        },
        methods: {
            onTap() {
                this.$store.dispatch('isLogin').then(e => {
                    if(e){
                        this.$store.dispatch('socketConnect')
                    }
                })
            }

        }
    }
</script>
