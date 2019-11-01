package org.nuaa.wa.waelder.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @Author: ToMax
 * @Description:
 * @Date: Created in 2018/12/18 17:56
 */
public class Base64Util {
    private static final Base64.Decoder BASE_64_DECODER = Base64.getDecoder();
    private static final Base64.Encoder BASE_64_ENCODER = Base64.getEncoder();
    /**
     * 编码
     * @param code
     * @return
     */
    public static String encode(String code) throws UnsupportedEncodingException {
        return BASE_64_ENCODER.encodeToString(code.getBytes("UTF-8"));
    }

    /**
     * 解码
     * @param code
     * @return
     */
    public static String decode(String code) throws UnsupportedEncodingException {
        return new String(BASE_64_DECODER.decode(code), "UTF-8");
    }
}
