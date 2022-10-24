package com.example.fuelizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class CustomerListView extends AppCompatActivity {

    Button showListBtn;
    ListView stationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list_view);

        showListBtn = findViewById(R.id.fetchStationBtn);
        stationList = findViewById(R.id.stationListView);

        showListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StationDataService stationDataService = new StationDataService(CustomerListView.this);
                stationDataService.getAllStations(new StationDataService.VolleyResponseListener() {
                    @Override
                    public void onResponse(ArrayList<StationModel> stationModel) {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(CustomerListView.this, android.R.layout.simple_list_item_1,stationModel);
//                        CustomerListAdapter listAdapter = new CustomerListAdapter(CustomerListView.this,stationModel);
                        stationList.setAdapter(arrayAdapter);
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(CustomerListView.this,message , Toast.LENGTH_LONG).show();
                    }
                });
            }

        });
    }

//
}