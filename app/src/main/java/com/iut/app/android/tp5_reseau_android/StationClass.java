package com.iut.app.android.tp5_reseau_android;

import android.util.Log;

import com.iut.app.android.tp5_reseau_android.fuel.FuelStation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StationClass implements Serializable {
    String nom;
    String p95;
    String pe10;
    String pGazole;
    String pe85;
    String Ville;
    List<Float> geoPosition;


    public StationClass(String nom, String p95, String pe10, String pGazole, String pe85, String Ville, List<Float> geoPosition){
        this.nom = nom;
        this.p95 = p95;
        this.pe10 = pe10;
        this.pGazole = pGazole;
        this.pe85 = pe85;
        this.Ville = Ville;
        this.geoPosition = geoPosition;
    }

    public StationClass(String nom, String p95, String pe10, String pGazole, String pe85, String Ville){
        this.nom = nom;
        this.p95 = p95;
        this.pe10 = pe10;
        this.pGazole = pGazole;
        this.pe85 = pe85;
        this.Ville = Ville;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setP95(String nom) {
        this.p95 = nom;
    }
    public void setPe10(String nom) {
        this.pe10 = nom;
    }
    public void setPgazole(String nom) {
        this.pGazole = nom;
    }
    public void setPe85(String nom) {
        this.pe85 = nom;
    }
    public void setVille(String nom) {
        this.Ville = nom;
    }
    public void setGeoPosition(List<Float> pos) {
        this.geoPosition = pos;
    }



    public void afficher (){

        Log.d("test affichage : ",nom+" "+ p95+" "+ pe10+" "+ pGazole+" "+ pe85+" " + Ville+" " + geoPosition);
    }

}
