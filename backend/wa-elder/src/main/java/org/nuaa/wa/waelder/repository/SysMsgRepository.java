package org.nuaa.wa.waelder.repository;

import org.nuaa.wa.waelder.entity.SysMsgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Name: SysMsgRepository
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-14 05:41
 * @Version: 1.0
 */
public interface SysMsgRepository extends JpaRepository<SysMsgEntity, Long> {
    @Query(value = "select * from sys_msg limit ?1 offset ?2", nativeQuery = true)
    List<SysMsgEntity> getMsg(int limit, int offset);
}
