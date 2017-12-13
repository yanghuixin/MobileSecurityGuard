package com.yhx.mobilesecurityguard.activity;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.yhx.mobilesecurityguard.R;
import com.yhx.mobilesecurityguard.utils.ToastUtils;
import com.yhx.mobilesecurityguard.utils.VersionInformationUtils;

/**
 * 闪屏页面
 *
 * 展示logo，公司品牌
 * 检查版本更新
 * 项目初始化
 * 校验合法性（检查是否有网络，检查是否登陆）
 */
public class SplashActivity extends Activity {

    private TextView tv_splash_edition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tv_splash_edition = findViewById(R.id.tv_splash_edition);
        tv_splash_edition.setText("版本名：" + VersionInformationUtils.getVersionName(this));
    }

}
