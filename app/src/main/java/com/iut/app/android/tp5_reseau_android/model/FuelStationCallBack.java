package com.iut.app.android.tp5_reseau_android.model;

import com.iut.app.android.tp5_reseau_android.StationClass;
import com.iut.app.android.tp5_reseau_android.fuel.FuelResponse;
import com.iut.app.android.tp5_reseau_android.fuel.FuelStation;

import java.util.ArrayList;

public interface FuelStationCallBack {

    void getTimeResponseSuccess(ArrayList<StationClass> list);
    void getTimeResponseError(String message);
}
