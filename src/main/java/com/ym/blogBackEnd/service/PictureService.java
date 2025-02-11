package com.ym.blogBackEnd.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ym.blogBackEnd.model.domain.Picture;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ym.blogBackEnd.model.domain.User;
import com.ym.blogBackEnd.model.dto.picture.UploadPictureDto;
import com.ym.blogBackEnd.model.dto.picture.admin.AdminPagePictureDto;
import com.ym.blogBackEnd.model.vo.picture.PictureVo;
import com.ym.blogBackEnd.model.vo.picture.UploadPictureVo;
import com.ym.blogBackEnd.model.vo.user.UserVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 54621
 * @description 针对表【picture(图片)】的数据库操作Service
 * @createDate 2025-02-07 16:09:35
 */
public interface PictureService extends IService<Picture> {


    /**
     * 上传 图片
     *
     * @param file             文件
     * @param uploadPictureDto 上传图片的参数
     * @param request          请求
     * @return 返回上传图片的vo
     */
    UploadPictureVo uploadPicture(MultipartFile file,
                                  UploadPictureDto uploadPictureDto,
                                  HttpServletRequest request);


    /**
     * 使用 图片
     *
     * @param pictureId 使用图片的id
     * @param request   请求
     * @return 返回是否编辑成功
     */
    Boolean usedPicture(Long pictureId, HttpServletRequest request);


    /**
     * 管理员分页查询图片
     *
     * @param adminPagePictureDto 分页参数
     * @return 返回图片分页结果
     */
    Page<PictureVo> adminPicturePage(AdminPagePictureDto adminPagePictureDto);


    /**
     * 图片 转换为 图片脱敏信息
     *
     * @param picture 图片
     * @return 图片脱敏信息
     */
    public PictureVo pictureToVo(Picture picture);

    /**
     * 图片列表 转换为 图片脱敏信息列表
     *
     * @param pictures 图片列表
     * @return 图片脱敏信息列表
     */
    List<PictureVo> pictureListToVos(List<Picture> pictures);
}
