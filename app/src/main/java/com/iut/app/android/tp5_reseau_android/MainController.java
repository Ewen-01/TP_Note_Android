package com.iut.app.android.tp5_reseau_android;


import android.util.Log;

import com.iut.app.android.tp5_reseau_android.fuel.FuelResponse;
import com.iut.app.android.tp5_reseau_android.model.ApiManager;
import com.iut.app.android.tp5_reseau_android.model.CacheManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {

    private final ApiManager apiManager;

    public MainController() {
        apiManager = ApiManager.getInstance();

    }

    public void getFuelResponse(FuelStationCallBackTwo callback){
        Call<FuelResponse> call = apiManager.getFuelService().getFuelResponse();
        call.enqueue(new Callback<FuelResponse>() {
            @Override
            public void onResponse(Call<FuelResponse> call, Response<FuelResponse> response) {
                if(response.isSuccessful()){
                    FuelResponse fuelresponse = response.body();
                    CacheManager cm =  CacheManager.getInstance();
                    cm.setFuelResponse(fuelresponse);
                    callback.getFuelResponseSuccess(fuelresponse);
                }else{
                    Log.e("onResponse", "Not successfull : " + response.code());
                    callback.getFuelResponseError("Erreur le serveur a repondu status : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<FuelResponse> call, Throwable t) {
                Log.e("onFailure", t.getLocalizedMessage());
                callback.getFuelResponseError("Erreur lors de la requete : " + t.getLocalizedMessage());

            }
        });
    }
}
