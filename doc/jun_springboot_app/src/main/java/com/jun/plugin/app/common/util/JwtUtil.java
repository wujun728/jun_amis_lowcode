package com.jun.plugin.app.common.util;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil
{
    // 过期时间X分钟
    private static final long EXPIRE_TIME = 60 * 60 * 1000;

    /**
     * 校验token是否正确
     * @param token 密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String loginname, String secret)
    {
        try
        {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("loginname", loginname).build();
            verifier.verify(token);
            return true;
        }
        catch (Exception exception)
        {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getLoginname(String token)
    {
        try
        {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("loginname").asString();
        }
        catch (JWTDecodeException e)
        {
            return null;
        }
    }

    /**
     * 生成签名,Xmin后过期
     * @param loginname 用户名
     * @param secret 用户的密码
     * @return 加密的token
     */
    public static String sign(String loginname, String secret)
    {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带loginname信息
        return JWT.create().withClaim("loginname", loginname).withExpiresAt(date).sign(algorithm);
    }
}