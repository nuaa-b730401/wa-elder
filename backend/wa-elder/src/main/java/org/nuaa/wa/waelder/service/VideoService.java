package org.nuaa.wa.waelder.service;

import org.nuaa.wa.waelder.entity.Response;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Name: VideoService
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-20 18:26
 * @Version: 1.0
 */
public interface VideoService {
    Response upload(MultipartFile video, long deviceId, String alarmId);

    String getVideoPath(String alarmId);

    Resource getAlarmVideo(String alarmId);
}
