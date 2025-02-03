package com.ym.blogBackEnd.model.vo.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: UserVo
 * @Author YunMao
 * @Package com.ym.blogBackEnd.model.vo.user
 * @Date 2025/2/3 12:03
 * @description: 用户信息脱敏响应类
 */
@Data
public class UserVo implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 用户手机号
     */
    private String userPhone;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户角色：user/admin/blackUser
     */
    private String userRole;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户标签 - json字符串
     */
    private String userTags;

    /**
     * 用户简介
     */
    private String userIntroduction;

    /**
     * 用户性别：0-男，1-女
     */
    private Integer userFGender;

    /**
     * 用户年龄
     */
    private Integer userAge;

    /**
     * 用户状态：0-正常，1-封禁
     */
    private Integer userStatus;

    /**
     * 用户注册来源：account/email/admin
     */
    private String registeredSource;


    /**
     * 创建时间
     */
    private Date createTime;


    private static final long serialVersionUID = 1L;
}
