package org.nuaa.wa.waelder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.entity.UserEntity;
import org.nuaa.wa.waelder.service.UserService;
import org.nuaa.wa.waelder.util.constant.LogLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Name: UserController
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-10-28 20:19
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
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
    @ApiOperation(value = "update user password", notes = "修改密码", httpMethod = "POST")
    public Response updateUserPassword(long id, String oPass, String nPass) {
        return userService.updateUserPassword(id, oPass, nPass);
    }

}
