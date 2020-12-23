package com.example.shoppingmall.type.fragment;

import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingmall.R;
import com.example.shoppingmall.base.BaseFragment;
import com.example.shoppingmall.type.adapter.TagGridViewAdapter;
import com.example.shoppingmall.type.bean.TagBean;
import com.example.shoppingmall.utils.Constants;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by yang
 * date: 2020/11/9
 * Describe:
 */
public class TagFragment extends BaseFragment {

    private GridView gv_tag;
    private TagGridViewAdapter adapter;
    private List<TagBean.ResultBean> result;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_tag, null);
        gv_tag = view.findViewById(R.id.gv_tag);
        return view;
    }

    //获取数据
    @Override
    public void initData() {
        super.initData();

        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils
                .get()
                .url(Constants.TAG_URL)
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }

    public class MyStringCallback extends StringCallback {

        @Override
        public void onBefore(Request request, int id) {
        }

        @Override
        public void onAfter(int id) {
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            Log.e("TAG", "联网失败" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {

            switch (id) {
                case 100:
                    if (response != null) {
                        processData(response);
                        adapter = new TagGridViewAdapter(mContext, result);
                        gv_tag.setAdapter(adapter);
                    }
                    break;
                case 101:
                    Toast.makeText(mContext, "https", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    private void processData(String json) {
        Gson gson = new Gson();
        TagBean tagBean = gson.fromJson(json, TagBean.class);
        result = tagBean.getResult();
    }
}
