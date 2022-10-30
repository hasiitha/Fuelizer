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

      String   userName = getIntent().getStringExtra("username");


        UserDataServices dataService = new UserDataServices(OwnerMenue.this);
        dataService.GetUserByID(new UserDataServices.VolleyResponseListnergetOwnerByUserId() {
            @Override
            public void onResponse(String userId) {


                regbtn.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                    if(userId != null){
                        Intent intent = new Intent(OwnerMenue.this, PetrolStationRegistration.class);
                        intent.putExtra("userId", userId);
                        intent.putExtra("username", userName);
                        startActivity(intent);
                    }

                    }
                });

                viewbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(userId != null){
                        Intent intent = new Intent(OwnerMenue.this, MypetrolstationlistviewActivity.class);
                            intent.putExtra("userId", userId);
                            intent.putExtra("userName", userName);
                        startActivity(intent);
                        }
                    }
                });
            }

            //
            @Override
            public void onError(String message) {

            }
        },userName);


        //





    }
}