package com.ksbl;

import com.amadeus.resources.TripDetail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AirportLoader {

    final static String filePath = "airports.csv";

    final static Map<String, Airport> airports = new HashMap<>();

    private static AirportLoader instance;



    private AirportLoader() {
        loadAirports();
    }


    public static AirportLoader getInstance() {
        if (instance == null) {
            synchronized (AirportLoader.class) {
                if (instance == null) {
                    instance = new AirportLoader();
                }
            }
        }
        return instance;
    }

    private void loadAirports() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String iata = values[3];
                double lat = Double.parseDouble(values[values.length-2]);
                double lon = Double.parseDouble(values[values.length-1]);
                String airport = values[5];
                airports.put(iata, new Airport(iata, lat, lon,airport));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Airport getAirport(String code) {
        return airports.get(code);
    }


}
