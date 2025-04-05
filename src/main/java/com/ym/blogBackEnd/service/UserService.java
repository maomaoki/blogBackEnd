package com.ym.blogBackEnd.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ym.blogBackEnd.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ym.blogBackEnd.model.dto.user.UserLoginDto;
import com.ym.blogBackEnd.model.dto.user.UserRegisterDto;
import com.ym.blogBackEnd.model.dto.user.UserSendEmailCodeDto;
import com.ym.blogBackEnd.model.dto.user.UserEditDto;
import com.ym.blogBackEnd.model.dto.user.admin.AdminAddUserDto;
import com.ym.blogBackEnd.model.dto.user.admin.AdminDeleteUserDto;
import com.ym.blogBackEnd.model.dto.user.admin.AdminPageUserDto;
import com.ym.blogBackEnd.model.dto.user.admin.AdminUpdateUserDto;
import com.ym.blogBackEnd.model.vo.user.UserCommentVo;
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
     * 这个接口 是 给 评论用的 查询 评论人 信息
     *
     * @param userId 用户id
     * @return 用户脱敏信息
     */
    public UserCommentVo userByCommentUserId(Long userId);


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
     * 管理员 添加用户
     *
     * @param adminAddUserDto 管理员 添加用户 请求 类
     * @param request         http
     * @return 用户id
     */
    public Long adminAddUser(AdminAddUserDto adminAddUserDto, HttpServletRequest request);


    /**
     * 管理员 删除用户
     *
     * @param adminDeleteUserDto 管理员 删除用户 请求 类
     * @return 用户id
     */
    public Long adminDeleteUser(AdminDeleteUserDto adminDeleteUserDto);


    /**
     * 管理员 更新 用户
     *
     * @param adminUpdateUserDto 管理员 更新 用户 请求类
     * @param request            http请求
     */
    public void adminUpdateUser(AdminUpdateUserDto adminUpdateUserDto, HttpServletRequest request);


    /**
     * 管理员 分页 查询 用户
     *
     * @param adminPageUserDto 管理员 分页查询 用户 请求类
     * @return 用户分页信息
     */
    public Page<UserVo> adminPageUser(AdminPageUserDto adminPageUserDto);

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


    /**
     * 密码 加盐 转md5
     *
     * @param userPassword 旧密码
     * @return 加盐后的密码
     */
    String saltMd5(String userPassword);

    /**
     * 判断当前 用户 权限是不是 系统管理员
     *
     * @param request http请求
     * @return true 是 false 不是
     */
    public Boolean isBoss(HttpServletRequest request);


    /**
     * 判断当前 用户 权限是不是 管理员以上
     *
     * @param userVo 用户 脱敏 类
     * @return true 是 false 不是
     */
    public Boolean isAdmin(UserVo userVo);


    /**
     * 判断当前 用户 权限是不是 管理员以上
     *
     * @param request http请求
     * @return true 是 false 不是
     */
    public Boolean isAdmin(HttpServletRequest request);


    /**
     * 这个 专门 给 文章 断言 是否 为 管理员(不存在业务操作)
     * @param request 请求
     * @return true 是 false 不是
     */
    public Boolean articleAssertIsAdmin(HttpServletRequest request);

}
