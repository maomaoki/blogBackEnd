package com.ym.blogBackEnd.model.vo.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 云猫
 * @date 2025/3/16 21:43
 * @description ArticleTimeCountVo 文章 时间 统计 返回 vo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleTimeCountVo {
    private String time;
    private Long count;
}
