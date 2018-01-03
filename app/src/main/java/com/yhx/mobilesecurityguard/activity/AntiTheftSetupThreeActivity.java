package com.yhx.mobilesecurityguard.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yhx.mobilesecurityguard.R;

public class AntiTheftSetupThreeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anti_theft_setup_three);
    }

    /**
     * 上一页
     * @param view
     */
    public void previous(View view){
        startActivity(new Intent(getApplicationContext(), AntiTheftSetupTwoActivity.class));
        finish();
        overridePendingTransition(R.anim.anim_previous_in, R.anim.anim_previous_out);
    }

    /**
     * 下一页
     * @param view
     */
    public void next(View view){
        startActivity(new Intent(getApplicationContext(), AntiTheftSetupFourActivity.class));
        finish();
        //两个activity之间切换的动画,应该放在finish之后运行
        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
    }
}
