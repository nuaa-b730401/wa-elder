package org.nuaa.wa.waelder.service.impl;

import com.google.common.base.Preconditions;
import org.nuaa.wa.waelder.entity.DeviceEntity;
import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.repository.DeviceRepository;
import org.nuaa.wa.waelder.repository.UserRepository;
import org.nuaa.wa.waelder.service.DeviceLogService;
import org.nuaa.wa.waelder.service.DeviceService;
import org.nuaa.wa.waelder.util.constant.LogLevel;
import org.nuaa.wa.waelder.util.constant.StatusConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Name: DeviceServiceImpl
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-11-07 19:59
 * @Version: 1.0
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    private static Logger logger = LoggerFactory.getLogger(LogLevel.SERVICE);

    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DeviceLogService deviceLogService;

    @Override
    public Response registerDevice(DeviceEntity device) {
        try {
            // TODO : check user exists
            // TODO : check device address
            device.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            DeviceEntity deviceEntity = deviceRepository.save(device);
            deviceLogService.addDeviceLog(
                    deviceEntity.getId(), device.getDeviceName(),
                    StatusConstant.DEVICE_ADD_TYPE, "新增"
            );
        } catch (Exception ex) {
            logger.warn("register device fail, msg : ", ex.getMessage());
            return new Response(Response.NORMAL_ERROR_CODE, "register device fail");
        }

        return new Response(Response.SUCCESS_CODE, "register device success");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Response modifyDevice(DeviceEntity device) {
        try {
            deviceRepository.updateDeviceInfo(
                    device.getDeviceName(),
                    device.getDeviceAddress(),
                    device.getDeviceType(),
                    device.getTemplateId(),
                    new Timestamp(System.currentTimeMillis()),
                    device.getId()
            );
            deviceLogService.addDeviceLog(
                    device.getId(), device.getDeviceName(),
                    StatusConstant.DEVICE_UPDATE_TYPE, "更新"
            );
        } catch (Exception ex) {
            logger.warn("update device fail, msg : {}", ex.getMessage());
            return new Response(Response.NORMAL_ERROR_CODE, "update device fail");
        }

        return new Response(Response.SUCCESS_CODE, "update device success");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Response removeDevice(long id) {
        try {
            deviceLogService.addDeviceLog(
                    id, getDeviceName(id),
                    StatusConstant.DEVICE_DELETE_TYPE, "删除"
            );
            // TODO : pre check
            deviceRepository.deleteById(id);

        } catch (Exception ex) {
            logger.warn("remove device fail, msg : {}", ex.getMessage());
            return new Response(Response.NORMAL_ERROR_CODE, "remove device fail");
        }
        return new Response(Response.SUCCESS_CODE, "remove device success");
    }

    @Override
    public Response getDeviceInfo(long id) {
        try {
            DeviceEntity device = deviceRepository.findById(id).orElseGet(() -> null);
            Preconditions.checkNotNull(device, "device not exists");

            return new Response<DeviceEntity>(
                    Response.SUCCESS_CODE,
                    "get device info success",
                    device
            );
        } catch (Exception ex) {
            logger.warn("get device info fail, ex : {}", ex.getMessage());
            return new Response(Response.SERVER_ERROR_CODE, ex.getMessage());
        }
    }

    @Override
    public Response getUserDeviceList(long userId) {
        List<DeviceEntity> deviceEntityList = deviceRepository.getDeviceEntitiesByUserId(userId);
        return new Response<DeviceEntity>(
                Response.SUCCESS_CODE,
                "get device list by user id success",
                deviceEntityList
        );
    }

    @Override
    public Response getDeviceList(int page, int limit) {
        return new Response<DeviceEntity>(
                Response.SUCCESS_CODE,
                "get device list",
                deviceRepository.getDeviceEntitiesPageable(
                    limit, (page - 1) * limit
                ),
                deviceRepository.count()
        );
    }

    @Override
    public Response getDeviceByType(int type, int page, int limit) {
        return null;
    }

    @Override
    public Response getDeviceByKey(String key, int page, int limit) {
        return null;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Response loginDevice(long id) {
        try {
            deviceRepository.updateDeviceStatus(
                    StatusConstant.DEVICE_ONLINE_STATUS,
                    id
            );

            DeviceEntity device = deviceRepository.findById(id).orElseGet(() -> null);

            deviceLogService.addDeviceLog(
                    id, getDeviceName(id),
                    StatusConstant.DEVICE_ONLINE_STATUS, "上线"
            );

            return new Response<DeviceEntity>(Response.SUCCESS_CODE, "device offline success", device);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Response offlineDevice(long id) {
        try {
            deviceRepository.updateDeviceStatus(
                    StatusConstant.DEVICE_OFFLINE_STATUS,
                    id
            );
            deviceLogService.addDeviceLog(
                    id, getDeviceName(id),
                    StatusConstant.DEVICE_OFFLINE_TYPE, "离线"
            );
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return new Response(Response.SUCCESS_CODE, "device offline success");
    }

    @Override
    public long getDeviceUser(long id) {
        try {
            return deviceRepository.getDeviceUser(id);
        } catch (Exception ex) {
            logger.warn("get device {} user fail, ex : {}", id, ex.getMessage());
        }
        return 0;
    }

    private String getDeviceName(long id) {
        return deviceRepository.getDeviceName(id);
    }
}
