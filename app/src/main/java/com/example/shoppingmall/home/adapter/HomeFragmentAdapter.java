package com.example.shoppingmall.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.shoppingmall.R;
import com.example.shoppingmall.app.GoodsInfoActivity;
import com.example.shoppingmall.home.bean.GoodsBean;
import com.example.shoppingmall.home.bean.ResultBeanData;
import com.example.shoppingmall.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yang
 * date: 2020/10/29
 * Describe:
 */
public class HomeFragmentAdapter extends RecyclerView.Adapter {

    //广告条幅类型
    private static final int BANNER = 0;

    //频道类型
    private static final int CHANNEL = 1;

    //活动类型
    private static final int ACT = 2;

    //秒杀类型
    private static final int SECKILL = 3;

    //推荐类型
    private static final int RECOMMEND = 4;

    //热卖类型
    private static final int HOT = 5;

    private static final String GOODS_BEAN = "goods_bean";

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private ResultBeanData.ResultBean resultData;

    //默认当前类型
    private int currentType = BANNER;

    public HomeFragmentAdapter(Context mContext, ResultBeanData.ResultBean resultData) {
        this.mContext = mContext;
        this.resultData = resultData;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    /**
     * 相当于getView
     * 创建viewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContext, mLayoutInflater.inflate(R.layout.banner_viewpager, parent, false));
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(mContext, mLayoutInflater.inflate(R.layout.channel_item, parent, false));
        } else if (viewType == ACT) {
            return new ActViewHolder(mContext, mLayoutInflater.inflate(R.layout.act_item, parent, false));
        } else if (viewType == SECKILL) {
            return new SeckillViewHolder(mContext, mLayoutInflater.inflate(R.layout.seckill_item, parent, false));
        } else if (viewType == RECOMMEND) {
            return new RecommendViewHolder(mContext, mLayoutInflater.inflate(R.layout.recommend_item, parent, false));
        } else if (viewType == HOT) {
            return new HotViewHolder(mContext, mLayoutInflater.inflate(R.layout.hot_item, parent, false));
        }
        return null;
    }

    /**
     * 相当于getView的绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(resultData.getBanner_info());
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(resultData.getChannel_info());
        } else if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(resultData.getAct_info());
        } else if (getItemViewType(position) == SECKILL) {
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
            seckillViewHolder.setData(resultData.getSeckill_info());
        } else if (getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(resultData.getRecommend_info());
        } else if (getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(resultData.getHot_info());
        }
    }

    //hot
     class HotViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private TextView tv_more_hot;
        private GridView gv_hot;
        private HotGridViewAdapter adapter;

        public HotViewHolder(Context mContext, View view) {
            super(view);
            this.mContext = mContext;
            tv_more_hot = view.findViewById(R.id.tv_more_hot);
            gv_hot = view.findViewById(R.id.gv_hot);

        }

        public void setData(List<ResultBeanData.ResultBean.HotInfoBean> hot_info) {
            //有数据
            //设置适配器
            adapter = new HotGridViewAdapter(mContext, hot_info);
            gv_hot.setAdapter(adapter);

            //点击事件
            gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(mContext, "点击了" + i, Toast.LENGTH_SHORT).show();

                    //热卖商品类
                    ResultBeanData.ResultBean.HotInfoBean hotInfoBean = hot_info.get(i);
                    //商品信息类
                    GoodsBean goods = new GoodsBean();
                    goods.setCover_price(hotInfoBean.getCover_price());
                    goods.setFigure(hotInfoBean.getFigure());
                    goods.setName(hotInfoBean.getName());
                    goods.setProduct_id(hotInfoBean.getProduct_id());
                    startGoodsInfoActivity(goods);
                }
            });
        }
    }

    //recommend
     class RecommendViewHolder extends RecyclerView.ViewHolder {
        private Context mContent;
        private TextView tv_more_recommend;
        private GridView gv_recommend;
        private RecommendGridViewAdapter adapter;

        public RecommendViewHolder(Context mContext, View view) {
            super(view);
            this.mContent = mContext;
            tv_more_recommend = view.findViewById(R.id.tv_more_recommend);
            gv_recommend = view.findViewById(R.id.gv_recommend);

        }

        public void setData(List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
            //设置适配器
            adapter = new RecommendGridViewAdapter(mContent, recommend_info);
            gv_recommend.setAdapter(adapter);

            //点击事件
            gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(mContext, "点击推荐=" + i, Toast.LENGTH_SHORT).show();

                    ResultBeanData.ResultBean.RecommendInfoBean recommendInfoBean = recommend_info.get(i);
                    //商品信息类
                    GoodsBean goods = new GoodsBean();
                    goods.setCover_price(recommendInfoBean.getCover_price());
                    goods.setFigure(recommendInfoBean.getFigure());
                    goods.setName(recommendInfoBean.getName());
                    goods.setProduct_id(recommendInfoBean.getProduct_id());
                    startGoodsInfoActivity(goods);
                }
            });
        }
    }

    //seckill
     class SeckillViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private TextView tv_time_seckill;
        private TextView tv_more_seckill;
        private RecyclerView rv_seckill;
        private SeckillRecyclerViewAdapter adapter;

        //相差多少时间-毫秒
        private long dt = 0;

        @SuppressLint("HandlerLeak")
        private Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                dt = dt - 1000;
                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                String time = format.format(new Date(dt));
                tv_time_seckill.setText(time);

                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0, 1000);
                if (dt <= 0) {
                    handler.removeCallbacksAndMessages(null);
                }
            }
        };

        public SeckillViewHolder(Context mContext, View view) {
            super(view);
            this.mContext = mContext;
            tv_time_seckill = view.findViewById(R.id.tv_time_seckill);
            tv_more_seckill = view.findViewById(R.id.tv_more_seckill);
            rv_seckill = view.findViewById(R.id.rv_seckill);
        }

        public void setData(ResultBeanData.ResultBean.SeckillInfoBean seckill_info) {
            //适配器
            adapter = new SeckillRecyclerViewAdapter(mContext, seckill_info.getList());
            rv_seckill.setAdapter(adapter);

            //设置布局管理器
            rv_seckill.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            //设置item的点击事件
            adapter.setOnSeckillRecycleView(new SeckillRecyclerViewAdapter.OnSeckillRecycleView() {
                @Override
                public void OnItemClick(int position) {
                    Toast.makeText(mContext, "秒杀=" + position, Toast.LENGTH_SHORT).show();

                    ResultBeanData.ResultBean.SeckillInfoBean.ListBean seckillInfoBean = seckill_info.getList().get(position);
                    //商品信息类
                    GoodsBean goods = new GoodsBean();
                    goods.setCover_price(seckillInfoBean.getCover_price());
                    goods.setFigure(seckillInfoBean.getFigure());
                    goods.setName(seckillInfoBean.getName());
                    goods.setProduct_id(seckillInfoBean.getProduct_id());
                    startGoodsInfoActivity(goods);
                }
            });

            //秒杀时间
            dt = Integer.parseInt(seckill_info.getEnd_time()) - Integer.parseInt(seckill_info.getStart_time());
            handler.sendEmptyMessageAtTime(0, 1000);
        }

    }

    //ACT
    static class ActViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private ViewPager act_viewpager;

        public ActViewHolder(Context mContext, View view) {
            super(view);
            this.mContext = mContext;
            act_viewpager = view.findViewById(R.id.act_viewpager);
        }

        public void setData(List<ResultBeanData.ResultBean.ActInfoBean> act_info) {
            //设置边距
            act_viewpager.setPageMargin(40);
            act_viewpager.setOffscreenPageLimit(3);//>=3
            //setPageTransformer 决定动画效果
            act_viewpager.setPageTransformer(true, new
                    ScaleInTransformer());

            //1.有数据了
            //2.设置适配器
            act_viewpager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return act_info.size();
                }

                /**
                 *
                 * @param view 页面
                 * @param object instantiateItem方法返回的值
                 * @return
                 */
                @Override
                public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                    return view == object;
                }

