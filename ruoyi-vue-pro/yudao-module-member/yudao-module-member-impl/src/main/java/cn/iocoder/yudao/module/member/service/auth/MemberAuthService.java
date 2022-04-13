package cn.iocoder.yudao.module.member.service.auth;

import cn.iocoder.yudao.framework.security.core.service.SecurityAuthFrameworkService;
import cn.iocoder.yudao.module.member.controller.app.auth.vo.*;

import javax.validation.Valid;

/**
 * 会员的认证 Service 接口
 *
 * 提供用户的账号密码登录、token 的校验等认证相关的功能
 *
 * @author 芋道源码
 */
public interface MemberAuthService extends SecurityAuthFrameworkService {

    /**
     * 手机 + 密码登录
     *
     * @param reqVO 登录信息
     * @param userIp 用户 IP
     * @param userAgent 用户 UA
     * @return 身份令牌，使用 JWT 方式
     */
    String login(@Valid AppAuthLoginReqVO reqVO, String userIp, String userAgent);

    /**
     * 手机 + 验证码登陆
     *
     * @param reqVO 登陆信息
     * @param userIp 用户 IP
     * @param userAgent 用户 UA
     * @return 身份令牌，使用 JWT 方式
     */
    String smsLogin(@Valid AppAuthSmsLoginReqVO reqVO, String userIp, String userAgent);


    /**
     * 社交登录，使用 code 授权码
     *
     * @param reqVO 登录信息
     * @param userIp 用户 IP
     * @param userAgent 用户 UA
     * @return 身份令牌，使用 JWT 方式
     */
    String socialLogin(@Valid AppAuthSocialLoginReqVO reqVO, String userIp, String userAgent);

    /**
     * 社交登录，使用 手机号 + 手机验证码
     *
     * @param reqVO 登录信息
     * @param userIp 用户 IP
     * @param userAgent 用户 UA
     * @return 身份令牌，使用 JWT 方式
     */
    String socialLogin2(@Valid AppAuthSocialLogin2ReqVO reqVO, String userIp, String userAgent);

    /**
     * 社交绑定，使用 code 授权码
     *
     * @param userId 用户编号
     * @param reqVO 绑定信息
     */
    void socialBind(Long userId, @Valid AppAuthSocialBindReqVO reqVO);

    /**
     * 取消社交绑定
     *
     * @param userId 用户编号
     * @param reqVO 解绑信息
     */
    void unbindSocialUser(Long userId, @Valid AppAuthSocialUnbindReqVO reqVO);

    /**
     * 获得社交认证 URL
     *
     * @param type 社交平台类型
     * @param redirectUri 跳转地址
     * @return 认证 URL
     */
    String getSocialAuthorizeUrl(Integer type, String redirectUri);

    /**
     * 修改用户密码
     * @param userId 用户id
     * @param userReqVO 用户请求实体类
     */
    void updatePassword(Long userId, AppAuthUpdatePasswordReqVO userReqVO);

    /**
     * 忘记密码
     * @param userReqVO 用户请求实体类
     */
    void resetPassword(AppAuthResetPasswordReqVO userReqVO);

    /**
     * 给用户发送短信验证码
     *
     * @param userId 用户编号
     * @param reqVO 发送信息
     */
    void sendSmsCode(Long userId, AppAuthSendSmsReqVO reqVO);

}
