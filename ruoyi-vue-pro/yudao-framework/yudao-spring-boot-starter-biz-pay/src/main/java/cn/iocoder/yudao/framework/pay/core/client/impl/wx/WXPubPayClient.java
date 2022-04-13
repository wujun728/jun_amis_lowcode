package cn.iocoder.yudao.framework.pay.core.client.impl.wx;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.util.io.FileUtils;
import cn.iocoder.yudao.framework.common.util.object.ObjectUtils;
import cn.iocoder.yudao.framework.pay.core.client.PayCommonResult;
import cn.iocoder.yudao.framework.pay.core.client.dto.*;
import cn.iocoder.yudao.framework.pay.core.client.impl.AbstractPayClient;
import cn.iocoder.yudao.framework.pay.core.enums.PayChannelEnum;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderV3Request;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderV3Result;
import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

import static cn.iocoder.yudao.framework.common.util.json.JsonUtils.toJsonString;
import static cn.iocoder.yudao.framework.pay.core.client.impl.wx.WXCodeMapping.CODE_SUCCESS;
import static cn.iocoder.yudao.framework.pay.core.client.impl.wx.WXCodeMapping.MESSAGE_SUCCESS;

/**
 * 微信支付（公众号）的 PayClient 实现类
 *
 * @author 芋道源码
 */
@Slf4j
public class WXPubPayClient extends AbstractPayClient<WXPayClientConfig> {

    private WxPayService client;

    public WXPubPayClient(Long channelId, WXPayClientConfig config) {
        super(channelId, PayChannelEnum.WX_PUB.getCode(), config, new WXCodeMapping());
    }

    @Override
    protected void doInit() {
        WxPayConfig payConfig = new WxPayConfig();
        BeanUtil.copyProperties(config, payConfig, "keyContent");
        payConfig.setTradeType(WxPayConstants.TradeType.JSAPI); // 设置使用 JS API 支付方式
//        if (StrUtil.isNotEmpty(config.getKeyContent())) {
//            payConfig.setKeyContent(config.getKeyContent().getBytes(StandardCharsets.UTF_8));
//        }
        if (StrUtil.isNotEmpty(config.getPrivateKeyContent())) {
            // weixin-pay-java 存在 BUG，无法直接设置内容，所以创建临时文件来解决
            payConfig.setPrivateKeyPath(FileUtils.createTempFile(config.getPrivateKeyContent()).getPath());
        }
        if (StrUtil.isNotEmpty(config.getPrivateCertContent())) {
            // weixin-pay-java 存在 BUG，无法直接设置内容，所以创建临时文件来解决
            payConfig.setPrivateCertPath(FileUtils.createTempFile(config.getPrivateCertContent()).getPath());
        }
        // 真实客户端
        this.client = new WxPayServiceImpl();
        client.setConfig(payConfig);
    }

    @Override
    public PayCommonResult<WxPayMpOrderResult> doUnifiedOrder(PayOrderUnifiedReqDTO reqDTO) {
        WxPayMpOrderResult response;
        try {
            switch (config.getApiVersion()) {
                case WXPayClientConfig.API_VERSION_V2:
                    response = this.unifiedOrderV2(reqDTO);
                    break;
                case WXPayClientConfig.API_VERSION_V3:
                    WxPayUnifiedOrderV3Result.JsapiResult responseV3 = this.unifiedOrderV3(reqDTO);
                    // 将 V3 的结果，统一转换成 V2。返回的字段是一致的
                    response = new WxPayMpOrderResult();
                    BeanUtil.copyProperties(responseV3, response, true);
                    break;
                default:
                    throw new IllegalArgumentException(String.format("未知的 API 版本(%s)", config.getApiVersion()));
            }
        } catch (WxPayException e) {
            log.error("[unifiedOrder][request({}) 发起支付失败，原因({})]", toJsonString(reqDTO), e);
            return PayCommonResult.build(ObjectUtils.defaultIfNull(e.getErrCode(), e.getReturnCode(), "CustomErrorCode"),
                    ObjectUtils.defaultIfNull(e.getErrCodeDes(), e.getCustomErrorMsg()),null, codeMapping);
        }
        return PayCommonResult.build(CODE_SUCCESS, MESSAGE_SUCCESS, response, codeMapping);
    }


