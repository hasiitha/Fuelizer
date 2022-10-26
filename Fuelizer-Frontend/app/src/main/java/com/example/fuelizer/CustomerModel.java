package com.example.fuelizer;
/*
IT18014396
Model for customer details implemented in this class
 */
public class CustomerModel {
    private String id;
    private String userName;
    private String vehicleType;
    private String fuelType ;

    public CustomerModel() {
    }

    public CustomerModel(String id, String userName, String vehicleType, String fuelType) {
        this.id = id;
        this.userName = userName;
        this.vehicleType = vehicleType;
        this.fuelType = fuelType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
