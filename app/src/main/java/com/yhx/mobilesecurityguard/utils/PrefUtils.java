package com.yhx.mobilesecurityguard.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 对SharedPreferences的封装
 * Created by Administrator on 2017/12/17.
 */

public class PrefUtils {

    public static void putBoolean(String key, boolean value, Context context){
        SharedPreferences sp = context.getSharedPreferences("config",
                Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(String key, boolean value, Context context){
        SharedPreferences sp = context.getSharedPreferences("config",
                Context.MODE_PRIVATE);
        return sp.getBoolean(key, value);
    }
}
