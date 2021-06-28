<template>
    <view class="uni-page-body">
        <!-- <popup-input ref="popupInput"></popup-input> -->
        <view class="uni-common-mt">
            <block v-for="item in formList" :key='item.name'>
                <view class="uni-form-item uni-column">
                    <view class="title">{{item.lable}}</view>
                    <input class="uni-input" v-model="form[item.name]" focus :placeholder="item.placeholder" />
                </view>
            </block>
            <button type="primary" @click="submit">确认</button>
        </view>
    </view>
</template>

<script>
    import popupInput from "@/components/template/popup/input.vue"
    import Request from '@/request/index.js'
    import {
        Validate,
        Md5
    } from '@/common/yc_js/'
    export default {
        data() {
            return {
                form: {
                    username: '',
                    password: '',

                },
                formList: [{
                    name: 'username',
                    lable: '用户名',
                    placeholder: '请输入用户名',
                    // text: '请输入评论内容'
                }, {
                    name: 'password',
                    lable: '登录密码',
                    placeholder: '请输入登录密码',
                    // text: '请输入评论内容'
                }, {
                    lable: '确认密码',
                    name: 'confirm_password',
                    placeholder: '再次输入登录密码',
                    // text: '请输入评论内容'
                }]
            }
        },
        components: {
            popupInput,
        },
        methods: {
            submit() {
                var form = this.form
                var info = {
                    username: '用户名',
                    password: '密码',
                    confirm_password: '确认密码'
                }
                var checkName = {
                    ok: true,
                    message: '验证通过,可以注册'
                }
                for (let key in form) {
                    checkName = Validate.checkUsername(form[key]);
                    if (!checkName.ok) {
                        checkName.message = info[key] + checkName.message
                        break;
                    }
                }
                if (!form.password) {
                    checkName.ok = false;
                    checkName.message = '请输入密码';
                }
                if (form.password != form.confirm_password) {
                    checkName.ok = false;
                    checkName.message = '二次密码不一致';
                }
                if (checkName.ok) {
                    var query = {
                        username: form.username,
                        password: Md5.Md5(Md5.Md5(form.username + form.password))
                    }
                    Request('LoginCreateUsername', {
                        data: query
                    }).then(res => {
                        if (res.statusCode == 200) {
                            var userInfo = this.$store.getters.userInfo;
                            userInfo.login = {
                                username: res.data.data.username,
                            }
                            this.$store.commit('userInfo', userInfo)
                            uni.showLoading({
                                title: '设置成功，正在跳转'
                            });

                            setTimeout(() => {
                                uni.hideLoading();
                                uni.navigateBack();
                            }, 1000)
                        } else {
                            uni.showModal({
                                title: '失败',
                                content: res.data.message,
                                showCancel: false,
                                success: res => {},
                                fail: () => {},
                                complete: () => {}
                            });
                        }
                        // console.log(JSON.stringify(res))
                    })
                } else {
                    uni.showModal({
                        title: '提示',
                        content: checkName.message,
                        success: res => {
                            console.log(res)
                        },
                        fail: () => {},
                        complete: () => {}
                    });
                }

            },
        },
        onReady() {

        },
        onLoad(event) {
            // console.log(this.$refs.popupInput)
        }
    }
</script>

<style>
</style>
