package com.example.fuelizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

/*
IT18014396
 displaying station list view and search part implemented here
 */
public class CustomerListView extends AppCompatActivity {

    ListView stationList;
    SearchView searchView;
    CustomerListAdapter listAdapter;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list_view);

        userName = getIntent().getStringExtra("username");

        stationList = (ListView) findViewById(R.id.stationListView);
        searchView = findViewById(R.id.searchbar_input);

        //creation of station service object
        StationDataService stationDataService = new StationDataService(CustomerListView.this);

        //calling customer user details service
        stationDataService.getUserDetails(new StationDataService.UserDetailsResponseListener() {
            @Override
            public void onResponse(CustomerModel object) {
//                Toast.makeText(CustomerListView.this, object.getFuelType(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String message) {
                Toast.makeText(CustomerListView.this, message, Toast.LENGTH_LONG).show();
            }
        });

        //calling getting all stations service
        stationDataService.getAllStations(new StationDataService.VolleyResponseListener() {
            @Override
            public void onResponse(ArrayList<StationModel> stationModel) {
//              ArrayAdapter arrayAdapter = new ArrayAdapter(CustomerListView.this, android.R.layout.simple_list_item_1,stationModel);
                listAdapter = new CustomerListAdapter(getApplicationContext(),stationModel);
                stationList.setAdapter(listAdapter);
                stationList.setClickable(true);
                stationList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            System.out.println("Clicked One:" + i);
                                Intent intent = new Intent(getApplicationContext(),StationDetailsActivity.class);
                                intent.putExtra("name", stationModel.get(i).getStation_name());
                                intent.putExtra("ID",stationModel.get(i).getId());
                                GlobalVariables.selectedStation = stationModel.get(i).getId();
                                startActivity(intent);
                    }
                });

            }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(CustomerListView.this,message , Toast.LENGTH_LONG).show();
                    }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                listAdapter.getFilter().filter(s);
                return false;
            }
        });
    }

//
}