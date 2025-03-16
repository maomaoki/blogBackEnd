package com.ym.blogBackEnd.model.vo.article;

import lombok.Data;

/**
 * @author 云猫
 * @date 2025/3/16 21:59
 * @description ArticleInfoCountVo 文章 信息 统计 返回 vo
 */
@Data
public class ArticleInfoCountVo {
    private Long articleCount;
    private String articleWordCount;
}
