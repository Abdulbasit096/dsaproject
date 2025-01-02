package com.ksbl;

import com.amadeus.travel.analytics.AirTraffic;

import java.util.Objects;

public final class Airport {
    private final String country;
    private final String city;
    private final String countryCode;
    private final String regionName;
    private final String IATA;
    private final String ICAO;
    private final String airportName;
    private final double latitude;
    private final double longitude;

    public Airport(
            String country,
            String city,
            String countryCode,
            String regionName,
            String IATA,
            String ICAO,
            String airportName,
            double latitude,
            double longitude) {
        this.country = country;
        this.city = city;
        this.countryCode = countryCode;
        this.regionName = regionName;
        this.IATA = IATA;
        this.ICAO = ICAO;
        this.airportName = airportName;
        this.latitude = latitude;
        this.longitude = longitude;
    }



    public String country() {
        return country;
    }

    public String city() {
        return city;
    }

    public String countryCode() {
        return countryCode;
    }

    public String regionName() {
        return regionName;
    }

    public String IATA() {
        return IATA;
    }

    public String ICAO() {
        return ICAO;
    }

    public String airportName() {
        return airportName;
    }

    public double latitude() {
        return latitude;
    }

    public double longitude() {
        return longitude;
    }





}
