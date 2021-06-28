<template>
    <view class="uni-page-body" style="background: #2C405A;position: relative;width: 100%;box-sizing: border-box;">

        <scroll-view id="scrollview" scroll-y @scrolltolower="scrolltolower" :scroll-into-view="scrollIntoId" :style="'height:'+contentHeight+'px;'">
            <view class="" style="padding: 5px;box-sizing: border-box;color: #FFFFFF;">
                <view class="sp-list uni-flex uni-row" style="box-sizing: border-box;height: 140upx;">

                    <view class="sp-list uni-flex uni-column uni-flex-item" style="padding:0 10px;">
                        <view class="sp-list uni-flex uni-row">

                            <view class="sp-list uni-flex uni-row">
                                <view class="" style="box-sizing: border-box;padding-right:10px ;">
                                    {{resume.name}}
                                </view>
                                <view style="opacity: 0.7;">
                                    <text style="color: #999;background: #EEEEEE;" class="uni-badge">{{resume.auth_info.name}}</text>
                                </view>
                            </view>
                        </view>
                        <view class="sp-list uni-flex uni-column" style="justify-content: space-between;font-size: 0.8em;opacity: 0.7;">
                            <view class="" style="font-size: 0.8em;">
                                <text>主营:{{resume.speciality}}</text>
                            </view>
                            <view class="sp-list uni-flex uni-row" style="font-size: 0.8em;">
                                <text class="iconfont">公司规模:</text> <text style="">{{resume.scale_hiring}}/人</text>
                            </view>
                        </view>
                    </view>
                    <view class="sp-list uni-flex uni-column" style="justify-content: center;align-items: center;width: 130upx;padding:10upx;">
                        <image class="logo" style="width: 100%;height: 100%;" mode="aspectFill" src="https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D500/sign=e02206834c36acaf5de096fc4cd88d03/ca1349540923dd547d83e2d2d609b3de9d824893.jpg"></image>
                    </view>
                </view>
                <view class="sp-list uni-flex uni-column" style="" @tap="openLocation">
                    <view class="uni-flex uni-column" style="padding:10px;" @tap="openLocation">
                        公司地址【打开地图】
                    </view>
                    <view class="uni-flex uni-row uni-flex-item" style="padding:0 10px;font-size: 0.7em;opacity: 0.7;">
                        <text class="iconfont">&#xe8dd;</text> <text style="">{{resume.auth_info.regLocation}}</text>
                    </view>
                    <!--                    <view class="uni-flex" style="width: 100%; height: 300px;position: relative;">
                        <map style="width: 100%; height: 300px;position: relative;" :markers="markers" :latitude="latitude" :longitude="longitude">
                        </map>
                    </view> -->
                </view>
                <view class="sp-list uni-flex uni-column" style="">
                    <view class="uni-flex uni-column" style="padding:10px;">
                        公司官网
                    </view>
                    <view class="uni-flex uni-column uni-flex-item" style="padding:0 10px;font-size: 0.7em;opacity: 0.7;">
                        {{resume.website}}
                    </view>
                </view>
                <view class="sp-list uni-flex uni-column" style="">
                    <view class="uni-flex uni-column" style="padding:10px;">
                        公司介绍
                    </view>
                    <view class="uni-flex uni-column uni-flex-item" style="padding:0 10px;font-size: 0.7em;opacity: 0.7;">
                        {{resume.info}}
                    </view>
                </view>
                <view class="sp-list uni-flex uni-column" style="padding: 10px 0;">
                    <view class="uni-flex uni-column" style="padding:10px;">
                        公司图片
                    </view>
                    <view class="uni-padding-wrap detail-list" style="width: 100%;box-sizing: border-box;">
                        <view class="page-section swiper  " style=" ">

                            <swiper class="swiper " indicator-dots='true' autoplay interval='2000' style="height: 280px;">
                                <block class="" v-for="(img,idx) in resume.image" :key="idx">
                                    <swiper-item style="width: 100%;">
                                        <!-- <view class="swiper-item uni-bg-red"> -->
                                        <image :src="img.url" mode="aspectFit" style="width: 100%;"></image>
                                        <!-- </view> -->
                                    </swiper-item>
                                </block>

                            </swiper>

                        </view>
                    </view>
                </view>


            </view>
            <view v-if="list" class="uni-flex-item" style="padding: 5px;background: #F8F8F8;width: 100%;box-sizing: border-box;">

                <view class="sp-list uni-flex uni-column" style="">
                    <view class="uni-flex uni-column" style="padding:10px;">
                        相关信息
                    </view>
                    <view class="uni-flex uni-column uni-flex-item" style="padding:0 5px;">
                        <view v-for="(item,idx) in list" :key='idx'>
                            <view class="" style="padding: 2px 0;box-sizing: border-box;">
                                <view class="sp-list uni-flex uni-row" style="box-sizing: border-box;height: 140upx;background: #fff;">
                                    <view @tap="bt('detail',item)" class="sp-list uni-flex uni-column" style="justify-content: center;align-items: center;width: 130upx;padding:10upx;">
                                        <image class="logo" style="width: 100%;height: 100%;" mode="aspectFill" :src="item.image"></image>
                                    </view>
                                    <view class="sp-list uni-flex uni-column uni-flex-item" style="padding:0 10upx;">
                                        <view @tap="bt('detail',item)" class="sp-list uni-flex uni-row">
                                            <view>
                                                <text style="background: #FFB400;color: #FFFFFF;" class="uni-badge">{{item.category}}</text>
                                            </view>
                                            <view class="" style="box-sizing: border-box;padding-left:10px ;">
                                                {{item.title}}
                                            </view>
                                        </view>
                                        <view class="sp-list uni-flex uni-row" style="font-size: 0.8em;justify-content: space-between;">
                                            <view @tap="bt('detail',item)" class="uni-flex uni-row" style="justify-content: space-between;">
                                                <text style="color: #FF3333;">￥{{item.price}}/{{item.unit}}</text>
                                                <text style="padding-left:10upx;box-sizing: border-box;opacity: 0.7;">已预约{{item.sales}}次</text>
                                            </view>
                                            <view class="uni-flex uni-row">
                                                <view @tap="bt('shouchang',item)" class="" style="padding: 0 5px;box-sizing: border-box;">
                                                    <text style="border:1px solid #F1F1F1;box-sizing: border-box;padding: 0 5px;border-radius: 3px;color: #666666;">收藏</text>
                                                </view>
                                                <view @tap="bt('chat',item)" class="">
                                                    <text style="border:1px solid #F1F1F1;box-sizing: border-box;padding: 0 5px;border-radius: 3px;color: #666666;">聊聊</text>
                                                </view>
                                            </view>
                                        </view>
                                        <view class="sp-list uni-flex uni-row" style="justify-content: space-between;">
                                            <view class="">
                                                <text class="iconfont">&#xe8dd;</text><text>{{item.city}}</text>
                                            </view>
                                            <view class="uni-flex uni-row" style="opacity: 0.7;font-size:0.7em;padding: 0 5px;">
                                                {{item.datetime}}
                                            </view>

                                        </view>
                                    </view>

                                </view>
                            </view>
                        </view>
                    </view>
                </view>


            </view>
            <view class="" id="bottom">

            </view>
        </scroll-view>
        <view class="" :style="'height: '+TBarHeight+'px;'">
            <tab-nav ref="navTabBar" :tabBar="navData" :style="theStyle" @change="tabBarTap" @changeInput="changeInput"
                :Height="TBarHeight-1">
            </tab-nav>
        </view>
    </view>
