package org.nuaa.wa.waelder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nuaa.wa.waelder.entity.NotifyConfigTemplateEntity;
import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.service.NotifyConfigTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Name: NotifyConfigTemplateController
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-11-09 17:05
 * @Version: 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/api/nct")
@Api("通知配置管理接口")
public class NotifyConfigTemplateController {
    @Autowired
    private NotifyConfigTemplateService notifyConfigTemplateService;

    @PostMapping
    @ApiOperation(value = "create config", notes = "创建消息通知配置模板")
//    @Permission("login")
    public Response createConfig(NotifyConfigTemplateEntity nct) {
        return notifyConfigTemplateService.createTemplate(nct);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "get nct by id", notes = "获取消息通知配置信息")
//    @Permission("query:nct:id")
    public Response getNctById(@PathVariable(name = "id") long id) {
        return notifyConfigTemplateService.getTemplateById(id);
    }

    @PutMapping
    @ApiOperation(value = "update nct", notes = "更新消息通知配置")
//    @Permission("update:nct:id")
    public Response updateNctById(NotifyConfigTemplateEntity nct) {
        return notifyConfigTemplateService.updateTemplate(nct);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete nct by id", notes = "删除消息通知配置")
//    @Permission("delete:nct:id")
    public Response deleteNctById(@PathVariable(name = "id") long id){
        return notifyConfigTemplateService.removeTemplate(id);
    }

    @GetMapping("/user/{userid}")
    @ApiOperation(value = "get list by userid", notes = "获取用户配置的所有消息配置")
//    @Permission("query:nct:userId")
    public Response getListByUserId(@PathVariable(name = "userid") long userId) {
        return notifyConfigTemplateService.getTemplateList(userId);
    }

    @GetMapping("/simple/user/{userid}")
    @ApiOperation(value = "get simple list by userid", notes = "获取配置列表，通常用于下拉菜单选择")
//    @Permission("query:nct:userId")
    public Response getSimpleListByUserId(@PathVariable(name = "userid") long userId) {
        return notifyConfigTemplateService.getTemplateSimpleList(userId);
    }

}
