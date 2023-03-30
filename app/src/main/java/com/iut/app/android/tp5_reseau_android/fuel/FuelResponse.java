package com.iut.app.android.tp5_reseau_android.fuel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FuelResponse {

    @SerializedName("nhits")
    @Expose
    private int nHits;

    @SerializedName("parameters")
    @Expose
    private FuelResponseParameters fuelResponseParameters;

    @SerializedName("records")
    @Expose
    private List<FuelStationDataSet> fuelStationDataSets;

    public int getNHits() {
        return nHits;
    }

    public void setNHits(int nHits) {
        this.nHits = nHits;
    }

    public FuelResponseParameters getParameters() {
        return fuelResponseParameters;
    }

    public void setParameters(FuelResponseParameters fuelResponseParameters) {
        this.fuelResponseParameters = fuelResponseParameters;
    }

    public List<FuelStationDataSet> getRecords() {
        return fuelStationDataSets;
    }

    public void setRecords(List<FuelStationDataSet> fuelStationDataSets) {
        this.fuelStationDataSets = fuelStationDataSets;
    }

}
