package com.iut.app.android.tp5_reseau_android.model;


import com.iut.app.android.tp5_reseau_android.fuel.FuelResponse;

import java.util.ArrayList;
import java.util.List;

public class CacheManager {
    FuelResponse fuelResponse;

    List<String> favoriteList;
    private static CacheManager instance;
    private CacheManager(){
        favoriteList = new ArrayList<>();
    }

    public static CacheManager getInstance(){
        if(instance == null){
            instance = new CacheManager();
        }
        return instance;
    }

    public void setFuelResponse(FuelResponse fuelResponse){
        this.fuelResponse = fuelResponse;
    }

    public FuelResponse getFuelResponse(){
        return fuelResponse;
    }

    /*
    public void addFavorite(String recordId){
        if(recordId != null){
            favoriteList.add(recordId);
        }
    }

    public List<String> getFavoriteList(){
        return favoriteList;
    }

     */
}
