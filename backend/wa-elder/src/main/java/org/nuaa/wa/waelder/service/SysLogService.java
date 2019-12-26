package org.nuaa.wa.waelder.service;

import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.entity.SysLogEntity;

/**
 * @Name: SysLogService
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-14 04:48
 * @Version: 1.0
 */
public interface SysLogService {
    void addLog(SysLogEntity log);

    Response getLog(int page, int limit);

    Response deleteLog(long id);
}
