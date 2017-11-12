package com.company.zicure.shopmarket.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.company.zicure.shopmarket.R;

/**
 * Created by ballomo on 11/12/2017 AD.
 */

public class DetailProductAdapter extends RecyclerView.Adapter<DetailProductAdapter.DetailProductHolder>{

    private String titleDetail, titlePromotion;

    public DetailProductAdapter(){

    }

    @Override
    public DetailProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_detail_product, null);
        return new DetailProductHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailProductHolder holder, int position) {
        switch (position){
            case 0: titleDetail = "ข้อมูล : ";
                break;
            case 1:
                titlePromotion = "โปรโมชั่น : ";
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class DetailProductHolder extends RecyclerView.ViewHolder {

        public TextView txtDetail;

        public DetailProductHolder(View itemView) {
            super(itemView);
            txtDetail = (TextView) itemView.findViewById(R.id.txt_detail_product);
        }
    }
}
