package com.yhx.mobilesecurityguard.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.yhx.mobilesecurityguard.R;
import com.yhx.mobilesecurityguard.utils.ToastUtils;

/**
 * Created by Administrator on 2018/1/4.
 */

public abstract class BaseSetupActivity extends Activity {
    //手势识别器
    private GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
            /**
             * 快速滑动
             * @param e1：起点坐标
             * @param e2：终点坐标
             * @param velocityX：水平滑动速度
             * @param velocityY：竖直滑动速度
             * @return
             */
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (Math.abs(e2.getRawY() - e1.getRawY()) > 100){//竖直方向滑动范围
                    ToastUtils.showToast(getApplicationContext(), "您的滑动范围太大，请重新滑动");
                    return true;
                }
                if (Math.abs(velocityX) < 100){
                    ToastUtils.showToast(getApplicationContext(), "您的滑动太慢，请重新滑动");
                    return true;
                }
                //判断向左滑还是向右滑
                //e1.getX();//相对父控件的X坐标
                //e1.getRawX();//屏幕的绝对坐标
                if (e2.getRawX() - e1.getRawX() > 200){//向右划，上一页
                    showPrevious();
                    return true;
                } else if (e1.getRawX() - e2.getRawX() > 200){//向左划，下一页
                    showNext();
                    return true;
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }

    /**
     * 上一页
     * @param view
     */
    private void previous(View view){
        showPrevious();
    }

    /**
     * 下一页
     * @param view
     */
    private void next(View view){
        showNext();
    }

    /**
     * 跳转上一页
     */
    public abstract void showPrevious();

    /**
     * 跳转下一页
     */
    public abstract void showNext();

    /**
     * 当前页面被触摸时，执行此方法
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("获取到的手势识别器：" + detector);
        detector.onTouchEvent(event);//将事件委托给手势识别器处理
        return super.onTouchEvent(event);
    }
}
