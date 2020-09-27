package com.puboot.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class R {


    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 返回码
     */
    private int code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private Object data;


    /**
     * 自定义返回结果
     * 建议使用统一的返回结果，特殊情况可以使用此方法
     *
     * @param success
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static R customR(boolean success, int code, String msg, String data) {
        R R = new R();
        R.setSuccess(success);
        R.setCode(code);
        R.setMsg(msg);
        R.setData(data);
        return R;
    }

    /**
     * 参数为空或者参数格式错误
     *
     * @return
     */
    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        r.setMsg("服务器内部错误");
        return r;
    }

    public static R error(String msg) {
        R r = new R();
        r.setSuccess(false);
        r.setCode(HttpStatus.BAD_REQUEST.value());
        r.setMsg(msg);
        return r;
    }

    public static R error(Integer code, String msg) {
        R r = new R();
        r.setSuccess(false);
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    /**
     * 成功
     *
     * @param data
     * @return
     */
    public static R ok(Object data) {
        R R = new R();
        R.setSuccess(true);
        R.setCode(HttpStatus.OK.value());
        R.setMsg(HttpStatus.OK.getReasonPhrase());
        R.setData(data);
        return R;
    }

    public static R ok() {
        R R = new R();
        R.setSuccess(true);
        R.setCode(HttpStatus.OK.value());
        R.setMsg(HttpStatus.OK.getReasonPhrase());
        return R;
    }

}
