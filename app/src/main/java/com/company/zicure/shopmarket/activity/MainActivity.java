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
    private SQLiteDatabase mDb = null;
    private DBHelper mHelper = null;
    private Cursor mCursor = null;

    private ArrayList<String> arrItem = null;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();
        arrItem = new ArrayList<>();
        checkDatabase();
        initDataBase();
        initCategory();

        if (savedInstanceState == null) {

        }
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
    protected void onPause() {
        super.onPause();
        mHelper.close();
        mDb.close();
    }

    private void initDataBase(){
        mHelper = new DBHelper(this);
        mDb = mHelper.getWritableDatabase();

        mCursor = mDb.rawQuery("SELECT name FROM " + DBHelper.TABLE_NAME, null);
        mCursor.moveToFirst();
        while (!mCursor.isAfterLast()){
            arrItem.add("Name : " + mCursor.getString(mCursor.getColumnIndex(DBHelper.COL_NAME)));
            mCursor.moveToNext();
        }

        Log.d("Database", arrItem.get(0));
    }

    private void checkDatabase(){
        String url = "/data/data/" + getPackageName() + "/databases/" + DBHelper.DB_NAME;
        File file = new File(url);

        if (!file.exists()){
            try{
                mHelper = new DBHelper(this);
                mDb = mHelper.getWritableDatabase();
                mDb.close();
                mHelper.close();
                InputStream in = getAssets().open(DBHelper.DB_NAME);
                OutputStream out = new FileOutputStream(url);
                byte[] buffer = new byte[in.available()];
                in.read(buffer);
                out.write(buffer, 0, buffer.length);
                in.close();
                out.close();
            } catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
