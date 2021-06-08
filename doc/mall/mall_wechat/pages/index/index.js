let App = getApp();

Page({
  data: {
    // banner轮播组件属性
    indicatorDots: true, // 是否显示面板指示点	
    autoplay: true, // 是否自动切换
    interval: 3000, // 自动切换时间间隔
    duration: 800, // 滑动动画时长
    imgHeights: {}, // 图片的高度
    imgCurrent: {}, // 当前banne所在滑块指针
    // 页面元素
    items: {},
    newest: {},
    best: {},
    coupon: [],
    scrollTop: 0,
    page: 2,
  },

  onLoad: function() {
    // 获取首页数据
    this.getIndexData();
  },

  /**
   * 获取首页数据
   */
  getIndexData: function() {
    let _this = this;
    App._get('index/page', {}, function(result) {
      _this.setData(result.data);
    });
  },

  getCoupon(e) {
    let couponId = e.currentTarget.dataset.index;
    App._post_form('coupon/receive', {couponId: couponId }, function (result) {
      if (result.code === 200) {
        wx.showToast({
          title: "领取成功"
        })
      } else {
        wx.showToast({
          title: "领取失败"
        })
      }
    });
  },

  //点击加载更多
  getMore: function (e) {
    let _this = this;
    var page = _this.data.page;    
    App._get('/index/getlist', {page:page}, function (result) {
      var prolist = result.data.prolist;
      if (prolist == '') {
        wx.showToast({
          title: '没有更多数据！',
          duration: 2000
        });
        return false;
      }
      _this.setData({
        page: page + 1,
        best: _this.data.best.concat(prolist)
      });
    });
  },

  bindChange: function(e) {
    let itemKey = e.target.dataset.itemKey,
    imgCurrent = this.data.imgCurrent;
    imgCurrent[itemKey] = e.detail.currentItemId;
    this.setData({
      imgCurrent
    });
  },

  goTop: function(t) {
    this.setData({
      scrollTop: 0
    });
  },

  scroll: function(t) {
    this.setData({
      indexSearch: t.detail.scrollTop
    }), t.detail.scrollTop > 300 ? this.setData({
      floorstatus: !0
    }) : this.setData({
      floorstatus: !1
    });
  },

  onShareAppMessage: function() {
    return {
      title: "小程序首页",
      desc: "",
      path: "/pages/index/index"
    };
  }
});