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

import com.company.zicure.shopmarket.R;
import com.company.zicure.shopmarket.activity.SearchProductActivity;

/**
 * Created by Pakgon on 11/9/2017 AD.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {
    private Context context = null;
    private CategoryAdapter.OnClickCategoryListener onClickCategoryListener = null;

    public CategoryAdapter(Context context,CategoryAdapter.OnClickCategoryListener onClickCategoryListener){
        this.context = context;
        this.onClickCategoryListener = onClickCategoryListener;
    }


    @Override
    public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_category, null);

        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryHolder holder, int position) {

        holder.setOnItemClick(new OnClickCategoryListener() {
            @Override
            public void setItemClick(View view, int position, SearchProductActivity.OnCloseLightListener onCloseLightListener) {
                onClickCategoryListener.setItemClick(view, position, onCloseLightListener);
            }
        });

        if (position == 0) {
            holder.title.setText("ตรวจสอบราคาสินค้าและคำนวณราคารวม");
            holder.imgCategory.setImageDrawable(context.getDrawable(R.drawable.online_shop));
        }
        else if (position == 1) {
            holder.title.setText("ค้นหาเชลจำหน่ายสินค้า");
            holder.imgCategory.setImageDrawable(context.getDrawable(R.drawable.cart));
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
            onClickCategoryListener.setItemClick(view, getAdapterPosition(), null);
        }

        public void setOnItemClick(OnClickCategoryListener onClickCategory){
            onClickCategoryListener = onClickCategory;
        }
    }

    public interface OnClickCategoryListener {
        void setItemClick(View view, int position, SearchProductActivity.OnCloseLightListener onCloseLightListener);
    }

    public interface OnClickCategoryListener2 {
        void setItemClick2(View view, int position, SearchProductActivity.OnCloseLightListener2 onCloseLightListener);
    }

    public interface OnClickCategoryListener3 {
        void setItemClick3(View view, int position, SearchProductActivity.OnCloseLightListener3 onCloseLightListener);
    }

    public interface OnClickCategoryListener4 {
        void setItemClick4(View view, int position, SearchProductActivity.OnCloseLightListener4 onCloseLightListener);
    }
}
