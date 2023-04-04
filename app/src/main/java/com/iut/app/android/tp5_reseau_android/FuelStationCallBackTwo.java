package com.iut.app.android.tp5_reseau_android;

import com.iut.app.android.tp5_reseau_android.fuel.FuelResponse;

public interface FuelStationCallBackTwo {
    void getFuelResponseSuccess(FuelResponse fuelResponse);
    void getFuelResponseError(String error);
}
