package org.nuaa.wa.waelder.repository;

import org.nuaa.wa.waelder.entity.DeviceLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Name: DeviceLogRepository
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-14 02:44
 * @Version: 1.0
 */
public interface DeviceLogRepository extends JpaRepository<DeviceLogEntity, Long> {
    @Query(value = "select * from t_device_log limit ?1 offset ?2", nativeQuery = true)
    List<DeviceLogEntity> listDeviceLog(int limit, int offset);
}
