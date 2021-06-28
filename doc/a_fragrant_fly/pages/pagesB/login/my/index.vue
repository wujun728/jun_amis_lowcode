<template>
    <view class="uni-page-body">
        <view class="uni-flex" style="flex-direction: column;width: 100%;">
            <view class="uni-media-list-body uni-flex uni-row" style="height: 200upx;justify-content: center;padding-bottom: 60upx;">
                <view class="uni-media-list uni-flex uni-row" style="justify-content: center;">
                    <!-- 余额(元) -->
                </view>
                <view class="uni-media-list uni-flex uni-row" style="justify-content: center;font-size: 3em;">
                    <!-- {{detail.amount}} -->
                </view>

            </view>
            <view class="uni-list" v-if="loginList">

                <view class="uni-list-cell  uni-flex" hover-class="uni-list-cell-hover" >
                    <view class="uni-list-cell-navigate " style="width: 100upx;">
                        微信
                    </view>
                    <view class="uni-list-cell-navigate " @tap="tapTag('weixin')" style="flex: 1;justify-content: flex-end;">
                        <text v-if="loginList.weixin">解绑</text>
                        <text v-else>立即绑定</text>
                    </view>
                </view>

                <view class="uni-list-cell uni-flex " hover-class="uni-list-cell-hover" >
                    <view class="uni-list-cell-navigate " style="width: 100upx;">
                        手机号
                    </view>
                    <view class="uni-list-cell-navigate " @tap="tapTag('phone')" style="flex: 1;justify-content: flex-end;">
                        <text v-if="loginList.phone">解绑</text>
                        <text v-else>立即绑定</text>
                    </view>
                </view>

                <view class="uni-list-cell  uni-flex" hover-class="uni-list-cell-hover">
                    <view class="uni-list-cell-navigate " style="width: 100upx;">
                        电子邮箱
                    </view>
                    <view class="uni-list-cell-navigate " @tap="tapTag('email')" style="flex: 1;justify-content: flex-end;">
                        <text v-if="loginList.email">解绑</text>
                        <text v-else>立即绑定</text>
                    </view>
                </view>

            </view>

        </view>

    </view>
</template>

<script>
    import Request from "@/request/index.js";
    export default {
        computed: {
            loginList(){
               var userInfo=this.$store.getters.userInfo
               var list={
                   email:null,
                   phone:null,
                   weixin:null,
                   login:null
               }
               if(userInfo.login){
                   list.login=userInfo.login
               }
               for (let i in list) {
                   if(userInfo[i] && isNaN(userInfo[i].length)){
                       list[i]=userInfo[i];
                   }else if(userInfo['login_'+i] && isNaN(userInfo['login_'+i].length)){
                       list[i]=userInfo['login_'+i];
                   }
               }
               // console.log(userInfo)
               return list;
               
            },
            // loginList() {
            //     return this.detail
            // }
        },
        data() {
            return {
                detail: [],
                // detail: null
                // amount:'54.6'
            }
        },
        onLoad() {
            Request('UserLogin_BindList', {
                data: {}
            }).then(res => {
                // console.log(res)
                if (res.data.code === 200) {
                    this.detail = res.data.data
                } else {

                }
            })
        },
        methods: {
            tapTag(type) {
                if(!this.loginList[type]){
                 switch (type) {
                     case 'weixin':
                         uni.showToast({
                             title: '暂未开通',
                             icon: 'none'
                         })
                         break;
                     case 'phone':
                         // 去绑定手机
                         var url = 'bindPhone';
                         uni.navigateTo({
                             url: url
                         })
                         break;
                     case 'email':
                         // 去绑定邮箱
                         var url = 'bindEmail';
                         uni.navigateTo({
                             url: url
                         })
                         break;
                     default:
                         break;
                 }   
                }else{
                    uni.showToast({
                        title:'暂不支持解绑',
                        icon:'none'
                    })
                }
            },
        }
    }
</script>

<style >
    .uni-list-cell{
        flex-direction: row;
        justify-content: space-between;
    }
</style>
