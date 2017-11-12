package com.company.zicure.shopmarket.model;

/**
 * Created by ballomo on 11/12/2017 AD.
 */

public class ItemStoreModel {
    private String id;
    private String name;
    private int qty;
    private String price;
    private String imgItem;
    private String promotion;
    private String detail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImgItem() {
        return imgItem;
    }

    public void setImgItem(String imgItem) {
        this.imgItem = imgItem;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
