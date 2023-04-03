package com.iut.app.android.tp5_reseau_android.fuel;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FuelStationDataSet implements Parcelable {

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

    protected FuelStationDataSet(Parcel in) {
        datasetId = in.readString();
        recordId = in.readString();
        recordTimestamp = in.readString();
    }

    public static final Creator<FuelStationDataSet> CREATOR = new Creator<FuelStationDataSet>() {
        @Override
        public FuelStationDataSet createFromParcel(Parcel in) {
            return new FuelStationDataSet(in);
        }

        @Override
        public FuelStationDataSet[] newArray(int size) {
            return new FuelStationDataSet[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(datasetId);
        dest.writeString(recordId);
        dest.writeString(recordTimestamp);
    }
}
