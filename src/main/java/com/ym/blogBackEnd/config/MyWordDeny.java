package com.ym.blogBackEnd.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import com.github.houbb.sensitive.word.api.IWordDeny;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title: MyWordDeny
 * @Author YunMao
 * @Package com.ym.blogBackEnd.config
 * @Date 2025/4/4 20:35
 * @description: 自定义 黑名单
 */
@Component
@Slf4j
public class MyWordDeny implements IWordDeny {
    @Override
    public List<String> deny() {
        List<String> list = new ArrayList<>();
        String fileName = "mySensitiveWords";

        List<URL> resources = ResourceUtil.getResources(fileName);
        if (CollUtil.isEmpty(resources)) {
            log.error("敏感词文件不存在");
            return null;
        }
        URL resource = resources.get(0);
        InputStream inputStream = null;
        try {
            inputStream = resource.openStream();
            list = IoUtil.readLines(inputStream, StandardCharsets.UTF_8, new ArrayList<>());
        } catch (IOException e) {
            log.error("创建流错误");
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                log.error("关闭流失败");
            }
        }


        return list;
    }

}
