package com.yhx.mobilesecurityguard.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yhx.mobilesecurityguard.R;
import com.yhx.mobilesecurityguard.utils.PrefUtils;

public class AntiTheftSetupFourActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anti_theft_setup_four);
    }

    /**
     * 上一页
     * @param view
     */
    public void previous(View view){
        startActivity(new Intent(getApplicationContext(), AntiTheftSetupThreeActivity.class));
        finish();
    }

    /**
     * 下一页
     * @param view
     */
    public void next(View view){
        //表示已经展示过向导页，下次不再显示
        PrefUtils.putBoolean("configed", true, this);
        startActivity(new Intent(getApplicationContext(), AntiTheftActivity.class));
        finish();
    }
}
