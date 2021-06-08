let App = getApp();

Page({
  data: {
    comments: [],
    goodCommentList: [],
    badCommentList: [],
    valueId: 0,
    showType: 0,
    goodCount: 0,
    badCount: 0,
    goodPage: 1,
    badPage: 1,
    limit: 5
  },
  getCommentCount: function() {
    let that = this;
    App._get('comment/count', {
      valueId: that.data.valueId
    }, function (res) {
      that.setData({
        goodCount: res.data.goodCount,
        badCount: res.data.badCount
      });
    });
  },
  getCommentList: function() {
    let that = this;
    App._get('comment/list', {
      valueId: that.data.valueId,
      limit: that.data.limit,
      page: (that.data.showType == 0 ? that.data.goodPage : that.data.badPage),
      showType: that.data.showType
    }, function (res) {
      if (that.data.showType == 0) {
        that.setData({
          goodCommentList: that.data.goodCommentList.concat(res.data.comments),
          goodPage: res.data.page,
          comments: that.data.goodCommentList.concat(res.data.comments)
        });
      } else {
        that.setData({
          badCommentList: that.data.badCommentList.concat(res.data.comments),
          badPage: res.data.page,
          comments: that.data.badCommentList.concat(res.data.comments)
        });
      }
    });
  },
  onLoad: function(options) {
    // 页面初始化 options为页面跳转所带来的参数
    this.setData({
      valueId: options.goodsId
    });
    this.getCommentCount();
    this.getCommentList();
  },
  onReady: function() {
    // 页面渲染完成

  },
  onShow: function() {
    // 页面显示

  },
  onHide: function() {
    // 页面隐藏

  },
  onUnload: function() {
    // 页面关闭

  },
  switchTab0: function() {
    let that = this;
    that.setData({
      goodCommentList: [],
      goodPage: 1,
      comments: [],
      showType: 0
    });
    this.getCommentList();
  },
  switchTab1: function () {
    let that = this;
    that.setData({
      badCommentList: [],
      badPage: 1,
      comments: [],
      showType: 1
    });
    this.getCommentList();
  },

  onReachBottom: function() {
    if (this.data.showType == 0) {

      if (this.data.goodCount / this.data.limit < this.data.goodPage) {
        return false;
      }

      this.setData({
        'goodPage': this.data.goodPage + 1
      });
    } else {
      if (this.data.badCount / this.data.limit < this.data.badPage) {
        return false;
      }

      this.setData({
        'badPage': this.data.badPage + 1
      });
    }
    this.getCommentList();
  }
})