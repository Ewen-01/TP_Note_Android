package com.iut.app.android.tp5_reseau_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FuelStationAdapter extends RecyclerView.Adapter<FuelStationAdapter.MyViewHolder>{

    Context context;
    ArrayList<StationClass> listStation;

    public FuelStationAdapter(Context context, ArrayList<StationClass> listStation) {

        this.context = context;
        this.listStation = listStation;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_item,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StationClass stations = listStation.get(position);
        holder.station_name.setText(stations.nom);
        holder.station_location.setText(stations.Ville);
        holder.station_price.setText("  SP95 : " + stations.p95 + "  e10 : " + stations.pe10 + "  e85 : "+ stations.pe85 + "  Gazole : " + stations.pGazole);
    }

    @Override
    public int getItemCount() {
        if(listStation == null){
            return 0;
        }
        else{
            return listStation.size();
        }

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView station_name;
        TextView station_location;
        TextView station_price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            station_name = itemView.findViewById(R.id.station_name);
            station_location = itemView.findViewById(R.id.station_location);
            station_price = itemView.findViewById(R.id.station_price);
        }
    }
}
