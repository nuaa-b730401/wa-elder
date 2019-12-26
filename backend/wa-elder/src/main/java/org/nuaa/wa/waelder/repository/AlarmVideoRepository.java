package org.nuaa.wa.waelder.repository;

import org.nuaa.wa.waelder.entity.AlarmVideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @Name: AlarmVideoRepository
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-20 18:33
 * @Version: 1.0
 */
public interface AlarmVideoRepository extends JpaRepository<AlarmVideoEntity, String> {
    @Query(value = "select filename from sys_alarm_video where id = ?1", nativeQuery = true)
    String getVideoPath(String alarmId);
}
