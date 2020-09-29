
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
	
public class TestAppStateWeatherData {
    static WeatherData wd;
    static AppStateWeatherData asuWD;
	
    // These fields are used to redirect and capture System.out
    static PrintStream originalOut;
    static ByteArrayOutputStream baos;
    static PrintStream newOut;

    @BeforeEach
    void init() {
	wd = new XxxWeatherData();
	asuWD = new AppStateWeatherData(wd);

	originalOut = System.out;     // save to restore later

	baos = new ByteArrayOutputStream();
	newOut = new PrintStream(baos);
	System.setOut(newOut);
    }

    @AfterEach
    void tearDown() {
	System.setOut(originalOut);  // restore 
    }

    @Test
    void testMeasurementsChanged() {
	String expectedOutput = "Current conditions: " + 1.1 
	    + "F degrees and " + 2.2 + "% humidity\n"
	    + "Avg/Max/Min temperature = " 
	    + 1.1 + "/" + 1.1 + "/" + 1.1 + "\n"
	    + "Forecast: 2+ measurements needed to forecast\n";	

	// method writes to System.out which has been redirected
	asuWD.measurementsChanged();

	System.out.flush();
	String actualOutput = baos.toString();
	
	assertEquals(expectedOutput, actualOutput);
    }

}

class XxxWeatherData extends WeatherData {

    public float getCurrTemp() { 	return 1.1f;    }
    public float getCurrHumid() {	return 2.2f;    }
    public float getCurrPress() {	return 3.3f;    }

}
