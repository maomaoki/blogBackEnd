package com.ym.blogBackEnd.aop;

import com.ym.blogBackEnd.annotate.CheckAuth;
import com.ym.blogBackEnd.enums.ErrorEnums;
import com.ym.blogBackEnd.enums.UserRoleEnums;
import com.ym.blogBackEnd.exception.BusinessException;
import com.ym.blogBackEnd.model.vo.user.UserVo;
import com.ym.blogBackEnd.service.UserService;
import com.ym.blogBackEnd.utils.ThrowUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Title: AuthInterceptor
 * @Author YunMao
 * @Package com.ym.blogBackEnd.aop
 * @Date 2025/2/4 17:10
 * @description: 权限切入检查
 */
@Aspect
@Component
public class AuthInterceptor {

    @Resource
    private UserService userService;

    /**
     * 执行 拦截 检查
     *
     * @param point     切入点
     * @param checkAuth 注解
     * @return
     */
    @Around("@annotation(checkAuth)")
    public Object doInterceptor(ProceedingJoinPoint point, CheckAuth checkAuth) throws Throwable {

        // 检查注解角色是否符合
        String mustRole = checkAuth.mustRole();
        UserRoleEnums userRoleEnums = UserRoleEnums.getRole(mustRole);
        ThrowUtils.ifThrow(
                userRoleEnums == null,
                ErrorEnums.NOT_AUTH,
                "角色不明!"
        );


        // 获取当前 登录角色
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        UserVo userVo = userService.userGetLoginInfo(request);
        String userRole = userVo.getUserRole();
        UserRoleEnums loginUserRoleEnum = UserRoleEnums.getRole(userRole);
        ThrowUtils.ifThrow(
                loginUserRoleEnum == null,
                ErrorEnums.NOT_AUTH,
                "角色不明!"
        );


        // 检查是否拥有权限

        // 需要 云猫权限
        if (
                UserRoleEnums.BOSS.equals(userRoleEnums) &&
                        !UserRoleEnums.BOSS.equals(loginUserRoleEnum)) {
            throw new BusinessException(ErrorEnums.NOT_AUTH, "你不是云猫开发大大!");
        }


        // 需要 管理员权限
        if (
                UserRoleEnums.ADMIN.equals(userRoleEnums) &&
                        !UserRoleEnums.ADMIN.equals(loginUserRoleEnum) &&
                        !UserRoleEnums.BOSS.equals(loginUserRoleEnum)
        ) {
            throw new BusinessException(ErrorEnums.NOT_AUTH, "权限不足!");
        }

        // 通过权限校验，放行
        return point.proceed();

    }

}
