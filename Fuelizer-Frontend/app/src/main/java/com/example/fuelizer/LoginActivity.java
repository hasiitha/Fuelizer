package com.example.fuelizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/*
*   this class is to login to the system
* */

public class LoginActivity extends AppCompatActivity {

    // initializing elements
    private TextView txtView_RegisterHere;
    private String userType;
    private Button btn_signIn;
    private EditText editTxt_username, editTxt_password;
    private DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userType = getIntent().getStringExtra("userType");
        System.out.println(userType);

        DB = new DBHelper(this);

        txtView_RegisterHere = findViewById(R.id.txtView_RegisterHere);
        btn_signIn = findViewById(R.id.btn_sign_in);
        editTxt_username = (EditText) findViewById(R.id.editTxt_username_login);
        editTxt_password = (EditText) findViewById(R.id.editTxt_password_login);


        // sign in button click
        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uname = editTxt_username.getText().toString();
                String pw = editTxt_password.getText().toString();

                if(uname.equals("") || pw.equals("")){
                    Toast.makeText(LoginActivity.this, "Enter username and password", Toast.LENGTH_SHORT).show();

                } else {

                    Boolean validStatus = DB.checkusernamepassword(uname, pw);
                    if(validStatus == true){

                        DB.checkUserType(uname);

                        if(userType.equals(DB.USERTYPE)){
                            // API call for user login  (check usertype before call)
                            if(userType.equals("vehicleOwner")){
                                //adding userName for global variable
                                GlobalVariables.userName = uname;
                                Intent intent = new Intent(LoginActivity.this, CustomerListView.class);
                                intent.putExtra("username", uname);
                                startActivity(intent);
                            } else{
                                GlobalVariables.userName = uname;
                                Intent intent = new Intent(LoginActivity.this, OwnerMenue.class);
                                intent.putExtra("username", uname);
                                startActivity(intent);
                            }
                            Toast.makeText(LoginActivity.this, "Valid user !", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Incorrect user type !", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(LoginActivity.this, "Invalid user !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Register button click
        txtView_RegisterHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // navigating to vehicle or station owner registrations
                switch (userType){
                    case "vehicleOwner":
                        Intent intent = new Intent(LoginActivity.this, VehicalOwnerRegisterActivity.class);
                        intent.putExtra("userType", "vehicleOwner");
                        startActivity(intent);
                        break;
                    case "stationOwner":
                        Intent intent1 = new Intent(LoginActivity.this, StationOwnerRegisterActivity.class);
                        intent1.putExtra("userType", "stationOwner");
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