package edu.iu.habahram.weathermonitoring.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StatisticsDisplay implements Observer, DisplayElement {
    private List<Float> temperatures;
    private List<Float> humiditys;
    private List<Float> pressures;
    private Subject weatherData;

    public StatisticsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        this.temperatures = new ArrayList<>();
    }

    @Override
    public String display() {
        String html = "";

        float sum = 0;
        float min = 100;
        float max = 0;
        for (Float number : temperatures) {
            sum += number;
            if (number < min) {
                min = number;
            }
            if (number > max) {
                max = number;
            }
        }

        float average = (float) ((double) sum / temperatures.size());
        html += "<h1>Weather Stats</h1>";
        html += "<li>Avg. Temp: " + average + "</li>";
        html += "<li>Min. Temp: " + min + "</li>";
        html += "<li>Max. Temp: " + max + "</li>";
        return html;
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperatures.add(temperature);
        this.humiditys.add(humidity);
        this.pressures.add(pressure);
    }

    @Override
    public String name() {
        return "Statistics";
    }

    @Override
    public String id() {
        return "statistics";


    }

    public void subscribe() {
        weatherData.registerObserver(this);
    }

    public void unsubscribe() {
        weatherData.removeObserver(this);
    }
}
