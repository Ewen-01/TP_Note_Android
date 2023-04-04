package com.iut.app.android.tp5_reseau_android;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.navigation.NavigationView;
import com.iut.app.android.tp5_reseau_android.Utility.NetworkChangeListener;
import com.iut.app.android.tp5_reseau_android.fuel.FuelResponse;
import com.iut.app.android.tp5_reseau_android.fuel.FuelStation;
import com.iut.app.android.tp5_reseau_android.model.FuelStationCallBack;
import com.iut.app.android.tp5_reseau_android.model.MainActivityController;
import com.iut.app.android.tp5_reseau_android.service.FuelService;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback, FuelStationCallBack {

    FuelService fuelService = null;
    private DrawerLayout drawerLayout;
    public final static String USER_LOCATION_KEY = "USER_LOCATION_KEY";
    public static int ROWS = 10;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    public final static String FUEL_STATION_KEY = "FUEL_STATION_KEY";

    FuelResponse fuelResponse;
    ListeFragment listeFragment;
    NavigationFragment navigationFragment;

    LatLng userLoc;

    MainActivity mainActivity;
    MainController mainController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mainActivity = this;
        mainController = new MainController();



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
        navigationFragment = new NavigationFragment();


        //getFuelResponse();



        getApiData();
        drawerLayout.closeDrawer(GravityCompat.START);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                break;

            case R.id.nav_liste:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, listeFragment).commit();
                break;

            case R.id.nav_navigation:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, navigationFragment).commit();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }


    public void getApiData(){
        mainController.getFuelResponse(new FuelStationCallBackTwo() {
            @Override
            public void getFuelResponseSuccess(FuelResponse data) {
                fuelResponse = data;
                Toast.makeText(getApplicationContext(), "API request Successful !", Toast.LENGTH_LONG).show();

                listeFragment = ListeFragment.newInstance(data);

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, listeFragment).commit();

                Bundle listeFragmentBundle = new Bundle();
                listeFragmentBundle.putParcelable(FUEL_STATION_KEY, data);
                listeFragment.setArguments(listeFragmentBundle);

                Bundle mapsFragmentBundle = new Bundle();
                mapsFragmentBundle.putParcelable(FUEL_STATION_KEY, data);
                mapsFragmentBundle.putParcelable(USER_LOCATION_KEY, userLoc);

                navigationFragment.setArguments(mapsFragmentBundle);

            }

            @Override
            public void getFuelResponseError(String error) {
                Log.e("getCarpoolAreaResponseError", error);
            }
        });
    }


    /*
    private void createRetrofitFuelResponse(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();



        Retrofit retrofitFuelResponse = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        fuelService = retrofitFuelResponse.create(FuelService.class);
    }

     */


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

    }


    @Override
    public void getTimeResponseSuccess(FuelResponse fuelResponse) {

    }

    @Override
    public void getTimeResponseError(String message) {

    }
}