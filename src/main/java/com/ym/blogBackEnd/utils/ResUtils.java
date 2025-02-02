package com.ym.blogBackEnd.utils;


import com.ym.blogBackEnd.common.response.Result;
import com.ym.blogBackEnd.enums.ErrorEnums;

/**
 * @Title: ResUtils
 * @Author YunMao
 * @Package com.ym.blogBackEnd.utils
 * @Date 2025/2/2 16:57
 * @description: 响应工具类
 */
public class ResUtils {

    /**
     * 成功返回
     *
     * @param data 数据
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ErrorEnums.SUCCESS.getCode(), data, ErrorEnums.SUCCESS.getMsg());
    }


    /**
     * 成功返回
     *
     * @param data 数据
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data, String msg) {
        return new Result<>(ErrorEnums.SUCCESS.getCode(), data, msg);
    }


    /**
     * 错误 返回
     *
     * @param code
     * @param msg
     * @return
     */
    public static Result<?> error(int code, String msg) {
        return new Result<>(code, null, msg);
    }


    /**
     * 错误返回
     *
     * @param enums
     * @return
     */
    public static Result<?> error(ErrorEnums enums) {
        return new Result<>(enums.getCode(), null, enums.getMsg());
    }


    /**
     * 错误返回
     *
     * @param enums
     * @return
     */
    public static Result<?> error(ErrorEnums enums, String msg) {
        return new Result<>(enums.getCode(), null, msg);
    }


}
