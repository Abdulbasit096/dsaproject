package com.ksbl;

import com.google.gson.annotations.SerializedName;

public class AirportResponse {

    @SerializedName("iata_code")
    private String iataCode;

    @SerializedName("latitude_deg")
    private double latitude;

    @SerializedName("longitude_deg")
    private double longitude;

    // Constructors, getters, and setters

    public AirportResponse() {}

    public AirportResponse(String iataCode, double latitude, double longitude) {
        this.iataCode = iataCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

