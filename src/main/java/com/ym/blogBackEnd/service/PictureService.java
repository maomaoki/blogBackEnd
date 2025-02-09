package com.ym.blogBackEnd.service;

import com.ym.blogBackEnd.model.domain.Picture;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ym.blogBackEnd.model.dto.picture.UploadPictureDto;
import com.ym.blogBackEnd.model.vo.picture.UploadPictureVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
* @author 54621
* @description 针对表【picture(图片)】的数据库操作Service
* @createDate 2025-02-07 16:09:35
*/
public interface PictureService extends IService<Picture> {


    /**
     * 上传 图片
     * @param file 文件
     * @param uploadPictureDto 上传图片的参数
     * @param request 请求
     * @return 返回上传图片的vo
     */
    UploadPictureVo uploadPicture(MultipartFile file ,
                                  UploadPictureDto uploadPictureDto,
                                  HttpServletRequest request);


    /**
     * 使用 图片
     * @param pictureId 使用图片的id
     * @param request 请求
     * @return 返回是否编辑成功
     */
    Boolean usedPicture(Long pictureId,HttpServletRequest request);

}
