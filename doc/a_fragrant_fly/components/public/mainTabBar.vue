<template>
    <view class="" style="position: fixed;width: 100%;bottom:0;background: #F7F7F7;">
        <nav-tab-bar :tabBar="bottomNav" @change="tabBarTap" :style="'height:'+TBarHeight+'px'"></nav-tab-bar>
    </view>
</template>
<!-- 自定义底部导航 -->
<script>
    export default {
        data() {
            return {
                TBarHeight: 50,
                bottomNav: [{
                    name: 'xiaoxi',
                    tag: '10',
                    text: '消息',
                    style: 'flex:1',
                    icon: '&#xe632;',
                }, {
                    name: 'tongxunlu',
                    text: '通讯录',
                    icon: '&#xe711;',
                    style: 'flex:1',
                }, {
                    name: 'faxian',
                    text: '发现',
                    icon: '&#xe605;',
                    style: 'flex:1',
                }, {
                    name: 'wode',
                    text: '我的',
                    icon: '&#xe604;',
                    style: 'flex:1',
                }]
            }
        },
        methods: {
            tabBarTap(e) {
//                 var url='/pages/pagesA/main/main'
//                 switch (e.name) {
//                     case 'xiaoxi':
//                     url='/pages/pagesA/chat/my/index'
//                         break;
//                     case 'tongxunlu':
//                     url='/pages/pagesB/friend/my/index'
//                         break;
//                     case 'faxian':
//                     url='/pages/pagesA/main/main'
//                         break;
//                     case 'wode':
//                     url='/pages/pagesB/user/my/wode'
//                         break;
//                     default:
//                         break;
// 
//                 }
//                 uni.navigateTo({
//                     url:url
//                 })
                this.$emit('change', e)
            }
        }
    }
</script>

<style>
</style>
