package com.ym.blogBackEnd.config;

import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Title: UserConfig
 * @Author YunMao
 * @Package com.ym.blogBackEnd.config
 * @Date 2025/2/3 12:43
 * @description: 用户配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "ym.user")
public class UserConfig {
    /**
     * 注册邮箱验证码过期时间
     */
    private Long registerEmailCodeExpireTime;

    /**
     * 注册邮箱验证码长度
     */
    private Long registerEmailCodeLength;

    /**
     * 密码盐值
     */
    private String passwordSalt;

    /**
     *  密码 最小长度
     */
    private Integer passwordMinLength;

    /**
     * 账号 最小长度
     */
    private Integer accountMinLength;

    /**
     *  默认 密码
     */
    private String defaultPassword;

}
