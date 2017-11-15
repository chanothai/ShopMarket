package com.company.zicure.shopmarket.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.company.zicure.shopmarket.R;
import com.company.zicure.shopmarket.model.ItemStoreModel;

import java.util.ArrayList;

/**
 * Created by ballomo on 11/12/2017 AD.
 */

public class DetailProductAdapter extends RecyclerView.Adapter<DetailProductAdapter.DetailProductHolder>{

    private ItemStoreModel arrItem = null;

    public DetailProductAdapter(ItemStoreModel arrItem){
        this.arrItem = arrItem;
    }

    @Override
    public DetailProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_detail_product, null);
        return new DetailProductHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailProductHolder holder, int position) {
        switch (position){
            case 0:
                holder.txtTitle.setText("ชื่อ :");
                holder.txtDetail.setText(arrItem.getName());
                break;
            case 1:
                holder.txtTitle.setText("ราคา :");
                holder.txtDetail.setText(arrItem.getPrice() + " บาท");
                break;
            case 2:
                holder.txtTitle.setText("โปรโมชั่น :");
                holder.txtDetail.setText(arrItem.getPromotion());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class DetailProductHolder extends RecyclerView.ViewHolder {

        public TextView txtDetail, txtTitle;

        public DetailProductHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_title);
            txtDetail = (TextView) itemView.findViewById(R.id.txt_detail_product);
        }
    }
}
