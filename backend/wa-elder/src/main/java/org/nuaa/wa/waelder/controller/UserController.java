package org.nuaa.wa.waelder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nuaa.wa.waelder.aop.Permission;
import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.entity.UserEntity;
import org.nuaa.wa.waelder.service.UserService;
import org.nuaa.wa.waelder.util.constant.LogLevel;
import org.nuaa.wa.waelder.util.constant.PermissionConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @Name: UserController
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-10-28 20:19
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/user")
@CrossOrigin
@Api("用户操作接口")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(LogLevel.SERVICE);

    @Autowired
    private UserService userService;

    @PostMapping("/send-code")
    @ApiOperation(value = "send verify code", notes = "发送短信验证码", httpMethod = "POST")
    public Response sendSmsCode(String phone) {
        return userService.sendSmsVerifyCode(phone);
    }

    @PostMapping("/signup/phone")
    @ApiOperation(value = "signup by phone", notes = "手机号注册", httpMethod = "POST")
    public Response signUpByPhone(String phone, String code) {
        return userService.signUpByPhoneNumber(phone, code);
    }

    @PostMapping("/signup/set-password")
    @ApiOperation(value = "set password", notes = "设置密码", httpMethod = "POST")
    public Response setPassword(UserEntity user) {
        return userService.setUserPassword(user);
    }

    @PostMapping("/update-password")
//    @Permission("login")
    @ApiOperation(value = "update user password", notes = "修改密码", httpMethod = "POST")
    public Response updateUserPassword(long id, String oPass, String nPass) {
        return userService.updateUserPassword(id, oPass, nPass);
    }

    @PostMapping("/login/phone")
    @ApiOperation(value = "login by phone", notes = "使用手机号登录", httpMethod = "POST")
    public Response signInByPhone(UserEntity user, HttpServletResponse response) {
        return userService.signInByPhoneNumber(user, response);
    }

    @GetMapping("/{id}")
//    @Permission("login")
    @ApiOperation(value = "get user by id", notes = "获取指定用户信息", httpMethod = "GET")
    public Response getUserById(@PathVariable(value = "id") long id) {
        return userService.getUserInfo(id);
    }

    @PutMapping
//    @Permission("login")
    @ApiOperation(value = "update user info", notes = "更新用户信息")
    public Response updateUser(UserEntity user) {
        return userService.updateUserInfo(user);
    }

}
