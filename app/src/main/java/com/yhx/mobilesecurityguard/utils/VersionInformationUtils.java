package com.yhx.mobilesecurityguard.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/12/14.
 */

public class VersionInformationUtils {

    /**
     * 获取版本名称
     * @param content
     * @return
     */
    public static String getVersionName(Context content){
        PackageManager pm = content.getPackageManager();//包管理器
        try {
            //根据包名，获取相关信息
            PackageInfo packageInfo = pm.getPackageInfo(content.getPackageName(), 0);
            String versionName = packageInfo.versionName;//版本名称
            int versionCode = packageInfo.versionCode;//版本号
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            //包名未找到异常
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 检查版本
     */
    public static void checkVersion(){
        new Thread(){
            @Override
            public void run() {
                try {
                    //10.0.2.2是预留ip，供模拟器访问PC的服务器
                    HttpURLConnection conn= (HttpURLConnection) new URL("http://10.0.2.2:8080/update.json").openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(2000);//连接超时
                    conn.setReadTimeout(2000);//读取超时，连接上了，服务器不给响应
                    conn.connect();

                    int responseCode = conn.getResponseCode();
                    if (responseCode == 200){
                        InputStream in = conn.getInputStream();
                        String result = StreamUtils.streamToString(in);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
