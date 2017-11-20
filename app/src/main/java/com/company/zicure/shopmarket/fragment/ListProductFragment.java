package com.company.zicure.shopmarket.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import com.company.zicure.shopmarket.R;
import com.company.zicure.shopmarket.activity.ShopActivity;
import com.company.zicure.shopmarket.adapter.ListProductAdapter;
import com.company.zicure.shopmarket.model.ItemStoreModel;
import com.company.zicure.shopmarket.util.ModelCart;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListProductFragment extends Fragment implements ListProductAdapter.OnResponsePriceItem, ListProductAdapter.OnRemoveItemListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Make : View
    private RecyclerView recyclerView = null;
    private TextView txtResultPrice = null;
    private CheckBox btnDeleteItem = null;

    //Make : properties
    private ListProductAdapter listProductAdapter = null;
    private int resultPrice = 0;
    private int totalAmount = 0;
    private String baht = " บาท";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ListProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListProductFragment newInstance(String param1, String param2) {
        ListProductFragment fragment = new ListProductFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_list_product, container, false);
        bindView(root);
        return root;
    }

    private void bindView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_category);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        txtResultPrice = (TextView) view.findViewById(R.id.txt_result_price);
        btnDeleteItem = (CheckBox) view.findViewById(R.id.btn_remove_item);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            if (ModelCart.getInstance().getItemStoreModel() != null || ModelCart.getInstance().getItemStoreModel().size() > 0) {
                setStoreItem(ModelCart.getInstance().getItemStoreModel());
            }
        }

        btnDeleteItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean check) {
                if (check){
                    ModelCart.getInstance().getItemStoreModel().clear();
                    if (listProductAdapter != null) {
                        listProductAdapter.notifyDataSetChanged();
                        totalAmount = 0;
                        txtResultPrice.setText(String.valueOf(totalAmount) +" บ.");

                        ((ScanProductFragment)getFragmentManager().findFragmentByTag("scan_product_fragment")).setDetailItem(null);
                    }
                }
            }
        });
    }

    public void setStoreItem(ArrayList<ItemStoreModel> arrItem){
        listProductAdapter = new ListProductAdapter(getActivity(), this, this);
        recyclerView.setAdapter(listProductAdapter);

        int price = 0;

        for (int i = 0; i < arrItem.size(); i++) {
            price = Integer.parseInt(arrItem.get(i).getPrice());
            resultPrice += price;
        }

        txtResultPrice.setText(String.valueOf(resultPrice) + baht);
    }

    public void updateStoreItem(){
        listProductAdapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(ModelCart.getInstance().getItemStoreModel().size());
    }

    public void updateResultTotalPrice(int price){
        totalAmount += price;
        txtResultPrice.setText(String.valueOf(totalAmount) + baht);
    }

    @Override
    public void setOnPlusPriceItem(int priceItem) {
        totalAmount += priceItem;
        txtResultPrice.setText(String.valueOf(totalAmount) + baht);
    }

    @Override
    public void setOnNegativePriceItem(int priceItem) {
        totalAmount -= priceItem;
        txtResultPrice.setText(String.valueOf(totalAmount) + baht);
    }


    @Override
    public void setOnRemove(int price, int position) {
        listProductAdapter.notifyDataSetChanged();

        ModelCart.getInstance().getItemStoreModel().remove(position);
        totalAmount -= price;
        txtResultPrice.setText(String.valueOf(totalAmount) + baht);

        ((ShopActivity)getActivity()).dismissDialog();
        ((ScanProductFragment)getFragmentManager().findFragmentByTag("scan_product_fragment")).setDetailItem(null);
    }
}
