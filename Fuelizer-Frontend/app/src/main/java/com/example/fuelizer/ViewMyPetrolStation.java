package com.example.fuelizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
/*
* Class to view the petrol stations and to navigate to the update methods
* */
public class ViewMyPetrolStation extends AppCompatActivity {
    // viewing the petrol station details
    private Button selectStation;
    Button petrol_btn,petrol_btn_updatestock;
    String stationName;
    String stationId;
    String stationStatus;
    String stationLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_petrol_station);



        stationName = getIntent().getStringExtra("name");
        stationId = getIntent().getStringExtra("ID");
        stationStatus = getIntent().getStringExtra("status");
        stationLocation = getIntent().getStringExtra("location");

        TextView textViewToChange = (TextView) findViewById(R.id.st_name);
        textViewToChange.setText(stationName);

        MyPetrolStationDataService stationDataService = new MyPetrolStationDataService(ViewMyPetrolStation.this);
        stationDataService.getstationFuelTypes(new MyPetrolStationDataService.VolleyResponseListenerDet() {
            @Override
            public void onResponse(HashMap<String,FuelTypesData> fuelType) {
//                        ArrayAdapter arrayAdapter = new ArrayAdapter(CustomerListView.this, android.R.layout.simple_list_item_1,stationModel);

                String petrol92remain = (fuelType.get("petrol").getRemainder())+"L/";
                String petrol92capacity = (fuelType.get("petrol").getCapacity());
                String petrol92arrivalDateTime = (fuelType.get("petrol").getArrivalTime());
                //String petrol92arrivalTime = (fuelType.get("petrol").getArriva());


                String petrol95remain = (fuelType.get("petrol95").getRemainder())+"L/";
                String petrol95capacity = (fuelType.get("petrol95").getCapacity());
                String petrol95arrivalDateTime = (fuelType.get("petrol95").getArrivalTime());
                //String petrol95arrivalTime = (fuelType.get("petrol95").getRemainder());



                String dieselRemain = (fuelType.get("diesel").getRemainder())+"L/";;
                String dieselCapacity = (fuelType.get("diesel").getCapacity());
                String dieselArrivalDateTime = (fuelType.get("diesel").getArrivalTime());
                //String dieselArrivalTime = (fuelType.get("diesel").getRemainder());


                String superDieselRemain = ("      "+fuelType.get("superdiesel").getRemainder())+"L/";;
                String superDieselCapacity = (fuelType.get("superdiesel").getCapacity());
                String superDieselArrivalDateTime = (fuelType.get("superdiesel").getArrivalTime());
                //String superDieselArrivalTime = (fuelType.get("superdiesel").getRemainder());





                TextView remainderPetrol92 = (TextView) findViewById(R.id.h_petrol_remain);
                remainderPetrol92.setText(petrol92remain);

                TextView remainderPetrol95 = (TextView) findViewById(R.id.h_petrol95_remain);
                remainderPetrol95.setText(petrol95remain);

                TextView remainderDiesel = (TextView) findViewById(R.id.h_diesel_remain);
                remainderDiesel.setText(dieselRemain);

                TextView remainderSuperDiesel = (TextView) findViewById(R.id.h_superdiesel_remain);
                remainderSuperDiesel .setText(superDieselRemain);

                TextView capacityPetrol92 = (TextView) findViewById(R.id.h_petrol_capacity);
                capacityPetrol92.setText(petrol92capacity);

                TextView capacityPetrol95 = (TextView) findViewById(R.id.h_petrol95_capacity);
                capacityPetrol95.setText(petrol95capacity);

                TextView capacityDiesel = (TextView) findViewById(R.id.h_diesel_capacity);
                capacityDiesel.setText(dieselCapacity);

                TextView capacitySuperDiesel = (TextView) findViewById(R.id.h_superdiesel_capacity);
                capacitySuperDiesel.setText(superDieselCapacity);

                TextView arrivalDatePetrol92 = (TextView) findViewById(R.id.h_petrol_arrive_i);
                arrivalDatePetrol92.setText(petrol92arrivalDateTime);

//                TextView arrivalTimePetrol92 = (TextView) findViewById(R.id.petrolval);
//                arrivalTimePetrol92.setText(petrol92remain);

                TextView arrivalDatePetrol95 = (TextView) findViewById(R.id.h_pertrol95_arrive_i);
                arrivalDatePetrol95.setText(petrol95arrivalDateTime);

//                TextView arrivalTimePetrol95 = (TextView) findViewById(R.id.petrolval);
//                arrivalTimePetrol95.setText(petrol92remain);

                TextView arrivalDateDiesel = (TextView) findViewById(R.id.h_diesel_arrive_i);
                arrivalDateDiesel.setText(dieselArrivalDateTime);

//                TextView arrivalTimeDiesel = (TextView) findViewById(R.id.petrolval);
//                arrivalTimeDiesel.setText(petrol92remain);

                TextView arrivalDateSuperDiesel = (TextView) findViewById(R.id.h_superdiesel_arrive_i);
                arrivalDateSuperDiesel.setText(superDieselArrivalDateTime);

//                TextView arrivalTimeSuperDiesel = (TextView) findViewById(R.id.petrolval);
//                arrivalTimeSuperDiesel.setText(petrol92remain);

//
                petrol_btn = findViewById(R.id.h_btn_nextarrival_patrol);

                String petrolItemId = (fuelType.get("petrol").getId());
                petrol_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ViewMyPetrolStation.this, Update_Next_Arrival_Stock.class);
                        intent.putExtra("itemId", petrolItemId);
                        intent.putExtra("itemType","petrol");
                        startActivity(intent);
                    }
                });
                String petrol95ItemId = (fuelType.get("petrol95").getId());
                petrol_btn_updatestock = findViewById(R.id.h_update_petrol92_vol);

                petrol_btn_updatestock.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ViewMyPetrolStation.this, UpdateStationStorage.class);
                        intent.putExtra("itemId", petrol95ItemId);
                        intent.putExtra("itemType","petrol");
                        startActivity(intent);
                    }
                });


                ;



            }

            @Override
            public void onError(String message) {
                Toast.makeText(ViewMyPetrolStation.this,message , Toast.LENGTH_LONG).show();
            }
        });

    }


}