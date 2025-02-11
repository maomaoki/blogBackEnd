package com.ym.blogBackEnd.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ym.blogBackEnd.annotate.CheckAuth;
import com.ym.blogBackEnd.common.response.Result;
import com.ym.blogBackEnd.config.PictureConfig;
import com.ym.blogBackEnd.constant.UserConstant;
import com.ym.blogBackEnd.manager.picture.PictureManager;
import com.ym.blogBackEnd.model.dto.picture.UploadPictureDto;
import com.ym.blogBackEnd.model.dto.picture.admin.AdminPagePictureDto;
import com.ym.blogBackEnd.model.dto.user.admin.AdminPageUserDto;
import com.ym.blogBackEnd.model.vo.picture.PictureVo;
import com.ym.blogBackEnd.model.vo.picture.UploadPictureVo;
import com.ym.blogBackEnd.model.vo.user.UserVo;
import com.ym.blogBackEnd.service.PictureService;
import com.ym.blogBackEnd.utils.ResUtils;
import io.swagger.models.Path;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Title: PictureController
 * @Author YunMao
 * @Package com.ym.blogBackEnd.controller
 * @Date 2025/2/7 12:22
 * @description: 图片接口
 */
@RestController
@RequestMapping("/picture")
public class PictureController {

    @Resource
    private PictureService pictureService;


    @PostMapping("/upload")
    public Result<UploadPictureVo> uploadPicture(@Param("file") MultipartFile file,
                                                 UploadPictureDto uploadPictureDto,
                                                 HttpServletRequest request) {
        UploadPictureVo uploadPictureVo = pictureService.uploadPicture(file, uploadPictureDto, request);
        return ResUtils.success(uploadPictureVo, "保存成功");
    }


    @PostMapping("admin/page")
    @CheckAuth(mustRole = UserConstant.USER_ROLE_ADMIN)
    public Result<Page<PictureVo>> adminPagePicture(@RequestBody AdminPagePictureDto adminPagePictureDto) {
        Page<PictureVo> pictureVoPage = pictureService.adminPicturePage(adminPagePictureDto);
        return ResUtils.success(pictureVoPage, "查询成功");
    }


}
