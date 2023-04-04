package com.iut.app.android.tp5_reseau_android;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;

import com.iut.app.android.tp5_reseau_android.Utility.EndRecyclerViewScrollListener;
import com.iut.app.android.tp5_reseau_android.fuel.FuelResponse;
import com.iut.app.android.tp5_reseau_android.fuel.FuelStation;
import com.iut.app.android.tp5_reseau_android.fuel.FuelStationDataSet;
import com.iut.app.android.tp5_reseau_android.model.CacheManager;
import com.iut.app.android.tp5_reseau_android.model.FuelStationCallBack;
import com.iut.app.android.tp5_reseau_android.model.MainActivityController;
import com.iut.app.android.tp5_reseau_android.service.FuelService;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListeFragment extends Fragment {
    FuelStationAdapter adapter;
    FuelResponse fuelResponse;
    String lastSearch ="";

    private RecyclerView recyclerview;
    EndRecyclerViewScrollListener scrollListener;
    private MainActivityController mainActivityController;
    private static final String FUEL_RESPONSE = "FUEL_RESPONSE";
    private ArrayList<FuelStation> list1;
    FuelService fuelService = null;

    public ListeFragment(){
        // Vide
    }

    public static ListeFragment newInstance(FuelResponse fuelResponse) {
        ListeFragment fragment = new ListeFragment();
        Bundle args = new Bundle();
        args.putParcelable(FUEL_RESPONSE, fuelResponse);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.fuelResponse = CacheManager.getInstance().getFuelResponse();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_liste, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        SearchView searchBar = view.findViewById(R.id.search_bar);
        searchBar.clearFocus();

        adapter = new FuelStationAdapter(fuelResponse);

        //ImageView listVide = (ImageView) view.findViewById(R.id.iv_liste_vide);

        recyclerview = view.findViewById(R.id.recyclerview);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(linearLayoutManager);
        scrollListener = new EndRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.v("...", "Tt : " + totalItemsCount);
                loadNextDataFromAPI(page);
            }
        };
        recyclerview.addOnScrollListener(scrollListener);
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<FuelStationDataSet> records = adapter.fuelResponse.find(s);

                if(records.isEmpty()){
                    Log.e("onTextChanged()", "recordList is empty");

                    //listVide.setVisibility(View.VISIBLE);
                    recyclerview.setVisibility(View.GONE);
                }else{
                    //adapter.recordList.clear();
                    adapter.setRecordList(records);
                    adapter.notifyDataSetChanged();
                    //listVide.setVisibility(View.GONE);
                    recyclerview.setVisibility(View.VISIBLE);
                }
                lastSearch = s;
                return false;
            }
        });

        ObservableArrayList<FuelStationDataSet> records =CacheManager.getInstance().getFuelResponse().getRecords();

        records.addOnListChangedCallback(new ObservableList.OnListChangedCallback() {
            @Override
            public void onChanged(ObservableList sender) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {
                adapter.notifyItemRangeChanged(positionStart,itemCount);
            }

            @Override
            public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
                adapter.notifyItemRangeInserted(positionStart,itemCount);
            }

            @Override
            public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount) {
                adapter.notifyItemRangeChanged(fromPosition,itemCount);
            }

            @Override
            public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
                adapter.notifyItemRangeRemoved(positionStart,itemCount);
            }
        });

        return view;
    }

    public void loadNextDataFromAPI(int page){
        int offset = page * MainActivity.ROWS;
        mainActivityController = new MainActivityController(offset);
        mainActivityController.getNameStationResponse(new FuelStationCallBack() {
            @Override
            public void getTimeResponseSuccess(FuelResponse fuelResponses) {
                fuelResponse.append(fuelResponses, false);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void getTimeResponseError(String message) {
                Log.e("getFuelResponseResponseError", message);

            }


        });
    }
}