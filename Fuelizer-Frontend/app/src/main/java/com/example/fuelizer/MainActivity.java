package com.example.fuelizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
* this class is to choose the user category type
* */


public class MainActivity extends AppCompatActivity {

    private Button btn_v_owner, btn_s_owner ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_v_owner=findViewById(R.id.btn_vehical_owner);
        btn_s_owner = findViewById(R.id.btn_station_owner);
        // button click for vehicle owner
        btn_v_owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra("userType", "vehicleOwner");
                startActivity(intent);
            }
        });

        // button click for station owner
        btn_s_owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra("userType", "stationOwner");
                startActivity(intent);
            }
        });



    }
}