package com.example.shoppingmall.home.bean;

import java.io.Serializable;

/**
 * Created by yang
 * date: 2020/11/3
 * Describe: 商品对象
 */
public class GoodsBean implements Serializable {

    //价格
    private String cover_price;
    //图片
    private String figure;
    //名称
    private String name;
    //id
    private String product_id;

    private int number = 1;

    private boolean isSelect = true;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCover_price() {
        return cover_price;
    }

    public void setCover_price(String cover_price) {
        this.cover_price = cover_price;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "GoodsBean{" +
                "cover_price='" + cover_price + '\'' +
                ", figure='" + figure + '\'' +
                ", name='" + name + '\'' +
                ", product_id='" + product_id + '\'' +
                ", number=" + number +
                ", isSelect=" + isSelect +
                '}';
    }
}
