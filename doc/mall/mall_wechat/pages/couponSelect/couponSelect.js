let App = getApp();

Page({
  data: {
    options: {}, // 当前页面参数
    couponList: [],
    couponId: 0,
    scrollTop: 0
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // 当前页面参数
    this.data.options = options;
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

    wx.showToast({
      title: '加载中...',
      icon: 'loading',
      duration: 2000
    });

    let _this = this;
    _this.setData({
      couponList: JSON.parse(_this.options.availableCoupon)
    });
    wx.hideToast();
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
  onReachBottom() {
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  selectCoupon: function (e) {
    const pages = getCurrentPages(); //获取当前页面js里面的pages里的所有信息。
    const prevPage = pages[pages.length - 2];
    prevPage.setData({  // 将我们想要传递的参数在这里直接setData。上个页面就会执行这里的操作。
      couponId: e.currentTarget.dataset.id,
    })
    wx.navigateBack({
      delta: 1
    })
  },
  unselectCoupon: function() {
    // 如果优惠券ID设置-1，则表示订单不使用优惠券
    const pages = getCurrentPages(); //获取当前页面js里面的pages里的所有信息。
    const prevPage = pages[pages.length - 2];
    prevPage.setData({  // 将我们想要传递的参数在这里直接setData。上个页面就会执行这里的操作。
      couponId: -1,
    })
    wx.navigateBack({
      delta: 1
    })
  }

})