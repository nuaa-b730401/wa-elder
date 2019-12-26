package org.nuaa.wa.waelder.repository;

import org.nuaa.wa.waelder.entity.SysAlarmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Name: SysAlarmRepository
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-14 05:42
 * @Version: 1.0
 */
public interface SysAlarmRepository extends JpaRepository<SysAlarmEntity, Long> {
    @Query(value = "select * from sys_alarm limit ?1 offset ?2", nativeQuery = true)
    List<SysAlarmEntity> getAlarm(int limit, int offset);

    @Query(value = "select * from sys_alarm where user_id = ?1", nativeQuery = true)
    List<SysAlarmEntity> getUserAlarm(long userId);
}
