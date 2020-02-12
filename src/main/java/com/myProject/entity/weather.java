package com.myProject.entity;


public class weather {
    private Integer id;
    private String city;
    private String saveTime;
    private airData airData;


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

    public String getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(String saveTime) {
        this.saveTime = saveTime;
    }

    public airData getAirData() {
        return airData;
    }

    public void setAirData(airData airData) {
        this.airData = airData;
    }
}
