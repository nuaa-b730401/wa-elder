package org.nuaa.wa.waelder.util.constant;

/**
 * @Name: PermissionConstant
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-10-27 21:46
 * @Version: 1.0
 */
public interface PermissionConstant {
    int TOKEN_EXPIRE_TIME = 60 * 60 * 1000;
    int COOKIE_EXPIRE_TIME = 15 * 60 * 1000;

    String TOKEN_HEADER = "user-token";

    String ADMIN_TOKEN_HEADER = "admin-user-token";

    String USER_INFO_HEADER = "user-id";

    String PERMISSION_ANON = "anon";

    String PERMISSION_LOGIN = "login";

    String PERMISSION_ADMIN = "admin";
}
