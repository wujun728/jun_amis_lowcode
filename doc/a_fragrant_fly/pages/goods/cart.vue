<template>
    <view class="page-body tui">
        <drawer-bottom ref="drawerBottom" :goods="goods" v-on:change="goodsUpdate"></drawer-bottom>
        <view v-if="totalPrice">
            <!-- <view class="cart-box"> -->
            <scroll-view scroll-y class=" tui-flex tui-center tui-column" style="" :style="{height:contentHeight + 'px'}">

                <view class="tui-list cart-list" v-for="(item,key) in  cartData" :key="key" style="position: relative;">
                    <view class="cart-left">

                        <view class="" style="font-size: 1.5em;">
                            <text v-if="item.selected" class="iconfont" style="color:#32CD32;" :data-index="key" @tap="selectList(key)">&#xe99c;</text>
                            <text v-else class="iconfont" :data-index="key" @tap="selectList(key)">&#xe9ae;</text>
                            <view class=" iconfont icon-shanchu center" style="color: #999;" @tap="deleteList(key)"
                                :data-index="key"></view>
                        </view>
                        <image class="cart-thumb" :src="item.image" @tap="goPage('detail',item)" mode="aspectFill">

                        </image>

                    </view>
                    <view class="cart-right ">
                        <view class="uni-flex">
                            <view class="cart-pro-name uni-flex-item" style="">
                                {{item.title}}
                            </view>
                            <view class="cart-pro-price" style="color: red;">
                                ￥{{item.price}}
                            </view>
                        </view>

                        <view class="uni-flex" style="justify-content: space-between;">
                            <view class="uni-flex-item" style="font-size: 1.5em;">
                                <number-box :min="0" :max="Number(item.stock)" :item="item" :value="item.number" :other="{index:key}"
                                    v-on:change="numberUpdate"></number-box>
                            </view>
                            <view class="" v-if="item.version">
                                <button style="line-height: 1.4em;padding: 5upx;" size="mini" type="primary" @click="setGoods(item)">已选:{{item.versionName}}</button>
                            </view>


                        </view>
                        <view class="tui-item" style="background:#EEE9E9;">
                            <input type="text" value="" @blur="KeyInput" :data-key="key" placeholder="请在这里输入商品备注" />
                        </view>
                    </view>


                </view>
                <!-- </view> -->
            </scroll-view>

        </view>

        <view v-else>
            <view class="cart-no-data">购物车是空的哦~</view>
        </view>

        <nav class="tui-bottom-nav tui-flex " style="font-size: 1.4em">
            <view class="tui-flex tui-item  tui-row" style="background: #F9F9F9;z-index: 99;text-align: left;padding-left: 20upx;">
                <text v-if="selectAllStatus" class="iconfont " @tap="selectAll()" style="color:#32CD32;font-size: 1.6em;">&#xe99c;</text>
                <text v-else class="iconfont " @tap="selectAll()" style="font-size: 1.6em;">&#xe9ae;</text>
                <view class="tui-center" style="text-align: left;padding: 0 15upx;">全选</view>
                <view class="tui-center tui-item" style="text-align: right;padding-right: 30upx;">
                    合计:<text style="color: red;">￥{{totalPrice}}</text>
                </view>
            </view>
            <button type="warn" size="mini" @tap="goPage('order')" style="line-height:100upx;padding: 0 15upx;">确认下单</button>
        </nav>
    </view>
</template>

