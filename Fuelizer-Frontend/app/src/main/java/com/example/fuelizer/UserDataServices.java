package com.example.fuelizer;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class UserDataServices {

    Context context;
    String userName ="63565a4d34628adcf7ce4daf";


    public UserDataServices(Context context) {
        this.context = context;
    }

    //finish fuel stocks at once
    public interface VolleyResponseListnergetOwnerByUserId{
        void onResponse(String Id);
        void onError(String message);
    }


    //update fuel types in fuel stations
    public void GetUserByID(UserDataServices.VolleyResponseListnergetOwnerByUserId
                                    getOwnerByIdByListener,String userName){

        RequestQueue queue = Volley.newRequestQueue(context);

        System.out.println(userName+"usernamehsa");
        String url = "http://192.168.1.15:8081/api/Supplier/getUserIdByUserName/"+"?username="+userName;;


        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        getOwnerByIdByListener.onResponse(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.getMessage());
                        getOwnerByIdByListener.onError("Error Updating Queue!!!");
                    }
                }
        );

        queue.add(getRequest);


    }
}
