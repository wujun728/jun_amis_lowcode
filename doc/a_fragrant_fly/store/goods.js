
import {Storage} from '@/common/yc_js/';

	export default{
	state: {

		shop:{shop_id:0,shop_name:'未知'},
		cartTotal:0,//购物车有效商品 总价
		cartCount:0,//购物车有效商品 统计总数
		cartItemCount:0,//购物车有效商品 种目数
		cart:{},
// 		{1:{//购物车
// 				"id":"1",
// 				"title":"点心",
// 				"shop_id":"1",
// 				"image":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544799000&di=1610b5317bb15dbb6a36e4d57cf4eb3f&imgtype=jpg&er=1&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fc995d143ad4bd1138854651451afa40f4bfb057f.jpg",
// 				"stock":"23",//库存
// 				"price":"88",//价格
// 				"total":88,//总价
// 				"selected":true,//选中状态
// 				"number":1,//购买数量
// 				"version":[{"name":"麻辣",price:87,stock:4,image:""}],//更多版本
// 				"versionIndex":1,
// 				"versionName":"麻辣",//版本选项名
// 			}}
		order:{},
// 				{sn:'fdsf22',shop_id:'',shop_name:'',sum:89.9
//				goods:[{//订单
// 						id: 1,
// 						title: '新鲜芹菜 半斤',
// 						image: 'https://img-cdn-qiniu.dcloud.net.cn/uploads/example/product3.jpg',
// 						num: 4,
// 						price: 0.01
// 				}
// 				]},
		orderTotal:0,
	},
	getters:{
		shop(state){
			return state.shop;
		},cart(state){
			return state.cart;
		},cartCount(state){
			return state.cartCount
		},cartTotal(state){
			return state.cartTotal
		},cartItemCount(state){
			return state.cartItemCount
		},order(state){
			return state.order
		},orderTotal(state){
			return state.orderTotal;
		}
	},
	mutations: {
		shop(state,Obj){
			var shop={};
			shop.shop_id=Obj.shop_id || 0;
			shop.shop_name=Obj.shop_name || "未知";
			state.shop=shop;
		},
		init(state){
			var that=this;
			Storage.Sync.get('cart'+state.shop.shop_id,function(data){
				if(data){
					that.commit('cart',data)
				}
			})
		},
		cartCount(state){
   //          console.log(Storage)
			// Storage.Sync.reset();
			// Storage.Sync.resetSync();
			// 统计购物车
			var cart=state.cart;
			var total=0;//订单总价
			var count=0;//订单总数
			var itemCount=0;//订单总商品
			for(var i in cart){
				if(cart[i].selected) {                     // 判断选中才会计算价格
					if(!cart[i].number){
						delete  cart[i];//删除为0的商品
					}else{
						count+=cart[i].number;
						itemCount+=1;
						total+=(cart[i].number*cart[i].price);
					}
				}
				
			}
			state.cartCount=count;
			state.cartTotal=Math.floor(total*100)/100;
			state.cartItemCount=itemCount;
			Storage.Sync.set('cart'+state.shop.shop_id,cart,5000);	
		},
		cart(state,Cart){
			state.cart=Cart;
			this.commit('cartCount')
		},version(state,Obj){
			var index=Obj.index;
			var goods=Obj.goods;
			goods.versionName=goods.version[index].name;
			goods.versionIndex=index;
			goods.image=goods.version[index].image|| goods.image;
			goods.price=goods.version[index].price || goods.price;
			goods.stock=goods.version[index].stock || goods.stock;
			goods.number=goods.number || 1;
			goods.total=goods.price*goods.number;
			// goods.selected=true;//当前商品是否选中状态
			// Cart[goods[key]]=goods
			var cart=state.cart;
			cart[goods.id]=goods;
			state.cart==cart;
			// console.log(cart)
			this.commit('cartCount')
		},
		order(state,order){
			state.order=order;
		},addCart(state,Obj){
			// 向购物车添加或删除商品
			var goods=Obj.goods;
			var method=Obj.methods || "+";
			var key= Obj.key || 'id';
			var Cart=state.cart;	
			var total=0;//订单总价
			goods.number= goods.number || 0;
			
			if(typeof Cart!='object'){
				Cart={}
			}

			
			if(Cart[goods[key]]){
				if(method=='+'){
					if(Cart[goods[key]].number>=Cart[goods[key]].stock){
						uni.showToast({
							title: '库存不足',
							icon:"none",
							duration: 2000
						});
						return console.log("操作无效"+method)
					}
					var item=Cart[goods[key]];
					if(typeof item.version==='object' && typeof item.versionIndex =='number'){
						var index=item.versionIndex;
						item.versionName=item.version[index].name || '';
						item.image=item.version[index].image|| item.image;
						item.price=item.version[index].price || item.price;
						item.stock=item.version[index].stock || item.stock;
					}
					item.number++;
					Cart[goods[key]]=item;
					// Cart[goods[key]].total=Cart[goods[key]].price*Cart[goods[key]].number;//单品总价
				}else if(Cart[goods[key]].number>0){
					Cart[goods[key]].number--
					// Cart[goods[key]].total=Cart[goods[key]].price*Cart[goods[key]].number;//单品总价
				}else{
					uni.showToast({
						title: '不能再减了',
						icon:"none",
						duration: 2000
					});
					return console.log("操作无效"+goods.number+method)
				}
				
			}else{
				if(method!='-'){
					goods.number=1;
					if(goods.version && goods.version[0] && goods.version[0].name && !goods.versionName){
						goods.versionName=goods.version[0].name;
						goods.versionIndex=0;
						goods.price=goods.version[0].price || goods.price;
						goods.image=goods.version[0].image || goods.image;
						goods.stock=goods.version[0].stock || goods.stock;
					}
					goods.total=goods.price;
					Cart[goods[key]]=goods
				}else{
					uni.showToast({
						title: '不能再减了',
						icon:"none",
						duration: 2000
					});
					return console.log("操作无效"+goods.number+method)
				}
			}
			Cart[goods[key]].total=Cart[goods[key]].price*Cart[goods[key]].number;//单品总价
			// console.log(state)
			state.cart=Cart;
			this.commit('cartCount')
		}
	},
	actions: {},
	}