package org.nuaa.wa.waelder.service.impl;

import org.nuaa.wa.waelder.entity.AlarmVideoEntity;
import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.repository.AlarmVideoRepository;
import org.nuaa.wa.waelder.service.VideoService;
import org.nuaa.wa.waelder.util.FileUtil;
import org.nuaa.wa.waelder.util.constant.LogLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

/**
 * @Name: VideoServiceImpl
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-20 18:28
 * @Version: 1.0
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class VideoServiceImpl implements VideoService {

    private static Logger logger = LoggerFactory.getLogger(LogLevel.SERVICE);

    @Autowired
    private AlarmVideoRepository alarmVideoRepository;

    @Value("${system.store.video-path}")
    private String sysVideoPath;

    @Override
    public Response upload(MultipartFile video, long deviceId, String alarmId) {
        try {
            FileUtil.saveFile(video, sysVideoPath, alarmId.concat(".mp4"));
            AlarmVideoEntity alarmVideoEntity = new AlarmVideoEntity();
            alarmVideoEntity.setDeviceId(deviceId);
            alarmVideoEntity.setId(alarmId);
            alarmVideoEntity.setFilename(alarmId.concat(".mp4"));
            alarmVideoRepository.save(alarmVideoEntity);
        } catch (Exception ex) {
            logger.warn("upload device {} alarm video fail, ex : {}", deviceId, ex.getMessage());
            return new Response(Response.SERVER_ERROR_CODE, "upload video fail");
        }

        return new Response(Response.SUCCESS_CODE, "upload video success");
    }

    @Override
    public String getVideoPath(String alarmId) {
        return alarmVideoRepository.getVideoPath(alarmId);
    }

    @Override
    public Resource getAlarmVideo(String alarmId) {
        try {
            String path = getVideoPath(alarmId);
            Resource resource = new FileUrlResource(sysVideoPath.concat("/").concat(path));
            if (resource.exists()) {
                return resource;
            }
        } catch (Exception ex) {
            logger.warn("get alarm {} video fail, ex : {}", alarmId, ex.getMessage());
        }

        return null;
    }
}
