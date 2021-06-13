package com.qingshop.mall.modules.wxapi.service;

import com.qingshop.mall.modules.wxapi.utils.JwtHelper;

/**
 * 维护用户token
 */
public class UserTokenManager {
	
	public static String generateToken(Long id) {
        JwtHelper jwtHelper = new JwtHelper();
        return jwtHelper.createToken(id);
    }
	
    public static Long getUserId(String token) {
    	JwtHelper jwtHelper = new JwtHelper();
    	Long userId = jwtHelper.verifyTokenAndGetUserId(token);
    	if(userId == null || userId == 0L){
    		return null;
    	}
        return userId;
    }
}
