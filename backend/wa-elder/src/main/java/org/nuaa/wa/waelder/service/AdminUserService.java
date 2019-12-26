package org.nuaa.wa.waelder.service;

import org.nuaa.wa.waelder.entity.AdminUserEntity;
import org.nuaa.wa.waelder.entity.Response;

import javax.servlet.http.HttpServletResponse;

/**
 * @Name: AdminUserService
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-14 00:16
 * @Version: 1.0
 */
public interface AdminUserService {
    Response addAdmin(AdminUserEntity admin);

    Response getAdminInfo(Long id);

    Response getAdminList(int page, int limit);

    Response updateAdmin(AdminUserEntity admin);

    Response deleteAdmin(Long id);

    Response getAdminPassword(Long id);

    Response searchAdmin(String key, int page, int limit);

    Response login(String username, String password, HttpServletResponse response);

    String getUsername(long userId);
}
