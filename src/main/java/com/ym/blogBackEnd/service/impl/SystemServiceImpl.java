package com.ym.blogBackEnd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ym.blogBackEnd.constant.PictureConstant;
import com.ym.blogBackEnd.enums.ErrorEnums;
import com.ym.blogBackEnd.exception.BusinessException;
import com.ym.blogBackEnd.model.domain.Picture;
import com.ym.blogBackEnd.model.vo.system.BannerInfoVo;
import com.ym.blogBackEnd.model.vo.system.SystemInfoVo;
import com.ym.blogBackEnd.service.ArticleService;
import com.ym.blogBackEnd.service.PictureService;
import com.ym.blogBackEnd.service.SystemService;
import com.ym.blogBackEnd.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Resource
    private PictureService pictureService;


    /**
     * 获取 系统 信息
     *
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


    /**
     * 获取 banner 信息
     *
     * @return banner 信息
     */
    @Override
    public BannerInfoVo getBannerInfo() {

        BannerInfoVo bannerInfoVo = new BannerInfoVo();

        QueryWrapper<Picture> pictureQueryWrapper = new QueryWrapper<>();
        pictureQueryWrapper.eq("pictureCategory", PictureConstant.PICTURE_CATEGORY_BANNER);
        Picture picture = pictureService.getOne(pictureQueryWrapper);
        if (picture == null) {
            throw new BusinessException(ErrorEnums.NOT_FOUND_ERROR, "没有封面照片");
        }
        bannerInfoVo.setBannerImageUrl(picture.getPictureUrl());

        // todo 到时 提取 title 出来
        bannerInfoVo.setBannerTitle("云猫•博客");

        List<String> strings = new ArrayList<>();
        strings.add(
                "河边芦苇青苍苍,秋深露水结成霜。意中之人在何处?就在河水那一方。"
        );
        strings.add(
                "关关雎鸠,在河之洲。窈窕淑女,君子好逑。"
        );
        strings.add(
                "参差荇菜,左右采之。窈窕淑女,琴瑟友之。"
        );
        strings.add(
                "风萧萧兮易水寒,壮士一去兮不复还。"
        );

        bannerInfoVo.setPrintList(strings);

        return bannerInfoVo;
    }
}
