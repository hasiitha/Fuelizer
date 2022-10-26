package com.example.fuelizer;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class StationDetailsActivity extends AppCompatActivity {

    private Button btnArrived,btnDeparted;
    String name,id;
    TextView stationName,p92rem,p92full,p95rem,p95full,s92rem,s95full,s95rem,s92full;
    TextView noCar,noVan,noBike,noTrishaw,noLorry,arrivalTime;
    TableRow petrolNormal_row,petrolSuper_row,diselNormal_row,diselSuper_row;
    String typeID,stationID,fuelType,remainder,capacity,noCars,noVans,noBikes,noTuks,noLorries,aTime;
    boolean fnish;
    int updateNo = 0;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_details);

        btnArrived = findViewById(R.id.arrivedBtn);
        btnDeparted = findViewById(R.id.departedBtn);
        stationName = findViewById(R.id.stationName_txt);
        petrolNormal_row = findViewById(R.id.p92row);
        petrolSuper_row = findViewById(R.id.p95row);
        diselNormal_row = findViewById(R.id.drow);
        diselSuper_row = findViewById(R.id.superdrow);
        p92rem = findViewById(R.id.p92Rem_txt);
        p95rem = findViewById(R.id.p95Rem_txt);
        p92full = findViewById(R.id.p92Full_txt);
        p95full = findViewById(R.id.p95Full_txt);
        s92rem = findViewById(R.id.dRem_txt);
        s95rem = findViewById(R.id.dSuperRem_txt);
        s92full = findViewById(R.id.dFull_txt);
        s95full = findViewById(R.id.dSuperFull_txt);
        noCar = findViewById(R.id.carCount_txt);
        noVan = findViewById(R.id.vanCount_txt);
        noBike = findViewById(R.id.motoCount_txt);
        noTrishaw = findViewById(R.id.triShawCount_txt);
        noLorry = findViewById(R.id.lorryCount_txt);
        arrivalTime = findViewById(R.id.pArriveDate_txt);


        Intent intent = this.getIntent();
        if(intent != null){
            name = intent.getStringExtra("name");
            id = intent.getStringExtra("ID");
        }
        System.out.println("Intent Name: "+ name+" AND ID: "+id);
        stationName.setText(name);

//        System.out.println(GlobalVariables.userFuelType.toUpperCase());
        if(GlobalVariables.userFuelType.equals("Petrol92") || GlobalVariables.userFuelType.equals("Petrol95")){
            diselSuper_row.setVisibility(View.GONE);
            diselNormal_row.setVisibility(View.GONE);
        }else if(GlobalVariables.userFuelType.equals("Diesel") || GlobalVariables.userFuelType.equals("SuperDiesel")){
            petrolSuper_row.setVisibility(View.GONE);
            petrolNormal_row.setVisibility(View.GONE);
        }



        StationDataService dataService = new StationDataService(StationDetailsActivity.this);
        dataService.getFuelStationDetails(new StationDataService.FuelDataResponseListener() {
            @Override
            public void onResponse(FuelStationDetailsModel stationModel) {
                if(stationModel.getType().equals("Petrol92")||stationModel.getType().equals("Petrol95")){
                    p92rem.setText(stationModel.getRemainder());
                    p92full.setText(stationModel.getCapacity());
                }else if(stationModel.getType().equals("Diesel") || stationModel.getType().equals("SuperDiesel")){
                    s92rem.setText(stationModel.getRemainder());
                    s92full.setText(stationModel.getCapacity());
                }
                noCar.setText(stationModel.getNoOfCars());
                noVan.setText(stationModel.getNoOfVans());
                noBike.setText(stationModel.getNoOfMotocycles());
                noTrishaw.setText(stationModel.getNoOfTrishaw());
                noLorry.setText(stationModel.getNoOfLorries());
                arrivalTime.setText(stationModel.getArrivalTime());

                typeID = stationModel.getId();
                stationID= stationModel.getStationID();
                fuelType = stationModel.getType();
                remainder = stationModel.getRemainder();
                capacity = stationModel.getCapacity();
                noCars = stationModel.getNoOfCars();
                noVans = stationModel.getNoOfVans();
                noBikes = stationModel.getNoOfMotocycles();
                noTuks = stationModel.getNoOfTrishaw();
                noLorries = stationModel.getNoOfLorries();
                aTime = stationModel.getArrivalTime();
                fnish = stationModel.isFinish();


            }

            @Override
            public void onError(String message) {
                Toast.makeText(StationDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        btnDeparted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitPopUp();
            }
        });

        btnArrived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StationDataService stationDataService = new StationDataService(StationDetailsActivity.this);
                String vehicleType = GlobalVariables.userVehicle;
                switch (vehicleType){
                    case "Car":
                        updateNo = Integer.parseInt(noCars);
                        updateNo = updateNo + 1;
                        noCars = Integer.toString(updateNo);
                        break;
                    case "Van":
                        updateNo = Integer.parseInt(noVans);
                        updateNo = updateNo + 1;
                        noVans = Integer.toString(updateNo);
                        break;
                    case "Motorcycle":
                        updateNo = Integer.parseInt(noBikes);
                        updateNo = updateNo + 1;
                        noBikes = Integer.toString(updateNo);
                        break;
                    case "Trishaw":
                        updateNo = Integer.parseInt(noTuks);
                        updateNo = updateNo + 1;
                        noTuks = Integer.toString(updateNo);
                        break;
                    case "Lorry":
                        updateNo = Integer.parseInt(noLorries);
                        updateNo = updateNo + 1;
                        noLorries = Integer.toString(updateNo);
                        break;
                }
                stationDataService.updateFuelQueue(new StationDataService.UpdateFuelQueueResponseListener() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(StationDetailsActivity.this, "SuCCESS"+response, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(StationDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }, typeID, stationID, fuelType, remainder, capacity, noCars, noVans, noBikes, noTuks, noLorries, aTime, fnish);
            }
        });
    }

    private void exitPopUp(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.exit_popup_dialog);
        ImageView buttonClose = dialog.findViewById(R.id.close_icon);
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}