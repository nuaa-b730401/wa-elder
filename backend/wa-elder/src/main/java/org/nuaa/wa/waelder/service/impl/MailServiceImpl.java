package org.nuaa.wa.waelder.service.impl;

import org.nuaa.wa.waelder.client.MailClient;
import org.nuaa.wa.waelder.entity.MailEntity;
import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.entity.SysLogEntity;
import org.nuaa.wa.waelder.repository.SysLogRepository;
import org.nuaa.wa.waelder.service.MailService;
import org.nuaa.wa.waelder.util.constant.LogLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * @Name: MailServiceImpl
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-19 21:24
 * @Version: 1.0
 */
@Service
public class MailServiceImpl implements MailService {
    private static Logger logger = LoggerFactory.getLogger(LogLevel.SERVICE);

    @Autowired
    private MailClient mailClient;
    @Autowired
    private SysLogRepository sysLogRepository;

    @Override
    public Response sendWishMail(String content) {
        logger.info("christmas wish from her : {}", content);
        SysLogEntity entity = new SysLogEntity();
        entity.setDescription(content);
        entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        entity.setTarget("wish");
        sysLogRepository.save(entity);

        MailEntity mailEntity = new MailEntity();
        mailEntity.setContent(content);
        mailEntity.setRecipient("1121584497@qq.com");
        mailEntity.setSubject("her wish");
        mailClient.sendSimpleMail(mailEntity);
        return new Response(Response.SUCCESS_CODE, "wish success");
    }
}
