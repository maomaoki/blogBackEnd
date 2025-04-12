package com.ym.blogBackEnd.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ym.blogBackEnd.model.domain.ArticleCollect;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ym.blogBackEnd.model.dto.articleCollect.CollectArticleDeleteDto;
import com.ym.blogBackEnd.model.dto.articleCollect.CollectArticleDto;
import com.ym.blogBackEnd.model.dto.articleCollect.CollectArticlePageDto;
import com.ym.blogBackEnd.model.vo.articleCollect.CollectArticleVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 54621
 * @description 针对表【article_collect(文章收藏表)】的数据库操作Service
 * @createDate 2025-04-06 20:23:56
 */
public interface ArticleCollectService extends IService<ArticleCollect> {

    /**
     * 收藏 文章
     *
     * @param collectArticleDto
     * @param request           请求
     * @return
     */
    Boolean collectArticle(CollectArticleDto collectArticleDto, HttpServletRequest request);


    /**
     * 分页 查询 收藏 文章
     *
     * @param collectArticlePageDto
     * @param request
     * @return
     */
    Page<CollectArticleVo> collectArticlePage(CollectArticlePageDto collectArticlePageDto, HttpServletRequest request);

    /**
     * 删除 记录
     *
     * @param collectArticleDeleteDto
     * @param request
     */
    void collectArticleDelete(CollectArticleDeleteDto collectArticleDeleteDto, HttpServletRequest request);

    /**
     * 根据 文章id 和 用户 id 查询 是否 收藏了 此 文章
     *
     * @param articleId
     * @param request
     * @return
     */
    Boolean getIsCollectArticleByArticleIdAndUserId(Long articleId, HttpServletRequest request);
}
