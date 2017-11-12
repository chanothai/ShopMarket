package com.company.zicure.shopmarket.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.company.zicure.shopmarket.R;
import com.company.zicure.shopmarket.model.ItemStoreModel;

import java.util.ArrayList;

/**
 * Created by ballomo on 11/12/2017 AD.
 */

public class ListProductAdapter extends RecyclerView.Adapter<ListProductAdapter.ListProductHolder>{

    private Context context = null;
    private ArrayList<ItemStoreModel> arrItemStore = null;

    public ListProductAdapter(Context context){
        this.context = context;
    }

    public void setItemStore(ArrayList<ItemStoreModel> arrItemStore){
        this.arrItemStore = arrItemStore;
    }

    @Override
    public ListProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_list_product, null);

        return new ListProductHolder(view);
    }

    @Override
    public void onBindViewHolder(ListProductHolder holder, int position) {
        holder.nameItem.setText(arrItemStore.get(position).getName());
        holder.qty.setText(String.valueOf(arrItemStore.get(position).getQty()));
        holder.price.setText(arrItemStore.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return arrItemStore.size();
    }

    public class ListProductHolder extends RecyclerView.ViewHolder {

        public TextView nameItem, qty, price;

        public ListProductHolder(View itemView) {
            super(itemView);
            nameItem = (TextView) itemView.findViewById(R.id.txt_name_product);
            qty = (TextView) itemView.findViewById(R.id.txt_qty_product);
            price = (TextView) itemView.findViewById(R.id.txt_price_product);
        }

    }
}
