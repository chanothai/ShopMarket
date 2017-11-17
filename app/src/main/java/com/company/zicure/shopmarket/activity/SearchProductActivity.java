package com.company.zicure.shopmarket.activity;

import android.content.Context;
import android.os.PowerManager;
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
import android.widget.TextView;
import android.widget.Toast;

import com.company.zicure.shopmarket.R;
import com.company.zicure.shopmarket.adapter.CategoryAdapter;
import com.company.zicure.shopmarket.adapter.SearchProductAdapter;
import com.company.zicure.shopmarket.common.BaseActivity;
import com.company.zicure.shopmarket.model.ResponseStatusLight;
import com.company.zicure.shopmarket.model.ResponseStatusLight2;
import com.company.zicure.shopmarket.model.ResponseStatusLight3;
import com.company.zicure.shopmarket.model.ResponseStatusLight4;
import com.company.zicure.shopmarket.network.ClientHttp;
import com.company.zicure.shopmarket.util.EventBusCart;
import com.company.zicure.shopmarket.util.ModelCart;
import com.company.zicure.shopmarket.util.NextzyUtil;
import com.company.zicure.shopmarket.util.PromotionItem;
import com.squareup.otto.Subscribe;

public class SearchProductActivity extends BaseActivity implements CategoryAdapter.OnClickCategoryListener, CategoryAdapter.OnClickCategoryListener2, CategoryAdapter.OnClickCategoryListener3, CategoryAdapter.OnClickCategoryListener4{
    //Make: View
    private Toolbar toolbar = null;
    private ImageView imgLogo = null;
    private RecyclerView listSearchItem;
    private TextView txtDetailPromotion;

    //Properties
    private Context context = this;
    private int lightID = 0;

