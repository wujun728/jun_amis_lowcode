package cn.kiwipeach.exeception;

import cn.kiwipeach.enums.ResponseCodeEnum;

/**
 * Create Date: 2017/11/06
 * Description: 业务异常
 *
 * @author kiwipeach [1099501218@qq.com]
 */
public class BusinessException extends BaseException{


    public BusinessException(String defaultMessage) {
        super(defaultMessage);
    }

    public BusinessException(ResponseCodeEnum codeEnum) {
        super(codeEnum.getCode(), codeEnum.getMessage());
    }
}
