package org.nuaa.wa.waelder.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nuaa.wa.waelder.aop.Permission;
import org.nuaa.wa.waelder.aop.ServiceLog;
import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Name: AdminDeviceController
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-11-17 12:39
 * @Version: 1.0
 */
@RestController
@RequestMapping("/admin/api/device")
@CrossOrigin
@Api("管理端设备数据操作接口")
public class AdminDeviceController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping
//    @Permission("admin")
    @ApiOperation(value = "get device list", notes = "获取设备列表")
    @ServiceLog(name = "getDeviceList", description = "get device list")
    public Response getDeviceList(int page, int limit) {
        return deviceService.getDeviceList(page, limit);
    }

    @GetMapping("/classify/{type}")
//    @Permission("admin")
    @ApiOperation(value = "get device list by type", notes = "获取指定类别设备列表")
    @ServiceLog(name = "get device list classify", description = "get device list classify")
    public Response getDeviceListClassify(@PathVariable(name = "type") int type, int page, int limit) {
        return deviceService.getDeviceByType(type, page, limit);
    }

    @GetMapping("/online/{id}")
//    @Permission("admin")
    @ApiOperation(value = "device online", notes = "设备上线")
    @ServiceLog(name = "deviceOnline", description = "device online")
    public Response deviceOnline(@PathVariable(name = "id") long id) {
        return deviceService.loginDevice(id);
    }

    @GetMapping("/offline/{id}")
//    @Permission("admin")
    @ApiOperation(value = "device offline", notes = "设备下线")
    @ServiceLog(name = "deviceOffline", description = "device offline")
    public Response deviceOffline(@PathVariable(name = "id") long id) {
        return deviceService.offlineDevice(id);
    }
}


