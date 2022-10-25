package com.example.fuelizer;

import static com.example.fuelizer.R.color.green;

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

public class CustomerListAdapter extends ArrayAdapter<StationModel> {
    //Reference: https://www.youtube.com/watch?v=RHqGiWluAzU&ab_channel=Foxandroid
    public CustomerListAdapter(@NonNull Context context, ArrayList<StationModel> stationArrayList) {
        super(context, R.layout.station_list_item,stationArrayList);


    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        StationModel model = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.station_list_item,parent,false);
        }


        TextView stationName = convertView.findViewById(R.id.stationName);
        TextView status = convertView.findViewById(R.id.stationStatus);

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
