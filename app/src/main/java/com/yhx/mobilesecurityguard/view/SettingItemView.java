package com.yhx.mobilesecurityguard.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yhx.mobilesecurityguard.R;

/**
 * Created by Administrator on 2017/12/16.
 */

public class SettingItemView extends RelativeLayout {

    private TextView tv_title;
    private TextView tv_desc;
    private CheckBox cb_check;
    public SettingItemView(Context context) {
        super(context);
        initView();
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SettingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public SettingItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView(){
        //初始化组合控件布局
        View view = View.inflate(getContext(), R.layout.item_setting_view, null);

        tv_title = view.findViewById(R.id.tv_title);
        tv_desc = view.findViewById(R.id.tv_desc);
        cb_check = view.findViewById(R.id.cb_check);

        //将布局添加给当前的RelativeLayout对象
        this.addView(view);
    }

    /**
     * 设置标题
     * @param title
     */
    public void setTitle(String title){
        tv_title.setText(title);
    }

    /**
     * 设置描述
     * @param desc
     */
    public void setDesc(String desc){
        tv_desc.setText(desc);
    }

    /**
     * 判断是否勾选
     * @return
     */
    public boolean isChecked(){
        return cb_check.isChecked();
    }

    /**
     * 设置选中状态
     * @param checked
     */
    public void setChecked(boolean checked){
        cb_check.setChecked(checked);
    }
}
