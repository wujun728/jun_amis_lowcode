package com.jun.plugin.app.common.exception;

public class UnauthorizedException extends RuntimeException
{
    //
    private static final long serialVersionUID = 3885400551304383736L;

    public UnauthorizedException(String msg)
    {
        super(msg);
    }

    public UnauthorizedException()
    {
        super();
    }
}