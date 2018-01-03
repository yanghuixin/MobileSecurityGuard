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
        overridePendingTransition(R.anim.anim_previous_in, R.anim.anim_previous_out);
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
        //两个activity之间切换的动画,应该放在finish之后运行
        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
    }
}
