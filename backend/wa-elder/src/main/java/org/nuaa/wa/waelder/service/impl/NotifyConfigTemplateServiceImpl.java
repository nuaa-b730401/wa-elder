package org.nuaa.wa.waelder.service.impl;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.nuaa.wa.waelder.entity.NotifyConfigTemplateEntity;
import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.repository.NotifyConfigTemplateRepository;
import org.nuaa.wa.waelder.service.NotifyConfigTemplateService;
import org.nuaa.wa.waelder.util.constant.LogLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Name: NotifyConfigTemplateServiceImpl
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-11-09 16:44
 * @Version: 1.0
 */
@Service
public class NotifyConfigTemplateServiceImpl implements NotifyConfigTemplateService {

    private static Logger logger = LoggerFactory.getLogger(LogLevel.SERVICE);

    @Autowired
    private NotifyConfigTemplateRepository notifyConfigTemplateRepository;

    @Override
    public Response createTemplate(NotifyConfigTemplateEntity template) {
        try {
            notifyConfigTemplateRepository.save(template);
        } catch (Exception ex) {
            logger.warn("create notify config template fail, ex : {}", ex.getMessage());
            return new Response(Response.SERVER_ERROR_CODE, "create template fail");
        }

        return new Response(Response.SUCCESS_CODE, "create template success");
    }

    @Override
    public Response getTemplateById(long id) {
        try {
            NotifyConfigTemplateEntity notifyConfigTemplateEntity =
                    notifyConfigTemplateRepository.findById(id).orElseGet(() -> null);

            Preconditions.checkNotNull(notifyConfigTemplateEntity);

            return new Response<NotifyConfigTemplateEntity>(
                    Response.SUCCESS_CODE,
                    "get template success",
                    notifyConfigTemplateEntity
            );
        } catch (Exception ex) {
            logger.warn("template not exists, ex : {}", ex.getMessage());
            return new Response(Response.SERVER_DATA_NOT_FOUND_ERROR, "template not exists");
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Response updateTemplate(NotifyConfigTemplateEntity template) {
        try {
            template.setLevel(judgeLevel(template.getPhone(), template.getWechatId()));
            notifyConfigTemplateRepository.updateNotifyConfigTemplate(
                    template.getPhone(),
                    template.getWechatId(),
                    template.getLevel(),
                    template.getTemplateName(),
                    template.getId()
            );
        } catch (Exception ex) {
            logger.warn("template(id={}) update fail, ex : {}", template.getId(), ex.getMessage());
            return new Response(
                    Response.SERVER_ERROR_CODE,
                    "template update fail"
            );
        }
        return new Response(
                Response.SUCCESS_CODE, "template update success"
        );
    }

    @Override
    public Response removeTemplate(long id) {
        try {
            notifyConfigTemplateRepository.deleteById(id);
        } catch (Exception ex) {
            logger.warn("template(id={}) update fail, ex : {}", id, ex.getMessage());
            return new Response(
                    Response.SERVER_ERROR_CODE,
                    "template delete fail"
            );
        }
        return new Response(
                Response.SUCCESS_CODE, "template delete success"
        );
    }

    @Override
    public Response getTemplateSimpleList(long userId) {
        return new Response<NotifyConfigTemplateEntity>(
                Response.SUCCESS_CODE,
                "get list success",
                notifyConfigTemplateRepository.getSimpleList(userId)
        );
    }

    @Override
    public Response getTemplateList(long userId) {
        return new Response<NotifyConfigTemplateEntity>(
                Response.SUCCESS_CODE,
                "get list success",
                notifyConfigTemplateRepository.findAllByUserId(userId)
        );
    }

    @Override
    public Response getTemplateListAll(int page, int limit) {
        return new Response<NotifyConfigTemplateEntity>(
                Response.SUCCESS_CODE,
                "get list success",
                notifyConfigTemplateRepository.getAllTemplate(
                        limit, (page - 1) * limit
                )
        );
    }

    @Override
    public Response getTemplateByKey(String key, int page, int limit) {
        return null;
    }

    private int judgeLevel(String phoneNumber, String wechatId) {
        int phoneFlag = StringUtils.isEmpty(phoneNumber) ? 0 : 1;
        int wechatFlag = StringUtils.isEmpty(wechatId) ? 0 : 1;

        return phoneFlag + (wechatFlag << 1);
    }
}
