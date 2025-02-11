package com.ym.blogBackEnd.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ym.blogBackEnd.model.domain.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ym.blogBackEnd.model.dto.article.admin.AdminAddArticleDto;
import com.ym.blogBackEnd.model.dto.article.admin.AdminDeleteArticleDto;
import com.ym.blogBackEnd.model.dto.article.admin.AdminEditArticleDto;
import com.ym.blogBackEnd.model.dto.article.admin.AdminPageArticleDto;
import com.ym.blogBackEnd.model.vo.article.ArticleVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 54621
 * @description 针对表【article(文章表)】的数据库操作Service
 * @createDate 2025-02-11 16:44:06
 */
public interface ArticleService extends IService<Article> {

    /**
     * 管理员添加文章
     *
     * @param adminAddArticleDto 管理员添加文章请求类
     * @param request            请求
     * @return 文章id
     */
    Long adminAddArticle(AdminAddArticleDto adminAddArticleDto, HttpServletRequest request);


    /**
     * 管理员删除文章
     *
     * @param adminDeleteArticleDto 管理员删除文章请求类
     * @return 是否删除成功
     */
    boolean adminDeleteArticle(AdminDeleteArticleDto adminDeleteArticleDto);


    /**
     * 管理员编辑文章
     *
     * @param adminEditArticleDto 管理员编辑文章请求类
     * @return 文章id
     */
    Long adminEditArticle(AdminEditArticleDto adminEditArticleDto);


    /**
     * 管理员 根据 id 获取编辑文章
     * @param id 文章 id
     * @return 文章
     */
    Article adminGetEditArticleById(Long id);


    /**
     * 根据文章id获取文章
     * @param id 文章id
     * @return 文章
     */
    ArticleVo getByArticleId(Long id);


    /**
     * 根据文章id和密码获取文章
     * @param id 文章 id
     * @param password 加密 密码
     * @return 文章
     */
    ArticleVo getByArticleIdAndPassword(Long id,String password);





    /**
     * 管理员分页查询文章
     *
     * @param adminPageArticleDto 管理员分页查询文章请求类
     * @return 文章分页
     */
    Page<ArticleVo> adminPageArticle(AdminPageArticleDto adminPageArticleDto);


    /**
     * 文章转 vo
     *
     * @param article 文章类
     * @return 文章vo
     */
    ArticleVo articleToVo(Article article);


    /**
     * 文章列表转 vo
     *
     * @param articles 文章列表
     * @return 文章vo列表
     */
    List<ArticleVo> articleListToVos(List<Article> articles);
}