<script>
    import drawerBottom from '../../components/template/drawer/bottom.vue'
    import numberBox from '../../components/template/box/number.vue'
    import {Storage} from '@/common/yc_js/';
    import cart_Data from '../../request/data/cart.js';

    export default {
        components: {
            numberBox,
            drawerBottom
        },
        data() {
            return {
                goods: {},
                contentHeight: 0,
                winHeight: 0,
                change: 0,
                totalNumber: 0, //购物车总数量
                selectAllStatus: true, // 全选状态，默认全选
            };
        },
        computed: {
            totalPrice() {
                return this.$store.state.goods.cartTotal;
            },
            cartData: {
                get() {
                    return this.$store.getters.cart
                },
                set(cartData) {
                    return cartData
                }
            }

        },
        onLoad(e) {
            let winHeight = uni.getSystemInfoSync().windowHeight;
            //创建节点选择器 获取底部导航高度 
            this.contentHeight = (winHeight - uni.upx2px(105));
            this.winHeight = winHeight;

            var cartData = this.cartData;
            for (var k in cartData) {
                if (!cartData[k].number) {
                    cartData[k].number = 1;
                }
                if (!cartData[k].selected) {
                    cartData[k].selected = false;
                }
                cartData[k].stock = parseInt(cartData[k].stock)

                if (!cartData[k].versionName) {
                    let version = cartData[k].version;

                    if (version && version[0] && version[0].name) {
                        cartData[k].versionName = version[0].name;
                    }
                }
            }
            this.$store.commit('cart', cartData)

        },
        mounted() {

        },
        methods: {
            // 弹出遮罩商品类别选项触发
            goodsUpdate(e) {
                var cartData = this.cartData;
                cartData[e.id] = e;
                this.$store.commit('cart', cartData)
                console.log(e)
            },
            setGoods(item) {
                // console.log(this.$refs.drawerBottom)
                this.$refs.drawerBottom.drawerBottomShow = true;
                // this.drawerBottomShow=true;
                this.goods = item;
            },
            KeyInput(e) {
                var cartData = this.cartData;
                var key = e.target.dataset.key;
                cartData[key].note = e.detail.value;
                this.$store.commit('cart', cartData)
            },
            goPage(pg, item) {
                var id = "";
                var shop = this.$store.getters.shop;
                var id = shop.shop_id;
                if (pg === 'order') {
                    var orderData = [];
                    var cartData = this.cartData;
                    var sum = 0;
                    for (let key in cartData) {
                        var good = {};
                        if (cartData[key].selected && id === cartData[key].shop_id) {
                            id = cartData[key].shop_id;
                            good.id = cartData[key].id;
                            good.shop_id = cartData[key].shop_id;
                            good.number = cartData[key].number;
                            good.image = cartData[key].image;
                            good.note = cartData[key].note;
                            good.price = cartData[key].price;
                            good.title = cartData[key].title;
                            good.versionName = cartData[key].versionName;
                            orderData.push(good);
                            sum += (good.price * good.number)


                        }
                    }
                    var shop = this.$store.getters.shop;
                    var orders = {
                        sn: new Date().getTime(),
                        shop_id: id,
                        shop_name: shop.shop_name,
                        sum: Math.floor(sum * 100) / 100,
                        goods: orderData
                    };
                    console.log(orders)
                    this.$store.commit('order', orders)

                } else {
                    id = item.shop_id;
                }
                var cur = getCurrentPages()
                // console.log(cur);
                console.log("goods/cart" + pg)
                uni.navigateTo({
                    url: '/pages/goods/' + pg + "?id=" + id,
                    success: res => {
                        console.log('res1')
                    },
                    fail: () => {
                        console.log('res2')
                    },
                    complete: () => {
                        console.log('res3')
                    }
                });
            },
            // 购物数量增减 触发
            numberUpdate(Obj) {
                var value = Obj.value;
                var other = Obj.other;
                var item = Obj.item;
                var cart = this.cartData;
                item.number = value;
                this.cartData[item.id] = item;
                this.$store.commit('cart', this.cartData)

            },
            pageinit() {
                console.log('before')
                this.hasList = true;
                this.getTotalPrice();
            },
            /**
             * 当前商品选中事件
             */
            selectList(index) {
                // const index = e.currentTarget.dataset.index;
                let cartData = this.cartData;
                const selected = cartData[index].selected || false;
                cartData[index].selected = !selected;
                this.cartData = cartData;
                this.$store.commit('cart', this.cartData)
                // var item =item;
                // this.getTotalPrice();
            },
            /**
             * 删除购物车当前商品
             */
            deleteList(index) {
                let cartData = this.cartData;
                delete cartData[index];
                this.$store.commit('cart', cartData)
            },

            /**
             * 购物车全选事件
             */
            selectAll(e) {
                this.selectAllStatus = !this.selectAllStatus;
                let cartData = this.cartData;
                // var newData={}
                for (let index in cartData) {
                    if (cartData[index]) {
                        cartData[index].selected = this.selectAllStatus
                    }

                }
                this.$store.commit('cart', cartData)
            },
        },
    }
</script>

<style>
    .cart-box {
        padding-bottom: 100upx;
    }

    .cart-list {
        position: relative;
        width: 750upx;
        display: flex;
        padding: 15upx;
        box-sizing: border-box;
        justify-content: center;
        flex-direction: row;

        border-bottom: 1upx solid #e9e9e9;
    }

    .cart-left {
        display: flex;
        justify-content: space-around;
        align-items: center;
    }

    .cart-right {
        display: flex;
        flex: 1;
        flex-direction: column;
        justify-content: space-between;
    }

    .cart-right .cart-pro-name {
        font-size: 1.2em;
        color: #000;
        text-align: left;
        font-weight: bold;
    }

    .cart-right .picker {

        border-bottom: 1upx solid #e9e9e9;
    }

    .cart-list .cart-pro-select {
        align-self: center;

    }

    .cart-list .cart-thumb {
        padding: 0 15upx;
        width: 150upx;
        height: 150upx;
    }

    .cart-tip {
        height: 100%;
        display: flex;
        justify-content: space-around;
        flex-direction: column;
        align-items: center;
        right: 15upx;
        position: absolute;
    }

    .cart-count-box {
        font-size: 2em;
    }


    .total-select {
        position: absolute;
        left: 20upx;
        width: 45upx;
    }

    .cart-no-data {
        padding: 40upx 0;
        color: #999;
        text-align: center;
    }
</style>
