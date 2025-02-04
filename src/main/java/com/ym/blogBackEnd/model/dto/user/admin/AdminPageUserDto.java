package com.ym.blogBackEnd.model.dto.user.admin;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ym.blogBackEnd.common.request.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: AdminPageUserDto
 * @Author YunMao
 * @Package com.ym.blogBackEnd.model.dto.user.admin
 * @Date 2025/2/4 10:42
 * @description: 管理员分页查询用户请求类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AdminPageUserDto extends PageRequest implements Serializable {
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
     * 用户角色：user/admin/blackUser
     */
    private String userRole;

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
     *  开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
