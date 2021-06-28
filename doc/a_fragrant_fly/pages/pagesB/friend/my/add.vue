<template>
    <view class="uni-page-body" :style="'height:'+winHeight+'px'">

        <scroll-view :style="'height:'+winHeight+'px'">
            <view class=" uni-media-list" style="display: flex;flex-direction: row;box-sizing: border-box;padding-left: 20upx;">
                <!-- 查找 -->
                <input type="text" class="uni-input uni-flex-item" style="width: 100%;border: 1px #BBBBBB solid;background: #F1F1F1;"
                    placeholder="请输入对方用户名" v-model="username">
                <view class="iconfont " @tap="getUser()" style="width: 50px;display: flex;align-items: center;justify-content: center;">
                    &#xe62f;</view>
                </input>

            </view>
            <view v-if="userInfoData" class="uni-list-cell" hover-class="uni-list-cell-hover" @tap="addFriend()" style="box-sizing: border-box;">
                <view class=" uni-media-list">
                    <view class="uni-media-list-logo " style="position: relative;">
                        <image :src="userInfoData.headimg" @error="userInfoData.headimg=''" style="z-index: 0;"></image>
                    </view>
                    <view class="uni-media-list-body" style="flex: 1;">
                        <view class="uni-media-list-text-top">
                            <text>{{userInfoData.nickname}}</text><text class="uni-badge" v-if="userInfoData.isHave">已添加</text>
                        </view>
                        <view class=" uni-ellipsis">{{userInfoData.individuality}}</view>
                    </view>
                    <view class="iconfont" style="width: 120upx;text-align: right;line-height: 70upx;">
                        &#xe657;
                    </view>
                </view>
            </view>

        </scroll-view>
    </view>
</template>

<script>
    import yc_js from "@/common/yc_js/index.js"
    var Time = yc_js.Time;
    import Request from "@/request/index.js";
    import config from "@/config/index.js"
    export default {
        onShow() {
            this.onshow = true;
            this.$store.commit('pages', this.pageName || "friendAdd")
        },
        data() {
            return {
                username: '',
                userInfo: null,
                title: "添加好友",
                winHeight: 0,
                contentHeight: 0,
            }
        },
        onLoad(e) {

            let winHeight = uni.getSystemInfoSync().windowHeight;
            //创建节点选择器 获取底部导航高度 
            this.contentHeight = (winHeight - uni.upx2px(100));
            this.winHeight = winHeight;

            uni.setNavigationBarTitle({
                title: this.title
            });

            if (e.username && e.username != 'null') {
                this.username = e.username
                this.getUser()
            }
        },
        computed: {
            userInfoData() {
                var userInfo = this.userInfo;
                var friendList = this.friendList
                if (userInfo && typeof userInfo == "object" && userInfo.user_id && typeof friendList == 'object' &&
                    friendList[userInfo.user_id]) {
                    userInfo.isHave = true;
                }
                return userInfo
            },
            friendList() {
                var friendList = this.$store.getters.friend || {};

                return friendList;
            }
        },
        methods: {
            addFriend() {
                var friendList = this.$store.getters.friend || {};

                if (this.userInfoData.isHave) {
                    uni.showToast({
                        title: '好友已存在,无需重复添加',
                        icon: 'none',
                        duration: 1000
                    });
                    return;
                }
                if (this.userInfoData.user_id == this.$store.getters.userInfo.id) {
                    uni.showToast({
                        title: '不能添加自己的',
                        icon: 'none',
                        duration: 1000
                    });
                    return;
                }
                uni.showLoading({
                    title: '请求中'
                });
                // uni.hideLoading();
                // console.log(JSON.stringify(this.userInfo))
                // return 
                Request('UserFriend_add', {
                        data: {
                            uid: this.userInfo.user_id
                        }
                    })
                    .then(e => {
                        // console.log(e.data)
                        if (e.data.code == 200) {
                            uni.hideLoading();

                            friendList[this.userInfo.user_id] = this.userInfo
                            // console.log(friendList)
                            this.$store.commit('friend', friendList)
                            // console.log(this.$store.getters.friend)
                            uni.showToast({
                                title: '添加成功',
                                icon: 'none',
                                duration: 1000
                            });
                            this.userInfo.isHave = true;
                        } else if (e.data.message) {
                            uni.showToast({
                                title: e.data.message || '未知错误',
                                icon: 'none',
                                duration: 1000
                            });
                        }
                    })
            },
            getUser() {
                uni.showLoading({
                    title: '请求中'
                });
                Request('UserUser_info', {
                        data: {
                            username: this.username
                        }
                    })
                    .then(e => {
                        uni.hideLoading();
                        console.log(JSON.stringify(e.data))
                        if (e.data.code == 200) {
                            var userInfo = e.data.data;
                            userInfo.headimg = config.getFileUrl(userInfo.headimg)
                            this.userInfo = userInfo
                        } else {
                            uni.showToast({
                                title: e.data.message || '未知错误',
                                icon: 'none'
                            })
                        }
                    })
            },

        }
    }
</script>

<style>
    .title {
        padding: 20upx;
    }
</style>
