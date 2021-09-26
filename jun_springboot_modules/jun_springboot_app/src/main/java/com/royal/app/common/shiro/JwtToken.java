package com.royal.app.common.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class JwtToken implements AuthenticationToken
{
    //
    private static final long serialVersionUID = -2442203732980013072L;

    // 密钥
    private String            token;

    public JwtToken(String token)
    {
        this.token = token;
    }

    @Override
    public Object getPrincipal()
    {
        return token;
    }

    @Override
    public Object getCredentials()
    {
        return token;
    }
}