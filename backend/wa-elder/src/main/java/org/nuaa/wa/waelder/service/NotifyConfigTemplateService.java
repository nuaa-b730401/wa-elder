package org.nuaa.wa.waelder.service;

import org.nuaa.wa.waelder.entity.NotifyConfigTemplateEntity;
import org.nuaa.wa.waelder.entity.Response;

/**
 * @Name: NotifyConfigTemplateService
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-11-09 16:40
 * @Version: 1.0
 */
public interface NotifyConfigTemplateService {
    Response createTemplate(NotifyConfigTemplateEntity template);

    Response getTemplateById(long id);

    Response updateTemplate(NotifyConfigTemplateEntity template);

    Response removeTemplate(long id);

    Response getTemplateSimpleList(long userId);

    Response getTemplateList(long userId);

    Response getTemplateListAll(int page, int limit);

    Response getTemplateByKey(String key, int page, int limit);
}
