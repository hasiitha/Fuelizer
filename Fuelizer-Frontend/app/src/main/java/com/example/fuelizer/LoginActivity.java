package com.example.fuelizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private TextView txtView_RegisterHere;
    private String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userType = getIntent().getStringExtra("userType");
        System.out.println(userType);

        txtView_RegisterHere = findViewById(R.id.txtView_RegisterHere);


        txtView_RegisterHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (userType){
                    case "vehicleOwner":
//                        Toast.makeText(LoginActivity.this, "vehicle", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, VehicalOwnerRegisterActivity.class);
                        startActivity(intent);
                        break;
                    case "stationOwner":
//                        Toast.makeText(LoginActivity.this, "station", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(LoginActivity.this, StationOwnerRegisterActivity.class);
                        startActivity(intent1);
                        break;
                    default:
                        Toast.makeText(LoginActivity.this, "error", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });

    }
}