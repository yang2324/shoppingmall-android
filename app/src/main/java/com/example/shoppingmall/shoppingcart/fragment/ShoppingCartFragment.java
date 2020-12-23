package com.example.shoppingmall.shoppingcart.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingmall.R;
import com.example.shoppingmall.base.BaseFragment;
import com.example.shoppingmall.home.bean.GoodsBean;
import com.example.shoppingmall.shoppingcart.adapter.ShoppingCartAdapter;
import com.example.shoppingmall.shoppingcart.utils.CartStorage;

import java.util.List;

/**
 * Created by yang
 * date: 2020/10/28
 * Describe: 购物车面Fragment
 */
public class ShoppingCartFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "alex";

    private TextView tvShopcartEdit;
    private RecyclerView recyclerview;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private CheckBox cbAll;
    private Button btnDelete;
    private Button btnCollection;

    private LinearLayout ll_empty_shopcart;
    private ImageView ivEmpty;
    private TextView tvEmptyCartTobuy;

    private ShoppingCartAdapter adapter;

    //编辑状态
    private static final int ACTION_EDIT = 1;
    //完成状态
    private static final int ACTION_COMPLETE = 2;

    @Override
    public View initView() {
        Log.e(TAG, "购物车面的fragment的ui被创建出来");

        View view = View.inflate(mContext, R.layout.fragment_shopping_cart, null);

        tvShopcartEdit = view.findViewById(R.id.tv_shopcart_edit);
        recyclerview = view.findViewById(R.id.recyclerview);
        llCheckAll = view.findViewById(R.id.ll_check_all);
        checkboxAll = view.findViewById(R.id.checkbox_all);
        tvShopcartTotal = view.findViewById(R.id.tv_shopcart_total);
        btnCheckOut = view.findViewById(R.id.btn_check_out);
        llDelete = view.findViewById(R.id.ll_delete);
        cbAll = view.findViewById(R.id.cb_all);
        btnDelete = view.findViewById(R.id.btn_delete);
        btnCollection = view.findViewById(R.id.btn_collection);

        ll_empty_shopcart = view.findViewById(R.id.ll_empty_shopcart);
        ivEmpty = view.findViewById(R.id.iv_empty);
        tvEmptyCartTobuy = view.findViewById(R.id.tv_empty_cart_tobuy);

        btnCheckOut.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnCollection.setOnClickListener(this);

        //编辑按钮的点击事件
        initListener();

        return view;
    }

    private void initListener() {
        //设置默认的编辑状态
        tvShopcartEdit.setTag(ACTION_EDIT);
        tvShopcartEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int action = (int) view.getTag();
                if (action == ACTION_EDIT) {
                    //完成状态
                    showDelete();
                } else {
                    //编辑状态
                    hideDelete();
                }
            }
        });
    }

    private void hideDelete() {
        //设置状态
        tvShopcartEdit.setTag(ACTION_EDIT);
        tvShopcartEdit.setText("编辑");
        //变成勾选
        if (adapter != null) {
            adapter.checkAll_none(true);
            adapter.checkAll();
        }
        //删除视图隐藏
        llDelete.setVisibility(View.GONE);
        //结算视图显示
        llCheckAll.setVisibility(View.VISIBLE);
    }

    private void showDelete() {
        //设置状态
        tvShopcartEdit.setTag(ACTION_COMPLETE);
        tvShopcartEdit.setText("完成");
        //变成非勾选
        if (adapter != null) {
            adapter.checkAll_none(false);
            adapter.checkAll();
            adapter.showTotalPrice();
        }
        //删除视图显示
        llDelete.setVisibility(View.VISIBLE);
        //结算视图隐藏
        llCheckAll.setVisibility(View.GONE);
    }

    //点击方法实现
    @Override
    public void onClick(View v) {
        if (v == btnCheckOut) {
            // Handle clicks for btnCheckOut
        } else if (v == btnDelete) {
            // Handle clicks for btnDelete
            //编辑状态下删除选择的
            adapter.deleteData();
            //检验
            adapter.checkAll();
            //没有数据时候
            if (adapter.getItemCount()==0){
                emptyShopCart();
            }
        } else if (v == btnCollection) {
            // Handle clicks for btnCollection
        }
    }


    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        showData();
    }


    /**
     * 显示数据
     */
    private void showData() {
        List<GoodsBean> goodsBeanList = CartStorage.getInstance().getAllData();

        if (goodsBeanList != null && goodsBeanList.size() > 0) {
            tvShopcartEdit.setVisibility(View.VISIBLE);
            checkboxAll.setVisibility(View.VISIBLE);
            //有数据
            //把显示没有数据的布局隐藏
            ll_empty_shopcart.setVisibility(View.GONE);
            //设置设配器
            adapter = new ShoppingCartAdapter(mContext, goodsBeanList, tvShopcartTotal, checkboxAll,cbAll);
            recyclerview.setAdapter(adapter);

            //设置布局构造器
            recyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        } else {
            //没有数据
            emptyShopCart();
        }
    }

    private void emptyShopCart() {
        //把显示没有数据的布局显示
        ll_empty_shopcart.setVisibility(View.VISIBLE);
        //编辑按钮隐藏
        tvShopcartEdit.setVisibility(View.GONE);
        //删除按钮隐藏
        llDelete.setVisibility(View.GONE);
    }

}
