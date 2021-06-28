<template>
    <!--    <view class="yc-content" @touchstart="touchstart" @touchmove="touchmove" @touchcancel="touchcancel" @touchend="touchend"
        style="position: relative;"> -->
    <view class="">
        <scroll-view :scroll-top="scrollTop" :style="'height:'+contentHeight+'px'" scroll-y="true" class="scroll-Y"
            @scrolltoupper="upper" @scrolltolower="scrolltolower" lower-threshold=50  @scroll="scroll">
            <view class="yc-nav" style="position:fixed; top: 0;z-index: 6;" :style="'background: rgba(253,253,253,'+opacity+')'">
                <view class="yc-nav-domain">
                    <view class="yc-nav-domain-left" style="width: 100px;" @tap="tapCity">
                        <view class="iconfont" style="padding-right: 5px;box-sizing: border-box;">
                            &#xe613;
                        </view>
                        <view class="">
                            {{city}}
                        </view>
                    </view>
                    <view class="yc-nav-domain-right" style="position: relative;border-radius: 20px;" :style="'background: rgba(230,230,230,'+opacity+')'"
                        @tap="goPage({name:'搜索页面'})">
                        <view class="yc-nav-input" style="padding:0 5px 0 30px;width: 100%;font-size: 0.7em;color: #999999;">
                            搜索商家或商品的名字
                        </view>
                        <view class="iconfont" style="position: absolute;left: 7px;font-size:0.9em ;">
                            &#xe603;
                        </view>
                    </view>

                </view>
            </view>
            <view id="ad">

                <swiper style="height: 160px;width: 100%;" :indicator-dots="indicatorDots" :autoplay="autoplay"
                    :interval="interval" :duration="duration">
                    <swiper-item v-for="(item,idx) in aD" :key="idx">
                        <view class="swiper-item uni-bg-red" style=";">
                            <image :src="item.img" mode="aspectFill" style="height: 160px;width: 100%;display: flex;flex-direction: column;justify-content: ;"></image>
                        </view>
                    </swiper-item>
                </swiper>

            </view>
            <view class="yc-content-top" style="display:flex;width: 100%; flex-wrap: wrap;justify-content: center;align-items: center;background: #fff;">
                <view v-for="(cls,key) in  classAll" :key="key" class="" style="width: 20%;display: flex;justify-content: center;align-content: center;box-sizing: border-box;padding: 5px;">
                    <view @tap="tapClass(cls)" class="" style="display: flex;justify-content: center;align-items: center;flex-direction: column;">
                        <view v-if="cls.img" class="iconfont" style="display: flex;justify-content: center;align-items: center;">
                            <image :src="cls.img" mode="" :class="cls.class?cls.class:'img'"></image>
                        </view>
                        <view v-else class="iconfont" style="display: flex;justify-content: center;align-items: center;"
                            v-html="cls.icon">

                        </view>
                        <view class="" style="font-size: 0.7em;">
                            {{cls.name}}
                        </view>
                    </view>
                </view>
            </view>
            <view class="" style="padding: 6px;box-sizing: border-box;background: #fff;">
                <view class="" style="border-radius: 5px;border: 1px solid #CCCCCC;">
                    <image src="../../static/ad/2.jpg" style="width: 100%;height: 45px;" mode=" aspectFill"></image>
                </view>
            </view>

            <view class="" style="padding: 6px;box-sizing: border-box;font-size: 0.9em;background: #fff;">
                <view class="" style="background: #EEE685;display: flex;flex-direction: row;padding: 5px;" @tap="goPage({name:'优惠卷'})">
                    <view class="" style="font-weight: 500;display: flex;flex-direction: row;align-items: center;">
                        <view class="iconfont" style="font-size: 1.5em;">
                            &#xe610;
                        </view>
                        <view class="" style="padding: 0 5px 0 10px;">
                            领取优惠卷
                        </view>
                    </view>
                    <view class="" style="flex:1;display: flex;flex-direction: row;justify-content: space-between;font-size: 0.7em;align-items: center;">
                        <view class="">
                            ●优惠不停
                        </view>
                        <view class="" style="color: #aaaaaa;">
                            立即进入
                        </view>
                    </view>
                </view>
            </view>
            <view class="yc-content-tuijian" style="display: flex;flex-direction: column;padding: 6px;box-sizing: border-box;background: #fff;">
                <view class="" style="font-size: 1.2em;font-weight: 600;padding:0 0  15px 0;">
                    优选推荐
                </view>
                <view class="" style="display: flex;flex-direction: row;">
                    <image @tap="goPage(item)" v-for="(item,idx) in recommend" :src="item.img" mode="" style="flex: 1;height: 140px;flex: 1; padding: 3px;box-sizing: border-box;"
                        :key="item.img"></image>

                </view>
            </view>
            <view class="yc-content-promotion" @tap="goPage({name:'首单立减'})">
                <image src="../../static/ad/1.jpg" mode="scaleToFill" style="height: 90px;width: 100%;"></image>
            </view>
            <view class="" style="display: flex;padding: 6px;justify-content: center;flex-direction: row; color: #BBBBBB;">
                —— 推荐商家 ——
            </view>
            <view class="" style="z-index: 6;top: 40px;width: 100%;background: #fdfdfd;" :style="contentTop?'position: fixed;':'position: ;'">
                <view class="" style="display: flex;padding: 6px;box-sizing: border-box;flex-direction: column;border-top:1px solid #ccc; border-bottom:1px solid #ccc;">
                    <view style="display:flex;flex-direction: row; font-size: 0.8em;width: 100%;justify-content: space-between;align-items: center;">
                        <view class="" style="display: flex;flex-direction: row;justify-content: flex-start;flex: 1;">
                            <view class="" style="padding:3px 5px;" @tap="tapSort({name:'综合排序'})">
                                {{paixu}} <text v-html="'&#xe606;'" class="iconfont" style="padding-left:3px;"> </text>
                            </view>
                            <view class="" @tap="tapSort({name:'销量'})" style="padding:3px 5px;">
                                销量
                            </view>
                            <view class="" @tap="tapSort({name:'距离'})" style="padding:3px 5px;">
                                距离
                            </view>
                        </view>

                        <view class="" @tap="tapSort({name:'筛选'})" style="padding:3px 5px;">
                            筛选
                            <text v-html="'&#xe606;'" class="iconfont" style="padding-left:3px;"> </text>
                        </view>
                    </view>
                    <view style="display:flex;flex-direction: row; font-size: 0.8em;width: 100%;justify-content: space-between;align-items: center;padding: 5px;box-sizing: border-box;">
                        <view @tap="tapSort({name:'满减优惠'})" class="tag">
                            满减优惠
                        </view>
                        <view @tap="tapSort({name:'进店领劵'})" class="tag">
                            进店领劵
                        </view>
                        <view @tap="tapSort({name:'折扣商家'})" class="tag">
                            折扣商家
                        </view>
                        <view @tap="tapSort({name:'减配送费'})" class="tag">
                            减配送费
                        </view>
                    </view>
                </view>
            </view>


            <view id="content" style="width: 100%;box-sizing: border-box;background: #f0f0f0;">

                <view v-for="(shop,idx) in shops" id="demo1" :key="idx" class="scroll-view-item" style="padding:2px  1px;box-sizing: border-box;">
                    <view class="" style="background: #fff;display: flex;flex-direction: row;align-items: center;" @tap="tapShop(shop)">
                        <view class="" style="padding: 6px;box-sizing: border-box;">
                            <image :src="shop.img" style="width: 70px;height: 70px;border-radius: 10px;" mode="aspectFill"></image>
                        </view>
                        <view class="" style="display: flex;flex-direction: column; font-size: 0.6em;flex: 1;padding-left: 10px;box-sizing: border-box;">
                            <view class="" style="font-size: 1.3em;padding: 3px;">
                                {{shop.name}}
                            </view>
                            <view class="" style="display: flex;flex-direction: row;align-items: center;justify-content: space-between;opacity: 0.8;padding: 3px;">
                                <view class="">
                                    <text>月售{{shop.sold}}</text>
                                    <text style="padding-left: 5px;box-sizing: border-box;">点击率 {{shop.visits}}</text>
                                </view>
                                <view class="">
                                    <text style="color: #fff;padding: 3px 6px;width: 100px;height: 30px;background: #1E90FF; background: linear-gradient(-45deg,transparent 5px,#1E90FF 0) right,linear-gradient(135deg,transparent 5px,#1E90FF 0) left;background-size: 60% 100%;background-repeat: no-repeat;">信浪配送</text>
                                    <text style="padding: 5px;"></text>
                                    <text style="padding: 3px;background: #DD524D;color: #fff;">外卖</text>
                                </view>
                            </view>
                            <view class="" style="display: flex;flex-direction: row;align-items: center;justify-content: space-between;opacity: 0.8;padding: 3px;">
                                <view class="">
                                    <text>起送￥{{shop.startSend}}</text>
                                    <text style="color: red;padding-left: 5px;box-sizing: border-box;">配送￥{{shop.postage}}</text>
                                </view>
                                <view class="">
                                    <text>{{shop.juli}}km</text>
                                    <text> | </text>
                                    <text>{{shop.time}}分钟</text>
                                </view>
                            </view>
                            <view class="" style="display: flex;flex-direction: row;align-items: center;flex-wrap: wrap;">
                                <view class="" :key="itm.text" v-for="(itm,idx) in shop.promotion" style="padding: 3px; box-sizing: border-box;">
                                    <view class="" style="border:1px solid red;color: red;padding:0 2px;box-sizing: border-box;">
                                        {{itm.text}}
                                    </view>
                                </view>

                            </view>
                        </view>

                    </view>
                </view>

            </view>
            <!--        <view @tap="goTop" class="uni-link uni-center uni-common-mt">
            点击这里返回顶部
        </view> -->
            <mpvue-city-picker :themeColor="themeColor" ref="mpvueCityPicker" :dataList="cityPickerData" :pickerValueDefault="cityPickerValueDefault"
                @onConfirm="cityConfirm"></mpvue-city-picker>
        </scroll-view>
    </view>
