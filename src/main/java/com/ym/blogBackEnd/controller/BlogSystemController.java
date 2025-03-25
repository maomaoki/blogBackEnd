package com.ym.blogBackEnd.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ym.blogBackEnd.annotate.CheckAuth;
import com.ym.blogBackEnd.common.response.Result;
import com.ym.blogBackEnd.constant.UserConstant;
import com.ym.blogBackEnd.model.domain.BlogSystem;
import com.ym.blogBackEnd.model.dto.blogSystem.admin.AdminAddBlogSystemDto;
import com.ym.blogBackEnd.model.vo.blogSystem.BlogSystemInfoVo;
import com.ym.blogBackEnd.service.BlogSystemService;
import com.ym.blogBackEnd.utils.ResUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    private BlogSystemService blogSystemService;

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


}
