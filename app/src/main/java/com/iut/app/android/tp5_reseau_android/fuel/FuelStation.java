package com.iut.app.android.tp5_reseau_android.fuel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class FuelStation implements Serializable {

    @SerializedName("geo_point")
    @Expose
    private List<Float> geoPoint;

    @SerializedName("price_sp95")
    @Expose
    private float priceSp95;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("automate_24_24")
    @Expose
    private String automate2424;

    @SerializedName("price_e10")
    @Expose
    private float priceE10;

    @SerializedName("pop")
    @Expose
    private String pop;

    @SerializedName("timetable")
    @Expose
    private String timetable;

    @SerializedName("services")
    @Expose
    private String services;

    @SerializedName("brand")
    @Expose
    private String brand;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("shortage")
    @Expose
    private String shortage;

    @SerializedName("cp")
    @Expose
    private String cp;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("fuel")
    @Expose
    private String fuel;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("update")
    @Expose
    private String update;

    @SerializedName("price_e85")
    @Expose
    private float priceE85;

    @SerializedName("price_gazole")
    @Expose
    private float priceGazole;

    public List<Float> getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(List<Float> geoPoint) {
        this.geoPoint = geoPoint;
    }

    public float getPriceSp95() {
        return priceSp95;
    }

    public void setPriceSp95(float priceSp95) {
        this.priceSp95 = priceSp95;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAutomate2424() {
        return automate2424;
    }

    public void setAutomate2424(String automate2424) {
        this.automate2424 = automate2424;
    }

    public float getPriceE10() {
        return priceE10;
    }

    public void setPriceE10(float priceE10) {
        this.priceE10 = priceE10;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getTimetable() {
        return timetable;
    }

    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortage() {
        return shortage;
    }

    public void setShortage(String shortage) {
        this.shortage = shortage;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public float getPriceE85() {
        return priceE85;
    }

    public void setPriceE85(float priceE85) {
        this.priceE85 = priceE85;
    }

    public float getPriceGazole() {
        return priceGazole;
    }

    public void setPriceGazole(float priceGazole) {
        this.priceGazole = priceGazole;
    }

}
