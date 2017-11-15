package com.company.zicure.shopmarket.activity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.company.zicure.shopmarket.R;
import com.company.zicure.shopmarket.common.BaseActivity;
import com.company.zicure.shopmarket.database.DBHelper;
import com.company.zicure.shopmarket.fragment.ListProductFragment;
import com.company.zicure.shopmarket.fragment.ScanProductFragment;
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

public class ShopActivity extends BaseActivity {

    // Make: view
    private ImageView imgLogo;
    private Toolbar toolbar;
    //make :Properties
    private ScanProductFragment fragment = null;
    private ListProductFragment listProductFragment = null;

    private SQLiteDatabase mDb = null;
    private DBHelper mHelper = null;
    private Cursor mCursor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        bindView();
        checkDatabase();

        if (savedInstanceState == null) {
            callListStoreItem();
            callCamera();
            mHelper = new DBHelper(this);
            mDb = mHelper.getWritableDatabase();
        }
    }

    private void bindView(){
        imgLogo = (ImageView) findViewById(R.id.logo_toolbar);
        imgLogo.setImageResource(R.drawable.logo_ajinomoto);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbar(toolbar);
    }

    private void callCamera(){
        FragmentManager fm = getSupportFragmentManager();
        fragment = (ScanProductFragment) fm.findFragmentByTag("scan_product_fragment");
    }

    private void callListStoreItem(){
        FragmentManager fm = getSupportFragmentManager();
        listProductFragment = (ListProductFragment) fm.findFragmentByTag("list_product_fragment");
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

    public void queryItem(String id){
        ArrayList<ItemStoreModel> arrItemStore = new ArrayList<>();
        try{
            mCursor = mDb.rawQuery("SELECT * FROM " + DBHelper.TABLE_NAME + " WHERE barcode = '" + id + "'" , null);

            if (mCursor.moveToFirst()){
                ItemStoreModel itemStoreModel = new ItemStoreModel();
                itemStoreModel.setBarcode(mCursor.getString(mCursor.getColumnIndex(DBHelper.COL_BARCODE)));
                itemStoreModel.setName(mCursor.getString(mCursor.getColumnIndex(DBHelper.COL_NAME)));
                itemStoreModel.setPrice(mCursor.getString(mCursor.getColumnIndex(DBHelper.COL_PRICE)));
                itemStoreModel.setQty(1);
                itemStoreModel.setImgItem(mCursor.getString(mCursor.getColumnIndex(DBHelper.COL_IMAGE)));
                itemStoreModel.setPromotion(mCursor.getString(mCursor.getColumnIndex(DBHelper.COL_PROMOTION)));

                arrItemStore.add(itemStoreModel);
                ModelCart.getInstance().setItemStoreModel(arrItemStore);

                fragment.setDetailItem(itemStoreModel);
                listProductFragment.setStoreItem(arrItemStore);

                NextzyUtil.deleyCamera(new NextzyUtil.LaunchCallback() {
                    @Override
                    public void onLaunchCallback() {
                        fragment.resumeCamera();
                    }
                });
            }

        }catch (SQLException e){
            Toast.makeText(this, "Barcode was wrong.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            fragment.resumeCamera();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fragment.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        fragment.stopCamera();
        mHelper.close();
        mDb.close();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 111:
                fragment.startCamera();
                break;
        }
    }
}
