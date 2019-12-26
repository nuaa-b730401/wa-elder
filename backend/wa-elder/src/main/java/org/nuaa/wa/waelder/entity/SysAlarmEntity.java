package org.nuaa.wa.waelder.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Name: SysAlarmEntity
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-14 05:39
 * @Version: 1.0
 */
@Entity
@Table(name = "sys_alarm", schema = "db_wa_elder", catalog = "")
public class SysAlarmEntity {
    private long id;
    private Long deviceId;
    private String deviceName;
    private Long userId;
    private String videoPath;
    private String imagePath;
    private Timestamp createTime;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "device_id")
    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    @Basic
    @Column(name = "device_name")
    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Basic
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "video_path")
    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    @Basic
    @Column(name = "image_path")
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysAlarmEntity that = (SysAlarmEntity) o;
        return id == that.id &&
                Objects.equals(deviceId, that.deviceId) &&
                Objects.equals(deviceName, that.deviceName) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(videoPath, that.videoPath) &&
                Objects.equals(imagePath, that.imagePath) &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deviceId, deviceName, userId, videoPath, imagePath, createTime);
    }
}
