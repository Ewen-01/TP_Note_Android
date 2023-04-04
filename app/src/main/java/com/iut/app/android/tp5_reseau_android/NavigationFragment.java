package com.iut.app.android.tp5_reseau_android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.iut.app.android.tp5_reseau_android.fuel.FuelResponse;
import com.iut.app.android.tp5_reseau_android.fuel.FuelStation;
import com.iut.app.android.tp5_reseau_android.fuel.FuelStationDataSet;
import com.iut.app.android.tp5_reseau_android.model.CacheManager;

import java.util.List;

public class NavigationFragment extends Fragment {

    FuelResponse fuelresponse;
    LatLng userLoc = new LatLng(0,0);

    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            fuelresponse = CacheManager.getInstance().getFuelResponse();
            /*
            if (getArguments() != null) {
                userLoc = getArguments().getParcelable(MainActivity.USER_LOCATION_KEY);
            }

             */
            pointsMap(googleMap);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }


    public void pointsMap(GoogleMap map) {
        List<FuelStationDataSet> recordList = fuelresponse.getRecords();

        LatLng origin = userLoc;
        map.moveCamera(CameraUpdateFactory.newLatLng(origin));
        posPoints(origin,map);

        for (FuelStationDataSet record : recordList) {
            List<Float> coord = record.getFields().getGeoPoint();
            LatLng pin = new LatLng(coord.get(0), coord.get(1));
            map.addMarker(new MarkerOptions().position(pin).title(record.getFields().getCity()));
        }
        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(@NonNull Marker marker) {
                Toast.makeText(getContext(),"Clicked on " + marker.getTitle(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void posPoints(LatLng position, GoogleMap map) {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(position)
                .zoom(7.0f).build();

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}