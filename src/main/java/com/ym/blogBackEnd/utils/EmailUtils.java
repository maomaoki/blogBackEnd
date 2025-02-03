package com.ym.blogBackEnd.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Title: EmailUtils
 * @Author YunMao
 * @Package com.ym.blogBackEnd.utils
 * @Date 2025/2/2 22:49
 * @description: 邮件工具类
 */
@Component
@Data
@Slf4j
@EnableAsync
@ConfigurationProperties(prefix = "spring.mail")
public class EmailUtils {

    private String username;

    @Resource
    private JavaMailSender mailSender;

    /**
     * 异步 发送 邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    @Async
    public void sendEmail(String to, String subject, String content) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

}
