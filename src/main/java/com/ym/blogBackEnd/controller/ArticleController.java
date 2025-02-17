package com.ym.blogBackEnd.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ym.blogBackEnd.annotate.CheckAuth;
import com.ym.blogBackEnd.common.response.Result;
import com.ym.blogBackEnd.constant.UserConstant;
import com.ym.blogBackEnd.model.domain.Article;
import com.ym.blogBackEnd.model.dto.article.GetArticleByPasswordDto;
import com.ym.blogBackEnd.model.dto.article.admin.AdminAddArticleDto;
import com.ym.blogBackEnd.model.dto.article.admin.AdminDeleteArticleDto;
import com.ym.blogBackEnd.model.dto.article.admin.AdminEditArticleDto;
import com.ym.blogBackEnd.model.dto.article.admin.AdminPageArticleDto;
import com.ym.blogBackEnd.model.vo.article.ArticleVo;
import com.ym.blogBackEnd.service.ArticleService;
import com.ym.blogBackEnd.utils.ResUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Title: ArticleController
 * @Author YunMao
 * @Package com.ym.blogBackEnd.controller
 * @Date 2025/2/11 18:26
 * @description: 文章接口
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;


    @PostMapping("admin/add")
    @CheckAuth(mustRole = UserConstant.USER_ROLE_BOSS)
    public Result<Long> adminAddArticle(@RequestBody AdminAddArticleDto adminAddArticleDto, HttpServletRequest request) {
        Long articleId = articleService.adminAddArticle(adminAddArticleDto, request);
        return ResUtils.success(articleId, "添加成功");
    }

    @PostMapping("admin/delete")
    @CheckAuth(mustRole = UserConstant.USER_ROLE_ADMIN)
    public Result<Boolean> adminDeleteArticle(@RequestBody AdminDeleteArticleDto adminDeleteArticleDto) {
        boolean result = articleService.adminDeleteArticle(adminDeleteArticleDto);
        return ResUtils.success(result, "删除成功");
    }


    @PostMapping("admin/edit")
    @CheckAuth(mustRole = UserConstant.USER_ROLE_ADMIN)
    public Result<Long> adminEditArticle(@RequestBody AdminEditArticleDto adminEditArticleDto,HttpServletRequest request) {
        Long articleId = articleService.adminEditArticle(adminEditArticleDto,request);
        return ResUtils.success(articleId, "编辑成功");
    }


    @PostMapping("admin/page")
    @CheckAuth(mustRole = UserConstant.USER_ROLE_ADMIN)
    public Result<Page<ArticleVo>> adminPageArticle(@RequestBody AdminPageArticleDto adminPageArticleDto) {
        Page<ArticleVo> articleVoPage = articleService.adminPageArticle(adminPageArticleDto);
        return ResUtils.success(articleVoPage, "查询成功");
    }


    @GetMapping("admin/edit/{id}")
    public Result<Article> adminGetEditArticleById(@PathVariable String id) {
        Article article = articleService.adminGetEditArticleById(Long.valueOf(id));
        return ResUtils.success(article, "查询成功");
    }


    @GetMapping("get/{id}")
    public Result<ArticleVo> getArticleById(@PathVariable String id) {
        ArticleVo articleVo = articleService.getByArticleId(Long.valueOf(id));
        return ResUtils.success(articleVo, "查询成功");
    }

    @PostMapping("get/encrypt")
    public Result<ArticleVo> getArticleByIdAndPassword(@RequestBody GetArticleByPasswordDto getArticleByPasswordDto) {
        ArticleVo articleVo = articleService.getByArticleIdAndPassword(getArticleByPasswordDto.getId(), getArticleByPasswordDto.getPassword());
        return ResUtils.success(articleVo, "查询成功");
    }
}
