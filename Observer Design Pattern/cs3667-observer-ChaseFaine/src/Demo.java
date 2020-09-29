

public class Demo {
    public static void main(String[] args) {

	// Create the device object from their API
	WeatherData dataSource = new WeatherData();
	// Create our boundary object that "wraps" that data source
	//  to provide a simpler, easier to use object
	// Note that to wrap the device object we need a reference to the 
	//  the device object!
	AppStateWeatherData ourWeather = new AppStateWeatherData(dataSource);

	// Now simulate some weather!!
	// Data point #1
	System.out.println();
	dataSource.setMeasurements(82, 88, 30.4f);


	// Ooooo, conditions are changing!!
	System.out.println();
	dataSource.setMeasurements(78, 65, 29.2f);


	// Weather is still changing...
	System.out.println();
	dataSource.setMeasurements(65, 58, 29.0f);
	System.out.println();
    }
}
