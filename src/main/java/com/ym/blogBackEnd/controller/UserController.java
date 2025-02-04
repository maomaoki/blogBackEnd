package com.ym.blogBackEnd.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ym.blogBackEnd.common.response.Result;
import com.ym.blogBackEnd.model.dto.user.UserEditDto;
import com.ym.blogBackEnd.model.dto.user.UserLoginDto;
import com.ym.blogBackEnd.model.dto.user.UserRegisterDto;
import com.ym.blogBackEnd.model.dto.user.UserSendEmailCodeDto;
import com.ym.blogBackEnd.model.dto.user.admin.AdminAddUserDto;
import com.ym.blogBackEnd.model.dto.user.admin.AdminDeleteUserDto;
import com.ym.blogBackEnd.model.dto.user.admin.AdminPageUserDto;
import com.ym.blogBackEnd.model.dto.user.admin.AdminUpdateUserDto;
import com.ym.blogBackEnd.model.vo.user.UserVo;
import com.ym.blogBackEnd.service.UserService;
import com.ym.blogBackEnd.utils.ResUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Title: UserController
 * @Author YunMao
 * @Package com.ym.blogBackEnd.controller
 * @Date 2025/2/3 19:25
 * @description: 用户接口
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/sendEmailCode")
    public Result<Boolean> userSendEmailCode(@RequestBody UserSendEmailCodeDto userSendEmailCodeDto) {
        userService.userSendEmailCode(userSendEmailCodeDto);
        return ResUtils.success(true, "发送成功");
    }


    @PostMapping("/login")
    public Result<UserVo> userLogin(@RequestBody UserLoginDto userLoginDto, HttpServletRequest request) {
        UserVo userVo = userService.userLogin(userLoginDto, request);
        return ResUtils.success(userVo, "登陆成功");
    }

    @PostMapping("/register")
    public Result<Long> userRegister(@RequestBody UserRegisterDto userRegisterDto) {
        Long userId = userService.userRegister(userRegisterDto);
        return ResUtils.success(userId, "注册成功");
    }

    @PostMapping("/logout")
    public Result<Boolean> userLogout(HttpServletRequest request) {
        userService.userLogout(request);
        return ResUtils.success(true, "退出成功");
    }

    @GetMapping("/userGetLoginInfo")
    public Result<UserVo> userGetLoginInfo(HttpServletRequest request) {
        UserVo userVo = userService.userGetLoginInfo(request);
        return ResUtils.success(userVo, "获取成功");
    }

    @PostMapping("/edit")
    public Result<Boolean> userEdit(@RequestBody UserEditDto userEditDto, HttpServletRequest request) {
        userService.userEdit(userEditDto, request);
        return ResUtils.success(true, "编辑成功");
    }


    @PostMapping("/admin/add")
    public Result<Long> adminAddUser(@RequestBody AdminAddUserDto adminAddUserDto,HttpServletRequest request) {
        Long userId = userService.adminAddUser(adminAddUserDto,request);
        return ResUtils.success(userId, "添加成功");
    }


    @PostMapping("/admin/update")
    public Result<Boolean> adminEditUser(@RequestBody AdminUpdateUserDto adminUpdateUserDto, HttpServletRequest request) {
        userService.adminUpdateUser(adminUpdateUserDto, request);
        return ResUtils.success(true, "更新成功");
    }


    @PostMapping("/admin/delete")
    public Result<Long> adminDeleteUser(@RequestBody AdminDeleteUserDto adminDeleteUserDto) {
        Long deleteUserId = userService.adminDeleteUser(adminDeleteUserDto);
        return ResUtils.success(deleteUserId, "删除成功");
    }


    @PostMapping("admin/page")
    public Result<Page<UserVo>> adminPageUser(@RequestBody AdminPageUserDto adminPageUserDto) {
        Page<UserVo> userVoPage = userService.adminPageUser(adminPageUserDto);
        return ResUtils.success(userVoPage, "查询成功");
    }

}
