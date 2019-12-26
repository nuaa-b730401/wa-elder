package org.nuaa.wa.waelder.service;

import org.nuaa.wa.waelder.entity.AlarmEntity;
import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.entity.SysAlarmEntity;

/**
 * @Name: SysAlarmService
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-14 05:44
 * @Version: 1.0
 */
public interface SysAlarmService {
    Response alarm(AlarmEntity alarmEntity);

    Response getAlarm(int page, int limit);

    Response getUserAlarm(long userId);

    Response deleteAlarm(long id);
}
