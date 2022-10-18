package com.example.fuelizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StationOwnerRegisterActivity extends AppCompatActivity {

    private TextView txtView_login_registeredUser_SO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_owner_register);

        txtView_login_registeredUser_SO = findViewById(R.id.txtView_RegisteredUser_SO_signIn);

        txtView_login_registeredUser_SO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StationOwnerRegisterActivity.this, LoginActivity.class);
                intent.putExtra("userType", "stationOwner");
                startActivity(intent);
            }
        });

    }
}