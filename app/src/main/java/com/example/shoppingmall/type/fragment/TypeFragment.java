package com.example.shoppingmall.type.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.shoppingmall.R;
import com.example.shoppingmall.base.BaseFragment;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yang
 * date: 2020/10/28
 * Describe: 分类面Fragment
 */
public class TypeFragment extends BaseFragment {
    private static final String TAG = "alex";

    private SegmentTabLayout segmentTabLayout;
    private ImageView iv_type_search;
    private FrameLayout fl_type;

    private List<BaseFragment> fragmentList;
    private Fragment tempFragment;
    public ListFragment listFragment;
    public TagFragment tagFragment;

    @Override
    public View initView() {
       // Log.e(TAG, "分类面的fragment的ui被创建出来");
        View view = View.inflate(mContext, R.layout.type_fragment, null);
        segmentTabLayout = view.findViewById(R.id.tl_1);
        iv_type_search = view.findViewById(R.id.iv_type_search);
        fl_type = view.findViewById(R.id.fl_type);

        return view;
    }

    /**
     * 数据
     */
    @Override
    public void initData() {
        super.initData();
       // Log.e(TAG, "分类面的fragment的数据被创建出来");

        initFragment();

        String[] titles ={"分类","标签"};
        segmentTabLayout.setTabData(titles);

        segmentTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switchFragment(tempFragment,fragmentList.get(position));
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        switchFragment(tempFragment, fragmentList.get(0));
    }

    public void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (tempFragment != nextFragment) {
            tempFragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }

                    transaction.add(R.id.fl_type, nextFragment, "tagFragment").commit();
                } else {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }


    private void initFragment() {
        fragmentList = new ArrayList<>();

        listFragment = new ListFragment();
        tagFragment = new TagFragment();

        fragmentList.add(listFragment);
        fragmentList.add(tagFragment);

        switchFragment(tempFragment, fragmentList.get(0));

    }


}
