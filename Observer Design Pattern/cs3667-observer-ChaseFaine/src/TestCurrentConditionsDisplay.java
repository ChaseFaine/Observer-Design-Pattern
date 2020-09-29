
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
	
public class TestCurrentConditionsDisplay {
	
    static CurrentConditionsDisplay ccd;

    // These fields are used to redirect and capture System.out
    static PrintStream originalOut;
    static ByteArrayOutputStream baos;
    static PrintStream newOut;

    @BeforeEach
    void init() {
	ccd = new CurrentConditionsDisplay();

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
	String expectedOutput = "Current conditions: " + 0.0 
	    + "F degrees and " + 0.0 + "% humidity\n";

	// method writes to System.out which has been redirected
	ccd.display();
	System.out.flush();
	String actualOutput = baos.toString();
	
	assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testOneUpdate() {
	String expectedOutput = "Current conditions: " + 13.5 
	    + "F degrees and " + 5.31 + "% humidity\n";

	// method writes to System.out which has been redirected
	ccd.update(13.5f, 5.31f, 6.8f);
	System.out.flush();
	String actualOutput = baos.toString();
	
	assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testTwoUpdates() {
	String expectedOutput = "Current conditions: " + 5.31
	    + "F degrees and " + 6.8 + "% humidity\n";

	// method writes to System.out which has been redirected
	ccd.update(13.5f, 5.31f, 6.8f);
	System.out.flush();
	
	// don't want System.out output from above update
	// so refreshing it here...
	baos = new ByteArrayOutputStream();
	newOut = new PrintStream(baos);
	System.setOut(newOut);

	ccd.update(5.31f, 6.8f, 13.5f);
	String actualOutput = baos.toString();
	
	assertEquals(expectedOutput, actualOutput);
    }
}
