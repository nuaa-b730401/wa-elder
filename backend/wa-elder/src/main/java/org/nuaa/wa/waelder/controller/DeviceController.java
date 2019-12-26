package org.nuaa.wa.waelder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nuaa.wa.waelder.aop.Permission;
import org.nuaa.wa.waelder.entity.DeviceEntity;
import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Name: DeviceController
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-11-09 15:56
 * @Version: 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/api/device")
@Api("设备数据操作接口")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @PostMapping
//    @Permission("login")
    @ApiOperation(value = "register new device", notes = "注册一个新的设备")
    public Response registerDevice(DeviceEntity device) {
        return deviceService.registerDevice(device);
    }

    @GetMapping("/{id}")
//    @Permission("query:device:id")
    @ApiOperation(value = "get device by id", notes = "通过id获取device信息")
    public Response getDeviceById(@PathVariable(name = "id") long id) {
        return deviceService.getDeviceInfo(id);
    }

    @PutMapping
    @ApiOperation(value = "update device by id", notes = "更新指定设备数据")
//    @Permission("process:device:id")
    public Response updateDeviceById(DeviceEntity device) {
        return deviceService.modifyDevice(device);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete device by id", notes = "删除指定设备")
//    @Permission("process:device:id")
    public Response deleteDeviceById(@PathVariable(name = "id") long id) {
        return deviceService.removeDevice(id);
    }

    @GetMapping("/user/{userid}")
    @ApiOperation(value = "get device by user id", notes = "获取用户注册的设备列表")
//    @Permission("query:device:userId")
    public Response getDeviceByUserId(@PathVariable(name = "userid") long userId) {
        return deviceService.getUserDeviceList(userId);
    }
}
