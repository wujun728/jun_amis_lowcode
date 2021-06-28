<template>
    <view class="uni-page-body">
        <scroll-view scroll-y :style="{height: contentHeight+'px'}">
            <view class="header" v-bind:class="{'status':isH5Plus}">
                <view class="userinfo" @tap="toPage('info')">
                    <view class="face">
                        <image :src="userinfo.headimg"></image>
                    </view>
                    <view class="info">
                        <view class="username">{{userinfo.nickName}}</view>
                        <view class="integral">积分:{{userinfo.integral}}</view>
                    </view>
                </view>
                <view class="setting iconfont" style="color: #fff;" @tap="toPage('info')">&#xe62a;</view>
            </view>
            <view class="uni-flex uni-row" style="padding: 5upx 0; box-sizing: border-box;justify-content: space-between;">
                <view class="uni-row uni-flex" style="justify-content: center;align-items: center;background: #0FAEFF;padding: 5px;color: #fff;">
                    板块
                </view>
                <view style="background: #FFFFFF;padding: 5px;justify-content:space-around;box-sizing: border-box;"
                    class="uni-flex uni-row uni-flex-item">
                    <button type="default" @tap="onTap('news')" size="mini">文章</button>
                    <button type="default" @tap="onTap('shopwindow')" size="mini">橱窗</button>
                    <button type="default" @tap="onTap('storehouse')" size="mini">仓库</button>
                </view>

            </view>
            <view class="list" v-for="(list,list_i) in severList" :key="list_i">
                <view class="li" v-for="(li,li_i) in list" @tap="toPage(li.url)" v-bind:class="{'noborder':li_i==list.length-1}"
                    hover-class="hover" :key="li.name">
                    <view class="item uni-flex uni-row">
                        <view class="iconfont" v-html="li.icon"></view>
                        <view class="text">{{li.name}}</view>
                        <view class="iconfont to">&#xe95a;</view>
                    </view>

                </view>
            </view>
            <!-- <view class="list"   hover-class="hover"  hover-stay-time="50" > -->
            <button class="" style="" v-if="hasLogin" @tap="toPage('logout')">
                退出登陆
            </button>
            <button class="" v-else @tap="toPage('login')">
                登陆
            </button>
            <!-- </view> -->
        </scroll-view>
    </view>
</template>
<script>
    import {Storage} from '@/common/yc_js/';
    export default {
        props: ['contentHeight'],
        data() {
            return {
                title: "我的",
                // winHeight:0,
                // 				userinfo:{
                // 					headimg:'../../static/HM-PersonalCenter/face.jpeg',
                // 					nickName:"未登录",
                // 					integral:"1435",
                // 					},
                orderTypeLise: [
                    //name-标题 icon-图标 badge-角标
                    {
                        name: '待付款',
                        icon: 'l1.png',
                        badge: 1
                    },
                    {
                        name: '待评价',
                        icon: 'l4.png',
                        badge: 9
                    },
                ],
                severList: [
                    [{
                            name: '我的收藏',
                            icon: '&#xe86f;',
                            url: '../goods/collect'
                        },
                        // 						{name:'优惠券',icon:'quan.png'},
                        // 						{name:'红包',icon:'momey.png'},
                        // 						{name:'任务',icon:'renw.png'},
                    ],
                    [
                        // 						{name:'积分明细',icon:'mingxi.png'},
                        // 						{name:'抽奖',icon:'choujiang.png'},
                        {
                            name: '收货地址',
                            icon: '&#xe8dd;',
                            url: '../user/address'
                        },
                        // {name:'银行卡',icon:'bank.png'},
                        {
                            name: '安全中心',
                            icon: '&#xe8c9;',
                            url: '../user/info'
                        },
                        {
                            name: '在线客服',
                            icon: '&#xe8b4;',
                            url: '../chat/chat'
                        }
                    ]
                ],
            };
        },
        computed: {
            isH5Plus() {
                //#ifdef APP-PLUS
                return true
                //#endif 
                return false
            },
            hasLogin() {
                return this.$store.getters.login;
            },
            userinfo() {
                return this.$store.getters.userinfo;
            }
        },
        mounted() {
            var that = this;
            // var isLogin=this.$store.dispatch('isLogin')
            function st() {
                return that.$store.dispatch('isLogin')
            }
            st().then(function(data) {
                if (data) {
                    console.log("镇的")
                } else {
                    console.log(data + "假的")
                }

            });
            //加载
            this.init();
            uni.setNavigationBarTitle({
                title: this.title
            });
        },
        onLoad() {

        },
        methods: {
            init() {

                // 				var self=this;
                // 				/**
                // 				 * 获取用户信息
                // 				 */
                // 				uni.getUserInfo({
                // 				  success: function(res){
                // 					  console.log(res.userInfo)
                // 					  var userinfo={
                // 					  	headimg:res.userInfo.avatarUrl,
                // 					  	nickName:res.userInfo.nickName,
                // 					  	integral:"1435"
                // 					  }
                // 					   self.$store.commit('userinfo',userinfo); 
                // 				  }
                // 				})

            },
            onTap(e) {
                switch (e) {
                    case 'news':
                        uni.showActionSheet({
                            itemList: ['新增', '我的收藏', '内容管理'],
                            success: (e) => {
                                switch (e.tapIndex) {
                                    case 0:
                                        var url = '/pages/pagesB/news/my/publish';
                                        break;
                                    case 1:
                                        var url = '/pages/pagesB/news/my/collect';
                                        break;
                                    case 2:
                                        var url = '/pages/pagesB/news/my/list';
                                        break;
                                    default:
                                        break;
                                }
                                uni.navigateTo({
                                    url: url
                                })
                            }
                        })
                        break;
                    case "shopwindow":

                        uni.showActionSheet({
                            itemList: ['新增', '店铺维护', '商品管理', '店铺分享'],
                            success: (e) => {
                                switch (e.tapIndex) {
                                    case 0:
                                        var url = '/pages/pagesB/shopwindow/my/publish';
                                        break;
                                    case 1:
                                        var url = '/pages/pagesB/shopwindow/my/collect';
                                        break;
                                    case 2:
                                        var url = '/pages/pagesB/shopwindow/my/list';
                                        break;
                                    default:
                                        var url = '';
                                        uni.showModal({
                                            title: '提示',
                                            content: '功能暂停使用',
                                            success: () => {

                                            }
                                        })
                                        break;
                                }
                                uni.navigateTo({
                                    url: url
                                })
                            }
                        })
                        break;
                    case "storehouse":

                        uni.showActionSheet({
                            itemList: ['新增商品/服务', '商品管理', '自定义分类'],
                            success: (e) => {
                                switch (e.tapIndex) {
                                    case 0:
                                        var url = '/pages/pagesB/storehouse/my/publish';
                                        break;
                                    case 1:
                                        var url = '/pages/pagesB/storehouse/my/list';
                                        break;
                                    case 2:
                                        var url = '/pages/pagesB/storehouse/my/classify';
                                        break;
                                    default:
                                        break;
                                }
                                uni.navigateTo({
                                    url: url
                                })
                            }
                        })
                        break;
                    default:
                        break;
                }
                console.log(e)
            },
            //用户点击订单类型
            toOrderType(index) {
                uni.showToast({
                    title: this.orderTypeLise[index].name
                });
            },
            //用户点击列表项
            toPage(url) {
                console.log(url)
                switch (url) {
                    case "login":
                        url = "../login/login";
                        break;
                    case "info":
                        url = "/pages/user/info";
                        break;
                    case "logout":
                        this.$store.commit('logout');
                        return false;
                        url = "../login/logout";
                        break;
                    default:
                        break;
                }
                uni.navigateTo({
                    url: url
                })
            }
        }
    }
