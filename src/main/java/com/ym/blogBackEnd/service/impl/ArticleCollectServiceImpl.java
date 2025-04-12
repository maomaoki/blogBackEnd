package com.ym.blogBackEnd.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ym.blogBackEnd.enums.ErrorEnums;
import com.ym.blogBackEnd.model.domain.Article;
import com.ym.blogBackEnd.model.domain.ArticleCollect;
import com.ym.blogBackEnd.model.dto.articleCollect.CollectArticleDeleteDto;
import com.ym.blogBackEnd.model.dto.articleCollect.CollectArticleDto;
import com.ym.blogBackEnd.model.dto.articleCollect.CollectArticlePageDto;
import com.ym.blogBackEnd.model.vo.article.ArticlePageVo;
import com.ym.blogBackEnd.model.vo.articleCollect.CollectArticleVo;
import com.ym.blogBackEnd.model.vo.user.UserVo;
import com.ym.blogBackEnd.service.ArticleCollectService;
import com.ym.blogBackEnd.mapper.ArticleCollectMapper;
import com.ym.blogBackEnd.service.ArticleService;
import com.ym.blogBackEnd.service.UserService;
import com.ym.blogBackEnd.utils.ThrowUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 54621
 * @description 针对表【article_collect(文章收藏表)】的数据库操作Service实现
 * @createDate 2025-04-06 20:23:56
 */
@Service
public class ArticleCollectServiceImpl extends ServiceImpl<ArticleCollectMapper, ArticleCollect> implements ArticleCollectService {

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
        ThrowUtils.ifThrow(ObjUtil.isEmpty(collectArticleDto) || ObjUtil.isEmpty(articleId), ErrorEnums.PARAMS_ERROR, "请求信息不能为空");

        // 1. 必须登录
        UserVo userVo = userService.userGetLoginInfo(request);


        // 2. 查询 是否 存在 此 文章
        Article article = articleService.getPublishArticleById(articleId);
        ThrowUtils.ifThrow(ObjUtil.isEmpty(article), ErrorEnums.NOT_FOUND_ERROR, "查无此文章");


        // 3. 查询 此用户 是否 收藏过 此文章
        QueryWrapper<ArticleCollect> articleCollectQueryWrapper = new QueryWrapper<>();
        articleCollectQueryWrapper.eq("articleId", articleId);
        articleCollectQueryWrapper.eq("userId", userVo.getId());
        ArticleCollect articleCollect = this.getOne(articleCollectQueryWrapper);
        ThrowUtils.ifThrow(ObjUtil.isNotEmpty(articleCollect), ErrorEnums.OP_ERROR, "已收藏此文章,请不要重复收藏");

        // 4. 封装信息
        articleCollect = new ArticleCollect();
        articleCollect.setArticleId(articleId);
        articleCollect.setUserId(userVo.getId());

        // 5. 保存信息
        return this.save(articleCollect);
    }

    /**
     * 分页 查询 收藏 文章
     *
     * @param collectArticlePageDto
     * @param request
     * @return
     */
    @Override
    public Page<CollectArticleVo> collectArticlePage(CollectArticlePageDto collectArticlePageDto, HttpServletRequest request) {
        Integer pageNum = collectArticlePageDto.getPageNum();
        Integer pageSize = collectArticlePageDto.getPageSize();

        // 1. 必须登录
        UserVo userVo = userService.userGetLoginInfo(request);

        // 2. 根据 用户id 去查询 绑定文章（分页查询）
        Long userId = userVo.getId();
        QueryWrapper<ArticleCollect> articleCollectQueryWrapper = new QueryWrapper<>();
        articleCollectQueryWrapper.eq("userId", userId);
        Page<ArticleCollect> page = this.page(new Page<>(pageNum, pageSize), articleCollectQueryWrapper);
        List<ArticleCollect> articleCollectList = page.getRecords();
        if (ObjUtil.isEmpty(articleCollectList)) {
            return new Page<CollectArticleVo>(pageNum, pageSize, articleCollectList.size());
        }


        List<CollectArticleVo> collectArticleVos = BeanUtil.copyToList(articleCollectList, CollectArticleVo.class);
        // 进行脱敏 加 更改 文章数据
        List<Long> ids = articleCollectList.stream().map(ArticleCollect::getArticleId).toList();
        List<ArticlePageVo> articlePageVos = articleService.articleByIdsAndWrapper(ids, collectArticlePageDto);
        // 创建一个map
        HashMap<Long, ArticlePageVo> longArticlePageVoHashMap = new HashMap<>();
        articlePageVos.forEach(e -> longArticlePageVoHashMap.put(e.getId(), e));

        collectArticleVos.forEach(e -> {
            e.setArticlePageVo(longArticlePageVoHashMap.get(e.getArticleId()));
        });

        collectArticleVos = collectArticleVos.stream().filter(e -> ObjUtil.isNotEmpty(e.getArticlePageVo())).toList();

        Page<CollectArticleVo> collectArticleVoPage = new Page<>(pageNum, pageSize, collectArticleVos.size());
        collectArticleVoPage.setRecords(collectArticleVos);

        return collectArticleVoPage;
    }


    /**
     * 删除 记录
     *
     * @param collectArticleDeleteDto
     * @param request
     */
    @Override
    public void collectArticleDelete(CollectArticleDeleteDto collectArticleDeleteDto, HttpServletRequest request) {
        Long collectArticleDeleteDtoId = collectArticleDeleteDto.getArticleId();
        ThrowUtils.ifThrow(
                ObjUtil.isEmpty(collectArticleDeleteDtoId),
                ErrorEnums.PARAMS_ERROR,
                "记录id不能为空"
        );

        // 必须 登录
        UserVo userVo = userService.userGetLoginInfo(request);
        QueryWrapper<ArticleCollect> articleCollectQueryWrapper = new QueryWrapper<>();
        articleCollectQueryWrapper.eq("articleId", collectArticleDeleteDtoId);
        articleCollectQueryWrapper.eq("userId", userVo.getId());

        // 才能删除
        this.baseMapper.delete(articleCollectQueryWrapper);
    }


    /**
     * 根据 文章id 和 用户 id 查询 是否 收藏了 此 文章
     *
     * @param articleId
     * @param request
     * @return
     */
    @Override
    public Boolean getIsCollectArticleByArticleIdAndUserId(Long articleId, HttpServletRequest request) {
        // 1. 参数 问题
        ThrowUtils
                .ifThrow(ObjUtil.isEmpty(articleId), ErrorEnums.PARAMS_ERROR, "文章id不能为空");

        // 2. 登录
        UserVo userVo = userService.userGetLoginInfo(request);

        // 3. 根据 用户id 和 文章 id 查询 是否 存在 此 记录
        QueryWrapper<ArticleCollect> articleCollectQueryWrapper = new QueryWrapper<>();
        articleCollectQueryWrapper.eq("articleId", articleId);
        articleCollectQueryWrapper.eq("userId", userVo.getId());
        ArticleCollect articleCollect = this.getOne(articleCollectQueryWrapper);
        // 4. 返回结果
        return ObjUtil.isNotEmpty(articleCollect);
    }
}




