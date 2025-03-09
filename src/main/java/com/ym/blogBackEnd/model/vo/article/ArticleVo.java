package com.ym.blogBackEnd.model.vo.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 云猫
 * @date 2025/3/8 21:29
 * @description ArticleVo 文章 脱敏类
 */
@Data
public class ArticleVo implements Serializable {

    /**
     * 文章id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 文章背景图
     */
    private String articleBgImage;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 文章主题(简介)
     */
    private String articleIntroduction;

    /**
     * 文章内容
     */
    private String articleContent;

    /**
     * 文章标签 （JSON 数组）
     */
    private String articleTags;

    /**
     * 文章分类
     */
    private String articleCategory;

    /**
     * 文章点赞数
     */
    private Integer articleLikeNumber;

    /**
     * 文章收藏数
     */
    private Integer articleCollectNumber;

    /**
     * 文章观看数
     */
    private Integer articleLookNumber;

    /**
     * 文章评论数
     */
    private Integer articleCommentNumber;

    /**
     * 文章作者
     */
    private String articleAuthor;

    /**
     * 文章是否加密 0不加密(默认),1加密
     */
    private Integer isEncrypt;


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
     * 创建时间
     */
    private Date createTime;

    /**
     * 编辑时间
     */
    private Date editTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