</script>

<style lang="scss">
    // page {
    //     background-color: #fff
    // }

    .header {
        &.status {
            padding-top: var(--status-bar-height);
        }

        background-color:#ff6364;
        width:92%;
        height:30vw;
        padding:0 4%;
        display:flex;
        align-items:center;

        .userinfo {
            width: 90%;
            display: flex;

            .face {
                flex-shrink: 0;
                width: 15vw;
                height: 15vw;

                image {
                    width: 100%;
                    height: 100%;
                    border-radius: 100%
                }
            }

            .info {
                display: flex;
                flex-flow: wrap;
                padding-left: 15upx;

                .username {
                    width: 100%;
                    color: #fff;
                    font-size: 1em
                }

                .integral {
                    display: flex;
                    align-items: center;
                    padding: 0 10upx;
                    height: 20px;
                    color: #fff;
                    background-color: rgba(0, 0, 0, 0.1);
                    border-radius: 10upx;
                    font-size: 0.8em
                }
            }
        }

        .setting {
            flex-shrink: 0;
            width: 6vw;
            height: 6vw;

            image {
                width: 100%;
                height: 100%
            }
        }
    }

    .hover {
        background-color: #eee
    }

    .orders {
        background-color: #ff6364;
        width: 92%;
        height: 15vw;
        padding: 0 4%;
        margin-bottom: calc(5vw + 20upx);
        display: flex;
        align-items: flex-start;
        border-radius: 0 0 100% 100%;

        .box {
            width: 98%;
            padding: 0 1%;
            background-color: #fefefe;
            border-radius: 12upx;
            box-shadow: 0px 0px 10upx rgba(0, 0, 0, 0.15);
            margin-bottom: 50upx;
            display: flex;
            align-items: center;
            justify-content: center;

            .label {
                display: flex;
                align-items: center;
                justify-content: center;
                flex-flow: wrap;
                width: 100%;
                height: 16vw;
                color: #666666;
                font-size: 23upx;

            }
        }
    }

    .list {
        width: 100%;

        padding: 2px 0;
        box-sizing: border-box;
        // border-bottom: solid 13upx #f1f1f1;

        .li {
            .item {
                box-sizing: border-box;
                width: 100%;
                flex: 1;
                align-items: center;
                height: 100%;
                padding: 0 8px;
                background: #fff;
            }

            padding: 1px;
            box-sizing: border-box;
            height: 50px;

            &.noborder {
                border-bottom: 0
            }

            .icon {
                flex-shrink: 0;
                width: 50upx;

                image {
                    width: 50upx;
                }
            }

            .text {
                padding-left: 20upx;
                width: 100%;
                color: #666
            }

            .to {
                flex-shrink: 0;
                width: 40upx;
            }
        }
    }
</style>
