package com.hope.controller.base;

import com.hope.service.impl.WeChatService;
import com.hope.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 微信相关控制类
 *
 * @author aodeng
 */
@RestController
@RequestMapping("/wechat")
public class WeChatController {

    @Autowired
    private WeChatService weChatService;

    /**
    * 根据code获取微信小程序用户openid，session_key
    * @author aodeng
    */
    @RequestMapping("decodeOpenidAndKey")
    public AjaxResult decodeOpenid(HttpServletResponse response, String code) throws Exception{
        return AjaxResult.success(weChatService.decodeOpenid(response,code));
    }

    /**
     * 解密小程序用户手机号
     * encryptedData加密数据 iv偏移量 这个两个参数前端获取
     * @author aodeng
     */
    @RequestMapping("getPhoneNumber")
    public AjaxResult getPhoneNumber(String encryptedData, String session_key, String iv) throws Exception{
        return AjaxResult.success(weChatService.getPhoneNumber(encryptedData,session_key,iv));
    }
}
