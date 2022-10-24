package com.example.fuelizer;

import android.content.Context;
import android.widget.Toast;

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
import java.util.List;

public class StationDataService {

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
}
