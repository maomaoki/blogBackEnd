package com.ym.blogBackEnd.constant;

/**
 * @Title: CommentConstant
 * @Author YunMao
 * @Package com.ym.blogBackEnd.constant
 * @Date 2025/4/4 21:37
 * @description: 评论一些常量
 */
public interface CommentConstant {
    /**
     * 评论状态 0待审核 1 审核通过 2 审核拒绝
     */
    Integer COMMENT_STATUS_WAIT = 0;
    Integer COMMENT_STATUS_PASS = 1;
    Integer COMMENT_STATUS_REFUSE = 2;

    /**
     *  是否 热门
     */
    Integer COMMENT_IS_HOT = 1;
    Integer COMMENT_NOT_HOT = 0;

    /**
     * 是否置顶
     */
    Integer COMMENT_IS_STICKY = 1;
    Integer COMMENT_NOT_STICKY = 0;

    /**
     * 是否展示
     */
    Integer COMMENT_IS_SHOW = 1;
    Integer COMMENT_NOT_SHOW = 0;



}
