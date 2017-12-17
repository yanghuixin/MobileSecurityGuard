package com.yhx.mobilesecurityguard.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yhx.mobilesecurityguard.R;

/**
 * 设置向导页面
 */
public class AntiTheftSetupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anti_theft_setup);
        Button bt_anti_theft_setup_next = findViewById(R.id.bt_anti_theft_setup_next);
        bt_anti_theft_setup_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AntiTheftSetupTwoActivity.class));
            }
        });
    }
}
