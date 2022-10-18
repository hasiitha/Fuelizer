package com.example.fuelizer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

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

//    ActivityMainBinding binding;
    ActivityCustomerListViewBinding binding;
    ArrayList<String> stationList;
    ArrayAdapter<String> stationAdaptor;
    Handler handler = new Handler();
    ProgressBar progressBar;
    ProgressDialog progressDialog;
//    ListView stationListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerListViewBinding.inflate(getLayoutInflater());
//        setContentView(R.layout.activity_customer_list_view);
//        stationListView = findViewById(R.id.stationListView);
        setContentView(binding.getRoot());
        
        initializeStationList();
        new fetchStations().start();
    }

    private void initializeStationList() {
        stationList = new ArrayList<>();
        String Requests[] = {"Paint Job","House Paint","Door Color"};
        stationAdaptor = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,stationList);
        binding.stationListView.setAdapter(stationAdaptor);
    }

    class fetchStations extends Thread{
        String data = "";


        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
//                    progressBar = new ProgressBar(CustomerListView.this);
//                    progressBar.setVisibility(View.VISIBLE);
                    progressDialog = new ProgressDialog(CustomerListView.this);
                    progressDialog.setMessage("Loading Data");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                }
            });
            try {
                URL url = new URL("https://dummyjson.com/users");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while((line = bufferedReader.readLine()) != null){
                    data = data + line;
//                    System.out.println("DATA SET"+data);
                }

                if(!data.isEmpty()){
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray stations = jsonObject.getJSONArray("users");
                    stationList.clear();
                    for (int i = 0; i < stations.length(); i++) {
                        JSONObject names = stations.getJSONObject(i);
                        String name = names.getString("firstName");
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
//                    progressBar = new ProgressBar(CustomerListView.this);
//                    progressBar.setVisibility(View.GONE);
//                    stationAdaptor.notifyDataSetChanged();
                    if(progressDialog.isShowing()){
                        progressDialog.dismiss();
                        stationAdaptor.notifyDataSetChanged();
                    }
                }
            });
        }
    }
}