package com.iut.app.android.tp5_reseau_android.fuel;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FuelResponse implements Parcelable {

    @SerializedName("nhits")
    @Expose
    private int nHits;

    @SerializedName("parameters")
    @Expose
    private FuelResponseParameters fuelResponseParameters;

    @SerializedName("records")
    @Expose
    private ObservableArrayList<FuelStationDataSet> fuelStationDataSets;

    protected FuelResponse(Parcel in) {
        nHits = in.readInt();
    }

    public static final Creator<FuelResponse> CREATOR = new Creator<FuelResponse>() {
        @Override
        public FuelResponse createFromParcel(Parcel in) {
            return new FuelResponse(in);
        }

        @Override
        public FuelResponse[] newArray(int size) {
            return new FuelResponse[size];
        }
    };

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

    public void setRecords(ObservableArrayList<FuelStationDataSet> fuelStationDataSets) {
        this.fuelStationDataSets = fuelStationDataSets;
    }

    public void append(FuelResponse data, boolean appendAtBegin){
        if(appendAtBegin){
            ObservableArrayList<FuelStationDataSet> dataRecord = (ObservableArrayList<FuelStationDataSet>) data.getRecords();
            dataRecord.addAll(this.getRecords());
            this.fuelStationDataSets.clear();
            this.setRecords(dataRecord);
        }else{
            ObservableArrayList<FuelStationDataSet> tempRecords = (ObservableArrayList<FuelStationDataSet>) this.getRecords();
            tempRecords.addAll(data.getRecords());
            this.setRecords(tempRecords);
        }

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int flags) {
        parcel.writeInt(this.nHits);
        parcel.writeParcelable(this.fuelResponseParameters, flags);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            parcel.writeParcelableList(this.fuelStationDataSets, flags);
        }
    }

    public List<FuelStationDataSet> find(CharSequence charSequence){
        List<FuelStationDataSet> goodRecord = new ArrayList<>();
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(charSequence);
        boolean isNumber = matcher.find();
        for(FuelStationDataSet fuelStationDataSet : fuelStationDataSets){
            if(isNumber){
                String zip = fuelStationDataSet.getFields().getCp();
                if(zip.contains(charSequence)){
                    goodRecord.add(fuelStationDataSet);
                }
            }else{
                String name = fuelStationDataSet.getFields().getCity();
                if(name.contains(charSequence)){
                    goodRecord.add(fuelStationDataSet);
                }
            }
        }


        return goodRecord;
    }
}
