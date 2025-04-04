package com.ym.blogBackEnd.controller;

import com.ym.blogBackEnd.common.response.Result;
import com.ym.blogBackEnd.model.dto.comment.CommentSaveDto;
import com.ym.blogBackEnd.service.CommentService;
import com.ym.blogBackEnd.utils.ResUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
}
