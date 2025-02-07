package com.ym.blogBackEnd.model.vo.picture;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: UploadPictureVi
 * @Author YunMao
 * @Package com.ym.blogBackEnd.model.vo.picture
 * @Date 2025/2/7 16:16
 * @description: 上传图片结果
 */
@Data
public class UploadPictureVo implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 图片 url
     */
    private String pictureUrl;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
