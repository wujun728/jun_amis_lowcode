<template>
    <view class="uni-page-body" style=" overflow-y: auto;">
        <view class="uni-common-mt">

            <view class="uni-form-item uni-column" style="background: #FFA54F; align-items: center;" v-if="detail && detail.point">
                <view class="title">提示</view>
                <view class="" style="color: red;">
                    {{detail.point}}
                </view>
            </view>
            <view v-for="(img,i) in formImage" :key="img.name" @tap="openImage(img.name)" style="justify-content: center; align-items: center;"
                class="uni-flex uni-column">
                <view class="title">{{img.label}}</view>
                <view class="uni-flex-item" style="position:relative;">
                    <view class="uni-flex uni-row" style="z-index: 2;width: 100%; font-size: 200upx;position: absolute;color: #09BB07;justify-content: center;align-items: center;">+</view>
                    <image :src="formImg[img.name]?formImg[img.name]:img.err">
                    </image>
                </view>

            </view>
            <view v-for="(item,idx) in  formInput" :key="item.name">
                <block v-if="item.name=='effective_time'">
                    <view class="uni-form-item uni-column">
                        <view class="uni-form-item uni-row">
                            <view class="uni-label uni-flex uni-row" style="align-items: center;justify-content: center;">{{item.label}}</view>
                            <picker class="uni-input uni-flex-item" mode="date" :value="date" :start="startDate" :end="endDate"
                                @change="bindDateChange">
                                <view class="uni-flex-item">{{date}}</view>
                            </picker>
                        </view>
                    </view>
                </block>
                <view v-else-if="item.name=='phone'">
                    <view class="uni-form-item uni-column">

                        <view class="uni-form-item uni-row">
                            <view class="uni-label uni-flex uni-row" style="align-items: center;justify-content: center;">{{item.label}}</view>
                            <input class="uni-input" v-model="form[item.name]" focus :placeholder="item.placeholder" />
                        </view>
                        <view v-if="isShowCaptcha" class="uni-form-item uni-row" style="align-items: center;justify-content: center;">
                            <image class="uni-label" style="height: 80upx;" :src="captcha" mode="aspectFit" @tap="resetCaptcha"></image>
                            <input class="uni-input" v-model="inputCaptcha" type="text" :placeholder="'请输入图片验证码'">
                        </view>
                        <view class="uni-form-item uni-row">
                            <view class="uni-label uni-flex uni-row" style="align-items: center;justify-content: center;">手机验证码</view>
                            <input class="uni-input" v-model="form.code" focus placeholder="手机验证码" />
                            <view v-if="CountdownBt<1" class="uni-label uni-flex uni-row" style="align-items: center;justify-content: center;"
                                @tap="getPhoneCode">获取验证码</view>
                            <view v-else class="uni-label uni-flex uni-row" style="align-items: center;justify-content: center;">{{CountdownBt}}秒</view>
                        </view>
                    </view>
                </view>
                <block v-else>
                    <view class="uni-form-item uni-row">
                        <view class="uni-label uni-flex uni-row" style="align-items: center;justify-content: center;">{{item.label}}</view>
                        <input class="uni-input" v-model="form[item.name]" focus :placeholder="item.placeholder" />
                    </view>
                </block>
            </view>

            <button type="primary" style="width: 100%;" @click="submit">确认</button>
            <view v-if="images">
                <view class="uni-column" style="padding: 20px;box-sizing: border-box;">
                    <view class="uni-flex-item">
                        历史上传
                    </view>
                    <scroll-view scroll-x style=" height: auto;background: #ADADAD;">
                        <view style="white-space: nowrap;" class="uni-flex uni-row">
                            <block v-for="(img,idx) in images" :key="img.url">
                                <view class="uni-flex uni-row" style="justify-content: center; align-items: center;box-sizing:border-box;position:relative">
                                    <image style="box-sizing: border-box;padding: 10px;" :src="img.url" mode="aspectFill"></image>
                                    <view class="" style="position: absolute;buttom:20px;right: 20px;align-self: flex-end;background: #fff;padding: 2px;">
                                        {{img.datetime}}
                                    </view>
                                </view>
                            </block>
                        </view>
                    </scroll-view>
                </view>
            </view>
        </view>

        <compress ref="compress" :maxwh="2000" :quality="1"> </compress>
    </view>
</template>

