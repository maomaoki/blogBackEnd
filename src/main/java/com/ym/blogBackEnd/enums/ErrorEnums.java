package com.ym.blogBackEnd.enums;

import lombok.Getter;

/**
 * @Title: ErrorEnums
 * @Author YunMao
 * @Package com.ym.blogBackEnd.enums
 * @Date 2025/2/2 17:05
 * @description: 错误码枚举
 */
@Getter
public enum ErrorEnums {

    SUCCESS(0, "OK"),
    ERROR(1, "ERROR"),
    PARAMS_ERROR(40000,"参数错误"),
    NOT_AUTH(40100,"没有权限"),
    NOT_FOUND_ERROR(40400,"请求资源不存在"),
    OP_ERROR(50000,"操作失败"),
    ;
    /**
     * 响应码
     */
    private int code;

    /**
     * 响应信息
     */
    private String msg;

    ErrorEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
