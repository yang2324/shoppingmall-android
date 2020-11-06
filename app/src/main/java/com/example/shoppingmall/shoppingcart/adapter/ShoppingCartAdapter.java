package com.example.shoppingmall.shoppingcart.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoppingmall.R;
import com.example.shoppingmall.home.bean.GoodsBean;
import com.example.shoppingmall.shoppingcart.utils.CartStorage;
import com.example.shoppingmall.shoppingcart.view.AddSubView;
import com.example.shoppingmall.utils.Constants;

import java.util.List;

/**
 * Created by yang
 * date: 2020/11/5
 * Describe: 适配器的构造方法
 */
public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {

    private final Context mContext;
    private final List<GoodsBean> datas;
    private final TextView tvShopcartTotal;
    private final CheckBox checkboxAll;
    private final CheckBox cbAll;


    public ShoppingCartAdapter(Context mContext, List<GoodsBean> goodsBeanList, TextView tvShopcartTotal, CheckBox checkboxAll, CheckBox cbAll) {
        this.mContext = mContext;
        this.datas = goodsBeanList;
        this.tvShopcartTotal = tvShopcartTotal;
        this.checkboxAll = checkboxAll;
        this.cbAll = cbAll;

        showTotalPrice();
        //设置点击事件
        setListener();
        //检验是否全选
        checkAll();
    }

    private void setListener() {
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                //1.根据位置获取bean对象
                GoodsBean goodsBean = datas.get(position);
                //2.取反
                goodsBean.setSelect(!goodsBean.isSelect());
                //3.刷新
                notifyItemChanged(position);
                //4.检验是否全选
                checkAll();
                //5.再次显示总价
                showTotalPrice();
            }
        });

        //checkboxAll的点击事件
        checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1.得到状态
                boolean isCheck = checkboxAll.isChecked();
                //2.根据状态全选非全选
                checkAll_none(isCheck);
                //3.计算总价
                showTotalPrice();
            }
        });

        //cbAll的点击事件
        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1.得到状态
                boolean isCheck = cbAll.isChecked();
                //2.根据状态全选非全选
                checkAll_none(isCheck);
            }
        });
    }

    public void checkAll_none(boolean isCheck) {
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                goodsBean.setSelect(isCheck);
                notifyItemChanged(i);
            }
        }
    }

    //全选
    public void checkAll() {
        if (datas != null && datas.size() > 0) {
            int number = 0;
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                if (!goodsBean.isSelect()) {
                    //非全选
                    checkboxAll.setChecked(false);
                    cbAll.setChecked(false);
                } else {
                    number++;
                }
            }
            if (number == datas.size()) {
                //全选
                checkboxAll.setChecked(true);
                cbAll.setChecked(true);
            }
        } else {
            checkboxAll.setChecked(false);
            cbAll.setChecked(false);
        }
    }

    public void showTotalPrice() {
        tvShopcartTotal.setText("￥" + getTotalPrice());
    }

    private Double getTotalPrice() {
        double totalPrice = 0.0;
        //判断是否有数据
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                //选择情况下
                if (goodsBean.isSelect()) {
                    totalPrice = totalPrice + Double.valueOf(goodsBean.getNumber()) * Double.valueOf(goodsBean.getCover_price());
                }
            }
        }
        return totalPrice;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_shop_cart, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //根据位置得到对应的数据
        GoodsBean goodsBean = datas.get(position);
        //绑定数据
        holder.cb_gov.setChecked(goodsBean.isSelect());
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + goodsBean.getFigure()).into(holder.iv_gov);
        holder.tv_desc_gov.setText(goodsBean.getName());
        holder.tv_price_gov.setText("￥" + goodsBean.getCover_price());

        holder.ddSubView.setValue(goodsBean.getNumber());
        holder.ddSubView.setMinValue(1);
        holder.ddSubView.setMaxValue(10);

        //设置商品数量的变化
        holder.ddSubView.setOnNumberChangeListener(new AddSubView.OnNumberChangeListener() {
            @Override
            public void OnNumberChange(int value) {
                //1.同步内存
                goodsBean.setNumber(value);
                //2.同步本地
                CartStorage.getInstance().updateData(goodsBean);
                //3.刷新
                notifyItemChanged(position);
                //4.再次显示总价格
                showTotalPrice();
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void deleteData() {
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                //选择删除
                if (goodsBean.isSelect()) {
                    //内存-删除
                    datas.remove(goodsBean);
                    //本地删除
                    CartStorage.getInstance().deleteData(goodsBean);
                    //刷新
                    notifyItemRemoved(i);
                    i--;
                }
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox cb_gov;
        private ImageView iv_gov;
        private TextView tv_desc_gov;
        private TextView tv_price_gov;
        private AddSubView ddSubView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cb_gov = itemView.findViewById(R.id.cb_gov);
            iv_gov = itemView.findViewById(R.id.iv_gov);
            tv_desc_gov = itemView.findViewById(R.id.tv_desc_gov);
            tv_price_gov = itemView.findViewById(R.id.tv_price_gov);
            ddSubView = itemView.findViewById(R.id.ddSubView);

            //设置item点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.OnItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    /**
     * 监听每一个item
     */
    public interface OnItemClickListener {
        //点击某条时候被回调
        public void OnItemClick(int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
