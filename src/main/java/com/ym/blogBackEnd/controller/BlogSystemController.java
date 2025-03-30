package com.ym.blogBackEnd.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ym.blogBackEnd.annotate.CheckAuth;
import com.ym.blogBackEnd.api.client.NewsApi;
import com.ym.blogBackEnd.api.model.news.dto.NewsDto;
import com.ym.blogBackEnd.api.model.news.vo.NewsVo;
import com.ym.blogBackEnd.common.response.Result;
import com.ym.blogBackEnd.constant.UserConstant;
import com.ym.blogBackEnd.enums.ErrorEnums;
import com.ym.blogBackEnd.model.domain.BlogSystem;
import com.ym.blogBackEnd.model.dto.blogSystem.admin.AdminAddBlogSystemDto;
import com.ym.blogBackEnd.model.vo.blogSystem.BlogSystemInfoVo;
import com.ym.blogBackEnd.service.BlogSystemService;
import com.ym.blogBackEnd.utils.ResUtils;
import com.ym.blogBackEnd.utils.ThrowUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title: SystemController
 * @Author YunMao
 * @Package com.ym.blogBackEnd.controller
 * @Date 2025/2/17 16:17
 * @description: 系统接口
 */
@RestController
@RequestMapping("/blogSystem")
@Slf4j
public class BlogSystemController {

    @Resource
    private BlogSystemService blogSystemService;

    @Resource
    private NewsApi newsApi;

    @GetMapping("/getBlogSystemInfo")
    public Result<BlogSystemInfoVo> getBlogSystemInfo() {
        QueryWrapper<BlogSystem> blogSystemQueryWrapper = new QueryWrapper<>();
        BlogSystem blogSystem = blogSystemService.getOne(blogSystemQueryWrapper);
        BlogSystemInfoVo blogSystemInfoVo = BeanUtil.copyProperties(blogSystem, BlogSystemInfoVo.class);
        return ResUtils.success(blogSystemInfoVo, "查询成功");
    }

    @PostMapping("/admin/editBlogSystemInfo")
    @CheckAuth(mustRole = UserConstant.USER_ROLE_BOSS)
    public Result<Boolean> adminEditBlogSystemInfo(@RequestBody BlogSystem blogSystem) {
        boolean result = blogSystemService.updateById(blogSystem);
        return ResUtils.success(result, "更新成功");
    }

    @PostMapping("/admin/saveBlogSystemInfo")
    @CheckAuth(mustRole = UserConstant.USER_ROLE_BOSS)
    public Result<Boolean> adminSaveBlogSystemInfo(@RequestBody AdminAddBlogSystemDto adminAddBlogSystemDto) {
        BlogSystem blogSystem = BeanUtil.copyProperties(adminAddBlogSystemDto, BlogSystem.class);
        boolean result = blogSystemService.save(blogSystem);
        return ResUtils.success(result, "添加成功");
    }


    @PostMapping("/news")
    public Result<List<NewsVo>> getNewsList(@RequestBody NewsDto newsDto) {
        List<NewsVo> newsList = newsApi.getNewsList(newsDto);
        ThrowUtils.ifThrow(
                CollUtil.isEmpty(newsList),
                ErrorEnums.NOT_FOUND_ERROR, "请求新闻错误"
        );
        return ResUtils.success(newsList, "请求成功");
    }

}
