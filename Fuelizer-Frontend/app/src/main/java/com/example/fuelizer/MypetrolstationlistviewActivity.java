package com.example.fuelizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

/*Petrol station List Related Class
* */
public class MypetrolstationlistviewActivity extends AppCompatActivity {
    ListView stationList;
    SearchView searchView;
    PetrolStationAdapter listAdapter;
    String   ownerId;
    String status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypetrolstationlistview);

        stationList = (ListView) findViewById(R.id.stationListView);
        searchView = findViewById(R.id.searchbar_input);

        //getting data from Intent
         ownerId  = getIntent().getStringExtra("userId");


         //Fetching data of My Petrol Station
        GlobalVariables.userId = ownerId;
        MyPetrolStationDataService stationDataService = new MyPetrolStationDataService(MypetrolstationlistviewActivity.this);
        stationDataService.getAllStations(new MyPetrolStationDataService.VolleyResponseListener() {
            @Override
            public void onResponse(ArrayList<StationModel> stationModel) {
                listAdapter = new PetrolStationAdapter(getApplicationContext(),stationModel);
                stationList.setAdapter(listAdapter);
                stationList.setClickable(true);
                stationList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                    //Select my Petrol Station
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        System.out.println("Clicked One:" + i);
                        if(stationModel.get(i).isStatus()){
                            status ="true";
                        }else{
                            status ="false";
                        }
                        Intent intent = new Intent(getApplicationContext(),ViewMyPetrolStation.class);
                        intent.putExtra("name", stationModel.get(i).getStation_name());
                        intent.putExtra("ID",stationModel.get(i).getId());
                        intent.putExtra("location",stationModel.get(i).getLocation());
                        intent.putExtra("status",status);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onError(String message) {
                Toast.makeText(MypetrolstationlistviewActivity.this,message , Toast.LENGTH_LONG).show();
            }
        },GlobalVariables.userId);

    }
}