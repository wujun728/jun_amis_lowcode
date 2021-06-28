<template>
    <view class="uni-tab-bar">


        <!-- #ifndef MP-BAIDU -->
        <scroll-view class="list" scroll-y   @scrolltolower="loadMore()" upper-threshold="50" @scrolltoupper="scrolltoupper">
            <view v-if="refreshing" class="uni-flex" style="align-content: center;justify-content: center;" @touchstart="touchstart">
                {{refreshText}}
            </view>
            <block v-for="(newsItem, newsIndex) in listData" :key="newsItem.id">
                <uni-media-list :options="newsItem" @button="tapbtn" @click="goDetail(newsItem)"></uni-media-list>

            </block>
            <view class="uni-tab-bar-loading">
                <view class="loading-more">{{loadingText}}</view>
            </view>
        </scroll-view>
        <!-- #endif -->
        <!-- #ifdef MP-BAIDU -->
        <view class="scroll-wrap" >
            <scroll-view class="list" scroll-y @scrolltolower="loadMore()" :style="scrollViewHeight"
                upper-threshold="50" @scrolltoupper="scrolltoupper">
                <view v-if="refreshing" class="uni-flex" style="align-content: center;justify-content: center;"
                    @touchstart="touchstart">
                    {{refreshText}}
                </view>
                <block v-for="(newsItem, newsIndex) in listData" :key="newsIndex">
                    <uni-media-list :options="newsItem" @button="tapbtn" @click="goDetail(newsItem)"></uni-media-list>
                </block>
                <view class="uni-tab-bar-loading">
                    <view class="loading-more">{{loadingText}}</view>
                </view>
            </scroll-view>
        </view>
        <!-- #endif -->
    </view>
