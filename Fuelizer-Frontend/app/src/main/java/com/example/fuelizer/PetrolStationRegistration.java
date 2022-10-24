package com.example.fuelizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PetrolStationRegistration extends AppCompatActivity {

    Button btn_AddStation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petrol_station_registration);
        btn_AddStation = findViewById(R.id.btn_AddStation);

        btn_AddStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PetrolStationRegistration.this, MyPetrolStationList.class);
                intent.putExtra("stationId", "id");
                startActivity(intent);
            }
        });
    }
}