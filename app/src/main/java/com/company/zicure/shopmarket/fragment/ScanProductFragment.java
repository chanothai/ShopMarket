package com.company.zicure.shopmarket.fragment;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.company.zicure.shopmarket.R;
import com.company.zicure.shopmarket.activity.MainActivity;
import com.company.zicure.shopmarket.activity.ShopActivity;
import com.company.zicure.shopmarket.adapter.DetailProductAdapter;
import com.company.zicure.shopmarket.common.BaseActivity;
import com.company.zicure.shopmarket.model.ItemStoreModel;
import com.company.zicure.shopmarket.util.ModelCart;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScanProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScanProductFragment extends Fragment implements ZXingScannerView.ResultHandler{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Make: View
    private ZXingScannerView scannerView = null;
    private RecyclerView recyclerView = null;
    private ImageView imgItem = null;

    // Make: Properties
    private DetailProductAdapter detailProductAdapter = null;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ScanProductFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScanProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScanProductFragment newInstance(String param1, String param2) {
        ScanProductFragment fragment = new ScanProductFragment();
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
        View root = inflater.inflate(R.layout.fragment_scan_product, container, false);
        scannerView = (ZXingScannerView) root.findViewById(R.id.view_scan_qr);
        scannerView.setResultHandler(this);

        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerview_category);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        imgItem = (ImageView) root.findViewById(R.id.img_product);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            permissionCamera();
        }
    }

    private void permissionCamera(){
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 111);

        }
    }

    public void startCamera(){
        scannerView.startCamera();
    }

    public void stopCamera(){
        scannerView.stopCamera();
    }

    public void resumeCamera(){
        scannerView.resumeCameraPreview(this);
    }

    @Override
    public void handleResult(Result result) {
        String id = result.getText();
        if (ModelCart.getInstance().getItemStoreModel().size() > 0){
            for (int i = 0; i < ModelCart.getInstance().getItemStoreModel().size(); i++) {
                if (!id.equalsIgnoreCase(ModelCart.getInstance().getItemStoreModel().get(i).getBarcode())){
                    ((ShopActivity)getActivity()).queryItem(id);
                }
            }
        }else{
            ((ShopActivity)getActivity()).queryItem(id);
        }
    }

    public void setDetailItem(ItemStoreModel itemStoreModel){
        detailProductAdapter = new DetailProductAdapter(itemStoreModel);
        recyclerView.setAdapter(detailProductAdapter);

        int imgId = getActivity().getResources().getIdentifier(itemStoreModel.getImgItem(), "drawable", getActivity().getPackageName());
        imgItem.setImageResource(imgId);
    }
}
