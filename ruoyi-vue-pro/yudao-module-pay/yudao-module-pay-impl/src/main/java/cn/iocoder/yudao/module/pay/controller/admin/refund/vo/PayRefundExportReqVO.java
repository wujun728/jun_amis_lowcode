package cn.iocoder.yudao.module.pay.controller.admin.refund.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 退款订单 Excel 导出 Request VO", description = "参数和 PayRefundPageReqVO 是一致的")
@Data
public class PayRefundExportReqVO {

    @ApiModelProperty(value = "商户编号")
    private Long merchantId;

    @ApiModelProperty(value = "应用编号")
    private Long appId;

    @ApiModelProperty(value = "渠道编号")
    private Long channelId;

    @ApiModelProperty(value = "渠道编码")
    private String channelCode;

    @ApiModelProperty(value = "支付订单编号 pay_order 表id")
    private Long orderId;

    @ApiModelProperty(value = "交易订单号 pay_extension 表no 字段")
    private String tradeNo;

    @ApiModelProperty(value = "商户订单编号（商户系统生成）")
    private String merchantOrderId;

    @ApiModelProperty(value = "商户退款订单号（商户系统生成）")
    private String merchantRefundNo;

    @ApiModelProperty(value = "异步通知商户地址")
    private String notifyUrl;

    @ApiModelProperty(value = "通知商户退款结果的回调状态")
    private Integer notifyStatus;

    @ApiModelProperty(value = "退款状态")
    private Integer status;

    @ApiModelProperty(value = "退款类型(部分退款，全部退款)")
    private Integer type;

    @ApiModelProperty(value = "支付金额,单位分")
    private Long payAmount;

    @ApiModelProperty(value = "退款金额,单位分")
    private Long refundAmount;

    @ApiModelProperty(value = "退款原因")
    private String reason;

    @ApiModelProperty(value = "用户 IP")
    private String userIp;

    @ApiModelProperty(value = "渠道订单号，pay_order 中的channel_order_no 对应")
    private String channelOrderNo;

    @ApiModelProperty(value = "渠道退款单号，渠道返回")
    private String channelRefundNo;

    @ApiModelProperty(value = "渠道调用报错时，错误码")
    private String channelErrorCode;

    @ApiModelProperty(value = "渠道调用报错时，错误信息")
    private String channelErrorMsg;

    @ApiModelProperty(value = "支付渠道的额外参数")
    private String channelExtras;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始退款失效时间")
    private Date beginExpireTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束退款失效时间")
    private Date endExpireTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始退款成功时间")
    private Date beginSuccessTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束退款成功时间")
    private Date endSuccessTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始退款通知时间")
    private Date beginNotifyTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束退款通知时间")
    private Date endNotifyTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始创建时间")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;

}
