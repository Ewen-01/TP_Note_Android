package com.iut.app.android.tp5_reseau_android.model;

import android.util.Log;

import com.iut.app.android.tp5_reseau_android.StationClass;
import com.iut.app.android.tp5_reseau_android.fuel.FuelResponse;
import com.iut.app.android.tp5_reseau_android.fuel.FuelStation;
import com.iut.app.android.tp5_reseau_android.fuel.FuelStationDataSet;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityController {

    private final ApiManager apiManager;

    public MainActivityController() {
        apiManager = ApiManager.getInstance();
    }

    public void getNameStationResponse(FuelStationCallBack callBack, ArrayList<StationClass> listStation) {
        Call<FuelResponse> callTimeParis = apiManager.getFuelService().getFuelResponse();
        callTimeParis.enqueue(new Callback<FuelResponse>() {
            @Override
            public void onResponse(Call<FuelResponse> call, Response<FuelResponse> response) {
                if (response.isSuccessful()) {
                    FuelResponse c = response.body();
                    for(int y = 0; y < c.getRecords().size(); y++){
                        Log.e("onResponse", c.getRecords().get(y).getFields().getName());
                        listStation.get(y).setNom(c.getRecords().get(y).getFields().getName());
                        listStation.get(y).setVille(c.getRecords().get(y).getFields().getCity());
                        listStation.get(y).setGeoPosition(c.getRecords().get(y).getFields().getGeoPoint());
                        listStation.get(y).setNom(c.getRecords().get(y).getFields().getName());
                        listStation.get(y).setP95(String.valueOf(c.getRecords().get(y).getFields().getPriceSp95()));
                        listStation.get(y).setPgazole(String.valueOf(c.getRecords().get(y).getFields().getPriceGazole()));
                        listStation.get(y).setPe85(String.valueOf(c.getRecords().get(y).getFields().getPriceE85()));
                        listStation.get(y).setPe10(String.valueOf(c.getRecords().get(y).getFields().getPriceE10()));

                    }
                    callBack.getTimeResponseSuccess(listStation);



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

    public void getNameStationResponseThread(FuelStationCallBack callBack, ArrayList<StationClass> listStation) {


        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Response<FuelResponse> response = apiManager.getFuelService().getFuelData(20, "").execute();
                    if (response.isSuccessful()) {
                        FuelResponse c = response.body();
                        for(int y = 0; y < c.getRecords().size(); y++){
                            Log.e("onResponse", c.getRecords().get(y).getFields().getName());
                            listStation.get(y).setNom(c.getRecords().get(y).getFields().getName());
                            listStation.get(y).setVille(c.getRecords().get(y).getFields().getCity());
                            listStation.get(y).setGeoPosition(c.getRecords().get(y).getFields().getGeoPoint());
                            listStation.get(y).setNom(c.getRecords().get(y).getFields().getName());
                            listStation.get(y).setP95(String.valueOf(c.getRecords().get(y).getFields().getPriceSp95()));
                            listStation.get(y).setPgazole(String.valueOf(c.getRecords().get(y).getFields().getPriceGazole()));
                            listStation.get(y).setPe85(String.valueOf(c.getRecords().get(y).getFields().getPriceE85()));
                            listStation.get(y).setPe10(String.valueOf(c.getRecords().get(y).getFields().getPriceE10()));

                        }

                        callBack.getTimeResponseSuccess(listStation);
                    } else {
                        Log.e("onResponse", "Not successfull : " + response.code());
                        callBack.getTimeResponseError("Erreur le serveur a repondu status : " + response.code());
                    }

                } catch (IOException e) {
                    Log.e("onResponse", "Not successfull : " + e.getLocalizedMessage());
                    callBack.getTimeResponseError("Erreur lors de la requete : " + e.getLocalizedMessage());
                }

            }
        };

        new Thread(r).start();
    }

}
