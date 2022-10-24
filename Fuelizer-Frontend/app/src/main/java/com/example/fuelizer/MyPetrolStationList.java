package com.example.fuelizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MyPetrolStationList extends AppCompatActivity {

    ImageView selectArrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_petrol_station_list);

        selectArrow = findViewById(R.id.selectArrow);

        selectArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPetrolStationList.this, ViewMyPetrolStation.class);
                intent.putExtra("stationId", "id");
                startActivity(intent);
            }
        });
    }
}