package com.ym.blogBackEnd.enums;

import lombok.Getter;

/**
 * @Title: UserRoleEnums
 * @Author YunMao
 * @Package com.ym.blogBackEnd.enums
 * @Date 2025/2/4 17:13
 * @description: 用户权限枚举值
 */
@Getter
public enum UserRoleEnums {

    ADMIN("admin", "管理员"),
    USER("user", "普通用户"),
    BOSS("boss", "云猫"),
    BLACK_USER("blackUser", "黑名单");

    /**
     * 角色
     */
    private String role;

    /**
     * 角色描述
     */
    private String description;

    UserRoleEnums(String role, String description) {
        this.role = role;
        this.description = description;
    }

    public static UserRoleEnums getRole(String role) {
        for (UserRoleEnums userRoleEnums : UserRoleEnums.values()) {
            if (userRoleEnums.getRole().equals(role)) {
                return userRoleEnums;
            }
        }
        return null;
    }
}
