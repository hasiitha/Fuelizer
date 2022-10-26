package com.example.fuelizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

public class UpdateStationStorage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_station_storage);

        String fuelTypeItemId = getIntent().getStringExtra("itemId");

        String ItemType = getIntent().getStringExtra("itemType").toUpperCase(Locale.ROOT);

        TextView fuelType = (TextView) findViewById(R.id.h_fueltype_title);
        fuelType.setText(ItemType);
       // h_fuelTypeUpdate_title
    }
}