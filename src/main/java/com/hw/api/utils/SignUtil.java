package com.hw.api.utils;

import org.apache.commons.lang.StringUtils;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * 签名工具
 * @date 2019/11/13 10:20
 */
public class SignUtil {

    public static final String MD5 = "md5";
    public static final String SHA1 = "sha1";
    public static final String MARK_KEY = "key";
    public static final String MARK_SECRET = "secret";



    /**
     * 校验签名有效期
     * @param timestamp 时间戳 秒
     * @param outTime 过期时间 秒
     * @return
     */
    public static boolean checkSignTime(long timestamp, long outTime) {
        long currentTime = System.currentTimeMillis() / 1000;
        return Math.abs(currentTime - timestamp) > outTime;
    }


    /**
     * 生成签名.
     *
     * @param data 待签名数据
     * @param key API密钥
     * @param signType 签名方式
     * @param mark 密钥键
     * @return 签名
     */
    public static String generateSignature(final Map<String, String> data, String key, String signType, String mark) {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        String value;
        for (String k : keyArray) {
            // 参数值为空,或者sign不参与签名
            if (!k.equalsIgnoreCase("sign") ){
                value = String.valueOf(data.get(k));
                if (StringUtils.isNotBlank(value) && value.trim().length() > 0 && !value.trim().equals("null")) {
                    sb.append(k).append("=").append(value.trim()).append("&");
                }
            }
        }
        sb.append(mark + "=").append(key);
        if (MD5.equals(signType)) {
            try {
                return MD5(sb.toString()).toUpperCase();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }


    /**
     * 生成 MD5
     *
     * @param data 待处理数据
     * @return MD5结果
     */
    public static String MD5(String data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }



    /**
     * SHA1 安全加密算法
     * @param data 待处理数据
     * @return str
     * @throws Exception e
     */
    public static String SHA1(String data) throws Exception {
        //指定sha1算法
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        digest.update(data.getBytes("UTF-8"));
        //获取字节数组
        byte messageDigest[] = digest.digest();
        // Create Hex String
        StringBuilder hexString = new StringBuilder();
        // 字节数组转换为 十六进制 数
        for (int i = 0; i < messageDigest.length; i++) {
            String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
            if (shaHex.length() < 2) {
                hexString.append(0);
            }
            hexString.append(shaHex);
        }
        return hexString.toString().toUpperCase();

    }


}
