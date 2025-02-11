package com.ym.blogBackEnd.model.dto.article;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: GetArticleByPasswordDto
 * @Author YunMao
 * @Package com.ym.blogBackEnd.model.dto.article
 * @Date 2025/2/11 21:40
 * @description: 加密文章请求类
 */
@Data
public class GetArticleByPasswordDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String password;


}
