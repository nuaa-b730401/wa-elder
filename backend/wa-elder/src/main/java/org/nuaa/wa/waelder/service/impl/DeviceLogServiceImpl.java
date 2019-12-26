package org.nuaa.wa.waelder.service.impl;

import org.nuaa.wa.waelder.entity.DeviceLogEntity;
import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.repository.DeviceLogRepository;
import org.nuaa.wa.waelder.service.DeviceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;

/**
 * @Name: DeviceLogServiceImpl
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-14 02:50
 * @Version: 1.0
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class DeviceLogServiceImpl implements DeviceLogService {

    @Autowired
    private DeviceLogRepository deviceLogRepository;

    @Override
    public void addDeviceLog(long deviceId, String deviceName, int type, String description) {
        DeviceLogEntity logEntity = new DeviceLogEntity();
        logEntity.setDeviceId(deviceId);
        logEntity.setDeviceName(deviceName);
        logEntity.setType(type);
        logEntity.setDescription(description);
        logEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        deviceLogRepository.save(logEntity);
    }

    @Override
    public Response getDeviceLogList(int page, int limit) {
        return new Response<DeviceLogEntity>(
                Response.SUCCESS_CODE,
                "get device log success",
                deviceLogRepository.listDeviceLog(
                        limit, (page - 1) * limit
                )
        );
    }

    @Override
    public Response removeDeviceLog(long id) {
        deviceLogRepository.deleteById(id);
        return new Response(
                Response.SUCCESS_CODE,
                "remove device log success"
        );
    }
}
