package com.ksbl;

class Airport{

    String IATA;
    double latitude;
    double longitude;
    String airport;

    public Airport(String IATA, double latitude, double longitude,String airport) {
        this.IATA = IATA;
        this.latitude = latitude;
        this.longitude = longitude;
        this.airport = airport;
    }

    public String getIATA() {
        return IATA;
    }

    public void setIATA(String IATA) {
        this.IATA = IATA;
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

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }
}
