package com.example.shoppingmall.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shoppingmall.R;
import com.example.shoppingmall.home.bean.GoodsBean;
import com.example.shoppingmall.shoppingcart.utils.CartStorage;
import com.example.shoppingmall.utils.Constants;

public class GoodsInfoActivity extends Activity implements View.OnClickListener {

    private ImageButton ibGoodInfoBack;
    private ImageButton ibGoodInfoMore;
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoDesc;
    private TextView tvGoodInfoPrice;
    private TextView tvGoodInfoStore;
    private TextView tvGoodInfoStyle;
    private WebView wbGoodInfoMore;
    private LinearLayout llGoodsRoot;
    private TextView tvGoodInfoCallcenter;
    private TextView tvGoodInfoCollection;
    private TextView tvGoodInfoCart;
    private Button btnGoodInfoAddcart;
    //更多
    private TextView tvMoreShare;
    private TextView tvMoreSearch;
    private TextView tvMoreHome;
    private Button btnMore;
    private GoodsBean goodsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        findViews();

        //接收数据
        goodsBean = (GoodsBean) getIntent().getSerializableExtra("goods_bean");
        if (goodsBean != null) {
            //Toast.makeText(this,"goodsBean=="+goodsBean,Toast.LENGTH_SHORT).show();
            //设置数据
            setDataForView(goodsBean);
        }
    }

    private void setDataForView(GoodsBean goodsBean) {
        //设置图片
        Glide.with(this).load(Constants.BASE_URL_IMAGE+goodsBean.getFigure()).into(ivGoodInfoImage);
        //设置名称内容
        tvGoodInfoName.setText(goodsBean.getName());
        //设置价格
        tvGoodInfoPrice.setText("￥"+goodsBean.getCover_price());

        //设置网络页面
        setWebViewData(goodsBean.getProduct_id());
    }

    private void setWebViewData(String product_id) {
        if (product_id != null){
            wbGoodInfoMore.loadUrl("https://guangdong.mutuan.com/m/");
            //启用支持 javascript
            WebSettings settings = wbGoodInfoMore.getSettings();
            settings.setJavaScriptEnabled(true);//支持javascript
            settings.setUseWideViewPort(true);//设置支持双击页面

            wbGoodInfoMore.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
        }
    }


    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2020-11-03 14:54:40 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        tvMoreShare = findViewById(R.id.tv_more_share);
        tvMoreSearch = findViewById(R.id.tv_more_search);
        tvMoreHome = findViewById(R.id.tv_more_home);
        btnMore = findViewById(R.id.btn_more);

        ibGoodInfoBack = findViewById(R.id.ib_good_info_back);
        ibGoodInfoMore = findViewById(R.id.ib_good_info_more);
        ivGoodInfoImage = findViewById(R.id.iv_good_info_image);
        tvGoodInfoName = findViewById(R.id.tv_good_info_name);
        tvGoodInfoDesc = findViewById(R.id.tv_good_info_desc);
        tvGoodInfoPrice = findViewById(R.id.tv_good_info_price);
        tvGoodInfoStore = findViewById(R.id.tv_good_info_store);
        tvGoodInfoStyle = findViewById(R.id.tv_good_info_style);
        wbGoodInfoMore = findViewById(R.id.wb_good_info_more);
        llGoodsRoot = findViewById(R.id.ll_goods_root);

        tvGoodInfoCallcenter = findViewById(R.id.tv_good_info_callcenter);
        tvGoodInfoCollection = findViewById(R.id.tv_good_info_collection);
        tvGoodInfoCart = findViewById(R.id.tv_good_info_cart);
        btnGoodInfoAddcart = findViewById(R.id.btn_good_info_addcart);

        ibGoodInfoBack.setOnClickListener(this);
        ibGoodInfoMore.setOnClickListener(this);

        btnGoodInfoAddcart.setOnClickListener(this);
        tvGoodInfoCallcenter.setOnClickListener(this);
        tvGoodInfoCollection.setOnClickListener(this);
        tvGoodInfoCart.setOnClickListener(this);

        tvMoreShare.setOnClickListener(this);
        tvMoreSearch.setOnClickListener(this);
        tvMoreHome.setOnClickListener(this);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2020-11-03 14:54:40 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    public void onClick(View v) {
        if (v == ibGoodInfoBack) {
            // 返回按钮点击事件
            finish();
        } else if (v == ibGoodInfoMore) {
            // 更多按钮点击事件
            Toast.makeText(this, "更多", Toast.LENGTH_SHORT).show();
        } else if (v == btnGoodInfoAddcart) {
            // 添加购物车点击事件
            CartStorage.getInstance().addData(goodsBean);

            Toast.makeText(this, "加入购物车成功", Toast.LENGTH_SHORT).show();
        } else if (v == tvGoodInfoCallcenter) {
            // 联系客服点击事件
            Toast.makeText(this, "联系客服", Toast.LENGTH_SHORT).show();
        } else if (v == tvGoodInfoCollection) {
            // 收藏点击事件
            Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
        } else if (v == tvGoodInfoCart) {
            // 跳转购物车点击事件
            Toast.makeText(this, "跳转购物车", Toast.LENGTH_SHORT).show();
        } else if (v == tvMoreShare) {
            // 更多分享点击事件
            Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
        } else if (v == tvMoreSearch) {
            // 更多搜索点击事件
            Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
        } else if (v == tvMoreHome) {
            // 更多首页点击事件
            Toast.makeText(this, "首页", Toast.LENGTH_SHORT).show();
        }
    }


}