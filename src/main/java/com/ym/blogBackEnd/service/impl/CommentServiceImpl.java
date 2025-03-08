package com.ym.blogBackEnd.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ym.blogBackEnd.enums.ErrorEnums;
import com.ym.blogBackEnd.model.domain.Comment;
import com.ym.blogBackEnd.model.dto.comment.CommentSaveDto;
import com.ym.blogBackEnd.model.vo.article.ArticlePageVo;
import com.ym.blogBackEnd.model.vo.comment.CommentVo;
import com.ym.blogBackEnd.model.vo.user.UserVo;
import com.ym.blogBackEnd.service.ArticleService;
import com.ym.blogBackEnd.service.CommentService;
import com.ym.blogBackEnd.mapper.CommentMapper;
import com.ym.blogBackEnd.service.UserService;
import com.ym.blogBackEnd.utils.ThrowUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 54621
 * @description 针对表【comment(评论表)】的数据库操作Service实现
 * @createDate 2025-02-20 21:26:27
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {

    @Resource
    private UserService userService;

    @Resource
    private ArticleService articleService;


    /**
     * 用户 发表 评论
     *
     * @param commentSaveDto 保存评论 请求 dto
     * @param request        请求
     * @return 是否发表成功
     */
    @Override
    public Boolean userSaveComment(CommentSaveDto commentSaveDto, HttpServletRequest request) {

        // 1. 校验参数
        ThrowUtils.ifThrow(
                commentSaveDto == null,
                ErrorEnums.PARAMS_ERROR,
                "参数错误"
        );

        // 2. 校验登录
        UserVo userVo = userService.userGetLoginInfo(request);
        ThrowUtils.ifThrow(
                userVo == null,
                ErrorEnums.USER_NOT_EXIST,
                "用户未登录");

        // 3. 校验是否存在此文章
        Long articleId = commentSaveDto.getArticleId();
        ArticlePageVo articlePageVo = articleService.getByArticleId(articleId);
        ThrowUtils.ifThrow(
                articlePageVo == null,
                ErrorEnums.NOT_FOUND_ERROR,
                "文章不存在");


        // 4. 封装数据
        Comment comment = new Comment();
        BeanUtil.copyProperties(commentSaveDto, comment);
        comment.setUserId(userVo.getId());

        // 5. 保存评论
        return save(comment);
    }


    /**
     * 文章 评论列表
     *
     * @param articleId 文章id
     * @return 评论列表
     */
    @Override
    public List<CommentVo> articleCommentList(Long articleId) {

        // 1. 校验是否存在此文章
        ArticlePageVo articlePageVo = articleService.getByArticleId(articleId);
        ThrowUtils.ifThrow(
                articlePageVo == null,
                ErrorEnums.NOT_FOUND_ERROR,
                "文章不存在");


        // 2. 查询此文章id中的所有评论记录
        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("articleId", articleId);
        List<Comment> comments = list(commentQueryWrapper);

        // 2-1. 没有fatherId 说明是最高层
        



        // 2-2. 有fatherId 说明是子评论

        // 3. 封装评论记录

        // 4. 返回评论记录

        return List.of();
    }
}




