package com.example.fuelizer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomerListAdapter extends ArrayAdapter<StationModel> {
    public CustomerListAdapter(@NonNull Context context, ArrayList<StationModel> stationArrayList) {
        super(context, R.layout.station_list_item,stationArrayList);


    }

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
        }else {
            status.setText("CLOSED");
        }
        return super.getView(position, convertView, parent);
    }
}
