package com.ym.blogBackEnd.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ym.blogBackEnd.common.response.Result;
import com.ym.blogBackEnd.model.domain.BlogSystem;
import com.ym.blogBackEnd.model.vo.system.BannerInfoVo;
import com.ym.blogBackEnd.service.BlogSystemService;
import com.ym.blogBackEnd.service.SystemService;
import com.ym.blogBackEnd.utils.ResUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    private SystemService systemService;


    @Resource
    private BlogSystemService blogSystemService;

    @GetMapping("/getBlogSystemInfo")
    public Result<BlogSystem> getBlogSystemInfo() {
        QueryWrapper<BlogSystem> blogSystemQueryWrapper = new QueryWrapper<>();
        BlogSystem blogSystem = blogSystemService.getOne(blogSystemQueryWrapper);
        return ResUtils.success(blogSystem, "查询成功");
    }



    @GetMapping("/bannerInfo")
    public Result<BannerInfoVo> getBannerInfo() {
        BannerInfoVo bannerInfoVo = systemService.getBannerInfo();
        return ResUtils.success(bannerInfoVo,"查询成功");

    }



}
