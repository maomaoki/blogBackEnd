package com.ym.blogBackEnd.utils;

import com.ym.blogBackEnd.enums.ErrorEnums;
import com.ym.blogBackEnd.exception.BusinessException;

/**
 * @Title: ThrowUtils
 * @Author YunMao
 * @Package com.ym.blogBackEnd.utils
 * @Date 2025/2/2 21:48
 * @description: 手动抛异常工具类
 */
public class ThrowUtils {

    /**
     * 手动抛异常
     *
     * @param op        操作 如果 为true 就会抛异常
     * @param exception 运行异常
     */
    public static void ifThrow(boolean op, RuntimeException exception) {
        if (op) {
            throw exception;
        }
    }


    /**
     * 手动抛异常
     *
     * @param op    操作 如果 为true 就会抛异常
     * @param enums 错误枚举
     */
    public static void ifThrow(boolean op, ErrorEnums enums) {
        ifThrow(op, new BusinessException(enums));
    }

    /**
     * 手动抛异常
     *
     * @param op    操作 如果 为true 就会抛异常
     * @param enums 错误枚举
     * @param msg   错误信息
     */
    public static void ifThrow(boolean op, ErrorEnums enums, String msg) {
        ifThrow(op, new BusinessException(enums, msg));
    }

}
