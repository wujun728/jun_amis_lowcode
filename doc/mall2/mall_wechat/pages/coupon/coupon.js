let App = getApp();

Page({
  data: {
    couponList: [],
    page: 1,
    limit: 10,
    count: 0,
    scrollTop: 0,
    showPage: false
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getCouponList();
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  getCouponList: function () {

    let that = this;
    that.setData({
      scrollTop: 0,
      showPage: false,
      couponList: []
    });
    // 页面渲染完成
    wx.showToast({
      title: '加载中...',
      icon: 'loading',
      duration: 2000
    });
    let _this = this;
    App._get('coupon/list', {page: that.data.page,limit: that.data.limit}, function (result) {
      _this.setData({
        scrollTop: 0,
        couponList: result.data.list,
        showPage: true,
        count: result.data.total
      });
      wx.hideToast();
    });
  },
  getCoupon(e) {
    let couponId = e.currentTarget.dataset.index;
    App._post_form('coupon/receive', {couponId: couponId}, function (result) {
      if (result.code === 200) {
        wx.showToast({
          title: "领取成功"
        })
      } else {
        App.showError("领取失败");
      }
    });
  },
  nextPage: function (event) {
    var that = this;
    if (this.data.page > that.data.count / that.data.limit) {
      return true;
    }

    that.setData({
      page: that.data.page + 1
    });

    this.getCouponList();

  },
  prevPage: function (event) {
    if (this.data.page <= 1) {
      return false;
    }

    var that = this;
    that.setData({
      page: that.data.page - 1
    });
    this.getCouponList();
  }
})