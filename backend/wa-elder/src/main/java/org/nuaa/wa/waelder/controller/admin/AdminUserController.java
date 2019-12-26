package org.nuaa.wa.waelder.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nuaa.wa.waelder.aop.ServiceLog;
import org.nuaa.wa.waelder.entity.AdminUserEntity;
import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.service.AdminUserService;
import org.nuaa.wa.waelder.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Name: AdminUserController
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-14 00:49
 * @Version: 1.0
 */

@RestController
@RequestMapping("/admin/api/user/")
@Api("管理端管理员数据操作接口")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @PostMapping
    @ApiOperation(value = "add admin", notes = "添加管理员")
    @ServiceLog(name = "addAdmin", description = "add admin")
//    @Permission("admin")
    public Response addAdmin(AdminUserEntity admin) {
        return adminUserService.addAdmin(admin);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "get admin by id", notes = "根据id获取管理员")
    @ServiceLog(name = "getAdminById", description = "get admin by id")
    //    @Permission("admin")
    public Response getAdminById(@PathVariable(name = "id") long id) {
        return adminUserService.getAdminInfo(id);
    }

    @GetMapping
    @ApiOperation(value = "get admin list", notes = "获取管理员列表")
    @ServiceLog(name = "getAdminList", description = "get admin list")
    //    @Permission("admin")
    public Response getAdminList(int page, int limit) {
        return adminUserService.getAdminList(page, limit);
    }

    @PutMapping
    @ApiOperation(value = "update admin", notes = "更新管理员数据")
    @ServiceLog(name = "updateAdmin", description = "update admin")
    //    @Permission("admin")
    public Response upateAdmin(AdminUserEntity admin) {
        return adminUserService.updateAdmin(admin);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete admin", notes = "删除管理员数据")
    @ServiceLog(name = "deteleAdmin", description = "delete admin")
    //    @Permission("admin")
    public Response deleteAdmin(@PathVariable(name = "id") long id) {
        return adminUserService.deleteAdmin(id);
    }

    @GetMapping("/password/{id}")
    @ApiOperation(value = "get admin password", notes = "获取管理员密码")
    @ServiceLog(name = "getAdminPassword", description = "get admin password")
    public Response getAdminPassword(@PathVariable(name = "id") long id) {
        return adminUserService.getAdminPassword(id);
    }

    @GetMapping("search/{key}")
    @ApiOperation(value = "search admin", notes = "搜索管理员")
    @ServiceLog(name = "searchAdmin", description = "search admin")
    public Response searchAdmin(@PathVariable(name = "key") String key, int page, int limit) {
        return adminUserService.searchAdmin(key, page, limit);
    }

    @PostMapping("/login")
    @ApiOperation(value = "admin login", notes = "管理员登录")
    @ServiceLog(name = "adminLogin", description = "admin login")
    public Response adminLogin(String username, String password, HttpServletResponse response) {
        return adminUserService.login(username, password, response);
    }

    @GetMapping("/cookie")
    public String getCookie(String name, HttpServletRequest request) {
        return CookieUtil.getCookieByName(name, request).getValue();
    }

}
