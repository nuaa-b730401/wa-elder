package org.nuaa.wa.waelder.service;

import org.nuaa.wa.waelder.entity.DeviceEntity;
import org.nuaa.wa.waelder.entity.Response;

/**
 * @Name: DeviceService
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-11-07 19:56
 * @Version: 1.0
 */
public interface DeviceService {

    Response registerDevice(DeviceEntity device);

    Response modifyDevice(DeviceEntity device);

    Response removeDevice(long id);

    Response getDeviceInfo(long id);

    Response getUserDeviceList(long userId);

    Response getDeviceList(int page, int limit);

    Response getDeviceByType(int type, int page, int limit);

    Response getDeviceByKey(String key, int page, int limit);

    Response loginDevice(long id);

    Response offlineDevice(long id);

    long getDeviceUser(long id);

}
