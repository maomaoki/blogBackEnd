package com.ym.blogBackEnd.model.dto.article.admin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: AdminDeleteArticleDto
 * @Author YunMao
 * @Package com.ym.blogBackEnd.model.dto.article.admin
 * @Date 2025/2/11 17:26
 * @description: 管理员删除文章请求类
 */
@Data
public class AdminDeleteArticleDto implements Serializable {
    /**
     * 文章id
     */
    private Long id;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
