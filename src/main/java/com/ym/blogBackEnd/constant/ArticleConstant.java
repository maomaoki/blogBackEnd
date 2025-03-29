package com.ym.blogBackEnd.constant;

/**
 * @Title: ArticleConstant
 * @Author YunMao
 * @Package com.ym.blogBackEnd.constant
 * @Date 2025/2/11 21:10
 * @description: 文章常量
 */
public interface ArticleConstant {

    /**
     * 是否加密 - 加密
     */
    Integer IS_ENCRYPT = 1;

    /**
     * 是否加密 - 不加密
     */
    Integer IS_NOT_ENCRYPT = 0;

    /**
     * 文章状态 - 草稿
     */
    Integer ARTICLE_STATUS_DRAFT = 0;

    /**
     * 文章状态 - 未发布
     */
    Integer ARTICLE_STATUS_NOT_PUBLISH = 1;

    /**
     * 文章状态 - 发布
     */
    Integer ARTICLE_STATUS_PUBLISH = 2;
}
