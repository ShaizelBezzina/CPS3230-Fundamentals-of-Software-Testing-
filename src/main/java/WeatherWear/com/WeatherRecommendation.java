package WeatherWear.com;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;


public class WeatherRecommendation {
    public static void recommendClothingForCurrentLocation() {
               try {
            // Fetch IP Geolocation Data for the user's current location using primary service
            String userIpAddress = getUserIpAddress();
            JSONObject weatherData = getWeatherData(userIpAddress);

            // Extract and display the clothing recommendation for the user's current location
            String clothingRecommendation = extractClothingRecommendation(weatherData);
            System.out.println("For your current location: " + clothingRecommendation);
        } catch (Exception e) {
            System.out.println("Primary IP Geolocation service failed. Trying secondary service...");
            try {
                // Attempt to fetch IP Geolocation Data using a secondary service if the primary service fails
                String userIpAddress = getUserIpAddressSecondary();
                JSONObject weatherData = getWeatherData(userIpAddress);

                String clothingRecommendation = extractClothingRecommendation(weatherData);
                System.out.println("For your current location: " + clothingRecommendation);
            } catch (Exception ex) {
                System.out.println("Both primary and secondary services failed. Unable to get IP Geolocation data.");
            }
        }
    }

    public static void recommendClothingForFutureLocation() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Airport ICAO Code for the future location (e.g., MLA): ");
        String airportICAOCode = scanner.nextLine();

        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Allow the user to enter the date of arrival
        LocalDate dateOfArrival = null;
        boolean validDate = false;

        while (!validDate) {
            System.out.print("Enter the date of arrival (e.g., 23/12/2023): ");
            String dateInput = scanner.nextLine();

            // Validate the date format and ensure it's not more than 10 days in the future
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try {
                dateOfArrival = LocalDate.parse(dateInput, dateFormat);

                // Calculate the difference in days between the current date and the date of arrival
                long daysUntilArrival = ChronoUnit.DAYS.between(currentDate, dateOfArrival);

                if (daysUntilArrival <= 10) {
                    validDate = true;
                } else {
                    System.out.println("The date of arrival cannot be more than 10 days in the future.");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use the format dd/MM/yyyy.");
            }
        }

        Users user = new Users(airportICAOCode, dateOfArrival.toString());
        System.out.println("The Airport ICAO Code is " + user.getAirportICAOCOde() + " and the date of arrival is " + user.getDateOfArrival());

        try {
            // Step 1: Fetch IP Geolocation Data using the primary service
            String userIpAddress = getUserIpAddress();
            JSONObject weatherData = getWeatherData(user.getAirportICAOCOde()); // Pass the ICAO code

            // Step 2: Extract and display the clothing recommendation
            String clothingRecommendation = extractClothingRecommendation(weatherData);
            System.out.println(clothingRecommendation);
        } catch (Exception e) {
            System.out.println("Primary IP Geolocation service failed. Trying secondary service...");
            try {
                // Attempt to fetch IP Geolocation Data using a secondary service if the primary service fails
                String userIpAddress = getUserIpAddressSecondary();
                JSONObject weatherData = getWeatherData(user.getAirportICAOCOde()); // Pass the ICAO code

                String clothingRecommendation = extractClothingRecommendation(weatherData);
                System.out.println(clothingRecommendation);
            } catch (Exception ex) {
                System.out.println("Both primary and secondary services failed. Unable to get IP Geolocation data.");
            }
        }
    }


    private static String getUserIpAddress() throws Exception {
        InetAddress localhost = InetAddress.getLocalHost();
        String localIP = localhost.getHostAddress();

        URL ipApiUrl = new URL("http://checkip.amazonaws.com/");
        HttpURLConnection connection = (HttpURLConnection) ipApiUrl.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String publicIPAddress = reader.readLine();
        reader.close();

        return publicIPAddress;
    }


    private static String getUserIpAddressSecondary() throws Exception {
        // Use a secondary IP Geolocation service here
        // Replace the URL with a real secondary service URL
        // Example:
        InetAddress localhost = InetAddress.getLocalHost();
        String localIP = localhost.getHostAddress();

        URL ipApiUrl = new URL("http://secondary-ip-service.com/"); // Replace with the actual secondary service URL
        HttpURLConnection connection = (HttpURLConnection) ipApiUrl.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String publicIPAddress = reader.readLine();
        reader.close();

        return publicIPAddress;
    }


    private static JSONObject getWeatherData(String ipAddress) throws Exception {
        String rapidApiKey = "c97b470907msh70a283cc3b6294dp18db1fjsn08bc7fe44f6e";
        String weatherApiUrl = "https://weatherapi-com.p.rapidapi.com/current.json?q=" + ipAddress;

        URL url = new URL(weatherApiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com");
        connection.setRequestProperty("X-RapidAPI-Key", rapidApiKey);

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();

        return new JSONObject(response.toString());
    }

    private static String extractClothingRecommendation(JSONObject weatherData) {
        JSONObject locationData = weatherData.getJSONObject("location");
        String locationName = locationData.optString("name", "Unknown");

        JSONObject currentData = weatherData.getJSONObject("current");
        double temperatureCelsius = currentData.optDouble("temp_c", 0.0);

        // Check for "Moderate rain" in the condition text
        boolean isRaining = currentData.getJSONObject("condition").optString("text", "").equalsIgnoreCase("Moderate rain");

        String temperatureDescription = temperatureCelsius <= 15 ? "cold" : "warm";
        String clothingDescription = temperatureCelsius <= 15 ? "warm" : "light";
        String precipitationDescription = isRaining ? "currently" : "not";
        String umbrellaDescription = isRaining ? "do" : "don't";

        return String.format("It is %s so you should wear %s clothing in %s. It is %s raining so you %s need an umbrella.",
                temperatureDescription, clothingDescription, locationName, precipitationDescription, umbrellaDescription);
    }
}