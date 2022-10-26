package com.example.fuelizer;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MyPetrolStationDataService {

    //Reference: https://www.youtube.com/watch?v=xPi-z3nOcn8&t=6488s&ab_channel=freeCodeCamp.org
    Context context;
    String ownerId ="63565a4d34628adcf7ce4daf";
    String StationId = "634e6b9a2d6a9c529c1064a4";

    public MyPetrolStationDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onResponse(ArrayList<StationModel> stationModel);
        void onError(String message);
    }

    public void getAllStations(MyPetrolStationDataService.VolleyResponseListener volleyResponseListener){
        ArrayList<StationModel> stationModelList = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://192.168.1.15:8081/api/FuelStation/getmyfuelstations/"+ownerId;
        //http://192.168.1.15:8081/api/FuelStation
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


    //getting petrolstationdetails
    public interface VolleyResponseListenerDet{
        void onResponse(HashMap<String,FuelTypesData> fuelType);
        void onError(String message);
    }

    public void getstationFuelTypes(MyPetrolStationDataService.VolleyResponseListenerDet volleyResponseListener){
        ArrayList<StationModel> stationModelList = new ArrayList<>();
        HashMap<String,FuelTypesData> fueltypesmap = new HashMap<>();

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://192.168.1.15:8081/api/FuelType/getFuelTypesofStation/"+StationId;
        //http://192.168.1.15:8081/api/FuelStation
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    System.out.println("Hi: "+response);
                    for (int i = 0; i < response.length(); i++) {
                        FuelTypesData fuelType = new FuelTypesData();
                        JSONObject first_data = (JSONObject) response.get(i);
                        fuelType.setId(first_data.getString("id"));
                        fuelType.setArrivalTime(first_data.getString("arrivalTime"));
                        fuelType.setCapacity(first_data.getString("capacity"));
                        fuelType.setFinish(first_data.getBoolean("finish"));
                        fuelType.setStationId(first_data.getString("stationId"));
                        fuelType.setRemainder(first_data.getString("remainder"));
                        fuelType.setType(first_data.getString("type"));




                        if(fuelType.getType().equalsIgnoreCase("Petrol") ){
                            System.out.println("Single object type "+fuelType.getType());
                            fueltypesmap.put("petrol",fuelType);

                        }else if(fuelType.getType().equalsIgnoreCase("Petrol95") ){
                            fueltypesmap.put("petrol95",fuelType);
                        }else if(fuelType.getType().equalsIgnoreCase("Disel")  ){
                            fueltypesmap.put("diesel",fuelType);

                        }else if(fuelType.getType().equalsIgnoreCase("SuperDisel")){
                            fueltypesmap.put("superdiesel",fuelType);
                        }

                    }

                    volleyResponseListener.onResponse(fueltypesmap);

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
}
