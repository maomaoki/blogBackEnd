package com.ym.blogBackEnd.model.dto.articleCollect;

import com.ym.blogBackEnd.common.request.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询 收藏 文章 分页 dto
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CollectArticlePageDto extends PageRequest {

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 文章标签 （JSON 数组）
     */
    private String articleTags;

    /**
     * 文章分类
     */
    private String articleCategory;


}
