package com.example.shoppingmall.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
public class SeckillRecyclerViewAdapter extends RecyclerView.Adapter<SeckillRecyclerViewAdapter.ViewHolder> {

    private final Context mContext;
    private final List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> seckill_list;

    public SeckillRecyclerViewAdapter(Context mContext, List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> list) {
        this.mContext = mContext;
        this.seckill_list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_seckill, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //1.根据位置等到对应的数据
        ResultBeanData.ResultBean.SeckillInfoBean.ListBean list = seckill_list.get(position);
        //2.绑定数据
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + list.getFigure()).into(holder.iv_figure);
        //设置价格
        holder.tv_cover_price.setText(list.getCover_price());
        holder.tv_origin_price.setText(list.getOrigin_price());
    }

    @Override
    public int getItemCount() {
        return seckill_list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_figure;
        private TextView tv_cover_price;
        private TextView tv_origin_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_figure = itemView.findViewById(R.id.iv_figure);
            tv_cover_price = itemView.findViewById(R.id.tv_cover_price);
            tv_origin_price = itemView.findViewById(R.id.tv_origin_price);

            //秒杀点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // Toast.makeText(mContext,"秒杀="+getLayoutPosition(),Toast.LENGTH_SHORT).show();
                    if (onSeckillRecycleView!=null){
                        onSeckillRecycleView.OnItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    //监听器
    public interface OnSeckillRecycleView{
        /**
         * 当某条被点击时回调
         * @param position
         */
        public void OnItemClick(int position);
    }

    private OnSeckillRecycleView onSeckillRecycleView;

    /**
     * 设置item的监听
     * @param onSeckillRecycleView
     */
    public void setOnSeckillRecycleView(OnSeckillRecycleView onSeckillRecycleView) {
        this.onSeckillRecycleView = onSeckillRecycleView;
    }

}
