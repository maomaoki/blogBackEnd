package com.ym.blogBackEnd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ym.blogBackEnd.model.domain.User;
import com.ym.blogBackEnd.model.vo.system.SystemInfoVo;
import com.ym.blogBackEnd.service.ArticleService;
import com.ym.blogBackEnd.service.SystemService;
import com.ym.blogBackEnd.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 54621
 * @description 系统 方面的 service
 * @createDate 2025-02-03 11:42:17
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Resource
    private UserService userService;

    @Resource
    private ArticleService articleService;



    /**
     * 获取 系统 信息
     * @return 系统信息
     */
    @Override
    public SystemInfoVo getSystemInfo() {


        SystemInfoVo systemInfoVo = new SystemInfoVo();

        // todo 获取在线人数
        systemInfoVo.setOnlineCount(999L);

        // 获取用户数
        long userCount = userService.count();
        systemInfoVo.setUserCount(userCount);

        // 获取文章数
        long articleCount = articleService.count();
        systemInfoVo.setArticleCount(articleCount);

        // todo 获取评论数
        systemInfoVo.setChatCount(99L);

        // todo 获取今日登录数
        systemInfoVo.setTodayLoginCount(10L);

        return systemInfoVo;
    }
}
