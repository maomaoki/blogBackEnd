package com.ym.blogBackEnd.api.model.news.dto;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 * 新闻 请求 类
 */
@Data
public class NewsDto {
    /**
     * 请求 某个 类型新闻需要 id
     */
    private String id;
    /**
     * 一次性多少条新闻
     */
    private Integer size;
}
