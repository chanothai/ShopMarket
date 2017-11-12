package com.company.zicure.shopmarket.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.company.zicure.shopmarket.R;
import com.company.zicure.shopmarket.activity.SearchProductActivity;
import com.company.zicure.shopmarket.activity.ShopActivity;

/**
 * Created by Pakgon on 11/9/2017 AD.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {
    private Context context = null;
    public CategoryAdapter(Context context){
        this.context = context;
    }

    @Override
    public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_category, null);

        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryHolder holder, int position) {
        if (position == 0) {
            holder.title.setText("ตรวจสอบราคาสินค้าและคำนวณราคารวม");
            holder.setOnItemClick(new OnClickCategoryListener() {
                @Override
                public void setItemClick(View view, int position) {
                    Intent intent = new Intent(context, ShopActivity.class);
                    context.startActivity(intent);
                }
            });
        }

        if (position == 1) {
            holder.title.setText("ค้นหาเชลจำหน่ายสินค้า");
            holder.setOnItemClick(new OnClickCategoryListener() {
                @Override
                public void setItemClick(View view, int position) {
                    Intent intent = new Intent(context, SearchProductActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class CategoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imgCategory;
        public TextView title;
        public OnClickCategoryListener onClickCategoryListener;

        public CategoryHolder(View itemView) {
            super(itemView);
            imgCategory = (ImageView) itemView.findViewById(R.id.img_category);
            title = (TextView) itemView.findViewById(R.id.title_category);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onClickCategoryListener.setItemClick(view, getAdapterPosition());
        }

        public void setOnItemClick(OnClickCategoryListener onClickCategory){
            onClickCategoryListener = onClickCategory;
        }
    }

    public interface OnClickCategoryListener {
        void setItemClick(View view, int position);
    }
}
