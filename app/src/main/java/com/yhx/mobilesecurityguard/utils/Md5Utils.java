package com.yhx.mobilesecurityguard.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2017/12/17.
 */

public class Md5Utils {

    /**
     * md5加密
     * @param password
     * @return
     */
    public static String getMd5Encode(String password){
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            //进行加密运算，返回加密后的字节数组
            byte[] bytes = digest.digest(password.getBytes());
            StringBuffer sb = new StringBuffer();
            for (byte b: bytes) {
                int i = b & 0xff;
                String hexString = Integer.toHexString(i);
                if (hexString.length() == 1){
                    hexString = "0" + hexString;
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            //没有此算法异常
            e.printStackTrace();
        }
        return null;
    }
}
