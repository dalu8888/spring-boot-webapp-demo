package com.digitalchina.common.utils;

import java.util.UUID;

/**
 * 主键生成
 *
 * Created by hongkai on 2016/8/16.
 */
public class UUIDUtil {
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
