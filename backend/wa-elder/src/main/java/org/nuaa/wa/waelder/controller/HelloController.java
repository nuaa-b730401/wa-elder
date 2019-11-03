package org.nuaa.wa.waelder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nuaa.wa.waelder.aop.Permission;
import org.nuaa.wa.waelder.aop.ServiceLog;
import org.nuaa.wa.waelder.client.SmsClient;
import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.util.CookieUtil;
import org.nuaa.wa.waelder.util.TokenGenerator;
import org.nuaa.wa.waelder.util.constant.PermissionConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Name: HelloController
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-10-24 11:31
 * @Version: 1.0
 */
@RestController
@RequestMapping("/")
@Api("hello rest")
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger("hello");

    @Autowired
    private SmsClient smsClient;

    @Permission("login")
    @ApiOperation(value = "hello user", notes = "input user name")
    @GetMapping("hello/{name}")
    @ServiceLog(name = "sayHello", description = "say hello to user")
    public Response sayHello(@PathVariable(name = "name") String name) {
        logger.info("{} visit hello", name);
        return new Response<String>().data("hello, " + name);
    }

    @GetMapping("/login")
    public Response login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = "tomax";
        CookieUtil.addCookie(PermissionConstant.TOKEN_HEADER,
                TokenGenerator.getInstance().encrypt(username.concat(".").concat(String.valueOf(System.currentTimeMillis()))),
                PermissionConstant.COOKIE_EXPIRE_TIME, response);
        return new Response();
    }

    @GetMapping("/send")
    public Response sendSmsCode() {
        smsClient.sendVerifyCode("15195963968", "123456");
        return new Response();
    }

}
