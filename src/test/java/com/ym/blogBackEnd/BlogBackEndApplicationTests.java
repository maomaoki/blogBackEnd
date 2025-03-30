package com.ym.blogBackEnd;

import cn.hutool.core.collection.CollUtil;
import com.ym.blogBackEnd.api.client.NewsApi;
import com.ym.blogBackEnd.api.model.news.dto.NewsDto;
import com.ym.blogBackEnd.api.model.news.vo.NewsVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Slf4j
@SpringBootTest
class BlogBackEndApplicationTests {

    @Resource
    private NewsApi newsApi;

    @Test
    void contextLoads(){


    }

}
