package org.nuaa.wa.waelder.entity;

import lombok.Data;

/**
 * @Name: AlarmEntity
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-14 06:06
 * @Version: 1.0
 */
@Data
public class AlarmEntity {
    private long deviceId;
    private String videoPath;
    private String imagePath;
    private double confidence;
}
