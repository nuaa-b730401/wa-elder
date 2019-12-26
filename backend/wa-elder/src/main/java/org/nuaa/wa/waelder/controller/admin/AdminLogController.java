package org.nuaa.wa.waelder.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nuaa.wa.waelder.aop.Permission;
import org.nuaa.wa.waelder.aop.ServiceLog;
import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Name: AdminLogController
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-14 04:58
 * @Version: 1.0
 */
@RestController
@RequestMapping("/admin/api/log")
@CrossOrigin
@Api("管理端日志数据接口")
public class AdminLogController {

    @Autowired
    private SysLogService sysLogService;

    @GetMapping
    @ApiOperation(value = "get log", notes = "获取日志")
    @ServiceLog(name = "getLog", description = "get log")
//    @Permission("admin")
    public Response getLog(int page, int limit) {
        return sysLogService.getLog(page, limit);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete log", notes = "删除日志")
    @ServiceLog(name = "deleteLog", description = "delete log")
//    @Permission("admin")
    public Response deleteLog(@PathVariable(name = "id") long id) {
        return sysLogService.deleteLog(id);
    }
}
