package org.nuaa.wa.waelder.client;

/**
 * @Name: AlarmNotifyClient
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-10-28 23:18
 * @Version: 1.0
 */
public interface AlarmNotifyClient {
    int sendAlarmMsg(String account, String msg);
}
