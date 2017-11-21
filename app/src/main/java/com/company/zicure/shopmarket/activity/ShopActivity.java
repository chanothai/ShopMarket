package com.company.zicure.shopmarket.activity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.PowerManager;
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

            PowerManager.WakeLock wl = ((PowerManager)getSystemService(POWER_SERVICE)).newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "tag");
            wl.acquire();
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
        int price = 0;
        try{

            if (!checkItem(id)) {
                mCursor = mDb.rawQuery("SELECT * FROM " + DBHelper.TABLE_NAME + " WHERE barcode = '" + id + "'" , null);
                mCursor.moveToFirst();

                while (!mCursor.isAfterLast()){
                    ItemStoreModel itemStoreModel = new ItemStoreModel();
                    itemStoreModel.setBarcode(mCursor.getString(mCursor.getColumnIndex(DBHelper.COL_BARCODE)));
                    itemStoreModel.setName(mCursor.getString(mCursor.getColumnIndex(DBHelper.COL_NAME)));
                    itemStoreModel.setPrice(mCursor.getString(mCursor.getColumnIndex(DBHelper.COL_PRICE)));
                    itemStoreModel.setQty(1);
                    itemStoreModel.setImgItem(mCursor.getString(mCursor.getColumnIndex(DBHelper.COL_IMAGE)));
                    itemStoreModel.setPromotion(mCursor.getString(mCursor.getColumnIndex(DBHelper.COL_PROMOTION)));

                    ModelCart.getInstance().getItemStoreModel().add(itemStoreModel);

                    fragment.setDetailItem(itemStoreModel);
                    price = Integer.parseInt(mCursor.getString(mCursor.getColumnIndex(DBHelper.COL_PRICE)));
                    mCursor.moveToNext();
                }


                if (ModelCart.getInstance().getItemStoreModel().size() == 1) {
                    listProductFragment.setStoreItem(ModelCart.getInstance().getItemStoreModel());
                }else{
                    listProductFragment.updateStoreItem();
                }

                listProductFragment.updateResultTotalPrice(price);
                deleyCamera();
            }else{
                deleyCamera();
            }

        }catch (SQLException e){
            Toast.makeText(this, "Barcode was wrong.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            fragment.resumeCamera();
        }
    }

    private void deleyCamera(){
        showLoadingDialog();
        NextzyUtil.deleyCamera(new NextzyUtil.LaunchCallback() {
            @Override
            public void onLaunchCallback() {
                dismissDialog();
            }
        });

        NextzyUtil.launchDelay(new NextzyUtil.LaunchCallback() {
            @Override
            public void onLaunchCallback() {
                fragment.resumeCamera();
            }
        });
    }

    private boolean checkItem(String id) {
        if (ModelCart.getInstance().getItemStoreModel() != null) {
            if (ModelCart.getInstance().getItemStoreModel().size() > 0) {
                for (int i = 0; i < ModelCart.getInstance().getItemStoreModel().size(); i++) {
                    if (ModelCart.getInstance().getItemStoreModel().get(i).getBarcode().equalsIgnoreCase(id)) {

                        //Set QTY
                        int qty = ModelCart.getInstance().getItemStoreModel().get(i).getQty();
                        qty += 1;
                        ModelCart.getInstance().getItemStoreModel().get(i).setQty(qty);

                        //Set Price
                        int result = ModelCart.getInstance().getItemStoreModel().get(i).getResultPrice() +
                                Integer.parseInt(ModelCart.getInstance().getItemStoreModel().get(i).getPrice());
                        ModelCart.getInstance().getItemStoreModel().get(i).setResultPrice(result);

                        fragment.setDetailItem(ModelCart.getInstance().getItemStoreModel().get(i));
                        listProductFragment.updateStoreItem();
                        listProductFragment.updateResultTotalPrice(Integer.parseInt(ModelCart.getInstance().getItemStoreModel().get(i).getPrice()));
                        return true;
                    }
                }
            }
            return false;
        }

        return false;
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
        int itemLast = ModelCart.getInstance().getItemStoreModel().size();
        if (itemLast > 0) {
            itemLast -= 1;
            fragment.setDetailItem(ModelCart.getInstance().getItemStoreModel().get(itemLast));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        fragment.stopCamera();

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHelper.close();
        mDb.close();
    }
}
