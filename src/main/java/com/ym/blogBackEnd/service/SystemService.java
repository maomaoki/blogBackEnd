package com.ym.blogBackEnd.service;

import com.ym.blogBackEnd.model.vo.system.BannerInfoVo;
import com.ym.blogBackEnd.model.vo.system.SystemInfoVo;

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


    /**
     * 获取 banner 信息
     * @return banner 信息
     */
    BannerInfoVo getBannerInfo();
}
