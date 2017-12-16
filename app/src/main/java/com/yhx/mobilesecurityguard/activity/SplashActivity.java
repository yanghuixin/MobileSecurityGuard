package com.yhx.mobilesecurityguard.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.yhx.mobilesecurityguard.R;
import com.yhx.mobilesecurityguard.utils.ToastUtils;
import com.yhx.mobilesecurityguard.utils.VersionInformationUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

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
    private TextView tv_progress;

    //10.0.2.2是预留ip，供模拟器访问PC的服务器
    private final static String JSONURL = "http://192.168.2.101:8080/update66.json";
    private final static int CODE_UPDATE_DIALOG = 1;
    private final static int CODE_ENTER_HOME = 2;
    private final static int CODE_ERROR = 3;

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
                case CODE_ERROR:
                    ToastUtils.showToast(getApplicationContext(), "数据加载异常");
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

        tv_progress = findViewById(R.id.tv_progress);

        checkVersion();
    }

    private void checkVersion(){
        new Thread(){
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
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
                    msg.what = CODE_ERROR;
                    e.printStackTrace();
                } finally {
                    long endTime = System.currentTimeMillis();
                    long timeUsed = endTime - startTime;//访问网络使用的时间
                    try {
                        if (timeUsed < 2000){
                            Thread.sleep(2000 - timeUsed);//强制等待一段时间,凑够两秒钟
                        }
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//这个必须要传一个activity对象
        builder.setTitle("发现新版本" + versionName);
        builder.setMessage(des);
        //builder.setCancelable(false);//不可取消，点返回键弹窗不消失，尽量不要用
        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                downloadApk();
            }
        });
        builder.setNegativeButton("以后再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                enterHome();
            }
        });
        //用户取消弹框的监听，比如点击返回键
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                enterHome();
            }
        });
        builder.show();
    }

    /**
     * 跳转到主页面
     */
    private void enterHome(){
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    /**
     * 下载应用
     * 权限：<uses-permission android:name="android.permission.INTERNET" />
     *<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
     */
    public void downloadApk(){
        //判断sdcard是否存在
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            tv_progress.setVisibility(View.VISIBLE);//显示进度
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/mobileSecurity.apk";
            //Xutils
            HttpUtils utils = new HttpUtils();
            utils.download(url, path, new RequestCallBack<File>() {

                @Override
                public void onLoading(long total, long current, boolean isUploading) {
                    super.onLoading(total, current, isUploading);
                    //下载进度
                    int percent = (int) (100 * current / total);
                    tv_progress.setText("下载进度：" + percent + "%");
                }

                @Override
                public void onSuccess(ResponseInfo<File> responseInfo) {
                    //下载成功
                    ToastUtils.showToast(getApplicationContext(), responseInfo.result.getAbsolutePath());
                    //跳转系统安装页面
                    jumpInstallationApplication(responseInfo.result);
                }

                @Override
                public void onFailure(HttpException error, String msg) {
                    //下载失败
                    error.printStackTrace();
                    ToastUtils.showToast(getApplicationContext(), msg);
                }
            });
        }else {
            ToastUtils.showToast(getApplicationContext(), "未找到sd卡");
        }
    }

    /**
     * 跳转到系统安装页面
     * @param file
     */
    public void jumpInstallationApplication(File file){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        startActivityForResult(intent, 0);
    }

    //用户取消安装应用，回调此方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        enterHome();
    }
}
