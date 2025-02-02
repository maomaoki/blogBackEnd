package com.ym.blogBackEnd.handler;

import com.ym.blogBackEnd.common.response.Result;
import com.ym.blogBackEnd.enums.ErrorEnums;
import com.ym.blogBackEnd.exception.BusinessException;
import com.ym.blogBackEnd.utils.ResUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Title: YmExceptionHandler
 * @Author YunMao
 * @Package com.ym.blogBackEnd.handler
 * @Date 2025/2/2 21:52
 * @description: 异常捕获
 */
@Slf4j
@ControllerAdvice
public class YmExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<?> exceptionHandler(Exception e) {
        log.error("系统异常信息", e);
        return ResUtils.error(ErrorEnums.OP_ERROR, "系统错误");
    }


    @ExceptionHandler(value = BusinessException.class)
    public Result<?> businessExceptionHandler(BusinessException e) {
        log.error("业务异常信息", e);
        return ResUtils.error(e.getCode(), e.getMessage());
    }
}
