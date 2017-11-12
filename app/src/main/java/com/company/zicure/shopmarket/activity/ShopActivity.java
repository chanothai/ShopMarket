package com.company.zicure.shopmarket.activity;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.company.zicure.shopmarket.R;
import com.company.zicure.shopmarket.common.BaseActivity;
import com.company.zicure.shopmarket.fragment.ScanProductFragment;

public class ShopActivity extends BaseActivity {

    private Toolbar toolbar;
    //make :Properties
    private ScanProductFragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        bindView();
        callCamera();
    }

    private void bindView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbar(toolbar);
    }

    private void callCamera(){
        FragmentManager fm = getSupportFragmentManager();
        fragment = (ScanProductFragment) fm.findFragmentByTag("scan_product_fragment");
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
