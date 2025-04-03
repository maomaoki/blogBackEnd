package com.ym.blogBackEnd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ym.blogBackEnd.model.domain.Comment;
import com.ym.blogBackEnd.service.CommentService;
import com.ym.blogBackEnd.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author 54621
* @description 针对表【comment(评论表)】的数据库操作Service实现
* @createDate 2025-04-03 21:38:37
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




