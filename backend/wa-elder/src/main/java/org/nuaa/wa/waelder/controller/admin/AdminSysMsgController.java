package org.nuaa.wa.waelder.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nuaa.wa.waelder.aop.ServiceLog;
import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.service.SysMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Name: AdminSysMsgController
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-14 05:54
 * @Version: 1.0
 */
@RestController
@RequestMapping("/admin/api/msg")
@CrossOrigin
@Api("管理端消息数据接口")
public class AdminSysMsgController {

    @Autowired
    private SysMsgService sysMsgService;

    @GetMapping
    @ApiOperation(value = "get msg", notes = "获取消息")
    @ServiceLog(name = "getMsg", description = "get msg")
//    @Permission("admin")
    public Response getMsg(int page, int limit) {
        return sysMsgService.getMsg(page, limit);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete msg", notes = "删除消息")
    @ServiceLog(name = "deleteMsg", description = "delete msg")
//    @Permission("admin")
    public Response deleteMsg(@PathVariable(name = "id") long id) {
        return sysMsgService.deleteMsg(id);
    }
}
