<style lang="scss">
    .list {
        padding: 1px;

        .list-box {
            padding: 8px;
            background: #fff;

            .li {
                .li-box {
                    display: flex;
                    flex-direction: row;

                    .tag {
                        .num{
                            padding: 0 2px;
                            line-height: 20px;
                            height: 20px;
                            min-width: 16px;
                            border-radius: 80px;
                            background: #BBBBBB;
                            color: #fff;
                            text-align: center;
                        }

                        display: flex;
                        flex-direction: row;
                        justify-content: center;
                        align-items: center;
                    }
                }
            }
        }
    }
</style>
<template>
    <view class="uni-page-body">
        <popup-input ref="popupInput"></popup-input>
        <view class="list" v-for="(ls,idx) in listData" :key="ls.id">
            <view class="list-box">
                <view class="li uni-flex uni-row" style="justify-content: space-between;">
                    <view class="li-box uni-flex-item" @tap="open(idx)">
                        <view class="uni-flex uni-column" style="padding: 0 5px;justify-content: center;">{{ls.name}}</view>
                        <view class="tag" style="">
                            <text class="num">{{ls.count}}</text>
                        </view>
                    </view>
                    <view class="li-box iconfont" @tap="tapOption(idx)">
                        &#xe8fa;
                    </view>
                </view>

            </view>
            <view v-if="index==idx" class="" style="padding-left:20px;box-sizing: border-box;">
                <view class="list uni-flex-item uni-flex" v-for="(item,i) in ls.goods" :key="item.id" @tap="tapItem(i)"
                    style="">
                    <view class="list-box uni-flex uni-flex-item">
                        <view class="li uni-flex uni-row uni-flex-item" style="background: #fff;">
                            <view class="li-box uni-flex-item uni-flex">
                                <image src="../../../../static/image/logo.png" style="width: 40px;height: 40px;" mode="aspectFill"></image>
                                <view class="tag" style="padding: 0 8px;">{{item.title}}</view>
                                <view class="tag" style="">
                                    <text class="num">{{item.image}}</text>
                                </view>
                            </view>
                        </view>

                    </view>
                </view>
            </view>

        </view>
        <button type="primary" @click="tapadd()">添加分类</button>
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
                index: 0,
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
                goods: [{
                    id: 124,
                    title: '中餐',
                    image: '3',
                    classify_id: 123
                }, {
                    id: 125,
                    title: '中餐',
                    image: '3',
                    classify_id: 124
                }, {
                    id: 126,
                    title: '中餐',
                    image: '3',
                    classify_id: 120
                }, {
                    id: 127,
                    title: '中餐',
                    image: '3',
                    classify_id: 123
                }],
                List: [{
                    ranking: 1,
                    id: 123,
                    name: '西点',
                    count: 100
                }, {
                    id: 124,
                    ranking: 1,
                    name: '中餐',
                    count: 3
                }, {
                    id: 125,
                    ranking: 1,
                    name: '冷饮',
                    count: 4
                }, {
                    id: 'no',
                    ranking: 1,
                    name: '未分类',
                    count: 51
                }],
                noData: {

                }
            }
        },
        computed: {
            // listObj() {
            //     var List = this.List;
            //     var ls = {};
            //     for (var i = 0; i < List.length; i++) {
            //         ls[List[i].id] = List[i];
            //     }

            //     return ls;
            // },
            listData() {
                var goods = this.goods;

                var list = this.List.filter(e => {
                    var gs = e.goods || [];

                    for (var i = 0; i < goods.length; i++) {
                        if (goods[i].classify_id == e.id) {
                            goods[i].classIs=true;
                            gs.push(goods[i]);
                            // goods = goods.slice(i, 1);
                            // i = i - 1;
                            console.log(goods[i]);
                        }
                    }

                    return e
                })
                var noClass=[];
                for (var i = 0; i < goods.length; i++) {
                    if(!goods[i].classIs){
                        noClass.push(goods[i])
                    }
                }
                list.push({goods:noClass,id:'no',name:'未分类'})


                return list
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
            // this.getList(0);

        },
        methods: {
            open(idx) {
                console.log(idx)
                this.index = idx;
            },
            tapOption(idx) {
                uni.showActionSheet({
                    itemList: ['删除', '添加宝贝', '全部宝贝'],
                    success: (e) => {
                        switch (e.tapIndex) {
                            case 0:
                                if ((this.List.length - 1) == idx) {
                                    uni.showModal({
                                        title: '提示',
                                        content: '不可删除'
                                    })
                                    return;
                                }
                                this.List = this.List.filter((e, i) => {
                                    if (i != idx) {
                                        return e
                                    }
                                })
                                break;
                            case 2:

                                this.index = idx
                                break;
                            default:
                                break;
                        }
                        // console.log(e)
                    }
                })
                // console.log(idx)
            },
            tapItem(idx) {
                uni.showActionSheet({
                    itemList: ['更换分类', '删除宝贝'],
                    success: (e) => {
                        switch (e.tapIndex) {
                            case 0:
                                if ((this.List.length - 1) == idx) {
                                    uni.showModal({
                                        title: '提示',
                                        content: '不可删除'
                                    })
                                    return;
                                }
                                this.List = this.List.filter((e, i) => {
                                    if (i != idx) {
                                        return e
                                    }
                                })
                                break;
                            case 2:

                                this.index = idx
                                break;
                            default:
                                break;
                        }
                        // console.log(e)
                    }
                })
            },
            tapadd() {
                var num = (Date.now() + "").substring(10, 13);
                this.$refs.popupInput.show({
                    title: '输入框',
                    content: [{
                        type: 'input',
                        class: 'uni-column ',
                        items: [{
                            style: 'flex:2;font-size:16px',
                            value: '',
                            name: 'content',
                            placeholder: '请输入',
                            // text: '请输入评论内容'
                        }]

                    }, {
                        type: 'button',
                        items: [{
                            value: '',
                            name: 'cancel',
                            text: '取消'
                        }, {
                            type: 'primary',
                            value: '',
                            name: 'submit',
                            text: '确认'
                        }]
                    }],
                    success: (e) => {
                        // console.log(e)
                        if (e.type == 'button') {
                            this.$refs.popupInput.show(null);

                            if (e.name == 'submit') {

                                var value = e.content[0].items[0].value
                                // var requestParam = this.requestParam;
                                this.List.unshift({
                                    id: num,
                                    name: value,
                                    count: num
                                })

                            }
                        }
                    }
                })

            },
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

                    requestParams.time = requestParams.time || parseInt((new Date().getTime()) /
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
                                    title: result.data.message,
                                    icon: 'none'
                                });
                                this.have = false
                                this.loadingText = '没有更多数据';
                            }
                            this.requestParams = requestParams

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
            tapbtn(item) {
                uni.showActionSheet({
                    itemList: ['删除'],
                    success: (res) => {


                        switch (res.tapIndex) {
                            case 0:
                                this.dislike(item)
                                break;
                            default:
                                break;
                        }
                        // console.log('选中了第' + (res.tapIndex + 1) + '个按钮');
                    },
                    fail: function(res) {
                        console.log(res.errMsg);
                    }
                });
                // console.log(item) 
            },
            dislike(newsIndex) {
                // console.log([ newsIndex,this.List[newsIndex]])
                var data = this.List[newsIndex];
                uni.showModal({
                    title: "提示",
                    content: '确定删除？' + data.title,
                    success: (res) => {
                        if (res.confirm) {
                            if (!this.isLoading) {
                                this.isLoading = true;
                                this.loadingText = '加载中...';

                                Request('UserNews_delete', {
                                    data: {
                                        id: data.id
                                    },
                                    // responseType: 'arraybuffer',
                                }).then(result => {
                                    // console.log(result)
                                    if (result.data.code == 200) {
                                        this.List = this.List.filter(e => {
                                            if (e.id != data.id) {
                                                return e
                                            }
                                        })
                                        console.log(this.List)
                                        uni.showToast({
                                            title: result.data.message
                                        });
                                    } else {
                                        uni.showToast({
                                            icon: 'none',
                                            title: result.data.message
                                        });
                                    }


                                })

                                // this.tabBars[tabIndex].data.splice(newsIndex, 1);
                            }
                        }
                    }
                })
            },

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
