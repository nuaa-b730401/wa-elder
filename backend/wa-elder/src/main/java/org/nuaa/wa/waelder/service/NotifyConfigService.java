package org.nuaa.wa.waelder.service;

import org.nuaa.wa.waelder.entity.NotifyConfigEntity;
import org.nuaa.wa.waelder.entity.Response;

/**
 * @Name: NotifyConfigService
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-11-09 17:02
 * @Version: 1.0
 */
public interface NotifyConfigService {
    Response createConfig(NotifyConfigEntity notifyConfigEntity);

    Response getConfigById(long deviceId, long templateId);

    Response updateConfig(long deviceId, long templateId);

    Response removeConfig(long deviceId, long templateId);
}
