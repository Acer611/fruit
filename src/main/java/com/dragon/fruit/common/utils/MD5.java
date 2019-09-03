package com.dragon.fruit.common.utils;

import java.security.MessageDigest;

/**
 * User: rizenguo
 * Date: 2014/10/23
 * Time: 15:43
 */
public class MD5 {
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 转换字节数组为16进制字串
     * @param b 字节数组
     * @return 16进制字串
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) {
            resultSb.append(byteToHexString(aB));
        }
        return resultSb.toString();
    }

    /**
     * 转换byte到16进制
     * @param b 要转换的byte
     * @return 16进制格式
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * MD5编码
     * @param origin 原始字符串
     * @return 经过MD5加密之后的结果
     */
    public static String MD5Encode(String origin) {
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }


        // 对字符串进行MD5编码
        public static String encodeByMD5(String originstr) {
            String resultString = "";
            if (originstr != null) {
                try {
                    // 创建具有指定算法名称的信息摘要
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    // 使用指定的字节数组对摘要进行最后的更新，然后完成摘要计算
                    byte[] results = md.digest(originstr.getBytes());
                    // 将得到的字节数组编程字符窜返回
                    resultString = byteArrayToHexString(results);
                    return resultString.toUpperCase();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            return resultString.toUpperCase();
        }

}
