package com.example.fuelizer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class StationDetailsActivity extends AppCompatActivity {

    private Button btnArrived,btnDeparted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_details);

        btnArrived = findViewById(R.id.arrivedBtn);
        btnDeparted = findViewById(R.id.departedBtn);

        btnDeparted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitPopUp();
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