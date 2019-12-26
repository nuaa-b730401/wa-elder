package org.nuaa.wa.waelder.service;

import org.nuaa.wa.waelder.entity.Response;

/**
 * @Name: MailService
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-19 21:23
 * @Version: 1.0
 */
public interface MailService {
    Response sendWishMail(String content);
}
