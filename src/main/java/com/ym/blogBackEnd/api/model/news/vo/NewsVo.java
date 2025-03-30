package com.ym.blogBackEnd.api.model.news.vo;

import lombok.Data;

/**
 * 新闻 返回 vo
 */
@Data
public class NewsVo {
    /**
     * 序号
     */
    private Long index;

    /**
     * 新闻 标题
     */
    private String title;

    /**
     * 新闻 热度
     */
    private String hotValue;

    /**
     * 新闻 链接
     */
    private String link;

}
