import java.util.Observable;
import java.util.Observer;

public class ForecastDisplay implements Observer, DisplayElement {
    private float currentPressure = -1;
    private float lastPressure = -1;
    Observable observer;

    public ForecastDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    public void update(Observable observable, Object arg) {
        if (observable instanceof AppStateWeatherData) {
            AppStateWeatherData appStateWeatherData = (AppStateWeatherData)observable;
	        lastPressure = currentPressure;
	        currentPressure = appStateWeatherData.getPressure();
	        display();
        }
    }

    public void display() {
	System.out.print("Forecast: ");
	
	if (lastPressure < 0) {
	    System.out.println("2+ measurements needed to forecast");
	}
	else if (currentPressure > lastPressure) {
	    System.out.println("Improving weather on the way!");
	} else if (currentPressure == lastPressure) {
	    System.out.println("More of the same");
	} else if (currentPressure < lastPressure) {
	    System.out.println("Watch out for cooler, rainy weather");
	}
    }
}
