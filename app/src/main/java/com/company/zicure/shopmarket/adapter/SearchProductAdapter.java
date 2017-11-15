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
    private CategoryAdapter.OnClickCategoryListener2 onClickCategoryListener2 = null;
    private CategoryAdapter.OnClickCategoryListener3 onClickCategoryListener3 = null;
    private CategoryAdapter.OnClickCategoryListener4 onClickCategoryListener4 = null;
    private Context context = null;

    public SearchProductAdapter(Context context, CategoryAdapter.OnClickCategoryListener onClickCategoryListener,
                                CategoryAdapter.OnClickCategoryListener2 onClickCategoryListener2,
                                CategoryAdapter.OnClickCategoryListener3 onClickCategoryListener3,
                                CategoryAdapter.OnClickCategoryListener4 onClickCategoryListener4){
        this.context = context;
        this.onClickCategoryListener = onClickCategoryListener;
        this.onClickCategoryListener2 = onClickCategoryListener2;
        this.onClickCategoryListener3 = onClickCategoryListener3;
        this.onClickCategoryListener4 = onClickCategoryListener4;
    }

    @Override
    public SearchProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_search_product, null);
        return new SearchProductHolder(view);
    }

    @Override
    public void onBindViewHolder(final SearchProductHolder holder, final int position) {
        switch (position) {
            case 0:
                holder.imgSearchItem.setImageResource(R.drawable.logo_rosdee);
                holder.setUpImage(R.drawable.logo_rosdee_press,R.drawable.logo_rosdee, holder, position);

                break;
            case 1:
                holder.imgSearchItem.setImageResource(R.drawable.logo_yumyumcup);
                holder.setUpImage(R.drawable.logo_yumyumcup_press,R.drawable.logo_yumyumcup, holder, position);
                break;
            case 2:
                holder.imgSearchItem.setImageResource(R.drawable.logo_birdy);
                holder.setUpImage(R.drawable.logo_birdy_press,R.drawable.logo_birdy, holder, position);
                break;
            case 3:
                holder.imgSearchItem.setImageResource(R.drawable.logo_birdy_3in1);
                holder.setUpImage(R.drawable.logo_birdy_3in1_press,R.drawable.logo_birdy_3in1, holder, position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class SearchProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imgSearchItem;
        public View shadow;

        public CategoryAdapter.OnClickCategoryListener clickCategory;
        public CategoryAdapter.OnClickCategoryListener2 clickCategory2;
        public CategoryAdapter.OnClickCategoryListener3 clickCategory3;
        public CategoryAdapter.OnClickCategoryListener4 clickCategory4;

        public SearchProductActivity.OnCloseLightListener closeLight;
        public SearchProductActivity.OnCloseLightListener2 closeLight2;
        public SearchProductActivity.OnCloseLightListener3 closeLight3;
        public SearchProductActivity.OnCloseLightListener4 closeLight4;

        public SearchProductHolder(View itemView) {
            super(itemView);
            imgSearchItem = (ImageView) itemView.findViewById(R.id.img_search_item);
            shadow = (View) itemView.findViewById(R.id.shadow_bg);
            itemView.setOnClickListener(this);
        }

        public void setClickCategory(CategoryAdapter.OnClickCategoryListener clickCategory){
            this.clickCategory = clickCategory;
        }

        public void setClickCategory2(CategoryAdapter.OnClickCategoryListener2 clickCategory2){
            this.clickCategory2 = clickCategory2;
        }

        public void setClickCategory3(CategoryAdapter.OnClickCategoryListener3 clickCategory3){
            this.clickCategory3 = clickCategory3;
        }

        public void setClickCategory4(CategoryAdapter.OnClickCategoryListener4 clickCategory4){
            this.clickCategory4 = clickCategory4;
        }

        public void setCloseLight(SearchProductActivity.OnCloseLightListener closeLight){
            this.closeLight = closeLight;
        }

        public void setCloseLight2(SearchProductActivity.OnCloseLightListener2 closeLight2){
            this.closeLight2 = closeLight2;
        }

        public void setCloseLight3(SearchProductActivity.OnCloseLightListener3 closeLight3){
            this.closeLight3 = closeLight3;
        }

        public void setCloseLight4(SearchProductActivity.OnCloseLightListener4 closeLight4){
            this.closeLight4 = closeLight4;
        }

        @Override
        public void onClick(View v) {
            switch (getAdapterPosition()){
                case 0:
                    clickCategory.setItemClick(v, getAdapterPosition(), closeLight);
                    break;
                case 1:
                    clickCategory2.setItemClick2(v, getAdapterPosition(), closeLight2);
                    break;
                case 2:
                    clickCategory3.setItemClick3(v, getAdapterPosition(), closeLight3);
                    break;
                case 3:
                    clickCategory4.setItemClick4(v, getAdapterPosition(), closeLight4);
                    break;
            }
        }

        public void setUpImage(final int imagePress,final int image, final SearchProductHolder holder, int position){
            switch (position) {
                case 0:
                    holder.setClickCategory(new CategoryAdapter.OnClickCategoryListener() {
                        @Override
                        public void setItemClick(View view, int position, SearchProductActivity.OnCloseLightListener onCloseLightListener) {
                            holder.shadow.setVisibility(View.VISIBLE);
                            onClickCategoryListener.setItemClick(view, position, onCloseLightListener);

                            holder.imgSearchItem.setImageResource(imagePress);
                        }
                    });

                    holder.setCloseLight(new SearchProductActivity.OnCloseLightListener() {
                        @Override
                        public void setClose(int id) {
                            holder.shadow.setVisibility(View.GONE);
                            holder.imgSearchItem.setImageResource(image);
                        }
                    });
                    break;

                case 1:
                    holder.setClickCategory2(new CategoryAdapter.OnClickCategoryListener2() {
                        @Override
                        public void setItemClick2(View view, int position, SearchProductActivity.OnCloseLightListener2 onCloseLightListener) {
                            holder.shadow.setVisibility(View.VISIBLE);
                            onClickCategoryListener2.setItemClick2(view, position, onCloseLightListener);

                            holder.imgSearchItem.setImageResource(imagePress);
                        }
                    });

                    holder.setCloseLight2(new SearchProductActivity.OnCloseLightListener2() {
                        @Override
                        public void setClose() {
                            holder.shadow.setVisibility(View.GONE);
                            holder.imgSearchItem.setImageResource(image);
                        }
                    });
                    break;

                case 2:
                    holder.setClickCategory3(new CategoryAdapter.OnClickCategoryListener3() {
                        @Override
                        public void setItemClick3(View view, int position, SearchProductActivity.OnCloseLightListener3 onCloseLightListener) {
                            holder.shadow.setVisibility(View.VISIBLE);
                            onClickCategoryListener3.setItemClick3(view, position, onCloseLightListener);

                            holder.imgSearchItem.setImageResource(imagePress);
                        }
                    });

                    holder.setCloseLight3(new SearchProductActivity.OnCloseLightListener3() {
                        @Override
                        public void setClose() {
                            holder.shadow.setVisibility(View.GONE);
                            holder.imgSearchItem.setImageResource(image);
                        }
                    });
                    break;

                case 3:
                    holder.setClickCategory4(new CategoryAdapter.OnClickCategoryListener4() {
                        @Override
                        public void setItemClick4(View view, int position, SearchProductActivity.OnCloseLightListener4 onCloseLightListener) {
                            holder.shadow.setVisibility(View.VISIBLE);
                            holder.imgSearchItem.setImageResource(imagePress);
                            onClickCategoryListener4.setItemClick4(view, position, onCloseLightListener);
                        }
                    });

                    holder.setCloseLight4(new SearchProductActivity.OnCloseLightListener4() {
                        @Override
                        public void setClose() {
                            holder.shadow.setVisibility(View.GONE);
                            holder.imgSearchItem.setImageResource(image);
                        }
                    });
                    break;
            }
        }
    }
}
