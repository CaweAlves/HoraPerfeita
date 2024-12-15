package com.cawe.horaperfeita.application.services;

import com.cawe.horaperfeita.infrastructure.external.data.WeatherData;
import com.cawe.horaperfeita.infrastructure.external.interfaces.IForecast;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class WeatherService {

    @Autowired
    private final IForecast forecast;

    public Map<String, List<Double>> getSevenDaysForecast(String latitude, String longitude) {
        forecast.addTemperatureAndRain();
        forecast.addLatitudeAndLongitude(latitude, longitude);
        return this.groupTimeAndTemperatureAndRain(forecast.getWeatherForecast());
    }

    public Map<String, BigDecimal> getTemperatureSevenDaysForecast(String latitude, String longitude) {
        forecast.addTemperature();
        forecast.addLatitudeAndLongitude(latitude, longitude);
        return this.groupTimeAndTemperature(forecast.getWeatherForecast());
    }

    private Map<String, BigDecimal> groupTimeAndTemperature(WeatherData weatherForecast) {
        List<String> times = weatherForecast.getHourly().getTime();
        List<Double> temperatures = weatherForecast.getHourly().getTemperature2m();

        Map<String, BigDecimal> days = new HashMap<>();

        for (Integer count = 0; count < times.stream().count(); count++) {
            days.put(times.get(count), BigDecimal.valueOf(temperatures.get(count)));
        }

        return days;
    }

    private Map<String, List<Double>> groupTimeAndTemperatureAndRain(WeatherData weatherForecast) {
        List<String> times = weatherForecast.getHourly().getTime();
        List<Double> temperatures = weatherForecast.getHourly().getTemperature2m();
        List<Double> rains = weatherForecast.getHourly().getRain();

        Map<String, List<Double>> days = new LinkedHashMap<>();

        for (Integer count = 0; count < times.stream().count(); count++) {
            List<Double> valueDays = new ArrayList<>();
            valueDays.add(temperatures.get(count));
            valueDays.add(rains.get(count));
            days.put(times.get(count), valueDays);
        }

        return days;
    }

}
