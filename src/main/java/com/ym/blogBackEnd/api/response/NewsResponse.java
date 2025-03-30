package com.ym.blogBackEnd.api.response;

import lombok.Data;

/**
 * 请求 新闻 数据 返回 结构
 */
@Data
public class NewsResponse<T> {
    private Integer code;
    private T data;
    private String msg;
}
