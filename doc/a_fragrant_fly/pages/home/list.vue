<template>
	<view class="uni-flex tui" style="flex-direction: column;justify-content: space-between;position: relative;">
		<view style="box-sizing: border-box;flex: 1;"  :style="'height:+'+contentHeight+'px'">
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
			<page-shop-list   :windowHeight="contentHeight" v-if="index=='0'"></page-shop-list>	
<!-- 			<page-order :winHeight="winHeight"  :contentHeight="contentHeight" v-if="index=='1'"></page-order>
			<page-chat :winHeight="winHeight" :contentHeight="contentHeight"  v-if="index=='2'"></page-chat>
			<page-wode :winHeight="winHeight" :contentHeight="contentHeight" v-if="index=='3'"></page-wode> -->
		</view>
<!-- 		<view class="uni-flex" style="height: 98upx; border-top:1px solid #DDDDDD;" >
			<view  class="uni-flex-item uni-flex uni-column tui-center" v-for="(item,idx) in bottomNav" :key="idx" @tap="tijiao(idx,item)"  :class="index==idx?'uni-badge-primary uni-badge-inverted':''">
	
					<view class="nav-icon uni-item">
						<view class="tui-nav-badge" style="">
							<view class="uni-badge uni-badge-danger" style="font-size: 0.7em;position: absolute;" v-if="item.msg">
								<text v-if="item.msg>10">…</text>
								<text v-else>{{item.msg}}</text>
							</view>
						</view>
						<view class="uni-item iconfont" style="font-size:1.6em;line-height: 1.3em;" v-html="item.ico"></view>
					</view>
					<view class="uni-item nav-title">
							{{item.name}}
					</view>
			
			</view>
		</view> -->
</view>

</template>

<script>
	import pageShopList  from "./shopList.vue"
	import pageWode  from "../user/wode.vue"
	import pageChat  from "../../components/pages/chat/list.vue"
	import pageOrder  from "../order/list.vue"
	// import pageOrder  from "../order/list.vue"
	export default {
		components: { 
			pageShopList,pageWode,pageChat,pageOrder
			// bigAd,bottomNav,indexShopList,uniLoadMore
		},
		name: "bottomNav",
		data() {
			return {
                paixu: '综合排序',
                
                paixuList: ['综合排序', '速度最快', '评分最高', '起送价最低', '配送费最低', '人均高到低', '人均低到高'],
                
                contentTop:0,
				refreshTimeout:0,
				winHeight:0,
				contentHeight:0,
				index:0,
				bottomNav:[
					// 底部按钮数据
					{
						fn:this.tijiao,
						msg:0,
						name:'首页',
						ico:'&#xe98e;',
						url:'/pages/home/index'
					},{
						fn:this.tijiao,
						msg:1,
						name:'订单',
						ico:'&#xe8cd;',
						url:'/pages/order/list'
					},
					{
						fn:this.tijiao,
						msg:23,
						name:'消息',
						ico:'&#xe632;',
						url:'/pages/chat/list'
					},
					{
						fn:this.tijiao,
						msg:0,
						name:'我的',
						ico:'&#xe604;',
						url:'/pages/user/wode'
					}
				]
				
			}
		},onLoad(e){
            try{
                var param= JSON.parse(decodeURIComponent(e.param));
                this.param=param;
                // console.log(param)
                uni.setNavigationBarTitle({
                    title: param.name
                });
            }catch(e){
                //TODO handle the exception
            }

				let winHeight = uni.getSystemInfoSync().windowHeight;
			//创建节点选择器 获取底部导航高度 
				this.contentHeight=(winHeight-uni.upx2px(100));
                console.log(this.contentHeight)
				this.winHeight = winHeight;
			// console.log("template/nav/bottom/mounted")
		
		},methods:{
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
		},mounted() {
            console.log('shoplist')
			uni.hideLoading();
		}
	}
</script>

<style>
    .tag {
        box-sizing: border-box;
        padding: 5px;
        background: #EEEEEE;
    }
</style>
