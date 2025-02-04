package com.ym.blogBackEnd.model.dto.user.admin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: AdminUpdateUserDto
 * @Author YunMao
 * @Package com.ym.blogBackEnd.model.dto.user.admin
 * @Date 2025/2/4 10:40
 * @description: 管理员更新用户请求类
 */
@Data
public class AdminUpdateUserDto implements Serializable {

    /**
     * id
     */
    private Long id;

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
     * 用户状态：0-正常，1-封禁
     */
    private Integer userStatus;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;



}
