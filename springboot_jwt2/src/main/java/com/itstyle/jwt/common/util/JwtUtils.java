package com.itstyle.jwt.common.util;

import com.itstyle.jwt.common.model.CheckResult;
import io.jsonwebtoken.*;
import org.apache.xerces.impl.dv.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * JWT加密和解密的工具类
 */
public class JwtUtils {
    /**
     * 加密字符串 禁泄漏
     */
    public static final String SECRET = "e3f4e0ffc5e04432a63730a65f0792b0";
    public static final int JWT_ERROR_CODE_NULL = 4000; // Token不存在
    public static final int JWT_ERROR_CODE_EXPIRE = 4001; // Token过期
    public static final int JWT_ERROR_CODE_FAIL = 4002; // 验证不通过

    /**
     * 签发JWT
     * @param id
     * @param subject
     * @param ttlMillis
     * @return  String
     */
    public static String createJWT(String id, String subject, long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey secretKey = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(subject)   // 主题
                .setIssuer("爪哇笔记")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey); // 签名算法以及密匙
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate); // 过期时间
        }
        return builder.compact();
    }
    /**
     * 验证JWT
     * @param jwtStr
     * @return  CheckResult
     */
    public static CheckResult validateJWT(String jwtStr) {
        CheckResult checkResult = new CheckResult();
        Claims claims;
        try {
            claims = parseJWT(jwtStr);
            checkResult.setSuccess(true);
            checkResult.setClaims(claims);
        } catch (ExpiredJwtException e) {
            checkResult.setErrCode(JWT_ERROR_CODE_EXPIRE);
            checkResult.setSuccess(false);
        } catch (SignatureException e) {
            checkResult.setErrCode(JWT_ERROR_CODE_FAIL);
            checkResult.setSuccess(false);
        } catch (Exception e) {
            checkResult.setErrCode(JWT_ERROR_CODE_FAIL);
            checkResult.setSuccess(false);
        }
        return checkResult;
    }

    /**
     * 密钥
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.decode(SECRET);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 解析JWT字符串
     * @param jwt
     * @return
     * @throws Exception  Claims
     */
    public static Claims parseJWT(String jwt) {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}