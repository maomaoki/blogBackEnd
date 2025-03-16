package com.ym.blogBackEnd.model.dto.article.admin;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ym.blogBackEnd.common.request.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: AdminPageArticleDto
 * @Author YunMao
 * @Package com.ym.blogBackEnd.model.dto.article.admin
 * @Date 2025/2/11 17:27
 * @description: 管理员分页条件查询文章请求类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AdminPageArticleDto extends PageRequest implements Serializable {
    /**
     * 文章id
     */
    private Long id;

    /**
     * 文章标题
     */
    private String articleTitle;


    /**
     * 文章条件 可以搜索简介或者文章内容
     */
    private String articleCondition;


    /**
     * 文章标签 （JSON 数组）
     */
    private String articleTags;

    /**
     * 文章分类
     */
    private String articleCategory;


    /**
     * 文章作者
     */
    private String articleAuthor;


    /**
     * 文章是否推荐 0不推荐(默认),1推荐
     */
    private Integer isRecommend;

    /**
     * 文章是否热门 0不推荐(默认),1推荐
     */
    private Integer isHot;

    /**
     * 文章状态 0草稿 1未发布 2发布
     */
    private Integer articleStatus;


    /**
     * 文章 字数
     */
    private Integer articleSize;

    /**
     * 创建 开始时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createStartTime;

    /**
     * 创建 结束时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createEndTime;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
