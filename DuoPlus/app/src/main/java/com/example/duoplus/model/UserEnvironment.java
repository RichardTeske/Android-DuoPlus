package com.example.duoplus.model;

import java.util.List;

public class UserEnvironment {

    private int userEnvironmentId;
    private String userId;
    private String deviceId;
    private String environmentId;
    private int quantityDevices;
    private String environmentName;

    public int getUserEnvironmentId() {
        return userEnvironmentId;
    }

    public void setUserEnvironmentId(int userEnvironmentId) {
        this.userEnvironmentId = userEnvironmentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getEnvironmentId() {
        return environmentId;
    }

    public void setEnvironmentId(String environmentId) {
        this.environmentId = environmentId;
    }

    public int getQuantityDevices() {
        return quantityDevices;
    }

    public void setQuantityDevices(int quantityDevices) {
        this.quantityDevices = quantityDevices;
    }

    public String getEnvironmentName() {
        return environmentName;
    }

    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }

    public String toString(){
        return "ID: " + getUserEnvironmentId() +
                "\nUserID: "+ getUserId()+
                "\nDeviceID: "+getDeviceId()+
                "\nEnv ID: "+getEnvironmentId()+
                "\nEnv Name: "+getEnvironmentName()+
                "\nDev Qtd: "+getQuantityDevices();
    }
}