package com.wisewin.backend.util;

import java.lang.reflect.Field;

/**
 * 对象参数空值判断工具类
 */
public class ParamNullUtil {
    public static boolean checkObjAllFieldsIsNull(Object object) {
        if (null == object) {
            return true;
        }

        try {
            for (Field f : object.getClass().getDeclaredFields()) {
                f.setAccessible(true);

//                System.out.print(f.getName() + ":");
//                System.out.println(f.get(object));

                if (f.get(object) != null && StringUtils.isNotBlank(f.get(object).toString())) {
                    return false;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }
}
