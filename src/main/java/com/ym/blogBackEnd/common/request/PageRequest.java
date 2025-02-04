package com.ym.blogBackEnd.common.request;

import lombok.Data;

/**
 * @Title: PageRequest
 * @Author YunMao
 * @Package com.ym.blogBackEnd.common.request
 * @Date 2025/2/4 10:44
 * @description: 分页请求参数
 */
@Data
public class PageRequest {

    /**
     *  页码
     */
    private Integer pageNum = 1;

    /**
     *  条数
     */
    private Integer pageSize = 5;


    /**
     *  排序字段
     */
    private String sortField;

    /**
     *  排序方式
     */
    private String sortOrder;

}
