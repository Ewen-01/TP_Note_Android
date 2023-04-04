package com.iut.app.android.tp5_reseau_android.model;

import android.util.Log;

import com.iut.app.android.tp5_reseau_android.MainActivity;
import com.iut.app.android.tp5_reseau_android.fuel.FuelResponse;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Cache;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityController {

    private final ApiManager apiManager;
    Map<String,String> parameters;

    public MainActivityController(int offset) {
        apiManager = ApiManager.getInstance();
        parameters = new HashMap<>();
        parameters.put("rows", String.valueOf(MainActivity.ROWS));
        parameters.put("dataset", "prix_des_carburants_j_7");
        parameters.put("start", String.valueOf(offset));
    }

    public void getNameStationResponse(FuelStationCallBack callBack) {
        Call<FuelResponse> callTimeParis = apiManager.getFuelService().getFuelResponse2(parameters);
        callTimeParis.enqueue(new Callback<FuelResponse>() {
            @Override
            public void onResponse(Call<FuelResponse> call, Response<FuelResponse> response) {
                if (response.isSuccessful()) {
                    FuelResponse fuelResponse = response.body();
                    //CacheManager.getInstance().setFuelResponse(fuelResponse);
                    callBack.getTimeResponseSuccess(fuelResponse);
                } else {
                    Log.e("onResponse", "Not successfull : " + response.code());
                    callBack.getTimeResponseError("Erreur le serveur a repondu status : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<FuelResponse> call, Throwable t) {
                Log.e("onFailure", t.getLocalizedMessage());
                callBack.getTimeResponseError("Erreur lors de la requete : " + t.getLocalizedMessage());
            }
        });

    }
}