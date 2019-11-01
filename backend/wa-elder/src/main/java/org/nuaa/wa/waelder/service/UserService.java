package org.nuaa.wa.waelder.service;

import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.entity.UserEntity;

/**
 * @Name: UserService
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-10-29 22:37
 * @Version: 1.0
 */
public interface UserService {
    Response signInByPhoneNumber(UserEntity user);

    Response signInByWechat(UserEntity user);

    Response sendSmsVerifyCode(String phone);

    Response checkVerifyCode(String phone, String code);

    Response signUpByPhoneNumber(String phone, String code);

    Response signUpByWechat(UserEntity user);

    Response setUserPassword(UserEntity user);

    Response updateUserPassword(long id, String oldPassword, String newPassword);
}
