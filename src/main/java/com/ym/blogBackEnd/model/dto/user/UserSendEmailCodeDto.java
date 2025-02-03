package com.ym.blogBackEnd.model.dto.user;

import com.ym.blogBackEnd.enums.EmailCodeTypeEnums;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: UserSendEmailCodeDto
 * @Author YunMao
 * @Package com.ym.blogBackEnd.model.dto.user
 * @Date 2025/2/3 11:48
 * @description: 用户发送邮箱验证码请求类
 */
@Data
public class UserSendEmailCodeDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 验证码类型 (默认注册类型)
     */
    private Integer emailCodeType = EmailCodeTypeEnums.REGISTER.getCodeType();
}
