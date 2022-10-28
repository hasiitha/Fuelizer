package com.example.fuelizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

/**/
//public class MyPetrolStationList extends AppCompatActivity {
//    ListView stationList;
//    SearchView searchView;
//    CustomerListAdapter listAdapter;
//    ImageView selectArrow;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_petrol_station_list);
//
//        String   userName = getIntent().getStringExtra("userId");
//        stationList = (ListView) findViewById(R.id.stationListView);
//        searchView = findViewById(R.id.searchbar_input);
//
//        MyPetrolStationDataService stationDataService = new MyPetrolStationDataService(MyPetrolStationList.this);
//        stationDataService.getAllStations(new MyPetrolStationDataService.VolleyResponseListener() {
//            @Override
//            public void onResponse(ArrayList<StationModel> stationModel) {
////                        ArrayAdapter arrayAdapter = new ArrayAdapter(CustomerListView.this, android.R.layout.simple_list_item_1,stationModel);
//                listAdapter = new CustomerListAdapter(getApplicationContext(),stationModel);
//                stationList.setAdapter(listAdapter);
//                stationList.setClickable(true);
//                stationList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        System.out.println("Clicked One:" + i);
//                        Intent intent = new Intent(getApplicationContext(),StationDetailsActivity.class);
//                        intent.putExtra("name", stationModel.get(i).getStation_name());
//                        intent.putExtra("ID",stationModel.get(i).getId());
//                        intent.putExtra("location",stationModel.get(i).getLocation());
//                        startActivity(intent);
//                    }
//                },);
//
//            }
//
//            @Override
//            public void onError(String message) {
//                Toast.makeText(MyPetrolStationList.this,message , Toast.LENGTH_LONG).show();
//            }
//        });
//
//
//        selectArrow = findViewById(R.id.selectArrow);
//
//        selectArrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MyPetrolStationList.this, ViewMyPetrolStation.class);
//                intent.putExtra("stationId", "id");
//                startActivity(intent);
//            }
//        });
//    }
//}