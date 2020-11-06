package com.example.shoppingmall.home.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.example.shoppingmall.R;
import com.example.shoppingmall.base.BaseFragment;
import com.example.shoppingmall.home.adapter.HomeFragmentAdapter;
import com.example.shoppingmall.home.bean.ResultBeanData;
import com.example.shoppingmall.utils.Constants;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by yang
 * date: 2020/10/28
 * Describe: 主页面Fragment
 */
public class HomeFragment extends BaseFragment {

    private static final String TAG = HomeFragment.class.getSimpleName();
    private RecyclerView rvHome;
    private ImageView ib_top;
    private TextView tv_search_home;
    private TextView tv_message_home;
    //返回的数据
    private ResultBeanData.ResultBean resultData;

    private HomeFragmentAdapter adapter;

    @Override
    public View initView() {
        Log.e(TAG, "主页视图被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_home, null);

        rvHome = view.findViewById(R.id.rv_home);
        ib_top = view.findViewById(R.id.ib_top);
        tv_search_home = view.findViewById(R.id.tv_search_home);
        tv_message_home = view.findViewById(R.id.tv_message_home);
        //设置点击事件
        initListener();
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "主页数据被初始化了");

        //联网请求主页的数据
        getDataFromNet();

    }

    private void getDataFromNet() {

        String url = Constants.HOME_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    /**
                     * 当请求失败回调
                     * @param call
                     * @param e
                     * @param id
                     */
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "请求失败====" + e.getMessage());
                    }

                    /**
                     * 当联网成功时候回调
                     * @param response  请求成功的数据
                     * @param id
                     */
                    @Override
                    public void onResponse(String response, int id) {
                        //Log.e(TAG, "请求成功======" + response);
                        //解析数据
                        processData(response);
                    }
                });
    }

    private void processData(String json) {
        ResultBeanData resultBeanData = JSON.parseObject(json, ResultBeanData.class);
        resultData = resultBeanData.getResult();

        if (resultData != null) {
            //有数据
            //设置适配器
            adapter = new HomeFragmentAdapter(mContext, resultData);
            rvHome.setAdapter(adapter);

            GridLayoutManager manager = new GridLayoutManager(mContext, 1);
            //监听recyclerView的位置
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position <= 4) {
                        //隐藏
                        ib_top.setVisibility(View.GONE);
                    } else {
                        //显示
                        ib_top.setVisibility(View.VISIBLE);
                    }
                    //返回值只能是1
                    return 1;
                }
            });
            //设置布局
            rvHome.setLayoutManager(manager);

        } else {
            //没有数据
        }

        //Log.e(TAG, "解析成功====" + resultData.getChannel_info().get(0).getChannel_name());
    }

    private void initListener() {
        //置顶的监听
        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "返回顶部", Toast.LENGTH_SHORT).show();
                //回到顶部
                rvHome.scrollToPosition(0);
            }
        });
        //搜素的监听
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
            }
        });
        //消息的监听
        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "进入消息中心", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
