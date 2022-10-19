package com.example.fuelizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StationOwnerRegisterActivity extends AppCompatActivity {

    private TextView txtView_login_registeredUser_SO;
    private EditText editTxt_username, editTxt_password, editTxt_nic, editTxt_mobile, editTxt_email;
    private Button btn_register;
    private String userType;
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_owner_register);

        userType = getIntent().getStringExtra("userType");

        DB = new DBHelper(this);

        editTxt_username = findViewById(R.id.editTxt_Regis_SO_username);
        editTxt_password = findViewById(R.id.editTxt_Regis_SO_password);
        editTxt_nic = findViewById(R.id.editTxt_Regis_SO_nic);
        editTxt_mobile = findViewById(R.id.editTxt_Regis_SO_mobile);
        editTxt_email = findViewById(R.id.editTxt_Regis_SO_email);

        txtView_login_registeredUser_SO = findViewById(R.id.txtView_RegisteredUser_SO_signIn);
        btn_register = findViewById(R.id.btn_stationOwner_register);

        // registering the station owner
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = editTxt_username.getText().toString();
                String pw = editTxt_password.getText().toString();
                String nic = editTxt_nic.getText().toString();
                String mobile = editTxt_mobile.getText().toString();
                String email = editTxt_email.getText().toString();

                if(uname.equals("") || pw.equals("") || nic.equals("") || mobile.equals("") || email.equals("")){ //checking for empty fields
                    Toast.makeText(StationOwnerRegisterActivity.this, "Fill all the fields !", Toast.LENGTH_SHORT).show();
                } else {
//                    Toast.makeText(StationOwnerRegisterActivity.this, "Good", Toast.LENGTH_SHORT).show();
                    Boolean checkUser = DB.checkusername(uname);

                    if(checkUser == false){
                        Boolean insertSuccess = DB.insertData(uname, pw, userType);
                        if(insertSuccess == true){
                            Toast.makeText(StationOwnerRegisterActivity.this, "User Register Successfully !", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(StationOwnerRegisterActivity.this, PetrolStationRegistration.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(StationOwnerRegisterActivity.this, "Something went wrong !", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(StationOwnerRegisterActivity.this, "User already registered !", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        // redirect to login page
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