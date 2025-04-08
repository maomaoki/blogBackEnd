package com.ym.blogBackEnd.model.vo.articleCollect;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.ym.blogBackEnd.model.vo.article.ArticlePageVo;
import lombok.Data;

import java.util.Date;

/**
 * 用户 查看 自己 收藏 文章列表信息
 */
@Data
public class CollectArticleVo {
    /**
     * 默认记录id
     */
    private Long id;


    /**
     * 文章id 用来关联查询
     */
    private Long articleId;

    /**
     * 文章 脱敏数据
     */
    private ArticlePageVo articlePageVo;


    /**
     * 创建时间
     */
    private Date createTime;

}
