package com.ym.blogBackEnd.controller;


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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result<List<CollectArticleVo>> collectArticlePage(@RequestBody CollectArticlePageDto collectArticlePageDto, HttpServletRequest request) {

        List<CollectArticleVo> collectArticleVos = articleCollectService.collectArticlePage(collectArticlePageDto, request);

        return ResUtils.success(collectArticleVos, "查询成功!");
    }


    @PostMapping("/delete")
    public Result<Boolean> deleteCollectArticle(@RequestBody CollectArticleDeleteDto collectArticleDeleteDto, HttpServletRequest request) {
        articleCollectService.collectArticleDelete(collectArticleDeleteDto, request);
        return ResUtils.success(true, "删除成功!");
    }

}