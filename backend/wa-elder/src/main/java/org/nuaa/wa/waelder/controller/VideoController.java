package org.nuaa.wa.waelder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nuaa.wa.waelder.aop.Permission;
import org.nuaa.wa.waelder.aop.ServiceLog;
import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Name: VideoController
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-20 18:47
 * @Version: 1.0
 */
@Controller
@CrossOrigin
@RequestMapping("/video")
@Api("告警视频接口")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping("/upload")
    public @ResponseBody
    Response upload(MultipartFile video, long deviceId, String alarmId) {
        return videoService.upload(video, deviceId, alarmId);
    }

    @GetMapping("/download/{alarmid}")
//    @Permission("user:video:query")
    @ServiceLog(name = "downloadAlarmVideo", description = "download alarm video")
    @ApiOperation(value = "download alarm video", notes = "下载告警视频")
    public @ResponseBody
    ResponseEntity<Resource> downloadAlarmVideo(
            @PathVariable(name = "alarmid") String alarmId,
            HttpServletRequest request) throws UnsupportedEncodingException {
        Resource resource = videoService.getAlarmVideo(alarmId);
        if (resource == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (Exception ex) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" +
                                URLEncoder.encode(resource.getFilename(),
                                        "UTF-8") + "\"")
                .body(resource);
    }
}
