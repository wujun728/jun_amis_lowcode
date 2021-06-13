let App = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    nav_select: false, // 快捷导航
    options: {}, // 当前页面参数
    address: null, // 默认收货地址
    exist_address: false, // 是否存在收货地址
    intra_region: false, //配送区域收费
    goods: {}, // 商品信息
    disabled: false,
    hasError: false,
    error: '',
    code: '',
    couponPrice: 0.00, //优惠券的价格
    couponId: -1,
    availableCouponLength: 0, // 可用的优惠券数量
    availableCoupon: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    // 当前页面参数
    this.data.options = options;
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    // 获取当前订单信息
    this.getOrderData();
  },

  /**
   * 获取当前订单信息
   */
  getOrderData: function() {
    let _this = this,
      options = _this.data.options;

    // 获取订单信息回调方法
    let callback = function(result) {
      if (result.code !== 200) {
        App.showError(result.msg);
        _this.setData(result.data);
        return false;
      }
      // 显示错误信息
      if (result.data.has_error) {
        _this.data.hasError = true;
        _this.data.error = result.data.error_msg;
        App.showError(_this.data.error);
      }
      _this.setData(result.data);
    };
    // 立即购买
    if (options.order_type === 'buyNow') {
      App._get('order/buyNow', {
        goodsId: options.goods_id,
        number: options.goods_num,
        skudetailId: options.goods_sku_id,
        couponId: _this.data.couponId,
      }, function(result) {
        callback(result);
      });
    }else if (options.order_type === 'cart') { // 购物车结算
      App._get('order/cart', {couponId: _this.data.couponId}, function(result) {
        callback(result);
      });
    }

  },

  /**
   * 选择收货地址
   */
  selectAddress: function() {
    wx.navigateTo({
      url: '../address/' + (this.data.exist_address ? 'index?from=flow' : 'create')
    });
  },

  selectCoupon() {
    let _this = this;
    var availableCoupon = _this.data.availableCoupon;
    wx.navigateTo({
      url: '/pages/couponSelect/couponSelect?availableCoupon=' + JSON.stringify(availableCoupon),
    })
  },

  /**
   * 订单提交
   */
  submitOrder: function() {
    let _this = this,
      options = _this.data.options;

    if (_this.data.disabled) {
      return false;
    }

    if (_this.data.hasError) {
      App.showError(_this.data.error);
      return false;
    }

    wx.login({
      success: function (res) {
        _this.setData(res);
      }
    });

    // 订单创建成功后回调--微信支付
    let callback = function(result) {
      if (result.code != 200) {
        App.showError(result.msg, function() {
          // 跳转到未付款订单
          wx.redirectTo({
            url: '../order/index?type=payment',
          });
        });
        return false;
      }
      // 发起微信支付
      wx.requestPayment({
        timeStamp: result.data.payment.timeStamp,
        nonceStr: result.data.payment.nonceStr,
        package: result.data.payment.packageValue,
        signType: 'MD5',
        paySign: result.data.payment.paySign,
        success: function(res) {
          // 跳转到订单详情
          wx.redirectTo({
            url: '../order/detail?order_id=' + result.data.orderId,
          });
        },
        fail: function() {
          App.showError('订单未支付', function() {
            // 跳转到未付款订单
            wx.redirectTo({
              url: '../order/index?type=payment',
            });
          });
        },
      });
    };

    // 按钮禁用, 防止二次提交
    _this.data.disabled = true;

    // 显示loading
    wx.showLoading({
      title: '正在处理...'
    });

    // 创建订单-立即购买
    if (options.order_type === 'buyNow') {
      App._post_form('order/addOrderBuyNow', {
        goodsId: options.goods_id,
        number: options.goods_num,
        skudetailId: options.goods_sku_id,
        couponId: _this.data.couponId,
      }, function(result) {
        // success
        callback(result);
      }, function(result) {
        // fail
        console.log('fail');
      }, function() {
        // complete
        console.log('complete');
        // 解除按钮禁用
        _this.data.disabled = false;
      });
    }else if (options.order_type === 'cart') { // 创建订单-购物车结算
      App._post_form('order/addOrderFromCart', { couponId: _this.data.couponId }, function(result) {
        // success
        callback(result);
      }, function(result) {
        // fail
        console.log('fail');
      }, function() {
        // complete
        console.log('complete');
        // 解除按钮禁用
        _this.data.disabled = false;
      });
    }

  },


});