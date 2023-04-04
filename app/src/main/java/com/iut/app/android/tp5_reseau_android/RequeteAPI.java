package com.iut.app.android.tp5_reseau_android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iut.app.android.tp5_reseau_android.fuel.FuelResponse;
import com.iut.app.android.tp5_reseau_android.service.FuelService;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequeteAPI {

    AppCompatActivity appCompatActivity;
    private FuelResponse fuelResponse;
    ListeFragment listeFragment;
    NavigationFragment navigationFragment;
    Context context;
    Activity activity;



    public RequeteAPI(Context context,Activity activity,AppCompatActivity  appCompatActivity,ListeFragment listeFragment, NavigationFragment navigationFragment){
        this.context = context;
        this.activity = activity;
        this.appCompatActivity = appCompatActivity;
        this.listeFragment = listeFragment;
        this.navigationFragment = navigationFragment;
        this.listeFragment = new ListeFragment();
        this.navigationFragment = new NavigationFragment();

    }
    public void apiRequete(String api_url, int row_number, int offset) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api_url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        FuelService service = retrofit.create(FuelService.class);
        Map<String, String> parametreQuery = new HashMap<String, String>();
        parametreQuery.put("rows", String.valueOf(row_number));
        parametreQuery.put("dataset", "prix_des_carburants_j_7");
        parametreQuery.put("start", String.valueOf(offset));

        service.getFuelResponse2(parametreQuery).enqueue(new Callback<FuelResponse>() {
            @Override
            public void onResponse(Call<FuelResponse> call, Response<FuelResponse> response) {
                if (response.isSuccessful()) {
                    fuelResponse = response.body();

                    Bundle listeFragmentBundle = new Bundle();
                    listeFragmentBundle.putParcelable(MainActivity.FUEL_STATION_KEY, fuelResponse);
                    listeFragment.setArguments(listeFragmentBundle);

                    appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, listeFragment).commit();
                } else {
                    Toast.makeText(context, "API bad response...", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<FuelResponse> call, Throwable t) {
                Toast.makeText(context, "API request failed...", Toast.LENGTH_LONG).show();
                Log.e("FAILED API REQ", String.valueOf(t));

            }
        });
    }

    public FuelResponse getFuelResponse(){
        return fuelResponse;
    }

}
