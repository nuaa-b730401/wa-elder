package org.nuaa.wa.waelder.service.impl;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.nuaa.wa.waelder.client.SmsClient;
import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.entity.UserEntity;
import org.nuaa.wa.waelder.repository.UserRepository;
import org.nuaa.wa.waelder.service.UserService;
import org.nuaa.wa.waelder.util.PasswordEncryptUtil;
import org.nuaa.wa.waelder.util.TokenGenerator;
import org.nuaa.wa.waelder.util.cache.PhoneCacheFactory;
import org.nuaa.wa.waelder.util.constant.LogLevel;
import org.nuaa.wa.waelder.util.constant.StatusConstant;
import org.nuaa.wa.waelder.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @Name: UserServiceImpl
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-10-29 22:52
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    private static Logger logger = LoggerFactory.getLogger(LogLevel.SERVICE);
    private static Gson gson = new Gson();

    @Autowired
    private SmsClient smsClient;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Response signInByPhoneNumber(UserEntity user) {
        return null;
    }

    @Override
    public Response signInByWechat(UserEntity user) {
        return null;
    }

    @Override
    public Response sendSmsVerifyCode(String phone) {
        PhoneCacheFactory cacheFactory = PhoneCacheFactory.getInstance();
        String code = TokenGenerator.generateSmsCode();
        // 缓存验证码
        cacheFactory.set(phone, code);
        // 发送验证码
        int status = smsClient.sendVerifyCode(phone, code);
        if (status == 0) {
            return new Response(Response.SUCCESS_CODE, "send sms verify code success");
        }
        return new Response(Response.SERVER_ERROR_CODE, "send sms verify code fail");
    }

    @Override
    public Response checkVerifyCode(String phone, String code) {
        PhoneCacheFactory cacheFactory = PhoneCacheFactory.getInstance();

        String cacheCode = cacheFactory.get(phone);
        if (StringUtils.isEmpty(cacheCode) || !cacheCode.equals(code)) {
            logger.warn("verify code has expired or verify code error, phone number : {}", phone);
            return new Response(Response.SERVER_ERROR_CODE, "verify code error");
        }

        return new Response(Response.SUCCESS_CODE, "verify code check success");
    }

    @Override
    public Response signUpByWechat(UserEntity user) {
        return null;
    }

    @Override
    public Response signUpByPhoneNumber(String phone, String code) {
        // 检查手机号是否存在
        Optional<UserEntity> userEntityOptional = userRepository.findByPhone(phone);
        if (userEntityOptional.isPresent()) {
            return new Response(Response.SERVER_DATA_DUPLICATION, "phone exists");
        }
        // 检查手机验证码
        Response checkResponse = checkVerifyCode(phone, code);
        if (checkResponse.getCode() == Response.SERVER_ERROR_CODE) {
            return checkResponse;
        }

        // 保存账户信息
        try {
            UserEntity saveUser = new UserEntity();
            saveUser.setPhone(phone);
            saveUser.setStatus(StatusConstant.USER_UNACTIVATED_STATUS);
            UserEntity user = userRepository.save(saveUser);
            UserVo userVo = new UserVo();
            userVo.setId(user.getId());
            userVo.setPhone(phone);
            return new Response<UserVo>(
                    Response.SUCCESS_CODE, "sign up user success", userVo);

        } catch (Exception ex) {
            logger.warn("save user fail, msg : {}", ex.getMessage());
            return new Response(Response.SERVER_DATABASE_ERROR, "save user fail");
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Response setUserPassword(UserEntity user) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(user.getId());
        if (!userEntityOptional.isPresent()) {
            logger.warn("{} not exists", user.getId());
            return new Response(Response.SERVER_DATA_NOT_FOUND_ERROR, "user not exists");
        }
        try {
            userRepository.updateUserPasswordAndStatusById(
                    PasswordEncryptUtil.getEncryptedPwd(user.getPassword()),
                    StatusConstant.USER_NORMAL_STATUS, user.getId());
        } catch (Exception ex) {
            logger.warn("set user password fail, msg : {}", ex.getMessage());
            return new Response(Response.SERVER_ERROR_CODE, "set user password fail");
        }

        return new Response(Response.SUCCESS_CODE, "set user password success");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Response updateUserPassword(long id, String oldPassword, String newPassword) {
        UserEntity userEntity = userRepository.findById(id).orElseGet(() -> null);
        if (userEntity == null) {
            logger.warn("{} not exists", id);
            return new Response(Response.SERVER_DATA_NOT_FOUND_ERROR, "user not exists");
        }

        try {
            // 原密码输入错误
            if (!PasswordEncryptUtil.validPassword(oldPassword, userEntity.getPassword())) {
                return new Response(Response.INPUT_ERROR_CODE, "password wrong");
            }

            // 更新密码
            userRepository.updateUserPasswordById(
                    PasswordEncryptUtil.getEncryptedPwd(newPassword), id);
        } catch (Exception ex) {
            logger.warn("update password fail, msg : {}", ex.getMessage());
            return new Response(Response.SERVER_ERROR_CODE, "update password fail");
        }

        return new Response(Response.SUCCESS_CODE, "update password success");
    }
}
