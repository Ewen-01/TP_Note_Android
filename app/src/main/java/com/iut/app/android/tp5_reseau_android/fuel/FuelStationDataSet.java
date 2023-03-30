package com.iut.app.android.tp5_reseau_android.fuel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FuelStationDataSet {

    @SerializedName("datasetid")
    @Expose
    private String datasetId;

    @SerializedName("recordid")
    @Expose
    private String recordId;

    @SerializedName("fields")
    @Expose
    private FuelStation fuelStation;

    @SerializedName("geometry")
    @Expose
    private FuelStationCoordinates fuelStationCoordinates;

    @SerializedName("record_timestamp")
    @Expose
    private String recordTimestamp;

    public String getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(String datasetId) {
        this.datasetId = datasetId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public FuelStation getFields() {
        return fuelStation;
    }

    public void setFields(FuelStation fuelStation) {
        this.fuelStation = fuelStation;
    }

    public FuelStationCoordinates getGeometry() {
        return fuelStationCoordinates;
    }

    public void setGeometry(FuelStationCoordinates fuelStationCoordinates) {
        this.fuelStationCoordinates = fuelStationCoordinates;
    }

    public String getRecordTimestamp() {
        return recordTimestamp;
    }

    public void setRecordTimestamp(String recordTimestamp) {
        this.recordTimestamp = recordTimestamp;
    }

}
