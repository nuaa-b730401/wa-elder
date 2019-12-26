package org.nuaa.wa.waelder.service.impl;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.nuaa.wa.waelder.entity.AdminUserEntity;
import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.repository.AdminUserRepository;
import org.nuaa.wa.waelder.service.AdminUserService;
import org.nuaa.wa.waelder.util.CookieUtil;
import org.nuaa.wa.waelder.util.TokenGenerator;
import org.nuaa.wa.waelder.util.constant.LogLevel;
import org.nuaa.wa.waelder.util.constant.PermissionConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Name: AdminUserServiceImpl
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-14 00:18
 * @Version: 1.0
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class AdminUserServiceImpl implements AdminUserService {

    private static Logger logger = LoggerFactory.getLogger(LogLevel.SERVICE);

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Override
    public Response addAdmin(AdminUserEntity admin) {
        try {
            Preconditions.checkNotNull(admin, "param null");
            Preconditions.checkArgument(StringUtils.isNotEmpty(admin.getPhone()), "param phone empty");
            Preconditions.checkArgument(StringUtils.isNotEmpty(admin.getUsername()), "param username empty");

            Preconditions.checkArgument(adminUserRepository.countByPhoneOrUsername(
                    admin.getPhone(), admin.getUsername()
            ) <= 0, "user has existed");

            admin.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            adminUserRepository.save(admin);
        } catch (Exception ex) {
            logger.warn("add admin fail, ex : {}", ex.getMessage());
            return new Response(Response.SERVER_ERROR_CODE, "add admin fail");
        }
        return new Response(Response.SUCCESS_CODE, "add admin success");
    }

    @Override
    public Response getAdminInfo(Long id) {
        id = id == null ? -1 : id;
        return new Response<AdminUserEntity>(
                Response.SUCCESS_CODE,
                "get admin success",
                adminUserRepository.findById(id).orElseGet(() -> null)
        );
    }

    @Override
    public Response getAdminList(int page, int limit) {
        return new Response<AdminUserEntity>(
                Response.SUCCESS_CODE,
                "get admin success",
                adminUserRepository.getAdminUserListPageable(limit, (page - 1) * limit),
                adminUserRepository.count()
        );
    }

    @Override
    public Response updateAdmin(AdminUserEntity admin) {
        try {
            admin.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            adminUserRepository.updateAdminInfo(
                    admin.getUsername(),
                    admin.getPhone(),
                    admin.getEmail(),
                    admin.getSex(),
                    admin.getUpdateTime(),
                    admin.getId()
            );
        } catch (Exception ex) {
            logger.warn("update admin fail, ex : {}", ex.getMessage());
            return new Response(Response.SERVER_ERROR_CODE, "update admin fail");
        }
        return new Response(Response.SUCCESS_CODE, "update admin success");
    }

    @Override
    public Response deleteAdmin(Long id) {
        try {
            adminUserRepository.deleteById(id);
        } catch (Exception ex) {
            logger.warn("delete admin fail, ex : {}", ex.getMessage());
            return new Response(Response.SERVER_ERROR_CODE, "delete admin fail");
        }
        return new Response(Response.SUCCESS_CODE, "delete admin success");
    }

    @Override
    public Response getAdminPassword(Long id) {
        try {
            String password = adminUserRepository.getAdminPassword(id);
            Preconditions.checkArgument(StringUtils.isNotEmpty(password), "password invalid");
            return new Response<String>(
                    Response.SUCCESS_CODE,
                    "get admin password success",
                    password
            );
        } catch (Exception ex) {
            logger.warn("get admin password fail, ex : {}", ex.getMessage());
            return new Response(Response.SERVER_ERROR_CODE, "get admin password fail");
        }
    }

    @Override
    public Response searchAdmin(String key, int page, int limit) {
        try {
            return new Response<AdminUserEntity>(
                    Response.SUCCESS_CODE,
                    "get admin list success",
                    adminUserRepository.searchAdmin(key, limit, (page - 1) * limit),
                    adminUserRepository.searchAdminCount(key)
            );
        } catch (Exception ex) {
            return new Response(
                    Response.SERVER_ERROR_CODE, "get admin list fail"
            );
        }
    }

    @Override
    public Response login(String username, String password, HttpServletResponse response) {
        try {
            AdminUserEntity userEntity = adminUserRepository.getAdminUserEntityByUsernameOrPhone(username, username);
            Preconditions.checkNotNull(userEntity, "not exists");
            Preconditions.checkArgument(userEntity.getPassword().equals(password), "password wrong");

            String userToken = TokenGenerator.getInstance().encrypt(
                    String.format("%d.%d", userEntity.getId(), System.currentTimeMillis())
            );

            CookieUtil.addCookie(PermissionConstant.ADMIN_TOKEN_HEADER, userToken,
                    PermissionConstant.COOKIE_EXPIRE_TIME, response);

            CookieUtil.addCookie(PermissionConstant.USER_INFO_HEADER, String.valueOf(userEntity.getId()),
                    PermissionConstant.COOKIE_EXPIRE_TIME, response);

            return new Response(Response.SUCCESS_CODE, "login success");
        } catch (Exception ex) {
            logger.warn(ex.getMessage());
        }



        return new Response(Response.SERVER_ERROR_CODE, "login fail");
    }

    @Override
    public String getUsername(long userId) {
        return adminUserRepository.getUsernameById(userId);
    }
}
