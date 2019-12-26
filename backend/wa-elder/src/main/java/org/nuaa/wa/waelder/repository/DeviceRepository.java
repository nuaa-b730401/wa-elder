package org.nuaa.wa.waelder.repository;

import org.nuaa.wa.waelder.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Name: DeviceRepository
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-11-07 19:55
 * @Version: 1.0
 */
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {

    @Modifying
    @Query(value = "update t_device set device_name = ?1, device_address = ?2, device_type = ?3, template_id = ?4, update_time = ?5 where id = ?6", nativeQuery = true)
    void updateDeviceInfo(String deviceName, String deviceAddress, int deviceType, long templateId, Timestamp time, long id);

    List<DeviceEntity> getDeviceEntitiesByUserId(long userId);

    @Query(value = "select * from t_device order by create_time desc limit ?1 offset ?2", nativeQuery = true)
    List<DeviceEntity> getDeviceEntitiesPageable(int limit, int offset);

    long countByUserId(long userId);

    @Modifying
    @Query(value = "update t_device set device_status = ?1 where id = ?2", nativeQuery = true)
    void updateDeviceStatus(int status, long id);

    @Query(value = "select device_name from t_device where id = ?1", nativeQuery = true)
    String getDeviceName(long id);

    @Query(value = "select user_id from t_device where id = ?1", nativeQuery = true)
    long getDeviceUser(long id);
}
