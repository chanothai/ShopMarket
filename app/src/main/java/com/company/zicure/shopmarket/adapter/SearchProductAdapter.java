package com.company.zicure.shopmarket.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.company.zicure.shopmarket.R;
import com.company.zicure.shopmarket.activity.SearchProductActivity;
import com.company.zicure.shopmarket.network.ClientHttp;

/**
 * Created by ballomo on 11/12/2017 AD.
 */

public class SearchProductAdapter extends RecyclerView.Adapter<SearchProductAdapter.SearchProductHolder> {

    private CategoryAdapter.OnClickCategoryListener onClickCategoryListener = null;
    private Context context = null;

    public SearchProductAdapter(Context context, CategoryAdapter.OnClickCategoryListener onClickCategoryListener){
        this.context = context;
        this.onClickCategoryListener = onClickCategoryListener;
    }

    @Override
    public SearchProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_search_product, null);
        return new SearchProductHolder(view);
    }

    @Override
    public void onBindViewHolder(final SearchProductHolder holder, final int position) {
        holder.imgSearchItem.setImageResource(R.drawable.yumyum12);

        holder.setClickCategory(new CategoryAdapter.OnClickCategoryListener() {
            @Override
            public void setItemClick(View view, int position, SearchProductActivity.OnCloseLightListener onCloseLightListener) {
                holder.shadow.setVisibility(View.VISIBLE);
                onClickCategoryListener.setItemClick(view, position, onCloseLightListener);
            }
        });

        holder.setCloseLight(new SearchProductActivity.OnCloseLightListener() {
            @Override
            public void setClose() {
                holder.shadow.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class SearchProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imgSearchItem;
        public View shadow;

        public CategoryAdapter.OnClickCategoryListener clickCategory;
        public SearchProductActivity.OnCloseLightListener closeLight;

        public SearchProductHolder(View itemView) {
            super(itemView);
            imgSearchItem = (ImageView) itemView.findViewById(R.id.img_search_item);
            shadow = (View) itemView.findViewById(R.id.shadow_bg);
            itemView.setOnClickListener(this);
        }

        public void setClickCategory(CategoryAdapter.OnClickCategoryListener clickCategory){
            this.clickCategory = clickCategory;
        }

        public void setCloseLight(SearchProductActivity.OnCloseLightListener closeLight){
            this.closeLight = closeLight;
        }

        @Override
        public void onClick(View v) {
            clickCategory.setItemClick(v, getAdapterPosition(), closeLight);
        }
    }
}
