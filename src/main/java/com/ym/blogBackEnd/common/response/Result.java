package com.ym.blogBackEnd.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Title: Result
 * @Author YunMao
 * @Package com.ym.blogBackEnd.common.Response
 * @Date 2025/2/2 16:53
 * @description: result风格响应类
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private int code;

    /**
     *  返回数据
     */
    private T data;

    /**
     *  返回提示语
     */
    private String msg;

}
