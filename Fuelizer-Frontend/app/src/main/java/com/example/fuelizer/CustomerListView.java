package com.example.fuelizer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fuelizer.databinding.ActivityCustomerListViewBinding;
import com.example.fuelizer.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class CustomerListView extends AppCompatActivity {
    
    Button showListBtn;
    ListView stationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list_view);

        showListBtn = findViewById(R.id.fetchStationBtn);
        stationList = findViewById(R.id.stationListView);

        showListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(CustomerListView.this);
                String url = "http://192.168.1.5:8080/api/FuelStation";

// Request a array response from the provided URL.
                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String stationName = "";
                        try {
                            JSONObject stationInfo = response.getJSONObject(0);
                            stationName = stationInfo.getString("stationName");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(CustomerListView.this,stationName , Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CustomerListView.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

// Add the request to the RequestQueue.
                queue.add(request);
            }

        });
    }

//
}