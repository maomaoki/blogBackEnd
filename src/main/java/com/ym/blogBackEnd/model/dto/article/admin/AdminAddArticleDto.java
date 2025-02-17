package com.ym.blogBackEnd.model.dto.article.admin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: AdminAddArticle
 * @Author YunMao
 * @Package com.ym.blogBackEnd.model.dto.article.admin
 * @Date 2025/2/11 17:23
 * @description: 管理员添加文章 请求类
 */
@Data
public class AdminAddArticleDto implements Serializable {

    /**
     * 文章背景图
     */
    private String articleBgImage;

    /**
     *  背景图 id (用于修改图片用处)
     */
    private Long imageId;

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
     * 文章是否加密 0不加密(默认),1加密
     */
    private Integer isEncrypt;

    /**
     * 文章加密密码
     */
    private String encryptPassword;

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


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
