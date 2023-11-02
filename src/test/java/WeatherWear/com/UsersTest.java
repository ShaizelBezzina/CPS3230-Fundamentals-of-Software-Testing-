package WeatherWear.com;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsersTest {

    @Test

        void testGetAirportICAOCOde() {
            // Creating a Users object with known values
            Users user = new Users("JFK", "2023-12-31");

            // Testing if the getAirportICAOCOde method returns the correct value
            assertEquals("JFK", user.getAirportICAOCOde());
        }

        @Test
        void testGetDateOfArrival() {
            // Creating a Users object with known values
            Users user = new Users("LAX", "2023-12-25");

            // Testing if the getDateOfArrival method returns the correct value
            assertEquals("2023-12-25", user.getDateOfArrival());
        }
    }
