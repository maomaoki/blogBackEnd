package com.ym.blogBackEnd.api.client;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ym.blogBackEnd.api.model.news.dto.NewsDto;
import com.ym.blogBackEnd.api.model.news.vo.NewsVo;
import com.ym.blogBackEnd.api.response.NewsResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * 新闻 api
 */
@Slf4j
@Component
public class NewsApi {

    @Resource
    private OkHttpClient client;

    public List<NewsVo> getNewsList(NewsDto newsDto) {
        String id = newsDto.getId();
        Integer size = newsDto.getSize();

        // 2. 构建请求
        Request request = new Request.Builder()
                .url("https://api.codelife.cc/api/top/list?lang=cn&id=" + id + "&size=" + size)
                .addHeader("Origin", "https://go.itab.link")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36")
                .addHeader("Signaturekey", "U2FsdGVkX18/Rdr21WIfAjK39w038GvHjvcitqUXPRQ=")
                .build();

        // 3. 发送请求并处理响应
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful() && response.body() == null) {
                log.error("请求体错误");
                return null;
            }

            String responseStr = response.body().string();
            NewsResponse newsResponse = JSONUtil.toBean(responseStr, NewsResponse.class);
            if (newsResponse.getCode() != 200) {
                log.error("请求错误: {}", newsResponse.getMsg());
                return null;
            }
            return Convert.convert(new TypeReference<List<NewsVo>>() {
            }, newsResponse.getData());

        } catch (IOException e) {
            log.error("请求失败: {}", String.valueOf(e));
        }
        return null;
    }

}