<script>
    import compress from "@/components/template/image/compress.vue"

    import Request from '@/request/index.js'
    import {
        Validate,
        Md5,
        Time,
        Img
    } from "@/common/yc_js/index.js";
    var YCImg = Img
    export default {

        data() {
            const currentDate = this.getDate({
                format: true
            })
            return {
                inputCaptcha: '',
                captcha: '',
                isShowCaptcha: false,

                date: currentDate,

                CountdownBt: 0,
                param: {},
                formInput: [{
                    label: '姓名',
                    name: 'name',
                    placeholder: '请输入姓名',
                    value: ''
                }, {
                    label: '身份证号',
                    name: 'number',
                    placeholder: '请输入身份证号码',
                    value: ''
                }, {
                    label: '户籍地址',
                    name: 'address',
                    placeholder: '请输入证件户籍地址',
                    value: ''
                }, {
                    label: '证件有效期',
                    name: 'effective_time',
                    placeholder: '证件截止日期',
                    value: ''
                }, {
                    label: '实名手机号',
                    name: 'phone',
                    placeholder: '请输入实名手机号码',
                    value: ''
                }],
                formImage: [{
                        label: '身份证人像面',
                        name: 'zhengjian',
                        err: '/static/img/shenfenzheng.jpg',
                        value: ''
                    },
                    {
                        label: '身份证国徽面',
                        name: 'zhengjian1',
                        err: '/static/img/shenfenzheng2.jpg',
                        value: ''
                    }, {
                        label: '手持身份证',
                        name: 'zhengjian2',
                        err: '/static/img/shenfenzheng3.jpg',
                        value: ''
                    }
                ],

                form: {
                    name: '刘德华',
                    number: '21222202222',
                    phone: '18888888889',
                    address: '北京市东城区中南海1号院2号楼1022',
                    effective_time: '',
                    code: '',
                    // bank: '',
                    // bankName: '',
                    // bankNumber: ''
                },
                formImg: {
                    // yinhangka: '',
                    zhengjian2: '',
                    zhengjian1: '',
                    zhengjian: '',
                },
                imageList: [],
                detail: null,
                title: '实名认证'
            }
        },
        watch: {
            detail(val) {
                var form = this.form;
                this.formInput.filter(e => {
                    for (let k in val) {
                        if (e.name == k) {
                            form[k] = val[k];
                            e.value = val[k];
                            break;
                        }
                    }
                    return e
                })
                this.form = form

            },
        },
        computed: {
            startDate() {
                return this.getDate('start');
            },
            endDate() {
                return this.getDate('end');
            },
            images() {
                if (this.detail && this.detail.prove_image && !isNaN(this.detail.prove_image.length)) {

                    return this.detail.prove_image.filter(e => {
                        e.url = this.$config.getFileUrl(e.src, 'image');
                        e.datetime = Time.formatDate(e.create_time);
                        return e
                    })
                }
                return null
            }
        },
        components: {
            compress
        },
        methods: {
            getPhoneCode() {

                var inputCaptcha = this.inputCaptcha;
                var phone = this.form.phone || 0;
                let rule = (tp) => {
                    return [{
                        checkType: tp,
                        name: 'phone',
                        errorMsg: '这个错拉'
                    }];
                };

                let data = {
                    phone
                };


                if (!Validate.check(data, rule('phoneno'))) {
                    uni.showToast({
                        icon: 'none',
                        title: '手机号不正确',
                    });
                    return;
                }
                if (!this.isShowCaptcha) {
                    this.isShowCaptcha = true;
                    // console.log(this.isShowCaptcha)
                    uni.showToast({
                        icon: 'none',
                        title: '请输入图片验证码',
                    });
                    this.resetCaptcha();
                    return;
                }
                if (!inputCaptcha || inputCaptcha.length < 4) {
                    uni.showModal({
                        showCancel: false,
                        title: '失败提示',
                        content: '图片验证码至少4位'
                    });
                    return;
                }
                Request('UserVerify_SendSms', {
                    data: {
                        code: inputCaptcha,
                        phone: phone
                    }
                }).then(res => {
                    // console.log(res);
                    if (res.data.code === 200) {
                        uni.showToast({
                            icon: 'none',
                            title: res.data.message + ',手机验证码:' + res.data.code,
                            duration: 3000
                        });
                        var that = this;
                        // 倒计时秒
                        function settime(num) {
                            var num = that.CountdownBt;
                            if (num > 0) {
                                num--;
                                that.CountdownBt = num;
                                setTimeout(() => {
                                    settime()
                                }, 1000)
                            }
                        }
                        that.CountdownBt = 90
                        settime();
                    } else {

                        this.resetCaptcha(); //刷新验证码	
                        uni.showModal({
                            showCancel: false,
                            title: '失败提示',
                            content: res.data.message ? res.data.message : '未知'
                        });

                    }

                })

            },
            resetCaptcha() {
                var phone = this.form.phone || 0;
                var that = this;
                Request('Captcha', {
                    data: {
                        outType: 'base64',
                        sid: phone
                    }
                }, {
                    // responseType: 'arraybuffer',
                }).then(res => {
                    // console.log(res)
                    if (res.data) {
                        that.isShowCaptcha = true;
                        that.captcha = res.data;
                    }
                }).catch(res => {
                    // console.log(res)
                })
            },
            bindDateChange: function(e) {
                // console.log(e)
                this.form.effective_time = this.date
                this.date = e.target.value
            },
            getDate(type) {
                const date = new Date();
                let year = date.getFullYear();
                let month = date.getMonth() + 1;
                let day = date.getDate();

                if (type === 'start') {
                    year = year;
                } else if (type === 'end') {
                    year = year + 60;
                }
                month = month > 9 ? month : '0' + month;;
                day = day > 9 ? day : '0' + day;
                return `${year}-${month}-${day}`;
            },
            submit() {

                var form = this.form;
                //                 for (let k in form) {
                //                     if (form[k] && form[k] != '') {
                //                         console.log(form[k])
                //                     } else {
                //                         uni.showModal({
                //                             title: '提示',
                //                             content: this.label[k] + '不能为空'
                //                         })
                //                         return
                //                     }
                // 
                //                 }
                var imgList = [];
                var imgListK = [];
                uni.showLoading({
                    title: '处理中请耐心等待……'
                })
                var formImg = this.formImg
                for (let k in formImg) {

                    if (formImg[k] != '') {
                        var isHave = true
                        if (this.images) {
                            this.images.filter(e => {
                                if (e.url == formImg[k]) {
                                    // 未更改的图片无需提交
                                    isHave = false
                                }
                            })
                        }
                        if (isHave) {
                            // 只提交有变化的数
                            imgList.push(formImg[k])
                            imgListK.push(k);
                        }
                        // console.log(formImg[k])
                    } else {
                        // uni.showModal({
                        //     title: '提示',
                        //     content: this.label[k] + '不能为空'
                        // })
                        // return
                    }

                }


                function requst(form) {
                    form.effective_time = Time.toTimestamp(form.effective_time);
                    console.log(form)
                    uni.showLoading({
                        title: '服务器请求中请等待……'
                    })
                    // var data = form;
                    // console.log(JSON.stringify(data))
                    Request('UserProve_create', {
                        data: {
                            form: form
                        }
                    }).then(res => {
                        uni.hideLoading()
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

                this.$refs.compress.yasuoImg(imgList).then(imgs => {
                    uni.hideLoading()
                    uni.showLoading({
                        title: '正在压缩图片请等待……'
                    })
                    // console.log(JSON.stringify(imgs))
                    YCImg.imgsToBase64(imgs).then(base64All => {
                        uni.hideLoading()
                        // console.log(JSON.stringify(['转成base64', base64All]))
                        var formimgs = [];
                        if (base64All.length == imgList.length) {

                            for (let i = 0; i < imgListK.length; i++) {
                                formimgs.push({
                                    name: imgListK[i],
                                    img: base64All[i]
                                });
                                // formimgs[imgListK[i]] = base64All[i]
                            }

                            form.imgs = formimgs
                            requst(form)
                        }

                    })

                })

            },
            openImage(name) {
                uni.chooseImage({
                    count: 1, //默认9
                    sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
                    sourceType: ['album'], //从相册选择
                    success: (res) => {
                        var formImg = this.formImg;
                        formImg[name] = res.tempFilePaths[0]
                        this.formImg = formImg;
                        console.log(JSON.stringify(res.tempFilePaths));
                    }
                });
            },
            // 返回的图片列表
            fileChange(e) {
                this.imgList = e;

            },
            getData() {
                Request('UserProve_detail', {
                    // data: {
                    //     form: form
                    // }
                }).then(res => {
                    // console.log(res)
                    // uni.hideLoading()
                    if (res.statusCode === 200) {
                        var data = res.data.data
                        this.detail = data.detail || {}
                    } else {}
                }).catch(e => {
                    // console.log(e)
                })
            }
        },
        onLoad(event) {
            if (event.query) {
                // 目前在某些平台参数会被主动 decode，暂时这样处理。
                try {
                    this.param = JSON.parse(decodeURIComponent(event.query));
                } catch (error) {
                    this.param = JSON.parse(event.query);
                }
            }
            this.getData();
            // this.query = 'query=' + encodeURIComponent(JSON.stringify(this.param))
        }
    }
</script>

<style scoped>
    .uni-label {
        width: 130px;
    }
</style>
