package org.nuaa.wa.waelder.repository;

import org.nuaa.wa.waelder.entity.AdminUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Name: AdminUserRepository
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-14 00:15
 * @Version: 1.0
 */
public interface AdminUserRepository extends JpaRepository<AdminUserEntity, Long> {

    long countByPhoneOrUsername(String phone, String username);

    @Query(value = "select * from t_admin_user limit ?1 offset ?2", nativeQuery = true)
    List<AdminUserEntity> getAdminUserListPageable(int limit, int offset);

    @Modifying
    @Query(value = "update t_admin_user set username = ?1, phone = ?2, email = ?3, sex = ?4, update_time = ?5 where id = ?6", nativeQuery = true)
    void updateAdminInfo(String username, String phone, String email, String sex, Timestamp time, long id);

    @Query(value = "select password from t_admin_user where id = ?1", nativeQuery = true)
    String getAdminPassword(long id);

    @Query(value = "select * from t_admin_user where username like %?1% or phone like %?1% or email like %?1% limit ?2 offset ?3", nativeQuery = true)
    List<AdminUserEntity> searchAdmin(String key, int limit, int offset);

    @Query(value = "select count(1) from t_admin_user where username like %?1% or phone like %?1% or email like %?1%", nativeQuery = true)
    long searchAdminCount(String key);

    AdminUserEntity getAdminUserEntityByUsernameOrPhone(String username, String phone);

    @Query(value = "select username from t_admin_user where id = ?1", nativeQuery = true)
    String getUsernameById(long id);
}
