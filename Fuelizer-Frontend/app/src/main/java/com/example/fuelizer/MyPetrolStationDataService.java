package com.example.fuelizer;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/*A class to do the http request using volley for fuel stations related rest functionalities*/
//Reference: https://www.youtube.com/watch?v=xPi-z3nOcn8&t=6488s&ab_channel=freeCodeCamp.org
public class MyPetrolStationDataService {
    Context context;
    String ownerId ;
    public MyPetrolStationDataService(Context context) {
        this.context = context;
    }



    //Interface and method for GetAllStations for a specific user
    public interface VolleyResponseListener{
        void onResponse(ArrayList<StationModel> stationModel);
        void onError(String message);
    }
    public void getAllStations(MyPetrolStationDataService.VolleyResponseListener volleyResponseListener,String ownerId){
        System.out.println(ownerId+"Owneridesa");
        ArrayList<StationModel> stationModelList = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://192.168.1.15:8081/api/FuelStation/getmyfuelstations/"+ownerId;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

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


    //Interface and method for get Details of a specific Fuel Station
    public interface VolleyResponseListenerDet{
        void onResponse(HashMap<String,FuelTypesData> fuelType);
        void onError(String message);
    }

    public void getstationFuelTypes(MyPetrolStationDataService.VolleyResponseListenerDet volleyResponseListener,String StationId){
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
                        }else if(fuelType.getType().equalsIgnoreCase("diesel")  ){
                            fueltypesmap.put("diesel",fuelType);

                        }else if(fuelType.getType().equalsIgnoreCase("superdiesel")){
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


    //Interface and method for update Arriving date and times
    public interface VolleyResponseListenerUpdateArrivalDate{
        void onResponse(String msg);
        void onError(String message);
    }

    public void updateFuelArrivals(VolleyResponseListenerUpdateArrivalDate updateFuelArrivalsResponseListener,String typeID,String date){

     RequestQueue queue = Volley.newRequestQueue(context);

        String url = "http://192.168.1.15:8081/api/FuelType/toUpdateArrivals/"+typeID+"?arrivaltime="+date;


       StringRequest putRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>()
               {
                  @Override
                    public void onResponse(String response) {
                      updateFuelArrivalsResponseListener.onResponse(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.getMessage());
                        updateFuelArrivalsResponseListener.onError("Error Updating Queue!!!");
                    }
                }
        );

        queue.add(putRequest);





    }



//Interface and method for update Stocks
    public interface VolleyResponseListenerUpdateStocks{
        void onResponse(String msg);
        void onError(String message);
    }


    //update fuel capcities
    public void updateStocks(VolleyResponseListenerUpdateStocks updateFuelArrivalsResponsestocks,String typeID,String amount){

        RequestQueue queue = Volley.newRequestQueue(context);

        //String url = URI+"FuelType/toUpdateQueue/"+typeID+"?carCount="+noCars+"&vanCount="+noVans+"&bikeCount="+noBikes+"&tukCount="+noTuks+"&lorryCount="+noLorries;
        String url = "http://192.168.1.15:8081/api/FuelType/toaddamount/"+typeID+"?amount="+amount;


        StringRequest putRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        updateFuelArrivalsResponsestocks.onResponse(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.getMessage());
                        updateFuelArrivalsResponsestocks.onError("Error Updating Queue!!!");
                    }
                }
        );

        queue.add(putRequest);





    }


//Interface and method for Update capacity
    public interface VolleyResponseListenerUpdateCapacity{
        void onResponse(String msg);
        void onError(String message);
    }


    //update fuel types in fuel stations
    public void updateCapacity(VolleyResponseListenerUpdateCapacity updateCapacityListener,String typeID,String newCapacity){

        RequestQueue queue = Volley.newRequestQueue(context);


        String url = "http://192.168.1.15:8081/api/FuelType/toChangeCapacity/"+typeID+"?newCapacity="+newCapacity;


        StringRequest putRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        updateCapacityListener.onResponse(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.getMessage());
                        updateCapacityListener.onError("Error Updating Queue!!!");
                    }
                }
        );

        queue.add(putRequest);





    }



    //Interface and method for Update Finish status
    public interface VolleyResponseListenerFinishStocks{
        void onResponse(String msg);
        void onError(String message);
    }


    //update fuel types in fuel stations
    public void finishStocks(VolleyResponseListenerFinishStocks finishStocksListener,String typeID){

        RequestQueue queue = Volley.newRequestQueue(context);


        String url = "http://192.168.1.15:8081/api/FuelType/finishFuel/"+typeID;


        StringRequest putRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        finishStocksListener.onResponse(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.getMessage());
                        finishStocksListener.onError("Error Updating Queue!!!");
                    }
                }
        );

        queue.add(putRequest);
        }


        //Interface and Method for Update Petrol Station Open Close Status
    public interface VolleyResponseListenerUpdateStatus{
        void onResponse(String msg);
        void onError(String message);
    }

    public void updateStationStatus(VolleyResponseListenerUpdateStatus updateStationStatusListener,String stationId) {

        RequestQueue queue = Volley.newRequestQueue(context);


        String url = "http://192.168.1.15:8081/api/FuelStation/changeStatus/"+stationId;


        StringRequest putRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        updateStationStatusListener.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.getMessage());
                        updateStationStatusListener.onError("Error Updating Queue!!!");
                    }
                }
        );

        queue.add(putRequest);

    }




    }
