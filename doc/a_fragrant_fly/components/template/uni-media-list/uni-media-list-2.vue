<template>
    <view>

        <view class="list-cell" hover-class="uni-list-cell-hover">
            <view class="media-list" v-if="options.title">
                <view   @click="bindClick" :class="{'media-image-bottom': options.article_type === 1,'media-image-right': options.article_type === 2 , 'media-image-left': options.article_type === 3}">
                    <text class="media-title" :class="{'media-title1': options.article_type === 1,'media-title2': options.article_type === 2}">{{options.title}}</text>
                    <view v-if="options.image_list" class="image-section" :class="{'image-section-bottom': options.article_type === 1,'image-section-right': options.article_type === 2, 'image-section-left': options.article_type === 3, 'image-section-more': options.article_type === 4}">

                        <image style="padding: 5px;" :class="{'image-list1': options.article_type === 1,'image-list2': options.article_type === 2,'image-list3': options.article_type === 3,'image-list4': options.article_type === 4}"
                            mode="aspectFill" :src="source.url" v-for="(source, idx) in options.image_list" :key="idx">
                        </image>

                    </view>
                </view>
                <view class="media-foot">
                    {{options.note}}
                </view>
                <view class="media-foot">
                    <view class="media-info">
                        <text class="info-text" v-if="options.source">{{options.source}}</text>
                        <text class="info-text" v-if="options.comment_count">{{options.comment_count}}条评论</text>
                        <text class="info-text" v-if="options.datetime">{{options.datetime}}</text>
                    </view>
                    <view class="max-close-view" >
                        <!-- <button type="primary" size="mini">删除</button> -->
                        <view class="close-view" hover-class v-for="(btn,idx) in button" :key="idx">
                            <view class="close iconfont" style="font-size: 0.9em;" v-html="btn.icon" @click="tapBtn(options,idx)"></view>
                        </view>
                        <!-- <view class="close-view" hover-class><text class="close iconfont" style="font-size: 0.7em;">&#xe629;</text></view> -->
                    </view>
                </view>
            </view>
        </view>
    </view>
</template>

<script>
    // options.article_type=0 标题文字与图片为横竖结构 多图片为数组options.image_list=[{src:'tu.jpg'}]
    // options.article_type=1 标题与文字图片为横向结构 只显示单图片大图 
    // options.article_type=2 标题与文字图片为横向结构 只显示单图片 100%宽度  限高280px高度 
    export default {
        props: {
            button: {
                type: Array,
                default: function(e) {
                    return [{
                        text: null,
                        icon: '&#xe62a;'
                    }]
                }
            },
            options: {
                type: Object,
                default: function(e) {
                    return {}
                }
            }
        },
        methods: {

            tapBtn(e) {
                this.$emit('button',e);
            },
            bindClick() {
                this.$emit('click');
            }
        }
    }
</script>

<style>
    view {
        display: flex;
        /* background: rgba(0,0,0,0.5); */
        flex-direction: column;
        box-sizing: border-box;
    }

    .list-cell {

        width: 750upx;

        padding: 8upx 15upx;
    }

    .uni-list-cell-hover {
        background-color: #eeeeee;
    }

    .media-list {
        /* padding: 5px; */
        background: #FFFFFF;
        flex: 1;
        flex-direction: column;
        /* padding: 10upx 15upx; */
    }

    .media-image-right {
        flex-direction: row;
    }

    .media-image-left {
        flex-direction: row-reverse;
    }

    .media-title {
        flex: 1;
    }

    .media-title {
        lines: 3;
        text-overflow: ellipsis;
        font-size: 32upx;
        color: #555555;
    }

    .media-title2 {
        flex: 1;
        margin-top: 6upx;
        line-height: 40upx;
    }

    .image-section {
        margin-top: 20upx;
        flex-direction: row;
        justify-content: space-between;
    }

    .image-section-right {
        margin-top: 0upx;
        margin-left: 10upx;
        width: 225upx;
        max-height: 146upx;
    }

    .image-section-left {
        margin-top: 0upx;
        margin-right: 10upx;
        width: 225upx;
        max-height: 146upx;
    }

    .image-section-bottom {
        margin-top: 0upx;
        margin-right: 10upx;
        width: 690upx;
    }

    .image-section-more {
        display: flex;
        flex-wrap: wrap;
        box-sizing: border-box;
        justify-content: space-between;
    }

    .image-section-more:after {
        display: block;
        content: "";
        width: 32%;
        height: 0px;

    }

    .image-list {
        width: 100%;
        height: 481upx;
    }

    .image-list1 {
        width: 100%;
        height: 292upx;
    }

    .image-list2 {
        width: 337upx;
        height: 146upx;
    }

    .image-list3 {
        width: 225upx;
        height: 146upx;
    }

    .image-list4 {
        padding: 4upx;
        width: 225upx;
        height: 146upx;
        box-sizing: border-box;
    }

    .media-info {
        flex-direction: row;
    }

    .info-text {
        margin-right: 20upx;
        color: #999999;
        font-size: 24upx;
    }

    .media-foot {
        color: #999999;
        font-size:0.7em ;
        padding:5px 5px 0 5px;
        /* margin-top: 20upx; */
        flex-direction: row;
        justify-content: space-between;
    }

    .max-close-view {
        justify-content: flex-end;
        flex-direction: row;
        align-items: center;
        height: 40upx;
        width: 80upx;
    }

    .close-view {

        justify-content: center;
        height: 30upx;
        width: 70upx;
        line-height: 30upx;
        padding-bottom: 5upx;
    }

    .close {
        text-align: center;
        color: #999999;
        font-size: 28upx;
    }
</style>
