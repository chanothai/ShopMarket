package com.company.zicure.shopmarket.network;

import com.company.zicure.shopmarket.model.ResponseStatusLight;
import com.company.zicure.shopmarket.model.ResponseStatusLight2;
import com.company.zicure.shopmarket.model.ResponseStatusLight3;
import com.company.zicure.shopmarket.model.ResponseStatusLight4;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by BallOmO on 10/11/2016 AD.
 */
public interface LogApi {
    @GET("/led{id}/{status}")
    Call<ResponseStatusLight> callLight(@Path("id") int id, @Path("status") int status);

    @GET("/led{id}/{status}")
    Call<ResponseStatusLight2> callLight2(@Path("id") int id, @Path("status") int status);

    @GET("/led{id}/{status}")
    Call<ResponseStatusLight3> callLight3(@Path("id") int id, @Path("status") int status);

    @GET("/led{id}/{status}")
    Call<ResponseStatusLight4> callLight4(@Path("id") int id, @Path("status") int status);
}
