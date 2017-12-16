package com.yhx.mobilesecurityguard.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yhx.mobilesecurityguard.R;
import com.yhx.mobilesecurityguard.activity.HomeActivity;

/**
 * Created by Administrator on 2017/12/16.
 */

public class HomeAdapter extends BaseAdapter {

    private Context context;
    private String[] homeNames;
    private int[] imageIds;

    private ImageView iv_list_home;
    private TextView tv_list_home;

    public HomeAdapter(Context context, String[] homeNames, int[] imageIds){
        this.context = context;
        this.homeNames = homeNames;
        this.imageIds = imageIds;
    }

    @Override
    public int getCount() {
        return homeNames.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.item_list_home, null);
        iv_list_home = view.findViewById(R.id.iv_list_home);
        tv_list_home = view.findViewById(R.id.tv_list_home);
        tv_list_home.setText(homeNames[position]);
        iv_list_home.setBackgroundResource(imageIds[position]);
        return view;
    }
}
