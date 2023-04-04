package com.iut.app.android.tp5_reseau_android;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;
import com.iut.app.android.tp5_reseau_android.fuel.FuelResponse;

public class MapFragment extends Fragment {

    FuelResponse fuelResponse;
    LatLng userLoc = new LatLng(0,0);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }
}