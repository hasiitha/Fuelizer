package com.example.fuelizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class VehicalOwnerRegisterActivity extends AppCompatActivity {

    private TextView txtView_login_registeredUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehical_owner_register);

        txtView_login_registeredUser = findViewById(R.id.txtView_RegisteredUser_signIn);

        txtView_login_registeredUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VehicalOwnerRegisterActivity.this, LoginActivity.class);
                intent.putExtra("userType", "vehicleOwner");
                startActivity(intent);
            }
        });



    }
}