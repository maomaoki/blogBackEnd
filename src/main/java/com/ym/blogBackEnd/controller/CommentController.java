package com.ym.blogBackEnd.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ym.blogBackEnd.common.response.Result;
import com.ym.blogBackEnd.model.dto.comment.CommentByArticlePageDto;
import com.ym.blogBackEnd.model.dto.comment.CommentSaveDto;
import com.ym.blogBackEnd.model.vo.comment.CommentVo;
import com.ym.blogBackEnd.service.CommentService;
import com.ym.blogBackEnd.utils.ResUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Title: CommentController
 * @Author YunMao
 * @Package com.ym.blogBackEnd.controller
 * @Date 2025/4/4 19:59
 * @description: 评论接口
 */
@RequestMapping("/comment")
@RestController
@Slf4j
public class CommentController {

    @Resource
    private CommentService commentService;

    @PostMapping("/save")
    public Result<Boolean> saveComment(@RequestBody CommentSaveDto commentSaveDto, HttpServletRequest request) {
        Boolean result = commentService.saveComment(commentSaveDto, request);
        return ResUtils.success(result);
    }


    @PostMapping("/page")
    public Result<Page<CommentVo>> commentPageByArticleId(@RequestBody CommentByArticlePageDto commentByArticlePageDto) {
        Page<CommentVo> commentVoPage = commentService.pageCommentByArticleId(commentByArticlePageDto);
        return ResUtils.success(commentVoPage, "查询成功");
    }


    @GetMapping("/latest/{size}")
    public Result<List<CommentVo>> commentListByLatest(@PathVariable Integer size) {
        List<CommentVo> commentVos = commentService.latestComment(size);
        return ResUtils.success(commentVos, "查询成功");
    }
}
