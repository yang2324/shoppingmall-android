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
 * date: 2020/10/30
 * Describe:    recommend 推荐适配器
 */
public class ChannelAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<ResultBeanData.ResultBean.ChannelInfoBean> datas;


    public ChannelAdapter(Context mContext, List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
        this.mContext = mContext;
        this.datas = channel_info;
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
            view = View.inflate(mContext, R.layout.item_channel, null);
            viewHolder = new ViewHolder();
            viewHolder.iv_icon = view.findViewById(R.id.iv_channel);
            viewHolder.tv_text = view.findViewById(R.id.tv_channel);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        ResultBeanData.ResultBean.ChannelInfoBean listBeanXXX = datas.get(i);
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+ listBeanXXX.getImage()).into(viewHolder.iv_icon);
        viewHolder.tv_text.setText(listBeanXXX.getChannel_name());
        return view;
    }

    static class ViewHolder {
        ImageView iv_icon;
        TextView tv_text;
    }
}
