package com.ym.blogBackEnd.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ym.blogBackEnd.enums.ErrorEnums;
import com.ym.blogBackEnd.model.domain.Article;
import com.ym.blogBackEnd.model.domain.Comment;
import com.ym.blogBackEnd.model.dto.comment.CommentSaveDto;
import com.ym.blogBackEnd.model.vo.article.ArticleVo;
import com.ym.blogBackEnd.model.vo.user.UserVo;
import com.ym.blogBackEnd.service.ArticleService;
import com.ym.blogBackEnd.service.CommentService;
import com.ym.blogBackEnd.mapper.CommentMapper;
import com.ym.blogBackEnd.service.UserService;
import com.ym.blogBackEnd.utils.ThrowUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
* @author 54621
* @description 针对表【comment(评论表)】的数据库操作Service实现
* @createDate 2025-04-03 21:38:37
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

    @Resource
    private UserService userService;

    @Resource
    private ArticleService articleService;

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
        String comment = filterSensitiveCommentWords(saveDto.getContent());

        // 4. 组装保存数据


        // 5. 保存评论


        return true;
    }

    /**
     * 过滤 评论中 的 敏感词 替换成 **
     * @param content 旧评论
     * @return 过滤 后 的 评论
     */
    private String filterSensitiveCommentWords(String content) {
        ThrowUtils.ifThrow(
                StrUtil.isEmpty(content),
                ErrorEnums.PARAMS_ERROR,
                "评论内容不能为空"
        );

        //

        return null;
    }
}




