package com.example.fuelizer;

public class FuelStationDetailsModel {
    private String id;
    private String stationID;
    private String type;
    private String remainder;
    private String capacity;
    private String noOfCars;
    private String noOfVans;
    private String noOfMotocycles;
    private String noOfLorries;
    private String noOfTrishaw;
    private String arrivalTime;
    private boolean finish;

    public FuelStationDetailsModel() {
    }

    public FuelStationDetailsModel(String id, String stationID, String type, String remainder, String capacity, String noOfCars, String noOfVans, String noOfMotocycles, String noOfLorries, String noOfTrishaw, String arrivalTime, boolean finish) {
        this.id = id;
        this.stationID = stationID;
        this.type = type;
        this.remainder = remainder;
        this.capacity = capacity;
        this.noOfCars = noOfCars;
        this.noOfVans = noOfVans;
        this.noOfMotocycles = noOfMotocycles;
        this.noOfLorries = noOfLorries;
        this.noOfTrishaw = noOfTrishaw;
        this.arrivalTime = arrivalTime;
        this.finish = finish;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStationID() {
        return stationID;
    }

    public void setStationID(String stationID) {
        this.stationID = stationID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemainder() {
        return remainder;
    }

    public void setRemainder(String remainder) {
        this.remainder = remainder;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getNoOfCars() {
        return noOfCars;
    }

    public void setNoOfCars(String noOfCars) {
        this.noOfCars = noOfCars;
    }

    public String getNoOfVans() {
        return noOfVans;
    }

    public void setNoOfVans(String noOfVans) {
        this.noOfVans = noOfVans;
    }

    public String getNoOfMotocycles() {
        return noOfMotocycles;
    }

    public void setNoOfMotocycles(String noOfMotocycles) {
        this.noOfMotocycles = noOfMotocycles;
    }

    public String getNoOfLorries() {
        return noOfLorries;
    }

    public void setNoOfLorries(String noOfLorries) {
        this.noOfLorries = noOfLorries;
    }

    public String getNoOfTrishaw() {
        return noOfTrishaw;
    }

    public void setNoOfTrishaw(String noOfTrishaw) {
        this.noOfTrishaw = noOfTrishaw;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }
}
