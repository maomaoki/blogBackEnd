package com.ym.blogBackEnd.model.dto.user.admin;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: AdminDeleteUserDto
 * @Author YunMao
 * @Package com.ym.blogBackEnd.model.dto.user.admin
 * @Date 2025/2/4 10:39
 * @description: 管理员删除用户请求类
 */
@Data
public class AdminDeleteUserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
}
