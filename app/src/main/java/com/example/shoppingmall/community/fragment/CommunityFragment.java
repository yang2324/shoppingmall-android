package com.example.shoppingmall.community.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.shoppingmall.base.BaseFragment;

/**
 * Created by yang
 * date: 2020/10/28
 * Describe: 发现面Fragment
 */
public class CommunityFragment extends BaseFragment {
    private static final String TAG = "alex";
    private TextView textView;

    @Override
    public View initView() {

        Log.e(TAG,"发现面的fragment的ui被创建出来");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"发现面的fragment的数据被创建出来");
        textView.setText("发现");
    }
}
