package WeatherWear.com;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("WeatherWear.com");
        System.out.println("----------------");
        int choice;

        do {
            System.out.println("1. Recommend clothing for current location");
            System.out.println("2. Recommend clothing for future location");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    WeatherRecommendation.recommendClothingForCurrentLocation();
                    break;
                case 2:
                    WeatherRecommendation.recommendClothingForFutureLocation();
                    break;
                case 3:
                    System.out.println("Exiting the application. Goodbye :)");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (choice != 3);
    }
}