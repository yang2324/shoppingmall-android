package com.example.shoppingmall.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shoppingmall.R;
import com.example.shoppingmall.home.bean.ResultBeanData;
import com.example.shoppingmall.utils.Constants;

import java.util.List;

/**
 * Created by yang
 * date: 2020/11/2
 * Describe:
 */
public class RecommendGridViewAdapter extends BaseAdapter {

    private final Context mContent;
    private final List<ResultBeanData.ResultBean.RecommendInfoBean> datas;

    public RecommendGridViewAdapter(Context mContent, List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
        this.mContent = mContent;
        this.datas = recommend_info;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = View.inflate(mContent, R.layout.item_recommend_grid_view, null);
            viewHolder = new ViewHolder();
            viewHolder.iv_recommend = view.findViewById(R.id.iv_recommend);
            viewHolder.tv_name = view.findViewById(R.id.tv_name);
            viewHolder.tv_price = view.findViewById(R.id.tv_price);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        //根据位置拿到数据
        ResultBeanData.ResultBean.RecommendInfoBean recommend = datas.get(i);
        Glide.with(mContent).load(Constants.BASE_URL_IMAGE+recommend.getFigure()).into(viewHolder.iv_recommend);
        viewHolder.tv_name.setText(recommend.getName());
        viewHolder.tv_price.setText(recommend.getCover_price());
        return view;
    }

    static class ViewHolder {
        ImageView iv_recommend;
        TextView tv_name;
        TextView tv_price;
    }
}
