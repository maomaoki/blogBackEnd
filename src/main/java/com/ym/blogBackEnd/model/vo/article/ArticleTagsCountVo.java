package com.ym.blogBackEnd.model.vo.article;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 云猫
 * @date 2025/3/16 21:20
 * @description ArticleTagsCountVo 文章tags统计 返回 vo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleTagsCountVo {
    private String name;
    private Integer count;
}
