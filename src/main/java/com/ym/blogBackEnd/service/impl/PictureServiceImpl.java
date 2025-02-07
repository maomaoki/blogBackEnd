package com.ym.blogBackEnd.service.impl;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ym.blogBackEnd.config.PictureConfig;
import com.ym.blogBackEnd.constant.PictureConstant;
import com.ym.blogBackEnd.enums.ErrorEnums;
import com.ym.blogBackEnd.exception.BusinessException;
import com.ym.blogBackEnd.manager.picture.PictureManager;
import com.ym.blogBackEnd.model.domain.Picture;
import com.ym.blogBackEnd.model.dto.picture.UploadPictureDto;
import com.ym.blogBackEnd.model.vo.picture.UploadPictureVo;
import com.ym.blogBackEnd.model.vo.user.UserVo;
import com.ym.blogBackEnd.service.PictureService;
import com.ym.blogBackEnd.mapper.PictureMapper;
import com.ym.blogBackEnd.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author 54621
 * @description 针对表【picture(图片)】的数据库操作Service实现
 * @createDate 2025-02-07 16:09:35
 */
@Service
public class PictureServiceImpl extends ServiceImpl<PictureMapper, Picture>
        implements PictureService {


    @Resource
    private PictureConfig pictureConfig;

    @Resource
    private UserService userService;

    @Resource
    private PictureManager pictureManager;


    /**
     * 上传 图片
     *
     * @param file             文件
     * @param uploadPictureDto 上传图片的参数
     * @param request          请求
     * @return 返回上传图片的vo
     */
    @Override
    public UploadPictureVo uploadPicture(MultipartFile file,
                                         UploadPictureDto uploadPictureDto,
                                         HttpServletRequest request) {

        // 1. 校验参数
        if (file == null || uploadPictureDto == null) {
            throw new BusinessException(ErrorEnums.PARAMS_ERROR, "参数不能为空");
        }

        // 2. 校验文件
        verifyPictureFile(file);
        String pictureCategory = uploadPictureDto.getPictureCategory();

        // 3. 封装数据库数据
        Picture picture = new Picture();
        UserVo userVo = userService.userGetLoginInfo(request);

        // 替换原本文件名 改成随机生成 uuid
        // 生成随机文件名
        String pictureName = UUID.randomUUID().toString().replaceAll("-", "");

        // 组装物理存放路径
        // 父目录 + 类别目录 + 加上用户id
        // 类别目录
        String categoryDir = getCategoryDir(pictureCategory);
        // 物理存放路径
        String picturePath = pictureConfig.getUploadDir() + categoryDir + userVo.getId();

        // 组装 访问路径
        // 服务器ip + 类别目录 + 加上用户id + 文件
        String pictureUrl = pictureConfig.getHttpUrl() + categoryDir + userVo.getId() + "/" + pictureName + "." + getFileSuffix(Objects.requireNonNull(file.getOriginalFilename()));
        picture.setPictureUrl(pictureUrl);
        picture.setPicturePath(picturePath);
        // 目前 是本地
        picture.setPictureUpload(PictureConstant.PICTURE_UPLOAD_LOCAL);
        picture.setPictureName(pictureName);
        picture.setPictureCategory(pictureCategory);
        picture.setPictureSize(file.getSize());
        picture.setPictureFormat(getFileSuffix(Objects.requireNonNull(file.getOriginalFilename())));
        picture.setCreateUserId(userVo.getId());

        // 4. 异步保存文件
        pictureManager.uploadPicture(file, picturePath,pictureName + "." + getFileSuffix(Objects.requireNonNull(file.getOriginalFilename())));

        // 5. 保存数据库
        this.save(picture);

        // 6. 封装返回结果,并且返回
        UploadPictureVo uploadPictureVo = new UploadPictureVo();
        uploadPictureVo.setId(picture.getId());
        uploadPictureVo.setPictureUrl(picture.getPictureUrl());

        return uploadPictureVo;
    }


    /**
     * 获取文件后缀
     *
     * @param originalFilename 文件名
     * @return 后缀
     */
    private String getFileSuffix(String originalFilename) {
        return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
    }


    /**
     * 校验 图片 文件
     *
     * @param file
     */
    private void verifyPictureFile(MultipartFile file) {

        long size = file.getSize();
        String originalFilename = file.getOriginalFilename();
        // 文件后缀
        String suffix = null;
        if (originalFilename != null) {
            suffix = getFileSuffix(originalFilename);
        }

        // 校验大小(mb)
        if (size > 1024L * 1024 * pictureConfig.getMaxFileSize()) {
            throw new BusinessException(ErrorEnums.PARAMS_ERROR, "文件大小不能超过" + pictureConfig.getMaxFileSize() + "mb");
        }

        // 校验后缀
        if (!pictureConfig.getAllowedFileTypes().contains(suffix)) {
            throw new BusinessException(ErrorEnums.PARAMS_ERROR, "文件后缀名不合法");
        }

    }


    /**
     * 获取类别目录
     *
     * @param pictureCategory 类别
     * @return 目录
     */
    private String getCategoryDir(String pictureCategory) {

        if (PictureConstant.PICTURE_CATEGORY_ARTICLE.equals(pictureCategory)) {
            return pictureConfig.getBlogDir();
        }
        if (PictureConstant.PICTURE_CATEGORY_AVATAR.equals(pictureCategory)) {
            return pictureConfig.getAvatarDir();
        }
        return pictureConfig.getOtherDir();
    }


}




