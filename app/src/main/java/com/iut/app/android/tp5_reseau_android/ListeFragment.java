package com.iut.app.android.tp5_reseau_android;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iut.app.android.tp5_reseau_android.fuel.FuelStation;
import com.iut.app.android.tp5_reseau_android.model.FuelStationCallBack;
import com.iut.app.android.tp5_reseau_android.model.MainActivityController;
import com.iut.app.android.tp5_reseau_android.service.FuelService;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListeFragment extends Fragment implements FuelStationCallBack {

    final static String KEY_NOM = "nom";
    final static String KEY_P95 = "p95";
    final static String KEY_PE10 = "pe10";
    final static String KEY_PGAZOLE = "pgazole";
    final static String KEY_PE85 = "pe85";
    final static String KEY_VILLE = "ville";
    final static String KEY_GEOPOSITION = "geoposition";

    //FuelStationAdapter adapter;
    @NonNull View view;

    private ArrayList<StationClass> listStation;
    private ArrayList<FuelStation> list;
    private ArrayList<FuelStation> list2;
    private RecyclerView recyclerview;
    private MainActivityController mainActivityController = new MainActivityController();
    private ArrayList<FuelStation> list1;
    FuelService fuelService = null;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }


    /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_liste, container, false);
    }

     */


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        getNameStationResponse();


    }

    /*
    public void getTimeResponseSuccess(FuelStation station){
        listStation = new ArrayList<>();

        String nom = station.getName(), p95 = String.valueOf(station.getPriceSp95()), pe10 = String.valueOf(station.getPriceE10()), pGazole = String.valueOf(station.getPriceGazole()), pe85 = String.valueOf(station.getPriceE85()), Ville = station.getCity();

        StationClass station1 = new StationClass(nom, p95, pe10, pGazole, pe85, Ville);

        listStation.add(station1);
    }
     */


    private void getNameStationResponse(){
        mainActivityController.getNameStationResponse(this, listStation);
    }


    @Override
    public void getTimeResponseSuccess(ArrayList<StationClass> listStation) {
        recyclerview = view.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
        FuelStationAdapter fuelStationAdapter = new FuelStationAdapter(getContext(), listStation);
        recyclerview.setAdapter(fuelStationAdapter);
        fuelStationAdapter.notifyDataSetChanged();
    }

    @Override
    public void getTimeResponseError(String message) {

    }
}