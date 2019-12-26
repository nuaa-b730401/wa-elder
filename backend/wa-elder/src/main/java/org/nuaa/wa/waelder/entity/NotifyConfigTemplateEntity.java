package org.nuaa.wa.waelder.entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Name: NotifyConfigTemplateEntity
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-10-26 10:39
 * @Version: 1.0
 */
@Entity
@Table(name = "t_notify_config_template", schema = "db_wa_elder", catalog = "")
public class NotifyConfigTemplateEntity {
    private long id;
    private Long userId;
    private String phone;
    private String wechatId;
    private Integer level;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String templateName;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "wechat_id")
    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    @Basic
    @Column(name = "level")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Basic
    @Column(name = "create_time")
    @Generated(GenerationTime.INSERT)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotifyConfigTemplateEntity that = (NotifyConfigTemplateEntity) o;
        return id == that.id &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(wechatId, that.wechatId) &&
                Objects.equals(level, that.level) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, phone, wechatId, level, createTime, updateTime);
    }

    @Basic
    @Column(name = "template_name")
    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
