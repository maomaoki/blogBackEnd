package com.ym.blogBackEnd.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ym.blogBackEnd.annotate.CheckAuth;
import com.ym.blogBackEnd.common.response.Result;
import com.ym.blogBackEnd.constant.UserConstant;
import com.ym.blogBackEnd.model.dto.article.admin.AdminAddArticleDto;
import com.ym.blogBackEnd.model.dto.articleCollect.CollectArticleDeleteDto;
import com.ym.blogBackEnd.model.dto.articleCollect.CollectArticleDto;
import com.ym.blogBackEnd.model.dto.articleCollect.CollectArticlePageDto;
import com.ym.blogBackEnd.model.vo.articleCollect.CollectArticleVo;
import com.ym.blogBackEnd.service.ArticleCollectService;
import com.ym.blogBackEnd.utils.ResUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/articleCollect")
@RestController
@Slf4j
public class ArticleCollectController {

    @Resource
    private ArticleCollectService articleCollectService;


    @PostMapping("collectArticle")
    public Result<Boolean> collectArticle(@RequestBody CollectArticleDto collectArticleDto, HttpServletRequest request) {
        Boolean result = articleCollectService.collectArticle(collectArticleDto, request);
        return ResUtils.success(result, "收藏成功");
    }


    @PostMapping("/page")
    public Result<Page<CollectArticleVo>> collectArticlePage(@RequestBody CollectArticlePageDto collectArticlePageDto, HttpServletRequest request) {

        Page<CollectArticleVo> collectArticleVos = articleCollectService.collectArticlePage(collectArticlePageDto, request);

        return ResUtils.success(collectArticleVos, "查询成功!");
    }


    @PostMapping("/delete")
    public Result<Boolean> deleteCollectArticle(@RequestBody CollectArticleDeleteDto collectArticleDeleteDto, HttpServletRequest request) {
        articleCollectService.collectArticleDelete(collectArticleDeleteDto, request);
        return ResUtils.success(true, "删除成功!");
    }


    @GetMapping("/get/{articleId}")
    public Result<Boolean> getIsCollectArticleByArticleIdAndUserId(@PathVariable Long articleId, HttpServletRequest request) {
        Boolean isCollect = articleCollectService.getIsCollectArticleByArticleIdAndUserId(articleId, request);
        return ResUtils.success(isCollect, "查询成功");
    }
}