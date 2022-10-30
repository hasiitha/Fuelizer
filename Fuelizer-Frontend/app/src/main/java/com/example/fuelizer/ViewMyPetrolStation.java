package com.example.fuelizer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
/*
* Class to view the petrol stations and to navigate to the update methods
* */
public class ViewMyPetrolStation extends AppCompatActivity {
    // viewing the petrol station details
    private Button selectStation;
    Button petrol_btn,petrol_btn_updatestock,btn_update95,btn_updateDiesel,
            btn_updateSuperDiesel,btn_arrival95,btn_arrivalDiesel,btn_arrival_super_diesel,btn_update_Status;
    String stationName;
    String stationId;
    String stationStatus;
    String stationLocation;
    String statusO;
    ImageView lgBTN1;
    @SuppressLint("WrongViewCast")
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
        lgBTN1 = findViewById(R.id.lgout1);




        TextView textViewToChangeStatus = (TextView) findViewById(R.id.h_statusView);
        textViewToChangeStatus.setText(GlobalVariables.stationStatus);


        MyPetrolStationDataService stationDataService = new MyPetrolStationDataService(ViewMyPetrolStation.this);
        stationDataService.getstationFuelTypes(new MyPetrolStationDataService.VolleyResponseListenerDet() {
            @Override
            public void onResponse(HashMap<String,FuelTypesData> fuelType) {

                //Fetching details of the petrol station
                System.out.println((fuelType.get("diesel").getRemainder())+"remainder");
                String petrol92remain = (fuelType.get("petrol").getRemainder());
                String petrol92capacity = (fuelType.get("petrol").getCapacity());
                String petrol92arrivalDateTime = (fuelType.get("petrol").getArrivalTime());



                String petrol95remain = (fuelType.get("petrol95").getRemainder());
                String petrol95capacity = (fuelType.get("petrol95").getCapacity());
                String petrol95arrivalDateTime = (fuelType.get("petrol95").getArrivalTime());




                String dieselRemain = (fuelType.get("diesel").getRemainder());;
                String dieselCapacity = (fuelType.get("diesel").getCapacity());
                String dieselArrivalDateTime = (fuelType.get("diesel").getArrivalTime());



                String superDieselRemain = (fuelType.get("superdiesel").getRemainder());;
                String superDieselCapacity = (fuelType.get("superdiesel").getCapacity());
                String superDieselArrivalDateTime = (fuelType.get("superdiesel").getArrivalTime());



//Setting Views in Station List

                TextView remainderPetrol92 = (TextView) findViewById(R.id.h_petrol_remain);
                remainderPetrol92.setText(petrol92remain+"L/");

                TextView remainderPetrol95 = (TextView) findViewById(R.id.h_petrol95_remain);
                remainderPetrol95.setText(petrol95remain+"L/");

                TextView remainderDiesel = (TextView) findViewById(R.id.h_diesel_remain);
                remainderDiesel.setText(dieselRemain+"L/");

                TextView remainderSuperDiesel = (TextView) findViewById(R.id.h_superdiesel_remain);
                remainderSuperDiesel .setText("     "+ superDieselRemain+"L/");

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


                TextView arrivalDatePetrol95 = (TextView) findViewById(R.id.h_pertrol95_arrive_i);
                arrivalDatePetrol95.setText(petrol95arrivalDateTime);



                TextView arrivalDateDiesel = (TextView) findViewById(R.id.h_diesel_arrive_i);
                arrivalDateDiesel.setText(dieselArrivalDateTime);



                TextView arrivalDateSuperDiesel = (TextView) findViewById(R.id.h_superdiesel_arrive_i);
                arrivalDateSuperDiesel.setText(superDieselArrivalDateTime);


                petrol_btn = findViewById(R.id.h_btn_nextarrival_patrol);

                String petrolItemId = (fuelType.get("petrol").getId());

                //Update Petrol
                petrol_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ViewMyPetrolStation.this, Update_Next_Arrival_Stock.class);
                        intent.putExtra("itemId", petrolItemId);
                        intent.putExtra("itemType","petrol");

                        intent.putExtra("capacity",petrol92capacity);
                        intent.putExtra("remain",petrol92remain);

                        intent.putExtra("name",stationName);
                        intent.putExtra("ID",stationId);
                        intent.putExtra("status",stationStatus);
                        intent.putExtra("location",stationLocation);
                        startActivity(intent);
                    }
                });

                //Update Stocks
                petrol_btn_updatestock = findViewById(R.id.h_update_petrol92_vol);

                petrol_btn_updatestock.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       System.out.println("clicked95");
                        Intent intent = new Intent(ViewMyPetrolStation.this, UpdateStationStorage.class);
                        intent.putExtra("itemId", petrolItemId);
                        intent.putExtra("itemType","petrol");
                        intent.putExtra("name",stationName);
                        intent.putExtra("ID",stationId);
                        intent.putExtra("status",stationStatus);
                        intent.putExtra("location",stationLocation);

                        intent.putExtra("capacity",petrol92capacity);
                        intent.putExtra("remain",petrol92remain);

                        startActivity(intent);

                    }
                });

