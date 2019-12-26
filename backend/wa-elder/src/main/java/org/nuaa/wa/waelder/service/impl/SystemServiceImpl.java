package org.nuaa.wa.waelder.service.impl;

import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.entity.SystemDataEntity;
import org.nuaa.wa.waelder.repository.AdminUserRepository;
import org.nuaa.wa.waelder.repository.DeviceRepository;
import org.nuaa.wa.waelder.repository.SysAlarmRepository;
import org.nuaa.wa.waelder.repository.UserRepository;
import org.nuaa.wa.waelder.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Name: SystemServiceImpl
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-20 21:25
 * @Version: 1.0
 */
@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private SysAlarmRepository sysAlarmRepository;

    @Override
    public Response systemInfoData() {
        return new Response<SystemDataEntity>(
                Response.SUCCESS_CODE, "get system data success",
                new SystemDataEntity(
                        adminUserRepository.count(),
                        userRepository.count(),
                        deviceRepository.count(),
                        sysAlarmRepository.count()

                )
        );
    }
}
