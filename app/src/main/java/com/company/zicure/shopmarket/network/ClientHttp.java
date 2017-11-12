package com.company.zicure.shopmarket.network;

import android.content.Context;
import android.util.Log;

import com.company.zicure.shopmarket.model.ResponseStatusLight;
import com.company.zicure.shopmarket.util.EventBusCart;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by BallOmO on 10/13/2016 AD.
 */
public class ClientHttp {
    private Context context = null;
    private static ClientHttp me;

    private final String LOGAPI = "API_RESPONSE";
    private String jsonStr;

    private Retrofit retrofit = null;
    private LogApi service = null;
    private Gson gson = null;

    public ClientHttp(Context context){
        this.context = context;
        retrofit = RetrofitAPI.newInstance("192.168.4.1").getRetrofit();
        service = retrofit.create(LogApi.class);
        gson = new GsonBuilder().disableHtmlEscaping().create();
    }

    public static ClientHttp getInstance(Context context){
        if (me == null){
            me = new ClientHttp(context);
        }
        return me;
    }

    public void requestLight(int path){
        Call<ResponseStatusLight> callLight = service.callLight(path);
        callLight.enqueue(new Callback<ResponseStatusLight>() {
            @Override
            public void onResponse(Call<ResponseStatusLight> call, Response<ResponseStatusLight> response) {
                try{
                    EventBusCart.getInstance().getEventBus().post(response.body());
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseStatusLight> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
