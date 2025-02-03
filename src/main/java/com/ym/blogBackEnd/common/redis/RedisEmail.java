package com.ym.blogBackEnd.common.redis;

import lombok.Data;

/**
 * @Title: RedisEmail
 * @Author YunMao
 * @Package com.ym.blogBackEnd.common.redis
 * @Date 2025/2/3 12:26
 * @description: redis存储emial数据
 */
@Data
public class RedisEmail {

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 验证码 类型
     */
    private Integer emailCodeType;

    /**
     * 验证码
     */
    private String emailCode;

}

