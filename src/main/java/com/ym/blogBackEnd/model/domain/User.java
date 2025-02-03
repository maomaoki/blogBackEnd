package com.ym.blogBackEnd.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
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
     * 密码
     */
    private String userPassword;

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
     * 编辑时间
     */
    private Date editTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}