</template>
<script>
    import Request from '@/request/index.js'
    import uniMediaList from '@/components/template/uni-media-list/uni-media-list-2.vue';
    import uniLoadMore from '@/components/template/uni-load-more/uni-load-more.vue';
    import {
        Time
    } from '@/common/yc_js/index.js';


    export default {
        components: {
            uniMediaList,
            uniLoadMore
        },
        data() {
            return {
                loadingText: {
                    contentdown: '上拉加载更多',
                    contentrefresh: '正在加载...',
                    contentnomore: '没有更多数据了'
                },
                isLoading: false,
                scrollLeft: 0,
                refreshing: false,
                refreshText: '点击可以刷新',
                // newsList: null,
                tabIndex: 0,
                tabBars: [],
                List:[],
                noData: {

                }
            }
        },
        computed: {
            listData(){
               return this.List 
            },
            scrollViewHeight() {
                return 'height:' + (uni.getSystemInfoSync().windowHeight) + 'px';
            }
        },
        watch: {
            isLoading(val) {
                if (val) {
                    uni.showLoading({
                        title: '加载中'
                    })
                    setTimeout(() => {
                        uni.hideLoading();
                    }, 2000);
                } else {
                    uni.hideLoading();
                }
            }
        },
        onLoad: function() {
            this.getList(0);

        },
        methods: {

            scrolltoupper() {
                // 滚到顶部显示刷新按钮 2秒自动消失
                this.refreshing = true;
                setTimeout(() => {
                    this.refreshing = false;
                }, 2000);
            },
            touchstart(e) {
                this.refreshing = true;
                // 点击刷新
                // console.log(e)
                this.onRefresh()
            },
            onRefresh(event) {
                this.getList(0);
            },
            loadMore(e) {
                //加载更多
                // console.log(e)
                this.getList(2);
            },
            getList(action = 1) {
                if (!this.isLoading) {
                    this.isLoading = true;

                    let requestParams = this.requestParams || {};

                    requestParams.time=requestParams.time || parseInt((new Date().getTime()) /
                        1000);

                    if (action == 0) {
                        // 刷新
                        requestParams.category_id = 0;
                       requestParams.time = parseInt((new Date().getTime()) /
                            1000);
                        this.List = [];
                       this.have = true;
                    } else if (action === 1) {

                        requestParams.minId = 0;
                    }
                    if (this.have) {
                        this.loadingText = '加载中...';


                        Request('UserNews_list', {
                            data: requestParams,
                            // responseType: 'arraybuffer',
                        }).then(result => {


                            this.isLoading = false;
                            // console.log(JSON.stringify(result))
                            if (result.statusCode == 200) {
                               
                                const data = result.data.data.map((news) => {

                                    var item = {
                                        id: news.id,
                                        article_type: 0,
                                        datetime: Time.dateTimeformat(news.create_time,
                                            "mm/dd hh:MM"),
                                        time: news.create_time,
                                        title: news.title + "\n",
                                        // news.abstract.substring(0,100)

                                        nickname: news.nickname,
                                        comment_count: news.comment_num,
                                    };

                                    var image_list = [];
                                    if (typeof news.image == 'object') {

                                        image_list = news.image.map((img) => {
                                            if (img && img.length > 6) {
                                                return {
                                                    url: this.$config.getFileUrl(img) + ""
                                                }
                                            }
                                        })
                                        delete news.image
                                        switch (image_list.length) {
                                            case 1:
                                                item.article_type = 3;
                                                break;
                                            case 2:
                                                item.article_type = 3;
                                                break;
                                            case 3:
                                                item.article_type = 4;
                                                break;
                                            default:
                                                item.article_type = 3;
                                                break;

                                        }

                                    } else {

                                        item.article_type = 3;
                                        if (news.image && news.image.length > 6) {
                                            image_list = [{
                                                url: this.$config.getFileUrl(news.image) +
                                                    ""
                                            }];

                                        }

                                    }
                                    item.image_url = image_list[0] ? image_list[0].url : null
                                    item.image_list = image_list;
                                    // console.log(item)

                                    return item

                                });

                                if (typeof data == 'object') {
                                    if (data.length > 0) {
                                        this.List = this.List.concat(data)
                                        requestParams.time = data[data.length - 1].time;
                                        requestParams.minId = data[data.length - 1].id;
                                    }
                                    if (data.length < 10) {
                                        this.have = false
                                        this.loadingText = '没有更多数据';

                                    } else {
                                        this.have = true;
                                    }

                                } else {

                                    this.have = false
                                    this.loadingText = '没有更多数据';
                                }

                            } else {
                                uni.showToast({
                                    title:result.data.message,
                                    icon:'none'
                                });
                                this.have = false
                                this.loadingText = '没有更多数据';
                            }
                            this.requestParams=requestParams
                            
                        })
                    }
                }
            },
            goDetail(detail) {
                detail.nickname = '匿名';
                // console.log(JSON.stringify(detail))
                uni.navigateTo({
                    url: '/pages/pagesA/news/public/detail?query=' + encodeURIComponent(JSON.stringify(detail))
                });
            },
            tapbtn(item){
                uni.showActionSheet({
                    itemList: ['删除'],
                    success:(res)=> {
              
                        
                        switch (res.tapIndex){
                            case 0:
                            this.dislike(item)
                                break;
                            default:
                                break;
                        }
                        // console.log('选中了第' + (res.tapIndex + 1) + '个按钮');
                    },
                    fail: function (res) {
                        console.log(res.errMsg);
                    }
                });
               // console.log(item) 
            },
            dislike(newsIndex) {
                // console.log([ newsIndex,this.List[newsIndex]])
                var data=this.List[newsIndex];
                uni.showModal({
                    title:"提示",
                    content: '确定删除？'+data.title,
                    success: (res) => {
                        if (res.confirm) {
                            if (!this.isLoading) {
                                this.isLoading=true;
                                this.loadingText = '加载中...';

                                Request('UserNews_delete', {
                                    data:{id:data.id},
                                    // responseType: 'arraybuffer',
                                }).then(result => {
                                     // console.log(result)
                                    if(result.data.code==200){
                                        this.List=this.List.filter(e=>{
                                            if(e.id!=data.id){
                                                return e
                                            }
                                        })
                                        console.log(this.List)
                                        uni.showToast({
                                            title:result.data.message
                                        });
                                    }else{
                                        uni.showToast({
                                            icon:'none',
                                            title:result.data.message
                                        });
                                    }
                                   

                                })

                                // this.tabBars[tabIndex].data.splice(newsIndex, 1);
                            }
                        }
                    }
                })
            },

//             async changeTab(event) {
//                 let index = event.detail.current;
//                 if (this.isClickChange) {
//                     this.tabIndex = index;
//                     this.isClickChange = false;
//                     return;
//                 }
//                 let tabBar = await this.getElSize('tab-bar');
//                 let tabBarScrollLeft = tabBar.scrollLeft;
//                 let width = 0;
// 
//                 for (let i = 0; i < index; i++) {
//                     let result = await this.getElSize(this.tabBars[i].ref);
//                     width += result.width;
//                 }
//                 let winWidth = uni.getSystemInfoSync().windowWidth,
//                     nowElement = await this.getElSize(this.tabBars[index].ref),
//                     nowWidth = nowElement.width;
//                 if (width + nowWidth - tabBarScrollLeft > winWidth) {
//                     this.scrollLeft = width + nowWidth - winWidth;
//                 }
//                 if (width < tabBarScrollLeft) {
//                     this.scrollLeft = width;
//                 }
//                 this.isClickChange = false;
//                 this.tabIndex = index;
// 
//                 // 首次切换后加载数据
//                 const activeTab = this.newsList[this.tabIndex];
//                 if (activeTab.data.length === 0) {
//                     this.getList();
//                 }
//             },
            // getNodeSize(node) {
            //     return new Promise((resolve, reject) => {
            //         dom.getComponentRect(node, (result) => {
            //             resolve(result.size);
            //         });
            //     });
            // },

            // getElSize(id) { //得到元素的size
            //     return new Promise((res, rej) => {
            //         uni.createSelectorQuery().select('#' + id).fields({
            //             size: true,
            //             scrollOffset: true
            //         }, (data) => {
            //             res(data);
            //         }).exec();
            //     });
            // },
//             async tapTab(index, tab) {
//                 //点击tab-bar
//                 if (tab && tab.ref == 'publish') {
//                     // console.log(tab)
//                     uni.navigateTo({
//                         url: '/pages/news/my/' + tab.ref,
//                         // url: '/pages/news/detail?query=' + encodeURIComponent(JSON.stringify(detail))
//                     });
//                 } else {
//                     if (this.tabIndex === index) {
//                         return false;
//                     } else {
//                         this.tabIndex = index;
//                         // 首次切换后加载数据
//                         const activeTab = this.tabBars[this.tabIndex];
//                         if (activeTab.data.length === 0) {
// 
//                             this.getList();
//                         }
//                     }
//                 }
//             }
        }
    }
</script>
<style>
    page {
        /* background-color: #999; */
        height: 100%;
        font-size: 11px;
        line-height: 1.8;
    }

    .uni-tab-bar {
        display: flex;
        flex: 1;
        flex-direction: column;
        overflow: hidden;
        height: 100%;
    }

    .uni-tab-bar .list {
        width: 750upx;
        height: 100%;
    }



    .uni-tab-bar .active {
        color: #007AFF;
    }


    .uni-tab-bar-loading {
        text-align: center;
        padding: 20upx 0;
        font-size: 14px;
        color: #CCCCCC;
    }
</style>
