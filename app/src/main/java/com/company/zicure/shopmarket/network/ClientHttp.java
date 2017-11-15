package com.company.zicure.shopmarket.network;

import android.content.Context;
import android.util.Log;

import com.company.zicure.shopmarket.model.ResponseStatusLight;
import com.company.zicure.shopmarket.model.ResponseStatusLight2;
import com.company.zicure.shopmarket.model.ResponseStatusLight3;
import com.company.zicure.shopmarket.model.ResponseStatusLight4;
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
        retrofit = RetrofitAPI.newInstance("http://192.168.4.1").getRetrofit();
        service = retrofit.create(LogApi.class);
        gson = new GsonBuilder().disableHtmlEscaping().create();
    }

    public static ClientHttp getInstance(Context context){
        if (me == null){
            me = new ClientHttp(context);
        }
        return me;
    }

    public LogApi getService() {
        return service;
    }

    public void requestLight(int path, int status){
        Call<ResponseStatusLight> callLight = service.callLight(path,status);
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
                ResponseStatusLight responseLight = new ResponseStatusLight();
                responseLight.setStatus("offline");
                EventBusCart.getInstance().getEventBus().post(responseLight);
            }
        });
    }

    public void requestLight2(int path, int status){
        Call<ResponseStatusLight2> callLight2 = service.callLight2(path, status);
        callLight2.enqueue(new Callback<ResponseStatusLight2>() {
            @Override
            public void onResponse(Call<ResponseStatusLight2> call, Response<ResponseStatusLight2> response) {
                try{
                    EventBusCart.getInstance().getEventBus().post(response.body());
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseStatusLight2> call, Throwable t) {
                t.printStackTrace();
                ResponseStatusLight2 responseLight = new ResponseStatusLight2();
                responseLight.setStatus("offline");
                EventBusCart.getInstance().getEventBus().post(responseLight);
            }
        });
    }

    public void requestLight3(int path, int status){
        Call<ResponseStatusLight3> callLight = service.callLight3(path, status);
        callLight.enqueue(new Callback<ResponseStatusLight3>() {
            @Override
            public void onResponse(Call<ResponseStatusLight3> call, Response<ResponseStatusLight3> response) {
                try{
                    EventBusCart.getInstance().getEventBus().post(response.body());
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseStatusLight3> call, Throwable t) {
                t.printStackTrace();
                ResponseStatusLight3 responseLight = new ResponseStatusLight3();
                responseLight.setStatus("offline");
                EventBusCart.getInstance().getEventBus().post(responseLight);
            }
        });
    }

    public void requestLight4(int path, int status){
        Call<ResponseStatusLight4> callLight = service.callLight4(path, status);
        callLight.enqueue(new Callback<ResponseStatusLight4>() {
            @Override
            public void onResponse(Call<ResponseStatusLight4> call, Response<ResponseStatusLight4> response) {
                try{
                    EventBusCart.getInstance().getEventBus().post(response.body());
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseStatusLight4> call, Throwable t) {
                t.printStackTrace();
                ResponseStatusLight4 responseLight = new ResponseStatusLight4();
                responseLight.setStatus("offline");
                EventBusCart.getInstance().getEventBus().post(responseLight);
            }
        });
    }
}
