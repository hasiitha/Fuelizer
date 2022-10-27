package com.example.fuelizer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
/*Adapter to petril station Lists*/
public class PetrolStationAdapter extends ArrayAdapter<StationModel> {
    //Reference: https://www.youtube.com/watch?v=RHqGiWluAzU&ab_channel=Foxandroid
    public PetrolStationAdapter(@NonNull Context context, ArrayList<StationModel> stationArrayList) {
        super(context, R.layout.activity_my_petrol_station_list,stationArrayList);


    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        StationModel model = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_my_petrol_station_list,parent,false);
        }


        TextView stationName = convertView.findViewById(R.id.mystationName);
        TextView status = convertView.findViewById(R.id.mystationStatus);

        stationName.setText(model.getStation_name());
        if(model.isStatus() == true){
            status.setText("OPEN");
            status.setTextColor(Color.parseColor("#33cc5a"));
        }else {
            status.setText("CLOSED");
            status.setTextColor(Color.parseColor("#800811"));
        }
        return convertView;
//        return super.getView(position, convertView, parent);
    }
}