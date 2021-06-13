let App = getApp(),
  wxParse = require("../../wxParse/wxParse.js");

Page({

  /**
   * 页面的初始数据
   */
  data: {
    nav_select: false, // 快捷导航
    indicatorDots: true, // 是否显示面板指示点
    autoplay: true, // 是否自动切换
    interval: 3000, // 自动切换时间间隔
    duration: 800, // 滑动动画时长

    currentIndex: 1, // 轮播图指针
    floorstatus: false, // 返回顶部
    showView: true, // 显示商品规格

    mallGood: {}, // 商品详情信息
    pictureList: [], // 商品详情信息
    goods_price: 0, // 商品价格
    line_price: 0, // 划线价格
    stock_num: 0, // 库存数量

    goods_num: 1, // 商品数量
    goods_sku_id: 0, // 规格id
    cart_total_num: 0, // 购物车商品总数量
    specData: {}, // 多规格信息
    specifications:{}, //规格值json数据
    goodsComment:[]
  },

  // 记录规格的数组
  goods_spec_arr: [],

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    let _this = this;
    // 商品id
    _this.data.goods_id = options.goods_id;
    // 获取商品信息
    _this.getGoodsDetail();
  },

  /**
   * 获取商品信息
   */
  getGoodsDetail() {
    let _this = this;
    App._get('goods/detail', {
      goods_id: _this.data.goods_id
    }, function(result) {
      // 初始化商品详情数据
      let data = _this.initGoodsDetailData(result.data);
      _this.setData(data);
    });
    App._get('cart/goodscount', {}, function (result) {
      _this.setData(result.data);
    });
  },

  /**
   * 初始化商品详情数据
   */
  initGoodsDetailData(data) {
    let _this = this;
    // 富文本转码
    if (data.mallGood.detail.length > 0) {
      wxParse.wxParse('content', 'html', data.mallGood.detail, _this, 0);
    }
    // 商品价格/划线价/库存
    data.goods_sku_id = data.skuDetailList[0].skudetailId;
    data.goods_price = data.skuDetailList[0].goodPrice;
    data.line_price = data.skuDetailList[0].linePrice;
    data.stock_num = data.skuDetailList[0].number;
    var skudetailsArray = data.skuDetailList[0].skudetails.split("*");
    var specifications = "";
    for (var i = 0; i < skudetailsArray.length; i++) {
      specifications = specifications + (data.specData.spec_attr[i].group_name + ":" + skudetailsArray[i]) + " ";
    }
    data.specifications = specifications;
    // 初始化商品多规格
    data.specData = _this.initManySpecData(data.specData);
    return data;
  },

  /**
   * 初始化商品多规格
   */
  initManySpecData(data) {
    for (let i in data.spec_attr) {
      for (let j in data.spec_attr[i].spec_items) {
        if (j < 1) {
          data.spec_attr[i].spec_items[0].checked = true;
          this.goods_spec_arr[i] = data.spec_attr[i].spec_items[0].keyclassType;
        }
      }
    }
    return data;
  },

  /**
   * 点击切换不同规格
   */
  modelTap(e) {
    let attrIdx = e.currentTarget.dataset.attrIdx;
    let itemIdx = e.currentTarget.dataset.itemIdx;
    let specData = this.data.specData;
    for (let i in specData.spec_attr) {
      for (let j in specData.spec_attr[i].spec_items) {
        if (attrIdx == i) {
          specData.spec_attr[i].spec_items[j].checked = false;
          if (itemIdx == j) {
            specData.spec_attr[i].spec_items[itemIdx].checked = true;
            this.goods_spec_arr[i] = specData.spec_attr[i].spec_items[itemIdx].keyclassType;
          }
        }
      }
    }
    this.setData({
      specData
    });
    // 更新商品规格信息
    this.updateSpecGoods();
  },

  /**
   * 更新商品规格信息
   */
  updateSpecGoods() {
    let spec_sku_id = this.goods_spec_arr.join(' ');
    // 查找skuItem
    let spec_list = this.data.skuDetailList,
    skuItem = spec_list.find((val) => {
      return val.valueclassType == spec_sku_id;
    });
    var skudetailsArray = skuItem.skudetails.split("*");
    var specifications = "";
    for (var i = 0; i < skudetailsArray.length; i++){
      specifications = specifications + (this.data.specData.spec_attr[i].group_name + ":" + skudetailsArray[i]) + " ";
    }
    // 记录skudetailId
    // 更新商品价格、划线价、库存 （当前状态下的物品）
    if (typeof skuItem === 'object') {
      this.setData({
        goods_sku_id: skuItem.skudetailId,
        goods_price: skuItem.goodPrice,
        line_price: skuItem.linePrice,
        stock_num: skuItem.number,
        specifications: specifications,
      });
    }
  },

  /**
   * 设置轮播图当前指针 数字
   */
  setCurrent(e) {
    this.setData({
      currentIndex: e.detail.current + 1
    });
  },

  /**
   * 控制商品规格/数量的显示隐藏
   */
  onChangeShowState() {
    this.setData({
      showView: !this.data.showView
    });
  },

  /**
   * 返回顶部
   */
  goTop(t) {
    this.setData({
      scrollTop: 0
    });
  },

  /**
   * 显示/隐藏 返回顶部按钮
   */
  scroll(e) {
    this.setData({
      floorstatus: e.detail.scrollTop > 200
    })
  },

  /**
   * 增加商品数量
   */
  up() {
    this.setData({
      goods_num: ++this.data.goods_num
    })
  },

  /**
   * 减少商品数量
   */
  down() {
    if (this.data.goods_num > 1) {
      this.setData({
        goods_num: --this.data.goods_num
      });
    }
  },

  /**
   * 跳转购物车页面
   */
  flowCart: function () {
    wx.switchTab({
      url: "../flow/index"
    });
  },

  /**
   * 跳转到商品详情
   */
  toMore: function (e) {
    let goods_id = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: '../comment/comment?goodsId=' + goods_id
    });
  },

  /**
   * 加入购物车and立即购买
   */
  submit(e) {
    let _this = this,
      submitType = e.currentTarget.dataset.type;

    if (submitType === 'buyNow') {
      // 立即购买 跳转带参数
      wx.navigateTo({
        url: '../flow/checkout?' + App.urlEncode({
          order_type: 'buyNow',
          goods_id: _this.data.goods_id,
          goods_num: _this.data.goods_num,
          goods_sku_id: _this.data.goods_sku_id,
        })
      });
    } else if (submitType === 'addCart') {
      // 加入购物车
      App._post_form('cart/add', {
        goodsId: _this.data.goods_id,
        number: _this.data.goods_num,
        skudetailId: _this.data.goods_sku_id,
        specifications: _this.data.specifications,
      }, function(result) {
        App.showSuccess(result.msg);
        _this.setData(result.data.data);
      });
    }
  },

  /**
   * 分享当前页面
   */
  onShareAppMessage: function() {
    // 构建页面参数
    let _this = this;
    return {
      title: _this.data.mallGood.goodName,
      path: "/pages/goods/index?goods_id=" + _this.data.goods_id
    };
  },

})