package org.nuaa.wa.waelder.service.impl;

import com.google.common.base.Preconditions;
import org.nuaa.wa.waelder.client.SmsClient;
import org.nuaa.wa.waelder.entity.*;
import org.nuaa.wa.waelder.repository.SysAlarmRepository;
import org.nuaa.wa.waelder.service.DeviceService;
import org.nuaa.wa.waelder.service.NotifyConfigTemplateService;
import org.nuaa.wa.waelder.service.SysAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;

/**
 * @Name: SysAlarmServiceImpl
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-14 05:52
 * @Version: 1.0
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class SysAlarmServiceImpl implements SysAlarmService {

    @Autowired
    private SysAlarmRepository sysAlarmRepository;

    @Autowired
    private SmsClient smsClient;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private NotifyConfigTemplateService notifyConfigTemplateService;

    @Override
    public Response alarm(AlarmEntity alarmEntity) {
        try {
            Response deviceResponse = deviceService.getDeviceInfo(alarmEntity.getDeviceId());
            Preconditions.checkArgument(deviceResponse.getCode() == Response.SUCCESS_CODE, "get device fail");
            DeviceEntity device = (DeviceEntity) deviceResponse.getData();
            Preconditions.checkArgument(alarmEntity.getConfidence() >= device.getConfidence(), "not enough confidence");
            Response nctResponse = notifyConfigTemplateService.getTemplateById(device.getTemplateId());
            Preconditions.checkArgument(nctResponse.getCode() == Response.SUCCESS_CODE, "get nct fail");
            NotifyConfigTemplateEntity nct = (NotifyConfigTemplateEntity) nctResponse.getData();

            SysAlarmEntity sysAlarmEntity = new SysAlarmEntity();
            sysAlarmEntity.setDeviceId(device.getId());
            sysAlarmEntity.setDeviceName(device.getDeviceName());
            sysAlarmEntity.setUserId(device.getUserId());
            sysAlarmEntity.setVideoPath(alarmEntity.getVideoPath());
            sysAlarmEntity.setImagePath(alarmEntity.getImagePath());
            sysAlarmEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
            sysAlarmRepository.save(sysAlarmEntity);

            if ((nct.getLevel() & 0x1) == 1) {
                // 短信通知
                smsClient.sendAlarmMsg(nct.getPhone(), alarmEntity.getVideoPath(), device.getDeviceName());
            }
        } catch (Exception ex) {
            return new Response(Response.SERVER_ERROR_CODE, "alarm fail");
        }
        return new Response(Response.SUCCESS_CODE, "alarm success");
    }

    @Override
    public Response getAlarm(int page, int limit) {
        return new Response<SysAlarmEntity>(
                Response.SUCCESS_CODE,
                "get alarm success",
                sysAlarmRepository.getAlarm(
                        limit, (page - 1) * limit
                )
        );
    }

    @Override
    public Response getUserAlarm(long userId) {
        return new Response<SysAlarmEntity>(
                Response.SUCCESS_CODE,
                "get user alarm success",
                sysAlarmRepository.getUserAlarm(userId)
        );
    }

    @Override
    public Response deleteAlarm(long id) {
        sysAlarmRepository.deleteById(id);
        return new Response(Response.SUCCESS_CODE, "delete alarm success");
    }
}
