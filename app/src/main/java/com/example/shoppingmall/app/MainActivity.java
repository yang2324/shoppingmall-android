package com.example.shoppingmall.app;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.shoppingmall.R;
import com.example.shoppingmall.base.BaseFragment;
import com.example.shoppingmall.community.fragment.CommunityFragment;
import com.example.shoppingmall.home.fragment.HomeFragment;
import com.example.shoppingmall.shoppingcart.fragment.ShoppingCartFragment;
import com.example.shoppingmall.type.fragment.TypeFragment;
import com.example.shoppingmall.user.fragment.UserFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends FragmentActivity {


    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;

    //装多个fragment的实列集合
    private ArrayList<BaseFragment> fragments;
    private int position = 0;
    //缓存fragment
    private Fragment tempFragemnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //初始化fragment
        initFragment();
        //设置RadioGroup的监听
        initListener();

    }

    private void initListener() {

        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_home:      //主页
                        position = 0;
                        break;
                    case R.id.rb_type:      //分类
                        position = 1;
                        break;
                    case R.id.rb_community:     //发现
                        position = 2;
                        break;
                    case R.id.rb_cart:      // 购物车
                        position = 3;
                        break;
                    case R.id.rb_user:      //用户中心
                        position = 4;
                        break;
                    default:
                        position = 0;
                        break;
                }

                //根据位置选择不同的fragment
                BaseFragment baseFragment = getFragment(position);
                /**
                 * 1.第一参数：上次显示的fragement
                 * 2.第二参数：当前正要显示的fragement
                 */
                switchFragment(tempFragemnt, baseFragment);

            }
        });

        rgMain.check(R.id.rb_home);
    }

    /**
     * 添加时候要按照顺序
     */
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new UserFragment());
    }

    /**
     * 根据位置得到对应的 Fragment
     * @param position
     * @return
     */
    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    /**
     * 切换fragment
     * @param fromFragment
     * @param nextFragment
     */
    private void switchFragment(Fragment fromFragment, BaseFragment
            nextFragment) {
        if (tempFragemnt != nextFragment) {
            tempFragemnt = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction =
                        getSupportFragmentManager().beginTransaction();
                //判断 nextFragment 是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前 Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    //隐藏当前 Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }
}