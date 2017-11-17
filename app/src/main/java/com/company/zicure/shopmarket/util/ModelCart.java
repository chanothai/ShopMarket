package com.company.zicure.shopmarket.util;


import com.company.zicure.shopmarket.model.ItemStoreModel;

import java.util.ArrayList;

/**
 * Created by 4GRYZ52 on 10/25/2016.
 */

public class ModelCart {
    private static ModelCart me = null;
    private ArrayList<ItemStoreModel> itemStoreModel = null;
    private StartLightResponse startLightResponse = null;

    public ModelCart() {
        itemStoreModel = new ArrayList<>();
        startLightResponse = new StartLightResponse();
    }

    public static ModelCart getInstance(){
        if (me == null){
            me = new ModelCart();
        }

        return me;
    }

    public ArrayList<ItemStoreModel> getItemStoreModel() {
        return itemStoreModel;
    }

    public void setItemStoreModel(ArrayList<ItemStoreModel> itemStoreModel) {
        this.itemStoreModel = itemStoreModel;
    }

    public void removeData(){
        itemStoreModel.clear();
    }

    public StartLightResponse getStartLightResponse(){
        return startLightResponse;
    }
}
