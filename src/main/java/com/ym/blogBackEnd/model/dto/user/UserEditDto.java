package com.ym.blogBackEnd.model.dto.user;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: UserEditDto
 * @Author YunMao
 * @Package com.ym.blogBackEnd.model.dto.user
 * @Date 2025/2/3 11:58
 * @description: 用户编辑请求类
 */
@Data
public class UserEditDto implements Serializable {

    /**
     * 用户昵称
     */
    private String userName;


    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户头像 id(跟用户操作没有关系)
     */
    private Long avatarId;


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


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
