package com.pan.dev.springbootmybatis.util;

/**
 * @Author:panliang
 * @Description:
 * @params:$params$
 * @return: $returns$
 * @Date: $date$ $time$
 */
public class CodeEnumUtil {

    public static <E extends Enum<?> & EnumBehaviour> E codeOf(Class<E> enumClass, String code) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getValue().equals(code))
                return e;
        }
        return null;
    }
}
