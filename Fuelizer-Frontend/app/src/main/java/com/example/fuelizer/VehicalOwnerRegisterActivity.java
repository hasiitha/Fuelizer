package com.example.fuelizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VehicalOwnerRegisterActivity extends AppCompatActivity {

    private TextView txtView_login_registeredUser;
    private EditText editTxt_username, editTxt_password, editTxt_nic, editTxt_mobile, editTxt_vehicleType, editTxt_fuelType, editTxt_vehicleNumber, editText_chasisNumber;
    private Button btn_register;
//    private ProgressBar vehicleOwnerPB;
    private String userType;
    private DBHelper DB;
    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehical_owner_register);

        txtView_login_registeredUser = findViewById(R.id.txtView_RegisteredUser_signIn);

        userType = getIntent().getStringExtra("userType");

        DB = new DBHelper(this);

        editTxt_username = findViewById(R.id.editTxt_Regis_username);
        editTxt_password = findViewById(R.id.editTxt_Regis_password);
        editTxt_nic = findViewById(R.id.editTxt_Regis_nic);
        editTxt_mobile = findViewById(R.id.editTxt_Regis_mobile);
        editTxt_vehicleType = findViewById(R.id.editTxt_Regis_vehicleType);
        editTxt_fuelType = findViewById(R.id.editTxt_Regis_fuelType);
        editTxt_vehicleNumber = findViewById(R.id.editTxt_Regis_vehicleNumber);
        editText_chasisNumber = findViewById(R.id.editTxt_Regis_chasisNumber);
//        vehicleOwnerPB = findViewById(R.id.vehicle_owner_PB);

        btn_register = findViewById(R.id.btn_vehicleOwner_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname, pw, nic, mobile, vType, fType, vNumber, chasisNumber;
                uname = editTxt_username.getText().toString();
                pw = editTxt_password.getText().toString();
                nic = editTxt_nic.getText().toString();
                mobile = editTxt_mobile.getText().toString();
                vType = editTxt_vehicleType.getText().toString();
                fType = editTxt_fuelType.getText().toString();
                vNumber = editTxt_vehicleNumber.getText().toString();
                chasisNumber = editText_chasisNumber.getText().toString();

                if(uname.equals("") || pw.equals("") || nic.equals("") || mobile.equals("") || vType.equals("") || fType.equals("") || vNumber.equals("") || chasisNumber.equals("")){

                    Toast.makeText(VehicalOwnerRegisterActivity.this, "Enter all the fields !", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkUser = DB.checkusername(uname);
                    if(checkUser == false){
                        postDataToDB(uname,nic,mobile,vType,fType,vNumber,chasisNumber);

                        Boolean insertSuccess = DB.insertData(uname, pw, userType);

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

        // redirecting to login page
        txtView_login_registeredUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VehicalOwnerRegisterActivity.this, LoginActivity.class);
                intent.putExtra("userType", "vehicleOwner");
                startActivity(intent);
            }
        });



    }

    private void postDataToDB(String name, String nic, String mobile, String vType, String fType, String vNumber, String chasisNumber ){

        try {

            // url to post the user data
//            String url = "http://192.168.1.11:8081/api/Customer";
            String url = "http://192.168.1.5:8080/api/Customer";


            HashMap<String, String> params = new HashMap<String, String>();

            params.put("userName", name);
            params.put("nic", nic);
            params.put("mobileNumber", mobile);
            params.put("vehicleType", vType);
            params.put("fuelType", fType);
            params.put("vehicleNumber", vNumber);
            params.put("chasisNumber", chasisNumber);

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

            requestQueue = Volley.newRequestQueue(VehicalOwnerRegisterActivity.this);
            requestQueue.add(req);

        }catch (Exception e){

        }



    }

}