package com.example.shoppingmall.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yang
 * date: 2020/11/4
 * Describe:    缓存工具
 */

public class CacheUtils {

    /**
     * 得到保持的String类型的数据
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }


    /**
     * 保持String类型数据
     * @param context 上下文
     * @param key
     * @param value 保持的值
     */
    public static void saveString(Context context, String key,String value) {
        SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }
}

//public class CacheUtils {
//
//    /**
//     * 得到保存string类型的数据
//     * @param context
//     * @param key
//     * @return
//     */
//    public static String getString(Context context, String key) {
//        SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
//        return sp.getString(key,"");
//    }
//
//    /**
//     * 保存string类型数据
//     * @param context
//     * @param key
//     * @param value
//     */
//    public static void saveString(Context context, String key,String value) {
//        SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
//        sp.edit().putString(key,value).commit();
//    }
//}
