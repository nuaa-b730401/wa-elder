package org.nuaa.wa.waelder.service.impl;

import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.entity.SysMsgEntity;
import org.nuaa.wa.waelder.repository.SysMsgRepository;
import org.nuaa.wa.waelder.service.SysMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;

/**
 * @Name: SysMsgServiceImpl
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-14 05:45
 * @Version: 1.0
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class SysMsgServiceImpl implements SysMsgService {
    @Autowired
    private SysMsgRepository sysMsgRepository;

    @Override
    public void addSysMsg(SysMsgEntity msg) {
        msg.setCreateTime(new Timestamp(System.currentTimeMillis()));
        sysMsgRepository.save(msg);
    }

    @Override
    public Response getMsg(int page, int limit) {
        return new Response<SysMsgEntity>(
                Response.SUCCESS_CODE,
                "get msg success",
                sysMsgRepository.getMsg(
                        limit, (page - 1) * limit
                )
        );
    }

    @Override
    public Response deleteMsg(long id) {
        sysMsgRepository.deleteById(id);
        return new Response(
                Response.SUCCESS_CODE,
                "delete msg success"
        );
    }
}
