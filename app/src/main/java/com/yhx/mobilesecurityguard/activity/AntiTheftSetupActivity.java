package com.yhx.mobilesecurityguard.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yhx.mobilesecurityguard.R;

/**
 * 设置向导页面
 */
public class AntiTheftSetupActivity extends BaseSetupActivity {

    private Button bt_theft_setup_next;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anti_theft_setup);
        bt_theft_setup_next = findViewById(R.id.bt_theft_setup_next);
    }

    /**
     * 跳转上一页
     */
    @Override
    public void showPrevious() {

    }

    /**
     * 跳转下一页
     */
    @Override
    public void showNext() {
        startActivity(new Intent(getApplicationContext(), AntiTheftSetupTwoActivity.class));
        finish();
        //两个activity之间切换的动画,应该放在finish之后运行
        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
    }
}
