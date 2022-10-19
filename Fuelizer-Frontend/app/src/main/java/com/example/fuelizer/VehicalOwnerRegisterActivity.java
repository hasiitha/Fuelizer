package com.example.fuelizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class VehicalOwnerRegisterActivity extends AppCompatActivity {

    private TextView txtView_login_registeredUser;
    private EditText editTxt_username, editTxt_password, editTxt_nic, editTxt_mobile, editTxt_vehicleType, editTxt_fuelType, editTxt_vehicleNumber;
    private Button btn_register;
    private DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehical_owner_register);

        txtView_login_registeredUser = findViewById(R.id.txtView_RegisteredUser_signIn);

        DB = new DBHelper(this);

        editTxt_username = findViewById(R.id.editTxt_Regis_username);
        editTxt_password = findViewById(R.id.editTxt_Regis_password);
        editTxt_nic = findViewById(R.id.editTxt_Regis_nic);
        editTxt_mobile = findViewById(R.id.editTxt_Regis_mobile);
        editTxt_vehicleType = findViewById(R.id.editTxt_Regis_vehicleType);
        editTxt_fuelType = findViewById(R.id.editTxt_Regis_fuelType);
        editTxt_vehicleNumber = findViewById(R.id.editTxt_Regis_vehicleNumber);

        btn_register = findViewById(R.id.btn_vehicleOwner_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname, pw, nic, mobile, vType, fType, vNumber;
                uname = editTxt_username.getText().toString();
                pw = editTxt_password.getText().toString();
                nic = editTxt_nic.getText().toString();
                mobile = editTxt_mobile.getText().toString();
                vType = editTxt_vehicleType.getText().toString();
                fType = editTxt_fuelType.getText().toString();
                vNumber = editTxt_vehicleNumber.getText().toString();

                if(uname.equals("") || pw.equals("") || nic.equals("") || mobile.equals("") || vType.equals("") || fType.equals("") || vNumber.equals("")){
                    Toast.makeText(VehicalOwnerRegisterActivity.this, "Enter all the fields !", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkUser = DB.checkusername(uname);
                    if(checkUser == false){
                        Boolean insertSuccess = DB.insertData(uname, pw);
                        if(insertSuccess == true){
                            Toast.makeText(VehicalOwnerRegisterActivity.this, "User Register Successfully !", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(VehicalOwnerRegisterActivity.this, LoginActivity.class);
                            intent.putExtra("userType", "vehicleOwner");
                            startActivity(intent);
                        }else {
                            Toast.makeText(VehicalOwnerRegisterActivity.this, "Something went wrong !", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(VehicalOwnerRegisterActivity.this, "User already registered !", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

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