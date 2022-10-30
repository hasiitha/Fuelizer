package com.example.fuelizer;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

/*
IT18014396
 implementation of fuel station details such as remaining fuel, number of vehicles in the queue and customer arrival and departure parts done in this class.
 */
public class StationDetailsActivity extends AppCompatActivity {

    //variables
    private Button btnArrived,btnDeparted;
    String name,id;
    TextView stationName,p92rem,p92full,p95rem,p95full,s92rem,s95full,s95rem,s92full;
    TextView noCar,noVan,noBike,noTrishaw,noLorry,arrivalTime,approxTimeText;
    TableRow petrolNormal_row,petrolSuper_row,diselNormal_row,diselSuper_row;
    String typeID,stationID,fuelType,remainder,capacity,noCars,noVans,noBikes,noTuks,noLorries,aTime;
    boolean fnish;
    int updateNo = 0;
    double updatedRemainder =0;
    int approxTimetoOneVehicle = 4;
    int approxTime = 0;

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
        approxTimeText = findViewById(R.id.turn_txt);


        Intent intent = this.getIntent();
        if(intent != null){
            name = intent.getStringExtra("name");
            id = intent.getStringExtra("ID");
        }
        System.out.println("Intent Name: "+ name+" AND ID: "+id);
        stationName.setText(name);

        //filtering UI according to user fuel type
        if(GlobalVariables.userFuelType.equals("Petrol92") || GlobalVariables.userFuelType.equals("Petrol95")){
            diselSuper_row.setVisibility(View.GONE);
            diselNormal_row.setVisibility(View.GONE);
        }else if(GlobalVariables.userFuelType.equals("Diesel") || GlobalVariables.userFuelType.equals("SuperDiesel")){
            petrolSuper_row.setVisibility(View.GONE);
            petrolNormal_row.setVisibility(View.GONE);
        }

        System.out.println("User NAME: "+GlobalVariables.userName);

        //calling station data service class to get fuel station details
        StationDataService dataService = new StationDataService(StationDetailsActivity.this);
        dataService.getFuelStationDetails(new StationDataService.FuelDataResponseListener() {
            @Override
            public void onResponse(FuelStationDetailsModel stationModel) {
                //setting fuel capacities according to fuel type
                if(stationModel.getType().equals("Petrol92")||stationModel.getType().equals("Petrol95")){
                    p92rem.setText(stationModel.getRemainder());
                    p92full.setText(stationModel.getCapacity());
                }else if(stationModel.getType().equals("Diesel") || stationModel.getType().equals("SuperDiesel")){
                    s92rem.setText(stationModel.getRemainder());
                    s92full.setText(stationModel.getCapacity());
                }

                //Setting retrieved data for variables
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

        //calling next turn time calculator
//        String time = timeCalculator();
//        approxTimeText.setText(time+" minutes");
        String vehicleType = GlobalVariables.userVehicle;
//        Checking user vehicle type and adding to existing queue
        System.out.println(vehicleType);
        switch (vehicleType){

            case "Car":
                approxTime = GlobalVariables.iNoCars * approxTimetoOneVehicle;
                System.out.println("Approx Car:"+ approxTime);
                break;
            case "Van":
                approxTime = GlobalVariables.iNoVans * approxTimetoOneVehicle;
                System.out.println("Approx Van:"+ approxTime);
                break;
            case "Motorcycle":
                approxTime = GlobalVariables.iNoBikes * approxTimetoOneVehicle;
                System.out.println("Approx Bike:"+ approxTime);
                break;
            case "Trishaw":
                approxTime = GlobalVariables.iNoTuks * approxTimetoOneVehicle;
                System.out.println("Approx Tri:"+ approxTime);
                break;
            case "Lorry":
                approxTime = GlobalVariables.iNoLorries * approxTimetoOneVehicle;
                System.out.println("Approx Lorry:"+ approxTime);
                break;
        }
        String time = Integer.toString(approxTime)+" minutes";
        System.out.println("Time:"+ time);

        approxTimeText.setText(time);

        //popup part
        btnDeparted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitPopUp();
            }
        });

