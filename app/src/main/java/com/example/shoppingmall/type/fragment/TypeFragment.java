package com.example.shoppingmall.type.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.shoppingmall.base.BaseFragment;

/**
 * Created by yang
 * date: 2020/10/28
 * Describe: 分类面Fragment
 */
public class TypeFragment extends BaseFragment {
    private static final String TAG = "alex";
    private TextView textView;

    @Override
    public View initView() {

        Log.e(TAG,"分类面的fragment的ui被创建出来");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"分类面的fragment的数据被创建出来");
        textView.setText("分类面");
    }
}
