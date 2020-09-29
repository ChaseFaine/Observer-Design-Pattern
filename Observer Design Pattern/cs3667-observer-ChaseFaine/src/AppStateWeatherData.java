/*
  AppStateWeatherData.java

  This is a "boundary object" for our weather software system.
  
  Our boundary object connects the rest of our system with the
  device API. This way, the other parts of our system do not
  need to fret over device particulars. We offer a nice
  and easy to use set of methods.

  Based on Head First Design Patterns
  @author Jay Fenwick
  @version 2018
*/

import java.util.Observable;
public class AppStateWeatherData extends Observable implements WeatherDataInterface {

    // Reference to actual device via WeatherData API object
    WeatherData weatherStation;

    // Our various ways of displaying weather data
    // CurrentConditionsDisplay condDisplay;
    // StatisticsDisplay statDisplay;
    // ForecastDisplay forecastDisplay;
    // HeatIndexDisplay heatIndexDisplay;
    

    public AppStateWeatherData(WeatherData station) {

	    // Setup two-way connection between us and device:
	    //  we know about them:
	    weatherStation = station;
    	//  tell them about us:
	    station.connect(this);

    	// Create our displays
        // condDisplay = new CurrentConditionsDisplay();
        // statDisplay = new StatisticsDisplay();
        // forecastDisplay = new ForecastDisplay();
        // heatIndexDisplay = new heatIndexDisplay();
    }
    
    /* WeatherDataInterface API requires this method. Since they know about us,
       when weather conditions change they can alert us. But they have to know
       how to contact us; so we implemented the WeatherDataInterface which 
       requires us to implement this method and then they know that we have
       this method available!! (Dang, interfaces are kinda cool like that!) */

    public void measurementsChanged() {

	    /* Weather station device has picked up changes in weather and
	    called this method. Now, we need to pull current values 
	    from them. */
	    float t = weatherStation.getCurrTemp();
	    float h = weatherStation.getCurrHumid();
	    float p = weatherStation.getCurrPress();

	    // With new weather condition data, we need to update our displays.
        // condDisplay.update(t, h, p);
        // statDisplay.update(t, h, p);
        // forecastDisplay.update(t, h, p);
        // heatIndexDisplay.update(t, h);
	    setChanged();
        notifyObservers();
    }
    
    public float getTemperature() {
        return weatherStation.getCurrTemp();
    }

    public float getHumidity() {
        return weatherStation.getCurrHumid();
    }

    public float getPressure() {
        return weatherStation.getCurrPress();
    }

}
