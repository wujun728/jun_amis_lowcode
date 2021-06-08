let App = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    goods_list: [], // 商品列表
    order_total_num: 0,
    order_total_price: 0,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    this.getCartList();
  },

  /**
   * 获取购物车列表
   */
  getCartList: function() {
    let _this = this;
    App._get('cart/lists', {}, function(result) {
      _this.setData(result.data);
    });
  },

  /**
   * 递增指定的商品数量
   */
  addCount: function(e) {
    let _this = this,
      index = e.currentTarget.dataset.index,
      goodsSkuId = e.currentTarget.dataset.skuId,
      goods = _this.data.goods_list[index],
      order_total_price = _this.data.order_total_price;
    // 后端同步更新
    wx.showLoading({
      title: '加载中',
      mask: true
    })
    App._post_form('cart/add', {
      goodsId: goods.goodsId,
      number: 1,
      skudetailId: goodsSkuId,
      specifications: goods.specifications
    }, function() {
      goods.number++;
      _this.setData({
        ['goods_list[' + index + ']']: goods,
        order_total_price: _this.mathadd(order_total_price, goods.goodPrice)
      });
    });
  },

  /**
   * 递减指定的商品数量
   */
  minusCount: function(e) {
    let _this = this,
      index = e.currentTarget.dataset.index,
      goodsSkuId = e.currentTarget.dataset.skuId,
      goods = _this.data.goods_list[index],
      order_total_price = _this.data.order_total_price;
    let number = (goods.number - 1 > 1) ? goods.number - 1 : 1;
    if (goods.number > 1) {
      wx.showLoading({
        title: '加载中',
        mask: true
      })
      App._post_form('cart/cut', {
        goodsId: goods.goodsId,
        number: number,
        cartId: goods.cartId,
        skudetailId: goodsSkuId
      }, function() {
        goods.number--;
        goods.number > 0 &&
        _this.setData({
          ['goods_list[' + index + ']']: goods,
          order_total_price: _this.mathsub(order_total_price, goods.goodPrice)
        });
      });

    }
  },

  /**
   * 删除商品
   */
  del: function(e) {
    let _this = this,
      cartId = e.currentTarget.dataset.cartId;
      var cartArray = new Array();
      cartArray.push(cartId);
    wx.showModal({
      title: "提示",
      content: "您确定要移除当前商品吗?",
      success: function(e) {
        e.confirm && App._post_form('cart/delete', {
          cartIdList: cartArray.join()
        }, function(result) {
          _this.getCartList();
        });
      }
    });
  },

  /**
   * 购物车结算
   */
  submit: function(t) {
    wx.navigateTo({
      url: '../flow/checkout?order_type=cart'
    });
  },

  /**
   * 加法
   */
  mathadd: function(arg1, arg2) {
    return (Number(arg1) + Number(arg2)).toFixed(2);
  },

  /**
   * 减法
   */
  mathsub: function(arg1, arg2) {
    return (Number(arg1) - Number(arg2)).toFixed(2);
  },

  /**
   * 去购物
   */
  goShopping: function() {
    wx.switchTab({
      url: '../index/index',
    });
  },

})