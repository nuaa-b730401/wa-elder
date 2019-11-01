package org.nuaa.wa.waelder.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @Name: NotifyConfigEntityPK
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-10-26 10:43
 * @Version: 1.0
 */
public class NotifyConfigEntityPK implements Serializable {
    private Long deviceId;
    private Long templateId;

    @Column(name = "device_id")
    @Basic
    @Id
    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    @Column(name = "template_id")
    @Basic
    @Id
    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotifyConfigEntityPK that = (NotifyConfigEntityPK) o;
        return Objects.equals(deviceId, that.deviceId) &&
                Objects.equals(templateId, that.templateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceId, templateId);
    }
}
