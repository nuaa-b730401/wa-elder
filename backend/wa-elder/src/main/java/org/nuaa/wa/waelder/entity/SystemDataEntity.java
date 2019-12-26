package org.nuaa.wa.waelder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Name: SystemDataEntity
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-20 21:27
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SystemDataEntity {
    private long adminUserNum;
    private long userNum;
    private long deviceNum;
    private long alarmNum;
}
