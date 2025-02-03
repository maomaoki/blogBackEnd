package com.ym.blogBackEnd.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: UserRegisterDto
 * @Author YunMao
 * @Package com.ym.blogBackEnd.model.dto.user
 * @Date 2025/2/3 11:46
 * @description: 用户注册请求类
 */
@Data
public class UserRegisterDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;

    /**
     *  确认密码
     */
    private String checkPassword;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 邮箱验证码
     */
    private String userEmailCode;

}
