package org.nuaa.wa.waelder.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nuaa.wa.waelder.aop.ServiceLog;
import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Name: SystemController
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-20 21:19
 * @Version: 1.0
 */
@RestController
@RequestMapping("/admin/api/sys")
@CrossOrigin
@Api("系统数据管理")
public class SystemController {
    @Autowired
    private SystemService systemService;

    @GetMapping("/data")
//    @Permission("admin")
    @ServiceLog(name = "systemData", description = "system data")
    @ApiOperation(value = "get system data", notes = "获取系统数据")
    public Response systemData() {
        return systemService.systemInfoData();
    }

}
