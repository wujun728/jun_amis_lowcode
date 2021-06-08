package com.hope.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hope.exception.CustomException;
import com.hope.mapper.TmConfigMapper;
import com.hope.model.bean.TmConfig;
import com.hope.utils.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletResponse;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信开发工具实现类，获取openid等
 * 官方文档地址： https://developers.weixin.qq.com/miniprogram/dev/api/open-api/login/wx.login.html
 *
 * @author aodeng
 */
@Service
public class WeChatService {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(WeChatService.class);

    @Autowired
    private TmConfigMapper tmConfigMapper;

    /**
     * 根据code获取微信小程序用户openid，session_key
     *
     * @author aodeng
     */
    public Map<String, Object> decodeOpenid(HttpServletResponse response, String code) throws Exception{
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        //获取存放在数据库的配置信息
        TmConfig tmConfig = tmConfigMapper.selectOne(new QueryWrapper<TmConfig>().eq("item_first_cls", "wxspconfig"));
        //填写小程序appid
        String wxspAppid = tmConfig.getItemValue();
        //填写小程序密钥
        String wxspSecret = tmConfig.getRemark2();

        // 授权（必填）
        String grant_type = "authorization_code";
        Map<String, Object> formMap = new HashMap<>();
        formMap.put("appid", wxspAppid);
        formMap.put("secret", wxspSecret);
        formMap.put("js_code", code);
        formMap.put("grant_type", grant_type);
        // 发送请求
        String sr = HttpRequest.post("https://api.weixin.qq.com/sns/jscode2session").form(formMap).timeout(20000)
                .execute().body();
        // 解析相应内容（转换成json对象）
        JSONObject json = JSONObject.parseObject(sr);
        logger.info("解析code请求结果:" + json.toString());
        // 获取会话密钥（session_key）
        String session_key = json.getString("session_key");
        String openid = json.getString("openid");
        String errcode = json.getString("errcode");
        if (StringUtils.isNotEmpty(errcode)) {
            throw new CustomException("获取失败，微信异常code：" + errcode);
        } else if (StringUtils.isNotEmpty(openid)) {
            logger.info("openid:" + openid);
            Map<String, Object> map = new HashMap<String, Object>(16);
            map.put("openid", openid);
            map.put("session_key", session_key);
            return map;
        }
        return null;
    }

    /**
     * 解密小程序用户手机号
     * encryptedData机密数据 iv偏移量 这个两个参数前端获取
     * @author aodeng
     */
    public Object getPhoneNumber(String encryptedData, String session_key, String iv) throws Exception{
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(session_key);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);

        // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
        int base = 16;
        if (keyByte.length % base != 0) {
            int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
            keyByte = temp;
        }
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
        AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
        parameters.init(new IvParameterSpec(ivByte));
        // 初始化
        cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
        byte[] resultByte = cipher.doFinal(dataByte);
        if (null != resultByte && resultByte.length > 0) {
            String result = new String(resultByte, "UTF-8");
            return JSONObject.parseObject(result);
        }
        return null;
    }
}
