let App = getApp();

Page({
  data: {
    disabled: false,
    orderId: 0,
    goodsId: 0,
    orderGoods: {},
    content: '',
    stars: [0, 1, 2, 3, 4],
    star: 5,
    starText: '十分满意'
  },

  selectRater: function(e) {
    var star = e.currentTarget.dataset.star + 1;
    var starText;
    if (star == 1) {
      starText = '很差';
    } else if (star == 2) {
      starText = '不太满意';
    } else if (star == 3) {
      starText = '满意';
    } else if (star == 4) {
      starText = '比较满意';
    } else {
      starText = '十分满意'
    }
    this.setData({
      star: star,
      starText: starText
    })
  },

  onLoad: function(options) {
    var that = this;
    that.setData({
      orderId: options.orderId,
      goodsId: options.goodsId
    });
    this.getOrderGoods();
  },
  getOrderGoods: function() {
    let _this = this;
    App._get('comment/detail', {
      orderId: _this.data.orderId,
      goodsId: _this.data.goodsId
    }, function (result) {
      _this.setData({
        orderGoods: result.data.orderGoods,
      });
      wx.hideToast();
    });
  },

  onClose: function() {
    wx.navigateBack();
  },

  onPost: function() {
    let that = this;

    if (!this.data.content) {
      App.showError('请填写评论');
      return false;
    }

    // 提交到后端
    App._post_form('comment/add', {
      valueId: that.data.orderGoods.goodsId,
      type: 0,
      content: that.data.content,
      star: that.data.star
    }, function (result) {
      App.showSuccess(result.msg, function () {
        wx.navigateBack();
      });
    }, false, function () {
      // 解除禁用
      that.setData({
        disabled: false
      });
    });
  },

  bindInputValue(event) {

    let value = event.detail.value;

    //判断是否超过140个字符
    if (value && value.length > 140) {
      return false;
    }

    this.setData({
      content: event.detail.value,
    })
  },
  onReady: function() {

  },
  onShow: function() {
    // 页面显示

  },
  onHide: function() {
    // 页面隐藏

  },
  onUnload: function() {
    // 页面关闭

  }
})