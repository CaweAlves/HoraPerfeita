package com.cawe.horaperfeita.application.services;

import com.cawe.horaperfeita.infrastructure.external.data.WeatherData;
import com.cawe.horaperfeita.infrastructure.external.interfaces.IForecast;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WeatherService {

    @Autowired
    private final IForecast forecast;

    public Map<String, Double> getSevenDaysForecast(String latitude, String longitude) {
        forecast.addTemperature();
        forecast.addLatitudeAndLongitude(latitude, longitude);
        return this.groupTimeAndTemperature(forecast.getWeatherForecast());
    }

    public Map<String, Double> groupTimeAndTemperature(WeatherData weatherForecast) {
        List<String> times = weatherForecast.getHourly().getTime();
        List<Double> temperatures = weatherForecast.getHourly().getTemperature2m();

        Map<String, Double> days = new HashMap<>();

        for (Integer count = 0; count < times.stream().count(); count++) {
            days.put(times.get(count), temperatures.get(count));
        }

        return days;
    }

}
