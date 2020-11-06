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
 * date: 2020/11/3
 * Describe:
 */
public class HotGridViewAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<ResultBeanData.ResultBean.HotInfoBean> datas;

    public HotGridViewAdapter(Context mContext, List<ResultBeanData.ResultBean.HotInfoBean> hot_info) {
        this.mContext = mContext;
        this.datas = hot_info;
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
            view = View.inflate(mContext, R.layout.item_hot_grid_view, null);
            viewHolder = new ViewHolder();
            viewHolder.iv_hot =view.findViewById(R.id.iv_hot);
            viewHolder.tv_name =view.findViewById(R.id.tv_name);
            viewHolder.tv_price =view.findViewById(R.id.tv_price);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        //根据位置设置数据
        ResultBeanData.ResultBean.HotInfoBean list = datas.get(i);
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+list.getFigure()).into(viewHolder.iv_hot);
        viewHolder.tv_name.setText(list.getName());
        viewHolder.tv_price.setText(list.getCover_price());

        return view;
    }

    static class ViewHolder {
        ImageView iv_hot;
        TextView tv_name;
        TextView tv_price;
    }
}
