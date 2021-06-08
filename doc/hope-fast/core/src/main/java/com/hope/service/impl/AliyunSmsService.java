package com.hope.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.hope.config.SmsConfig;
import com.hope.consts.Constants;
import com.hope.exception.CustomException;
import com.hope.service.RedisService;
import com.hope.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 阿里云短信服务
 *
 * @author aodeng
 */
@Service
public class AliyunSmsService {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(AliyunSmsService.class);

    @Autowired
    private RedisService redisService;

    /**
     * 发送短信
     *
     * @author aodeng
     */
    public int sendsms(String phonenumbers, String code2, String uuid) {

        //图片验证码
        if (StringUtils.isEmpty(code2)) {
            throw new CustomException("图片验证码不能为空");
        }
        if (StringUtils.isEmpty(uuid)) {
            throw new CustomException("图片验证码已失效");
        }
        String verifyKey2 = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisService.getCacheObject(verifyKey2);
        redisService.deleteObject(verifyKey2);

        if (!code2.equalsIgnoreCase(captcha)) {
            throw new CustomException("图片验证码错误");
        }

        //手机号校验
        if (StringUtils.isEmpty(phonenumbers)) {
            throw new CustomException("手机号不能为空");
        }

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", SmsConfig.getAccessKeyId(), SmsConfig.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("SignName", SmsConfig.getSignName());
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phonenumbers);
        request.putQueryParameter("TemplateCode", SmsConfig.getTemplateCode());
        String verifyCode = RandomUtil.randomNumbers(4);
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + verifyCode + "\"}");

        //正式调用代码
        /*try {
            CommonResponse response = client.getCommonResponse(request);
            logger.info("短信发送日志：{},验证码：{}发送手机号；{}", response.getData(), verifyCode, phonenumbers);
            //判断发送成功存入redis，设置过期时间1分钟
            JSONObject jsonObject = JSONUtil.parseObj(response.getData());
            if ("OK".equals(jsonObject.get("Message"))) {
                String verifyKey = Constants.SMS_CODE_KEY + phonenumbers;
                redisService.setCacheObject(verifyKey, verifyCode, Constants.SMS_CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
            }
        } catch (ServerException e) {
            logger.error("短信发送失败！：{}", e.getMessage());
            throw new RuntimeException("短信发送失败！");
        } catch (ClientException e) {
            logger.error("短信发送失败！：{}", e.getMessage());
            throw new RuntimeException("短信发送失败！");
        }*/

        //开发测试调用代码，节省短信调用次数
        JSONObject jsonObject= JSONUtil.parseObj("{\"Message\":\"OK\",\"RequestId\":\"CA830728-6D17-4757-AF12-33C4BC7C0EDB\",\"BizId\":\"821820800757100677^0\",\"Code\":\"OK\"}");
        if ("OK".equals(jsonObject.get("Message"))){
            String verifyKey = Constants.SMS_CODE_KEY + phonenumbers;
            logger.info("测试验证码：{}",6666);
            redisService.setCacheObject(verifyKey,"6666", Constants.SMS_CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        }
        return 1;
    }
}
