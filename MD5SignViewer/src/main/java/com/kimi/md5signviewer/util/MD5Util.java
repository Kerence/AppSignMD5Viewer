package com.kimi.md5signviewer.util;

/**
 * 创建日期：2012-10-23下午8:56:49
 * <p/>
 * 修改日期：
 * <p/>
 * 作者：zxx
 * <p/>
 * TODO
 * <p/>
 * return
 */

import java.security.MessageDigest;

public class MD5Util {
    public final static String MD5(byte[] btInput) {
        if (btInput == null) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final static String MD5(String s) {
        return MD5(s.getBytes());
    }
}
