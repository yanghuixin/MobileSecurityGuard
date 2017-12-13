package com.yhx.mobilesecurityguard.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by yhx on 2017/12/10.
 */

public class ToastUtils {

    public static void showToast(Context context,String msg){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
