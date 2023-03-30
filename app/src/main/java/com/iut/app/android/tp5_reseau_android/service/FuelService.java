package com.iut.app.android.tp5_reseau_android.service;

import com.iut.app.android.tp5_reseau_android.fuel.FuelResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FuelService {

    @GET("https://public.opendatasoft.com/api/records/1.0/search/?dataset=prix_des_carburants_j_7&q=&rows=20&start=0&sort=update")
    Call<FuelResponse> getFuelResponse();

    @GET("?dataset=prix_des_carburants_j_7")
    Call<FuelResponse> getFuelData(@Query("rows") int rows, @Query("q") String query);

    @GET("?dataset=prix_des_carburants_j_7")
    Call<FuelResponse> getFuelData(@Query("rows") int rows, @Query("start") int offset, @Query("q") String query);
}
