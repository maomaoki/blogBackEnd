package com.ym.blogBackEnd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ym.blogBackEnd.mapper") //扫描mapper
public class BlogBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogBackEndApplication.class, args);
    }

}
