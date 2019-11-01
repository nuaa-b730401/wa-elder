package org.nuaa.wa.waelder.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: ToMax
 * @Description:
 * @Date: Created in 2018/12/18 18:05
 */
public class Md5Util {
    private static final String[] HEX_ARRAY =  new String[]{
        "0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"
    };

    /**
     * 编码
     * @param code
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encode(String code) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(code.getBytes());
        byte[] rawBit = md.digest();
        StringBuilder outputMD5 = new StringBuilder(" ");
        for(int i = 0; i<16; i++){
            outputMD5.append(HEX_ARRAY[rawBit[i]>>>4& 0x0f]);
            outputMD5.append(HEX_ARRAY[rawBit[i]& 0x0f]);
        }
        return outputMD5.toString().trim();
    }
}
