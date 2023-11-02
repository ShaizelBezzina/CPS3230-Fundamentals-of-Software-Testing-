package WeatherWear.com;

import org.junit.jupiter.api.Test;

public class WeatherRecommendationTest {

    @Test
    void testRecommendClothingForCurrentLocation() {
        // Calling the method to ensure it doesn't throw an exception
        WeatherRecommendation.recommendClothingForCurrentLocation();
    }

    @Test
    void testRecommendClothingForFutureLocation() {
        // Calling the method to ensure it doesn't throw an exception
        WeatherRecommendation.recommendClothingForFutureLocation();
    }
}

