package com.ym.blogBackEnd.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ym.blogBackEnd.enums.ErrorEnums;
import com.ym.blogBackEnd.model.domain.Article;
import com.ym.blogBackEnd.model.domain.ArticleCollect;
import com.ym.blogBackEnd.model.dto.articleCollect.CollectArticleDto;
import com.ym.blogBackEnd.model.vo.user.UserVo;
import com.ym.blogBackEnd.service.ArticleCollectService;
import com.ym.blogBackEnd.mapper.ArticleCollectMapper;
import com.ym.blogBackEnd.service.ArticleService;
import com.ym.blogBackEnd.service.UserService;
import com.ym.blogBackEnd.utils.ThrowUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 54621
 * @description 针对表【article_collect(文章收藏表)】的数据库操作Service实现
 * @createDate 2025-04-06 20:23:56
 */
@Service
public class ArticleCollectServiceImpl extends ServiceImpl<ArticleCollectMapper, ArticleCollect>
        implements ArticleCollectService {

    @Resource
    private UserService userService;

    @Resource
    private ArticleService articleService;

    /**
     * 收藏 文章
     *
     * @param collectArticleDto
     * @param request           请求
     * @return
     */
    @Override
    public Boolean collectArticle(CollectArticleDto collectArticleDto, HttpServletRequest request) {

        Long articleId = collectArticleDto.getArticleId();
        ThrowUtils.ifThrow(
                ObjUtil.isEmpty(
                        collectArticleDto
                ) || ObjUtil.isEmpty(articleId),
                ErrorEnums.PARAMS_ERROR,
                "请求信息不能为空"
        );

        // 1. 必须登录
        UserVo userVo = userService.userGetLoginInfo(request);


        // 2. 查询 是否 存在 此 文章
        Article article = articleService.getPublishArticleById(articleId);
        ThrowUtils.ifThrow(
                ObjUtil.isEmpty(article),
                ErrorEnums.NOT_FOUND_ERROR,
                "查无此文章"
        );


        // 3. 查询 此用户 是否 收藏过 此文章
        QueryWrapper<ArticleCollect> articleCollectQueryWrapper = new QueryWrapper<>();
        articleCollectQueryWrapper.eq("articleId", articleId);
        articleCollectQueryWrapper.eq("userId", userVo.getId());
        ArticleCollect articleCollect = this.getOne(articleCollectQueryWrapper);
        ThrowUtils
                .ifThrow(ObjUtil.isNotEmpty(articleCollect), ErrorEnums.OP_ERROR, "已收藏此文章,请不要重复收藏");

        // 4. 封装信息
        articleCollect = new ArticleCollect();
        articleCollect.setArticleId(articleId);
        articleCollect.setUserId(userVo.getId());

        // 5. 保存信息
        return this.save(articleCollect);
    }
}




