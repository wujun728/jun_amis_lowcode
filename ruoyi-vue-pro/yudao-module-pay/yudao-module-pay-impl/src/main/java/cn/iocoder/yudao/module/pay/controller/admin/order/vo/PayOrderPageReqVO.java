package cn.iocoder.yudao.module.pay.controller.admin.order.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("管理后台 - 支付订单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PayOrderPageReqVO extends PageParam {

    @ApiModelProperty(value = "商户编号")
    private Long merchantId;

    @ApiModelProperty(value = "应用编号")
    private Long appId;

    @ApiModelProperty(value = "渠道编号")
    private Long channelId;

    @ApiModelProperty(value = "渠道编码")
    private String channelCode;

    @ApiModelProperty(value = "商户订单编号")
    private String merchantOrderId;

    @ApiModelProperty(value = "商品标题")
    private String subject;

    @ApiModelProperty(value = "商品描述")
    private String body;

    @ApiModelProperty(value = "异步通知地址")
    private String notifyUrl;

    @ApiModelProperty(value = "通知商户支付结果的回调状态")
    private Integer notifyStatus;

    @ApiModelProperty(value = "支付金额，单位：分")
    private Long amount;

    @ApiModelProperty(value = "渠道手续费，单位：百分比")
    private Double channelFeeRate;

    @ApiModelProperty(value = "渠道手续金额，单位：分")
    private Long channelFeeAmount;

    @ApiModelProperty(value = "支付状态")
    private Integer status;

    @ApiModelProperty(value = "用户 IP")
    private String userIp;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始订单失效时间")
    private Date beginExpireTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束订单失效时间")
    private Date endExpireTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始订单支付成功时间")
    private Date beginSuccessTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束订单支付成功时间")
    private Date endSuccessTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始订单支付通知时间")
    private Date beginNotifyTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束订单支付通知时间")
    private Date endNotifyTime;

    @ApiModelProperty(value = "支付成功的订单拓展单编号")
    private Long successExtensionId;

    @ApiModelProperty(value = "退款状态")
    private Integer refundStatus;

    @ApiModelProperty(value = "退款次数")
    private Integer refundTimes;

    @ApiModelProperty(value = "退款总金额，单位：分")
    private Long refundAmount;

    @ApiModelProperty(value = "渠道用户编号")
    private String channelUserId;

    @ApiModelProperty(value = "渠道订单号")
    private String channelOrderNo;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始创建时间")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;

}
