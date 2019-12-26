package org.nuaa.wa.waelder.service;

import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.entity.SysMsgEntity;

/**
 * @Name: SysMsgService
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-14 05:43
 * @Version: 1.0
 */
public interface SysMsgService {
    void addSysMsg(SysMsgEntity msg);

    Response getMsg(int page, int limit);

    Response deleteMsg(long id);
}
