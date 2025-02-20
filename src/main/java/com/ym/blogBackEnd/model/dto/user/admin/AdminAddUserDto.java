package com.ym.blogBackEnd.model.dto.user.admin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: AdminAddUserDto
 * @Author YunMao
 * @Package com.ym.blogBackEnd.model.dto.user.admin
 * @Date 2025/2/4 10:38
 * @description: 管理员添加用户请求类
 */
@Data
public class AdminAddUserDto implements Serializable {

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


    private static final long serialVersionUID = 1L;

}
