package com.example.fuelizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/*Dashboard for the station owner*/
public class OwnerMenue extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_menue);

        Button regbtn = findViewById(R.id.h_btn_regstation);
        Button viewbtn = findViewById(R.id.h_btn_viewstation);

      //String   stationId = getIntent().getStringExtra("userId");
        String   stationId="";
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerMenue.this, PetrolStationRegistration.class);

                startActivity(intent);
            }
        });

        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerMenue.this, MypetrolstationlistviewActivity.class);
                intent.putExtra("userId", "stationId");
                startActivity(intent);
            }
        });
    }
}