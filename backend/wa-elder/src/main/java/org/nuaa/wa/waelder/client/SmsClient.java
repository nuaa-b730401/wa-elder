package org.nuaa.wa.waelder.client;

/**
 * @Name: SmsClient
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-10-28 23:12
 * @Version: 1.0
 */
public interface SmsClient extends AlarmNotifyClient{
    int sendVerifyCode(String phone, String code);
}
