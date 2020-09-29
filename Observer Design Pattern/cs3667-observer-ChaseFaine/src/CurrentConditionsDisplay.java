import java.util.Observable;
import java.util.Observer;
	
public class CurrentConditionsDisplay implements Observer, DisplayElement {
	private float temperature;
	private float humidity;
    Observable observable;
	
	public CurrentConditionsDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
	}
	
	public void update(Observable observable, Object arg) {
        if (observable instanceof AppStateWeatherData) {
            AppStateWeatherData appStateWeatherData = (AppStateWeatherData)observable;
		    this.temperature = appStateWeatherData.getTemperature();
		    this.humidity = appStateWeatherData.getHumidity();
		    display();
        }
	}
	
	public void display() {
		System.out.println("Current conditions: " + temperature 
			+ "F degrees and " + humidity + "% humidity");
	}
}
