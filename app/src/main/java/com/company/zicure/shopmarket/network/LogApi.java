package com.company.zicure.shopmarket.network;

import com.company.zicure.shopmarket.model.ResponseStatusLight;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by BallOmO on 10/11/2016 AD.
 */
public interface LogApi {
    @GET("/led1/{id}")
    Call<ResponseStatusLight> callLight(@Path("id") int id);
}
