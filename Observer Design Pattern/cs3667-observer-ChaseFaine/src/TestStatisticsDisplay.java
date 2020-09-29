
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
	
public class TestStatisticsDisplay {
	
    static StatisticsDisplay sd;

    // These fields are used to redirect and capture System.out
    static PrintStream originalOut;
    static ByteArrayOutputStream baos;
    static PrintStream newOut;

    @BeforeEach
    void init() {
	sd = new StatisticsDisplay();

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
	String expectedOutput = "Avg/Max/Min temperature = " 
	    + 0.0 + "/" + 0.0 + "/" + 200.0 + "\n";

	// method writes to System.out which has been redirected
	sd.display();
	System.out.flush();
	String actualOutput = baos.toString();
	
	assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testOneReadingMaxMin() {
	String expectedOutput = "Avg/Max/Min temperature = " 
	    + 1.1 + "/" + 1.1 + "/" + 1.1 + "\n";

	// method writes to System.out which has been redirected
	sd.update(1.1f, 5.31f, 6.8f);
	System.out.flush();
	String actualOutput = baos.toString();
	
	assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testOneReadingMin() {
	String expectedOutput = "Avg/Max/Min temperature = " 
	    + 0.0 + "/" + 0.0 + "/" + 0.0 + "\n";

	// method writes to System.out which has been redirected
	sd.update(0.0f, 5.31f, 6.8f);
	System.out.flush();
	String actualOutput = baos.toString();
	
	assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testOneReadingMax() {
	String expectedOutput = "Avg/Max/Min temperature = " 
	    + 201.0 + "/" + 201.0 + "/" + 200.0 + "\n";

	// method writes to System.out which has been redirected
	sd.update(201.0f, 5.31f, 6.8f);
	System.out.flush();
	String actualOutput = baos.toString();
	
	assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testTwoReadings() {
	String expectedOutput = "Avg/Max/Min temperature = " 
	    + 9.05 + "/" + 10.1 + "/" + 8.0 + "\n";

	// method writes to System.out which has been redirected
	sd.update(8.0f, 8.0f, 6.8f);
	System.out.flush();
	
	// don't want System.out output from above update
	// so refreshing it here...
	baos = new ByteArrayOutputStream();
	newOut = new PrintStream(baos);
	System.setOut(newOut);

	sd.update(10.1f, 6.8f, 13.5f);
	String actualOutput = baos.toString();
	
	assertEquals(expectedOutput, actualOutput);
    }
}
