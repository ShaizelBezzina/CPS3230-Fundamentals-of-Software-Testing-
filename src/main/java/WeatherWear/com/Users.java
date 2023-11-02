package WeatherWear.com;

public class Users {

    String AirportICAOCOde;
    String DateOfArrival;

    public Users(String AirportICAOCOde, String DateOfArrival) {
        this.AirportICAOCOde = AirportICAOCOde;
        this.DateOfArrival = DateOfArrival;
    }

    public String getAirportICAOCOde() {
        return AirportICAOCOde;
    }

    public void setAirportICAOCOde(String AirportICAOCOde) {
        this.AirportICAOCOde = AirportICAOCOde;
    }

    public String getDateOfArrival() {
        return DateOfArrival;
    }

    public void setDateOfArrival(String DateOfArrival) {
        this.DateOfArrival = DateOfArrival;
    }
}