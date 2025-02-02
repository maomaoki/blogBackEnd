package com.ym.blogBackEnd.exception;

import com.ym.blogBackEnd.enums.ErrorEnums;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Title: BusinessException
 * @Author YunMao
 * @Package com.ym.blogBackEnd.exception
 * @Date 2025/2/2 21:45
 * @description: 业务自定义异常类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException{

    /**
     *  业务异常码
     */
    private int code;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ErrorEnums enums) {
        super(enums.getMsg());
        this.code = enums.getCode();
    }

    public BusinessException(ErrorEnums enums,String msg) {
        super(msg);
        this.code = enums.getCode();
    }
}
