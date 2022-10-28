package com.example.fuelizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;
/*Methods for update station storage*/
public class UpdateStationStorage extends AppCompatActivity {
    String stationName,stationId,stationStatus,stationLocation;
    EditText stock_Update,capacity_Update;
    Button update_Stock,update_Capacity,finish_Stock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_station_storage);

        String fuelTypeItemId = getIntent().getStringExtra("itemId");
        String ItemType = getIntent().getStringExtra("itemType").toUpperCase(Locale.ROOT);
        TextView fuelType = (TextView) findViewById(R.id.h_fueltype_title);
        fuelType.setText(ItemType);

        stationName = getIntent().getStringExtra("name");
        stationId = getIntent().getStringExtra("ID");
        stationStatus = getIntent().getStringExtra("status");
        stationLocation = getIntent().getStringExtra("location");

        stock_Update = (EditText) findViewById(R.id.h_editnewAmountStorage);
        capacity_Update = (EditText) findViewById(R.id.h_edittextnewCapacitystorage);

        update_Stock =  (Button) findViewById(R.id.h_btnUpdateStockStorage);
        update_Capacity = (Button) findViewById(R.id.h_btn_update_capacity_storage);
        finish_Stock= (Button) findViewById(R.id.h_btn_finishstock_storage);

        update_Stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = stock_Update.getText().toString();
                MyPetrolStationDataService dataService = new MyPetrolStationDataService(UpdateStationStorage.this);
                dataService.updateStocks(new MyPetrolStationDataService.VolleyResponseListenerUpdateStocks() {
                    @Override
                    public void onResponse(String msg) {

                        System.out.println("done updating");
                        Intent intent = new Intent(UpdateStationStorage.this, ViewMyPetrolStation.class);
                        intent.putExtra("name",stationName);
                        intent.putExtra("ID",stationId);
                        intent.putExtra("status",stationStatus);
                        intent.putExtra("location",stationLocation);
                        startActivity(intent);
                    }


                    //                intent.putExtra("itemId", petrolItemId);
//                intent.putExtra("itemType","petrol");
//                startActivity(intent);
                    @Override
                    public void onError(String message) {

                    }
                },fuelTypeItemId,amount);

//
            }
        });

        update_Capacity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = capacity_Update.getText().toString();
                MyPetrolStationDataService dataService = new MyPetrolStationDataService(UpdateStationStorage.this);
                dataService.updateCapacity(new MyPetrolStationDataService.VolleyResponseListenerUpdateCapacity() {
                    @Override
                    public void onResponse(String msg) {
                        System.out.println("done updating");

                        System.out.println("done updating");
                        Intent intent = new Intent(UpdateStationStorage.this, ViewMyPetrolStation.class);
                        intent.putExtra("name",stationName);
                        intent.putExtra("ID",stationId);
                        intent.putExtra("status",stationStatus);
                        intent.putExtra("location",stationLocation);
                        startActivity(intent);
                    }


                    @Override
                    public void onError(String message) {

                    }
                },fuelTypeItemId,amount);

//
            }
        });


        finish_Stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyPetrolStationDataService dataService = new MyPetrolStationDataService(UpdateStationStorage.this);
                dataService.finishStocks(new MyPetrolStationDataService.VolleyResponseListenerFinishStocks() {
                    @Override
                    public void onResponse(String msg) {

                        System.out.println("done updating");
                        Intent intent = new Intent(UpdateStationStorage.this, ViewMyPetrolStation.class);
                        intent.putExtra("name",stationName);
                        intent.putExtra("ID",stationId);
                        intent.putExtra("status",stationStatus);
                        intent.putExtra("location",stationLocation);
                        startActivity(intent);

                    }

                    @Override
                    public void onError(String message) {

                    }
                },fuelTypeItemId);

            }
        });
    }
}