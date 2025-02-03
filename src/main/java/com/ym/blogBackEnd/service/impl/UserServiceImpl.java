package com.ym.blogBackEnd.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ym.blogBackEnd.common.redis.RedisEmail;
import com.ym.blogBackEnd.config.UserConfig;
import com.ym.blogBackEnd.constant.UserConstant;
import com.ym.blogBackEnd.enums.EmailCodeTypeEnums;
import com.ym.blogBackEnd.enums.ErrorEnums;
import com.ym.blogBackEnd.exception.BusinessException;
import com.ym.blogBackEnd.model.domain.User;
import com.ym.blogBackEnd.model.dto.user.UserLoginDto;
import com.ym.blogBackEnd.model.dto.user.UserRegisterDto;
import com.ym.blogBackEnd.model.dto.user.UserSendEmailCodeDto;
import com.ym.blogBackEnd.model.dto.user.UserEditDto;
import com.ym.blogBackEnd.model.vo.user.UserVo;
import com.ym.blogBackEnd.service.UserService;
import com.ym.blogBackEnd.mapper.UserMapper;
import com.ym.blogBackEnd.utils.EmailUtils;
import com.ym.blogBackEnd.utils.RedisUtils;
import com.ym.blogBackEnd.utils.ThrowUtils;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author 54621
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2025-02-03 11:42:17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private EmailUtils emailUtils;

    @Resource
    private UserConfig userConfig;


    /**
     * 用户 发送邮箱验证码
     *
     * @param userSendEmailCodeDto 用户 发送邮箱验证码 请求 类
     */
    @Override
    public void userSendEmailCode(UserSendEmailCodeDto userSendEmailCodeDto) {
        // 1. 校验参数
        String userAccount = userSendEmailCodeDto.getUserAccount();
        String userEmail = userSendEmailCodeDto.getUserEmail();
        Integer emailCodeType = userSendEmailCodeDto.getEmailCodeType();
        EmailCodeTypeEnums enumByCodeType = EmailCodeTypeEnums.getEnumByCodeType(emailCodeType);
        ThrowUtils.ifThrow(
                StrUtil.isAllBlank(userAccount, userEmail),
                ErrorEnums.PARAMS_ERROR,
                "用户账号和邮箱不能为空"
        );

        ThrowUtils.ifThrow(
                enumByCodeType == null,
                ErrorEnums.PARAMS_ERROR,
                "验证码类型错误");


        // 2. 查询是否redis 存在 此记录 (redis key = userEmail)
        // todo 这里会不会有 访问频繁问题优化
        Object o = redisUtils.get(userEmail);
        RedisEmail redisEmail = (RedisEmail) o;
        ThrowUtils.ifThrow(
                redisEmail != null &&
                        redisEmail.getEmailCodeType().equals(enumByCodeType.getCodeType()),
                ErrorEnums.PARAMS_ERROR,
                "验证码已发送，请勿重复发送"
        );

        // 3. 生成随机验证码
        String randomCode = RandomUtil.randomString(Math.toIntExact(userConfig.getRegisterEmailCodeLength()));

        // 4. 创建redis存储数据
        redisEmail = new RedisEmail();
        redisEmail.setUserAccount(userAccount);
        redisEmail.setUserEmail(userEmail);
        redisEmail.setEmailCodeType(enumByCodeType.getCodeType());
        redisEmail.setEmailCode(randomCode);

        String sub = "云猫注册验证码";

        // 5. 发送邮箱
        try {
            emailUtils.sendEmail(
                    userEmail,
                    sub,
                    "您的验证码为：" + randomCode + "，请勿泄露给他人,过期时间三分钟!"
            );
            // 6. 存储redis
            redisUtils.set(userEmail, redisEmail, userConfig.getRegisterEmailCodeExpireTime(), TimeUnit.MINUTES);
        } catch (MailException e) {
            log.error("发送邮件失败", e);
        }

    }


    /**
     * 用户 登录
     *
     * @param userLoginDto 用户登录 请求类
     * @param request      http请求
     * @return 脱敏用户信息
     */
    @Override
    public UserVo userLogin(UserLoginDto userLoginDto, HttpServletRequest request) {
        String userAccount = userLoginDto.getUserAccount();
        String userPassword = userLoginDto.getUserPassword();

        // 1. 校验参数
        verifyAccountAndPassword(userAccount, userPassword, userPassword);

        // 密码一定要转换
        userPassword = saltMd5(userPassword);

        // 2. 查询用户是否存在
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("userAccount", userAccount).or().eq("userEmail", userAccount);
        userQueryWrapper.eq("userPassword", userPassword);
        User user = this.getOne(userQueryWrapper);
        // 就算不存在 也不能 明显 提示
        ThrowUtils.ifThrow(
                user == null,
                ErrorEnums.USER_NOT_EXIST,
                "账号或密码错误"
        );

        // 3. 查询用户状态
        Integer userStatus = user.getUserStatus();
        ThrowUtils.ifThrow(
                userStatus.equals(UserConstant.USER_STATUS_FREEZE),
                ErrorEnums.NOT_AUTH,
                "账号已被冻结,请联系管理员!"
        );

        // 4.记录登录态
        request.getSession().setAttribute(UserConstant.USER_LOGIN_KEY, user);


        // 5. 正常情况 返回脱敏信息
        return userToVo(user);
    }


    /**
     * 用户 注册
     *
     * @param userRegisterDto 用户 注册 请求 类
     * @return 用户id
     */
    @Override
    public Long userRegister(UserRegisterDto userRegisterDto) {

        String userAccount = userRegisterDto.getUserAccount();
        String userPassword = userRegisterDto.getUserPassword();
        String checkPassword = userRegisterDto.getCheckPassword();
        String userEmail = userRegisterDto.getUserEmail();
        String userEmailCode = userRegisterDto.getUserEmailCode();

        // 1. 校验参数
        verifyAccountAndPassword(userAccount, userPassword, checkPassword);

        // 2. 根据 邮箱 去 查询 redis 是否存在 此纪录
        RedisEmail redisEmail = (RedisEmail) redisUtils.get(userEmail);
        ThrowUtils.ifThrow(
                redisEmail == null,
                ErrorEnums.PARAMS_ERROR,
                "验证码已过期，请重新发送");

        // 3. 校验 redis 数据 是否 一致
        String redisUserAccount = redisEmail.getUserAccount();
        String redisUserEmail = redisEmail.getUserEmail();
        Integer redisEmailCodeType = redisEmail.getEmailCodeType();
        EmailCodeTypeEnums enumByCodeType = EmailCodeTypeEnums.getEnumByCodeType(redisEmailCodeType);
        String redisEmailCode = redisEmail.getEmailCode();


        boolean result =
                enumByCodeType == null ||
                        !enumByCodeType.equals(EmailCodeTypeEnums.REGISTER) ||
                        !redisUserAccount.equals(userAccount) ||
                        !redisUserEmail.equals(userEmail) ||
                        !redisEmailCode.equals(userEmailCode);
        ThrowUtils.ifThrow(
                result,
                ErrorEnums.PARAMS_ERROR,
                "验证码错误");

        // 4. 校验 用户账号 是否存在(要校验邮箱和账号,账号也有可能是邮箱)
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("userAccount", userAccount).or().eq("userEmail", userAccount).or().eq("userEmail", userEmail);
        User user = this.getOne(userQueryWrapper);

        // 用户 存在
        if (user != null) {
            // 7. 删除redis
            redisUtils.delete(userEmail);
            throw new BusinessException(ErrorEnums.PARAMS_ERROR, "用户已存在,请登录!");
        }


        // 5. 封装 user 插入数据库
        User userInsert = new User();
        userInsert.setUserAccount(userAccount);
        userInsert.setUserEmail(userEmail);
        // 密码 一定要加密 加盐
        userInsert.setUserPassword(saltMd5(userPassword));
        // 用户昵称默认为账号 前6位
        userInsert.setUserName(StrUtil.subPre(userAccount, Math.toIntExact(userConfig.getRegisterEmailCodeLength())));
        // 权限 默认user
        userInsert.setUserRole(UserConstant.USER_ROLE_USER);

        // todo 头像 后面 有默认
        // userInsert.setUserAvatar();

        // 性别 默认 小哥哥
        userInsert.setUserFGender(UserConstant.USER_SEX_BOY);

        // 状态 默认正常
        userInsert.setUserStatus(UserConstant.USER_STATUS_NORMAL);

        // 注册来源(邮箱)
        userInsert.setRegisteredSource(UserConstant.USER_REGISTER_SOURCE_EMAIL);

        // 6. 插入
        this.save(userInsert);

        // 7. 删除redis
        redisUtils.delete(userEmail);

        // 8. 返回用户id
        return userInsert.getId();
    }


    /**
     * 用户 发送邮箱验证码
     *
     * @param request http请求
     * @return 用户脱敏信息
     */
    @Override
    public UserVo userGetLoginInfo(HttpServletRequest request) {
        Object attribute = request.getSession().getAttribute(UserConstant.USER_LOGIN_KEY);
        User oldUser = (User) attribute;
        ThrowUtils.ifThrow(
                oldUser == null,
                ErrorEnums.NOT_AUTH,
                "用户未登录");
        // 要用 用户id 去数据库重新查询一次 要不然会出现数据不同步情况
        Long userId = oldUser.getId();
        User user = this.getById(userId);

        // 这里 也可以 根据状态区分一下(可以清空session)
        Integer userStatus = user.getUserStatus();
        if (userStatus.equals(UserConstant.USER_STATUS_FREEZE)) {
            request.getSession().removeAttribute(UserConstant.USER_LOGIN_KEY);
            throw new BusinessException(ErrorEnums.NOT_AUTH, "账号已被冻结,请联系管理员!");
        }
        return userToVo(user);
    }

    /**
     * 用户 注销
     *
     * @param request http请求
     */
    @Override
    public void userLogout(HttpServletRequest request) {
        request.getSession().removeAttribute(UserConstant.USER_LOGIN_KEY);
    }

    /**
     * 用户 更新
     *
     * @param userEditDto 用户 编辑 请求 类
     * @param request     http请求
     */
    @Override
    public void userEdit(UserEditDto userEditDto, HttpServletRequest request) {
        // 1. 判断是否登录(里面已经判断是否登录)
        UserVo userVo = this.userGetLoginInfo(request);

        // 2. 封装编辑数据(根据id更新)
        User userUpdate = new User();
        BeanUtil.copyProperties(userEditDto, userUpdate);
        userUpdate.setId(userVo.getId());
        userUpdate.setEditTime(new Date());
        // 3. 更新
        this.updateById(userUpdate);
    }

    /**
     * 用户 转换为 用户脱敏信息
     *
     * @param user 用户
     * @return 用户脱敏信息
     */
    @Override
    public UserVo userToVo(User user) {
        if (user == null) {
            return null;
        }
        UserVo userVo = new UserVo();
        BeanUtil.copyProperties(user, userVo);
        return userVo;
    }

    /**
     * 用户列表 转换为 用户脱敏信息列表
     *
     * @param users 用户列表
     * @return 用户脱敏信息列表
     */
    @Override
    public List<UserVo> userListToVos(List<User> users) {
        if (users == null) {
            return new ArrayList<>();
        }
        return users.stream()
                .map(this::userToVo).collect(Collectors.toList());
    }


    /**
     * 校验 账号 密码 和 确认密码
     *
     * @param userAccount   用户账号
     * @param userPassword  密码
     * @param checkPassword 确认密码
     */
    private void verifyAccountAndPassword(String userAccount, String userPassword, String checkPassword) {

        // 1. 参数是否存在
        ThrowUtils.ifThrow(
                StrUtil.isAllBlank(userAccount, userPassword, checkPassword),
                ErrorEnums.PARAMS_ERROR,
                "用户账号和密码不能为空");

        // 2. 账号 长度
        ThrowUtils.ifThrow(
                userAccount.length() < userConfig.getAccountMinLength(),
                ErrorEnums.PARAMS_ERROR,
                "用户账号长度不能小于" + userConfig.getAccountMinLength());

        // 3. 密码长度 和 确认密码是否一致
        ThrowUtils.ifThrow(
                userPassword.length() < userConfig.getPasswordMinLength() ||
                        !userPassword.equals(checkPassword),
                ErrorEnums.PARAMS_ERROR,
                "密码长度不能小于" + userConfig.getPasswordMinLength() + "，或者密码和确认密码不一致");
    }


    /**
     * 密码 加盐 转md5
     *
     * @param userPassword 旧密码
     * @return 加盐后的密码
     */
    private String saltMd5(String userPassword) {
        return SecureUtil.md5(userPassword + userConfig.getPasswordSalt());
    }
}




