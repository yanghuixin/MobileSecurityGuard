package com.yhx.mobilesecurityguard.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yhx.mobilesecurityguard.R;
import com.yhx.mobilesecurityguard.utils.PrefUtils;
import com.yhx.mobilesecurityguard.view.SettingItemView;

public class SettingActivity extends Activity {

    private SettingItemView siv_update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        siv_update = (SettingItemView) findViewById(R.id.siv_update);
        initUpdate();
    }

    private void initUpdate(){
        siv_update.setTitle("自动更新设置");
        if (PrefUtils.getBoolean("auto_update", true, this)){
            siv_update.setChecked(true);
            siv_update.setDesc("自动更新已开启");
        }else {
            siv_update.setChecked(false);
            siv_update.setDesc("自动更新已关闭");
        }

        siv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (siv_update.isChecked()){
                    siv_update.setChecked(false);
                    siv_update.setDesc("自动更新已关闭");
                    PrefUtils.putBoolean("auto_update", false, getApplicationContext());
                }else {
                    siv_update.setChecked(true);
                    siv_update.setDesc("自动更新已开启");
                    PrefUtils.putBoolean("auto_update", true, getApplicationContext());
                }
            }
        });
    }
}
