package com.myProject.entity;

import java.sql.Timestamp;
import java.util.Date;

public class weather {
    private Integer id;
    private String city;
    private Timestamp saveTime;
    private airData airData;

    public weather(Integer id, String city, Timestamp saveTime, com.myProject.entity.airData airData) {
        this.id = id;
        this.city = city;
        this.saveTime = saveTime;
        this.airData = airData;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(Timestamp saveTime) {
        this.saveTime = saveTime;
    }

    public com.myProject.entity.airData getAirData() {
        return airData;
    }

    public void setAirData(com.myProject.entity.airData airData) {
        this.airData = airData;
    }
}
