package org.nuaa.wa.waelder.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Name: NotifyConfigEntity
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-10-26 10:39
 * @Version: 1.0
 */
@Entity
@Table(name = "t_notify_config", schema = "db_wa_elder", catalog = "")
@IdClass(NotifyConfigEntityPK.class)
public class NotifyConfigEntity {
    private Long deviceId;
    private Long templateId;

    @Id
    @Basic
    @Column(name = "device_id")
    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    @Id
    @Basic
    @Column(name = "template_id")
    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(long templateId) {
        this.templateId = templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotifyConfigEntity that = (NotifyConfigEntity) o;
        return Objects.equals(deviceId, that.deviceId) &&
                Objects.equals(templateId, that.templateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceId, templateId);
    }
}
