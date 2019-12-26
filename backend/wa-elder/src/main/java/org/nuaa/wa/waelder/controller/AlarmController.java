package org.nuaa.wa.waelder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.service.SysAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Name: AlarmController
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-21 11:56
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/alarm")
@CrossOrigin
@Api("告警数据接口")
public class AlarmController {

    @Autowired
    private SysAlarmService sysAlarmService;

    @GetMapping("/user/{userid}")
    @ApiOperation(value = "user alarm data", notes = "用户告警数据")
    public Response getUserAlarmData(@PathVariable(name = "userid") long userId) {
        return sysAlarmService.getUserAlarm(userId);
    }
}
