let App = getApp();

Page({
  data: {
    addressList: [],
    default_id: '',
  },

  onLoad: function(options) {
    // 当前页面参数
    this.data.options = options;
  },

  onShow: function() {
    // 获取收货地址列表
    this.getAddressList();
  },

  /**
   * 获取收货地址列表
   */
  getAddressList: function() {
    let _this = this;
    App._get('address/list', {}, function(result) {
      _this.setData(result.data);
    });
  },

  /**
   * 添加新地址
   */
  createAddress: function() {
    wx.navigateTo({
      url: './create'
    });
  },

  /**
   * 编辑地址
   */
  editAddress: function(e) {
    wx.navigateTo({
      url: "./detail?addressId=" + e.currentTarget.dataset.addressId
    });
  },

  /**
   * 移除收货地址
   */
  removeAddress: function(e) {
    let _this = this,
      addressId = e.currentTarget.dataset.addressId;
    wx.showModal({
      title: "提示",
      content: "您确定要移除当前收货地址吗?",
      success: function(o) {
        o.confirm && App._post_form('address/delete', {
          addressId
        }, function(result) {
          _this.getAddressList();
        });
      }
    });
  },

  /**
   * 设置为默认地址
   */
  setDefault: function(e) {
    let _this = this,
      addressId = e.detail.value;
    App._post_form('address/setDefault', {
      oldAddressId: _this.data.default_id,
      newAddressId: addressId
    }, function(result) {
      _this.setData({
        default_id: parseInt(addressId)
      });
      _this.data.options.from === 'flow' && wx.navigateBack();
    });
    return false;
  },

});