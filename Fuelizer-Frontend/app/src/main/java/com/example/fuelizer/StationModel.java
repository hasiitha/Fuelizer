package com.example.fuelizer;
/*
IT18014396
Model for station details implemented in this class
 */
public class StationModel {
    private String id;
    private String station_name;
    private String location;
    private boolean status;

    public StationModel() {

    }

    public StationModel(String id, String station_name, String location, boolean status) {
        this.id = id;
        this.station_name = station_name;
        this.location = location;
        this.status = status;
    }

    @Override
    public String toString() {
        return station_name + ' ' +
                " " + location + ' ' +
                " " + status
                ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
