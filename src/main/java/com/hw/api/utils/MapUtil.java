package com.hw.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * map相关工具类
 * @date 2019/11/13 15:04
 */
public class MapUtil {

    private static ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 对象转map
     * @param obj 对象
     * @return
     */
    public static Map<?, ?> objectToMap(Object obj) {
        String mJson;
        try {
            mJson = MAPPER.writeValueAsString(obj);
            return MAPPER.readValue(mJson, HashMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_MAP;
    }

}
