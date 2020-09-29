
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
	
public class TestForecastDisplay {
	
    static ForecastDisplay ccd;

    // These fields are used to redirect and capture System.out
    static PrintStream originalOut;
    static ByteArrayOutputStream baos;
    static PrintStream newOut;

    @BeforeEach
    void init() {
	ccd = new ForecastDisplay();

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
    void testDisplayAfterConstructor() {
	String expectedOutput = "Forecast: 2+ measurements needed to forecast\n";

	// method writes to System.out which has been redirected
	ccd.display();
	System.out.flush();
	String actualOutput = baos.toString();
	
	assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testOneUpdate() {
	String expectedOutput = "Forecast: 2+ measurements needed to forecast\n";

	// method writes to System.out which has been redirected
	ccd.update(13.5f, 5.31f, 6.8f);
	System.out.flush();
	String actualOutput = baos.toString();
	
	assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testTwoUpdatesIncreasing() {
	String expectedOutput = "Forecast: Improving weather on the way!\n";

	// method writes to System.out which has been redirected
	ccd.update(13.5f, 5.31f, 6.8f);
	System.out.flush();
	
	// don't want System.out output from above update
	// so refreshing it here...
	baos = new ByteArrayOutputStream();
	newOut = new PrintStream(baos);
	System.setOut(newOut);

	ccd.update(15.1f, 6.8f, 13.5f);
	String actualOutput = baos.toString();
	
	assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testTwoUpdatesDecreasing() {
	String expectedOutput = "Forecast: Watch out for cooler, rainy weather\n";

	// method writes to System.out which has been redirected
	ccd.update(13.5f, 5.31f, 6.8f);
	System.out.flush();
	
	// don't want System.out output from above update
	// so refreshing it here...
	baos = new ByteArrayOutputStream();
	newOut = new PrintStream(baos);
	System.setOut(newOut);

	ccd.update(15.1f, 6.8f, 5.31f);
	String actualOutput = baos.toString();
	
	assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testTwoUpdatesEquals() {
	String expectedOutput = "Forecast: More of the same\n";

	// method writes to System.out which has been redirected
	ccd.update(13.5f, 5.31f, 6.8f);
	System.out.flush();
	
	// don't want System.out output from above update
	// so refreshing it here...
	baos = new ByteArrayOutputStream();
	newOut = new PrintStream(baos);
	System.setOut(newOut);

	ccd.update(15.1f, 6.8f, 6.8f);
	String actualOutput = baos.toString();
	
	assertEquals(expectedOutput, actualOutput);
    }
}
