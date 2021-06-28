<template>
    <scroll-view v-if="bodyShow" class="" :style="'height:'+windowHeight+'px'" @scrolltoupper="scrolltoupper" style="flex: 1;"
        :lower-threshold="100" @scroll="scroll" @scrolltolower="scrolltolower" scroll-y>
<!--        <view v-if="bigAdData.length>0" style="height: 300upx;width: 100vw;">
            <big-ad ref="bigAd" :bigAd="bigAdData" :autoplay="bigAdAutoplay"></big-ad>
        </view> -->

        <view class="uni-flex-item">
            <index-shop-list ref="productList" :productList="productListData"></index-shop-list>
        </view>
        <view class="" @tap="loadData">
            <uni-load-more :loadingType="loadingType" :contentText="contentText"></uni-load-more>
        </view>
        <!-- <view style="height: 90upx;"></view> -->
    </scroll-view>
</template>
<script>
    import indexShopList from '../../components/template/product/shopList.vue';
    import bottomNav from '../../components/template/nav/bottom.vue';
    import bigAd from '../../components/template/swiper/big-ad.vue';
    import uniLoadMore from '../../components/template/unit/uni-load-more.vue';
    import {Url} from '@/common/yc_js/';
    import Request from '../../request/index.js'
    export default {
        components: {
            bigAd,
            bottomNav,
            indexShopList,
            uniLoadMore
        },
        props: ['windowHeight'],
        name: "indexIndex",
        data() {
            return {
                // windowHeight:0,//手机屏幕高度
                scrollTop: 0, //记录上次滚动的html标签与屏幕顶部的距离
                scrollTopPx: 100, //滚动条滚动范围距离上次大于xx像素开始触发事件
                scrollTime: 300, //滚动条定时器间隔触发时间毫秒 防止触发频率太高性能下降
                scrollTimeout: 0, //记录懒加载定时触发器
                refreshTimeout: 0, //上拉刷新定时器
                timeOut: 0,
                bodyShow: false,
                bigAdData: [], //大屏广告数据替换
                bigAdAutoplay: true, //大屏广告自动滚动开关
                loadingType: 1, //加载圈圈
                contentText: {
                    contentdown: "上拉显示更多",
                    contentrefresh: "正在加载...",
                    contentnomore: "没有更多数据了"
                },
                productListData: [], //传给子组件商品列表信息
                searchInput: '',
                index: 0,
                //loading:0,//下拉加载开启
                number: 2, //每页请求数量

            }
        },
        methods: {
            goTo(e) {
                // var host=this.$config.host;
                console.log('gotoindex')
                uni.navigateTo({
                    url: '/pages/goods/detail?' + Url.urlEncode(e)
                });
            },
            scrolltoupper() {

                // if(!this.bigAdAutoplay){
                console.log(this.bigAdAutoplay + '滚动顶部触发-开启自动滚动和下拉刷新功能')
                this.bigAdAutoplay = true;
                this.switchPullRefresh(true); //开启下拉刷新
                // }
            },
            scrolltolower(e) {
                console.log(this.loadingType);
                // 如果下来触发状态未关闭 将会触发请求服务器
                if (this.loadingType != 2) {
                    console.log("滚动底部触发")
                    clearTimeout(this.timeOut)
                    this.timeOut = setTimeout(() => {
                        this.loadData();
                    }, 100);

                }
                // 滚到底部或者右边触发
            },
            scroll(e) {
                var self = this;
                // console.log(e.detail.scrollTop)
                var Top = e.detail.scrollTop;
                // 控制顶部大屏广告在屏幕中触发自动滚动生效的所在位置
                if (Top > 10 && this.bigAdAutoplay) {
                    // console.log(this.bigAdAutoplay+'关闭自动滚动')
                    this.bigAdAutoplay = false;
                    this.switchPullRefresh(false); //关闭下拉刷新
                }
                // 控制商品列表 滚动条滚动达到距离 触发懒加载
                if (Top > this.scrollTop + this.scrollTopPx) {
                    this.scrollTop = Top;
                    clearTimeout(this.scrollTimeout);
                    this.scrollTime
                    this.scrollTimeout = setTimeout(() => {
                        // console.log('触发懒加载扫描')
                        this.$refs.productList.load()
                    }, self.scrollTime)
                }
            },
            loadData(action = 'add') {

                // console.log(action)
                if (action === 'refresh') {
                    this.productListData = [];
                    // var host=this.$config.host+"";//主服务器地址
                    Request("index").then((res) => {
                        if (res.data.code == 200) {
                            if (res.data.data.length < this.number) {
                                // 如果服务器端数据少于20条关闭下拉触发
                                this.loadingType = 0;
                            } else {
                                this.p = 0;
                                var bigAd = res.data.data.adList;
                                for (let i = 0; i < bigAd.length; i++) {
                                    // 判断图片地址是站内或站外，替换链接地址http
                                    let src = '';
                                    if (bigAd[i].image.indexOf("http") < 0) {
                                        src = this.$config.server.image;
                                    }
                                    bigAd[i].image = src + bigAd[i].image;
                                }
                                this.bigAdData = bigAd;
                                var shopList = res.data.data.shopList;
                                for (let i = 0; i < shopList.length; i++) {
                                    // 判断图片地址是否来自站外
                                    let src = '';
                                    if (shopList[i].image.indexOf("http") < 0) {
                                        src = this.$config.server.image;
                                    }
                                    shopList[i].juli = parseInt(shopList[i].id * 6 + 1) + 325 * 3; //这里只是模拟距离运算。

                                }
                                this.productListData = this.productListData.concat(shopList)

                            }
                        }
                        // console.log(res.data)

                        this.bodyShow = true;
                        uni.hideLoading();


                    })
                } else {
                    // return;
                    console.log("ajax")
                    var host = "shopList"; //主服务器地址
                    var rdata = {
                        p: this.p
                    };
                    Request(host,{data:rdata}).then((res) => {
                        var data = res.data.data;
                        console.log(res.data);
                        if (data.length < this.number) {
                            // 如果服务器端数据少于20条关闭下拉触发
                            this.loadingType = 2;
                        } else {
                            this.p = this.p + 1;
                            for (let i = 0; i < data.length; i++) {
                                // 判断图片地址是否来自站外
                                let src = '';
                                if (data[i].image.indexOf("http") < 0) {
                                    src = this.$config.server.image;
                                }
                                data[i].juli = parseInt(data[i].id * 6 + 1) + 325 * 3; //这里只是模拟距离运算。

                            }
                            this.productListData = this.productListData.concat(data)
                            this.bodyShow = true;
                        }
                        uni.hideLoading();

                    })
                }

            },
            tijiao(item) {
                console.log(item)
                uni.navigateTo({
                    url: item.url
                });

            },
            switchPullRefresh(status = true) {
                // console.log('is support:' + status);
                // #ifdef APP-PLUS
                // 5+ 手机端的下拉刷新功能
                const pages = getCurrentPages();
                const page = pages[pages.length - 1];
                const currentWebview = page.$getAppWebview();

                currentWebview.setStyle({
                    pullToRefresh: {
                        support: status,
                        style: plus.os.name === 'Android' ? 'circle' : 'default'
                    }
                });
                // #endif

            }
        },
        mounted: function() {
            var that = this;
            // el渲染完成触发
            this.$nextTick(function() {
                that.loadData('refresh'); //加载列表数据
            })

        }
    }
</script>

<style>
    .load-more {
        height: 3em;

    }
</style>