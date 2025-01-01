package com.ksbl;


import com.amadeus.resources.TripDetail;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Scanner;

public class App {
    public static void main( String[] args ) {
        Scanner scanner  = new Scanner(System.in);
        System.out.println("Enter the country you wanna departure from :");
        Dotenv dotenv = Dotenv.load();
        AirportLoader airportLoader = AirportLoader.getInstance();
        Countries countries = new Countries();
        Object searchedCountries = countries.searchCountryByName("United arab");




//        String origin = "KHI";
//        String destination = "MEL";
//        int routes = 5;



//        String apiKey = dotenv.get("API_KEY");
//        String apiSecret = dotenv.get("API_SECRET");
//
//
//        FlightDataLoader dataLoader = new FlightDataLoader(apiKey, apiSecret);
//
//
//        LocalDate date = LocalDate.now().plusDays(7); // Search for flights a week from now
//        FlightGraph graph = dataLoader.loadFlightData(origin, destination, date,routes);
//
//
//        Route shortestRoute = graph.findShortestRoute(origin, destination);
//        Route cheapestRoute = graph.findCheapestRoute(origin, destination);
//
//
//        System.out.println("Shortest Route:");
//        System.out.println(shortestRoute);
//        System.out.println("\nCheapest Route:");
//        System.out.println(cheapestRoute);
    }
}
