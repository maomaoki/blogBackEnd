package com.ym.blogBackEnd.constant;

/**
 * @Title: UserConstant
 * @Author YunMao
 * @Package com.ym.blogBackEnd.constant
 * @Date 2025/2/3 17:53
 * @description: 用户常量
 */
public interface UserConstant {

    /**
     * 用户 登录态 存储 key
     */
    String USER_LOGIN_KEY = "userLoginKey";


    /**
     * 用户 账号 状态 正常
     */
    Integer USER_STATUS_NORMAL = 0;

    /**
     * 用户 账号 状态 冻结
     */
    Integer USER_STATUS_FREEZE = 1;

    /**
     * 用户 角色 - 普通用户
     */
    String USER_ROLE_USER = "user";

    /**
     * 用户 角色 - 管理员
     */
    String USER_ROLE_ADMIN = "admin";

    /**
     * 用户 角色 - 黑名单
     */
    String USER_ROLE_BLACK_USER = "blackUser";

    /**
     * 用户 角色 - 系统管理员
     */
    String USER_ROLE_BOSS = "boss";


    /**
     * 用户 性别 - 小哥哥
     */
    Integer USER_SEX_BOY = 0;

    /**
     * 用户 性别 - 小姐姐
     */
    Integer USER_SEX_GIRL = 1;


    /**
     * 注册 来源 - 邮箱
     */
    String USER_REGISTER_SOURCE_EMAIL = "email";

    /**
     * 注册 来源 - 账号
     */
    String USER_REGISTER_SOURCE_ACCOUNT = "account";

    /**
     * 注册 来源 - 管理员添加
     */
    String USER_REGISTER_SOURCE_ADMIN = "admin";

}
