package com.example.fuelizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class Update_Next_Arrival_Stock extends AppCompatActivity {

    private EditText dateT,timeT;
    private Button update;
    private Editable date,time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_next_arrival_stock);

       String fuelTypeItemId = getIntent().getStringExtra("itemId");

       String ItemType = getIntent().getStringExtra("itemType").toUpperCase(Locale.ROOT);

        timeT = (EditText) findViewById(R.id.ed_updD_h_date);
        dateT = (EditText) findViewById(R.id.h_udateT);
         time = timeT.getText();
         date = dateT.getText();

        TextView fuelType = (TextView) findViewById(R.id.h_fuel_type_arrivalupdate);
        fuelType.setText(ItemType);

        update = findViewById(R.id.h_updatearrivals);
        String toUpdate = date +" "+time;
        System.out.println(toUpdate);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyPetrolStationDataService dataService = new MyPetrolStationDataService(Update_Next_Arrival_Stock.this);
                dataService.updateFuelArrivals(new MyPetrolStationDataService.VolleyResponseListenerUpdateArrivalDate() {
                    @Override
                    public void onResponse(String msg) {
                        System.out.println("done updating");
                    }

                    @Override
                    public void onError(String message) {

                    }
                },fuelTypeItemId,"2022-03-31 8:56");

//                Intent intent = new Intent(ViewMyPetrolStation.this, Update_Next_Arrival_Stock.class);
//                intent.putExtra("itemId", petrolItemId);
//                intent.putExtra("itemType","petrol");
//                startActivity(intent);
            }
        });

    }

}