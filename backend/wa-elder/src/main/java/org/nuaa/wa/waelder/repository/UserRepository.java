package org.nuaa.wa.waelder.repository;

import org.nuaa.wa.waelder.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


/**
 * @Name: UserRepository
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-10-29 22:35
 * @Version: 1.0
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByPhone(String phone);

    @Modifying
    @Query(value = "update t_user set password = ?1, status = ?2 where id = ?3", nativeQuery = true)
    void updateUserPasswordAndStatusById(String password, int status, long id);

    @Modifying
    @Query(value = "update t_user set password = ?1 where id = ?2", nativeQuery = true)
    void updateUserPasswordById(String password, long id);

    @Query(value = "select * from t_user where id = ?1", nativeQuery = true)
    UserEntity getUserById(long id);

    @Query(value = "select * from t_user where status = ?1 limit ?2 offset ?3", nativeQuery = true)
    List<UserEntity> getUserList(int status, int beg, int end);

    int countByStatus(int status);

    @Modifying
    @Query(value = "update t_user set status = ?1 where id = ?2", nativeQuery = true)
    void updateUserStatus(int status, long userId);

    @Modifying
    @Query(value = "update t_user set username = ?1, sex = ?2, address = ?3 where id = ?4", nativeQuery = true)
    void updateUserBasicInfo(String username, String sex, String address, long id);

    @Modifying
    @Query(value = "update t_user set phone = ?1 where id = ?2", nativeQuery = true)
    void updateUserPhone(String phone, long userId);

    @Modifying
    @Query(value = "update t_user set wechat_id = ?1 where id = ?2", nativeQuery = true)
    void updateUserWechatId(String wechatId, long userId);

    long countByPhoneOrUsername(String phone, String username);
}
