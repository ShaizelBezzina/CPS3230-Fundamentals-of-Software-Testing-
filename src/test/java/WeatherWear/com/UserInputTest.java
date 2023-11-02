package WeatherWear.com;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UserInputTest {

    /*@Test
    public void testGetUserInputForFutureLocationValid() {
        String airportICAOCode = "MLA";
        String dateInput = "23/12/2023";

        Users user = UserInput.getUserInputForFutureLocation(airportICAOCode, dateInput);

        assertNotNull(user);
        assertEquals(airportICAOCode, user.getAirportICAOCOde());
        assertEquals(dateInput, user.getDateOfArrival());
    }*/

    @Test
    public void testGetUserInputForFutureLocationInvalidDate() {
        String airportICAOCode = "MLA";
        String dateInput = "24/12/2023";

        Users user = UserInput.getUserInputForFutureLocation(airportICAOCode, dateInput);

        assertNull(user);
    }

    @Test
    public void testGetUserInputForFutureLocationInvalidFormat() {
        String airportICAOCode = "JLK";
        String dateInput = "2023-12-23"; // Invalid format

        Users user = UserInput.getUserInputForFutureLocation(airportICAOCode, dateInput);

        assertNull(user);
    }

    @Test
    public void testGetUserInputForFutureLocationFutureDateOutOfRange() {
        String airportICAOCode = "LHR";
        String dateInput = "01/01/2030"; // More than 10 days in the future

        Users user = UserInput.getUserInputForFutureLocation(airportICAOCode, dateInput);

        assertNull(user);
    }
}
