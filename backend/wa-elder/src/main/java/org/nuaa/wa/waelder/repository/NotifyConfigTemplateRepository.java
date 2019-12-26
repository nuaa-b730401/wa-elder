package org.nuaa.wa.waelder.repository;

import org.nuaa.wa.waelder.entity.NotifyConfigTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Name: NotifyConfigTemplateRepository
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-11-09 16:39
 * @Version: 1.0
 */
public interface NotifyConfigTemplateRepository extends JpaRepository<NotifyConfigTemplateEntity, Long> {
    @Modifying
    @Query(value = "update t_notify_config_template set phone = ?1, wechat_id = ?2, level = ?3, template_name = ?4 where id = ?5", nativeQuery = true)
    void updateNotifyConfigTemplate(String phone, String wechatId, int level, String templateName, long id);

    @Query(value = "select * from t_notify_config_template where user_id = ?1", nativeQuery = true)
    List<NotifyConfigTemplateEntity> getSimpleList(long userId);

    List<NotifyConfigTemplateEntity> findAllByUserId(long userId);

    @Query(value = "select * from t_notify_config_template order by create_time desc limit ?1 offset ?2", nativeQuery = true)
    List<NotifyConfigTemplateEntity> getAllTemplate(int limit, int offset);

    long countByUserId(long userId);
}
