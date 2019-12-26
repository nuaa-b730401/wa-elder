package org.nuaa.wa.waelder.repository;

import org.nuaa.wa.waelder.entity.SysLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Name: SysLogRepository
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-14 04:48
 * @Version: 1.0
 */
public interface SysLogRepository extends JpaRepository<SysLogEntity, Long> {

    @Query(value = "select * from sys_log limit ?1 offset ?2", nativeQuery = true)
    List<SysLogEntity> getLog(int limit, int offset);
}
