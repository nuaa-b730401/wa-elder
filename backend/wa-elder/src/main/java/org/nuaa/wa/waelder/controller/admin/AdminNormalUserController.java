package org.nuaa.wa.waelder.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nuaa.wa.waelder.aop.Permission;
import org.nuaa.wa.waelder.aop.ServiceLog;
import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.entity.UserEntity;
import org.nuaa.wa.waelder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Name: AdminUserController
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-11-17 12:29
 * @Version: 1.0
 */
@RestController
@RequestMapping("/admin/api/user-normal/")
@CrossOrigin
@Api("管理端用户数据操作接口")
public class AdminNormalUserController {
    @Autowired
    private UserService userService;

    @GetMapping
//    @Permission("admin")
    @ApiOperation(value = "get normal user list", notes = "获取正常状态的用户列表")
    public Response getNormalUserList(int page, int limit) {
        return userService.getUserList(page, limit);
    }

    @GetMapping("/locked")
//    @Permission("admin")
    @ApiOperation(value = "get locked user list", notes = "获取锁定状态的用户列表")
    public Response getLockedUserList(int page, int limit) {
        return userService.getUserLockedList(page, limit);
    }

    @GetMapping("/unactivated")
//    @Permission("admin")
    @ApiOperation(value = "get unactivated user list", notes = "获取未激活的用户列表")
    public Response getUnActivatedUserList(int page, int limit) {
        return userService.getUserUnActivatedList(page, limit);
    }

    @PostMapping("/lock/{id}")
//    @Permission("admin")
    @ApiOperation(value = "lock user", notes = "锁定用户")
    public Response lockUser(@PathVariable(name = "id") long userId) {
        return userService.lockUser(userId);
    }

    @PostMapping("/unlock/{id}")
//    @Permission("admin")
    @ApiOperation(value = "unlock user", notes = "解除锁定用户")
    public Response unlockUser(@PathVariable(name = "id") long userId) {
        return userService.unlockUser(userId);
    }

    @PostMapping
//    @Permission("admin")
    @ApiOperation(value = "add user", notes = "用户开户")
    @ServiceLog(name = "addUser", description = "add user")
    public Response addUser(UserEntity user) {
        return userService.addUser(user);
    }

    @GetMapping("/device/{userid}")
    @ApiOperation(value = "get user device info", notes = "获取用户设备统计信息")
    @ServiceLog(name = "getUserDeviceInfo", description = "get user device info")
    public Response getUserDeviceInfo(@PathVariable(name = "userid") long userId) {
        return userService.queryUserDeviceInfo(userId);
    }
}