    private WxPayMpOrderResult unifiedOrderV2(PayOrderUnifiedReqDTO reqDTO) throws WxPayException {
        // 构建 WxPayUnifiedOrderRequest 对象
        WxPayUnifiedOrderRequest request = WxPayUnifiedOrderRequest.newBuilder()
                .outTradeNo(reqDTO.getMerchantOrderId())
                // TODO 芋艿：貌似没 title？
                .body(reqDTO.getBody())
                .totalFee(reqDTO.getAmount().intValue()) // 单位分
                .timeExpire(DateUtil.format(reqDTO.getExpireTime(), "yyyyMMddHHmmss"))
                .spbillCreateIp(reqDTO.getUserIp())
                .openid(getOpenid(reqDTO))
                .notifyUrl(reqDTO.getNotifyUrl())
                .build();
        // 执行请求
        return client.createOrder(request);
    }

    private WxPayUnifiedOrderV3Result.JsapiResult unifiedOrderV3(PayOrderUnifiedReqDTO reqDTO) throws WxPayException {
        // 构建 WxPayUnifiedOrderRequest 对象
        WxPayUnifiedOrderV3Request request = new WxPayUnifiedOrderV3Request();
        request.setOutTradeNo(reqDTO.getMerchantOrderId());
        // TODO 芋艿：貌似没 title？
        request.setDescription(reqDTO.getBody());
        request.setAmount(new WxPayUnifiedOrderV3Request.Amount().setTotal(reqDTO.getAmount().intValue())); // 单位分
        request.setTimeExpire(DateUtil.format(reqDTO.getExpireTime(), "yyyyMMddHHmmss"));
        request.setPayer(new WxPayUnifiedOrderV3Request.Payer().setOpenid(getOpenid(reqDTO)));
        request.setSceneInfo(new WxPayUnifiedOrderV3Request.SceneInfo().setPayerClientIp(reqDTO.getUserIp()));
        request.setNotifyUrl(reqDTO.getNotifyUrl());
        // 执行请求
        return client.createOrderV3(TradeTypeEnum.JSAPI, request);
    }

    private static String getOpenid(PayOrderUnifiedReqDTO reqDTO) {
        String openid = MapUtil.getStr(reqDTO.getChannelExtras(), "openid");
        if (StrUtil.isEmpty(openid)) {
            throw new IllegalArgumentException("支付请求的 openid 不能为空！");
        }
        return openid;
    }

    @Override
    public PayOrderNotifyRespDTO parseOrderNotify(PayNotifyDataDTO data) throws WxPayException {
        WxPayOrderNotifyResult notifyResult = client.parseOrderNotifyResult(data.getBody());
        Assert.isTrue(Objects.equals(notifyResult.getResultCode(), "SUCCESS"), "支付结果非 SUCCESS");
        // 转换结果
        return PayOrderNotifyRespDTO.builder().orderExtensionNo(notifyResult.getOutTradeNo())
                .channelOrderNo(notifyResult.getTransactionId()).channelUserId(notifyResult.getOpenid())
                .successTime(DateUtil.parse(notifyResult.getTimeEnd(), "yyyyMMddHHmmss"))
                .data(data.getBody()).build();
    }

    @Override
    public PayRefundNotifyDTO parseRefundNotify(PayNotifyDataDTO notifyData) {
        //TODO 需要实现
        throw new UnsupportedOperationException("需要实现");
    }


    @Override
    protected PayCommonResult<PayRefundUnifiedRespDTO> doUnifiedRefund(PayRefundUnifiedReqDTO reqDTO) throws Throwable {
        //TODO 需要实现
        throw new UnsupportedOperationException();
    }

}
