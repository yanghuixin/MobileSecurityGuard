package com.yhx.mobilesecurityguard.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import com.yhx.mobilesecurityguard.R;
import com.yhx.mobilesecurityguard.utils.ToastUtils;
import com.yhx.mobilesecurityguard.utils.VersionInformationUtils;

import org.json.JSONException;
import org.json.JSONObject;

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
    //10.0.2.2是预留ip，供模拟器访问PC的服务器
    private final static String JSONURL = "http://192.168.2.101:8080/update66.json";
    private final static int CODE_UPDATE_DIALOG = 1;
    private final static int CODE_ENTER_HOME = 2;

    //版本信息
    private String versionName;
    private int versionCode;
    private String des;
    private String url;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case CODE_UPDATE_DIALOG:
                    showUpdateDialog();
                    break;
                case CODE_ENTER_HOME:
                    enterHome();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tv_splash_edition = findViewById(R.id.tv_splash_edition);
        tv_splash_edition.setText("版本名：" + VersionInformationUtils.getVersionName(this));
        checkVersion();
    }

    private void checkVersion(){
        new Thread(){
            @Override
            public void run() {
                String result = VersionInformationUtils.getResult(JSONURL);
                Message msg = Message.obtain();
                try {
                    //解析json
                    JSONObject json = new JSONObject(result);
                    versionName = json.getString("versionName");
                    versionCode = json.getInt("versionCode");
                    des = json.getString("des");
                    url = json.getString("url");
                    if (VersionInformationUtils.getVersionCode(SplashActivity.this) < versionCode){
                        //showUpdateDialog();
                        msg.what = CODE_UPDATE_DIALOG;
                    } else{
                        msg.what = CODE_ENTER_HOME;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        Thread.sleep(2000);//强制等待一段时间
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendMessage(msg);
                }
            }
        }.start();
    }

    /**
     * 升级弹窗
     */
    private void showUpdateDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("发现新版本" + versionName);
        builder.setMessage(des);
        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("以后再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    /**
     * 跳转到主页面
     */
    private void enterHome(){
        startActivity(new Intent(this, HomeActivity.class));
    }
}
