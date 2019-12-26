package org.nuaa.wa.waelder.client;

import org.nuaa.wa.waelder.entity.MailEntity;

/**
 * @Name: MailClient
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-12-19 21:20
 * @Version: 1.0
 */
public interface MailClient {
    void sendSimpleMail(MailEntity mail);
}
