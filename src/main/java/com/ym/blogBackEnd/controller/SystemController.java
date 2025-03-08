package com.ym.blogBackEnd.controller;

import com.ym.blogBackEnd.common.response.Result;
import com.ym.blogBackEnd.model.vo.system.BannerInfoVo;
import com.ym.blogBackEnd.model.vo.system.SystemInfoVo;
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
@RequestMapping("/system")
@Slf4j
public class SystemController {

    @Resource
    private SystemService systemService;



    @GetMapping("/info")
    public Result<SystemInfoVo> getSystemInfo() {
        SystemInfoVo systemInfo = systemService.getSystemInfo();
        return ResUtils.success(systemInfo, "查询成功");
    }

    @GetMapping("/bannerInfo")
    public Result<BannerInfoVo> getBannerInfo() {
        BannerInfoVo bannerInfoVo = systemService.getBannerInfo();
        return ResUtils.success(bannerInfoVo,"查询成功");

    }

}
