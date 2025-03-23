package com.ym.blogBackEnd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ym.blogBackEnd.model.domain.BlogSystem;
import com.ym.blogBackEnd.service.BlogSystemService;
import com.ym.blogBackEnd.mapper.BlogSystemMapper;
import org.springframework.stereotype.Service;

/**
* @author 54621
* @description 针对表【blog_system(博客系统表)】的数据库操作Service实现
* @createDate 2025-03-23 23:49:39
*/
@Service
public class BlogSystemServiceImpl extends ServiceImpl<BlogSystemMapper, BlogSystem>
    implements BlogSystemService{

}




