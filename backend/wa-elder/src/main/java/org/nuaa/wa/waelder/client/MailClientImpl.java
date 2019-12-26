package org.nuaa.wa.waelder.client;

import org.nuaa.wa.waelder.entity.MailEntity;
import org.nuaa.wa.waelder.util.constant.LogLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Name: MailClientImpl
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-19 21:21
 * @Version: 1.0
 */
@Component("mail")
public class MailClientImpl implements MailClient {

    private static Logger logger = LoggerFactory.getLogger(LogLevel.SERVICE);

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    @Async
    public void sendSimpleMail(MailEntity mail) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            //邮件发送人
            simpleMailMessage.setFrom("1121584497@qq.com");
            //邮件接收人
            simpleMailMessage.setTo(mail.getRecipient());
            //邮件主题
            simpleMailMessage.setSubject(mail.getSubject());
            //邮件内容
            simpleMailMessage.setText(mail.getContent());
            javaMailSender.send(simpleMailMessage);
            logger.info("邮件发送成功");
        } catch (Exception e) {
            logger.error("邮件发送失败", e.getMessage());
        }
    }
}
