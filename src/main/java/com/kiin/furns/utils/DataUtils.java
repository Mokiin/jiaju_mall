package com.kiin.furns.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class DataUtils {

    public static <T> T copyParamToBean(Map map, T bean) {
        try {
            BeanUtils.populate(bean, map);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return bean;
    }

    public static int parseInt(String val,int defaultValue){
        int parseInt;
        try {
            parseInt = Integer.parseInt(val);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
        return parseInt;
    }
}
