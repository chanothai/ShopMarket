package com.company.zicure.shopmarket.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.company.zicure.shopmarket.R;
import com.company.zicure.shopmarket.adapter.CategoryAdapter;
import com.company.zicure.shopmarket.adapter.SearchProductAdapter;
import com.company.zicure.shopmarket.common.BaseActivity;
import com.company.zicure.shopmarket.model.ResponseStatusLight;
import com.company.zicure.shopmarket.network.ClientHttp;
import com.company.zicure.shopmarket.util.EventBusCart;
import com.company.zicure.shopmarket.util.NextzyUtil;
import com.squareup.otto.Subscribe;

public class SearchProductActivity extends BaseActivity implements CategoryAdapter.OnClickCategoryListener{
    //Make: View
    private Toolbar toolbar = null;
    private ImageView imgLogo = null;
    private RecyclerView listSearchItem;

    //Properties
    private Context context = this;
    private int lightID = 0;
    private OnCloseLightListener onCloseLightListener = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);
        EventBusCart.getInstance().getEventBus().register(this);

        bindView();
        if (savedInstanceState == null){
            listSearchItem.setAdapter(new SearchProductAdapter(this,this));
        }
    }

    private void bindView(){
        imgLogo = (ImageView) findViewById(R.id.logo_toolbar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbar(toolbar);

        listSearchItem = (RecyclerView) findViewById(R.id.recyclerview_category);
        listSearchItem.setLayoutManager(new GridLayoutManager(this, 4));
        listSearchItem.setItemAnimator(new DefaultItemAnimator());
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
    protected void onDestroy() {
        super.onDestroy();
        EventBusCart.getInstance().getEventBus().unregister(this);
    }

    @Subscribe
    public void onEventResponseStatusLight(ResponseStatusLight statusLight){
        dismissDialog();
        Toast.makeText(this, statusLight.getStatus(), Toast.LENGTH_SHORT).show();

        if (!statusLight.getStatus().equalsIgnoreCase("offline")) {
            NextzyUtil.requestDelay(new NextzyUtil.LaunchCallback() {
                @Override
                public void onLaunchCallback() {
                    ClientHttp.getInstance(context).requestCloseLight(lightID);
                }
            });
        }else{
            onCloseLightListener.setClose();
        }
    }


    @Override
    public void setItemClick(View view, int position, OnCloseLightListener onCloseLightListener) {
        this.onCloseLightListener = onCloseLightListener;
        showLoadingDialog();
        switch (position) {
            case 0:
                lightID = 1;
                break;
            case 1:
                lightID = 2;
                break;
            case 2:
                lightID = 3;
                break;
            case 3:
                lightID = 4;
                break;
        }

        ClientHttp.getInstance(this).requestLight(lightID);
    }

    public interface OnCloseLightListener {
        void setClose();
    }
}
