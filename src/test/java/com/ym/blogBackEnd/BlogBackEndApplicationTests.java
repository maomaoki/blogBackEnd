package com.ym.blogBackEnd;

import cn.hutool.core.collection.CollUtil;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.core.SensitiveWordHelper;
import com.ym.blogBackEnd.api.client.NewsApi;
import com.ym.blogBackEnd.api.model.news.dto.NewsDto;
import com.ym.blogBackEnd.api.model.news.vo.NewsVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Slf4j
@SpringBootTest
class BlogBackEndApplicationTests {

    @Resource
    private SensitiveWordBs sensitiveWordBs;

    @Test
    void contextLoads(){

        String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前,操,草,尼@!@$##玛,逼";
        System.out.println(sensitiveWordBs.replace(text));

    }

}
