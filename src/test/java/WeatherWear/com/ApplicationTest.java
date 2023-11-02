package WeatherWear.com;

import org.junit.jupiter.api.Test;

import org.junit.After;
import org.junit.Before;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

class ApplicationTest {
    private final ByteArrayInputStream inContent = new ByteArrayInputStream("1\n3".getBytes());
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final String expectedOutput = "WeatherWear.com\n" +
            "----------------\n" +
            "1. Recommend clothing for current location\n" +
            "2. Recommend clothing for future location\n" +
            "3. Exit\n" +
            "Enter choice: Exiting the application. Goodbye!\n";

    @Before
    public void setUp() {
        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    public void testApplicationMain() {
        Application.main(null);
        assertEquals(expectedOutput, outContent.toString());
    }
}