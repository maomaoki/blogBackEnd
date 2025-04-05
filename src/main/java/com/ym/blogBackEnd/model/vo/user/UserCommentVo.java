package com.ym.blogBackEnd.model.vo.user;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 提供 给 评论 返回 用户 信息
 */
@Data
public class UserCommentVo implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 账号
     */
    private String userAccount;


    /**
     * 用户昵称
     */
    private String userName;
    /**
     * 用户头像
     */
    private String userAvatar;


    @Serial
    private static final long serialVersionUID = 1L;
}
