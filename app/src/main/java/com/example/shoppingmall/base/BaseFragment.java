package com.example.shoppingmall.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by yang
 * date: 2020/10/28
 * Describe: 基类fragment
 * <p>
 * 首页：HomeFragment
 * 分类：TypeFragment
 * 发现：CommunityFragment
 * 购物车：ShoppingCartFragment
 * 用户中心：UserFragemnt
 * 都继承与BaseFragment
 */
public abstract class BaseFragment extends Fragment {

    //上下文
    protected Context mContext;

    /**
     * 但该类被系统调用时候创建
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    /**
     * 抽象类，有子实现
     *
     * @return
     */
    public abstract View initView();

    /**
     * 当activity被创建时候调用
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 当子类需要联网时候，重新该方法，在该方法中联网请求
     */
    public void initData() {
    }
}
