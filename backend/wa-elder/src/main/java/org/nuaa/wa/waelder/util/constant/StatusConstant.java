package org.nuaa.wa.waelder.util.constant;

/**
 * @Name: StatusConstant
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-10-29 23:27
 * @Version: 1.0
 */
public class StatusConstant {
    /**
     * user status
     */
    public static final int USER_NORMAL_STATUS = 1;
    public static final int USER_UNACTIVATED_STATUS = 2;
    public static final int USER_LOCKED_STATUS = 3;

    /**
     * device status
     */
    public static final int DEVICE_ONLINE_STATUS = 1;
    public static final int DEVICE_OFFLINE_STATUS = 2;

    /**
     * device log type
     */
    public static final int DEVICE_ADD_TYPE = 1;
    public static final int DEVICE_UPDATE_TYPE = 2;
    public static final int DEVICE_DELETE_TYPE = 3;
    public static final int DEVICE_ONLINE_TYPE = 4;
    public static final int DEVICE_OFFLINE_TYPE = 5;
    public static final int DEVICE_ALARM_TYPE = 6;

    /**
     * msg status
     */
    public static final int MSG_OK_STATUS = 0;
    public static final int MSG_FAIL_STATUS = 1;
}
