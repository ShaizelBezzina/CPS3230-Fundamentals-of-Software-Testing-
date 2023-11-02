package WeatherWear.com;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;


public class UserInput {
    public static Users getUserInputForFutureLocation(String airportICAOCode, String dateInput) {
        LocalDate currentDate = LocalDate.now();
        LocalDate dateOfArrival = null;
        boolean validDate = false;

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            dateOfArrival = LocalDate.parse(dateInput, dateFormat);
            long daysUntilArrival = ChronoUnit.DAYS.between(currentDate, dateOfArrival);

            if (daysUntilArrival <= 10) {
                validDate = true;
            }
        } catch (DateTimeParseException e) {
            validDate = false;
        }

        if (validDate) {
            return new Users(airportICAOCode, dateOfArrival.toString());
        } else {
            return null;
        }
    }
}
