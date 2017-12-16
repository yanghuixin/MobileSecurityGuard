package com.yhx.mobilesecurityguard.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 强制获取焦点的TextView
 * Created by Administrator on 2017/12/16.
 */

public class FocusedTextView extends TextView {

    //从代码中new对象
    public FocusedTextView(Context context) {
        super(context);
    }

    //当布局文件中有属性时，系统底层解析时，执行此构造方法
    public FocusedTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //当布局文件中有属性和样式时，系统底层解析时，执行此构造方法
    public FocusedTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;//强制让TextView获取焦点
    }
}
