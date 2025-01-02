package com.ksbl;







import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import io.github.cdimascio.dotenv.Dotenv;
import java.util.*;


public class App {

    public static void main(String[] args) {
        AirlineLoader airlineLoader = AirlineLoader.getInstance();
        Dotenv dotenv = Dotenv.load();
        Airports airports = new Airports();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the origin country, or search for the country if you don't know the exact official name: ");
        String origin = scanner.nextLine();
        HashMap<String,String> originCountry = new HashMap<String,String>();
        HashMap<String,String> destinationCountry = new HashMap<String,String>();


        List<Airport> originsList = airports.searchByCountry(origin);
        List<String> distinctOriginCountries = getDistinctCountries(originsList);

        if (distinctOriginCountries.size() > 1) {
            originCountry = printCountries(originsList, distinctOriginCountries, scanner);
        } else {
            List<String> distinctRegions = getDistinctRegions(originsList);
            originCountry = printRegions(originsList, distinctRegions, distinctOriginCountries.get(0),scanner);
        }

        System.out.print("Enter the destination country, or search for the country if you don't know the exact official name: ");
        String destination = scanner.nextLine();

        List<Airport> destinationList = airports.searchByCountry(destination);




        List<String> distinctDestinationCountries = getDistinctCountries(destinationList);
        if (distinctDestinationCountries.size() > 1) {
            destinationCountry = printCountries(destinationList, distinctDestinationCountries, scanner);
        } else {
            List<String> distinctDestinationRegions = getDistinctRegions(destinationList);
            destinationCountry = printRegions(destinationList, distinctDestinationRegions,distinctDestinationCountries.get(0) ,scanner);
        }

        System.out.print("Enter the date you wanna fly on (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Do you want the Cheapest or the shortest flight ? ");
        String mode = scanner.nextLine();

        boolean cheapest = mode.equalsIgnoreCase("cheapest");


        System.out.println("FLIGHT SUMMARY");
        System.out.println("+----------------------+----------------------+----------------------+");
        System.out.println("Origin Country: " + originCountry.get("country"));
        System.out.println("Destination Country: " + destinationCountry.get("country"));
        System.out.println("Date : " + date);
        System.out.println("Cheapest : " + (cheapest ? "Yes":"No"));
        System.out.println("Shortest : " + (!cheapest ? "Yes":"No"));
        System.out.println("+----------------------+----------------------+----------------------+");

        int routes = 5;



        String apiKey = dotenv.get("API_KEY");
        String apiSecret = dotenv.get("API_SECRET");


        FlightDataLoader dataLoader = new FlightDataLoader(apiKey, apiSecret);


        LocalDate localDate = LocalDate.parse(date); // Search for flights a week from now

        System.out.println("Getting the flights....");

        FlightGraph graph = dataLoader.loadFlightData(originCountry.get("IATA"), destinationCountry.get("IATA"),localDate,routes,cheapest);




        if (cheapest){
            Route cheapestRoute = graph.findCheapestRoute(originCountry.get("IATA"), destinationCountry.get("IATA"));
            if (cheapestRoute.toString().isEmpty()){
                System.out.println("We're sorry but there are no flights available");
            }else{

            System.out.println("The Cheapest Route for you will be: ");
            System.out.println(cheapestRoute);
            }
        }else{
            Route shortestRoute = graph.findShortestRoute(originCountry.get("IATA"), destinationCountry.get("IATA"));
            System.out.println("The Shortest Route for you will be");
            System.out.println(shortestRoute);

        }
    }

    public static List<String> getDistinctCountries(List<Airport> airports) {
        return airports.stream()
                .map(Airport::country)
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<String> getDistinctRegions(List<Airport> airports) {
        return airports.stream()
                .map(Airport::regionName)
                .distinct()
                .collect(Collectors.toList());
    }

    public static HashMap<String,String> printCountries(List<Airport> airports, List<String> distinctCountries, Scanner sc) {
        System.out.println("+----------------------+----------------------+----------------------+");
        for (int i = 0; i < distinctCountries.size(); i++) {
            System.out.println("|- " + (i + 1) + ": " + distinctCountries.get(i));
        }



        System.out.print("Select your country: ");
        int state = sc.nextInt();
        sc.nextLine();
        List<String> distinctRegions = airports.stream()
                .filter(airport -> airport.country().equals(distinctCountries.get(state - 1)))
                .map(Airport::regionName)
                .distinct()
                .collect(Collectors.toList());
        return printRegions(airports, distinctRegions, distinctCountries.get(state-1),sc);
    }

    public static HashMap<String,String> printRegions(List<Airport> regionsAirports, List<String> distinctRegions,String country ,Scanner sc) {
        System.out.println("Following are the regions in the "+country+": ");
        System.out.println("+----------------------+----------------------+----------------------+");
        for (int i = 0; i < distinctRegions.size(); i++) {
            System.out.println("|- " + (i + 1) + ": " + distinctRegions.get(i));
        }

        System.out.print("Select your state/region/province: ");
        int state = sc.nextInt();
        sc.nextLine();
        Set<Airport> cities = regionsAirports.stream()
                .filter(a -> a.regionName().equals(distinctRegions.get(state - 1)))
                .collect(Collectors.toSet());
        return printCities(new ArrayList<>(cities), distinctRegions.get(state-1) ,sc);
    }

    public static HashMap<String,String> printCities(List<Airport> airportsCities, String region ,Scanner sc) {
        String[] cities = new String[airportsCities.size()];
        System.out.println("Following are the cities in " + region+": ");
        System.out.println("+----------------------+----------------------+----------------------+");
        for (int i = 0; i < airportsCities.size(); i++) {
            System.out.println("|- " + (i + 1) + ": " + airportsCities.get(i).city());
            cities[i] = airportsCities.get(i).city();
        }
        System.out.print("Select your city: ");
        int state = sc.nextInt();
        sc.nextLine();
        Airport airport = airportsCities.stream()
                .filter(a -> a.city().equals(cities[state - 1]))
                .findFirst()
                .orElse(null);
        HashMap<String,String> flight = new HashMap<String,String>();
        if (airport != null) {
            flight.put("IATA",airport.IATA());
            flight.put("country",airport.country());
            System.out.println("+----------------------+----------------------+----------------------+");
            System.out.println("|--+ Your flight will take off from " + airport.airportName()+" +--|");
            System.out.println("+----------------------+----------------------+----------------------+");

        } else {
            System.out.println("No airport found in the selected city.");

        }
        return flight;
    }
}



