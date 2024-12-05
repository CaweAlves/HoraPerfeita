package com.cawe.horaperfeita.application.services;

import com.cawe.horaperfeita.infrastructure.external.data.WeatherData;
import com.cawe.horaperfeita.infrastructure.external.interfaces.IForecast;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {

    @Autowired
    private final IForecast forecast;

    public WeatherData getSevenDaysForecast(String latitude, String longitude) {
        forecast.addTemperature();
        forecast.addLatitudeAndLongitude(latitude, longitude);
        String weatherForecast = this.forecast.getWeatherForecast();
        return this.toWeatherData(weatherForecast);
    }

    private WeatherData toWeatherData(String weatherForecast) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            WeatherData weatherData = objectMapper.readValue(weatherForecast, WeatherData.class);
            return weatherData;
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
