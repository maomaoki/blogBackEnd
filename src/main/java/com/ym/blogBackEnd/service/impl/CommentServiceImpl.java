package com.ym.blogBackEnd.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.ym.blogBackEnd.constant.CommentConstant;
import com.ym.blogBackEnd.enums.ErrorEnums;
import com.ym.blogBackEnd.model.domain.Article;
import com.ym.blogBackEnd.model.domain.Comment;
import com.ym.blogBackEnd.model.dto.comment.CommentByArticlePageDto;
import com.ym.blogBackEnd.model.dto.comment.CommentSaveDto;
import com.ym.blogBackEnd.model.vo.article.ArticleVo;
import com.ym.blogBackEnd.model.vo.comment.CommentVo;
import com.ym.blogBackEnd.model.vo.user.UserCommentVo;
import com.ym.blogBackEnd.model.vo.user.UserVo;
import com.ym.blogBackEnd.service.ArticleService;
import com.ym.blogBackEnd.service.CommentService;
import com.ym.blogBackEnd.mapper.CommentMapper;
import com.ym.blogBackEnd.service.UserService;
import com.ym.blogBackEnd.utils.ThrowUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 54621
 * @description 针对表【comment(评论表)】的数据库操作Service实现
 * @createDate 2025-04-03 21:38:37
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {

    @Resource
    private UserService userService;

    @Resource
    private ArticleService articleService;

    @Resource
    private SensitiveWordBs sensitiveWordBs;

    /**
     * 保存 评论
     *
     * @param saveDto 保存 评论 dto
     * @param request 请求 登录凭证
     * @return 是否保存成功
     */
    @Override
    public Boolean saveComment(CommentSaveDto saveDto, HttpServletRequest request) {
        // 1. 需要 登录
        UserVo userVo = userService.userGetLoginInfo(request);

        // 2. 需要判断 文章是否存在
        Long articleId = saveDto.getArticleId();
        articleService.getPublishArticleById(articleId);

        // 3. 需要过滤敏感词
        ThrowUtils.ifThrow(
                StrUtil.isEmpty(saveDto.getContent()),
                ErrorEnums.PARAMS_ERROR,
                "评论内容不能为空"
        );
        saveDto.setContent(filterSensitiveCommentWords(saveDto.getContent()));

        // 4. 组装保存数据
        Comment comment = new Comment();
        BeanUtil.copyProperties(saveDto, comment);

        // 默认数据补充
        comment.setUserId(userVo.getId());
        comment.setIsHot(CommentConstant.COMMENT_NOT_HOT);
        comment.setIsSticky(CommentConstant.COMMENT_NOT_STICKY);
        comment.setLikeNumber(0);
        comment.setReplyNumber(0);
        // 额外字段
        comment.setIsShow(CommentConstant.COMMENT_IS_SHOW);

        // 设备 定位 还没有做

        // 审核 现在 默认 审核 通过
        comment.setReviewStatus(CommentConstant.COMMENT_STATUS_PASS);
        comment.setReviewReason("默认通过");


        // 5. 保存评论
        return this.save(comment);
    }


    /**
     * 根据 文章 id 分页 查询 评论列表
     *
     * @param commentByArticlePageDto
     * @return
     */
    @Override
    public Page<CommentVo> pageCommentByArticleId(CommentByArticlePageDto commentByArticlePageDto) {

        Long articleId = commentByArticlePageDto.getArticleId();
        ThrowUtils.ifThrow(
                ObjUtil.isEmpty(articleId),
                ErrorEnums.PARAMS_ERROR,
                "文章id不能为空"
        );

        Integer pageNum = commentByArticlePageDto.getPageNum();
        Integer pageSize = commentByArticlePageDto.getPageSize();


        // 查询 文章 是否 存在
        articleService.getPublishArticleById(articleId);

        // 根据 文章 id 查询 所有 评论列表(状态为审核通过) 并且默认以时间最新排序
        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("articleId", articleId);
        commentQueryWrapper.eq("reviewStatus", CommentConstant.COMMENT_STATUS_PASS);
        commentQueryWrapper.eq("isShow", CommentConstant.COMMENT_IS_SHOW);
        commentQueryWrapper.orderBy(true, false, "createTime");

        Page<Comment> page = this.page(new Page<>(pageNum, pageSize), commentQueryWrapper);

        // 封装 返回 数据
        Page<CommentVo> commentVoPage = new Page<>(pageNum, pageSize, page.getTotal());
        // 这里需要将 用户id 转成 用户 脱敏信息(交给 脱敏评论列表做就行了)
        commentVoPage.setRecords(this.commentListToVoList(page.getRecords()));
        return commentVoPage;
    }

    /**
     * 过滤 评论中 的 敏感词 替换成 **
     *
     * @param content 旧评论
     * @return 过滤 后 的 评论
     */
    private String filterSensitiveCommentWords(String content) {
        ThrowUtils.ifThrow(
                StrUtil.isEmpty(content),
                ErrorEnums.PARAMS_ERROR,
                "评论内容不能为空"
        );

        // todo 其实这里可以 异步处理 毕竟 还有一步 审核评论操作

        return sensitiveWordBs.replace(content);
    }


    /**
     * comment 脱敏
     *
     * @param comment
     * @return
     */
    private CommentVo commentToVo(Comment comment) {

        if (comment == null) {
            return null;
        }

        // 需要 查询 用户 信息 脱敏 转换
        Long userId = comment.getUserId();
        UserCommentVo userCommentVo = userService.userByCommentUserId(userId);
        if (userCommentVo == null) {
            return null;
        }
        CommentVo commentVo = BeanUtil.copyProperties(comment, CommentVo.class);
        commentVo.setUserCommentVo(userCommentVo);
        return commentVo;
    }


    /**
     * commentList 脱敏
     *
     * @param commentList
     * @return
     */
    private List<CommentVo> commentListToVoList(List<Comment> commentList) {

        if (CollUtil.isEmpty(commentList)) {
            return new ArrayList<>();
        }
        return commentList.stream().map(this::commentToVo).toList();
    }
}




