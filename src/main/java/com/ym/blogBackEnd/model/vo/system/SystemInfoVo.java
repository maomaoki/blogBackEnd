package com.ym.blogBackEnd.model.vo.system;

import lombok.Data;

/**
 * @Title: SystemInfoVo
 * @Author YunMao
 * @Package com.ym.blogBackEnd.model.vo.system
 * @Date 2025/2/17 16:20
 * @description: 系统信息返回vo
 */
@Data
public class SystemInfoVo {

    /**
     * 在线流量
     */
    private Long onlineCount;


    /**
     * 用户 统计数
     */
    private Long userCount;


    /**
     * 文章 统计数
     */
    private Long articleCount;


    /**
     * 群聊消息 统计数
     */
    private Long chatCount;

    /**
     *  今日登录 统计数
     */
    private Long todayLoginCount;

}