                /**
                 *
                 * @param container viewPager
                 * @param position  页面对应的位置
                 * @return
                 */
                @NonNull
                @Override
                public Object instantiateItem(@NonNull ViewGroup container, int position) {
                    ImageView imageView = new ImageView(mContext);
                    //设置图片拉伸
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    //Glide 加载图片简单用法
                    Glide.with(mContext).load(Constants.BASE_URL_IMAGE + act_info.get(position).getIcon_url()).into(imageView);
                    //添加到容器中
                    container.addView(imageView);

                    //点击事件
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(mContext, "点击了" + position + "个item", Toast.LENGTH_SHORT).show();
                        }
                    });
                    return imageView;
                }

                @Override
                public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                    container.removeView((View) object);
                }
            });
        }

    }

    //channel
    static class ChannelViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private GridView gv_channel;
        private ChannelAdapter adapter;

        public ChannelViewHolder(Context mContext, View view) {
            super(view);
            this.mContext = mContext;
            gv_channel = view.findViewById(R.id.gv_channel);

            //点击item的事件
            gv_channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(mContext, "点击了第" + i + "个item", Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
            //设置数据
            //适配器
            adapter = new ChannelAdapter(mContext, channel_info);
            gv_channel.setAdapter(adapter);
        }

    }

    //banner
    class BannerViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private Banner banner;

        public BannerViewHolder(Context mContext, View view) {
            super(view);
            this.mContext = mContext;
            this.banner = view.findViewById(R.id.banner);
        }

        public void setData(List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {
            //设置数据

            //得到图片集合
            List<String> imagesUrl = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                String imageUrl = banner_info.get(i).getImage();
                imagesUrl.add(imageUrl);
            }
            //设置点击事件
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "点击" + position, Toast.LENGTH_SHORT).show();
                    //startGoodsInfoActivity(goods);
                }
            });

            //设置图片加载器
            banner.setImageLoader(new GlideImageLoader());
            //设置图片集合
            banner.setImages(imagesUrl);
            //banner设置方法全部调用完毕时最后调用
            banner.start();
        }

        public class GlideImageLoader extends ImageLoader {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {

                //Glide 加载图片简单用法
                Glide.with(context).load(Constants.BASE_URL_IMAGE + path).into(imageView);
            }
        }

    }

    //启动商品信息页面
    private void startGoodsInfoActivity(GoodsBean goods) {
        Intent intent = new Intent(mContext, GoodsInfoActivity.class);
        intent.putExtra(GOODS_BEAN,goods);
        mContext.startActivity(intent);
    }

    /**519710
     * 创建类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }

    /**
     * 总共有多少个item
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return 6;
    }


}
