package com.company.zicure.shopmarket.util;


/**
 * Created by 4GRYZ52 on 10/25/2016.
 */

public class ModelCart {
    private static ModelCart me = null;

    private ModelCart() {

    }

    public static ModelCart getInstance(){
        if (me == null){
            me = new ModelCart();
        }

        return me;
    }
}
