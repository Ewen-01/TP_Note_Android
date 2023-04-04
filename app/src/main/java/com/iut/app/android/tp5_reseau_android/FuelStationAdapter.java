package com.iut.app.android.tp5_reseau_android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iut.app.android.tp5_reseau_android.fuel.FuelResponse;
import com.iut.app.android.tp5_reseau_android.fuel.FuelStation;
import com.iut.app.android.tp5_reseau_android.fuel.FuelStationDataSet;
import com.iut.app.android.tp5_reseau_android.model.CacheManager;

import java.util.List;

public class FuelStationAdapter extends RecyclerView.Adapter<FuelStationAdapter.ViewHolder> {

    FuelResponse fuelResponse;
    List<FuelStationDataSet> recordList;

    CacheManager cacheManager;

    public FuelStationAdapter(FuelResponse fuelResponses) {

        fuelResponse = fuelResponses;
        recordList = fuelResponses.getRecords();
        cacheManager = CacheManager.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_item, parent, false);

        return new FuelStationAdapter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull FuelStationAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        FuelStation fuelStation;
        if(position<0){
            fuelStation = recordList.get(0).getFields();
        }
        else{
            fuelStation = recordList.get(position).getFields();
        }

        holder.station_name.setText(fuelStation.getName());
        holder.station_location.setText(fuelStation.getCity());
        holder.station_price.setText("SP95 :" + fuelStation.getPriceSp95() + " E10 :" + fuelStation.getPriceE10() + " Gazole :" + fuelStation.getPriceGazole() + " E85 :" + fuelStation.getPriceE85());


        /*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), CarpoolAreaDetail.class);
                i.putExtra(CarpoolAreaDetail.DATA, (Serializable) itemField);
                holder.itemView.getContext().startActivity(i);

            }
        });
         */
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public void setRecordList(List<FuelStationDataSet> recordList) {
        this.recordList = recordList;
    }




    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView station_name, station_location, station_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            station_name = itemView.findViewById(R.id.station_name);
            station_location = itemView.findViewById(R.id.station_location);
            station_price = itemView.findViewById(R.id.station_price);
        }
    }
}
