import java.util.Observer;
import.java.util.Observable;
public class HeatIndexDisplay implements Observer, DisplayElement {
    float heatIndex;
    float t;
    float rh;
    Observable observable;

    public HeatIndexDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    public void update(Observable observable, Object arg) {
        if (observable instanceof AppStateWeatherData) {
            AppStateWeatherData appStateWeatherData = (AppStateWeatherData)observable;
            t = appStateWeatherData.getTemperature();
            rh = appStateWeatherData.getHumidity();
            heatIndex = computeHeatIndex(t, rh);
            display();
        }
    }

    float computeHeatIndex(float t, float rh) {
        float index = (float)((16.923 + (0.185212 * t) + (5.37941 * rh) - (0.100254 * t * rh) 
        + (0.00941695 * (t * t)) + (0.00728898 * (rh * rh)) 
        + (0.000345372 * (t * t * rh)) - (0.000814971 * (t * rh * rh)) +
        (0.0000102102 * (t * t * rh * rh)) - (0.000038646 * (t * t * t)) + (0.0000291583 * 
        (rh * rh * rh)) + (0.00000142721 * (t * t * t * rh)) + 
        (0.000000197483 * (t * rh * rh * rh)) - (0.0000000218429 * (t * t * t * rh * rh)) +
        0.000000000843296 * (t * t * rh * rh * rh)) -
        (0.0000000000481975 * (t * t * t * rh * rh * rh)));
        return index;
    }

    public void display() {
        System.out.println("The Heat Index is: " + heatIndex);
    }
}


