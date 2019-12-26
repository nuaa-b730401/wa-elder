package org.nuaa.wa.waelder.service.impl;

import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.entity.SysLogEntity;
import org.nuaa.wa.waelder.repository.SysLogRepository;
import org.nuaa.wa.waelder.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;

/**
 * @Name: SysLogServiceImpl
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-14 04:51
 * @Version: 1.0
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogRepository sysLogRepository;

    @Override
    public void addLog(SysLogEntity log) {
        log.setCreateTime(new Timestamp(System.currentTimeMillis()));
        sysLogRepository.save(log);
    }

    @Override
    public Response getLog(int page, int limit) {
        return new Response<SysLogEntity>(
                Response.SUCCESS_CODE,
                "get log success",
                sysLogRepository.getLog(
                        limit, (page - 1) * limit
                ),
                sysLogRepository.count()
        );
    }

    @Override
    public Response deleteLog(long id) {
        sysLogRepository.deleteById(id);
        return new Response(Response.SUCCESS_CODE, "delete log success");
    }
}
