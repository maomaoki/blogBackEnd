package com.ym.blogBackEnd.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ym.blogBackEnd.constant.ArticleConstant;
import com.ym.blogBackEnd.enums.ErrorEnums;
import com.ym.blogBackEnd.model.domain.Article;
import com.ym.blogBackEnd.model.dto.article.admin.AdminAddArticleDto;
import com.ym.blogBackEnd.model.dto.article.admin.AdminDeleteArticleDto;
import com.ym.blogBackEnd.model.dto.article.admin.AdminEditArticleDto;
import com.ym.blogBackEnd.model.dto.article.admin.AdminPageArticleDto;
import com.ym.blogBackEnd.model.dto.articleCollect.CollectArticlePageDto;
import com.ym.blogBackEnd.model.vo.article.*;
import com.ym.blogBackEnd.model.vo.user.UserVo;
import com.ym.blogBackEnd.service.ArticleService;
import com.ym.blogBackEnd.mapper.ArticleMapper;
import com.ym.blogBackEnd.service.PictureService;
import com.ym.blogBackEnd.service.UserService;
import com.ym.blogBackEnd.utils.ThrowUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 54621
 * @description 针对表【article(文章表)】的数据库操作Service实现
 * @createDate 2025-02-11 16:44:06
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {


    @Resource
    private UserService userService;

    @Resource
    private PictureService pictureService;


    /**
     * 管理员添加文章
     *
     * @param adminAddArticleDto 管理员添加文章请求类
     * @param request            请求
     * @return 文章id
     */
    @Override
    public Long adminAddArticle(AdminAddArticleDto adminAddArticleDto, HttpServletRequest request) {
        // 1.校验参数(文章内容不需要检查,因为有草稿状态)
        ThrowUtils.ifThrow(
                adminAddArticleDto == null,
                ErrorEnums.PARAMS_ERROR,
                "参数错误"
        );

        // 校验是否加密
        Integer isEncrypt = adminAddArticleDto.getIsEncrypt();
        String encryptPassword = adminAddArticleDto.getEncryptPassword();
        if (isEncrypt.equals(ArticleConstant.IS_ENCRYPT)) {
            // 加密需要检验是否有密码(就不设置默认密码了)
            ThrowUtils.ifThrow(StrUtil.isBlank(encryptPassword), ErrorEnums.PARAMS_ERROR, "加密文章需要设置密码");
            // 密码 需要 加密
            encryptPassword = userService.saltMd5(encryptPassword);
        }


        // 2.封装数据库
        Article article = new Article();
        BeanUtil.copyProperties(adminAddArticleDto, article);
        // 3.补充参数
        UserVo userVo = userService.userGetLoginInfo(request);
        article.setArticleAuthor(userVo.getUserName());
        article.setEncryptPassword(encryptPassword);

        if (StrUtil.isBlank(article.getArticleIntroduction())) {
            // 如果文章简介为空,则默认为 内容 前 30 字符
            article.setArticleIntroduction(StrUtil.sub(article.getArticleContent(), 0, 30));
        }

        if (!StrUtil.isBlank(article.getArticleBgImage())) {
            // 存在 修改图片用处
            pictureService.usedPicture(adminAddArticleDto.getImageId(), request);
        }
        // 4.保存到数据库
        boolean save = save(article);
        ThrowUtils.ifThrow(
                !save,
                ErrorEnums.OP_ERROR,
                "文章插入数据库错误");

        // 5.返回文章id
        return article.getId();
    }


    /**
     * 管理员删除文章
     *
     * @param adminDeleteArticleDto 管理员删除文章请求类
     * @return 是否删除成功
     */
    @Override
    public boolean adminDeleteArticle(AdminDeleteArticleDto adminDeleteArticleDto) {
        // 1.校验参数
        ThrowUtils.ifThrow(
                adminDeleteArticleDto == null || adminDeleteArticleDto.getId() == null,
                ErrorEnums.PARAMS_ERROR,
                "参数错误");

        // 2.查询是否存在
        Article article = this.getById(adminDeleteArticleDto.getId());
        ThrowUtils.ifThrow(
                article == null,
                ErrorEnums.NOT_FOUND_ERROR,
                "文章不存在");

        // 3.删除
        int result = this.baseMapper.deleteById(adminDeleteArticleDto.getId());
        return result > 0;
    }


    /**
     * 管理员编辑文章
     *
     * @param adminEditArticleDto 管理员编辑文章请求类
     * @return 文章id
     * @ request http
     */
    @Override
    public Long adminEditArticle(AdminEditArticleDto adminEditArticleDto, HttpServletRequest request) {
        // 1.校验参数
        ThrowUtils.ifThrow(
                adminEditArticleDto == null || adminEditArticleDto.getId() == null,
                ErrorEnums.PARAMS_ERROR,
                "参数错误");

        // 2.查询是否存在
        Article oldArticle = this.getById(adminEditArticleDto.getId());
        ThrowUtils.ifThrow(
                oldArticle == null,
                ErrorEnums.NOT_FOUND_ERROR,
                "文章不存在");

        // 3.更新数据库
        Article article = new Article();
        BeanUtil.copyProperties(adminEditArticleDto, article);

        // 4.补充参数
        article.setEditTime(new Date());
        UserVo userVo = userService.userGetLoginInfo(request);
        article.setArticleAuthor(userVo.getUserName());

        // 存在加密 密码就需要加密再存入
        if (ArticleConstant.IS_ENCRYPT.equals(article.getIsEncrypt())) {
            article.setEncryptPassword(userService.saltMd5(article.getEncryptPassword()));
        }

        // TODO 图片修改
        if (ObjUtil.isNotNull(adminEditArticleDto.getImageId())) {
            // 存在 修改图片用处
            pictureService.usedPicture(adminEditArticleDto.getImageId(), request);
        }

        // 5.更新数据操作
        boolean update = updateById(article);
        ThrowUtils.ifThrow(
                !update,
                ErrorEnums.OP_ERROR,
                "文章更新数据库错误");
        // 4.返回文章id
        return article.getId();
    }


    /**
     * 管理员 分页 查询文章
     *
     * @param adminPageArticleDto 管理员分页查询文章请求类
     * @return 文章分页
     */
    @Override
    public Page<ArticlePageVo> adminPageArticle(AdminPageArticleDto adminPageArticleDto) {
        // 1.校验参数
        ThrowUtils.ifThrow(
                adminPageArticleDto == null,
                ErrorEnums.PARAMS_ERROR,
                "参数错误");
        Integer pageSize = adminPageArticleDto.getPageSize();
        Integer pageNum = adminPageArticleDto.getPageNum();


        // 2.封装查询参数
        QueryWrapper<Article> queryWrapper = queryWrapper(adminPageArticleDto);

        // 3.查询数据库
        Page<Article> articlePage = this.page(new Page<>(pageNum, pageSize), queryWrapper);

        // 4.封装返回参数
        Page<ArticlePageVo> articleVoPage = new Page<>(pageNum, pageSize, articlePage.getTotal());
        articleVoPage.setRecords(articleListToPageVos(articlePage.getRecords()));
        return articleVoPage;
    }


    /**
     * 用户 查询 文章 列表
     *
     * @param adminPageArticleDto 管理员分页查询文章请求类
     * @return 文章分页
     */
    @Override
    public Page<ArticlePageVo> pageArticle(AdminPageArticleDto adminPageArticleDto) {
        // 1.校验参数
        ThrowUtils.ifThrow(
                adminPageArticleDto == null,
                ErrorEnums.PARAMS_ERROR,
                "参数错误");

        Integer pageNum = adminPageArticleDto.getPageNum();

        // 这里 需要  做 条数 限制 防止 爬虫 一次性 崩坏 服务器

        // 不是 就限制 条数(默认10条)
        // 还需要 限制 查询 只能 查询 发布的 文章
        Integer pageSize = adminPageArticleDto.getPageSize();
        if (adminPageArticleDto.getPageSize() > 10) {
            pageSize = 10;
        }

        adminPageArticleDto.setArticleStatus(ArticleConstant.ARTICLE_STATUS_PUBLISH);


        // 2.封装查询参数
        QueryWrapper<Article> queryWrapper = queryWrapper(adminPageArticleDto);

        // 3.查询数据库
        Page<Article> articlePage = this.page(new Page<>(pageNum, pageSize), queryWrapper);

        // 4.封装返回参数
        Page<ArticlePageVo> articleVoPage = new Page<>(pageNum, pageSize, articlePage.getTotal());
        articleVoPage.setRecords(articleListToPageVos(articlePage.getRecords()));
        return articleVoPage;
    }


    /**
     * 根据 id 获取编辑文章
     *
     * @param id 文章 id
     * @return 文章
     */
    @Override
    public Article adminGetEditArticleById(Long id) {
        // 1. 校验参数
        ThrowUtils.ifThrow(
                id == null,
                ErrorEnums.PARAMS_ERROR,
                "参数错误");

        // 2. 查询数据库
        Article article = this.getById(id);
        ThrowUtils.ifThrow(
                article == null,
                ErrorEnums.NOT_FOUND_ERROR,
                "文章不存在");

        // 3. 返回文章
        return article;

    }

    /**
     * 根据 文章id 获取 发布中的文章
     */
    @Override
    public Article getPublishArticleById(Long id) {
        // 1. 校验参数
        ThrowUtils.ifThrow(
                id == null,
                ErrorEnums.PARAMS_ERROR,
                "参数错误");

        // 2. 查询数据库
        Article article = this.getById(id);
        ThrowUtils.ifThrow(
                article == null,
                ErrorEnums.NOT_FOUND_ERROR,
                "文章不存在");


        // 3. 查看状态(只能发布中才能查看)
        ThrowUtils.ifThrow(
                !article.getArticleStatus().equals(ArticleConstant.ARTICLE_STATUS_PUBLISH),
                ErrorEnums.NOT_AUTH,
                "文章未发布");


        return article;
    }


    /**
     * 管理员根据文章id获取文章
     *
     * @param id 文章id
     * @return 文章
     */
    @Override
    public ArticleVo getByArticleId(Long id) {

        // 1. 校验 文章是否存在 并且 为 发布中状态
        Article article = getPublishArticleById(id);


        // 4. 查看是否为加密,如果是加密只能返回简介，内容为空
        Integer isEncrypt = article.getIsEncrypt();
        if (isEncrypt.equals(ArticleConstant.IS_ENCRYPT)) {
            // 加密只能返回简介,内容为空,后续携带密码再请求获取内容
            article.setArticleContent(null);
        }

        // 5. 返回文章
        return articleToVo(article);
    }


    /**
     * 根据文章id和密码获取文章
     *
     * @param id       文章 id
     * @param password 加密 密码
     * @return 文章
     */
    @Override
    public ArticleVo getByArticleIdAndPassword(Long id, String password) {
        // 1. 校验 文章是否存在 并且 为 发布中状态
        Article article = getPublishArticleById(id);

        String encryptPassword = article.getEncryptPassword();
        ThrowUtils.ifThrow(
                !encryptPassword.equals(userService.saltMd5(password)),
                ErrorEnums.NOT_AUTH,
                "密码错误");

        // 3.验证通过返回
        return articleToVo(article);
    }


    /**
     * 文章转 pageVo
     *
     * @param article 文章类
     * @return pageVo
     */
    @Override
    public ArticlePageVo articleToPageVo(Article article) {
        if (article == null) {
            return null;
        }
        ArticlePageVo articlePageVo = new ArticlePageVo();
        BeanUtil.copyProperties(article, articlePageVo);
        return articlePageVo;
    }

    /**
     * 文章列表转 pageVo
     *
     * @param articles 文章列表
     * @return 文章pageVo列表
     */
    @Override
    public List<ArticlePageVo> articleListToPageVos(List<Article> articles) {
        if (articles == null) {
            return new ArrayList<>();
        }
        return articles.stream().map(this::articleToPageVo).collect(Collectors.toList());
    }

    /**
     * 封装查询条件
     *
     * @param adminPageArticleDto 分页参数
     * @return 查询条件
     */
    private QueryWrapper<Article> queryWrapper(AdminPageArticleDto adminPageArticleDto) {

        ThrowUtils.ifThrow(
                adminPageArticleDto == null,
                ErrorEnums.PARAMS_ERROR,
                "请求参数不能为空"
        );

        Long id = adminPageArticleDto.getId();
        String articleTitle = adminPageArticleDto.getArticleTitle();
        String articleCondition = adminPageArticleDto.getArticleCondition();
        String articleTags = adminPageArticleDto.getArticleTags();
        String articleCategory = adminPageArticleDto.getArticleCategory();
        String articleAuthor = adminPageArticleDto.getArticleAuthor();
        Integer isRecommend = adminPageArticleDto.getIsRecommend();
        Integer articleSize = adminPageArticleDto.getArticleSize();
        Integer isHot = adminPageArticleDto.getIsHot();
        Integer articleStatus = adminPageArticleDto.getArticleStatus();
        Date createStartTime = adminPageArticleDto.getCreateStartTime();
        Date createEndTime = adminPageArticleDto.getCreateEndTime();
        String sortField = adminPageArticleDto.getSortField();
        String sortOrder = adminPageArticleDto.getSortOrder();
        String timeSortField = adminPageArticleDto.getTimeSortField();
        String timeSortOrder = adminPageArticleDto.getTimeSortOrder();


        QueryWrapper<Article> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq(ObjUtil.isNotNull(id), "id", id);
        userQueryWrapper.like(StrUtil.isNotBlank(articleTitle), "articleTitle", articleTitle);
        // 这里可以查询 简介 或者 文章
        userQueryWrapper.like
                        (StrUtil.isNotBlank(articleCondition), "articleIntroduction", StrUtil.trimStart(articleCondition))
                .or()
                .like(StrUtil.isNotBlank(articleCondition), "articleContent", StrUtil.trimStart(articleCondition))
                .or()
                .like(StrUtil.isNotBlank(articleCondition), "articleTitle", StrUtil.trimStart(articleCondition));

        userQueryWrapper.eq(StrUtil.isNotBlank(articleCategory), "articleCategory", articleCategory);
        userQueryWrapper.eq(StrUtil.isNotBlank(articleAuthor), "articleAuthor", articleAuthor);
        userQueryWrapper.eq(ObjUtil.isNotNull(isRecommend), "isRecommend", isRecommend);
        userQueryWrapper.eq(ObjUtil.isNotNull(isHot), "isHot", isHot);
        userQueryWrapper.ge(ObjUtil.isNotNull(articleSize), "articleSize", articleSize);
        userQueryWrapper.eq(ObjUtil.isNotNull(articleStatus), "articleStatus", articleStatus);


        // 标签 需要 拼接查询
        if (StrUtil.isNotBlank(articleTags)) {
            List<String> tagsList = JSONUtil.toList(articleTags, String.class);
            tagsList.forEach(tag -> userQueryWrapper.like("articleTags", "\"" + tag + "\""));
        }


        // 时间范围
        userQueryWrapper.between(ObjUtil.isNotNull(createStartTime) && ObjUtil.isNotNull(createEndTime)
                , "createTime", createStartTime, createEndTime);


        // 排序字段
        userQueryWrapper.orderBy(StrUtil.isNotBlank(sortField), "asc".equals(sortOrder), sortField);
        // 时间排序字段
        userQueryWrapper.orderBy(StrUtil.isNotBlank(timeSortField), "asc".equals(timeSortOrder), timeSortField);
        return userQueryWrapper;
    }


    /**
     * 文章转 page
     *
     * @param article 文章类
     * @return 文章page
     */
    @Override
    public ArticleVo articleToVo(Article article) {
        if (article == null) {
            return null;
        }
        ArticleVo articleVo = new ArticleVo();
        BeanUtil.copyProperties(article, articleVo);
        return articleVo;
    }

    /**
     * 文章列表转 vo
     *
     * @param articles 文章列表
     * @return 文章vo列表
     */
    @Override
    public List<ArticleVo> articleListToVos(List<Article> articles) {
        if (articles == null) {
            return new ArrayList<>();
        }
        return articles.stream().map(this::articleToVo).collect(Collectors.toList());
    }


    /**
     * 获取 文章 标签 统计 数量
     * todo 这里可以优化 可以放进去缓存
     *
     * @return
     */
    @Override
    // 重写父类方法，获取文章标签数量
    public List<ArticleTagsCountVo> getArticleTagsCount() {

        // 获取文章标签数量
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.select("articleTags");
        articleQueryWrapper.eq("articleStatus", ArticleConstant.ARTICLE_STATUS_PUBLISH);

        List<Article> articles = this.list(articleQueryWrapper);
        List<List<String>> tagsList = articles.stream().map(
                article -> article.getArticleTags() != null ?
                        JSONUtil.toList(article.getArticleTags(), String.class) : new ArrayList<String>()
        ).toList();
        HashMap<String, Integer> articleTagsCount = new HashMap<>();

        for (List<String> tags : tagsList) {
            tags.forEach(tag -> {
                articleTagsCount.put(tag, articleTagsCount.getOrDefault(tag, 0) + 1);
            });
        }

        List<ArticleTagsCountVo> articleTagsCountVoList = new ArrayList<>();
        articleTagsCount.forEach((tag, count) -> {
            articleTagsCountVoList.add(new ArticleTagsCountVo(tag, count));
        });

        return articleTagsCountVoList;
    }


    /**
     * 获取 文章 时间 数量
     *
     * @return 时间数量
     */
    @Override
    public List<ArticleTimeCountVo> getArticleTimeCount() {

        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("articleStatus", ArticleConstant.ARTICLE_STATUS_PUBLISH);
        articleQueryWrapper.select("createTime");
        articleQueryWrapper.orderBy(true, false, "createTime");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        List<String> articleCreateTimeList = this.baseMapper.selectList(articleQueryWrapper).stream()
                .map(article -> sdf.format(article.getCreateTime())).toList();

        LinkedHashMap<String, Long> articleCreateTimeMap = new LinkedHashMap<>();
        articleCreateTimeList.forEach(
                createTime -> articleCreateTimeMap.put(
                        createTime,
                        articleCreateTimeMap.getOrDefault(createTime, 0L) + 1
                )
        );


        ArrayList<ArticleTimeCountVo> articleTimeCountVos = new ArrayList<>();
        articleCreateTimeMap.forEach((createTime, count) -> {
            articleTimeCountVos.add(new ArticleTimeCountVo(createTime, count));
        });
        return articleTimeCountVos;
    }

    /**
     * 获取 文章 统计 信息(只有发布的)
     *
     * @return
     */
    @Override
    public ArticleInfoCountVo getArticleInfoCount() {

        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("articleStatus", ArticleConstant.ARTICLE_STATUS_PUBLISH);
        long count = this.baseMapper.selectCount(articleQueryWrapper);

        articleQueryWrapper.select("sum(articleSize)");
        List<Object> objects = this.baseMapper.selectObjs(articleQueryWrapper);
        ThrowUtils.ifThrow(
                CollUtil.isEmpty(objects),
                ErrorEnums.OP_ERROR,
                "统计文章信息出错"
        );


        int articleWordSize = Integer.parseInt(objects.get(0) + "");

        ArticleInfoCountVo articleInfoCountVo = new ArticleInfoCountVo();
        articleInfoCountVo.setArticleWordCount(
                (articleWordSize >= 1000) ? articleWordSize / 1000.0 + "k" : articleWordSize + "");

        articleInfoCountVo.setArticleCount(count);

        return articleInfoCountVo;
    }


    /**
     * 获取 文章 统计 分类 信息(只有发布的)
     *
     * @return
     */
    @Override
    public List<ArticleTagsCountVo> getArticleCategoryList() {
//        List.of("前端开发", "后端开发", "测试", "学习", "日常", "无聊", "其他");
        // 获取文章标签数量
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.select("articleCategory");
        articleQueryWrapper.eq("articleStatus", ArticleConstant.ARTICLE_STATUS_PUBLISH);
        List<Article> articles = this.list(articleQueryWrapper);
        HashMap<String, Integer> articleCategorysCount = new HashMap<>();
        for (Article article : articles) {
            if (StrUtil.isNotBlank(article.getArticleCategory())) {
                articleCategorysCount.put(article.getArticleCategory(), articleCategorysCount.getOrDefault(article.getArticleCategory(), 0) + 1);
            }
        }

        List<ArticleTagsCountVo> articleCategorysCountVoList = new ArrayList<>();
        articleCategorysCount.forEach((category, count) -> {
            articleCategorysCountVoList.add(new ArticleTagsCountVo(category, count));
        });

        return articleCategorysCountVoList;
    }


    /**
     * 这个 接口 给 收藏文章 查询 使用
     *
     * @param ids ids 列表
     * @return
     */
    @Override
    public List<ArticlePageVo> articleByIdsAndWrapper(List<Long> ids, CollectArticlePageDto collectArticlePageDto) {

        AdminPageArticleDto adminPageArticleDto = BeanUtil.copyProperties(collectArticlePageDto, AdminPageArticleDto.class);
        QueryWrapper<Article> articleQueryWrapper = queryWrapper(adminPageArticleDto);
        articleQueryWrapper.eq("articleStatus",ArticleConstant.ARTICLE_STATUS_PUBLISH);
        articleQueryWrapper.in("id", ids);


        // 根据 ids 和 wrapper 查询
        List<Article> articles = this.baseMapper.selectList(articleQueryWrapper);


        return articleListToPageVos(articles);
    }
}