</template>

<script>
    import mpvueCityPicker from '@/components/picker/cityPicker.vue';
    import provinceData from '@/components/picker/city-data/province.js';
    import cityData from '@/components/picker/city-data/city.js';
    import areaData from '@/components/picker/city-data/area.js';
    import {
        Url
    } from '@/common/yc_js/';
    var shops = [{
        name: '绝味鸭脖',
        img: 'http://img0.imgtn.bdimg.com/it/u=3975998290,3510446418&fm=26&gp=0.jpg',
        sold: 1232,
        visits: 3223,
        startSend: 5,
        postage: 1,
        juli: '12',
        time: '120',
        promotion: [{
            type: 1,
            text: '满10减2'
        }, {
            type: 1,
            text: '满18减4'
        }, {
            type: 1,
            text: '满20减5'
        }, {
            type: 1,
            text: '满100减30'
        }]
    }, {
        name: '肯德基',
        img: 'http://img4.imgtn.bdimg.com/it/u=815585866,2727252036&fm=26&gp=0.jpg',
        sold: 1232,
        visits: 3223,
        startSend: 5,
        postage: 1,
        juli: '113',
        time: '110',
        promotion: [{
            type: 1,
            text: '满10减1'
        }, {
            type: 1,
            text: '满100减30'
        }]
    }, {
        name: '必胜客',
        img: 'http://www.cardbaobao.com/eWebEditor_v280/UploadFile/2014520111441668.jpg',
        sold: 1232,
        visits: 3223,
        startSend: 5,
        postage: 1,
        juli: '123',
        time: '140',
        promotion: [{
            type: 1,
            text: '满10减2'
        }, {
            type: 1,
            text: '满18减4'
        }, {
            type: 1,
            text: '满100减30'
        }]
    }, {
        name: '李先生',
        img: 'http://oss.huangye88.net/live/user/2454692/1519981596048304400-0.jpg@1e_1c_170w_150h_90Q.jpg',
        sold: 232,
        visits: 1223,
        startSend: 5,
        postage: 1,
        juli: '126',
        time: '122',
        promotion: [{
            type: 1,
            text: '满10减2'
        }, {
            type: 1,
            text: '满20减5'
        }, {
            type: 1,
            text: '满100减30'
        }]
    }, {
        name: '麦当劳',
        img: 'http://awb.img.xmtbang.com/img/uploadnew/201603/01/5cd721c3baac4abcab968ee087059f75.jpg',
        sold: 132,
        visits: 323,
        startSend: 5,
        postage: 1,
        juli: '93',
        time: '121',
        promotion: [{
            type: 1,
            text: '满10减2'
        }, {
            type: 1,
            text: '满18减4'
        }, {
            type: 1,
            text: '满20减5'
        }, {
            type: 1,
            text: '满100减30'
        }]
    }, {
        name: '真功夫',
        img: 'http://p5.qhimg.com/dr/200_200_/t0102efa29756ff2544.jpg',
        sold: 982,
        visits: 8223,
        startSend: 5,
        postage: 1,
        juli: '121',
        time: '111',
        promotion: [{
            type: 1,
            text: '满20减5'
        }, {
            type: 1,
            text: '满100减30'
        }]
    }, {
        name: '巫山烤鱼',
        img: 'http://www.zuizhifu.com/uploads/bimg/2017/08/30/d15040581748765.jpg',
        sold: 1232,
        visits: 3223,
        startSend: 5,
        postage: 1,
        juli: '121',
        time: '114',
        promotion: [{
            type: 1,
            text: '满10减2'
        }, {
            type: 1,
            text: '满20减5'
        }, {
            type: 1,
            text: '满100减30'
        }]
    }];
    var recommend = [{
        name: '元气早餐',
        img: '../../static/ad/a1.jpg'
    }, {
        name: '满减优惠',
        img: '../../static/ad/a2.jpg'
    }, {
        name: '新店来袭',
        img: '../../static/ad/a3.jpg'
    }, {
        name: '超市水果',
        img: '../../static/ad/a4.jpg'
    }]
    var classAll = [{
            name: '外卖',
            icon: '&#xe63d;',
            class: 'img-max',
            img: '/static/icon/waimaiyuding.png'
        }, {
            name: '本地商超',
            icon: '',
            class: 'img-max',
            img: '/static/icon/bendishangchao.png'
        }, {
            name: '同城服务',
            icon: '',
            class: 'img-max',
            img: '/static/icon/tongcheng.png'
        }, {
            name: '拼团',
            icon: '',
            class: 'img-max',
            img: '/static/icon/pintuan.png'
        }, {
            name: '周边商超',
            icon: '',
            class: 'img-max',
            img: '/static/icon/zhoubian.png'
        }, {
            name: '包子粥铺',
            icon: '',
            img: '/static/icon/baozizhoupu.png'
        }, {
            name: '简餐便当',
            icon: '',
            img: '/static/icon/jiancanbiandang.png'
        },
        {
            name: '日韩料理',
            icon: '',
            img: '/static/icon/rihanliaoli.png'
        }, {
            name: '汉堡披萨',
            icon: '',
            img: '/static/icon/hanbaopisa.png'
        }, {
            name: '面包蛋糕',
            icon: '',
            img: '/static/icon/mianbaodangao.png'
        }, {
            name: '饺子混沌',
            icon: '',
            img: '/static/icon/jiaozihundun.png'
        },
        {
            name: '商场便利',
            icon: '',
            img: '/static/icon/shangchangbianli.png'
        },
        {
            name: '水果',
            icon: '',
            img: '/static/icon/shuiguo.png'
        },
        {
            name: '甜点饮品',
            icon: '',
            img: '/static/icon/tiandianyinpin.png'
        },
        {
            name: '美食大餐',
            icon: '',
            img: '/static/icon/meishidacan.png'
        },
    ];
    var aD = [{
        img: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575703306859&di=3bc151db690a69b96e6b1eb28e504303&imgtype=jpg&src=http%3A%2F%2Fimg3.imgtn.bdimg.com%2Fit%2Fu%3D3731427063%2C2084348399%26fm%3D214%26gp%3D0.jpg',
        shop_id: 1,
    }, {
        img: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575703304409&di=853abfae05794bf228409d54c3ed5b36&imgtype=0&src=http%3A%2F%2Fpic139.nipic.com%2Ffile%2F20170823%2F21485791_223335198000_2.jpg',
        shop_id: 2,
    }, {
        img: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575703304409&di=b4a20c58e60302f32307385ac45fa35c&imgtype=0&src=http%3A%2F%2Fimage.tupian114.com%2F20141107%2F16510237.jpg',
        shop_id: 3,
    }]
    export default {
        props: {
            windowHeight: {
                type: Number,
                default: function() {
                    return 0;
                }
            }
        },
        components: {
            mpvueCityPicker
        },
        data() {
            return {
                recommend,

                opacity: 0.2,
                contentTop: false,
                scrollTop: 0,
                old: {
                    scrollTop: 0
                },

                aD,
                indicatorDots: true,
                autoplay: true,
                interval: 2000,
                duration: 500,
                shops:shops,
                classAll,
                cityPickerData: [provinceData,cityData,areaData],
                provinceData: provinceData,
                cityData: cityData,

                themeColor: '#007AFF',
                value: '请选择',
                
                cityPickerValueDefault: [5, 7, 4],
                areaObj: {}, //三联地址内容
                form: {
                    address: '',
                    area_id: ""
                },

                city: '盖州',
                paixu: '综合排序',

                paixuList: ['综合排序', '速度最快', '评分最高', '起送价最低', '配送费最低', '人均高到低', '人均低到高']
            }
        },
        computed: {
            contentHeight() {
                return this.windowHeight
            }
        },
        mounted() {
            // this.contentHeight=this.windowHeight
            console.log(this.windowHeight)
        },
        methods: {
            upper: function(e) {
                console.log(e)
            },
            
            scrolltolower: function(e) {
                this.shops= this.shops.concat(shops)
                // 滚动到底部触发
                console.log(e)
            },
            scroll: function(e) {
                // console.log(e)
                const query = uni.createSelectorQuery().in(this);
                query.select('#content').boundingClientRect(data => {
                    // 商家导航漂浮设定
                    if (data.top < 125) {
                        this.contentTop = true;
                    } else {
                        this.contentTop = false;
                    }
                })
                query.select('#ad').boundingClientRect(data => {
                    // 导航条背景透明度设定
                    var hei = Math.abs(data.top);

                    var opacity = 1;
                    if (hei < 160) {
                        opacity = hei / 160;
                    }
                    if (opacity < 0.9) {

                        this.opacity = opacity + 0.2;
                    } else {
                        this.opacity = 1;
                    }

                }).exec();

            },
            goTop: function(e) {
                this.scrollTop = this.old.scrollTop
                this.$nextTick(function() {
                    this.scrollTop = 0
                });
                uni.showToast({
                    icon: "none",
                    title: "纵向滚动 scrollTop 值已被修改为 0"
                })
            },
            tapSort(e) {
                console.log(e)
                if (e.name == '综合排序') {
                    var paixuList = this.paixuList;
                    uni.showActionSheet({
                        itemList: paixuList,
                        success: (res) => {
                            this.paixu = paixuList[res.tapIndex];
                            console.log(res)
                            console.log('选中了第' + (res.tapIndex + 1) + '个按钮');
                        },
                        fail: function(res) {
                            console.log(res.errMsg);
                        }
                    });
                } else if (e.name == '距离') {
                    uni.getLocation({
                        type: 'wgs84',
                        success: (res) => {
                            console.log('当前位置的纬度：' + res);
                            // console.log('当前位置的经度：' + res.longitude);
                            // console.log('当前位置的纬度：' + res.latitude);
                            uni.showModal({
                                title: '位置',
                                content: JSON.stringify(res),
                                success: rs => {},
                                fail: () => {},
                                complete: () => {}
                            });
                        }
                    });
                } else {
                    uni.showModal({
                        title: '提示【' + e.name + '】',
                        content: '功能暂未开放'
                    })
                }

            },
            tapShop(e) {
                uni.navigateTo({
                    url: '/pages/goods/shop?' + Url.urlEncode(e)
                });
                // 
                uni.showModal({
                    title: '提示【' + e.name + '】',
                    content: '暂未开通' + JSON.stringify(e),

                    success: res => {

                    },
                    fail: () => {},
                    complete: () => {}
                });
            },
            goPage(e) {
                uni.showModal({
                    title: '提示【' + e.name + '】',
                    content: '页面暂未开放'
                })
                console.log(e)
            },
            tapCity(e) {
                console.log(e)
                // 三级联动选择
                this.$refs.mpvueCityPicker.show(true)
            },
            cityConfirm(e) {
                console.log(e)
                this.value = e.label;
                this.areaObj = e;
                this.city = this.value.split("-")[2];
                this.form.area_id = e.cityCode;

            },
            tapClass(item) {
                console.log(item)
                var param = encodeURIComponent(JSON.stringify(item));
                // console.log(param);
                // console.log(JSON.parse(decodeURIComponent(param)));
                uni.navigateTo({
                    url: '/pages/home/list?param=' + param,
                    success: res => {},
                    fail: () => {},
                    complete: () => {}
                });
            }
        }
    }
</script>

<style>
    .yc-nav {
        height: 40px;
        font-size: 0.8em;
        padding: 0 10px;
        box-sizing: border-box;
        /* background: #fdfdfd; */
        display: flex;
        justify-content: space-between;
        width: 100%;

    }

    .yc-nav-noTop {
        position: fixed;
    }

    .yc-nav-domain {
        padding: 5px;
        flex: 1;
        display: flex;
        /* background: #007AFF; */
    }

    .yc-nav-domain-left {
        display: flex;
        width: 100px;
        /* background: #007Acc; */
        flex-direction: row;
        align-items: center;
        justify-content: flex-start;
    }

    .yc-nav-domain-right {
        display: flex;
        flex: 1;
        flex-direction: row;
        align-items: center;
        justify-content: flex-start;
    }

    .yc-content-top {
        font-size: 0.7em;
        padding: 3px;
        box-sizing: border-box;
    }

    .yc-content-top .img-max {
        width: 3em;
        height: 3em;
    }

    .tag {
        box-sizing: border-box;
        padding: 5px;
        background: #EEEEEE;
    }

    .img {
        width: 2em;
        height: 2em;
    }
</style>
