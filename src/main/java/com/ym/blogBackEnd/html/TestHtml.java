package com.ym.blogBackEnd.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author 云猫
 * @date 2025/3/3 12:39
 * @description 测试 爬虫
 */
public class TestHtml {


    public static void test() throws IOException {

        Document document = Jsoup.connect("https://blog.anheyu.com/").get();
        Element elementById = document.getElementById("skills-tags-group-all");
        Element element = elementById.getElementsByClass("tags-group-wrapper").first();
        for (Element elementsByClass : element.getElementsByClass("tags-group-icon-pair")) {
            Elements img = elementsByClass.getElementsByTag("img");
            for (Element imgElement : img) {
                System.out.println(imgElement.attr("data-lazy-src"));
            }
        }


    }


}
