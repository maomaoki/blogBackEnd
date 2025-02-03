package com.ym.blogBackEnd.enums;

import lombok.Getter;

/**
 * @Title: EmailCodeTypeEnums
 * @Author YunMao
 * @Package com.ym.blogBackEnd.enums
 * @Date 2025/2/3 11:52
 * @description: 邮箱验证码类型枚举类
 */
@Getter
public enum EmailCodeTypeEnums {

    /**
     * 注册
     */
    REGISTER(0, "注册"),

    /**
     * 忘记密码
     */
    FORGET_PASSWORD(1, "忘记密码");

    private Integer codeType;
    private String codeTypeDesc;

    EmailCodeTypeEnums(Integer codeType, String codeTypeDesc) {
        this.codeType = codeType;
        this.codeTypeDesc = codeTypeDesc;
    }

    /**
     * 根据 codeType 获取枚举类
     * @param codeType
     * @return
     */
    public static EmailCodeTypeEnums getEnumByCodeType(Integer codeType) {
        for (EmailCodeTypeEnums emailCodeTypeEnums : EmailCodeTypeEnums.values()) {
            if (emailCodeTypeEnums.getCodeType().equals(codeType)) {
                return emailCodeTypeEnums;
            }
        }
        return null;
    }
}
