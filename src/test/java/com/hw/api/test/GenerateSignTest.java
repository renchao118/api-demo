package com.hw.api.test;

import com.alibaba.fastjson.JSON;
import com.hw.api.utils.SignUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 参数生成sign-测试类
 */
public class GenerateSignTest {

    private String CLIENT_ID="ABAEE61B6A2629B598DF57D0F33E3F73";
    private String KEY="$2a$10$NT.GVaJ/VfhO/3v4Q.yrGewXbsp3weUSPcrma9wEN9T3d21P5Ue0S";

    @Test
    public void getUserById(){
        //测试实例
        Map<String, String> map = new HashMap<String, String>(9);
        map.put("clientId", CLIENT_ID);
        map.put("method", "qywUser_getUserById");
        map.put("id", "1");
        map.put("version", "1.0");
        map.put("timeStamp", "1561693894");//System.currentTimeMillis() / 1000+ ""
        String sign=SignUtil.generateSignature(map, KEY, SignUtil.MD5, SignUtil.MARK_KEY);
        map.put("sign",sign );
        String json = JSON.toJSONString(map);//map转String
        System.out.println("map转String:"+json);
        //打印生成sign
        System.out.println("sign:"+sign);

    }
}
