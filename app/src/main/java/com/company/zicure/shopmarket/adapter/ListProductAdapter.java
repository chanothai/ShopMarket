package com.company.zicure.shopmarket.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import com.company.zicure.shopmarket.R;
import com.company.zicure.shopmarket.model.ItemStoreModel;
import com.company.zicure.shopmarket.util.ModelCart;

import java.util.ArrayList;

/**
 * Created by ballomo on 11/12/2017 AD.
 */

public class ListProductAdapter extends RecyclerView.Adapter<ListProductAdapter.ListProductHolder>{

    private Context context = null;

    private int qty = 0;
    private int price = 0, resultPrice = 0, sumPrice = 0, sumQTY = 0, cPrice = 0;
    private String bath = " บ.";

    private OnResponsePriceItem onResponsePriceItem = null;
    private OnRemoveItemListener onRemoveItemListener = null;

    public ListProductAdapter(Context context, OnResponsePriceItem onResponsePriceItem, OnRemoveItemListener onRemoveItemListener){
        this.context = context;
        this.onResponsePriceItem = onResponsePriceItem;
        this.onRemoveItemListener = onRemoveItemListener;
    }

    @Override
    public ListProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_list_product, null);

        return new ListProductHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListProductHolder holder, final int position) {
        price = Integer.parseInt(ModelCart.getInstance().getItemStoreModel().get(position).getPrice());
        qty = ModelCart.getInstance().getItemStoreModel().get(position).getQty();
        resultPrice = price * qty;

        holder.nameItem.setText(ModelCart.getInstance().getItemStoreModel().get(position).getName());
        holder.qty.setText(String.valueOf(qty));
        holder.price.setText(String.valueOf(resultPrice) + bath);
        ModelCart.getInstance().getItemStoreModel().get(position).setResultPrice(resultPrice);

        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cPrice = Integer.parseInt(ModelCart.getInstance().getItemStoreModel().get(position).getPrice());
                sumQTY = ModelCart.getInstance().getItemStoreModel().get(position).getQty();
                sumQTY += 1;
                holder.qty.setText(String.valueOf(sumQTY));

                ModelCart.getInstance().getItemStoreModel().get(position).setQty(sumQTY);

                sumPrice = ModelCart.getInstance().getItemStoreModel().get(position).getResultPrice() + cPrice;
                holder.price.setText(String.valueOf(sumPrice) + bath);
                ModelCart.getInstance().getItemStoreModel().get(position).setResultPrice(sumPrice);

                onResponsePriceItem.setOnPlusPriceItem(cPrice);
            }
        });

        holder.btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cPrice = Integer.parseInt(ModelCart.getInstance().getItemStoreModel().get(position).getPrice());
                sumQTY = ModelCart.getInstance().getItemStoreModel().get(position).getQty();
                if (sumQTY > 1) {
                    sumQTY -= 1;
                    holder.qty.setText(String.valueOf(sumQTY));
                    ModelCart.getInstance().getItemStoreModel().get(position).setQty(sumQTY);

                    sumPrice = ModelCart.getInstance().getItemStoreModel().get(position).getResultPrice() - cPrice;
                    holder.price.setText(String.valueOf(sumPrice) + bath);
                    ModelCart.getInstance().getItemStoreModel().get(position).setResultPrice(sumPrice);

                    onResponsePriceItem.setOnNegativePriceItem(cPrice);
                }
            }
        });


        holder.checkBox.setVisibility(View.VISIBLE);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean check) {
                onRemoveItemListener.setOnRemove(ModelCart.getInstance().getItemStoreModel().get(position).getResultPrice(), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ModelCart.getInstance().getItemStoreModel().size();
    }

    public class ListProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nameItem, qty, price;
        public ImageButton btnPlus, btnNegative;
        public CheckBox checkBox;

        public ListProductHolder(View itemView) {
            super(itemView);
            nameItem = (TextView) itemView.findViewById(R.id.txt_name_product);
            qty = (TextView) itemView.findViewById(R.id.txt_qty_product);
            price = (TextView) itemView.findViewById(R.id.txt_price_product);
            btnPlus = (ImageButton) itemView.findViewById(R.id.btn_plus);
            btnNegative = (ImageButton) itemView.findViewById(R.id.btn_negative);
            checkBox = (CheckBox) itemView.findViewById(R.id.check_delete);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

        }
    }

    public interface OnResponsePriceItem {
        void setOnPlusPriceItem(int priceItem);
        void setOnNegativePriceItem(int priceItem);
    }

    public interface OnRemoveItemListener {
        void setOnRemove(int price, int position);
    }
}
