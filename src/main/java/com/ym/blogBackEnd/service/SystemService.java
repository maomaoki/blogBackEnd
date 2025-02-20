package com.ym.blogBackEnd.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ym.blogBackEnd.model.domain.User;
import com.ym.blogBackEnd.model.dto.user.UserEditDto;
import com.ym.blogBackEnd.model.dto.user.UserLoginDto;
import com.ym.blogBackEnd.model.dto.user.UserRegisterDto;
import com.ym.blogBackEnd.model.dto.user.UserSendEmailCodeDto;
import com.ym.blogBackEnd.model.dto.user.admin.AdminAddUserDto;
import com.ym.blogBackEnd.model.dto.user.admin.AdminDeleteUserDto;
import com.ym.blogBackEnd.model.dto.user.admin.AdminPageUserDto;
import com.ym.blogBackEnd.model.dto.user.admin.AdminUpdateUserDto;
import com.ym.blogBackEnd.model.vo.system.SystemInfoVo;
import com.ym.blogBackEnd.model.vo.user.UserVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 54621
 * @description 系统 方面的 service
 * @createDate 2025-02-03 11:42:17
 */
public interface SystemService {


    /**
     * 获取 系统 信息
     * @return 系统信息
     */
    SystemInfoVo getSystemInfo();




}
