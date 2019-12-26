package org.nuaa.wa.waelder.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nuaa.wa.waelder.aop.ServiceLog;
import org.nuaa.wa.waelder.entity.AlarmEntity;
import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.service.SysAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Name: AdminSysAlarmController
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-14 05:56
 * @Version: 1.0
 */
@RestController
@RequestMapping("/admin/api/alarm")
@CrossOrigin
@Api("管理端警告数据接口")
public class AdminSysAlarmController {
    @Autowired
    private SysAlarmService sysAlarmService;

    @GetMapping("/do")
    @ApiOperation(value = "do alarm", notes = "告警")
    @ServiceLog(name = "doAlarm", description = "do alarm")
    public Response doAlarm(AlarmEntity alarmEntity) {
        return sysAlarmService.alarm(alarmEntity);
    }
    @GetMapping
    @ApiOperation(value = "get alarm", notes = "获取警告")
    @ServiceLog(name = "getAlarm", description = "get alarm")
//    @Permission("admin")
    public Response getAlarm(int page, int limit) {
        return sysAlarmService.getAlarm(page, limit);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete alarm", notes = "删除警告")
    @ServiceLog(name = "deleteAlarm", description = "delete alarm")
//    @Permission("admin")
    public Response deleteAlarm(@PathVariable(name = "id") long id) {
        return sysAlarmService.deleteAlarm(id);
    }
}
