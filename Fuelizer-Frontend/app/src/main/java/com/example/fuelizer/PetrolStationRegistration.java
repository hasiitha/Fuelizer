package com.example.fuelizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

/*Register petrol stations don here*/
public class PetrolStationRegistration extends AppCompatActivity {
    private RequestQueue requestQueue; EditText station,locationN;
    Button btn_AddStation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petrol_station_registration);
        btn_AddStation = findViewById(R.id.btn_AddStation);

        station = findViewById(R.id.txtE_stationName);
        locationN = findViewById(R.id.txtE_location);





        btn_AddStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stationName,location;
                String status;
                stationName = station.getText().toString();
                location =  locationN.getText().toString();
                status = "true";

                if(location.equals("") || stationName.equals("")){

                    Toast.makeText(PetrolStationRegistration.this, "Enter all the fields !", Toast.LENGTH_SHORT).show();
                }else {


//                        postDataToDB(stationName,location,status);
//                    Toast.makeText(PetrolStationRegistration.this, "Station  Registered Successfully !", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PetrolStationRegistration.this, MypetrolstationlistviewActivity.class);
                intent.putExtra("stationId", "id");
                startActivity(intent);

                }

                //nav
//
            }
        });
    }


    //db
    private void postDataToDB(String stationName, String location,String status){
        // url to post the user data
        String url = "http://192.168.1.15:8081/api/FuelStation";
//        vehicleOwnerPB.setVisibility(View.VISIBLE);

        HashMap<String, String> params = new HashMap<String, String>();

        params.put("stationName", stationName);
        params.put("location", location);
        params.put("status", status);
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

        requestQueue = Volley.newRequestQueue(PetrolStationRegistration.this);
        requestQueue.add(req);

    }

}