package com.jun.biz.common.base.vo;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

import com.jun.biz.common.base.enums.VoCodeEnum;

/**
 * 封装统一的响应数据VO
 *
 * 
 */
@NoArgsConstructor
@Data
public class ResultVO<T> implements Serializable {


    private Integer code;
    private String msg;
    private T data;



    public ResultVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVO(VoCodeEnum resultStatus, T data) {
        this.code = resultStatus.getCode();
        this.msg = resultStatus.getMsg();
        this.data = data;
    }

    public static <T> ResultVO<T> buildResult(VoCodeEnum resultStatus) {
        return new ResultVO<>(resultStatus, null);
    }


    public static <T> ResultVO<T> buildResult(VoCodeEnum resultStatus, T data) {
        return new ResultVO<>(resultStatus, data);
    }

    public static ResultVO<?> buildResult(int code, String msg) {
        return new ResultVO<>(code, msg);
    }

    public static <T> ResultVO<T> buildFailResult() {
        return new ResultVO<>(VoCodeEnum.FAIL, null);
    }

    public static <T> ResultVO<T> buildFailResult(T data) {
        return new ResultVO<>(VoCodeEnum.FAIL, data);
    }


    public static <T> ResultVO<T> buildFailResult(String msg) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(VoCodeEnum.OTHER.getCode());
        resultVO.setMsg(msg);
        return resultVO;
    }


    public static <T> ResultVO<T> buildSuccessResult() {
        return new ResultVO<>(VoCodeEnum.SUCCESS, null);
    }

    public boolean isSuccess() {
        return Objects.equals(VoCodeEnum.SUCCESS.getCode(), code);
    }

    public static <T> ResultVO<T> buildSuccessResult(T data) {
        return new ResultVO<>(VoCodeEnum.SUCCESS, data);
    }

    public static <T> ResultVO<T> buildSuccessResult(String msg) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(VoCodeEnum.SUCCESS.getCode());
        resultVO.setMsg(msg);
        return resultVO;
    }
}