</template>

<script>
    export default {
        data() {
            return {
                latitude: 39.909,
                longitude: 116.39742,
                markers: [{
                    latitude: 39.907,
                    longitude: 116.395,
                    iconPath: '/static/yuandian.png'
                }, {
                    latitude: 39.9079,
                    longitude: 116.39742,
                    iconPath: '/static/location.png'
                }],
                scrollIntoId: '',
                contentHeight: 0,
                theStyle: 'background:#fafafa;font-size:16px;color:#555;border-top:1px solid #EBEBEB',
                TBarHeight: 44,
                windowHeight: 0,
                navData: [
                    //     {
                    //     style: 'width:auto',
                    //     icon: '&#xe8b4;',
                    //     text: '聊聊',
                    //     url: '/pages/order/list'
                    // },
                    {
                        style: 'border-left:1px solid #EBEBEB',
                        // icon: '&#xe8b4;',
                        text: '资源/服务',
                        url: '/pages/order/list'
                    }, {
                        style: 'flex:1;background:#EE6363;color:#fff;justify-content:center',
                        type: 'button',
                        // msg: 0,
                        text: '电话预约',
                        tag: '12345',
                        leftIcon: '&#xe8bb;',
                        rightIcon: '&#xe8bb;',
                        icon: '&#xe8bb;',
                        url: '/pages/order/list'
                    }
                ],

                resume: {

                    name: '大唐圣殿',
                    scale_hiring: '100-500', //雇佣人数
                    speciality: '后期制作，影棚，电影发行',
                    scale: 2, //企业规模
                    logo: '',
                    info: "大唐圣殿影视文化有限公司（Datang Temple Film Culture CO.LTD ）是中国21世纪新兴成长的文化媒体传播和制作公司。公司本着在影视文化制作过程中不断开拓创新的精神，力求突破传统的制作模式，引领业界新一轮的影视革命。 前期的策划、剧本创作，是大唐圣殿影视创作的灵魂。为此，成立公司下设影视策划部、剧本创作部、制作部、市场推广部及演艺经纪部等多个部门。公司将在影视文化制作的同时，规模培养出一批制作、导演和演员人才，做好剧本，拍好影视，实现人才和影视文化制作并进的发展模式；与此同时，公司在国内运作和资金整合的基础上，力主寻求国外影视投资、制作、发行机构，实行双方资源互助，走国际合作化路线，真正使大唐圣殿成为具有标志性的国际知名品牌公司。",
                    image: [{
                            url: 'https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=70e8f0092f2eb938f86072a0b40bee50/0e2442a7d933c895f54c6b93d11373f082020023.jpg'
                        },
                        {
                            url: 'https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=70e8f0092f2eb938f86072a0b40bee50/0e2442a7d933c895f54c6b93d11373f082020023.jpg'
                        },
                        {
                            url: 'https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=70e8f0092f2eb938f86072a0b40bee50/0e2442a7d933c895f54c6b93d11373f082020023.jpg'

                        }
                    ],
                    address: '',
                    website: 'http://www.01film.cn',
                    coordinate: {
                        city: '北京',
                        city_code: 111000, //城市代码
                        address: '', //地址
                        longitude: 12.3234, //经度
                        latitude: 99.3234, //纬度
                    },
                    auth_info: {
                        "base": "bj",
                        "name": "北京百度网讯科技有限公司",
                        "legalPersonName": "梁志祥",
                        "legalPersonType": 1,
                        "regNumber": "110108002734659",
                        "industry": null,
                        "companyOrgType": "有限责任公司(自然人投资或控股)",
                        "regLocation": "北京市海淀区上地十街10号百度大厦2层",
                        "estiblishTime": "2001-06-05",
                        "fromTime": "2001-06-05",
                        "toTime": "2021-06-04",
                        "businessScope": "技术服务、技术培训、技术推广；设计、开发、销售计算机软件；经济信息咨询；利用www；baidu；com、www；hao123；com(www；hao222；net、www；hao222；com)网站发布广告；设计、制作、代理、发布广告；货物进出口、技术进出口、代理进出口；医疗软件技术开发；委托生产电子产品、玩具、照相器材；销售家用电器、机械设备、五金交电、电子产品、文化用品、照相器材、计算机、软件及辅助设备、化妆品、卫生用品、体育用品、纺织品、服装、鞋帽、日用品、家具、首饰、避孕器具、工艺品、钟表、眼镜、玩具、汽车及摩托车配件、仪器仪表、塑料制品、花、草及观赏植物、建筑材料、通讯设备；预防保健咨询；公园门票、文艺演出、体育赛事、展览会票务代理；翻译服务；通讯设备和电子产品的技术开发；计算机系统服务；因特网信息服务业务（除出版、教育、医疗保健以外的内容）；图书、电子出版物、音像制品批发、零售、网上销售；利用信息网络经营音乐娱乐产品、演出剧（节）目、动漫产品、游戏产品（含网络游戏虚拟货币发行）、表演、网络游戏技法展示或解说（网络文化经营许可证有效期至2020年04月17日）；演出经纪；人才中介服；经营电信业务。（企业依法自主选择经营项目，开展经营活动；演出经纪、人才中介服务、利用信息网络经营音乐娱乐产品、演出剧（节）目、动漫产品、游戏产品（含网络游戏虚拟货币发行）、表演、网络游戏技法展示或解说、经营电信业务以及依法须经批准的项目，经相关部门批准后依批准的内容开展经营活动；不得从事本市产业政策禁止和限制类项目的经营活动。）",
                        "approvedTime": "2019-07-30",
                        "regStatus": "开业",
                        "regCapital": "1342128.000000万人民币",
                        "regInstitute": "北京市工商行政管理局海淀分局",
                        "orgNumber": "802100433",
                        "creditCode": "91110000802100433B",
                        "property3": "Beijing Baidu Netcom Science and Technology Co.,Ltd.",
                        "updatetime": "2019-08-12 12:05:21.347028",
                        "companyId": null,
                        "taxNumber": null,
                        "email": "www.baidu.com",
                        "website": 'www.baidu.com',
                        "phoneNumber": "010-59928888",
                        "revokeDate": null,
                        "revokeReason": null,
                        "cancelDate": null,
                        "cancelReason": null
                    }


                },
                list: null
            }
        },
        onLoad() {

        },
        onReady() {

            var system = uni.getSystemInfoSync();
            this.windowHeight = system.windowHeight;
            var contentHeight = this.windowHeight - this.TBarHeight;
            this.contentHeight = contentHeight;
            // console.log(system)
        },
        methods: {
            tabBarTap(e) {
                console.log(e)
                var item = [{
                    type: 'weixin',
                    value: 'tanjiyuan-'
                }, {
                    type: 'phone',
                    value: 18101325006
                }];
                switch (e.idx) {
                    case 0:
                        this.getData();
                        break;
                    case 1:
                        uni.showActionSheet({
                            itemList: [
                                "微信:tanjiyuan-", "电话:18101325006",
                            ],
                            success: (e) => {
                                var tapIndex = e.tapIndex
                                switch (tapIndex) {
                                    case 0:
                                        break;
                                    case 1:
                                        uni.makePhoneCall({
                                            phoneNumber: "" + item[tapIndex].value //仅为示例
                                        });
                                        break;
                                    default:
                                        break;
                                }
                                console.log(e)
                            }
                        })
                        // uni.showModal({
                        //     title: '联系方式',
                        //     content: "微信:tanjiyuan-\n电话:18101325006",

                        // })
                        break;
                    case 2:

                        break;
                    default:
                        break;
                }
            },
            scrolltolower() {
                // 滚动条到底部触发
                this.getData()
            },
            openLocation(e) {
                // 打开地图
                var value = {
                    longitude: 116.39747,
                    latitude: 39.9085,
                    name: "天安门",
                    address: "北京市东城区东长安街"
                }
                // console.log(e)
                // var value = e.target.value
                uni.openLocation({
                    longitude: Number(value.longitude),
                    latitude: Number(value.latitude),
                    name: value.name,
                    address: value.address
                })
            },
            bt(e, item) {
                // console.log(e)
                var url = 'detail';
                switch (e) {
                    case 'detail':
                        url = item.url;
                        break;
                    default:
                        break;
                }
                var query = '?query=' + encodeURIComponent(JSON.stringify(item))
                uni.navigateTo({
                    url: url + query
                })

            },
            getData() {
                this.list = [{
                    id: 229,
                    url: '/pages/pagesA/news/public/detail',
                    title: '浏德华',
                    image: "https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D500/sign=e02206834c36acaf5de096fc4cd88d03/ca1349540923dd547d83e2d2d609b3de9d824893.jpg",
                    category: '演员',
                    city: '北京市',
                    price: 500,
                    unit: '天',
                    sales: 500,
                    datetime: '2019-10-28'
                }, {
                    url: '/pages/pagesA/news/public/detail',
                    title: '猪扒皮',
                    image: "https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D500/sign=e02206834c36acaf5de096fc4cd88d03/ca1349540923dd547d83e2d2d609b3de9d824893.jpg",
                    category: '编剧',
                    city: '北京市',
                    price: 10000,
                    unit: '天',
                    sales: 50,
                    datetime: '2019-10-28'
                }, {
                    id: 230,
                    url: '/pages/pagesA/news/public/detail',
                    title: '李德辉',
                    image: "https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D500/sign=e02206834c36acaf5de096fc4cd88d03/ca1349540923dd547d83e2d2d609b3de9d824893.jpg",
                    category: '制片',
                    city: '北京市',
                    price: 500,
                    unit: '天',
                    sales: 500,
                    datetime: '2019-10-28'
                }, {
                    id: 231,
                    url: '/pages/pagesA/news/public/detail',
                    title: '张晓敏',
                    image: "https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D500/sign=e02206834c36acaf5de096fc4cd88d03/ca1349540923dd547d83e2d2d609b3de9d824893.jpg",
                    category: '作曲',
                    city: '北京市',
                    price: 5000,
                    unit: '首',
                    sales: 500,
                    datetime: '2019-10-28'
                }, {
                    id: 228,
                    url: '/pages/pagesA/news/public/detail',
                    title: '溜溜',
                    image: "https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D500/sign=e02206834c36acaf5de096fc4cd88d03/ca1349540923dd547d83e2d2d609b3de9d824893.jpg",
                    category: '策划',
                    city: '北京市',
                    price: 10000,
                    unit: '次',
                    sales: 50,
                    datetime: '2019-10-28'
                }, {
                    id: 227,
                    url: '/pages/pagesA/news/public/detail',
                    title: '阿猫',
                    image: "https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D500/sign=e02206834c36acaf5de096fc4cd88d03/ca1349540923dd547d83e2d2d609b3de9d824893.jpg",
                    category: '场景',
                    city: '北京市',
                    price: 500,
                    unit: '天',
                    sales: 500,
                    datetime: '2019-10-28'
                }]
                setTimeout(e => {
                    this.scrollIntoId = 'bottom'
                }, 100)

            }
        }
    }
</script>

<style>


</style>
