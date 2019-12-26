package org.nuaa.wa.waelder.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Name: AlarmVideoEntity
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-20 18:33
 * @Version: 1.0
 */
@Entity
@Table(name = "sys_alarm_video", schema = "db_wa_elder", catalog = "")
public class AlarmVideoEntity {
    private String id;
    private Long deviceId;
    private String filename;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
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
    @Column(name = "filename")
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlarmVideoEntity that = (AlarmVideoEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(deviceId, that.deviceId) &&
                Objects.equals(filename, that.filename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deviceId, filename);
    }
}