//updating stocks and capacities
                String petrol95ItemId = (fuelType.get("petrol95").getId());
                String dieselItemId = (fuelType.get("diesel").getId());
                String superDieselItemId = (fuelType.get("superdiesel").getId());



                btn_update95 = findViewById(R.id.h_update_petrol95_vol);
                btn_updateDiesel = findViewById(R.id.h_update_diesel_vol);
                btn_updateSuperDiesel= findViewById(R.id.h_update_superdiesel_vol);
                btn_arrival95= findViewById(R.id.h_btn_nextarrival_petrol95);
                btn_arrivalDiesel= findViewById(R.id.h_btn_nextarrival_diesel);
                btn_arrival_super_diesel= findViewById(R.id.h_btn_nextarrival_superdiesel);


                btn_update_Status = findViewById(R.id.h_updateStatus);

                btn_update95.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ViewMyPetrolStation.this, UpdateStationStorage.class);
                        intent.putExtra("itemId", petrol95ItemId);
                        intent.putExtra("itemType","petrol95");

                        intent.putExtra("capacity",petrol95capacity);
                        intent.putExtra("remain",petrol95remain);

                        intent.putExtra("name",stationName);
                        intent.putExtra("ID",stationId);
                        intent.putExtra("status",stationStatus);
                        intent.putExtra("location",stationLocation);
                        startActivity(intent);
                    }
                });


                btn_updateDiesel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ViewMyPetrolStation.this, UpdateStationStorage.class);
                        intent.putExtra("itemId", dieselItemId);
                        intent.putExtra("itemType","diesel");
                        intent.putExtra("name",stationName);
                        intent.putExtra("ID",stationId);
                        intent.putExtra("status",stationStatus);
                        intent.putExtra("location",stationLocation);

                        intent.putExtra("capacity",dieselCapacity);
                        intent.putExtra("remain",dieselRemain);

                        startActivity(intent);
                    }
                });

                btn_updateSuperDiesel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ViewMyPetrolStation.this, UpdateStationStorage.class);
                        intent.putExtra("itemId", superDieselItemId);
                        intent.putExtra("itemType","superdiesel");
                        intent.putExtra("name",stationName);
                        intent.putExtra("ID",stationId);
                        intent.putExtra("status",stationStatus);
                        intent.putExtra("location",stationLocation);

                        intent.putExtra("capacity",superDieselCapacity);
                        intent.putExtra("remain",superDieselRemain);
                        startActivity(intent);
                    }
                });

                btn_arrival95.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ViewMyPetrolStation.this, Update_Next_Arrival_Stock.class);
                        intent.putExtra("itemId", petrol95ItemId);
                        intent.putExtra("itemType","petrol95");
                        intent.putExtra("name",stationName);
                        intent.putExtra("ID",stationId);
                        intent.putExtra("status",stationStatus);
                        intent.putExtra("location",stationLocation);

                        startActivity(intent);
                    }
                });

                btn_arrivalDiesel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ViewMyPetrolStation.this, Update_Next_Arrival_Stock.class);
                        intent.putExtra("itemId", dieselItemId);
                        intent.putExtra("itemType","diesel");
                        intent.putExtra("name",stationName);
                        intent.putExtra("ID",stationId);
                        intent.putExtra("status",stationStatus);
                        intent.putExtra("location",stationLocation);
                        startActivity(intent);
                    }
                });


                btn_arrival_super_diesel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ViewMyPetrolStation.this, Update_Next_Arrival_Stock.class);
                        intent.putExtra("itemId", superDieselItemId);
                        intent.putExtra("itemType","superdiesel");
                        intent.putExtra("name",stationName);
                        intent.putExtra("ID",stationId);
                        intent.putExtra("status",stationStatus);
                        intent.putExtra("location",stationLocation);
                        startActivity(intent);
                    }
                });

                btn_update_Status.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ViewMyPetrolStation.this, Update_Next_Arrival_Stock.class);
                        intent.putExtra("itemId", superDieselItemId);
                        intent.putExtra("itemType","superdiesel");
                        intent.putExtra("name",stationName);
                        intent.putExtra("ID",stationId);
                        intent.putExtra("status",stationStatus);
                        intent.putExtra("location",stationLocation);
                        startActivity(intent);
                    }
                });

                btn_update_Status.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        MyPetrolStationDataService dataService = new MyPetrolStationDataService(ViewMyPetrolStation.this);
                        dataService.updateStationStatus(new MyPetrolStationDataService.VolleyResponseListenerUpdateStatus() {
                            @Override
                            public void onResponse(String msg) {

                                System.out.println("done updating");
                                Toast.makeText(ViewMyPetrolStation.this, "Updated the Status of the Petrol Station", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ViewMyPetrolStation.this, MypetrolstationlistviewActivity.class);
                                intent.putExtra("userId", GlobalVariables.userId);
                                startActivity(intent);


                            }



                            @Override
                            public void onError(String message) {

                            }
                        },stationId);

                    }
                });

            }

            @Override
            public void onError(String message) {
                Toast.makeText(ViewMyPetrolStation.this,message , Toast.LENGTH_LONG).show();
            }
        },stationId);

        lgBTN1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewMyPetrolStation.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }


}