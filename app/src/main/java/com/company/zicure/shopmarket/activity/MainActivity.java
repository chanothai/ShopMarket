package com.company.zicure.shopmarket.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.company.zicure.shopmarket.R;
import com.company.zicure.shopmarket.adapter.CategoryAdapter;
import com.company.zicure.shopmarket.common.BaseActivity;
import com.company.zicure.shopmarket.database.DBHelper;
import com.company.zicure.shopmarket.model.ItemStoreModel;
import com.company.zicure.shopmarket.util.ModelCart;
import com.company.zicure.shopmarket.util.NextzyUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends BaseActivity implements CategoryAdapter.OnClickCategoryListener{

    //Make: View
    private RecyclerView recyclerView = null;

    // Make: properties
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();
        initCategory();
    }

    private void bindView(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_category);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initCategory(){
        recyclerView.setAdapter(new CategoryAdapter(this,this));
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void setItemClick(View view, int position, SearchProductActivity.OnCloseLightListener onCloseLightListener) {
        switch (position) {
            case 0:
                showLoadingDialog();
                NextzyUtil.launchDelay(new NextzyUtil.LaunchCallback() {
                    @Override
                    public void onLaunchCallback() {
                        dismissDialog();
                        Intent intent = new Intent(context, ShopActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case 1:
                showLoadingDialog();
                NextzyUtil.launchDelay(new NextzyUtil.LaunchCallback() {
                    @Override
                    public void onLaunchCallback() {
                        dismissDialog();
                        Intent intent = new Intent(context, SearchProductActivity.class);
                        context.startActivity(intent);
                    }
                });
                break;
        }
    }
}
