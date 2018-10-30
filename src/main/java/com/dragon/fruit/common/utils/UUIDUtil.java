package com.dragon.fruit.common.utils;

import java.util.UUID;

/**
 * @see  生成UUID的工具类
 * @author  Gaofei
 * @Date 2018-10-18
 */
public class UUIDUtil {
    public static String getUuidStr(){
        String uuids = UUID.randomUUID().toString();
        uuids = uuids.replaceAll("-", "");
        return uuids;
    }
}
