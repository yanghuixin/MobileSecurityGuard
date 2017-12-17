package com.yhx.mobilesecurityguard.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.yhx.mobilesecurityguard.R;
import com.yhx.mobilesecurityguard.adapter.HomeAdapter;
import com.yhx.mobilesecurityguard.utils.Md5Utils;
import com.yhx.mobilesecurityguard.utils.PrefUtils;
import com.yhx.mobilesecurityguard.utils.ToastUtils;

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
        gv_home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        //手机防盗
                        showSafeDialog();
                        break;
                    case 8:
                        //设置中心
                        startActivity(new Intent(getApplicationContext(), SettingActivity
                        .class));
                        break;
                }
            }
        });
    }

    /**
     * 手机防盗弹窗
     */
    private void showSafeDialog(){
        String pwd = PrefUtils.getString("password", null, this);
        if (!TextUtils.isEmpty(pwd)){
            //输入密码弹窗
            showInputPwdDialog();
        }else {
            //设置密码弹窗
            showSetPwdDialog();
        }
    }

    /**
     * 设置密码弹窗
     */
    private void showSetPwdDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        //给dialog设定布局
        View view = View.inflate(this, R.layout.dialog_set_pwd, null);
        dialog.setView(view,0 , 0, 0 , 0);

        Button bt_set_confirm = view.findViewById(R.id.bt_set_confirm);
        Button bt_set_cancel = view.findViewById(R.id.bt_set_cancel);
        final EditText et_set_pwd = view.findViewById(R.id.et_set_pwd);
        final EditText et_set_pwd_confirm = view.findViewById(R.id.et_set_pwd_confirm);
        bt_set_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = et_set_pwd.getText().toString().trim();
                String pwdConfirm = et_set_pwd_confirm.getText().toString().trim();
                if (!TextUtils.isEmpty(pwd) && !TextUtils.isEmpty(pwdConfirm)){
                    if (pwd.equals(pwdConfirm)){
                        //保存密码
                        PrefUtils.putString("password", Md5Utils.getMd5Encode(pwd), getApplicationContext());
                        dialog.dismiss();
                        //跳到手机防盗页面
                        startActivity(new Intent(getApplicationContext(), AntiTheftActivity.class));
                    }else {
                        ToastUtils.showToast(getApplicationContext(), "两次密码不一致");
                    }
                }else {
                    ToastUtils.showToast(getApplicationContext(), "密码不能为空");
                }
            }
        });
        bt_set_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * 输入密码弹窗
     */
    private void showInputPwdDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        //给dialog设定布局
        View view = View.inflate(this, R.layout.dialog_input_pwd, null);
        dialog.setView(view,0 , 0, 0 , 0);

        Button bt_input_confirm = view.findViewById(R.id.bt_input_confirm);
        Button bt_input_cancel = view.findViewById(R.id.bt_input_cancel);
        final EditText et_input_pwd = view.findViewById(R.id.et_input_pwd);
        bt_input_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = et_input_pwd.getText().toString().trim();
                if (!TextUtils.isEmpty(pwd)){
                    String savePwd = PrefUtils.getString("password", null,
                            getApplicationContext());
                    if (Md5Utils.getMd5Encode(pwd).equals(savePwd)){
                        //密码正确
                        dialog.dismiss();
                        //跳到手机防盗页面
                        startActivity(new Intent(getApplicationContext(), AntiTheftActivity.class));
                    }else {
                        ToastUtils.showToast(getApplicationContext(), "密码错误");
                    }
                }else {
                    ToastUtils.showToast(getApplicationContext(), "密码不能为空");
                }
            }
        });
        bt_input_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
