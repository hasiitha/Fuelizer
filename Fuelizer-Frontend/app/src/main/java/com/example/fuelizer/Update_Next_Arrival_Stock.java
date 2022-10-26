package com.example.fuelizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

public class Update_Next_Arrival_Stock extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_next_arrival_stock);

       String ItemId = getIntent().getStringExtra("itemId");

       String ItemType = getIntent().getStringExtra("itemType").toUpperCase(Locale.ROOT);


        TextView fuelType = (TextView) findViewById(R.id.h_fuel_type_arrivalupdate);
        fuelType.setText(ItemType);

    }
}