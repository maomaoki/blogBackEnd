package com.ym.blogBackEnd.model.dto.picture;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ym.blogBackEnd.constant.PictureConstant;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: UploadPictureDto
 * @Author YunMao
 * @Package com.ym.blogBackEnd.model.dto.picture
 * @Date 2025/2/7 16:11
 * @description: 上传图片参数
 */
@Data
public class UploadPictureDto implements Serializable {
    /**
     * 分类 默认 头像
     */
    private String pictureCategory = PictureConstant.PICTURE_CATEGORY_AVATAR;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
