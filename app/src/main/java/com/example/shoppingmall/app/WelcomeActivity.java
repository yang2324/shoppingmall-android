package com.example.shoppingmall.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.shoppingmall.R;

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        //两秒钟进入主线程
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //执行在主线程
                //启动主页面
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                //关闭
                finish();
            }
        },1000);
    }
}