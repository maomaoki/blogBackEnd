package com.ym.blogBackEnd.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: UserLoginDto
 * @Author YunMao
 * @Package com.ym.blogBackEnd.model.dto.user
 * @Date 2025/2/3 11:45
 * @description: 用户登录请求类
 */
@Data
public class UserLoginDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;

}
