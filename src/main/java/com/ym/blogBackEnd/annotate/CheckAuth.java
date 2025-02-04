package com.ym.blogBackEnd.annotate;

import org.mapstruct.TargetType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title: CheckAuth
 * @Author YunMao
 * @Package com.ym.blogBackEnd.annotate
 * @Date 2025/2/4 17:07
 * @description: 检查权限注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckAuth {

    /**
     * 必须 角色
     * @return
     */
    String mustRole() default "";
}
