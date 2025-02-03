package com.ym.blogBackEnd.service;

import com.ym.blogBackEnd.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ym.blogBackEnd.model.dto.user.UserLoginDto;
import com.ym.blogBackEnd.model.dto.user.UserRegisterDto;
import com.ym.blogBackEnd.model.dto.user.UserSendEmailCodeDto;
import com.ym.blogBackEnd.model.dto.user.UserEditDto;
import com.ym.blogBackEnd.model.vo.user.UserVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 54621
 * @description 针对表【user(用户)】的数据库操作Service
 * @createDate 2025-02-03 11:42:17
 */
public interface UserService extends IService<User> {


    /**
     * 用户 发送邮箱验证码
     *
     * @param userSendEmailCodeDto 用户 发送邮箱验证码 请求 类
     */
    public void userSendEmailCode(UserSendEmailCodeDto userSendEmailCodeDto);


    /**
     * 用户 登录
     *
     * @param userLoginDto 用户登录 请求类
     * @param request      http请求
     * @return 脱敏用户信息
     */
    public UserVo userLogin(UserLoginDto userLoginDto, HttpServletRequest request);


    /**
     * 用户 注册
     *
     * @param userRegisterDto 用户 注册 请求 类
     * @return 用户id
     */
    public Long userRegister(UserRegisterDto userRegisterDto);


    /**
     * 用户 获取登录信息
     *
     * @param request http请求
     * @return 用户脱敏信息
     */
    public UserVo userGetLoginInfo(HttpServletRequest request);

    /**
     * 用户 注销
     *
     * @param request http请求
     */
    public void userLogout(HttpServletRequest request);

    /**
     * 用户 编辑
     *
     * @param userEditDto 用户 编辑 请求 类
     * @param request     http请求
     */
    public void userEdit(UserEditDto userEditDto, HttpServletRequest request);


    /**
     * 用户 转换为 用户脱敏信息
     *
     * @param user 用户
     * @return 用户脱敏信息
     */
    public UserVo userToVo(User user);


    /**
     * 用户列表 转换为 用户脱敏信息列表
     *
     * @param users 用户列表
     * @return 用户脱敏信息列表
     */
    public List<UserVo> userListToVos(List<User> users);
}
