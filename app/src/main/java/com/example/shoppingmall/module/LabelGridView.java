package com.example.shoppingmall.module;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by yang
 * date: 2020/11/3
 * Describe: GridView高度自适应：数据撑满高度
 */
class LabelGridView extends GridView {

    public LabelGridView(Context context) {
        super(context);
    }
    public LabelGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}