package com.example.fuelizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AlertDialogLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
/**/
public class Update_Next_Arrival_Stock extends AppCompatActivity {

    private EditText dateT,timeT;
    private Button update;
    String stationName,stationId,stationStatus,stationLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_next_arrival_stock);

       String fuelTypeItemId = getIntent().getStringExtra("itemId");

       String ItemType = getIntent().getStringExtra("itemType").toUpperCase(Locale.ROOT);

        dateT = (EditText) findViewById(R.id.ed_updD_h_date);
        timeT= (EditText) findViewById(R.id.h_udateT);


        TextView fuelType = (TextView) findViewById(R.id.h_fuel_type_arrivalupdate);
        fuelType.setText(ItemType);

        update = findViewById(R.id.h_updatearrivals);

        stationName = getIntent().getStringExtra("name");
        stationId = getIntent().getStringExtra("ID");
        stationStatus = getIntent().getStringExtra("status");
        stationLocation = getIntent().getStringExtra("location");

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = timeT.getText().toString();
                String date= dateT.getText().toString();
                String toUpdate = date +" "+time;
                System.out.println(toUpdate);
                MyPetrolStationDataService dataService = new MyPetrolStationDataService(Update_Next_Arrival_Stock.this);
                dataService.updateFuelArrivals(new MyPetrolStationDataService.VolleyResponseListenerUpdateArrivalDate() {
                    @Override
                    public void onResponse(String msg)
                    {
                        System.out.println("done updating");
                        Intent intent = new Intent(Update_Next_Arrival_Stock.this, ViewMyPetrolStation.class);
                        intent.putExtra("name",stationName);
                        intent.putExtra("ID",stationId);
                        intent.putExtra("status",stationStatus);
                        intent.putExtra("location",stationLocation);
                        startActivity(intent);


                    }

                    @Override
                    public void onError(String message) {

                    }
                },fuelTypeItemId,toUpdate);

//
            }
        });

    }

}