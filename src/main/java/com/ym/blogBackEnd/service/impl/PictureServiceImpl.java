package com.ym.blogBackEnd.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ym.blogBackEnd.config.PictureConfig;
import com.ym.blogBackEnd.constant.PictureConstant;
import com.ym.blogBackEnd.enums.ErrorEnums;
import com.ym.blogBackEnd.exception.BusinessException;
import com.ym.blogBackEnd.manager.picture.PictureManager;
import com.ym.blogBackEnd.model.domain.Picture;
import com.ym.blogBackEnd.model.domain.User;
import com.ym.blogBackEnd.model.dto.picture.UploadPictureDto;
import com.ym.blogBackEnd.model.dto.picture.admin.AdminPagePictureDto;
import com.ym.blogBackEnd.model.vo.picture.PictureVo;
import com.ym.blogBackEnd.model.vo.picture.UploadPictureVo;
import com.ym.blogBackEnd.model.vo.user.UserVo;
import com.ym.blogBackEnd.service.PictureService;
import com.ym.blogBackEnd.mapper.PictureMapper;
import com.ym.blogBackEnd.service.UserService;
import com.ym.blogBackEnd.utils.ThrowUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

        // 校验权限,目前只有管理员才能上传其他照片,其他只能上传头像
        if (!userService.isAdmin(userVo)) {
            // 不是管理员,这里直接修改成 头像,不影响下面代码
            pictureCategory = PictureConstant.PICTURE_CATEGORY_AVATAR;
        }


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
        pictureManager.uploadPicture(file, picturePath, pictureName + "." + getFileSuffix(Objects.requireNonNull(file.getOriginalFilename())));

        // 5. 保存数据库
        this.save(picture);

        // 6. 封装返回结果,并且返回
        UploadPictureVo uploadPictureVo = new UploadPictureVo();
        BeanUtil.copyProperties(picture, uploadPictureVo);
        return uploadPictureVo;
    }


    /**
     * 使用 图片
     *
     * @param pictureId 使用图片的id
     * @param request   请求
     * @return 返回是否编辑成功
     */
    @Override
    public Boolean usedPicture(Long pictureId, HttpServletRequest request) {
        // 1. 校验参数
        if (pictureId == null) {
            throw new BusinessException(ErrorEnums.PARAMS_ERROR, "图片id不能为空");
        }

        // 2. 校验登录
        UserVo userVo = userService.userGetLoginInfo(request);
        if (userVo == null) {
            throw new BusinessException(ErrorEnums.NOT_AUTH, "未登录");
        }

        // 3. 封装修改数据
        Picture picture = new Picture();
        picture.setId(pictureId);
        picture.setUsedUserId(userVo.getId());
        picture.setEditTime(new Date());

        // 4. 更新操作
        this.updateById(picture);
        return true;
    }


    /**
     * 管理员分页查询图片
     *
     * @param adminPagePictureDto 分页参数
     * @return 返回图片分页结果
     */
    @Override
    public Page<PictureVo> adminPicturePage(AdminPagePictureDto adminPagePictureDto) {
        // 1. 校验参数
        ThrowUtils.ifThrow(
                adminPagePictureDto == null,
                ErrorEnums.PARAMS_ERROR,
                "查询参数不能为空!"
        );


        // 2. 封装查询条件
        QueryWrapper<Picture> queryWrapper = queryWrapper(adminPagePictureDto);


        // 3. 查询
        Integer pageSize = adminPagePictureDto.getPageSize();
        Integer pageNum = adminPagePictureDto.getPageNum();

        Page<Picture> page = this.page(new Page<>(pageNum, pageSize), queryWrapper);

        // 4. 封装返回结果
        Page<PictureVo> pictureVoPage = new Page<>(pageNum, pageSize, page.getTotal());
        pictureVoPage.setRecords(pictureListToVos(page.getRecords()));
        return pictureVoPage;
    }

    /**
     * 封装查询条件
     *
     * @param adminPagePictureDto 分页参数
     * @return 查询条件
     */
    private QueryWrapper<Picture> queryWrapper(AdminPagePictureDto adminPagePictureDto) {
        ThrowUtils.ifThrow(
                adminPagePictureDto == null,
                ErrorEnums.PARAMS_ERROR,
                "请求参数不能为空"
        );


        Long id = adminPagePictureDto.getId();
        String pictureUpload = adminPagePictureDto.getPictureUpload();
        String pictureName = adminPagePictureDto.getPictureName();
        String pictureCategory = adminPagePictureDto.getPictureCategory();
        String pictureTags = adminPagePictureDto.getPictureTags();
        Long pictureSize = adminPagePictureDto.getPictureSize();
        String pictureFormat = adminPagePictureDto.getPictureFormat();
        Long createUserId = adminPagePictureDto.getCreateUserId();
        Integer reviewStatus = adminPagePictureDto.getReviewStatus();
        Date reviewStartTime = adminPagePictureDto.getReviewStartTime();
        Date reviewEndTime = adminPagePictureDto.getReviewEndTime();
        Date createStartTime = adminPagePictureDto.getCreateStartTime();
        Date createEndTime = adminPagePictureDto.getCreateEndTime();
        String sortField = adminPagePictureDto.getSortField();
        String sortOrder = adminPagePictureDto.getSortOrder();


        QueryWrapper<Picture> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq(ObjUtil.isNotNull(id), "id", id);
        // 尺寸 应该是 小于或者等于
        userQueryWrapper.ge(ObjUtil.isNotNull(pictureSize), "pictureSize", pictureSize);
        userQueryWrapper.eq(ObjUtil.isNotNull(createUserId), "createUserId", createUserId);
        userQueryWrapper.eq(ObjUtil.isNotNull(reviewStatus), "reviewStatus", reviewStatus);

        userQueryWrapper.eq(StrUtil.isNotBlank(pictureUpload), "pictureUpload", pictureUpload);
        userQueryWrapper.eq(StrUtil.isNotBlank(pictureCategory), "pictureCategory", pictureCategory);
        userQueryWrapper.eq(StrUtil.isNotBlank(pictureFormat), "pictureFormat", pictureFormat);

        userQueryWrapper.like(StrUtil.isNotBlank(pictureName), "pictureName", pictureName);


        // 标签 需要 拼接查询
        if (StrUtil.isNotBlank(pictureTags)) {
            List<String> tagsList = JSONUtil.toList(pictureTags, String.class);
            tagsList.forEach(tag -> userQueryWrapper.like("pictureTags", "\"" + tag + "\""));
        }


        // 时间范围
        userQueryWrapper.between(ObjUtil.isNotNull(reviewStartTime) && ObjUtil.isNotNull(reviewEndTime)
                , "reviewTime", reviewStartTime, reviewEndTime);

        userQueryWrapper.between(ObjUtil.isNotNull(createStartTime) && ObjUtil.isNotNull(createEndTime)
                , "createTime", createStartTime, createEndTime);

        // 排序字段
        userQueryWrapper.orderBy(StrUtil.isNotBlank(sortField), "asc".equals(sortOrder), sortField);
        return userQueryWrapper;
    }


    /**
     * 图片 转换为 图片脱敏信息
     *
     * @param picture 图片
     * @return 图片脱敏信息
     */
    @Override
    public PictureVo pictureToVo(Picture picture) {
        if (picture == null) {
            return null;
        }
        PictureVo pictureVo = new PictureVo();
        BeanUtil.copyProperties(picture, pictureVo);
        return pictureVo;
    }


    /**
     * 图片列表 转换为 图片脱敏信息列表
     *
     * @param pictures 图片列表
     * @return 图片脱敏信息列表
     */
    @Override
    public List<PictureVo> pictureListToVos(List<Picture> pictures) {
        if (pictures == null) {
            return new ArrayList<>();
        }
        return pictures.stream()
                .map(this::pictureToVo).collect(Collectors.toList());
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
        if (PictureConstant.PICTURE_CATEGORY_BANNER.equals(pictureCategory)) {
            return pictureConfig.getBannerDir();
        }
        return pictureConfig.getOtherDir();
    }


}




