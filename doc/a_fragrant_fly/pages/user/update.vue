<template>
    <view class="tui">
        <view v-if="key=='headimg'" class="uni-list">

            <view class="uni-list-cell uni-flex" style="justify-content: center;" hover-class="uni-list-cell-hover">

                <image class="" style="padding:0 20upx;" :src="value" mode="aspectFit" @tap="toPage()"></image>

            </view>
        </view>
        <view v-else-if="key=='area'" class="uni-list">
            <view class="uni-padding-wrap uni-common-mt">

                <view class="uni-list-cell-navigate  uni-navigate-right" @click="showMulLinkageThreePicker">
                    {{value}}

                </view>
            </view>
            <mpvue-city-picker :themeColor="themeColor" ref="mpvueCityPicker" :dataList="cityPickerData"
                :pickerValueDefault="cityPickerValueDefault" @onConfirm="onConfirm"></mpvue-city-picker>
        </view>
        <view v-else class="uni-list">
            <view class="uni-list-cell uni-flex" style="justify-content: space-between;" hover-class="uni-list-cell-hover">
                <input class="uni-input" type="text" :value="value" @input="input" />
            </view>
        </view>
        <view class="tui-flex" style="padding: 40upx 0;align-self: flex-end;width: 100%;">

            <button  style="width: 100%;" type="primary" size="default" @tap="confirm()">确认</button>
        </view>

    </view>
</template>

<script>
    // import Time from "../../common/utils/Time.js";

    import {
        Url,
        ajax,
        Storage
    } from '@/common/yc_js/';

    // import crop from '../../pages/tool/crop.vue';
    // import mpvuePicker from '../../components/mpvue-picker/mpvuePicker.vue';

    import mpvueCityPicker from '../../components/picker/cityPicker.vue';
    import provinceData from '@/components/picker/city-data/province.js';
    import cityData from '@/components/picker/city-data/city.js';
    import areaData from '@/components/picker/city-data/area.js';
    // import cityData from '../../common/city.data.js';
    export default {
        components: {
            // crop,
            mpvueCityPicker
        },
        data() {
            return {
                title: "修改页面",
                value: "",
                oldValue: "",
                key: "",
                cityPickerData: [provinceData, cityData, areaData],
                // mulLinkageTwoPicker: cityData,
                cityPickerValueDefault: [0, 0, 1],
                themeColor: '#007AFF',
                pickerText: '', //三联地址内容

            }
        },
        onLoad(e) {
            console.log(e)
            this.key = e.key;
            this.value = e.value;
            this.oldValue = e.value;
        },
        methods: {
            toPage() {
                uni.navigateTo({
                    url: "../tool/crop?value=" + this.value
                })
            },
            // 三级联动选择
            showMulLinkageThreePicker() {
                this.$refs.mpvueCityPicker.show(true)
            },
            onConfirm(e) {
                this.value = e['label'];
                // this.newValue=e['label'];
                this.pickerText = JSON.stringify(e)
            },
            input(e) {

                this.value = e.detail.value;
                // console.log(e.detail.value)
            },
            confirm(e) {
                console.log(this.value)
                var self = this;
                if (this.oldValue == this.value) {
                    uni.showModal({
                        title: "提示",
                        content: '未做任何修改？',
                        showCancel: false,
                        success(e) {
                            if (e.confirm) {
                                console.log('已确认')
                            }
                        }
                    })

                } else {
                    uni.showModal({
                        title: "提示",
                        content: "确认修改？",
                        success(e) {
                            if (e.confirm) {
                                self.oldValue = self.value;
                                Storage.set('userInfoUpdate', self.value, 60);
                                var userinfo = self.$store.getters.userinfo;
                                if (userinfo[self.key]) {
                                    userinfo[self.key] = self.value;
                                    self.$store.commit('setUserinfo', userinfo);
                                }

                                uni.navigateBack({
                                    delta: 1
                                })
                                console.log('已确认')
                            } else {

                            }
                            console.log(e)
                        }
                    })
                }

            }
        },
        mounted() {
            var self = this;
            this.$nextTick(function() {
                if (self.key == 'area') {
                    self.showMulLinkageThreePicker();
                } else if (self.key == 'headimg') {
                    // self.toPage()
                }
            })
        },
        onShow() {
            var value = Storage.get('image');
            if (this.key == 'headimg' && value) {
                this.value = value;
            }
        }
    }
</script>

<style>
</style>
