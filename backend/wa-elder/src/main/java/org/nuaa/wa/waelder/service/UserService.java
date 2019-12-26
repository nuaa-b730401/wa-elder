package org.nuaa.wa.waelder.service;

import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.entity.UserEntity;

import javax.servlet.http.HttpServletResponse;

/**
 * @Name: UserService
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-10-29 22:37
 * @Version: 1.0
 */
public interface UserService {
    Response signInByPhoneNumber(UserEntity user, HttpServletResponse response);

    Response signInByWechat(UserEntity user);

    Response sendSmsVerifyCode(String phone);

    Response checkVerifyCode(String phone, String code);

    Response signUpByPhoneNumber(String phone, String code);

    Response signUpByWechat(UserEntity user);

    Response setUserPassword(UserEntity user);

    Response updateUserPassword(long id, String oldPassword, String newPassword);

    Response getUserInfo(long id);

    Response updateUserInfo(UserEntity user);

    Response getUserList(int page, int limit);

    Response getUserLockedList(int page, int limit);

    Response getUserUnActivatedList(int page, int limit);

    Response lockUser(long id);

    Response unlockUser(long id);

    Response addUser(UserEntity user);

    Response queryUserDeviceInfo(long userId);
}
