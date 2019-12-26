package org.nuaa.wa.waelder.entity;

import lombok.Data;

/**
 * @Name: MailEntity
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-19 21:19
 * @Version: 1.0
 */
@Data
public class MailEntity {
    private String recipient;
    private String subject;
    private String content;
}
