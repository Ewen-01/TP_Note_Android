package com.iut.app.android.tp5_reseau_android.fuel;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FuelResponseParameters implements Parcelable {

    @SerializedName("dataset")
    @Expose
    private String dataset;

    @SerializedName("rows")
    @Expose
    private int rows;

    @SerializedName("start")
    @Expose
    private int start;

    @SerializedName("sort")
    @Expose
    private List<String> sort;

    @SerializedName("format")
    @Expose
    private String format;

    @SerializedName("timezone")
    @Expose
    private String timezone;

    protected FuelResponseParameters(Parcel in) {
        dataset = in.readString();
        rows = in.readInt();
        start = in.readInt();
        sort = in.createStringArrayList();
        format = in.readString();
        timezone = in.readString();
    }

    public static final Creator<FuelResponseParameters> CREATOR = new Creator<FuelResponseParameters>() {
        @Override
        public FuelResponseParameters createFromParcel(Parcel in) {
            return new FuelResponseParameters(in);
        }

        @Override
        public FuelResponseParameters[] newArray(int size) {
            return new FuelResponseParameters[size];
        }
    };

    public String getDataset() {
        return dataset;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public List<String> getSort() {
        return sort;
    }

    public void setSort(List<String> sort) {
        this.sort = sort;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(dataset);
        dest.writeInt(rows);
        dest.writeInt(start);
        dest.writeStringList(sort);
        dest.writeString(format);
        dest.writeString(timezone);
    }
}
