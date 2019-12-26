package org.nuaa.wa.waelder.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nuaa.wa.waelder.aop.Permission;
import org.nuaa.wa.waelder.aop.ServiceLog;
import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.service.DeviceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Name: AdminDeviceLogController
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-14 02:55
 * @Version: 1.0
 */
@RestController
@RequestMapping("/admin/api/device/log")
@CrossOrigin
@Api("管理端设备日志数据接口")
public class AdminDeviceLogController {
    @Autowired
    private DeviceLogService deviceLogService;

    @GetMapping
    @ApiOperation(value = "get device log", notes = "获取设备日志")
    @ServiceLog(name = "getDeviceLog", description = "get device log")
//    @Permission("admin")
    public Response getDeviceLog(int page, int limit) {
        return deviceLogService.getDeviceLogList(page, limit);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete device log", notes = "删除设备日志")
    @ServiceLog(name = "deleteDeviceLog", description = "delete device log")
//    @Permission("admin")
    public Response deleteDeviceLog(@PathVariable(name = "id") long id) {
        return deviceLogService.removeDeviceLog(id);
    }
}