        //Queue Arrival part
        btnArrived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StationDataService stationDataService = new StationDataService(StationDetailsActivity.this);
                String vehicleType = GlobalVariables.userVehicle;
                //Checking user vehicle type and adding to existing queue
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
                //calling update fuel queue service
                stationDataService.updateFuelQueue(new StationDataService.UpdateFuelQueueResponseListener() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(StationDetailsActivity.this, "Joined to Queue...", Toast.LENGTH_SHORT).show();
                        //Refreshing the activity
                        Intent i = new Intent(StationDetailsActivity.this,StationDetailsActivity.class);
                        finish();
                        overridePendingTransition(0,0);
                        startActivity(i);
                        overridePendingTransition(0,0);
                        btnArrived.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(StationDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }, typeID, noCars, noVans, noBikes, noTuks, noLorries,remainder);
            }
        });
    }
    //Departure popup method
    private void exitPopUp(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.exit_popup_dialog);
        ImageView buttonClose = dialog.findViewById(R.id.close_icon);
        EditText pumpedED = (EditText) dialog.findViewById(R.id.filled_Amount_editText);
        Button exitWithPumped = dialog.findViewById(R.id.filled_Amount_Btn);
        Button leaveBtn = dialog.findViewById(R.id.leaveQueueBtn);
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
        Editable filledAmount = pumpedED.getText();

        exitWithPumped.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StationDataService stationDataService = new StationDataService(StationDetailsActivity.this);
                //removing from the queue calculation
                String vehicleType = GlobalVariables.userVehicle;
                switch (vehicleType){
                    case "Car":
                        updateNo = Integer.parseInt(noCars);
                        updateNo = updateNo - 1;
                        if(GlobalVariables.iNoCars != 0) {
                            GlobalVariables.iNoCars = GlobalVariables.iNoCars - 1;
                        }
                        noCars = Integer.toString(updateNo);
                        break;
                    case "Van":
                        updateNo = Integer.parseInt(noVans);
                        updateNo = updateNo - 1;
                        if(GlobalVariables.iNoVans != 0) {
                            GlobalVariables.iNoVans = GlobalVariables.iNoVans - 1;
                        }
                        noVans = Integer.toString(updateNo);
                        break;
                    case "Motorcycle":
                        updateNo = Integer.parseInt(noBikes);
                        updateNo = updateNo - 1;
                        if(GlobalVariables.iNoBikes != 0) {
                            GlobalVariables.iNoBikes = GlobalVariables.iNoBikes - 1;
                        }
                        noBikes = Integer.toString(updateNo);
                        break;
                    case "Trishaw":
                        updateNo = Integer.parseInt(noTuks);
                        updateNo = updateNo - 1;
                        if(GlobalVariables.iNoTuks != 0) {
                            GlobalVariables.iNoTuks = GlobalVariables.iNoTuks - 1;
                        }
                        noTuks = Integer.toString(updateNo);
                        break;
                    case "Lorry":
                        updateNo = Integer.parseInt(noLorries);
                        updateNo = updateNo - 1;
                        if(GlobalVariables.iNoLorries != 0) {
                            GlobalVariables.iNoLorries = GlobalVariables.iNoLorries - 1;
                        }
                        noLorries = Integer.toString(updateNo);
                        break;
                }

                //Calculating remainder fuel
                double filled = Double.parseDouble(filledAmount.toString());
                updatedRemainder = Double.parseDouble(remainder);
                updatedRemainder = updatedRemainder - filled;
                String upRemainder = Double.toString(updatedRemainder);

                stationDataService.updateFuelQueue(new StationDataService.UpdateFuelQueueResponseListener() {
                    @Override
                    public void onResponse(String response) {
                        Intent intent = new Intent(StationDetailsActivity.this,CustomerListView.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(StationDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }, typeID, noCars, noVans, noBikes, noTuks, noLorries,upRemainder);
            }
        });
        //leave without filling method
        leaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StationDataService stationDataService = new StationDataService(StationDetailsActivity.this);
                String vehicleType = GlobalVariables.userVehicle;
                switch (vehicleType){
                    case "Car":
                        updateNo = Integer.parseInt(noCars);
                        updateNo = updateNo - 1;
                        if(GlobalVariables.iNoCars != 0){
                            GlobalVariables.iNoCars = GlobalVariables.iNoCars - 1;
                        }
                        noCars = Integer.toString(updateNo);
                        break;
                    case "Van":
                        updateNo = Integer.parseInt(noVans);
                        updateNo = updateNo - 1;
                        if(GlobalVariables.iNoVans != 0){
                        GlobalVariables.iNoVans = GlobalVariables.iNoVans - 1;}
                        noVans = Integer.toString(updateNo);
                        break;
                    case "Motorcycle":
                        updateNo = Integer.parseInt(noBikes);
                        updateNo = updateNo - 1;
                        if(GlobalVariables.iNoBikes != 0) {
                            GlobalVariables.iNoBikes = GlobalVariables.iNoBikes - 1;
                        }
                        noBikes = Integer.toString(updateNo);
                        break;
                    case "Trishaw":
                        updateNo = Integer.parseInt(noTuks);
                        updateNo = updateNo - 1;
                        if(GlobalVariables.iNoTuks != 0){
                        GlobalVariables.iNoTuks = GlobalVariables.iNoTuks - 1;
                        }
                        noTuks = Integer.toString(updateNo);
                        break;
                    case "Lorry":
                        updateNo = Integer.parseInt(noLorries);
                        updateNo = updateNo - 1;
                        if(GlobalVariables.iNoLorries != 0) {
                            GlobalVariables.iNoLorries = GlobalVariables.iNoLorries - 1;
                        }
                        noLorries = Integer.toString(updateNo);
                        break;
                }

                stationDataService.updateFuelQueue(new StationDataService.UpdateFuelQueueResponseListener() {
                    @Override
                    public void onResponse(String response) {
                        Intent intent = new Intent(StationDetailsActivity.this,CustomerListView.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(StationDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }, typeID, noCars, noVans, noBikes, noTuks, noLorries,remainder);
            }
        });

    }

//    public String timeCalculator(){
//        String vehicleType = GlobalVariables.userVehicle;
//        //Checking user vehicle type and adding to existing queue
//        switch (vehicleType){
//            case "Car":
//                approxTime = Integer.parseInt(noCars) * approxTimetoOneVehicle;
//                System.out.println("Approx Car:"+ approxTime);
//                break;
//            case "Van":
//                approxTime = Integer.parseInt(noVans) * approxTimetoOneVehicle;
//                System.out.println("Approx Van:"+ approxTime);
//                break;
//            case "Motorcycle":
//                approxTime = Integer.parseInt(noBikes) * approxTimetoOneVehicle;
//                System.out.println("Approx Bike:"+ approxTime);
//                break;
//            case "Trishaw":
//                approxTime = Integer.parseInt(noTuks) * approxTimetoOneVehicle;
//                System.out.println("Approx Tri:"+ approxTime);
//                break;
//            case "Lorry":
//                approxTime = Integer.parseInt(noLorries) * approxTimetoOneVehicle;
//                System.out.println("Approx Lorry:"+ approxTime);
//                break;
//        }
//        System.out.println("Approx :"+ approxTime);
//        return Integer.toString(approxTime);
//    }


}