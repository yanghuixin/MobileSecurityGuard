package com.yhx.mobilesecurityguard.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/12/14.
 */

public class StreamUtils {

    /**
     * 把输入流转换成字符串
     * @param in
     * @return
     * @throws IOException
     */
    public static String streamToString(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = in.read(buffer)) != -1){
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
        return out.toString();
    }
}
