package org.nuaa.wa.waelder.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author: ToMax
 * @Description:
 * @Date: Created in 2018/12/3 17:36
 */
@Data
public class Response<T> {
    /**
     * no error ret code
     */
    public static final int SUCCESS_CODE = 0;

    /**
     * normal error ret code
     */
    public static final int NORMAL_ERROR_CODE = 400;


    public static final int PERMISSION_DENIED_CODE = 401;

    /**
     * input error ret code
     */
    public static final int INPUT_ERROR_CODE = 403;

    /**
     * server error ret code
     */
    public static final int SERVER_ERROR_CODE = 500;

    /**
     * server data exists
     */
    public static final int SERVER_DATA_DUPLICATION = 501;

    /**
     * server file system error
     */
    public static final int SERVER_FILE_SYSTEM_ERROR = 502;

    /**
     * data not find
     */
    public static final int SERVER_DATA_NOT_FOUND_ERROR = 503;

    public static final int SERVER_DATABASE_ERROR = 504;

    private int code;
    private String msg;

    private T data;
    private List<T> array;
    private long count;

    public Response() {
        this.code = SUCCESS_CODE;
    }

    public Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Response(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = data != null ? 1 : 0;
    }

    public Response(int code, String msg, List<T> array) {
        this.code = code;
        this.msg = msg;
        this.array = array;
        this.count = array != null ? array.size() : 0;
    }

    public Response(int code, String msg, List<T> array, long count) {
        this.code = code;
        this.msg = msg;
        this.array = array;
        this.count = count;
    }

    public Response data(T data) {
        this.data = data;
        return this;
    }
}
