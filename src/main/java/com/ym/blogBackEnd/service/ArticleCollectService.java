package com.ym.blogBackEnd.service;

import com.ym.blogBackEnd.model.domain.ArticleCollect;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ym.blogBackEnd.model.dto.articleCollect.CollectArticleDto;

import javax.servlet.http.HttpServletRequest;

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



}
