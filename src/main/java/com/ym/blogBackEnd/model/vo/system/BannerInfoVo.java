package com.ym.blogBackEnd.model.vo.system;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 54621
 * @date 2025/3/2 20:32
 * @description 主页信息 返回 vo
 */
@Data
public class BannerInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主页 banner 链接
     */
    private String bannerImageUrl;

    /**
     * 主页 banner title
     */
    private String bannerTitle;

    /**
     * 主页 banner printList
     */
    private List<String> printList;

}
