package org.nuaa.wa.waelder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nuaa.wa.waelder.entity.Response;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Name: NotifyController
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-11-17 15:08
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/notify")
@CrossOrigin
@Api("消息通知接口")
public class NotifyController {

    @GetMapping
    @ApiOperation(value = "alarm notify", notes = "预警通知")
    public Response alarmNotify(long deviceId, long time) {
        return null;
    }

    @GetMapping("/sms-alarm")
    @ApiOperation(value = "sms alarm notify", notes = "短信预警通知")
    public Response smsAlarmNotify(long deviceId, long time) {
        return null;
    }

    @GetMapping("/wechat-alarm")
    @ApiOperation(value = "wechat alarm notify", notes = "微信预警通知")
    public Response wechatAlarmNotify(long deviceId, long time) {
        return null;
    }
}
