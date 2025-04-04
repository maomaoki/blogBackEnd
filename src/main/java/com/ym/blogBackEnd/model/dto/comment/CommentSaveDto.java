package com.ym.blogBackEnd.model.dto.comment;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Title: CommentSaveDto
 * @Author YunMao
 * @Package com.ym.blogBackEnd.model.dto.comment
 * @Date 2025/2/20 21:40
 * @description: 评论保存请求dto
 */
@Data
public class CommentSaveDto implements Serializable {


    /**
     * 文章ID,用于关联文章
     */
    private Long articleId;

    /**
     * 评论内容
     */
    private String content;



    /**
     * 评论定位地址(可以前端定位 也可以后端定位)
     */
    private String commentPositionAddress;

    /**
     * 评论的设备
     */
    private String commentDevice;

    /**
     * 评论的浏览器设备
     */
    private String commentBrowserDevice;


    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
