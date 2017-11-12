package com.company.zicure.shopmarket.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.company.zicure.shopmarket.R;
import com.company.zicure.shopmarket.network.ClientHttp;

/**
 * Created by ballomo on 11/12/2017 AD.
 */

public class SearchProductAdapter extends RecyclerView.Adapter<SearchProductAdapter.SearchProductHolder>{

    private Context context = null;
    public SearchProductAdapter(Context context){
        this.context = context;
    }

    @Override
    public SearchProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_search_product, null);
        return new SearchProductHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchProductHolder holder, final int position) {
        holder.imgSearchItem.setImageResource(R.drawable.yumyum12);

        holder.setClickCategory(new CategoryAdapter.OnClickCategoryListener() {
            @Override
            public void setItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        ClientHttp.getInstance(context).requestLight(1);
                        break;
                    case 1:
                        ClientHttp.getInstance(context).requestLight(2);
                        break;
                    case 2:
                        ClientHttp.getInstance(context).requestLight(3);
                        break;
                    case 3:
                        ClientHttp.getInstance(context).requestLight(4);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class SearchProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imgSearchItem;
        public CategoryAdapter.OnClickCategoryListener clickCategory;

        public SearchProductHolder(View itemView) {
            super(itemView);
            imgSearchItem = (ImageView) itemView.findViewById(R.id.img_search_item);
            itemView.setOnClickListener(this);
        }

        public void setClickCategory(CategoryAdapter.OnClickCategoryListener clickCategory){
            this.clickCategory = clickCategory;
        }

        @Override
        public void onClick(View v) {
            clickCategory.setItemClick(v, getAdapterPosition());
        }
    }
}
