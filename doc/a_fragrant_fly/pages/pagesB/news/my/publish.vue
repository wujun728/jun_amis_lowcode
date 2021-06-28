<template>
    <view class="uni-page-body" style="box-sizing: border-box;">

        <city-picker ref="classifyPicker" :dataList="classifyPickerData" :value="'id'" @onConfirm="onConfirmClass"></city-picker>
        <scroll-view class="" scroll-y="true">

            <view class="feedback-body">
                <textarea placeholder="请输入内容,首行前20位字符将自动生成标题..." style="border-bottom:1px solid   #F1F1F3;" v-model="form.content"
                    class="feedback-textare">
                    </textarea>
            </view>
            <view class="" style="padding-top: 5px;">
                <view class="uni-row uni-flex" style="justify-content: space-between;background: #fff;padding: 10px;">

                    <view class="uni-flex" style="align-self: center;width: 160upx;">
                        类别
                    </view>
                    <input type="text" disabled="true" :value="classify" placeholder="请选择" style="background: #f3f3f3;padding: 5px;flex: 1;"
                        @tap="classifyTap" />
                </view>
            </view>
            <view class="swiper-list" style="padding-top: 5px;height: auto;">
                <choose :count="count" :imgList="imgList" @changes="fileChange"></choose>
            </view>
            <compress ref="compress" :maxwh="maxwh" :quality="quality"> </compress>


            <!--            <view class="swiper-list">
                <view class="uni-list-cell uni-list-cell-pd feedback-title">
                    <view class="uni-list-cell-db ">图片是否压缩</view>
                    <switch :checked="isYasuo" @change="changeIndicatorDots" />
                </view>
            </view> -->

        </scroll-view>
        <button type="default" style="width: 100%;" @tap="send">提交</button>
    </view>
</template>

<script>
    import choose from "@/components/template/image/choose.vue"
    import compress from "@/components/template/image/compress.vue"
    import Request from "@/request/index.js"
    import maskCommon from "@/components/template/mask/common.vue"

    import {
        Img
    } from "@/common/yc_js/index.js";
    var YCImg = Img
    export default {
        name: 'newsPublish',
        components: {
            choose,
            compress,
            maskCommon
        },
        data() {
            return {
                classifyPickerData: null,
                // classifyPickerData: [
                //     [{
                //         id: 'bianju',
                //         label: '编剧'
                //     }, {
                //         id: 'daoyan',
                //         label: '导演'
                //     }, {
                //         id: 'cehua',
                //         label: '策划'
                //     }]
                // ],

                banner: '',
                name: '',
                isYasuo: true,
                count: 6,
                maxwh: 720,
                quality: 1,

                msgContents: ["界面显示错乱", "启动缓慢，卡出翔了", "UI无法直视，丑哭了", "偶发性崩溃"],
                stars: [1, 2, 3, 4, 5],
                imgList: [],
                classify: '',
                form: {
                    category_id: 0,
                    content: "",
                }
            }
        },
        computed: {
            hasLogin() {
                return this.$store.getters.hasLogin
            }
        },
        onShow() {

        },
        onLoad(event) {
            // 目前在某些平台参数会被主动 decode，暂时这样处理。
            if (event.query) {
                try {
                    this.banner = JSON.parse(decodeURIComponent(event.query));
                } catch (error) {
                    this.banner = JSON.parse(event.query);
                }
                var title = this.banner.name || '';
                uni.setNavigationBarTitle({
                    title: "发布[" + title + "]"
                });
                this.classify=title;
                this.form.category_id = this.banner.id;
            }



            Request('NewsCategory_list', {
                data: {
                    quality: 2
                },

            }).then(result => {

                uni.hideLoading();

                if (result.statusCode == 200) {
                    var newsList = result.data.data;

                    newsList.sort(function(a, b) {
                        if (a.ranking < b.ranking) {
                            return -1;
                        } else if (a.ranking > b.ranking) {
                            return 1;
                        } else {
                            return 0;
                        }
                    })


                    this.classifyPickerData = [newsList.map(e => {
                        // console.log(e)
                        // e.label=e.name;
                        return {
                            id: e.id,
                            label: e.name
                        }
                    })]

                    // Storage.Sync.set('newsList', this.newsList, 84000)
                    // console.log(this.classifyPickerData)
                    // this.getList(0);
                }
            })
            // console.log(this.banner)
        },
        methods: {
            onConfirmClass(e) {
                this.classify = e.label
                this.form.category_id = e.data[0].id

                uni.setNavigationBarTitle({
                    title: "发布[" + this.classify + "]"
                });
                // console.log(this.form)  
            },
            classifyTap() {
                this.$refs.classifyPicker.showPickerView(true);
            },
            // 是否压缩图片反选
            changeIndicatorDots(e) {
                this.isYasuo = !this.isYasuo
            },
            // 返回的图片列表
            fileChange(e) {
                this.imgList = e;

            },

            previewImage() { //预览图片
                uni.previewImage({
                    urls: this.imgList
                });
            },
            send() {
                console.log(this.form.category_id)
                if (!this.form.category_id) {
                    uni.showToast({
                        title: "请选择分类",
                        icon: "none"
                    })
                    return
                }
                //发送提交
                // console.log(JSON.stringify(this.form));
                if (this.imgList.length < 1) {
                    uni.showToast({
                        title: "至少添加一张图片",
                        icon: "none"
                    })
                    return
                }
                if (this.form.content.length < 8) {
                    uni.showToast({
                        title: "内容不足8字",
                        icon: "none"
                    })
                    return
                }

                function requst(imgs, data) {
                    var data = Object.assign({}, data);
                    // data.category_id = 17;
                    var content = data.content.split('\n');

                    data.title = content[0].substring(0, 28);
                    if (content[1]) {
                        data.content = '';
                        for (let i = 0; i < content.length; i++) {
                            if (i != 0) {
                                data.content = data.content + content[i]
                            } else {
                                if (content.length == 1) {
                                    data.content = content[i]
                                }

                            }
                        }
                    }

                    data.image = imgs; //base64 图片字符串
                    // console.log(JSON.stringify([imgs,data]));
                    Request('UserNews_add', {
                        data: {
                            form: data
                        }
                    }).then(res => {
                        if (res.statusCode === 200) {
                            uni.showToast({
                                title: "发布成功!"
                            });
                            uni.navigateBack();
                        } else {
                            uni.showToast({
                                title: "发布失败!" + res.data.message,
                                icon: 'none'
                            });
                        }
                    }).catch(e => {
                        console.log(e)
                    })

                }

                if (this.isYasuo) {
                    this.$refs.compress.yasuoImg(this.imgList).then(imgs => {

                        YCImg.imgsToBase64(imgs).then(base64All => {

                            requst(base64All, this.form)
                        })

                    })
                } else {
                    YCImg.imgsToBase64(this.imgList).then(base64All => {
                        // console.log(['转成base64',base64All])
                        requst(base64All, this.form)
                    })

                }
            }
        }
    }
</script>

<style>

</style>
