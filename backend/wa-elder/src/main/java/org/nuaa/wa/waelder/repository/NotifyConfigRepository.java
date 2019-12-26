package org.nuaa.wa.waelder.repository;

import org.nuaa.wa.waelder.entity.NotifyConfigEntity;
import org.nuaa.wa.waelder.entity.NotifyConfigEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Name: NotifyConfigRepository
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-11-09 16:40
 * @Version: 1.0
 */
public interface NotifyConfigRepository extends JpaRepository<NotifyConfigEntity, NotifyConfigEntityPK> {
}
