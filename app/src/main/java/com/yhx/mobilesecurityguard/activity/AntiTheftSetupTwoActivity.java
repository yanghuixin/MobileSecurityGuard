package com.yhx.mobilesecurityguard.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.yhx.mobilesecurityguard.R;
import com.yhx.mobilesecurityguard.utils.PrefUtils;
import com.yhx.mobilesecurityguard.view.SettingItemView;

/**
 * 设置向导页面
 */
public class AntiTheftSetupTwoActivity extends BaseSetupActivity {

    private SettingItemView siv_theft_setup_two_update;
    private Button bt_theft_setup_twoprevious;
    private Button bt_theft_setup_twonext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anti_theft_setup_two);
        siv_theft_setup_two_update = findViewById(R.id.siv_theft_setup_two_update);
        bt_theft_setup_twoprevious = findViewById(R.id.bt_theft_setup_twoprevious);
        bt_theft_setup_twonext = findViewById(R.id.bt_theft_setup_twonext);
        initUpdate();
    }

    private void initUpdate(){
        siv_theft_setup_two_update.setTitle("点击绑定sim卡");
        if (PrefUtils.getBoolean("auto_update", true, this)){
            siv_theft_setup_two_update.setChecked(true);
            siv_theft_setup_two_update.setDesc("sim卡已绑定");
        }else {
            siv_theft_setup_two_update.setChecked(false);
            siv_theft_setup_two_update.setDesc("sim卡未绑定");
        }

        siv_theft_setup_two_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (siv_theft_setup_two_update.isChecked()){
                    siv_theft_setup_two_update.setChecked(false);
                    siv_theft_setup_two_update.setDesc("sim卡未绑定");
                    PrefUtils.putBoolean("auto_update", false, getApplicationContext());
                }else {
                    siv_theft_setup_two_update.setChecked(true);
                    siv_theft_setup_two_update.setDesc("sim卡已绑定");
                    PrefUtils.putBoolean("auto_update", true, getApplicationContext());
                }
            }
        });
    }

    /**
     * 跳转上一页
     */
    public void showPrevious(){
        startActivity(new Intent(getApplicationContext(), AntiTheftSetupActivity.class));
        finish();
        overridePendingTransition(R.anim.anim_previous_in, R.anim.anim_previous_out);
    }

    /**
     * 跳转下一页
     */
    public void showNext(){
        startActivity(new Intent(getApplicationContext(), AntiTheftSetupThreeActivity.class));
        finish();
        //两个activity之间切换的动画,应该放在finish之后运行
        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
    }
}
