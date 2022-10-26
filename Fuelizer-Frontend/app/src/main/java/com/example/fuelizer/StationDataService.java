package com.example.fuelizer;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StationDataService {

    //Reference: https://www.youtube.com/watch?v=xPi-z3nOcn8&t=6488s&ab_channel=freeCodeCamp.org
    Context context;

    public StationDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onResponse(ArrayList<StationModel> stationModel);
        void onError(String message);
    }

    public void getAllStations(VolleyResponseListener volleyResponseListener){
        ArrayList<StationModel> stationModelList = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://192.168.1.5:8080/api/FuelStation";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    System.out.println("Hi: "+response);
                    for (int i = 0; i < response.length(); i++) {
                        StationModel stationModel = new StationModel();
                        JSONObject first_data = (JSONObject) response.get(i);
                        stationModel.setId(first_data.getString("id"));
                        stationModel.setStation_name(first_data.getString("stationName"));
                        stationModel.setLocation(first_data.getString("location"));
                        stationModel.setStatus(first_data.getBoolean("openCloseStatus"));

                        stationModelList.add(stationModel);

                    }

                    volleyResponseListener.onResponse(stationModelList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("Error Fetching Stations!!!");
            }
        });
        queue.add(request);
    }
    public interface UserDetailsResponseListener{
        void onResponse(CustomerModel object);
        void onError(String message);
    }

    public void getUserDetails(UserDetailsResponseListener userDetailsResponseListener){
        RequestQueue queue = Volley.newRequestQueue(context);
        CustomerModel customerModel = new CustomerModel();
        String url = "http://192.168.1.5:8080/api/Customer/ByName/locha";
//                +GlobalVariables.userName;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println("USER DATA FROM API: " + response);
                    JSONObject object = new JSONObject(response);
                    customerModel.setId(object.getString("id"));
                    customerModel.setUserName(object.getString("userName"));
                    customerModel.setVehicleType(object.getString("vehicleType"));
                    customerModel.setFuelType(object.getString("fuelType"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                GlobalVariables.userFuelType = customerModel.getFuelType();
                GlobalVariables.userVehicle = customerModel.getVehicleType();
                userDetailsResponseListener.onResponse(customerModel);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                userDetailsResponseListener.onError("Error Fetching User!!!"+error);
            }
        });
        queue.add(request);
    }

    public interface FuelDataResponseListener{
        void onResponse(FuelStationDetailsModel detailsModels);
        void onError(String message);
    }

    public void getFuelStationDetails(FuelDataResponseListener fuelDataResponseListener){
//        ArrayList<StationModel> stationModelList = new ArrayList<>();
        FuelStationDetailsModel fuelStationDetailsModel = new FuelStationDetailsModel();
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://192.168.1.5:8080/api/FuelType/station/"+GlobalVariables.selectedStation+"/"+GlobalVariables.userFuelType;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    System.out.println("Hi: "+response);
//                    for (int i = 0; i < response.length(); i++) {
//                        StationModel stationModel = new StationModel();
                        JSONObject first_data = (JSONObject) response.get(0);
                    System.out.println("Fuel Types DAta: "+first_data);
                    fuelStationDetailsModel.setId(first_data.getString("id"));
                    fuelStationDetailsModel.setStationID(first_data.getString("stationId"));
                    fuelStationDetailsModel.setType(first_data.getString("type"));
                    fuelStationDetailsModel.setRemainder(first_data.getString("remainder"));
                    fuelStationDetailsModel.setCapacity(first_data.getString("capacity"));
                    fuelStationDetailsModel.setNoOfCars(first_data.getString("noOfCars"));
                    fuelStationDetailsModel.setNoOfVans(first_data.getString("noOfVans"));
                    fuelStationDetailsModel.setNoOfMotocycles(first_data.getString("noOfMotocycles"));
                    fuelStationDetailsModel.setNoOfLorries(first_data.getString("noOfLorries"));
                    fuelStationDetailsModel.setNoOfTrishaw(first_data.getString("noOfTrishaw"));
                    fuelStationDetailsModel.setArrivalTime(first_data.getString("arrivalTime"));
                    fuelStationDetailsModel.setFinish(first_data.getBoolean("finish"));

//                        stationModelList.add(stationModel);

//                    }

        fuelDataResponseListener.onResponse(fuelStationDetailsModel);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                fuelDataResponseListener.onError("Error Fetching Stations!!!");
            }
        });
        queue.add(request);
    }

    public interface UpdateFuelQueueResponseListener{
        void onResponse(String response);
        void onError(String message);
    }

    public void updateFuelQueue(UpdateFuelQueueResponseListener updateFuelQueueResponseListener,String typeID, String stationID, String fuelType, String remainder, String capacity, String noCars, String noVans, String noBikes, String noTuks, String noLorries, String aTime, boolean fnish){
        FuelStationDetailsModel detailsModel = new FuelStationDetailsModel();

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://192.168.1.5:8080/api/FuelType/"+typeID;


        StringRequest putRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
//                        Log.d("Response", response);
                        updateFuelQueueResponseListener.onResponse(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.getMessage());
                        updateFuelQueueResponseListener.onError("Error Updating Queue!!!");
                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String> ();
                params.put("id", typeID);
                params.put("stationId", stationID);
                params.put("type", fuelType);
                params.put("remainder", remainder);
                params.put("capacity", capacity);
                params.put("noOfCars", noCars);
                params.put("noOfVans", noVans);
                params.put("noOfMotocycles", noBikes);
                params.put("noOfLorries", noLorries);
                params.put("noOfTrishaw", noTuks);
                params.put("arrivalTime", aTime);
                params.put("finish", "");

                return params;
            }

        };

        queue.add(putRequest);





    }
}
