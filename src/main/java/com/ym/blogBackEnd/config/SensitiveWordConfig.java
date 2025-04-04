package com.ym.blogBackEnd.config;

import com.github.houbb.sensitive.word.api.IWordDeny;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;

import com.github.houbb.sensitive.word.support.deny.WordDenys;
import com.github.houbb.sensitive.word.support.ignore.SensitiveWordCharIgnores;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class SensitiveWordConfig {

//    @Resource
//    private MyWordDeny myDdWordDeny;

    /**
     * 初始化引导类
     * @return 初始化引导类
     * @since 1.0.0
     */
    @Bean
    public SensitiveWordBs sensitiveWordBs() {
        IWordDeny wordDeny = WordDenys.chains(WordDenys.defaults(), new MyWordDeny());
        return SensitiveWordBs.newInstance()
                .wordDeny(wordDeny)
                // 忽略大小写
                .ignoreCase(true)
                // 忽略半角圆角
                .ignoreWidth(true)
                // 忽略数字的写法
                .ignoreNumStyle(true)
                // 忽略中文的书写格式：简繁体
                .ignoreChineseStyle(true)
                // 忽略英文的书写格式
                .ignoreEnglishStyle(true)
                // 忽略重复词
                .ignoreRepeat(false)
                // 是否启用数字检测
                .enableNumCheck(true)
                // 是否启用邮箱检测
                .enableEmailCheck(true)
                // 是否启用链接检测
                .enableUrlCheck(true)
                // 数字检测，自定义指定长度
                .numCheckLen(8)
                .charIgnore(SensitiveWordCharIgnores.specialChars())
                // 各种其他配置
                .init();
    }

}