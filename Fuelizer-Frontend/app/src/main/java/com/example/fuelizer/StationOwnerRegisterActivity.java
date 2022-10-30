package com.example.fuelizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
/*
*  this class is to register a station owner to the system
* */



public class StationOwnerRegisterActivity extends AppCompatActivity {

    private TextView txtView_login_registeredUser_SO;
    private EditText editTxt_username, editTxt_password, editTxt_nic, editTxt_mobile, editTxt_email;
    private Button btn_register;
    private String userType;
    private DBHelper DB;
    private RequestQueue requestQueue;

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

                    Boolean checkUser = DB.checkusername(uname);

                    if(checkUser == false){
                        postDataToDB(uname, nic, mobile, email);

                        Boolean insertSuccess = DB.insertData(uname, pw, userType);

                        if(insertSuccess == true){
                            Toast.makeText(StationOwnerRegisterActivity.this, "User Register Successfully !", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(StationOwnerRegisterActivity.this, LoginActivity.class);
                            intent.putExtra("userType", "stationOwner");
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

    // implementation to post the station owner data to DB
    private void postDataToDB(String name, String nic, String mobile, String email ){

        try {
            // url to post the user data
            String url = "http://192.168.1.5:8081/api/Supplier";

            HashMap<String, String> params = new HashMap<String, String>();

            params.put("userName", name);
            params.put("nic", nic);
            params.put("mobileNumber", mobile);
            params.put("email", email);

            JsonObjectRequest req = new JsonObjectRequest(url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                VolleyLog.v("Response:%n %s", response.toString(4));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.e("Error: ", error.getMessage());
                }
            });

            requestQueue = Volley.newRequestQueue(StationOwnerRegisterActivity.this);
            requestQueue.add(req);

        }catch (Exception e){

        }

    }

}