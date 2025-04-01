package com.ym.blogBackEnd.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 博客系统表
 *
 * @TableName blog_system
 */
@TableName(value = "blog_system")
@Data
public class BlogSystem implements Serializable {
    /**
     * 主键id，唯一标识每条记录
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * home banner 背景图的URL地址
     */
    private String homeBannerBgImageUrl;

    /**
     * home banner 打印文字，以JSON列表格式存储
     */
    private String homeBannerPrintText;

    /**
     * home banner 的标题
     */
    private String homeBannerTitle;


    /**
     * 新闻 banner 背景 图片 url
     */
    private String newsBannerBgImageUrl;

    /**
     * 新闻 banner 背景 title
     */
    private String newsBannerTitle;

    /**
     * 新闻 banner 打印文字
     */
    private String newsBannerPrintText;

    /**
     * 随便看看 幻灯片 文字内容
     */
    private String slideWrapTitle;

    /**
     * 随便看看 幻灯片 url(其实也是我的网址)
     */
    private String slideWrapUrl;

    /**
     * 更多 背景图
     */
    private String moreImageUrl;

    /**
     * 更多 标题
     */
    private String moreTitle;

    /**
     * 更多 内容
     */
    private String moreContent;

    /**
     * 登录 背景图
     */
    private String loginImageUrl;

    /**
     * 名片 头像
     */
    private String businessCardAvatarUrl;

    /**
     * 名片顶部展示的文字
     */
    private String businessCardTop;

    /**
     * 名片展示文字，以JSON列表格式存储，html文档 支持<b></b>标签高亮
     */
    private String businessCardText;

    /**
     * 名片的标题
     */
    private String businessCardTitle;

    /**
     * 名片的名称
     */
    private String businessCardName;

    /**
     * 名片关联的B站分享地址
     */
    private String businessCardBiliUrl;

    /**
     * 名片关联的GitHub分享地址
     */
    private String businessCardGitHubUrl;

    /**
     * B站分享地址
     */
    private String shareBiliUrl;

    /**
     * GitHub分享地址
     */
    private String shareGitHubUrl;

    /**
     * Gitee分享地址
     */
    private String shareGiteeUrl;

    /**
     * Email分享地址
     */
    private String shareEmailUrl;

    /**
     * QQ分享地址
     */
    private String shareQQUrl;

    /**
     * 微信分享地址
     */
    private String shareWxUrl;

    /**
     * 抖音分享地址
     */
    private String shareDyUrl;

    /**
     * 博客系统管理员的名称
     */
    private String blogSystemUserName;

    /**
     * 博客的创建时间
     */
    private Date blogCreateTime;

    /**
     * 博客的备案号
     */
    private String blogRegistrationNumber;

    /**
     * 博客备案号地址
     */
    private String blogRegistrationNumberUrl;

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

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}