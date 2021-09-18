package com.jun.biz.common.utils;

import org.apache.commons.lang3.RandomStringUtils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created on 2020/10/28 10:39
 * <p>
 * Description: [TODO]
 * <p>
 * Company: []
 *
 * 
 */
public class AesEncryptUtil {
    //参数分别代表 算法名称/加密模式/数据填充方式
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    /**
     * 加密
     * @param content 加密的字符串
     * @param encryptKey 16位 key值
     * @return
     */
    public static String encrypt(String content, String encryptKey)  {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128);
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));
            byte[] b = cipher.doFinal(content.getBytes());
            // 采用base64算法进行转码,避免出现中文乱码
            return Base64.getEncoder().encodeToString(b);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException("aes加密异常！",e);
        }

    }

    /**
     * 解密
     * @param encryptStr 解密的字符串
     * @param decryptKey 16位解密的key值
     * @return
     */
    public static String decrypt(String encryptStr, String decryptKey)  {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128);
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
            // 采用base64算法进行转码,避免出现中文乱码
            byte[] encryptBytes = Base64.getDecoder().decode(encryptStr);
            byte[] decryptBytes = cipher.doFinal(encryptBytes);
            return new String(decryptBytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException("aes解密异常！",e);
        }
    }

    public static void main(String[] args) {
        String key = RandomStringUtils.randomAlphanumeric(16);
        System.out.println(key);
        String encrypt = encrypt("请求数据解密", key);
        String decrypt = decrypt( "sadfadfjasl;dkfjas;ldfkjweqoirasjfdkjashdfqwueoidfsdg", key);
        System.out.println(encrypt);
        System.out.println(decrypt);
    }

}
