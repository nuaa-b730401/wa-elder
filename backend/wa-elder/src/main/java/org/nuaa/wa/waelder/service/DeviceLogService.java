package org.nuaa.wa.waelder.service;

import org.nuaa.wa.waelder.entity.Response;

/**
 * @Name: DeviceLogService
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-14 02:48
 * @Version: 1.0
 */
public interface DeviceLogService {
    void addDeviceLog(long deviceId, String deviceName, int type, String description);

    Response getDeviceLogList(int page, int limit);

    Response removeDeviceLog(long id);
}
