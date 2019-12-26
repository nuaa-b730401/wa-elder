package org.nuaa.wa.waelder.util;

import org.apache.juli.logging.Log;
import org.nuaa.wa.waelder.util.constant.LogLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Name: ConvertUtil
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-11-15 11:51
 * @Version: 1.0
 */
public class ConvertUtil {
    private static Logger logger = LoggerFactory.getLogger(LogLevel.SERVICE);

    public static <T> T convertObject(Object src, Class<T> clazz) {
        try {
            T target = clazz.newInstance();
            BeanUtils.copyProperties(src, target);

            return target;
        } catch (Exception ex) {
            logger.warn("covert object fail, ex = {}", ex.getMessage());
        }

        return null;
    }

    public static <T, S> List<T> convertList(List<S> srcList, Class<T> clazz) {
        try {
            List<T> targetList = new ArrayList<>(srcList.size());
            for (S src : srcList) {
                T target = clazz.newInstance();
                BeanUtils.copyProperties(src, target);
                targetList.add(target);
            }

            return targetList;
        } catch (Exception ex) {
            logger.warn("covert object fail, ex = {}", ex.getMessage());
        }

        return null;
    }
}
