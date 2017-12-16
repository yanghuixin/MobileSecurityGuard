package com.yhx.mobilesecurityguard.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.yhx.mobilesecurityguard.R;
import com.yhx.mobilesecurityguard.adapter.HomeAdapter;

public class HomeActivity extends Activity {

    private GridView gv_home;
    private String[] homeNames = new String[]{
            "手机防盗", "通讯卫士", "软件管理",
            "进程管理", "流量统计", "手机杀毒",
            "缓存清理", "高级工具", "设置中心"
    };

    private int[] imageIds = new int[]{
            R.mipmap.home_safe, R.mipmap.home_callmsgsafe, R.mipmap.home_apps,
            R.mipmap.home_taskmanager, R.mipmap.home_netmanager, R.mipmap.home_trojan,
            R.mipmap.home_sysoptimize, R.mipmap.home_tools,R.mipmap.home_settings
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        gv_home = findViewById(R.id.gv_home);
        gv_home.setAdapter(new HomeAdapter(this, homeNames, imageIds));
    }
}
