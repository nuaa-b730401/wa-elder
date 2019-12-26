package org.nuaa.wa.waelder.vo;

import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * @Name: UserVo
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-10-29 23:20
 * @Version: 1.0
 */
@Data
@ToString
public class UserVo {
    private long id;
    private String phone;
    private String wechatId;
    private String username;
    private String sex;
    private String address;
    private Timestamp createTime;
    private Timestamp updateTime;
}