    private OnCloseLightListener onCloseLightListener = null;
    private OnCloseLightListener2 onCloseLightListener2 = null;
    private OnCloseLightListener3 onCloseLightListener3 = null;
    private OnCloseLightListener4 onCloseLightListener4 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);
        EventBusCart.getInstance().getEventBus().register(this);
        bindView();
        if (savedInstanceState == null){
            listSearchItem.setAdapter(new SearchProductAdapter(this,this,this,this,this));

            requestCloseLight();
        }
    }

    private void bindView(){
        txtDetailPromotion = (TextView) findViewById(R.id.txt_detail_promotion);
        imgLogo = (ImageView) findViewById(R.id.logo_toolbar);
        imgLogo.setImageResource(R.drawable.logo_ajinomoto);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbar(toolbar);

        listSearchItem = (RecyclerView) findViewById(R.id.recyclerview_category);
        listSearchItem.setLayoutManager(new GridLayoutManager(this, 4));
        listSearchItem.setItemAnimator(new DefaultItemAnimator());
    }

    private void requestCloseLight(){
        if (!ModelCart.getInstance().getStartLightResponse().startLight){
            ClientHttp.getInstance(context).requestLight(1, 0);
        }

        if (!ModelCart.getInstance().getStartLightResponse().startLight2){
            ClientHttp.getInstance(context).requestLight2(2, 0);
        }

        if (!ModelCart.getInstance().getStartLightResponse().startLight3){
            ClientHttp.getInstance(context).requestLight3(3, 0);
        }

        if (!ModelCart.getInstance().getStartLightResponse().startLight4){
            ClientHttp.getInstance(context).requestLight4(4, 0);
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
    protected void onDestroy() {
        super.onDestroy();
        ModelCart.getInstance().getStartLightResponse().startLight = false;
        ModelCart.getInstance().getStartLightResponse().startLight2 = false;
        ModelCart.getInstance().getStartLightResponse().startLight3 = false;
        ModelCart.getInstance().getStartLightResponse().startLight4 = false;

        EventBusCart.getInstance().getEventBus().unregister(this);
    }


    private void setupListener(String status, final int id){
        dismissDialog();
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();

        if (!status.equalsIgnoreCase("offline")) {
            NextzyUtil.requestDelay(new NextzyUtil.LaunchCallback() {
                @Override
                public void onLaunchCallback() {
                    switch (id){
                        case 1:
                            if (ModelCart.getInstance().getStartLightResponse().startLight){
                                ClientHttp.getInstance(context).requestLight(id, 0);
                            }
                            break;
                        case 2:
                            if (ModelCart.getInstance().getStartLightResponse().startLight2){
                                ClientHttp.getInstance(context).requestLight2(id, 0);
                            }
                            break;
                        case 3:
                            if (ModelCart.getInstance().getStartLightResponse().startLight3){
                                ClientHttp.getInstance(context).requestLight3(id, 0);
                            }
                            break;
                        case 4:
                            if (ModelCart.getInstance().getStartLightResponse().startLight4){
                                ClientHttp.getInstance(context).requestLight4(id, 0);
                            }
                            break;
                    }
                }
            });
        }else{
            switch (id) {
                case 1:
                    onCloseLightListener.setClose(0);
                    break;
                case 2:
                    onCloseLightListener2.setClose();
                    break;
                case 3:
                    onCloseLightListener3.setClose();
                    break;
                case 4:
                    onCloseLightListener4.setClose();
                    break;
            }
        }
    }

    @Subscribe
    public void onEventResponseStatusLight(ResponseStatusLight statusLight){
        if (ModelCart.getInstance().getStartLightResponse().startLight){
            setupListener(statusLight.getStatus(), 1);
        }else{
            Toast.makeText(context, statusLight.getStatus(), Toast.LENGTH_SHORT).show();
            ModelCart.getInstance().getStartLightResponse().startLight = true;
        }
    }

    @Subscribe
    public void onEventResponseStatusLight2(ResponseStatusLight2 statusLight) {
        if (ModelCart.getInstance().getStartLightResponse().startLight2){
            setupListener(statusLight.getStatus(), 2);
        }else{
            Toast.makeText(context, statusLight.getStatus(), Toast.LENGTH_SHORT).show();
            ModelCart.getInstance().getStartLightResponse().startLight2 = true;
        }
    }

    @Subscribe
    public void onEventResponseStatusLight3(ResponseStatusLight3 statusLight) {
        if (ModelCart.getInstance().getStartLightResponse().startLight3){
            setupListener(statusLight.getStatus(), 3);
        }else{
            Toast.makeText(context, statusLight.getStatus(), Toast.LENGTH_SHORT).show();
            ModelCart.getInstance().getStartLightResponse().startLight3 = true;
        }

    }

    @Subscribe
    public void onEventResponseStatusLight4(ResponseStatusLight4 statusLight) {
        if (ModelCart.getInstance().getStartLightResponse().startLight4){
            setupListener(statusLight.getStatus(), 4);
        }else{
            Toast.makeText(context, statusLight.getStatus(), Toast.LENGTH_SHORT).show();
            ModelCart.getInstance().getStartLightResponse().startLight4 = true;
        }
    }

    @Override
    public void setItemClick(View view, int position, OnCloseLightListener onCloseLightListener) {
        this.onCloseLightListener = onCloseLightListener;

        showLoadingDialog();
        lightID = 1;
        ClientHttp.getInstance(this).requestLight(lightID, 1);

        txtDetailPromotion.setText(PromotionItem.arrPromotion[0]);
    }

    @Override
    public void setItemClick2(View view, int position, OnCloseLightListener2 onCloseLightListener) {
        this.onCloseLightListener2 = onCloseLightListener;

        showLoadingDialog();
        lightID = 2;
        ClientHttp.getInstance(this).requestLight2(lightID, 1);

        txtDetailPromotion.setText(PromotionItem.arrPromotion[1]);
    }

    @Override
    public void setItemClick3(View view, int position, OnCloseLightListener3 onCloseLightListener) {
        this.onCloseLightListener3 = onCloseLightListener;

        showLoadingDialog();
        lightID = 3;
        ClientHttp.getInstance(this).requestLight3(lightID, 1);

        txtDetailPromotion.setText(PromotionItem.arrPromotion[2]);
    }

    @Override
    public void setItemClick4(View view, int position, OnCloseLightListener4 onCloseLightListener) {
        this.onCloseLightListener4 = onCloseLightListener;

        showLoadingDialog();
        lightID = 4;
        ClientHttp.getInstance(this).requestLight4(lightID, 1);

        txtDetailPromotion.setText(PromotionItem.arrPromotion[3]);
    }

    public interface OnCloseLightListener {
        void setClose(int id);
    }

    public interface OnCloseLightListener2 {
        void setClose();
    }

    public interface OnCloseLightListener3 {
        void setClose();
    }

    public interface OnCloseLightListener4 {
        void setClose();
    }
}
