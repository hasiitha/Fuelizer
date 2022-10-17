package com.example.fuelizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

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

    ActivityMainBinding binding;
    ArrayList<String> stationList;
    ArrayAdapter<String> stationAdaptor;
    Handler handler = new Handler();
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(R.layout.activity_customer_list_view);
        setContentView(binding.getRoot());
        
        initializeStationList();
    }

    private void initializeStationList() {
        stationList = new ArrayList<>();
        stationAdaptor = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,stationList);
//        binding.station
    }

    class fetchStations extends Thread{
        String data = "";


        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    progressBar = new ProgressBar(CustomerListView.this);
                    progressBar.setVisibility(View.VISIBLE);
                }
            });
            try {
                URL url = new URL("http://localhost:8080/api/Stations");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while((line= bufferedReader.readLine()) != null){
                    data = data+line;
                }

                if(!data.isEmpty()){
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray stations = jsonObject.getJSONArray("name");

                    for (int i = 0; i < stations.length(); i++) {
                        JSONObject names = stations.getJSONObject(i);
                        String name = names.getString("name");
                        stationList.add(name);
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            handler.post(new Runnable() {
                @Override
                public void run() {
                    progressBar = new ProgressBar(CustomerListView.this);
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
    }
}