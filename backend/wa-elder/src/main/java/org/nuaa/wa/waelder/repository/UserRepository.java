package org.nuaa.wa.waelder.repository;

import org.nuaa.wa.waelder.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

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
    @Query(value = "update user set password = ?1, status = ?2 where id = ?3", nativeQuery = true)
    void updateUserPasswordAndStatusById(String password, int status, long id);

    @Modifying
    @Query(value = "update user set password = ?1 where id = ?2", nativeQuery = true)
    void updateUserPasswordById(String password, long id);
}