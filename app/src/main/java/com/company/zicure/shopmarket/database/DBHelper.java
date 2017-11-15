package com.company.zicure.shopmarket.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pakgon on 11/7/2017 AD.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "newmoonline_product.db";
    private static final int DB_VERSION = 3;

    public static final String TABLE_NAME = "products_master";
    public static final String COL_NAME = "name";
    public static final String COL_DETAIL = "detail";
    public static final String COL_PRICE = "price";
    public static final String COL_BARCODE = "barcode";
    public static final String COL_IMAGE = "images_name";
    public static final String COL_PROMOTION = "promotion";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
