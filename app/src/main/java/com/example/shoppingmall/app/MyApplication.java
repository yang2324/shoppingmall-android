package com.example.shoppingmall.app;

import android.app.Application;
import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by yang
 * date: 2020/10/29
 * Describe:
 */
public class MyApplication extends Application {


    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

        //初始化okHttpUtil
        initOkHttpClient();
    }

    public static Context getContext() {
        return mContext;
    }


    private void initOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }
}
