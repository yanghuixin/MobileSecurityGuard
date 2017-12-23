package com.yhx.mobilesecurityguard.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yhx.mobilesecurityguard.R;
import com.yhx.mobilesecurityguard.utils.PrefUtils;

/**
 * 手机防盗页面
 */
public class AntiTheftActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断是否第一次进入
        if (!PrefUtils.getBoolean("configed", false, this)){
            //进入设置向导页面
            startActivity(new Intent(this, AntiTheftSetupActivity.class));
            finish();
        }else {
            setContentView(R.layout.activity_anti_theft);
        }
    }

    /**
     * 重新进入设置向导
     * @param view
     */
    public void reSetup(View view) {
        startActivity(new Intent(this, AntiTheftSetupActivity.class));
        finish();
    }
}
